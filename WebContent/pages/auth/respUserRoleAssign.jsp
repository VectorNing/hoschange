<%@ page contentType="text/html; charset=utf-8" language="java"%>
 <div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">分配角色</h4>
</div>
 <input type="hidden" id="user_id_assign" value="${userId}"/>
 <div class="row" style="padding: 10px;">
 <div  class="col-md-6">
  <div class="box">
  <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-editbutton="false">
			<!-- -->	<header>
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2>属于用户的角色列表</h2>
				</header> 
            <div class="box-header with-border">
               <form id="authRoleSearchFormA" role="form" class="form-inline">
<!--               	<h4>属于用户的角色</h4>  -->
                     <!--  <div class="form-group">
                    <label>是否启用</label>
                          <select class="form-control" name="enabled" style="width: 170px;">
                        		  <option value="">请选择</option>
                                  <option value="1">启用</option>
                                  <option value="0">禁用</option>
                           </select>
                           &nbsp;&nbsp;&nbsp;&nbsp;
                  <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
                    </div>  -->
                    <hr>
                </form>
            </div>
 <div class="box-body">
 	<div class="row">
 	  <div class="col-md-12">
 	                           <div class="btn-group " id="tableToolbarA" role="group">
                                    <button type="button" class="btn btn-default" onclick="removeFromRole();">
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
					<h2>不属于用户的角色列表</h2>
				</header> 
            <div class="box-header with-border">
<!--             <h4>不属于用户的角色</h4> -->
               <!-- <form id="authRoleSearchFormB" role="form" class="form-inline">
                    <div class="form-group">
                    <label>是否启用</label>
                          <select class="form-control" name="enabled" style="width: 170px;">
                        		  <option value="">请选择</option>
                                  <option value="1">启用</option>
                                  <option value="0">禁用</option>
                           </select>
                     &nbsp;&nbsp;&nbsp;&nbsp;
                     <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
                    </div>
                </form> -->
                <hr>
            </div>
 <div class="box-body">
 	<div class="row">
 	  <div class="col-md-12">
 	                       <div class="btn-group " id="tableToolbarB" role="group">
                                    <button type="button" class="btn btn-default" onclick="addToRole();">
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
$(function(){
   
})
// var userId=2;
var userId=$("#user_id_assign").val();
var jqTableDomA=$("#ahthRoleTableA");
var jqFormDomA=$("#authRoleSearchFormA");
var jqToolbarDomA=$("#tableToolbarA");
var isInRole=true;
var initUrlA="authRole/loadAuthRoleListByUserId?userId="+userId+"&isInRole="+isInRole;

var jqTableDomB=$("#ahthRoleTableB");
var jqFormDomB=$("#authRoleSearchFormB");
var jqToolbarDomB=$("#tableToolbarB");
var isInRole=false
var initUrlB="authRole/loadAuthRoleListByUserId?userId="+userId+"&isInRole="+isInRole;

$(function(){
	initData();
});
function initData(){
	var columns=[
					{checkbox:true},
					{field:'name',title:'角色名称',width:60},
				];
	zfesBstable.laodTable(jqTableDomA,jqFormDomA,jqToolbarDomA,initUrlA,columns,{height:500});
	zfesBstable.laodTable(jqTableDomB,jqFormDomB,jqToolbarDomB,initUrlB,columns,{height:500});
	
	setTimeout(function(){
		$('.layui-layer .box').find('.pull-right:not(button)').remove();
	},1000);
}
function search(){
	zfesBstable.reload(jqTableDomA);
	zfesBstable.reload(jqTableDomB);
}

function addToRole(){
	 var ids=zfesBstable.getRowIds(jqTableDomB);
	 alertSwal.confirm("增加角色","是否为该用户中增加角色",function(){
			 	var ajaxUrl = "authUser/assignUserToRole";
				var param = { "roleIds" : ids.join(','),"id":userId};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successText(data.message);
					zfesBstable.reload(jqTableDomA);
					zfesBstable.reload(jqTableDomB);
				}); 
	 });
	 
}

function removeFromRole(){
	 var ids=zfesBstable.getRowIds(jqTableDomA);
	 alertSwal.confirm("移除角色","是否从本用户中移除角色",function(){
			 	var ajaxUrl = "authUser/removeUserFromRole";
				var param = { "roleIds" : ids.join(','),"id":userId};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successText(data.message);
					zfesBstable.reload(jqTableDomA);
					zfesBstable.reload(jqTableDomB);
				}); 
	 });
	 
}
</script>

 </div>
 


