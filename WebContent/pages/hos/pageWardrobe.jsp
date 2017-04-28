<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 
				物资管理
			<span>> 
				发衣柜管理
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
					<h2>发衣柜柜列表</h2>
				</header> 
			
				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
					<form id="searchForm" role="form" class="form-inline">
					
					
		                    <div class="form-group">
		                         <label>编号</label>
		                         <input name="number" type="text" placeholder="" class="form-control">
		                    </div>
		                   
		                     <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
		                </form>
		                
		                
		                <hr>
		                
		                
					  	  <div class="btn-group " id="wardrobeTableToolbar" role="group">
					  	         <button type="button" class="btn btn-default" onclick="add();">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加
                                 </button>
								 <button type="button" class="btn btn-default" onclick="edit();">
                                        <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改
                                 </button>
                                 <button type="button" class="btn btn-default" onclick="deleteWardrobe();">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> 注销
                                 </button>
                                 <button type="button" class="btn btn-default" onclick="allotOperation();">
                                        <i class="glyphicon glyphicon-random" aria-hidden="true"></i> 设定托盘存放尺码
                                 </button>
                                 <button type="button" class="btn btn-default" onclick="loadOperation();">
                                        <i class="glyphicon glyphicon-random" aria-hidden="true"></i> 查看托盘使用
                                 </button>
                                    
                           </div>
							<table id="wardrobeBootstrapTable" data-mobile-responsive="true" ></table>
						</div>
					</div>
				</div>
		</article>
	</div>
</section>

<!-- Dynamic Modal -->  
<div class="modal fade" id="wardrobeModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">  
    <div class="modal-dialog">  
        <div class="modal-content">
        	
        </div>  
    </div>  
</div>  

<!-- Dynamic allotModel -->  
<div class="modal fade" id="allotModel" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true" >  
    <div class="modal-dialog" style="width:900px;">  
        <div class="modal-content">
        	
        </div>  
    </div>  
</div>

<script>

var jqTableDom=$("#wardrobeBootstrapTable");
function initData(){
	var jqFormDom=$("#searchForm");
	var jqToolbarDom=$("#wardrobeTableToolbar");
	var url="hosWardrobe/queryHosWardrobeSet";
	var columns=[
		{checkbox:true},
		{field:'number',title:'编号',width:160,sortable:true},
		{field:'traySum',title:'托盘数',width:160,sortable:true},
		//{field:'state',title:'状态',width:100,formatter:zfesUtil.formatStateUse},
		{field:'cnt',title:'手术服数量',width:160,sortable:true,formatter:function(value, row, index){
			var msg = ""; 
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
	$("#wardrobeModal").on('hide.bs.modal', function () {
		zfesBstable.refresh(jqTableDom);
	});
	$("#allotModel").on('hide.bs.modal', function () {
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

function edit(){
	if(zfesBstable.isOneRow(jqTableDom)){
		 var id=zfesBstable.getRowId(jqTableDom);
		 $("#wardrobeModal").modal({
			    remote: "hosWardrobe/enterEditHosWardrobe?id="+id
			});
	}
}
function add(){
	$("#wardrobeModal").modal({
	    remote: "hosWardrobe/enterAddHosWardrobe"
	});
}
function defendContainer(){
	if(zfesBstable.isOneRow(jqTableDom)){
		 var id=zfesBstable.getRowId(jqTableDom);
		 $("#wardrobeModal").modal({
			    remote: "hosWardrobe/enterDefendContainer?id="+id
			});
	}
}
function deleteWardrobe(){
	if(zfesBstable.isOneRow(jqTableDom)){
		 var url = "hosWardrobe/selectOperationFromWardBynumber";
		 var ajaxUrl = "hosWardrobe/deleteHosWardrobe";
		 var row = zfesBstable.getSelections(jqTableDom);
		 var id=row[0].id;
		 var number=row[0].number;
		 var param = { "id" : id,"number":number};
		 zfesAjax.ajaxTodo(url, param, function(data) {
			 var count = data.strData;
			 var wain;
			 if(count>0){
				 wain="该手术衣柜还有"+count+"件衣服，是否确定销毁该手术衣柜？";
			 }else{
				 wain="是否确认销毁该手术衣柜？"
			 }
			 alertSwal.confirm("确认销毁",wain,function(){
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						setTimeout(function () {
							alertSwal.successText(data.message);
				 			zfesBstable.refresh(jqTableDom);
						}, 100);
					});  
		     });
		 });
	}
}
function allotOperation(){
	if(zfesBstable.isOneRow(jqTableDom)){
		 var id=zfesBstable.getRowId(jqTableDom);
		 var row = zfesBstable.getSelections(jqTableDom);
		 if(row[0].state == 0){
				alertSwal.warningTitle("不能对未使用的衣柜设定尺码");
				return;
		 }
		 var number = row[0].number;
		 $("#allotModel").modal({
			    remote: "hosWardrobe/enterAllotOperationPageM1?warNumber="+number
			});
	}
}

function loadOperation(){
	if(zfesBstable.isOneRow(jqTableDom)){
		 var id=zfesBstable.getRowId(jqTableDom);
		 var row = zfesBstable.getSelections(jqTableDom);
		 var number = row[0].number;
		 $("#allotModel").modal({
			    remote: "hosWardrobe/enterLoadOperationPage?warNumber="+number
			});
	}
}
</script>