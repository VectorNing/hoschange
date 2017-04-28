<%@ page contentType="text/html; charset=utf-8" language="java"%>
 <div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">分配人员</h4>
</div>
 <input type="hidden" id="theaterId_div" value="${theaterId}"/>
 <div class="row" style="padding: 10px;">
 <div  class="col-md-6">
  <div class="box">
  <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-editbutton="false">
			<header>
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2>本更衣室人员列表</h2>
				</header> 
            <div class="box-header with-border">
               <form id="authSteSearchFormA" role="form" class="form-inline">
                    <div class="form-group">
                    <label>用户名</label>
                         <input name="loginName" id="loginName" type="text" placeholder="" class="form-control" style="width: 170px;">
                         <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
                    </div>
                    <hr>
                </form>
            </div>
 <div class="box-body">
 	<div class="row">
 	  <div class="col-md-12">
 	                           <div class="btn-group " id="tableToolbarA" role="group">
                                    <button type="button" class="btn btn-default" onclick="removeFromThe();">
                                        <i class="glyphicon glyphicon-minus" aria-hidden="true"></i> 移除
                                    </button>
                           </div> 
                                
           <table id="ahthSteTableA" data-mobile-responsive="true" ></table>
 	  </div>
 	</div>
 </div>
</div>
 </div></div>
 <div  class="col-md-6">
   <div class="box">
   <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2>不在本更衣室的人员列表</h2>
				</header> 
            <div class="box-header with-border">
               <form id="authSteSearchFormB" role="form" class="form-inline">
                    <div class="form-group">
                   <label>用户名</label>
                         <input name="loginName" id="loginName" type="text" placeholder="" class="form-control" style="width: 170px;">
                         <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
                    </div>
                </form>
                <hr>
            </div>
 <div class="box-body">
 	<div class="row">
 	  <div class="col-md-12">
 	                       <div class="btn-group " id="tableToolbarB" role="group">
                                    <button type="button" class="btn btn-default" onclick="addToThe();">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加
                                    </button>
                           </div> 
                                
           <table id="ahthSteTableB" data-mobile-responsive="true" ></table>
 	  </div>
 	</div>
 </div>
</div>
</div></div>
<script>
var theaterId=$("#theaterId_div").val();
var jqTableDomA=$("#ahthSteTableA");
var jqFormDomA=$("#authSteSearchFormA");
var jqToolbarDomA=$("#tableToolbarA");
var isInTheater=true;
var initUrlA="hosTheater/loadTheaterUserByTheaterId?theaterId="+theaterId+"&isInTheater="+isInTheater;

var jqTableDomB=$("#ahthSteTableB");
var jqFormDomB=$("#authSteSearchFormB");
var jqToolbarDomB=$("#tableToolbarB");
var isInTheater=false
var initUrlB="hosTheater/loadTheaterUserByTheaterId?theaterId="+theaterId+"&isInTheater="+isInTheater;

$(function(){
	initData();
});
function initData(){
	var columns=[
					{checkbox:true},
					{field:'loginName',title:'用户名',width:60,sortable:true},
					{field:'loginName2',title:'卡号',width:60},
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

function addToThe(){
	if(zfesBstable.isSelectedRow(jqTableDomB)){
		var ids=zfesBstable.getRowIds(jqTableDomB);
		 alertSwal.confirm("分配人员","是否为该手术室分配管理人员",function(){
				 	var ajaxUrl = "hosTheater/assignUserToTheater";
					var param = { "userIds" : ids.join(','),"theaterId":theaterId};
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						alertSwal.successText(data.message);
						zfesBstable.reload(jqTableDomA);
						zfesBstable.reload(jqTableDomB);
					}); 
		 });
	}
}

function removeFromThe(){
	if(zfesBstable.isSelectedRow(jqTableDomA)){
		var ids=zfesBstable.getRowIds(jqTableDomA);
		 alertSwal.confirm("移除人员","是否为该手术室移除管理人员",function(){
				 	var ajaxUrl = "hosTheater/removeTheaterFromUser";
				 	var param = { "userIds" : ids.join(','),"theaterId":theaterId};
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						alertSwal.successText(data.message);
						zfesBstable.reload(jqTableDomA);
						zfesBstable.reload(jqTableDomB);
					}); 
		 });
	}
}
</script>

 </div>
 


