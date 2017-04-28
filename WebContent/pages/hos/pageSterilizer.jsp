<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 
				物资管理
			<span>> 
				消毒鞋柜管理
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
					<h2>消毒鞋柜列表</h2>
				</header> 
			
				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
					<form id="searchForm" role="form" class="form-inline">
					
					
		                    <div class="form-group">
		                         <label>设备编号</label>
		                         <input name="number" type="text" placeholder="" class="form-control">
		                    </div>
		                    <!-- <div class="form-group">
		                    	<label>描述</label>
		                         <input name="description" type="text" placeholder="" class="form-control">
		                    </div> -->
		                   
		                     <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
		                </form>
		                
		                
		                <hr>
		                
		                
					  	  <div class="btn-group " id="sterilizerTableToolbar" role="group">
                                <button type="button" class="btn btn-default" onclick="add();">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加
                                 </button>
					  	        <button type="button" class="btn btn-default" onclick="edit();">
                                        <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改
                                 </button>
					  	           <button type="button" class="btn btn-default" onclick="deleteSterilizer();">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true" ></i> 注销
                                    </button>
					  	          <button type="button" class="btn btn-default" onclick="allotShoes();">
                                        <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 设定鞋子尺码
                                 </button>
                                 <button type="button" class="btn btn-default" onclick="setShoesUser();">
                                        <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 指定鞋柜使用人
                                 </button>
                                 <button type="button" class="btn btn-default" onclick="openOrLock();">
                                        <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 锁柜、开柜
                                 </button>
					  	          <!-- <button type="button" class="btn btn-default" onclick="loadShoes();">
                                        <i class="glyphicon glyphicon-random" aria-hidden="true"></i> 查看消毒鞋柜使用
                                 </button> -->
                           </div>
							<table style="table-layout:fixed;" id="sterilizerBootstrapTable" data-mobile-responsive="true" ></table>
						</div>
					</div>
				</div>
		</article>
	</div>
</section>

<!-- Dynamic sterilizerContainerModal Modal -->  
<div class="modal fade" id="sterilizerContainerModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">  
    <div class="modal-dialog" style="width:1025px;">  
        <div class="modal-content">
        	
        </div>  
    </div>  
</div>  

<!-- Dynamic Modal -->  
<div class="modal fade" id="sterilizerModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">  
    <div class="modal-dialog">  
        <div class="modal-content">
        	
        </div>  
    </div>  
</div>  


<script>

var jqTableDom=$("#sterilizerBootstrapTable");
function initData(){
	var jqFormDom=$("#searchForm");
	var jqToolbarDom=$("#sterilizerTableToolbar");
	var url="hosSterilizer/queryHosSterilizerSet";
	var columns=[
		{checkbox:true},
		{field:'number',title:'设备编号',width:160,sortable:true},
		{field:'total',title:'小柜总数',width:160},
		{field:'state',title:'状态',width:100,formatter:zfesUtil.formatStateUse},
		{field:'cnt',title:'已存储鞋子总数',width:160,formatter:function(value, row, index){
			var msg = ""; 
			if(value==null||value==""){
				value=0;
			}
			if(row.state == "1" && value < 10) 
				msg = '<b style="color:red;font-size:18px;">'+value+'</b>';
				else
				msg	= value;
			return msg;
			}},
		{field:'name',title:'存放位置',width:160},	
		{field:'description',title:'描述',width:160,formatter:zfesUtil.formatterOmit}
	];
	
    
	bstable=zfesBstable.laodTable(jqTableDom,jqFormDom,jqToolbarDom,url,columns,{rowStyle: function (row, index) {
		//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
        var strclass = "";
        if (row.state == "1" && row.cnt < 10) {
            strclass = 'danger';//还有一个active
        }
        else {
            return {};
        }
        return { classes: strclass }
	}});
}
function attachModalHidden(){
	$("#sterilizerModal").on('hide.bs.modal', function () {
		zfesBstable.refresh(jqTableDom);
	});
	$("#sterilizerContainerModal").on('hide.bs.modal', function () {
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
function setShoesUser(){
	if(zfesBstable.isOneRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		if(row[0].state == 0){
			alertSwal.warningTitle("不能将未使用的鞋柜与医护人员绑定");
			return;
		}
		var number = row[0].number;
		var total = row[0].total;
		var columns = 5.0;
		var rows = Math.ceil(total/columns);
		var url = "hosSterilizer/enterSetShoesUserHosSterilizer?steNumber="+number+"&rows="+rows+"&columns="+columns;
		$("#sterilizerContainerModal").modal({
		    remote: url
		});
	}
	
}
function openOrLock(){
	if(zfesBstable.isOneRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		if(row[0].state == 0){
			alertSwal.warningTitle("不能打开或锁定未使用的鞋柜");
			return;
		}
		var number = row[0].number;
		var total = row[0].total;
		var columns = 5.0;
		var rows = Math.ceil(total/columns);
		var url = "hosSterilizer/enterOpenOrLockHosSterilizer?steNumber="+number+"&rows="+rows+"&columns="+columns;
		$("#sterilizerContainerModal").modal({
		    remote: url
		});
	}
}
function edit(){
	if(zfesBstable.isOneRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		var id = row[0].id;
		var url = "hosSterilizer/enterEditHosSterilizer?id="+id;
		$("#sterilizerModal").modal({
		    remote: url
		});
	}
}
function add(){
	var url = "hosSterilizer/enterAddHosSterilizer";
	$("#sterilizerModal").modal({
	    remote: url
	});
}
function deleteSterilizer(){
	if(zfesBstable.isOneRow(jqTableDom)){
		 var row = zfesBstable.getSelections(jqTableDom);
		 var id=row[0].id;
		 var number=row[0].number;
		 var param = { "id" : id,"number":number};
		 var url = "hosSterilizer/selectShoesFromSterilizerByNumber";
		 var ajaxUrl = "hosSterilizer/deleteHosSterilizer";
		 zfesAjax.ajaxTodo(url, param, function(data) {
			 var count = data.strData
			 var wain;
			 if(count>0){
				 wain="该消毒鞋柜还有"+ count +"双手术鞋，是否确认销毁该消毒柜？"
			 }else{
				 wain="是否确认销毁该消毒柜？"
			 }
				 alertSwal.confirm("确认注销",wain,function(isComfirm){
					 if(isComfirm){
						 zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
		 						alertSwal.successText(data.message);
		 			 			zfesBstable.refresh(jqTableDom);
		 				}); 
					 }
				 });
		});
	}
}
function allotShoes(){
	if(zfesBstable.isOneRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		if(row[0].state == 0){
			alertSwal.warningTitle("不能对未使用的鞋柜设定尺码");
			return;
		}
		var number = row[0].number;
		var total = row[0].total;
		var columns = 5.0;
		var rows = Math.ceil(total/columns);
		
//		var url = "hosSterilizer/enterAllotShoes?steNumber="+number+"&rows="+rows+"&columns="+columns;
		var url = "hosSterilizer/enterSteAllotShoesM1?steNumber="+number+"&rows="+rows+"&columns="+columns;
		$("#sterilizerContainerModal").modal({
		    remote: url
		});
	}
}
function loadShoes(){
	if(zfesBstable.isOneRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		var number = row[0].number;
		var total = row[0].total;
		var columns = 5.0;
		var rows = Math.ceil(total/columns);
		var url = "hosSterilizer/enterSteLoadShoes?steNumber="+number+"&rows="+rows+"&columns="+columns;
		$("#sterilizerContainerModal").modal({
		    remote: url
		});
	}
}
</script>