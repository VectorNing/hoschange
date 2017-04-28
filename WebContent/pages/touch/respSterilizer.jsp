<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ include file="/libs/includeall.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/libs/css/touch.css">
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
			<span id="logo"> <img src="${pageContext.request.contextPath}/libs/img/logo.png" alt="SmartAdmin"></span> 
				<span style="float: right; margin: 7px;">
					<a class="btn btn-primary btn-rounded " href="javascript:goIndex();">返回首页</a>
				</span> 
				<span style="float: right; margin: 7px;"> 
				<a class="btn btn-primary btn-rounded " href="javascript:goBack();">返回上一页</a>
			</span>
		</div>
	</header>

	<div id="main" >
		<fieldset>
			
			<div class="row" style="margin: 10px;">
				<div class="col-md-12">
				
					<div style="height: 90%; overflow: auto;background-color:#000000;background-color:rgba(0,0,0,0.2);">
						<div class="col-md-10 containerBox">
							
							
							
							
							
							
							<c:forEach items="${lists}" var="r" varStatus="vs" >
								<input type="hidden" value="${r.number}" id="steNumber_number" />
									<c:forEach items="${r.hoss}" var="u" varStatus="st">
										<div class="seatCharts-cell">
											<div class="seatCharts-cell seatCharts-seat">
												<div class="steTitle">
													<p>${u.number}</p>
													  <c:forEach items="${u.sizes}" var="s" varStatus="sa">
													    <span style="color: black;"> <sx:dataDic type="shoes" value="${s.shoesSize}"/></span>
													  </c:forEach>
												</div>
												<div data-id="${u.id}" data-state="${u.state}" data-number="${u.number}" class="seatCharts-cell seatCharts-seat available">
													<c:forEach items="${u.containerList}" var="co" varStatus="sa">
														<div class="seatCharts-cell seatCharts-seat cell-con">
															<div class="cell">
																<p class="number">${co.lockerNumber}</p>
																<p class="size"><c:if test="${co.shoesSize == ''}"></c:if><c:if test="${co.shoesSize !=null}"><sx:dataDic type="shoes" value="${co.shoesSize}"/></c:if></p>
																<%-- <p>状态:<c:if test="${co.state == 0}">无</c:if><c:if test="${co.state == 1}">有</c:if></p> --%>
															</div>
															<div data-id="${co.id}" data-state="${co.state}" data-size="${co.shoesSize}" data-number="${co.lockerNumber}" data-steNumber="${u.number}" class="seatCharts-cell-con seatCharts-seat-con available war">
															</div>
														</div>
														<c:if test="${(sa.count)%(u.rows)==0}">
															<br/>
														</c:if>
													</c:forEach>
												</div>
											</div>
										</div>
									</c:forEach>
							</c:forEach>
							
						</div>
						
						<div class="col-md-2">
							<div class="booking_area">
								<form action="" id="addHosOperationForm">
									<section>
										<div class="row">
											<div class="col col-10">
												<label>个人信息</label>
											</div>
										</div>
									</section>
									<section>
										<div class="row">
											<div class="col col-10">
												<label>姓名:</label>
												<label class="input"> ${user.userName} </label>
											</div>
										</div>
									</section>
									<section>
										<div class="row">
											<div class="col col-10">
												<label>鞋码:</label>
												<label class="select"> <sx:dataDic type="shoes" value="${user.shoesSize}" />
												</label>
											</div>
										</div>
									</section>
									<%-- <section>
										<div class="row">
											<div class="col col-10">
													<label>更换鞋尺码:</label>
													<select dict="shoes" data-value="${user.shoesSize}"  style="color: black;" name="shoesSize">
													</select>
											</div>
										</div>
									</section> --%>
									<hr>
									<section>
										<div class="row">
												<label class="label col col-6">已选消毒柜编码:</label>
												<br/><br/>
												<ul id="tray_chose"></ul>
										</div>
									</section>
									<hr>
								</form>
								<footer style="text-align: center; margin-bottom: 20px;">
									<button type="button" class="btn btn-primary" onclick="receiveDevices();">领取</button>
									<button type="button" class="btn btn-primary" onclick="findSteCon();">取回个人物品</button>
								</footer>
								<br/>
							</div>
						</div>
					</div>
				</div>
				<fieldset>
			</div>
	</div>
	<div class="pageUpDown">
		<button class="btn btn-primary" onclick="pageUp()"><i class="fa fa-arrow-up"> </i></button><br>
		<button class="btn btn-primary" onclick="pageDown()"><i class="fa fa-arrow-down"> </i></button><br>
	</div>
