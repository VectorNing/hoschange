<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<style>
.col-md-8>.seatCharts-cell {
	height: 500px;
	width: 600px;
	overflow: auto;
}
</style>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title" id="myModalLabel">开柜、锁柜</h4>
</div>
<div id="container_div">
	<div></div>
</div>
<hr>
<footer style="text-align: center;">
	<button type="button" id="openBtn" class="btn btn-primary"
		onclick="openContainer();">打开小柜</button>
	<button type="button" id="openAllBtn" class="btn btn-primary"
		onclick="clean();">清空小柜</button>
	<button type="button" id="lockBtn" class="btn btn-primary"
		onclick="lock();">锁定小柜</button>
	<button type="button" id="lockAllBtn" class="btn btn-primary"
		onclick="stopLock();">取消锁定</button>


</footer>
<hr>
<input type="hidden" value="${steNumber}" id="steNumber_input" />
<input type="hidden" value="${rows}" id="rows_input" />
<input type="hidden" value="${columns}" id="columns_input" />
<script type="text/javascript">
$(function(){
	loadSte();
});
function loadSte(){
	var steNumber = $("#steNumber_input").val();//消毒柜编号
	var rows = $("#rows_input").val();
	var columns = $("#columns_input").val();
	var url = "hosSterilizer/enterAllotShoesList?steNumber="+steNumber+"&rows="+rows+"&columns="+columns+"&type=lock";
	$("#container_div").load(url);
}

function openContainer(){
	if(isNoLock($("#steNumber_input").val())){
		var numberss = getSelectNumbers();
		var numbers = numberss.join(",");
		var deviceNumber = $("#steNumber_input").val();//消毒柜编号
				var ajaxUrl = "${pageContext.request.contextPath}/hosSterilizer/openContainerShoesLog";
				var param = {"deviceNumber":deviceNumber,"numbers":numbers};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successTitle(data.message);
				});
	}else{
		alertSwal.warning("无法打开锁定中的小柜","请先取消锁定中小柜的选中状态");
	}
}
function clean(){
	if(isOnHave($("#steNumber_input").val())){
		var idArr = getSelectIds();
		var ids = idArr.join(",");
				var ajaxUrl = "${pageContext.request.contextPath}/touch/emptyContainerShoes";
				var param = {"ids":ids};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successTitle(data.message);
					loadSte();
				});
	}else{
		alertSwal.warning("无法清除未放置鞋子的小柜","请先取消未放置鞋子小柜的选中状态");
	}
}
function lock(){
	if(isOnUse($("#steNumber_input").val())){
		if(isNoLock($("#steNumber_input").val())){
					var idArr = getSelectIds();
					var ids = idArr.join(",");
					var ajaxUrl = "hosSterilizer/lockSterilizer";
					var param = {"ids":ids};
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						alertSwal.successText(data.message);
						loadSte();
					});
		}else{
			alertSwal.warning("小柜已锁定","请先取消已锁定小柜的选中状态");
		}
	}else{
		alertSwal.warning("无法锁定使用中的小柜","请先取消使用中小柜的选中状态");
	}
}
function stopLock(){
	if(isAllLocked($("#steNumber_input").val())){
				var idArr = getSelectIds();
				var ids = idArr.join(",");
				var ajaxUrl = "hosSterilizer/stopLockSterilizer";
				var param = {"ids":ids};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successText(data.message);
					loadSte();
				});
	}else{
		alertSwal.warning("无法解锁未锁定的小柜","请取消非锁定小柜的选中状态");
	}
}

</script>
