<%@ page contentType="text/html; charset=utf-8" language="java"%>
 <div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">分配手术衣柜</h4>
</div>
 <input type="hidden" id="theaterNumber_div" value="${theaterNumber}"/>
 <div class="row" style="padding: 10px;">
 <div  class="col-md-6">
  <div class="box">
  <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-editbutton="false">
			<header>
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2>本更衣室的手术衣柜列表</h2>
				</header> 
            <div class="box-header with-border">
               <form id="authWarSearchFormA" role="form" class="form-inline">
                    <div class="form-group">
                    <label>手术衣编号</label>
                         <input name="number" id="number" type="text" placeholder="" class="form-control" style="width: 170px;">
                         <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
                    </div>
                    <hr>
                </form>
            </div>
 <div class="box-body">
 	<div class="row">
 	  <div class="col-md-12">
 	                           <div class="btn-group " id="tableToolbarA" role="group">
                                    <button type="button" class="btn btn-default" onclick="removeFromWar();">
                                        <i class="glyphicon glyphicon-minus" aria-hidden="true"></i> 移除
                                    </button>
                           </div> 
                                
           <table id="ahthWarTableA" data-mobile-responsive="true" ></table>
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
					<h2>未使用的手术衣柜列表</h2>
				</header> 
            <div class="box-header with-border">
               <form id="authWarSearchFormB" role="form" class="form-inline">
                    <div class="form-group">
                   <label>手术衣编号</label>
                         <input name="number" id="number" type="text" placeholder="" class="form-control" style="width: 170px;">
                         <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
                    </div>
                </form>
                <hr>
            </div>
 <div class="box-body">
 	<div class="row">
 	  <div class="col-md-12">
 	                       <div class="btn-group " id="tableToolbarB" role="group">
                                    <button type="button" class="btn btn-default" onclick="addToWar();">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加
                                    </button>
                           </div> 
                                
           <table id="ahthWarTableB" data-mobile-responsive="true" ></table>
 	  </div>
 	</div>
 </div>
</div>
</div></div>
<script>
var theaterNumber=$("#theaterNumber_div").val();
var jqTableDomA=$("#ahthWarTableA");
var jqFormDomA=$("#authWarSearchFormA");
var jqToolbarDomA=$("#tableToolbarA");
var isInWarTrue=true;
var initUrlA="hosWardrobe/loadWardrobeListByTheNumber?theaterNumber="+theaterNumber+"&isInWar="+isInWarTrue;

var jqTableDomB=$("#ahthWarTableB");
var jqFormDomB=$("#authWarSearchFormB");
var jqToolbarDomB=$("#tableToolbarB");
var isInWarFalse=false
var initUrlB="hosWardrobe/loadWardrobeListByTheNumber?theaterNumber="+theaterNumber+"&isInWar="+isInWarFalse;

$(function(){
	initData();
});
function initData(){
	var columns=[
					{checkbox:true},
					{field:'number',title:'衣柜编号',width:60,sortable:true},
					{field:'traySum',title:'托盘数量',width:60},
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

function addToWar(){
	if(zfesBstable.isSelectedRow(jqTableDomB)){
		var ids=zfesBstable.getRowIds(jqTableDomB);
		 alertSwal.confirm("增加手术衣柜","是否为该手术室增加手术衣柜",function(){
				 	var ajaxUrl = "hosWardrobe/assignTheaterToWar";
					var param = { "ids" : ids.join(','),"number":theaterNumber};
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						alertSwal.successText(data.message);
						zfesBstable.reload(jqTableDomA);
						zfesBstable.reload(jqTableDomB);
					}); 
		 });
	}
}

function removeFromWar(){
	if(zfesBstable.isSelectedRow(jqTableDomA)){
		var ids=zfesBstable.getRowIds(jqTableDomA);
		 alertSwal.confirm("移除手术衣柜","是否为该手术室移除手术衣柜",function(){
				 	var ajaxUrl = "hosWardrobe/removeWarFromTheater";
				 	var param = { "ids" : ids.join(','),"number":theaterNumber};
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
 


