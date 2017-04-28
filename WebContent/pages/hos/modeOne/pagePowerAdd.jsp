<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/libs/js/bootstrap-suggest.js"></script>
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/laydate/laydate.js"></script> --%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title" id="myModalLabel">添加跟台人员</h4>
</div>


<div class="modal-body no-padding">

	<form action="" id="addPowerForm" class="smart-form">

		<fieldset>

			<section>
				<div class="row">

					<label class="label col col-3">人员</label>
					<div class="col col-9">
						<div class="input-group">
							<input type="text" class="form-control" id="renyuanInput"
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
					<label class="label col col-3">手术开始时间</label>
					<div class="col col-9">
						<label class="input"> <input id="startinputTime"
							type="text" name="startinputTime" class="laydate-icon">
						</label>

					</div>
				</div>
			</section>

			<section>
				<div class="row">
					<label class="label col col-3">手术结束时间</label>
					<div class="col col-9">
						<label class="input"> <input id="endinputTime" type="text"
							name="endinputTime" class="laydate-icon">
						</label>

					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-3">手术室名称</label>
					<div class="col col-9">
						<label class="select"> <select class="form-control"
							name="theaterNameNumber" id="theaterName">


						</select>
						</label>

					</div>
				</div>
			</section>
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="save();">添加</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消</button>
		</footer>

	</form>
</div>
<script>
var maxt='2999-06-16 00:00:00';
var mint='2010-00-00 00:00:00';
var starta = {
		elem: '#startinputTime',
		format: 'YYYY-MM-DD hh:mm:ss',
		min: mint, 
		max: maxt, 
		istime: true,
		istoday: false,
		choose: function(datas) {
			enda.min = datas; 						
		}
	};
	var enda = {
			elem: '#endinputTime',
			format: 'YYYY-MM-DD hh:mm:ss',
			min: mint, 
			max: maxt,
			istime: true,
			istoday: false,
			choose: function(datas) {
			starta.max=datas;
				starta.end = datas; 
			}
	};

		$("#startinputTime").focus(function(){
			laydate(starta);	
			
		});
		  setInterval(function(){is();},1000);
		function is(){
			if (($("#startinputTime").val()=='')&&($("#endinputTime").val()=='')) {
				starta.choose(mint);enda.choose(maxt);
			}
		}; 
		$("#endinputTime").focus(function(){
			laydate(enda);	
		});
		
	
	$("#renyuanInput").bsSuggest({
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
$("#theaterName").focus(function(){
	 $("#startinputTime").blur();
	$("#endinputTime").blur();
});

	$("#startinputTime").blur(function() {
		var s = $("#startinputTime").val();
		var e = $("#endinputTime").val();
		var r=$("#renyuanInput").val();
		if (s != '' && e != "") {
			selecttheaterName(s, e);
		}
		else{
			$("#theaterName").children("option").remove();	
		}
	});
	
	$("#endinputTime").blur(function() {
		var s = $("#startinputTime").val();
		var e = $("#endinputTime").val();
		var r=$("#renyuanInput").val();
		if (s != '' && e != "") {
			selecttheaterName(s, e);
		}
	});
	$("#renyuanInput").change(function(){
		$("input.laydate-icon").val("");
		$("#theaterName").children("option").remove();
	});	
	function save(){				
		var scheduleid=$("#theaterName").val();
		var userval=$("#renyuanInput").val();
		if(scheduleid==''||scheduleid==null||scheduleid=='暂无'||scheduleid=='请选择...'){
			swal("手术名称不能为空","请根据始终时间选择手术名称","warning");	
		}
		else if(userval==''||userval==null){
			swal("人员不能为空","请先选择人员","warning");	
		}
		else{
			var userid=$("#renyuanInput").attr("data-id");
		$.ajax({
			url : "${pageContext.request.contextPath}/hosPower/addFollower", 
			type : "post",
			data : {
				userId : userid,
				schedulid :scheduleid
			},
			dataType : "json",
			success : function(data) {
				alertSwal.infoText(data.message);
				clearval();
			},
		});
		}
	}
	function clearval(){
		$("#addPowerForm section>.row input").val("");
		$("#theaterName").children("option").remove();
	}
	
	function selecttheaterName(begin, end) {
		$.ajax({
					url : "${pageContext.request.contextPath}/hosScheduling/queryHosSchedulingByTime", //后台webservice里的方法名称  
					type : "post",
					data : {
						begintime : begin,
						endtime : end
					},
					dataType : "json",					
					success : function(data) {	
						$("#theaterName").children("option").remove();
						
						if(data.length>0){	
							$("#theaterName").append('<option>请选择...</option>');
						for (var j = 0; j < data.length; j++) {														
							$("#theaterName").append(
									'<option value='+data[j].id +'>'+'手术室:'+data[j].name +'  手术单号:'+data[j].operationNumber+ '</option>');							
						}
						}
						else{
	
							 $("#theaterName").append(
									'<option>暂无</option>'); 
						}
					},
				});
	}
	
</script>