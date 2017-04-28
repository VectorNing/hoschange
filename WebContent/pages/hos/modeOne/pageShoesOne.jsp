<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 
				设备管理 
			<span>> 
				手术鞋管理
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
					<h2>手术鞋列表</h2>
				</header> 
			
				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
					<form id="searchForm" role="form" class="form-inline">
								<div class="form-group">
								<label>更衣室</label> <label class="select" id="listRoom">
									<select name="number" class="form-control" id="theNumber"
									style="width: 140px;" >
									<!-- <option value="">请选择</option>  -->
										<c:forEach items="${roomLists}" var="room">
											<option value="${room.number}">${room.name}</option>
										</c:forEach>
								</select>
								</label>
							</div>
		                   <!--  <div class="form-group">
		                         <label>鞋尺码</label>
		                         
		                         <label class="select"> 
		                             <select dict="shoes"  name="shoeSize" class="form-control" style="width: 140px;"></select>
		                         </label>
		                    </div>
		                     <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button> -->
		                </form>
		                
		                
		                <hr>
		                
		                
					  	  <div class="btn-group " id="hosShoesTableToolbar" role="group">
								 <button type="button" class="btn btn-default" onclick="add();">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加
                                 </button>
                                 <button type="button" class="btn btn-default" onclick="edit();">
                                        <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改数量
                                 </button>
                                    <button type="button" class="btn btn-default" onclick="del();">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true" ></i> 删除
                                    </button>
                           </div>
							<table id="hosShoesBootstrapTable" data-mobile-responsive="true" ></table>
						</div>
					</div>
				</div>
		</article>
	</div>
</section>

<!-- Dynamic Modal -->  
<div class="modal fade" id="shoesModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">  
    <div class="modal-dialog">  
        <div class="modal-content" id="modal-content">
        	
        </div>  
    </div>  
</div>  



<script>

var jqTableDom=$("#hosShoesBootstrapTable");
function initData(){
	var jqFormDom=$("#searchForm");
	var jqToolbarDom=$("#hosShoesTableToolbar");
	var url="hosShoes/selectHosShoesList";
	var columns=[
					{checkbox:true},
					{field:'shoeSize',title:'尺码',width:160,formatter:dictFormat('shoes'),sortable:true},
					{field:'count',title:'总数量',width:160,sortable:true},
					{field:'fp',title:'已分配',width:160},
					{field:'syz',title:'使用中',width:160},
					//{field:'hsz',title:'回收中',width:160}
				];
	bstable=zfesBstable.laodTable(jqTableDom,jqFormDom,jqToolbarDom,url,columns);
}

function attachModalHidden(){
	$("#shoesModal").on('hide.bs.modal', function () {
		zfesBstable.refresh(jqTableDom);
	});
}
$(function(){
	initData();
	attachModalHidden();
	$("#theNumber").change(function(){
		zfesBstable.reload(jqTableDom);
	});
});
function add(){
	var url = "hosShoes/enterAddhosShoes"
	$("#shoesModal").modal({
	    remote: url
	});
}

function edit(){
	if(zfesBstable.isOneRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		var id = row[0].id;
		var yfp =row[0].fp+row[0].syz;
		var url = "hosShoes/enterEdithosShoes?id="+id+"&yfp="+yfp;
		$("#shoesModal").modal({
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
		if (row[0].fp > 0) {
			alertSwal.warningText("您还有"+row[0].fp+"双消毒鞋正在分配,不能删除");
			return false;
		}
		if (row[0].syz > 0) {
			alertSwal.warningText("您还有"+row[0].syz+"双消毒鞋正在使用,不能删除");
			return false;
		}
		
		var id = zfesBstable.getRowId(jqTableDom);
		alertSwal.confirm("删除", "是否删除此记录?", function(isConfirm) {
			if(isConfirm){
				var ajaxUrl = "hosShoes/deletehosShoes";
				var param = {
					"id" : id
				};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successText(data.message);
					zfesBstable.refresh(jqTableDom);
				});
			}
		});
	}
}

</script>