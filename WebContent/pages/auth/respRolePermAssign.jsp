<%@ page contentType="text/html; charset=utf-8" language="java"%>
 <div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">分配权限</h4>
</div>
 <input type="hidden" id="user_id_assign" value="${roleId}"/>
 <div class="row" style="padding: 10px;">
 <div  class="col-md-6">
  <div class="box">
  <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-editbutton="false">
			<!-- -->	<header>
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2>属于角色的权限列表</h2>
				</header> 
            <div class="box-header with-border">
               <form id="authRoleSearchFormA" role="form" class="form-inline">
<!--               	<h4>属于用户的角色</h4>  -->
                    <div class="form-group">
                    <label>权限名称</label>
                         <input name="name" id="name" type="text" placeholder="" class="form-control" style="width: 170px;">
                    </div>
                      <div class="form-group">
                    <!-- <label>是否启用</label>
                          <select class="form-control" name="enabled" style="width: 170px;">
                        		  <option value="">请选择</option>
                                  <option value="1">启用</option>
                                  <option value="0">禁用</option>
                           </select> -->
                           &nbsp;&nbsp;&nbsp;&nbsp;
                  <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
                    </div> 
                    <hr>
                </form>
            </div>
 <div class="box-body">
 	<div class="row">
 	  <div class="col-md-12">
 	                           <div class="btn-group " id="tableToolbarA" role="group">
                                    <button type="button" class="btn btn-default" onclick="removeFromPerm();">
                                        <i class="glyphicon glyphicon-minus" aria-hidden="true"></i> 移除
                                    </button>
                           </div> 
                                
           <table id="ahthRoleTableA" data-mobile-responsive="true" ></table>
 	  </div>
 	</div>
 </div>
</div>
 </div></div>
 <div  class="col-md-6">
   <div class="box">
   <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-editbutton="false">
			<!-- -->	<header>
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2>不属于角色的权限列表</h2>
				</header> 
            <div class="box-header with-border">
               <form id="authRoleSearchFormB" role="form" class="form-inline">
                    <div class="form-group">
                    <label>权限名称</label>
                         <input name="name" id="name" type="text" placeholder="" class="form-control" style="width: 170px;">
                    </div>
                    <div class="form-group">
                    <!-- <label>是否启用</label>
                          <select class="form-control" name="enabled" style="width: 170px;">
                        		  <option value="">请选择</option>
                                  <option value="1">启用</option>
                                  <option value="0">禁用</option>
                           </select> -->
                     &nbsp;&nbsp;&nbsp;&nbsp;
                     <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
                    </div>
                </form>
                <hr>
            </div>
 <div class="box-body">
 	<div class="row">
 	  <div class="col-md-12">
 	                       <div class="btn-group " id="tableToolbarB" role="group">
                                    <button type="button" class="btn btn-default" onclick="addToPerm();">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加
                                    </button>
                           </div> 
                                
           <table id="ahthRoleTableB" data-mobile-responsive="true" ></table>
 	  </div>
 	</div>
 </div>
</div>
</div></div>
<script>
// var param=zfesLayerEditDg.getLayerParam();
var roleId=$("#user_id_assign").val();
var jqTableDomA=$("#ahthRoleTableA");
var jqFormDomA=$("#authRoleSearchFormA");
var jqToolbarDomA=$("#tableToolbarA");
var isInRole=true;
var initUrlA="authPermission/loadAuthPermListByRoleId?roleId="+roleId+"&isInRole="+isInRole;

var jqTableDomB=$("#ahthRoleTableB");
var jqFormDomB=$("#authRoleSearchFormB");
var jqToolbarDomB=$("#tableToolbarB");
var isInRole=false
var initUrlB="authPermission/loadAuthPermListByRoleId?roleId="+roleId+"&isInRole="+isInRole;

$(function(){
	initData();
});
function initData(){
	var columns=[
					{checkbox:true},
					//{field:'permCode',title:'权限编码',width:60},
					{field:'name',title:'权限名称',width:120}
				];
	zfesBstable.laodTable(jqTableDomA,jqFormDomA,jqToolbarDomA,initUrlA,columns,{height:520});
	zfesBstable.laodTable(jqTableDomB,jqFormDomB,jqToolbarDomB,initUrlB,columns,{height:520});
	
	setTimeout(function(){
		$('.layui-layer .box').find('.pull-right:not(button)').remove();
	},1000);
}
function search(){
	zfesBstable.reload(jqTableDomA);
	zfesBstable.reload(jqTableDomB);
}

function addToPerm(){
	if(zfesBstable.isSelectedRow(jqTableDomB)){
		 var ids=zfesBstable.getRowIds(jqTableDomB);
		 alertSwal.confirm("增加权限","是否为该角色中增加权限",function(isComfirm){
			 if(isComfirm){
				 var ajaxUrl = "authRole/assignPermToRole";
					var param = { "permIds" : ids.join(','),"id":roleId};
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						alertSwal.successText(data.message);
						zfesBstable.refresh(jqTableDomA);
						zfesBstable.refresh(jqTableDomB);
					}); 
			 }
		 });
		
	}
	 
}

function removeFromPerm(){
	if(zfesBstable.isSelectedRow(jqTableDomA)){
		 var ids=zfesBstable.getRowIds(jqTableDomA);
		 alertSwal.confirm("移除权限","是否从本角色中移除权限",function(isComfirm){
			 if(isComfirm){
				 var ajaxUrl = "authRole/removePermFromRole";
					var param = { "permIds" : ids.join(','),"id":roleId};
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						alertSwal.successText(data.message);
						zfesBstable.refresh(jqTableDomA);
						zfesBstable.refresh(jqTableDomB);
					}); 
			 }
		 });
	}
}
</script>

 </div>
 


