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
	<h4 class="modal-title" id="myModalLabel">查看</h4>
</div>
		<div id="wardrobe_lists"></div>
		<hr>
<input type="hidden" value="${warNumber}" id="warNumber_input"/>
<script type="text/javascript">
$(function(){
	loadWardrobe();
});

function loadWardrobe(){
	var number = $("#warNumber_input").val();
	var url = "hosWardrobe/enterLoadOperationPageList?warNumber="+number;
	$("#wardrobe_lists").load(url);
}

</script>
