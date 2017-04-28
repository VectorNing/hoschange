<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<div class="modal-body no-padding">
	<fieldset>
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-6">
				<c:forEach items="${rooms}" var="r" varStatus="vs">
					<div class="panel panel-default">
						<!-- <div class="panel-heading clearfix">
							<div class="btn-group pull-right">
								<button data-id="1"  class="btn btn-primary allot-btn">分配手术服</button>
								<button data-id="1"  class="btn btn-danger clean-btn" >清空手术服</button>
							</div>
						</div>-->
						<div class="row">	
							<div class="smart-form">
								<label class="label col col-6">手术柜托盘:</label>
							</div>
						</div>
						<div class="panel-body" style="height: 320px;">
							<c:forEach items="${r.HosWardrobeContainerList}" var="u" varStatus="st">
								<div class="seatCharts-cell">
									<div class="seatCharts-cell seatCharts-seat">
									<c:choose> 
									<c:when test="${u.trayTotal > u.alloutCount}">
										<div data-id="${u.id}" data-opeSize="${u.opeSize}" class="seatCharts-cell seatCharts-seat available war">
											<p>编码:${u.trayNumber}</p>
											<p>容量:${u.trayTotal}</p>
											<p>尺码:${u.opeSizeName}</p>
											<p>已有数量:${u.alloutCount}</p>
										</div>
									</c:when>
									<c:otherwise>	
										<div data-id="${u.id}" data-opeSize="${u.opeSize}" class="seatCharts-cell seatCharts-seat unavailable non">
											<p>编码:${u.trayNumber}</p>
											<p>容量:${u.trayTotal}</p>
											<p>尺码:${u.opeSizeName}</p>
											<p>已有数量:${u.alloutCount}</p>
										</div>
									</c:otherwise>
									</c:choose>	
									</div>
								</div>
								
							</c:forEach>
						</div>
					</div>
				</c:forEach>
			</div>	
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-body">
						<div  class="booking_area" style="height: 320px;">
							<form action="" id="addHosOperationForm">
							<section>
								<div class="row">	
									<div class="smart-form">
										<label class="label col col-4">已选托盘编码:</label>
										<ul id="tray_chose"></ul><br/>
										<!-- <div id="tray_chose">
											
										</div> -->
									</div>
								</div>
							</section>
							<hr>
							<section>
								<div class="row">
									<div class="smart-form">
										<label class="label col col-4">衣服尺码:</label>
										<div class="col col-4">
											<label class="select"> 
												<select dict="cloth" name="clothSize" id="clothSize" onchange="selectCount(this.value);">
												</select>
											</label>
										</div>
									</div>
								</div>
							</section>	
							<hr>
							<section>
								<div class="row">
									<div class="smart-form">
										<label class="label col col-4">可分配数量:</label>
										<input type="text" name="allowCount" id="allowCount" style="width: 100px;" disabled="disabled">
									</div>
								</div>	
							</section>	
							<hr>
							<section>
								<div class="row">
									<div class="smart-form">
										<label class="label col col-4">分配数量:</label>
										<label class="input">
											<input type="text" name="allot" id="allot" style="width: 100px;">
										</label>
									</div>	
								</div>
							</section>
							<hr>
							<div id="legend" style="text-align: center;padding: 10px;color: red;"></div>
							
							</form>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		<fieldset>
	</div>
		<hr>
		<footer style="text-align: center;">
				<button type="button" class="btn btn-primary" onclick="saveAllot();">
					保存
				</button>
				<button type="button" class="btn btn-primary" onclick="empty();">
					清空托盘
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					取消
				</button>
			</footer>
		<hr>	
</div>
<script type="text/javascript">
$(function(){
	validateFrom();
});
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

var idArr=[],aa=[];
$('.war').hover(function(){
	$(this).addClass("focused");
   },function(){
	   $(this).removeClass("focused");
    });
$('.war').click(function(e){
	
	var t=$(e.target);
	if(e.target.tagName!='DIV')
		t=t.closest('.war');
	if(idArr.length==1 && idArr[0]!=t.attr('data-id')){
// 		alertSwal.errorText("只能");
		return;
	}
	if(t.hasClass("selected"))
		t.removeClass("selected");
	else
		t.addClass("selected");
	var seats = $(".seatCharts-cell>.seatCharts-cell").find('.selected');
	idArr=[];
	seats.each(function(){
		idArr.push($(this).attr('data-id'));
	});
	if(idArr.length==1){
		aa=[];
		t.parent().parent().siblings().find('.seatCharts-cell>div').each(function(){
			if($(this).hasClass('war'))
				aa.push($(this).attr('data-id'));
			$(this).addClass('unavailable');
		})
	}
	if(idArr.length==0){
		var siblingNode=t.parent().parent().siblings().find('.seatCharts-cell');
		for(var i =0 ;i<aa.length;i++){
			siblingNode.find('[data-id='+aa[i]+']').removeClass('unavailable');
		}
	}
	//console.log(idArr);
	$("#tray_chose").empty();
	for(var i = 0; i < idArr.length;i++){
		if(idArr[i] != "" && typeof(idArr[i]) != "undefined"){
			$('<li>'+ idArr[i] + '</li>').appendTo($("#tray_chose"));
			//$('<div class="detail">编码:'+ idArr[i] + '尺码:<select dict="cloth" name="cloth" id="cloth" onchange="selectCount(this.value);"></select>分配数量:<input type="text" name="count" style="width:50px;">可分配数量:<label id="allowCount"></lable></div><hr>').appendTo($("#tray_chose"));
			
		}
	}
	
	//loadDict();
	
});



function selectCount(value){
	var url="hosWardrobe/queryOperationAllowCount";
	var param = { "clothSize" : value};
	zfesAjax.ajaxTodo(url,param, function(data){
		$("#allowCount").empty();
		var allowCount = data.strData;
		$("#allowCount").val(allowCount);
	})
}
function saveAllot(){
	var trayNamber = idArr[0];
	var allowCount = $("#allowCount").val();
	var allot = $("#allot").val();
	var clothSize = $("#clothSize").val();
	var msg = "";
	$("#legend").empty();
	if(trayNamber == null || trayNamber == ""){
		msg = "请先选择一个衣柜托盘";
		$("#legend").append(msg);
		return false;
	}
	if(clothSize == null || clothSize == ""){
		msg = "请先选择衣服尺码";
		$("#legend").append(msg);
		return false;
	}
	if(allot == null || allot == ""){
		msg = "请填写分配数量";
		$("#legend").append(msg);
		return false;
	}
	if(parseInt(allot) > parseInt(allowCount)){
		//console.log(allot+";"+allowCount);
		msg = "分配数量不能大于可分配数量";
		$("#legend").append(msg);
		return false;
	}
	var params = {"trayNamber":trayNamber,"clothSize":clothSize,"count":allot}
	//console.log("trayNamber:"+trayNamber+";cloth:"+cloth+";allowCount:"+allowCount+";"+";allot:"+allot);
	var ajaxUrl = "hosWardrobe/allotOperation";
	zfesAjax.ajaxTodo(ajaxUrl, params, function(data) {
		alertSwal.successText(data.message);
		$('#allotModel').modal('hide');
	});
}
function empty(){
	var trayNumber = idArr[0];
	var ajaxUrl = "hosWardrobe/emptyContainer";
	var param = {"trayNumber":trayNumber}
	zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
		alertSwal.successText(data.message);
		$('#allotModel').modal('hide');
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
