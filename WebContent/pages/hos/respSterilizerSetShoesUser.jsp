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
	<h4 class="modal-title" id="myModalLabel">设定</h4>
</div>
<div id="container_div"></div>
<hr>
<footer style="text-align: center;">
	<button type="button" id="saveBtn" class="btn btn-primary"
		onclick="binding();">绑定使用人</button>
	<button type="button" id="emptyBtn" class="btn btn-primary"
		onclick="stopBinding();">取消绑定</button>
</footer>
<hr>
<input type="hidden" value="${steNumber}" id="steNumber_input" />
<input type="hidden" value="${rows}" id="rows_input" />
<input type="hidden" value="${columns}" id="columns_input" />
<script type="text/javascript">
	//
	 $(function() {
		loadSte();
	});
	function loadSte() {
		var steNumber = $("#steNumber_input").val();//消毒柜编号
		var rows = $("#rows_input").val();
		var columns = $("#columns_input").val();
		var url = "hosSterilizer/enterAllotShoesList?steNumber=" + steNumber
				+ "&rows=" + rows + "&columns=" + columns + "&type=binding";
		$("#container_div").load(url);
	} 

	//
	function getSelectId() {
		var id = $('.seatCharts-cell.seatCharts-seat.cell-con.select').attr(
				'data-id');
		if (id) {
			return id;
		} else {
			alertSwal.warningText("请选择小柜");
			return false;
		}
		;
	}
	function binding() {
		if (zfesBstable.isOneRow(jqTableDom1)) {
			var id = getSelectId();
			if (!id) {
				return false;
			}
			var row = zfesBstable.getSelections(jqTableDom1);
			var userId = row[0].id;
			var ajaxUrl = "hosSterilizer/bindingSterilizerAndUser";
			var param = {
				"id" : id,
				"userId" : userId
			};
			zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
				alertSwal.successText(data.message);
				loadSte();
			});

		}
	}
	function stopBinding() {
		var id = getSelectId();
		if (!id) {
			return false;
		}
		var ajaxUrl = "hosSterilizer/stopBindingSterilizerAndUser";
		var param = {
			"id" : id
		};
		zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
			alertSwal.successText(data.message);
			loadSte();
		});
	}
</script>