</body>

<script type="text/javascript">
	var idArr = [], aa = [];
	var lockerNumber = [],size= [],number = [],state=[];
	$('.war').hover(function() {
		$(this).addClass("focused");
	}, function() {
		$(this).removeClass("focused");
	});
	$('.war').click(
			function(e) {

				var t = $(e.target);
				if (e.target.tagName != 'DIV')
					t = t.closest('.war');
				if (idArr.length == 1 && idArr[0] != t.attr('data-id')) {
					return;
				}
				if (t.hasClass("selected"))
					t.removeClass("selected");
				else
					t.addClass("selected");
				var seats = $(".seatCharts-cell>.seatCharts-cell").find('.selected');
				idArr = [];
				lockerNumber = [];
				number = [];
				size = []
				seats.each(function() {
					idArr.push($(this).attr('data-id'));
					lockerNumber.push($(this).attr('data-number'));
					number.push($(this).attr('data-steNumber'));
					size.push($(this).attr('data-size'));
					state.push($(this).attr('data-state'));
				});
				if (idArr.length == 1) {
					aa = [];
					t.parent().parent().siblings().find('.seatCharts-cell>div')
							.each(function() {
								if ($(this).hasClass('war'))
									aa.push($(this).attr('data-id'));
								$(this).addClass('unavailable');
							})
				}
				if (idArr.length == 0) {
					var siblingNode = t.parent().parent().siblings().find('.seatCharts-cell');
					for (var i = 0; i < aa.length; i++) {
						siblingNode.find('[data-id=' + aa[i] + ']')
								.removeClass('unavailable');
					}
				}
				$("#tray_chose").empty();
				for (var i = 0; i < idArr.length; i++) {
					if (idArr[i] != "" && typeof (idArr[i]) != "undefined") {
						$('<li>' + lockerNumber[i] + '</li>').appendTo($("#tray_chose"));
					}
				}
				
			});

	function receiveDevices() {
		/* if (lockerNumber.length <= 0) {
			alertSwal.warningTitle("请选择消毒鞋柜！");
			return false;
		}
		if(state[0]==0){
			alertSwal.warningTitle("该柜内没有可领取的手术鞋！");
			return false;
		} 
		var locker = lockerNumber[0];
		var num = number[0];
		var shoesSize = size[0];*/
		var url = "${pageContext.request.contextPath}/touch/randomReceiveShoeByUserIdTheNumber";
		// 	var id=$("#userId").val();
		var id = 2;
		var theNumber = "SSS-001";
		var param = {
			userId : id,
			theNumber:theNumber
		};
		zfesAjax.ajaxTodo(url, param, function(data) {
			var ope = data.data.operation;
			var steNumber = data.data.steNumber;
			var lockerNumber = data.data.lockerNumber;
			alertSwal.successText("手术鞋在大柜编号"+steNumber+"的小柜：" + lockerNumber + "号柜内");
		})
	}
	
	function findSteCon(){
		var id = 2;
		var number = "SSS-001";
		var url="${pageContext.request.contextPath}/touch/findSteConByUserIdAndThNumber";
		var param={id:id,number:number}
		zfesAjax.ajaxTodo(url, param, function(data) {
			var ope = data.data.operation;
			var steNum = data.data.number;
			alertSwal.successText("请在" + steNum + "号消毒鞋柜取回");
			
			window.location.href="${pageContext.request.contextPath}/touch/loadSterilizerByThNumber?number=2"
		})
	}
	
	function goBack(){
	    window.history.go(-1);
	}
	function goIndex(){
		window.location.href="${pageContext.request.contextPath}/touch/enterTouchScreen"
	}
	
	function pageUp(){
		$('html body').scrollTop($('html body').scrollTop()-500);
	}
	function pageDown(){
		$('html body').scrollTop($('html body').scrollTop()+500);
	}
</script>
</html>
