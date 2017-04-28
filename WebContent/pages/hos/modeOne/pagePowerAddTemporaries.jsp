<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/libs/js/bootstrap-suggest.js"></script>
<style>
</style>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title" id="myModalLabel">添加临时权限</h4>
</div>




<div class="modal-body no-padding">

	<form action="" id="addShoesForm" class="smart-form">

		<fieldset>

			<section>
				<div class="row">
					<label class="label col col-2">用  户</label>
					<div class="col col-10">
						<div class="input-group">
							<input type="text" class="form-control" id="renyuan"
								autocomplete="off">
							<div class="input-group-btn">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="">
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu dropdown-menu-right" role="menu"></ul>
							</div>
						</div>
					</div>
				</div>
			</section>

			<section>
				<div class="row">
					<label class="label col col-2">权限开始时间</label>
					<div class="col col-10">
						<label class="input"> <input id="startinputtime"
							type="text" name="begintime" class="laydate-icon">
						</label>

					</div>
				</div>
			</section>

			<section>
				<div class="row">
					<label class="label col col-2">权限结束时间</label>
					<div class="col col-10">
						<label class="input"> <input id="endinputtime" type="text"
							name="endtime" class="laydate-icon">
						</label>
					</div>
				</div>
			</section>

		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary"
				onclick="saveShoesForm();">添加</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消</button>
		</footer>

	</form>
</div>
<script>
	var maxt = '2999-06-16 00:00:00';
	var mint = '2010-00-00 00:00:00';
	var startat = {
		elem : '#startinputtime',
		format : 'YYYY-MM-DD hh:mm:ss',
		min : mint,
		max : maxt,
		istime : true,
		istoday : false,
		choose : function(datas) {
			endat.min = datas;
		}
	};
	var endat = {
		elem : '#endinputtime',
		format : 'YYYY-MM-DD hh:mm:ss',
		min : mint,
		max : maxt,
		istime : true,
		istoday : false,
		choose : function(datas) {
			startat.max = datas;
			startat.end = datas;
		}
	};

	setInterval(function() {
		is();
	}, 1000);
	function is() {
		if (($("#startinputtime").val() == '')
				&& ($("#endinputtime").val() == '')) {
			startat.choose(mint);
			endat.choose(maxt);
		}
	};

	$(function() {
		$("#startinputtime").focus(function() {
			laydate(startat);

		});

		$("#endinputtime").focus(function() {
			laydate(endat);
		});

		var theater_starttimeval;
		var theater_endtimeval;
		$("#renyuan")
				.bsSuggest(
						{
							url : "${pageContext.request.contextPath}/authUser/queryUserByName",
							effectiveFields : [ "userName" ],
							searchFields : [ "userName" ],
							showHeader : false,
							autoSelect : false,
							showBtn : false,
							idField : "id",
							keyField : "userName"
						}).on('onDataRequestSuccess', function(e, result) {
					console.log('onDataRequestSuccess: ', result);
				}).on('onSetSelectValue', function(e, keyword, data) {
					console.log('onSetSelectValue: ', keyword, data);
				}).on('onUnsetSelectValue', function() {
					console.log("onUnsetSelectValue");
				});
	});

	function saveShoesForm() {
		var userid = $("#renyuan").attr("data-id");
		var begintime = $("#startinputtime").val();
		var endtime = $("#endinputtime").val();
		$.ajax({
			url : "hosPower/addTemporaries", //后台webservice里的方法名称  
			type : "post",
			data : {
				userId : userid,
				begintime : begintime,
				endtime : endtime
			},
			dataType : "json",
			success : function(data) {
				alertSwal.infoText(data.message);
			},
		});
	}
</script>