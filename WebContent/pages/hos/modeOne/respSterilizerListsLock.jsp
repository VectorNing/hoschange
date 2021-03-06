<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/libs/css/touch5.css">
<script type="text/javascript" src="js/dict.js"></script>
<script type="text/javascript" src="js/seatLock.js"></script>

<!-- <script type="text/javascript" src="js/choose-seat.js"></script>	 -->
<style>
.select {
	background-color: #ff6699;
}

.colSelector {
	top: 0;
	left: 35px;
}

.colSelector>input {
	margin-right: 35px;
}

.intro {
	margin-top: 40px;
}

.selectAll.btn.btn-success {
	margin-top: 1px;
	height: 30px;
}
.seatCharts-cell.seatCharts-seat.available{
	position: relative;
	margin:20px -94px 0px -55px\0;
}
</style>
<div class="modal-body no-padding">
	<fieldset>
		<div class="row">
			<div class="col-md-12">
				<div style="height: 90%; overflow: auto;">
					<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12">
						<c:forEach items="${rooms}" var="r" varStatus="vs">
							<input type="hidden" value="${r.steNumber}" id="steNumber_number" />
							<div class="seatCharts-cell" data-id="${r.steNumber }"
								style="height: 554px;">
								<div class="seatCharts-cell seatCharts-seat">
									<div class="steTitle" style="position: relative;">
										<button class="selectAll btn btn-success"
											data-id="${r.steNumber}" state="0">全部选中</button>
										<p>鞋柜</p>
										<b style="color: black">${r.steNumber}</b>
									</div>

									<div data-id="1" data-state="0" data-col="${r.columns}"
										data-row="${r.rows}"
										class="seatCharts-cell seatCharts-seat available">
										<%-- <div class="colSelector" data-id="${r.steNumber}">
											<input id="1" class="input_checkbox" type="checkbox"
												value="1" /> <input id="2" class="input_checkbox"
												type="checkbox" value="2" /> <input id="3"
												class="input_checkbox" type="checkbox" value="3" />  <input
												id="0" class="input_checkbox" type="checkbox" value="0" />

										</div>	 --%>	
										<div class="colSelector" data-id="${r.steNumber}"></div>
										<div class="rowSelector" data-id="${r.steNumber}"></div>							
										<c:forEach items="${r.containerList}" var="co" varStatus="sa">
											<!-- 小柜为空的状态 -->
											<c:if test="${co.state==0}">
												<div data-id="${co.id}" data-state="${co.state}"
													data-number="${co.lockerNumber}"
													data-steNumber="${r.steNumber}"
													data-yesOrNoLock="${co.yesOrNoLock }"
													data-row="${ Math.ceil((sa.count)/(r.columns))}"
													data-col="${(sa.count)%(r.columns )}"
													data-size="${co.shoesSize}"
													class="seatCharts-cell seatCharts-seat cell-con">
													<div class="cell">
														<p class="number">${co.lockerNumber}</p>
														<p class="size">
															<c:choose>
																<c:when test="${co.yesOrNoBinding == '1'}">
																	<p style="color: red;">${co.userName}</p>
																</c:when>
																<c:otherwise>
																	<c:if test="${co.shoesSize == ''}"></c:if>
																	<c:if test="${co.shoesSize !=null}">
																		<sx:dataDic type="shoes" value="${co.shoesSize}" />
																	</c:if>
																</c:otherwise>
															</c:choose>
														</p>
														<%-- <p>状态:<c:if test="${co.state == 0}">无</c:if><c:if test="${co.state == 1}">有</c:if></p> --%>
													</div>
													<div
														class="seatCharts-cell-con seatCharts-seat-con available war">
													</div>
												</div>
											</c:if>
											<!-- 小柜有鞋子状态 -->
											<c:if test="${co.state ==1}">
												<div data-id="${co.id}" data-state="${co.state}"
													data-number="${co.lockerNumber}"
													data-steNumber="${r.steNumber}"
													data-yesOrNoLock="${co.yesOrNoLock }"
													data-row="${ Math.ceil((sa.count)/(r.columns))}"
													data-col="${(sa.count)%(r.columns )}"
													data-size="${co.shoesSize}"
													class="seatCharts-cell seatCharts-seat cell-con">
													<div class="cell">
														<p class="number">${co.lockerNumber}</p>
														<p class="size">
															<c:choose>
																<c:when test="${co.yesOrNoBinding == '1'}">
																	<p style="color: red;">${co.userName}</p>
																</c:when>
																<c:otherwise>
																	<c:if test="${co.shoesSize == ''}"></c:if>
																	<c:if test="${co.shoesSize !=null}">
																		<sx:dataDic type="shoes" value="${co.shoesSize}" />
																	</c:if>
																</c:otherwise>
															</c:choose>
														</p>
														<%-- <p>状态:<c:if test="${co.state == 0}">无</c:if><c:if test="${co.state == 1}">有</c:if></p> --%>
													</div>
													<div
														class="seatCharts-cell-con seatCharts-seat-con unavailable ">
													</div>
												</div>
											</c:if>
											<!-- 存放使用人的鞋子状态 -->
											<c:if test="${co.state ==2}">
												<div data-id="${co.id}" data-state="${co.state}"
													data-size="${co.shoesSize}"
													data-number="${co.lockerNumber}"
													data-yesOrNoLock="${co.yesOrNoLock }"
													data-steNumber="${r.steNumber}"
													data-row="${ Math.ceil((sa.count)/(r.columns))}"
													data-col="${(sa.count)%(r.columns )}"
													class="seatCharts-cell seatCharts-seat cell-con">
													<div class="cell">
														<p class="number">${co.lockerNumber}</p>
														<p class="size">
															<c:choose>
																<c:when test="${co.yesOrNoBinding == '1'}">
																	<p style="color: red;">${co.userName}</p>
																</c:when>
																<c:otherwise>
																	<c:if test="${co.shoesSize == ''}"></c:if>
																	<c:if test="${co.shoesSize !=null}">
																		<sx:dataDic type="shoes" value="${co.shoesSize}" />
																	</c:if>
																</c:otherwise>
															</c:choose>
														</p>
														<%-- <p>状态:<c:if test="${co.state == 0}">无</c:if><c:if test="${co.state == 1}">有</c:if></p> --%>
													</div>
													<div
														class="seatCharts-cell-con seatCharts-seat-con unavailable ">
													</div>
												</div>
											</c:if>
											<c:if test="${(sa.count)%(r.columns)==0}">
												<br style="line-height:80px"/>
											</c:if>
										</c:forEach>
									</div>
								</div>
							</div>
						</c:forEach>
						<div class="intro">
							<div data-id="52" data-state="0" data-yesOrNoLock="0"
								data-row="1.0" data-col="1" data-size="1"
								class="seatCharts-cell seatCharts-seat cell-con"
								style="margin-left: 20px;">
								<div class="cell">
									<p class="size"></p>

								</div>
								<div
									class="seatCharts-cell-con seatCharts-seat-con available war">
								</div>
							</div>
							<p style="margin-left: 26px; margin-top: -26px;">空柜</p>
							<br />

							<div data-id="52" data-state="1" data-yesOrNoLock="0"
								data-row="1.0" data-col="1" data-size="1"
								class="seatCharts-cell seatCharts-seat cell-con"
								style="float: right; margin-right: 185px; margin-top: -213px;">
								<div class="cell">
									<p class="size"></p>

								</div>
								<div
									class="seatCharts-cell-con seatCharts-seat-con available war">
								</div>
							</div>
							<p style="float: right; margin-right: 194px; margin-top: -169px;">可用</p>
							<br />
							<!-- data-number="1" data-stenumber="xxx" -->
							<div data-id="52" data-state="2" data-yesOrNoLock="0"
								data-row="1.0" data-col="1" data-size="1"
								class="seatCharts-cell seatCharts-seat cell-con"
								style="float: right; margin-right: 95px; margin-top: -293px;">
								<div class="cell">
									<p class="size"></p>

								</div>
								<div
									class="seatCharts-cell-con seatCharts-seat-con available war">
								</div>
							</div>
							<p style="float: right; margin-right: 98px; margin-top: -249px;">使用中</p>
							<div data-id="52" data-state="0" data-yesOrNoLock="1"
								data-row="1.0" data-col="1" data-size="1"
								class="seatCharts-cell seatCharts-seat cell-con"
								style="float: right; margin-right: 16px; margin-top: -293px;">
								<div class="cell">
									<p class="size"></p>

								</div>
								<div
									class="seatCharts-cell-con seatCharts-seat-con available war">
								</div>
							</div>
							<p style="float: right; margin-right: 18px; margin-top: -249px;">锁定中</p>
						</div>
					</div>
					<%-- <div class="col-md-6 col-xs-6 col-sm-6 col-lg-6" style="margin-left: -83px;margin-top: 40px;">

					<section id="widget-grid">
										<form id="searchForm1" role="form" class="form-inline">
					
					
		                    <div class="form-group">
		                         <label>姓名</label>
		                         <input name="userName" type="text" placeholder="" class="form-control">
		                    </div>
		                     <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
		                </form>
		                <hr>
						<div class="row">
							<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-editbutton="false" style="border-top: 1px solid #cccccc;"> 
									 <div>
										 <div class="widget-body "> 
												<table id="ahthUserBootstrapTable" data-mobile-responsive="true" ></table>
											</div> 
									</div>
									</div>
							</article>
						</div>
					</section>
			</div>
		</div> --%>
				</div>

			</div>
			</div>
	</fieldset>
