<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<style type="text/css">
	div.seatCharts-cell {
	color: #182C4E;
	height: 105px;
	width: 105px;
	line-height: 15px;
	margin: 5px;
	float: left;
	text-align: center;
	outline: none;
	font-size: 13px;
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
		<div id="wardrobe_lists"></div>
		<hr>
		<footer style="text-align: center;">
				<button type="button" id="saveBtn" class="btn btn-primary" onclick="saveAllot();">
					保存
				</button>
				<button type="button" id="emptyBtn" class="btn btn-primary" onclick="empty();">
					清空托盘
				</button>
				<button type="button" id="emptyAllBtn" class="btn btn-primary" onclick="emptyAll();">
					清空全部
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					取消
				</button>
			</footer>
		<hr>	
<input type="hidden" value="${warNumber}" id="warNumber_input"/>
<script type="text/javascript">
$(function(){
	loadWardrobe();
});

function loadWardrobe(){
	var number = $("#warNumber_input").val();
	var url = "hosWardrobe/enterOperationPageList?warNumber="+number;
	$("#wardrobe_lists").load(url);
}

function saveAllot(){
	timer($("#saveBtn"));
	var ids = getSelectIds();
	ids = ids.join(",");
	var clothSize = $("#clothSize").val();
	var params = {"ids":ids,"clothSize":clothSize}
	//console.log("trayNamber:"+trayNamber+";cloth:"+cloth+";allowCount:"+allowCount+";"+";allot:"+allot);
	var ajaxUrl = "hosWardrobe/allotOperation";
	zfesAjax.ajaxTodo(ajaxUrl, params, function(data) {
		alertSwal.successText(data.message);
		loadWardrobe();
	});
}
function empty(){
	timer($("#emptyBtn"),"清空中..","清空托盘");
	var ids = getSelectIds();
	ids = ids.join(",");
	alertSwal.confirm("清空", "是否确认清空该小柜?", function(isConfirm) {
		if(isConfirm){
			var ajaxUrl = "hosWardrobe/emptyContainer";
			var param = {"ids":ids}
			zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
				alertSwal.successText(data.message);
				loadWardrobe();
			});
		}
	});
	
}
function emptyAll(){
	timer($("#emptyAllBtn"),"清空中..","清空全部");
	alertSwal.confirm("清空", "是否确认清空该衣柜?", function(isConfirm) {
		if(isConfirm){
			var number = $("#warNumber_number").val();
			var ajaxUrl = "hosWardrobe/emptyAllContainer";
			var param = {"number":number};
			zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
				alertSwal.successText(data.message);
				loadWardrobe();
			});
		}
	});
}

function loadDict(){
	$('select[dict]').each(function() {
		var dict = $(this).attr('dict');
		var data = window.top[dict];
		var value = $(this).attr('data-value');
		var option = "<option value=''>请选择尺码</option>";
		$(this).append(option);
//		console.log(data)
		for(var i=0;i<data.length;i++){
			$('<option>').html(data[i].name).attr('value',data[i].code).appendTo($(this));
		}
		if(value != null && value != "" && value !=undefined && value !="undefined" ){
			$(this).val(value);
		}
	});
	
}
</script>
