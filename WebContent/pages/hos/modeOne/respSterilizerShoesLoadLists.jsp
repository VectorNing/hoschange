<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/libs/css/touch.css">
<script type="text/javascript" src="js/dict.js"></script>
<script type="text/javascript" src="js/choose-seat.js"></script>	
<style>
</style>
	<div class="modal-body no-padding">
	<fieldset>
	<div class="row">
		<div class="col-md-12">
			<div style="height: 90%; overflow: auto;">
			<div class="col-md-8">
				<c:forEach items="${rooms}" var="r" varStatus="vs">
					<input type="hidden" value="${r.steNumber}"  id="steNumber_number"/>
					<div class="seatCharts-cell" data-id="${r.steNumber }">
						<div class="seatCharts-cell seatCharts-seat">
							<div class="steTitle" style="position:relative;">
<%-- 								<button class="selectAll btn btn-success" data-id="${r.steNumber}" state="0">全选</button> --%>
								<p>鞋柜</p>
								<b style="color: black">${r.steNumber}</b>  
							</div>
							
							<div data-id="1" data-state="0" data-col="${r.columns}" data-row="${r.rows}" class="seatCharts-cell seatCharts-seat available">
								<c:forEach items="${r.containerList}" var="co" varStatus="sa">
										<!-- 小柜为空的状态 -->
										<c:if test="${co.state==0}">
											<div data-id="${co.id}" data-state="${co.state}" data-number="${co.lockerNumber}" data-steNumber="${r.steNumber}"  data-row="${ Math.ceil((sa.count)/(r.columns))}" data-col="${(sa.count)%(r.columns )}"  data-size="${co.shoesSize}" class="seatCharts-cell seatCharts-seat cell-con">
												<div class="cell">
													<p class="number">${co.lockerNumber}</p>
													<p class="size"><c:if test="${co.shoesSize == ''}"></c:if><c:if test="${co.shoesSize !=null}"><sx:dataDic type="shoes" value="${co.shoesSize}"/></c:if></p>
													<%-- <p>状态:<c:if test="${co.state == 0}">无</c:if><c:if test="${co.state == 1}">有</c:if></p> --%>
												</div>
												<div class="seatCharts-cell-con seatCharts-seat-con available war">
												</div>
											</div>
										</c:if>	
										<!-- 小柜有鞋子状态 -->
										<c:if test="${co.state ==1}">
											<div data-id="${co.id}" data-state="${co.state}"  data-number="${co.lockerNumber}" data-steNumber="${r.steNumber}"  data-row="${ Math.ceil((sa.count)/(r.columns))}" data-col="${(sa.count)%(r.columns )}"  data-size="${co.shoesSize}" class="seatCharts-cell seatCharts-seat cell-con">
												<div class="cell">
													<p class="number">${co.lockerNumber}</p>
													<p class="size"><c:if test="${co.shoesSize == ''}"></c:if><c:if test="${co.shoesSize !=null}"><sx:dataDic type="shoes" value="${co.shoesSize}"/></c:if></p>
													<%-- <p>状态:<c:if test="${co.state == 0}">无</c:if><c:if test="${co.state == 1}">有</c:if></p> --%>
												</div>
												<div class="seatCharts-cell-con seatCharts-seat-con unavailable ">
												</div>
											</div>
										</c:if>
										<!-- 存放使用人的鞋子状态 -->
										<c:if test="${co.state ==2}">
											<div data-id="${co.id}" data-state="${co.state}" data-size="${co.shoesSize}" data-number="${co.lockerNumber}" data-steNumber="${r.steNumber}"  data-row="${ Math.ceil((sa.count)/(r.columns))}" data-col="${(sa.count)%(r.columns )}"   class="seatCharts-cell seatCharts-seat cell-con">
												<div class="cell">
													<p class="number">${co.lockerNumber}</p>
													<p class="size"><c:if test="${co.shoesSize == ''}"></c:if><c:if test="${co.shoesSize !=null}"><sx:dataDic type="shoes" value="${co.shoesSize}"/></c:if></p>
													<%-- <p>状态:<c:if test="${co.state == 0}">无</c:if><c:if test="${co.state == 1}">有</c:if></p> --%>
												</div>
												<div class="seatCharts-cell-con seatCharts-seat-con unavailable ">
												</div>
											</div>
										</c:if>
										<c:if test="${(sa.count)%(r.columns)==0}">
											<br/>
										</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>					
				
					
			<div class="col-md-4">
					<div  class="booking_area" style="margin-top: 50px;">
						
						<div class="intro">
							<div data-id="52" data-state="0" data-number="1" data-stenumber="xxx" data-row="1.0" data-col="1" data-size="1" class="seatCharts-cell seatCharts-seat cell-con">
								<div class="cell">
									<p class="size"></p>
									
								</div>
								<div class="seatCharts-cell-con seatCharts-seat-con available war">
								</div>
							</div>
							未使用<br />
							
							<div data-id="52" data-state="1" data-number="1" data-stenumber="xxx" data-row="1.0" data-col="1" data-size="1" class="seatCharts-cell seatCharts-seat cell-con">
								<div class="cell">
									<p class="size"></p>
									
								</div>
								<div class="seatCharts-cell-con seatCharts-seat-con available war">
								</div>
							</div>
							已使用<br />
							
							<div data-id="52" data-state="2" data-number="1" data-stenumber="xxx" data-row="1.0" data-col="1" data-size="1" class="seatCharts-cell seatCharts-seat cell-con">
								<div class="cell">
									<p class="size"></p>
									
								</div>
								<div class="seatCharts-cell-con seatCharts-seat-con available war">
								</div>
							</div>
							存放个人鞋子
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
