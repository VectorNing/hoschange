<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ include file="/libs/includeall.jsp"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/libs/css/touch4.css">
<script src="${pageContext.request.contextPath}/js/dict.js"></script>

<t:DataDict type="shoes" var="shoes"></t:DataDict>
<t:DataDict type="cloth" var="cloth"></t:DataDict>

<html>
<head>
<meta charset="UTF-8">
<title>神思旭辉</title>
</head>
<body class="signin fixed-header minified">
	<header id="header">
		<div id="logo-group" style="width: 99%">
			<span id="logo"> <img
				src="${pageContext.request.contextPath}/libs/img/logo.png"
				alt="SmartAdmin"></span> <span style="float: right; margin: 7px;">
				<a class="btn btn-primary btn-rounded " href="javascript:goIndex();">返回首页</a>
			</span> <span style="float: right; margin: 7px;"> <a
				class="btn btn-primary btn-rounded " href="javascript:goBack();">返回上一页</a>
			</span>
		</div>
	</header>

	<div class="modal-body no-padding" id="main">
		<fieldset>
			<div class="row" style="margin: 10px;">
				<div class="col-md-12">
					<div
						style="height: 90%; overflow: auto; background-color: #000000; background-color: rgba(0, 0, 0, 0.2);">
						<div class="col-md-8">
							<c:forEach items="${lists}" var="r" varStatus="vs">
								<input type="hidden" value="${r.number}" id="warNumber_number" />
								<c:forEach items="${r.hoss}" var="u" varStatus="st">
									<div class="seatCharts-cell">
										<div class="seatCharts-cell seatCharts-seat">
											<div class="steTitle">
												<span>衣柜</span> <strong style="color: black;">${u.number}</strong>
											</div>
											<div data-id="${u.id}" data-state="${u.state}"
												data-number="${u.number}"
												class="seatCharts-cell seatCharts-seat available">
												<c:forEach items="${u.containerList}" var="co"
													varStatus="sa">
													<div class="seatCharts-cell seatCharts-seat cell-con"
														data-id="${co.id}" data-size="${co.opeSize}"
														data-max="${co.trayTotal-co.alloutCount}"
														data-number="${u.number}">
														<div class="cell">
															<p class="trayNumber">${co.trayNumber}</p>
															<p class="size">
																<c:if test="${co.opeSize !=null}">
																	<sx:dataDic type="cloth" value="${co.opeSize}" />
																</c:if>
															</p>
															<p class="count">${co.alloutCount}/${co.trayTotal}</p>
														</div>
														<div data-id="${co.id}" data-number="${co.trayNumber}"
															class="seatCharts-cell-con seatCharts-seat-con available war">
														</div>
														<input class="allot" type="number"
															id="${co.id+co.trayNumber}"
															onclick="showTable(${u.id},this.id)">
														<c:if test="${co.alloutCount>=co.trayTotal*0.6}">
															<div class="green"
																style="height: ${co.alloutCount*1.325}px"></div>
														</c:if>
														<c:if test="${co.alloutCount<co.trayTotal*0.6}">
															<c:if test="${co.alloutCount>co.trayTotal*0.2}">
																<div class="yellow"
																	style="height: ${co.alloutCount*1.325}px"></div>
															</c:if>
														</c:if>
														<c:if test="${co.alloutCount<=co.trayTotal*0.2}">
															<div class="red"
																style="height: ${co.alloutCount*1.325}px"></div>
														</c:if>
													</div>
												</c:forEach>
												<div class="numTable" id="${u.id}">
													<div class="button" onclick="hiddeTable(${u.id})"></div>
													<div class="num">
														<input type="text" class="sumtext" id="sum${u.id}">
														<c:forEach begin="1" end="9" var="i" varStatus="status">
															<c:if test="${status.index==1}">
																<ul>
															</c:if>
															<li class="num${status.index}"
																onclick="numinput(${status.index})">${status.index}</li>
															<c:if test="${status.index%3==0}">
																</ul>
																<ul>
															</c:if>
														</c:forEach>
														<li class="num0" onclick="numinput(0)">0</li>
														</ul>
														<li class="delete" onclick="sumdelete()">重置</li>
														<li class="back" onclick="hiddeTable(${u.id})">取消</li>
														<li class="sure" onclick="suminput(${u.id})">确认</li>
													</div>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:forEach>
						</div>
						<div class="col-md-4">
							<div class="booking_area">
								<form action="" id="addHosOperationForm">
									<div class="ttitle">手术衣分配</div>

									<table class="sizeTable">
										<tr>
											<th>尺码</th>
											<c:forEach items="${sizeAndCountList}" var="sc"
												varStatus="scl">
												<th>${sc.name}</th>
											</c:forEach>
										<tr>
										<tr>
											<th>预分配</th>
											<c:forEach items="${sizeAndCountList}" var="sc"
												varStatus="scl">
												<td id="count_${sc.clothSize}"></td>
											</c:forEach>
										</tr>
									</table>

									<!-- <button type="button" class="btn btn-primary" id="fenpei" onclick="autoAllot()">分配所有</button>
											<br> -->
									<footer>
										<button type="button" class="btn btn-primary" id="allout_one"
											onclick="addAllotOne();">确认</button>
										<button type="button" class="btn btn-primary" id="allout_two"
											onclick="saveAllot();">分配</button>
										<button type="button" class="btn btn-primary"
											onclick="empty();">清空托盘</button>
										<button type="button" class="btn btn-primary"
											onclick="emptyAll();">清空全部</button>
									</footer>
									<br />
							</div>
						</div>
					</div>
				</div>
				<fieldset>
			</div>
	</div>
	<div class="pageUpDown">
		<button class="btn btn-primary" type="button" onclick="pageUp()">
			<i class="fa fa-arrow-up"> </i>
		</button>
		<br>
		<button class="btn btn-primary" type="button" onclick="pageDown()">
			<i class="fa fa-arrow-down"> </i>
		</button>
		<br>
	</div>
