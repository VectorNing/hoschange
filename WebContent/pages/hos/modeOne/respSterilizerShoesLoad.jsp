<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<style type="text/css">
	div.seatCharts-cell {
	color: #182C4E;
	height: 65px;
	width: 65px;
	line-height: 15px;
	margin: 4px;
	float: left;
	text-align: center;
	outline: none;
	font-size: 13px;
	/* padding-top: 13px; */
}
div.seatCharts-seat {
	color: #fff;
	cursor: pointer;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
}
div.seatCharts-seat.available {
	background-color: #B9DEA0;
}
div.seatCharts-seat.full {
	background-color: red;
}
div.seatCharts-seat.unavailable {
	background-color: #472B34;
	cursor: not-allowed;
}
div.seatCharts-seat.selected {
	background-color: #E6CAC4;
}
div.seatCharts-seat.focused {
	background-color: #76B474;
	border: none;
}
#tray_chose {
	max-height: 150px;
	overflow-y: auto;
	overflow-x: none;
	width: 200px;
}

#tray_chose li {
	float: left;
	width: 102px;
	height: 26px;
	line-height: 26px;
	border: 1px solid #d3d3d3;
	background: #f7f7f7;
	margin: 6px;
	font-size: 14px;
	font-weight: bold;
	text-align: center;
	float:left; 
}
ol, ul {
	list-style: none
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
<input type="hidden" value="${steNumber}" id="steNumber_input"/>
<input type="hidden" value="${rows}" id="rows_input"/>
<input type="hidden" value="${columns}" id="columns_input"/>
<script type="text/javascript">
$(function(){
	validateFrom();
	loadSte();
});
function loadSte(){
	var steNumber = $("#steNumber_input").val();//消毒柜编号
	var rows = $("#rows_input").val();
	var columns = $("#columns_input").val();
	var url = "hosSterilizer/enterSteLoadShoesList?steNumber="+steNumber+"&rows="+rows+"&columns="+columns;
	$("#container_div").load(url);
}
function validateFrom(){
	return $("#addHosOperationForm").validate({
		rules : {
			clothSize : {required : true},
			allot : {required : true,maxlength: 4}
		},
		messages : {
			clothSize : {required : '请选择尺码'},
			count : {required : '请输入分配数量',maxlength:"数量不能大于999"}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}
function saveAllot(){
	var ids = idArr;
	var shoesSize = $("#shoesSize").val();
	var msg = "";
	$("#legend").empty();
	if(ids[0] == null || ids[0] == ""){
		msg = "请先选择一个小柜";
		$("#legend").append(msg);
		return false;
	}
	if(shoesSize == null || shoesSize == ""){
		msg = "请先选择鞋子尺码";
		$("#legend").append(msg);
		return false;
	}
	ids = ids.join(",");
	var params = {"ids":ids,"shoesSize":shoesSize}
	var ajaxUrl = "hosSterilizer/allotSizeShoesToSterilizer";
	zfesAjax.ajaxTodo(ajaxUrl, params, function(data) {
		alertSwal.successText(data.message);
		loadSte();
	});
}
function empty(){
	$("#legend").empty();
	if(idArr[0] == null || idArr[0] == ""){
		msg = "请选择要清除的小柜";
		$("#legend").append(msg);
		return false;
	}
	alertSwal.confirm("清空", "是否确认清空选中的消毒小柜?", function(isConfirm) {
		if(isConfirm){
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
function emptyAll(){
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
