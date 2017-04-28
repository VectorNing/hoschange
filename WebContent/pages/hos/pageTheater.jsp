<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 
				物资管理
			<span>> 
				手术区管理
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
					<h2>手术区列表</h2>
				</header> 
			
				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
					<form id="searchForm" role="form" class="form-inline">
					
					
		                    <div class="form-group">
		                         <label>名称</label>
		                         <input name="name" type="text" placeholder="" class="form-control">
		                    </div>
		                    <div class="form-group">
		                    	<label>位置</label>
		                         <input name="address" type="text" placeholder="" class="form-control">
		                    </div>
		                   
		                     <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
		                </form>
		                
		                
		                <hr>
		                
		                
					  	  <div class="btn-group " id="theaterTableToolbar" role="group">
								 <button type="button" class="btn btn-default" onclick="add();">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加
                                 </button>
                                 <button type="button" class="btn btn-default" onclick="edit();">
                                        <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改
                                 </button>
                                    <button type="button" class="btn btn-default" onclick="deleteThe()">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> 注销
                                    </button>
                                    <!-- <button type="button" class="btn btn-default" onclick="TheaAssignWar()">
                                        <i class="glyphicon glyphicon-random" aria-hidden="true"></i> 分配手术衣柜
                                    </button> 
                                    <button type="button" class="btn btn-default" onclick="TheaAssignSte()">
                                        <i class="glyphicon glyphicon-random" aria-hidden="true"></i> 分配消毒鞋柜
                                    </button> -->
					  	     
					  	     
                                    <button type="button" class="btn btn-default" onclick="TheaAssignUser()">
                                        <i class="glyphicon glyphicon-random" aria-hidden="true"></i> 分配人员
                                    </button>
                           </div>
							<table id="theaterBootstrapTable" data-mobile-responsive="true" ></table>
						</div>
					</div>
				</div>
		</article>
	</div>
</section>

<!-- Dynamic Modal -->  
<div class="modal fade" id="theaterModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">  
    <div class="modal-dialog">  
        <div class="modal-content">
        	
        </div>  
    </div>  
</div>  

<div class="modal fade" id="TheaAssignWar" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true" style="margin-left: -200px;" >  
    <div class="modal-dialog" >  
        <div class="modal-content" id="modal-content" style="width: 1000px;">
        	
        </div>  
    </div>  
</div> 

<script>

var jqTableDom=$("#theaterBootstrapTable");
function initData(){
	var jqFormDom=$("#searchForm");
	var jqToolbarDom=$("#theaterTableToolbar");
	var url="hosTheater/queryHosTheaterSet";
	var columns=[
		{checkbox:true},
		{field:'name',title:'名称',width:160},
		{field:'warCount',title:'发衣柜数量',width:160},
		{field:'steCount',title:'消毒鞋柜数量',width:160},
		{field:'clothCount',title:'储物柜数量',width:160},
		{field:'description',title:'描述',width:160,formatter:zfesUtil.formatterOmit},
		{field:'address',title:'位置',width:200}
	];
	bstable=zfesBstable.laodTable(jqTableDom,jqFormDom,jqToolbarDom,url,columns);
}
$(function(){
	initData();
	attachModalHidden();
	attachTheaModalHidden();
});
function attachModalHidden(){
	$("#theaterModal").on('hide.bs.modal', function () {
		zfesBstable.refresh(jqTableDom);
	});
}
function attachTheaModalHidden(){
	$("#TheaAssignWar").on('hide.bs.modal', function () {
		zfesBstable.refresh(jqTableDom);
	});
}

function search(){
	zfesBstable.reload(jqTableDom);
}

function edit(){
	if(zfesBstable.isOneRow(jqTableDom)){
		 var id=zfesBstable.getRowId(jqTableDom);
		 $("#theaterModal").modal({
			    remote: "hosTheater/enterEditHosTheater?id="+id
			});
	}
}
function add(){
	$("#theaterModal").modal({
	    remote: "hosTheater/enterAddHosTheater"
	});
}
function deleteThe(){
	if(zfesBstable.isOneRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		var id=row[0].id;
		var number=row[0].number;
		var param = { "id" : id,"number":number};
		var ajaxUrl = "hosTheater/deleteHosTheater"
		alertSwal.confirm("删除", "是否删除此记录?", function(isConfirm) {
			if(isConfirm){
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successText(data.message);
					zfesBstable.refresh(jqTableDom);
				});
			}
			
		});
	}
}
function TheaAssignWar(){
	if(zfesBstable.isOneRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		var id=row[0].id;
		var number=row[0].number;
// 		console.log(row)
		var param = { "id" : id,"number":number};
	    $("#TheaAssignWar").modal({
			    remote: "hosWardrobe/enterAssignWarToTheater?theaterNumber="+number
		});
	}
}
function TheaAssignSte(){
	if(zfesBstable.isOneRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		var id=row[0].id;
		var number=row[0].number;
		var param = { "id" : id,"number":number};
	    $("#TheaAssignWar").modal({
			    remote: "hosSterilizer/enterAssignSteToTheater?theaterNumber="+number
		});
	}
}
function TheaAssignUser(){
	if(zfesBstable.isOneRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		var id=row[0].id;
	    $("#TheaAssignWar").modal({
			    remote: "hosTheater/enterAssignUserToTheater?id="+id
		});
	}
}
</script>