</body>

<script type="text/javascript">
	
	var idArr = [], aa = [];
	var number = [];
	var sinputid = 0;
	$('.seatCharts-cell.seatCharts-seat.cell-con').click(function(e) {
		if(e.target.tagName.toLowerCase()!='input')
			$(this).toggleClass('selected');
	});
	/*点击数量修改会弹出小键盘*/
	function showTable(dataid,inputid){
		var id = "#"+dataid
	/* 	$('div[id^='+ dataid +']').find('div.numTable').attr("display","inherit"); */
		$(id).css("display","inherit");
		sinputid = inputid*10;
		$(id).find('input').attr("id",sinputid);
	
		
	}
	/*弹出小键盘时点击上面空白区域会退出小键盘模式*/
	function hiddeTable(dataid){
		var id = "#"+dataid
		$(id).css("display","none");
	}
	/*小键盘输入*/
	function numinput(num){
		if($('.sumtext').val()==''){
			$('.sumtext').val(num);
		}else{
			var c=$('.sumtext').val();
			var d=c+num;
			$('.sumtext').val(d);
		}
		
	}
	/*小键盘输入内容重置*/
	 function sumdelete(){
		var id = "#"+sinputid;
		$(id).val("");
	} 
	/*小键盘确认键返回输入的值*/
	function suminput(dataid){
		/* var t = parseInt("sinputid"); */
		var t = sinputid/10;
		var tid = "#"+t;
		var id = "#"+sinputid;
		/*获取小键盘的数字*/
		var value = $(id).val();
		if(value=="")
			value=0;
		/*赋值给外面的input*/
		$(tid).val(value);
		hiddeTable(dataid);
		sumdelete();
		$(tid).change();
	}
	function selectCount(value){
		var url="${pageContext.request.contextPath}/hosWardrobe/queryOperationAllowCount";
		var param = { "clothSize" : value};
		zfesAjax.ajaxTodo(url,param, function(data){
			$("#allowCount").empty();
			var allowCount = data.strData;
			$("#allowCount").val(allowCount);
		})
	}
	function saveAllot(){
		var warNumber = $("#warNumber_number").val();
		//装货成功，调用硬件装货成功
		lodingSuccess(warNumber);
		var ajaxUrl = "${pageContext.request.contextPath}/touch/allotOperationToContainerForZD";//修改数量
		zfesAjax.ajaxTodo(ajaxUrl, {ids:JSON.stringify(getAlloc())}, function(data) {
			alertSwal.successText(data.message);
			var url = "${pageContext.request.contextPath}/touch/updateHosWardrobeEnabledByNumber";//设置暂停服务
			var param = {enabled:1,number:warNumber};
			zfesAjax.ajaxTodo(url, param, function(data) {
				clearWardorbeLocal();
				
				setTimeout(goIndex,4000);
			});
		});
	}
	function lodingSuccess(warNumber){
		var url = "${pageContext.request.contextPath}/touch/lodingSuccess";
		var param = {warNumber:warNumber};
		$.ajax({
			url : url,
			type : 'POST',
			data : param,
			dataType : "json",
			cache : false,
			success : function(data) {
			},
		});
	}
	function addAllotOne(){
		var count= 0 ;
		var allotList = $('[id^=count_]');
		allotList.each(function(){
			count += $(this).html();
		})
		if(count==0){
			alertSwal.warningText("预分配数量为0,请核对后重试");
			return false;
		} 
		saveWardorbeLocal();
		var warNumber = $("#warNumber_number").val();
		sendLodingMessageToDevice(warNumber);
		var url = "${pageContext.request.contextPath}/touch/updateHosWardrobeEnabledByNumber";
		var param = {enabled:0,number:warNumber};
		zfesAjax.ajaxTodo(url, param, function(data) {
			swal({title:'请在设备分放相对应的手术服，完成后请再次确认!',type:"success",timer: 4000});
			setTimeout(goIndex,5000);
		});
		
	}
	function sendLodingMessageToDevice(warNumber){
		var url = "${pageContext.request.contextPath}/touch/loding";
		var param = {warNumber:warNumber};
		$.ajax({
			url : url,
			type : 'POST',
			data : param,
			dataType : "json",
			cache : false,
			success : function(data) {
			},
		});
	}
	function empty(){
		var ids = [];
		var warNumber = $("#warNumber_number").val(); 
		$('.seatCharts-cell.seatCharts-seat.cell-con.selected').each(function(){
			ids.push($(this).attr('data-id'));
		})
		if( ids.length <= 0){
			alertSwal.warningText("请先选择一个小柜");
			return false;
		}
		alertSwal.confirm("清空", "是否确认清空选中小柜?", function(isConfirm) {
			if(isConfirm){
				var ajaxUrl = "${pageContext.request.contextPath}/touch/emptyContainer";
				zfesAjax.ajaxTodo(ajaxUrl, {ids:ids.join(',')}, function(data) {//{ids:JSON.stringify(ids)}
					alertSwal.successText(data.message);
					window.location.href="${pageContext.request.contextPath}/touch/allotWardrobe?warNumber="+warNumber;
				});
			}
		});
		
	}
	function emptyAll(){
		var warNumber = $("#warNumber_number").val(); 
		alertSwal.confirm("清空", "是否确认清空该衣柜?", function(isConfirm) {
			if(isConfirm){
				 var number = $("#warNumber_number").val();
				var ajaxUrl = "${pageContext.request.contextPath}/touch/emptyAllContainer";
				var param = {"number":number};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successText(data.message);
					window.location.href="${pageContext.request.contextPath}/touch/allotWardrobe?warNumber="+warNumber;
				});
			}
		});
	}
	
	function pageUp(){
		$('html body').scrollTop($('html body').scrollTop()-500);
	}
	function pageDown(){
		$('html body').scrollTop($('html body').scrollTop()+500);
	}
	function goBack(){
	    window.history.go(-1);
	}
	function goIndex(){
		window.location.href="${pageContext.request.contextPath}/touch/touchWardorbeGoIndex"
	}
	function autoAllot(){
		var allotList = $('[id^=count_]');
		allotList.each(function(){
			var size = $(this).attr('id').split('_')[1];
				$('.seatCharts-cell.seatCharts-seat.cell-con[data-size='+ size +'] input.allot').each(function(){
					var cell = $(this).parent();
					var max = parseInt(cell.attr('data-max'));
					$(this).val(max).change();
				})
		})
	}
	function calcAllot(){
		var sum = {};
		$('.seatCharts-cell.seatCharts-seat.cell-con').each(function(){
			var size = $(this).attr('data-size');
			if(typeof sum[size] == 'undefined')
				sum[size] = 0
			sum[size] += parseInt($(this).find('input.allot').val()!='' ? $(this).find('input.allot').val() :'0');
		});
		for(var size in sum){
			$('#count_'+size).html(sum[size]);
		}
	}
	function getAlloc(){
		var result = [];
		$('.seatCharts-cell.seatCharts-seat.cell-con').each(function(){
			if(parseInt($(this).find('input.allot').val())>0){
				result.push({
					id:$(this).attr('data-id'),
					count:$(this).find('input.allot').val(),
					size:$(this).attr('data-size'),
					number:$(this).attr('data-number')
				})
			}
		})
		return result;
	}
	$(function(){
		$('input.allot').change(function(){
			var cell = $(this).parent();
			var max = parseInt(cell.attr('data-max'));
			var id = cell.attr('data-id');
			var size = cell.attr('data-size');
			
			var input = parseInt($(this).val());
			 if(input>max)
				$(this).val(max);
			if(input<0)
				$(this).val(0);//不允许输入超过容量的数字  
			if(size==0||size==null){
				$(this).val(0);
			}
			
			var sum = 0;
			$('.seatCharts-cell.seatCharts-seat.cell-con[data-size='+size+']').each(function(){
				sum += parseInt($(this).find('input.allot').val()!='' ? $(this).find('input.allot').val() :'0');
			});
			$('#count_'+size).html(sum);
			
		})
		if(loadWardorbeLocal()){//第二次进入
			swal({
				title:"确认分配",
				text:"请确认分配信息是否需要修改",
				type: "warning",
				showCancelButton: true,
				showConfirmButton: true,
				closeOnConfirm: false,
				confirmButtonText: "确认分配",
				cancelButtonText: "修改分配信息",
				confirmButtonColor: "#ec6c62"
			},function(){
				saveAllot()
				});
			$("#allout_one").hide();
			$("#allout_two").show();
		}else{
			autoAllot();
			$("#allout_one").show();
			$("#allout_two").hide();
		}
	});
	
	//获取选中的行
	function getSelectIds(){
		var ids = [];
		$('.seatCharts-cell.seatCharts-seat.cell-con.selected').each(function(){
			ids.push($(this).attr('data-id'));
		})
		return ids;
	}
	function getIdsNumbers(){
		var numbers = [];
		$('input.allot').each(function(){
			var cell = $(this).parent();
			var max = parseInt(cell.attr('data-max'));
			var id = cell.attr('data-id');
			var size = cell.attr('data-size');
			
			var input = parseInt($(this).val());
			numbers.push(input);
		})
		return numbers;
	}
	
	//本地存储保存选中小柜id数组
	function saveWardorbeLocal(){
		localStorage.setItem('WardorbeLocal',JSON.stringify(getSelectIds()));
		localStorage.setItem('WardorbeLocalNumbers',JSON.stringify(getIdsNumbers()));
		localStorage.setItem("saveAllot_allotWardrobe",0);//是否暂停服务(0 暂停)
	}
	//加载本地存储保存的选中托盘id数组 存在返回true 无内容加载返回false
	function loadWardorbeLocal(){
		if(localStorage.getItem('WardorbeLocal')){
			var list = JSON.parse(localStorage.getItem('WardorbeLocal'));
			var numberList = JSON.parse(localStorage.getItem('WardorbeLocalNumbers'));
			console.log(numberList)
			for(var i=0; i<list.length; i++){
				$('.seatCharts-cell.seatCharts-seat.cell-con[data-id='+list[i]+']').click();
			}
			$('.seatCharts-cell.seatCharts-seat.available').find('input').each(function(index){
				$(this).val(numberList[index]);
			})
		//	changeLoadAllot();
			return true;
		}
		return false;
	}
	//清空本地存储保存选中小柜id数组
	function clearWardorbeLocal(){
		localStorage.removeItem('WardorbeLocal');
		localStorage.removeItem('WardorbeLocalNumbers');
		localStorage.removeItem('saveAllot_allotWardrobe');//清除锁屏标志
	}
	
	function changeLoadAllot(){
		$('input.allot').each(function(){
			var cell = $(this).parent();
			var max = parseInt(cell.attr('data-max'));
			var id = cell.attr('data-id');
			var size = cell.attr('data-size');
			
			var input = parseInt($(this).val());
			if(input>max)
				$(this).val(max);
			if(input<0)
				$(this).val(0);
			if(size==0||size==null){
				$(this).val(0);
			}
			
			var sum = 0;
			$('.seatCharts-cell.seatCharts-seat.cell-con[data-size='+size+']').each(function(){
				sum += parseInt($(this).find('input.allot').val()!='' ? $(this).find('input.allot').val() :'0');
			});
			$('#count_'+size).html(sum);
			
			/* if(sum >= parseInt($('#canAllot_'+size).html())){
				var padding =  sum - parseInt($('#canAllot_'+size).html()) ;
				sum -= padding;
				$(this).val( parseInt($(this).val()) - padding);  
				$('#count_'+size).html( $('#canAllot_'+size).html());
			} */
		})
	}
</script>
</html>
