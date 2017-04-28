<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 
				物资管理
			<span>> 
				手术衣管理
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
					<h2>手术衣列表</h2>
				</header> 
			
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body ">
					<form id="searchForm" role="form" class="form-inline">
					
		                     <div class="form-group">
		                         <label>更衣室编号</label>
		                         <label class="select" id="listRoom" > 
		                            <!-- 根据后台传入的数据填写更衣室信息 -->
		                            <select name="listRoomNumber" class="form-control" id="theNumber" style="width: 140px;">
		                            	<c:forEach items="${listRoom}" var="room">
		                            	 <option value="${room.number}" ">${room.name}</option>
		                            	 
		                            	</c:forEach>
                                	</select>
		                         </label>
		                    </div>
		                </form>
		                
		                
		                <hr>
		                
		                
					  	  <div class="btn-group " id="operationTableToolbar" role="group">
                                <button type="button" class="btn btn-default" onclick="add();">
                                 <i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加
                                 </button>
							
                                
								<button type="button" class="btn btn-default" onclick="editNum();">
	                                    <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改
	                            </button>
                                    <button type="button" class="btn btn-default" onclick="del();">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> 删除
                                    </button>
                           </div>
							<table id="operationBootstrapTable" data-mobile-responsive="true" ></table>
						</div>
					</div>
				</div>
		</article>
	</div>
</section>

<!-- Dynamic Modal -->  
<div class="modal fade" id="operationModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">  
    <div class="modal-dialog">  
        <div class="modal-content" id="modal-content">
        	
        </div>  
    </div>  
</div>  



<script>

var jqTableDom=$("#operationBootstrapTable");
function initData(){
	var jqFormDom=$("#searchForm");
	var jqToolbarDom=$("#operationTableToolbar");
	var url="hosOperation/selectHosOperationSet";
	
	var columns;
	columns=[
					{checkbox:true},
					{field:'clothSize',title:'尺码',width:160,formatter:dictFormat('cloth'),sortable:true},
					{field:'count',title:'总数量',width:160,sortable:true},
					{field:'fp',title:'分配',width:160},
					{field:'syz',title:'使用中',width:160},
					{field:'hsz',title:'回收中',width:160}
			];
	bstable=zfesBstable.laodTable(jqTableDom,jqFormDom,jqToolbarDom,url,columns);
}
$(function(){
	initData();
	attachModalHidden();
	$("#theNumber").change(function(){
		zfesBstable.reload(jqTableDom);
	});
});
function attachModalHidden(){
	$("#operationModal").on('hide.bs.modal', function () {
		zfesBstable.refresh(jqTableDom);
	});
}
function add(){
	var url= "hosOperation/enterAddHosOperation";
	$("#operationModal").modal({
	    remote: url
	});
	
}
function editNum(){
	if(zfesBstable.isOneRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		var clothSize = row[0].clothSize;
		var yfp =row[0].fp+row[0].syz+row[0].hsz;
		var sumCount=row[0].count+0;
		var id = row[0].id;
		var url= "hosOperation/enterEditHosOperationNum?clothSize="+clothSize+"&id="+id+"&yfp="+yfp+"&sumCount="+sumCount;
		$("#operationModal").modal({
		    remote: url
		});
	}
}

function del(){
	if (zfesBstable.isOneRow(jqTableDom)) {
		var row = zfesBstable.getSelections(jqTableDom);
		
		if (row.length > 1) {
			alertSwal.successText("请选择一条信息");
			return false;
		}
		var id = zfesBstable.getRowId(jqTableDom);
		var clothSize = row[0].clothSize;
		var fp = row[0].fp;
		var syz = row[0].syz;
		var hsz = row[0].hsz;
		if(fp > 0){
			alertSwal.warningText("该型号衣物已分配"+fp+"件，请清理后再试");
			return false;
		}
		if(syz > 0){
			alertSwal.warningText("改型号衣物使用中"+syz+"件，请清理后再试");
			return false;
		}
		if(hsz > 0){
			alertSwal.warningText("改型号衣物回收中"+hsz+"件，请清理后再试");
			return false;
		}
		alertSwal.confirm("删除", "是否全部删除此尺码的记录?", function(isConfirm) {
			if(isConfirm){
				var ajaxUrl = "hosOperation/deleteHosOperationByClothSize";
				var param = {
					"clothSize":clothSize,
					"id":id
				};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successText(data.message);
					zfesBstable.refresh(jqTableDom);
				});
			}
		});
	}
}


function initDataList(){
	var jqFormDom=$("#searchForm");
	var jqToolbarDom=$("#operationTableToolbar");
	var url="hosOperation/queryHosOperationSet";
	
	var columns;
	columns=[
					{checkbox:true},
					{field:'clothSize',title:'尺码',width:160,formatter:dictFormat('cloth'),sortable:true},
					{field:'count',title:'总数量',width:160,sortable:true},
					{field:'residueCount',title:'可分配数量',width:160},
					{field:'residueCount',title:'已分配数量',width:160,formatter:function (value, row, index) {
	                    return (parseInt(row.count) - parseInt(value))}
					}
					/* {field:'state',title:'状态',width:200,formatter:zfesUtil.formatStateDistribution} */
			];
	bstable=zfesBstable.laodTable(jqTableDom,jqFormDom,jqToolbarDom,url,columns);
}
</script>