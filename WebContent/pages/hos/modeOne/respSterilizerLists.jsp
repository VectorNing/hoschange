<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/libs/css/touch6.css">
<script type="text/javascript" src="js/dict.js"></script>
<script type="text/javascript" src="js/seat.js"></script>
<style>
.seatCharts-cell.seatCharts-seat.available{
	position: relative;
	margin:20px -65px 0px -55px\0;
}
</style>
<div class="modal-body no-padding">
	<fieldset>
		<div class="row">
			<div class="col-md-12">
				<div class="row" style="height: 90%; overflow: auto;">
					<div class="col-md-8 col-sm-8 col-xs-8">
						<c:forEach items="${rooms}" var="r" varStatus="vs">
							<input type="hidden" value="${r.steNumber}" id="steNumber_number" />
							<div class="seatCharts-cell" data-id="${r.steNumber }">
								<div class="seatCharts-cell seatCharts-seat">
									<div class="steTitle" style="position: relative;">
										<button class="selectAll btn btn-success"
											data-id="${r.steNumber}" state="0">全选</button>
										<p>鞋柜</p>
										<b style="color: black">${r.steNumber}</b>
									</div>

									<div data-id="1" data-state="0" data-col="${r.columns}"
										data-row="${r.rows}"
										class="seatCharts-cell seatCharts-seat available">
										<div class="colSelector" data-id="${r.steNumber}"></div>
										<div class="rowSelector" data-id="${r.steNumber}"></div>
										<c:forEach items="${r.containerList}" var="co" varStatus="sa">
											<!-- 小柜为空的状态 -->
											<c:if test="${co.state==0}">
												<div data-id="${co.id}"
													data-yesOrNoLock="${co.yesOrNoLock }"
													data-state="${co.state}" data-number="${co.lockerNumber}"
													data-steNumber="${r.steNumber}"
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
												<div data-id="${co.id}"
													data-yesOrNoLock="${co.yesOrNoLock }"
													data-state="${co.state}" data-number="${co.lockerNumber}"
													data-steNumber="${r.steNumber}"
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
												<div data-id="${co.id}"
													data-yesOrNoLock="${co.yesOrNoLock }"
													data-state="${co.state}" data-size="${co.shoesSize}"
													data-number="${co.lockerNumber}"
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
					</div>


					<div class="col-md-4 col-sm-4 col-xs-4">
						<div class="booking_area">
							<div class="smart-form">
								<div>
									<label class="label">鞋子尺码:</label> <label class="select">
										<select name="shoesSize" id="shoesSize" style="width: 150px;">
											<c:forEach items="${ shoesList}" var="s">
												<option value="${s.shoeSize }">${s.shoeSizeName }</option>
											</c:forEach>
									</select>
									</label>
								</div>
								<div id="legend"
									style="text-align: center; padding: 10px; color: red;"></div>
							</div>

							<div class="intro">
								<div data-id="52" data-state="0" data-yesOrNoLock="0"
									data-row="1.0" data-col="1" data-size="1"
									class="seatCharts-cell seatCharts-seat cell-con">
									<div class="cell">
										<p class="size"></p>

									</div>
									<div
										class="seatCharts-cell-con seatCharts-seat-con available war">
									</div>
								</div>
								空柜<br />

								<div data-id="52" data-state="1" data-yesOrNoLock="0"
									data-row="1.0" data-col="1" data-size="1"
									class="seatCharts-cell seatCharts-seat cell-con">
									<div class="cell">
										<p class="size"></p>

									</div>
									<div
										class="seatCharts-cell-con seatCharts-seat-con available war">
									</div>
								</div>
								可用<br />
								<!-- data-number="1" data-stenumber="xxx" -->
								<div data-id="52" data-state="2" data-yesOrNoLock="0"
									data-row="1.0" data-col="1" data-size="1"
									class="seatCharts-cell seatCharts-seat cell-con">
									<div class="cell">
										<p class="size"></p>

									</div>
									<div
										class="seatCharts-cell-con seatCharts-seat-con available war">
									</div>
								</div>
								使用中<br />
								<div data-id="52" data-state="0" data-yesOrNoLock="1"
									data-row="1.0" data-col="1" data-size="1"
									class="seatCharts-cell seatCharts-seat cell-con">
									<div class="cell">
										<p class="size"></p>

									</div>
									<div
										class="seatCharts-cell-con seatCharts-seat-con available war">
									</div>
								</div>
								锁定中
							</div>
						</div>


					</div>

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
</script>
