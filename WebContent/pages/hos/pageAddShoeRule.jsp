<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:DataDict type="roster" var="roster"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 用户管理 <span>> 添加消毒鞋方式设定 </span>
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
						<form id="searchForm" role="form" class="form-inline"></form>
						<hr>
						<div class="btn-group " id="hosShoesTableToolbar" role="group">
							<button type="button" class="btn btn-default"
								onclick="setRule();">
								<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 设定
							</button>
						</div>
						<table id="addSterilizerContainerTable"
							data-mobile-responsive="true"></table>
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
	var jqTableDom = $("#addSterilizerContainerTable");
	function initData() {
		var jqFormDom = $("#searchForm");
		var jqToolbarDom = $("#hosShoesTableToolbar");
		var url = "hosSterilizer/selectAddShoeRule";
		var columns = [ {
			checkbox : true
		}, {
			field : 'name',
			title : '名字',
			width : 60,
			sortable : true
		}, {
			field : 'description',
			title : '备注',
			width : 180,
			sortable : true
		}, ];
		bstable = zfesBstable.laodTable(jqTableDom, jqFormDom, jqToolbarDom,
				url, columns);
	}
	$(function() {
		initData();
	})
	function setRule() {
		if (zfesBstable.isOneRow(jqTableDom)) {
			var row = zfesBstable.getSelections(jqTableDom);
			var code = row[0].code;
			var yesOrNoSize = row[0].yesOrNoSize;
			if (yesOrNoSize == 1) {//按尺码添加，需要指定尺码
				var url= "hosSterilizer/addShoeRuleSetSize?code="+code;
				$("#ruleModal").modal({
				    remote: url
				});
			} else {
				var ajaxUrl = "hosSterilizer/addShoeRule";
				var param = { "code" : code};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successText(data.message);
				});
			}
			
		}
	}
</script>