</div>

<script type="text/javascript">
	var idArr = [];
	var number = [];

	$('.war').hover(function() {
		$(this).addClass("focused");
	}, function() {
		$(this).removeClass("focused");
	});
	//huoqushujubiaoge
	var jqTableDom1 = $("#ahthUserBootstrapTable");
	function initData() {
		var jqFormDom1 = $("#searchForm1");
		//var jqToolbarDom1=$("#authUserTableToolbar");
		var jqToolbarDom1 = null;
		var url = "authUser/queryAuthUserSet";
		var columns = [ {
			checkbox : true
		}, {
			field : 'userName',
			title : '姓名',
			width : 160
		}, {
			field : 'loginName',
			title : '用户名',
			width : 160
		}, {
			field : 'telephone',
			title : '联系方式',
			width : 200
		}, ];
		bstable = zfesBstable.laodTable(jqTableDom1, jqFormDom1, jqToolbarDom1,
				url, columns);
	}
	function attachModalHidden() {
		$("#userModal").on('hide.bs.modal', function() {
			zfesBstable.refresh(jqTableDom1);
		});
	}
	$(function() {
		initData();
		attachModalHidden();
	});

	function search() {
		zfesBstable.reload(jqTableDom1);
	}
	var idArr = [], aa = [];
	var lockerNumber = [], size = [], number = [], state = [];
	$('.war').hover(function() {
		$(this).addClass("focused");
	}, function() {
		$(this).removeClass("focused");
	});

	
	//选中的是否没有使用中的柜子  锁定小柜调用   有使用中的小柜时返回false 没有返回true
	function isOnUse(boxnum) {
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con.selected[data-stenumber='
				+ boxnum + ']');
		for (var i = 0; i < selectCells.length; i++) {
			if ($(selectCells[i]).attr("data-state") == 2) {
				/* swal("无法锁定使用中的小柜","请先取消使用中小柜的选中状态","warning"); */
				return false;
			}
		}
		return true;
	}
	//选中的小柜是否都未锁定
	function isNoLock(boxnum) {
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con.selected[data-stenumber='
				+ boxnum + ']');
		for (var i = 0; i < selectCells.length; i++) {
			if ($(selectCells[i]).attr("data-yesOrNoLock") == 1) {
				/* swal("无法锁定使用中的小柜","请先取消使用中小柜的选中状态","warning"); */
				return false;
			}
		}
		return true;
	}
	//选中的小柜是否有鞋子
	function isOnHave(boxnum) {
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con.selected[data-stenumber='
				+ boxnum + ']');
		for (var i = 0; i < selectCells.length; i++) {
			if ($(selectCells[i]).attr("data-state") != 1) {
				/* swal("无法锁定使用中的小柜","请先取消使用中小柜的选中状态","warning"); */
				return false;
			}
		}
		return true;
	}
	//选中的是否全为锁定中的小柜    取消锁定调用   是的话返回true 不是返回false
	function isAllLocked(boxnum) {
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con.selected[data-stenumber='
				+ boxnum + ']');
		for (var i = 0; i < selectCells.length; i++) {
			if ($(selectCells[i]).attr("data-yesOrNoLock") == 0) {
				/* swal("无法解锁未锁定的小柜","请取消非锁定小柜的选中状态","warning"); */
				return false;
			}
		}
		return true;
	}
</script>
