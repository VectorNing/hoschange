<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<style>
.col-md-8>.seatCharts-cell{
	height: 500px;
	width: 600px;
	overflow: auto;
}
</style>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">分配</h4>
</div>
	  <div id="container_div"></div>
		<hr>
		<footer style="text-align: center;">
				<button type="button" id="saveBtn" class="btn btn-primary" onclick="saveAllot();">
					保存
				</button>
				<button type="button" id="emptyBtn" class="btn btn-primary" onclick="emptyShoes();">
					清空小柜
				</button>
				<button type="button" id="emptyAllBtn" class="btn btn-primary" onclick="emptyAllShoes();">
					清空全部
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					取消
				</button>
			</footer>
		<hr>	
<input type="hidden" value="${steNumber}" id="steNumber_input"/>
<input type="hidden" value="${rows}" id="rows_input"/>
<input type="hidden" value="${columns}" id="columns_input"/>
<script type="text/javascript">
$(function(){
	loadSte();
});
function loadSte(){
	var steNumber = $("#steNumber_input").val();//消毒柜编号
	var rows = $("#rows_input").val();
	var columns = $("#columns_input").val();
	var url = "hosSterilizer/enterAllotShoesList?steNumber="+steNumber+"&rows="+rows+"&columns="+columns+"&type=set";
	$("#container_div").load(url);
}

function saveAllot(){
	timer($("#saveBtn"));
	var ids = getSelectIds();
	var shoesSize = $("#shoesSize").val();
	var msg = "";
	ids = ids.join(",");
	var params = {"ids":ids,"shoesSize":shoesSize}
	var ajaxUrl = "hosSterilizer/allotSizeShoesToSterilizer";
	zfesAjax.ajaxTodo(ajaxUrl, params, function(data) {
		alertSwal.successText(data.message);
		loadSte();
	});
}
function emptyShoes(){
	timer($("#emptyBtn"),"清空中..","清空小柜");
	alertSwal.confirm("清空", "是否确认清空选中的消毒小柜?", function(isConfirm) {
		if(isConfirm){
			var idArr = getSelectIds();
			var ids = idArr.join(",");
			var ajaxUrl = "hosSterilizer/emptyContainer";
			var param = {"ids":ids};
			zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
				alertSwal.successText(data.message);
				loadSte();
			});
		}
	});
}
function emptyAllShoes(){
	timer($("#emptyAllBtn"),"清空中..","清空全部");
	alertSwal.confirm("清空", "是否确认清空该消毒柜?", function(isConfirm) {
		if(isConfirm){
			var number=$("#steNumber_number").val();
			var ajaxUrl = "hosSterilizer/emptyAllContainer";
			var param = {"number":number};
			zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
				alertSwal.successText(data.message);
				loadSte();
			});
		}
	});
}

</script>
