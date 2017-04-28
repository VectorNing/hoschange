<%@ page contentType="text/html; charset=utf-8" language="java"%>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 
				权限管理 
			<span>> 
				菜单管理
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
					<h2>菜单列表</h2>
				</header> 
			
				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
					<form id="searchForm" role="form" class="form-inline">
					
					
		                    <div class="form-group">
		                         <label>菜单名称</label>
		                         <input name="name" type="text" placeholder="" class="form-control">
		                    </div>
		                    <div class="form-group">
		                    <label>编码</label>
		                         <input name="permCode" id="permCode" type="text" placeholder="" class="form-control">
		                    </div>
		                     <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
		                </form>
		                
		                
		                <hr>
		                
		                
					  	  <div class="btn-group " id="authPermTableToolbar" role="group">
                                <!-- <a href="pages/auth/respAddUser.jsp" data-toggle="modal" data-target="#permissionModal" class="btn btn-default">
									<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>角色
								</a> -->
								<button type="button" class="btn btn-default" onclick="add();">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加
                                 </button>
								 <button type="button" class="btn btn-default" onclick="edit();">
                                        <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改
                                 </button>
                                    <button type="button" class="btn btn-default">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> 删除
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
	var url="";
	var columns=[
					{checkbox:true},
					{field:'name',title:'菜单名称',width:160},
					{field:'parentId',title:'上级菜单',width:160},
					{field:'ordercol',title:'排列顺序',width:60},
					{field:'description',title:'描述',width:200}
				];
	bstable=zfesBstable.laodTable(jqTableDom,jqFormDom,jqToolbarDom,url,columns);
}
$(function(){
	initData();
});

function search(){
	zfesBstable.reload(jqTableDom);
}

function add(){
	$("#permissionModal").modal({
	    remote: "pages/auth/respMenuAdd.jsp"
	}).modal('show').on('hide.bs.modal', function () {
		zfesBstable.reload(jqTableDom);
	});;
}

function edit(){
	$("#permissionModal").modal({
	    remote: "pages/auth/respMenuEdit.jsp"
	}).modal('show').on('hide.bs.modal', function () {
		zfesBstable.reload(jqTableDom);
	});;
}

function cancel(){
	  if(zfesBstable.isOneRow(jqTableDom)){
			var row = zfesBstable.getSelections(jqTableDom);
			 if(row[0].enabled=='0'){
						alertSwal.warningTitle("该权限已经是停用状态");
						 return false;
					 }
			var id=zfesBstable.getRowId(jqTableDom);
			var flag="0";
			 alertSwal.confirm("注销","是否注销该权限",function(){
			       layer.close(layer.index);
				 	var ajaxUrl = host_auth+"auth/authPermission/updatePermissionStatus";
					var param = { "id" : id,"flag":flag};
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						alertSwal.successText(data.message);
		 			layer.close(layer.index);
		 			zfesBstable.refresh(jqTableDom);
					},layer.close(layer.index)); 
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
		var flag="1";
		 alertSwal.confirm("启用","是否启用该权限",function(){
		       layer.close(layer.index);
			 	var ajaxUrl = host_auth+"auth/authPermission/updatePermissionStatus";
				var param = { "id" : id,"flag":flag};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successText(data.message);
	 			layer.close(layer.index);
	 			zfesBstable.refresh(jqTableDom);
				}); 
	});
	}
}
</script>