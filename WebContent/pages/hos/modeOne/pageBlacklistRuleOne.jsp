<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:DataDict type="roster" var="roster"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 用户管理 <span>> 黑灰名单规则维护 </span>
		</h1>
	</div>
</div>
<section id="widget-grid">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0"
				data-widget-editbutton="false">
				<!-- -->
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2>规则列表</h2>
				</header>
				<input type="hidden" id="modeStr" value="${mode }">

				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
						<form id="searchForm" role="form" class="form-inline">
						<input type="hidden" id="sys-configName" value="${sysConfig.configName}"/>
						<input type="hidden" id="sys-config" value="${sysConfig.config}"/>
						<input type="hidden" id="sys-id" value="${sysConfig.id}"/>
							<div class="form-group">
								<label>是否启用黑名单:</label>
								<select class="form-control" id="config" name="config" style="width: 170px">
									<option value="1">启用</option>
									<option value="0">禁用</option>
								</select>
							</div>
						  
						</form>
						<hr>


						<div class="btn-group " id="hosShoesTableToolbar" role="group">

							<c:choose>
								<c:when test="${mode==1 }">
									<button type="button" class="btn btn-default" onclick="changeMode();">
										<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
										模式一
									</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-default" onclick="changeMode();">
										<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
										模式二
									</button>
								</c:otherwise>
							</c:choose>
							

							<button type="button" class="btn btn-default" onclick="edit();">
								<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
								修改规则
							</button>
						</div>
						<table id="blackListRuleTable" data-mobile-responsive="true"></table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

<!-- Dynamic Modal -->
<div class="modal fade" id="ruleModal" tabindex="-1" role="dialog"
	aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" id="modal-content"></div>
	</div>
</div>



<script>
	var jqTableDom = $("#blackListRuleTable");
	function initData1() {
		//window.location.reload();
		var jqFormDom = $("#searchForm");
		var jqToolbarDom = $("#hosShoesTableToolbar");
		var url = "hosBlack/selectBlackListRuleSetM1";
		var columns = [ {
			checkbox : true
		},
		// 					{field:'id',title:'编号',width:80},
		{
			field : 'rosterType',
			title : '名单类型',
			width : 120,
			formatter : dictFormat('roster')
		}, {
			field : 'sums',
			title : '次数限制',
			width : 120
		}, {
			field : 'latelyNum',
			title : '最近次数',
			width : 120
		}, {
			field : 'description',
			title : '描述',
			width : 220
		} ];
		bstable = zfesBstable.laodTable(jqTableDom, jqFormDom, jqToolbarDom,
				url, columns);
	}
	//模式二加载
	function initData2() {
		//window.location.reload();
		var jqFormDom = $("#searchForm");
		var jqToolbarDom = $("#hosShoesTableToolbar");
		var url = "hosBlack/selectBlackListRuleSetM2";
		var columns = [ {
			checkbox : true
		},
		//	 					{field:'id',title:'编号',width:50},
		{
			field : 'rosterType',
			title : '名单类型',
			width : 100,
			formatter : dictFormat('roster')
		}, {
			field : 'sums',
			title : '次数限制',
			width : 100
		}, {
			field : 'rosterTime',
			title : '时间段限制(天)',
			width : 100
		}, {
			field : 'description',
			title : '描述',
			width : 280
		} ];
		bstable = zfesBstable.laodTable(jqTableDom, jqFormDom, jqToolbarDom,
				url, columns);
	}
function initData(){
	var mode = $("#modeStr").val();
	if (mode == 1) {
		initData1();
	} else {
		initData2();
	}
	
	attachModalHidden();
}
	$(function() {
		var defaultConfig = $("#sys-config").val();
		$("#config").val(defaultConfig);
		if(defaultConfig==0){//1代表启用0代表未启用
			$("#hosShoesTableToolbar").hide();
		}else{
			initData();
		}
		$("#config").change(function(){
			var configName = $("#sys-configName").val();
			var id = parseInt($("#sys-id").val());
			var config = $("#config").val();
			var text = $("#config").find('[value="'+config+'"]').text();
			alertSwal.confirm("警告","确定要"+text+"灰/黑名单吗？",function(isConfirm){
				if(isConfirm){
					var ajaxUrl = "sys/updateSysConfig";
					var param = {id:id,config:config,configName:configName};
					zfesAjax.ajaxTodo(ajaxUrl,param,function(data){
						document.location.reload();
					});
				}else{
					$("#config").val(defaultConfig);
				}
			});
		});
	});
	function attachModalHidden() {
		$("#ruleModal").on('hide.bs.modal', function() {
			zfesBstable.refresh(jqTableDom);
		});
	}
	function search() {
		zfesBstable.reload(jqTableDom);
	}

	function edit() {
		var mode = $("#modeStr").val();
		if (zfesBstable.isOneRow(jqTableDom)) {
			var row = zfesBstable.getSelections(jqTableDom);
			var id = row[0].id;
			var url = "hosBlack/enterEditBlacklistRuleM1?id=" + id+"&mode="+mode;
			$("#ruleModal").modal({
				remote : url
			});
		}
	}
	
	 function changeMode(){
		var url = "hosBlack/enterBlacklistRuleChangeMode";
		$("#ruleModal").modal({
			remote : url
		});
	};
</script>