<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 
				权限管理 
			<span>> 
				权限管理
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
					<h2>权限列表</h2>
				</header> 
			
				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
					<form id="searchForm" role="form" class="form-inline">
					
					
		                    <div class="form-group">
		                         <label>权限名称</label>
		                         <input name="name" type="text" placeholder="" class="form-control">
		                    </div>
		                    <div class="form-group">
		                    <label>权限代码</label>
		                         <input name="permCode" id="permCode" type="text" placeholder="" class="form-control">
		                    </div>
		                     <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
		                </form>
		                
		                
		                <hr>
		                
		                
					  	  <div class="btn-group " id="authPermTableToolbar" role="group">
								<button type="button" class="btn btn-default" onclick="add();">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加
                                 </button>
								 <button type="button" class="btn btn-default" onclick="edit();">
                                        <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改
                                 </button>
                                 <button type="button" class="btn btn-default" onclick="cancel();">
                                        <i class="glyphicon glyphicon-remove" aria-hidden="true"></i> 注销
                                 </button>
                                 <button type="button" class="btn btn-default" onclick="activate();">
                                        <i class="glyphicon glyphicon-ok" aria-hidden="true"></i> 启用
                                 </button>
                           </div>
							<table id="ahthPermBootstrapTable" data-mobile-responsive="true" ></table>
						</div>
					</div>
				</div>
		     </article>
		</div>
</section>

<!-- Dynamic Modal -->  
<div class="modal fade" id="permissionModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">  
    <div class="modal-dialog">  
        <div class="modal-content" id="modal-content">
        	
        </div>  
    </div>  
</div>  



<script>

var jqTableDom=$("#ahthPermBootstrapTable");
function initData(){
	var jqFormDom=$("#searchForm");
	var jqToolbarDom=$("#authPermTableToolbar");
	var url="authPermission/queryAuthPermSet";
	var columns=[
					{checkbox:true},
					{field:'name',title:'权限名称',width:160,sortable:true},
					{field:'permCode',title:'权限编码',width:160},
					{field:'enabled',title:'权限状态',width:60,formatter:zfesUtil.formatBool},
					{field:'description',title:'描述',width:200}
				];
	bstable=zfesBstable.laodTable(jqTableDom,jqFormDom,jqToolbarDom,url,columns);
}
function attachModalHidden(){
	$("#permissionModal").on('hide.bs.modal', function () {
		zfesBstable.refresh(jqTableDom);
	});
}
$(function(){
	initData();
	attachModalHidden();
});

function search(){
	zfesBstable.reload(jqTableDom);
}

function add(){
	$("#permissionModal").modal({
	    remote: "authPermission/enterAuthPermAdd"
	});
}

function edit(){
	 if(zfesBstable.isOneRow(jqTableDom)){
		 var id=zfesBstable.getRowId(jqTableDom);
		 $("#permissionModal").modal({
			    remote: "authPermission/enterAuthPermEdit?id="+id
			});
    }
}

function cancel(){
	  if(zfesBstable.isOneRow(jqTableDom)){
			var row = zfesBstable.getSelections(jqTableDom);
			 if(row[0].enabled=='0'){
						alertSwal.warningTitle("该权限已经是停用状态");
						 return false;
					 }
			var id=zfesBstable.getRowId(jqTableDom);
			 alertSwal.confirm("注销","是否注销该权限",function(isComfirm){
				 if(isComfirm){
					 var ajaxUrl = "authPermission/disableAuthPerm";
						var param = {"id" : id};
						zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						alertSwal.successText(data.message);
			 			zfesBstable.refresh(jqTableDom);
					}); 
				 }
			});
			}
}

function activate(){
	if(zfesBstable.isOneRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		if(row[0].enabled=='1'){
			alertSwal.warningTitle("该权限已经是启用状态");
			 return false;
		}
		var id = zfesBstable.getRowId(jqTableDom);
		 alertSwal.confirm("启用","是否启用该权限",function(isComfirm){
			 if(isComfirm){
				 var ajaxUrl = "authPermission/enableAuthPerm";
					var param = { "id" : id};
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						alertSwal.successText(data.message);
		 			zfesBstable.refresh(jqTableDom);
				}); 
			 }
	});
}
}

//-------------------------------11-25-批量注销启用 -------------------------

function cancel(){
	  if(zfesBstable.isSelectedRow(jqTableDom)){
			var row = zfesBstable.getSelections(jqTableDom);
			for(var i=0;i<row.length;i++){
				 if(row[i].enabled=='0'){
					 var warn = row[i].name+"权限已经是停用状态";
					 alertSwal.warningTitle(warn);
					 return false;
				 }
			}
			var ids=zfesBstable.getRowIds(jqTableDom);
			 alertSwal.confirm("注销","是否注销权限",function(isComfirm){
				 if(isComfirm){
					var ajaxUrl = "authPermission/disableBatchAuthPerm";
					var param = {"ids" : ids.join(',')};
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						alertSwal.successText(data.message);
			 			zfesBstable.refresh(jqTableDom);
					}); 
				 }
			});
			}
}

function activate(){
	if(zfesBstable.isSelectedRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		for(var i=0;i<row.length;i++){
			 if(row[i].enabled=='1'){
				 var warn = row[i].name+"权限已经是启用状态";
				 alertSwal.warningTitle(warn);
				 return false;
			 }
		}
		var ids = zfesBstable.getRowIds(jqTableDom);
		 alertSwal.confirm("启用","是否启用权限",function(isComfirm){
			 if(isComfirm){
				var ajaxUrl = "authPermission/enableBatchAuthPerm";
				var param = { "ids" : ids.join(',')};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successText(data.message);
		 			zfesBstable.refresh(jqTableDom);
				}); 
			 }
	});
	}
}

</script>