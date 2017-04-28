<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<t:DataDict type="roster" var="roster"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 
				物资管理 
			<span>> 
				黑名单管理
			</span>
		</h1>
	</div>
</div>
<section id="widget-grid">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-editbutton="false">
			<!-- -->	<header>
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2>名单列表</h2>
				</header> 
			
				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
		                <form id="searchForm" role="form" class="form-inline">
		                
		                	 <div class="form-group">
		                         <label>姓名</label>
		                         <label class="select">
		                            <input type="text" name="userName" class="form-control"/> 
                    		     </label>
		                    </div>
		                    <input type="hidden" value="1" name="type"><!-- 1代表黑名单，0代表灰名单 -->
		                    <input type="hidden" value="1" name="enabled" id="enabled"><!-- 1代表有效 -->
		                    <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
		                </form>
		                <hr>
		                
		                
					  	  <div class="btn-group " id="hosShoesTableToolbar" role="group">
                                 <button type="button" class="btn btn-default" onclick="edit();">
                                        <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 移出名单
                                 </button>
                                 <button type="button" id="selectAll" data-state="1" class="btn btn-default" onclick="selectAll();">历史查询</button>
                                  <button type="button" class="btn btn-default display-cancel" display="0">取消大屏显示</button>
                                  <button type="button" class="btn btn-default display-set" display="1">设置大屏显示</button>
                           </div>
							<table  id="blackListRuleTable" data-mobile-responsive="true" ></table>
						</div>
					</div>
				</div>
		</article>
	</div>
</section>

<!-- Dynamic Modal -->  
<div class="modal fade" id="ruleModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">  
    <div class="modal-dialog">  
        <div class="modal-content" id="modal-content">
        	
        </div>  
    </div>  
</div>  



<script>

var jqTableDom=$("#blackListRuleTable");
function initData(){
	var jqFormDom=$("#searchForm");
	var jqToolbarDom=$("#hosShoesTableToolbar");
	var url="hosBlack/selectBlacklistSet";
	var flag=$("#enabled").val();
	var columns=[];
	if(flag==1){
		 columns=[
			{checkbox:true},
//				{field:'id',title:'编号',width:80},
			{field:'userName',title:'姓名',width:120},
			{field:'type',title:'名单类型',width:120,formatter:dictFormat('roster')},
			{field:'creatorTime',title:'时间',width:120,sortable:true},
			{field:'enabled',title:'是否有效',width:120,formatter:zfesUtil.formatYesNo},
			{field:'display',title:'是否显示在大屏幕',width:120,formatter:zfesUtil.formatYesNo}
		];
	}else{ 
		columns=[
			{checkbox:true},
//				{field:'id',title:'编号',width:80},
			{field:'userName',title:'姓名',width:120},
			{field:'type',title:'名单类型',width:120,formatter:dictFormat('roster')},
			{field:'creatorTime',title:'创建时间',width:120,sortable:true},
			{field:'outTime',title:'移出时间',width:120,sortable:true},
			{field:'operationName',title:'操作人员',width:120,sortable:true},
			{field:'enabled',title:'是否有效',width:120,formatter:zfesUtil.formatYesNo},
		];
	}
	bstable=zfesBstable.laodTable(jqTableDom,jqFormDom,jqToolbarDom,url,columns);
}

$(function(){
	initData();
	attachModalHidden();
});
function attachModalHidden(){
	$("#ruleModal").on('hide.bs.modal', function () {
		zfesBstable.refresh(jqTableDom);
	});
}
function search(){
	zfesBstable.reload(jqTableDom);
}
function selectAll(){
	if($("#selectAll").attr("data-state")==1){
		$("#enabled").val(null);
		$("#selectAll").html("查询有效");
		$("#selectAll").attr("data-state",0);
	}else{
		$("#enabled").val(1);
		$("#selectAll").html("历史查询");
		$("#selectAll").attr("data-state",1);
	}
	initData();
}
function edit(){
	if(zfesBstable.isOneRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		var id = row[0].id;
		var userId = row[0].userId;
		var url="hosBlack/updateHosBacklist";
		var params = {"id":id,
				"userId":userId};
		
		zfesAjax.ajaxTodo(url, params, function(data) {
			alertSwal.successText(data.message);
			zfesBstable.refresh(jqTableDom);
		});
	}
}
//设置显示在大屏
$(".display-set").click(function(){
	var displayScreen = $(this).attr("display");
	var row = zfesBstable.getSelections(jqTableDom);
	for (var i = 0; i < row.length; i++) {
		if (row[i].display == '1') {
			var warn = row[i].userName + "显示在大屏幕";
			alertSwal.warningTitle(warn);
			return false;
		}
	}
	var ids = zfesBstable.getRowIds(jqTableDom);
			var ajaxUrl = "hosBlack/displayScreenHosBacklist";
			var param = {
				"ids" : ids.join(','),
				"display" : displayScreen
			};
			zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
				setTimeout(function() {
					alertSwal.successText(data.message);
					zfesBstable.reload(jqTableDom);
				}, 100);
			});
});
//设置不显示在大屏
$(".display-cancel").click(function(){
	var displayScreen = $(this).attr("display");
	var row = zfesBstable.getSelections(jqTableDom);
	for (var i = 0; i < row.length; i++) {
		if (row[i].display == '0') {
			var warn = row[i].userName + "未显示在大屏幕";
			alertSwal.warningTitle(warn);
			return false;
		}
	}
	var ids = zfesBstable.getRowIds(jqTableDom);
			var ajaxUrl = "hosBlack/displayScreenHosBacklist";
			var param = {
				"ids" : ids.join(','),
				"display" : displayScreen
			};
			zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
				setTimeout(function() {
					alertSwal.successText(data.message);
					zfesBstable.reload(jqTableDom);
				}, 100);
			});
});























</script>