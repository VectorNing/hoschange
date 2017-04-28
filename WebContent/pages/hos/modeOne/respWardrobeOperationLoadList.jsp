<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/libs/css/touch2.css">
<script type="text/javascript" src="js/dict.js"></script>
<script type="text/javascript" src="js/choose-seat.js"></script>	
<div class="modal-body no-padding">
	<fieldset>
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-10">
				<c:forEach items="${rooms}" var="r" varStatus="vs">
				<input type="hidden" value="${r.warNumber}"  id="warNumber_number"/>
					<div class="seatCharts-cell" data-id="${r.warNumber }" style="margin-left: 202px;">
						<div class="seatCharts-cell seatCharts-seat">
							
							<div class="steTitle" >
								<p>
									<span>手术衣柜</span>
									<b style="color: black">${r.warNumber}</b>
									
								</p>
								  
							</div>
							
							<div data-id="1" data-state="0" data-col="${r.columns}" data-row="${r.rows}" class="seatCharts-cell seatCharts-seat available">
								<c:forEach items="${r.HosWardrobeContainerList}" var="co" varStatus="sa">
									<div class="seatCharts-cell seatCharts-seat cell-con" data-id="${co.id}" data-size="${co.opeSize}" data-max="${co.trayTotal-co.alloutCount}" >
										<div class="cell">
											<p class="trayNumber">${co.trayNumber}</p>
											
											<p class="size"><%-- <c:if test="${co.opeSize == ''}">为空</c:if> --%><c:if test="${co.opeSize !=null}"><sx:dataDic type="cloth" value="${co.opeSize}"/></c:if></p>
											<p class="count">${co.alloutCount} / ${co.trayTotal}</p>
										</div>
										<div data-id="${co.id}"  data-number="${co.trayNumber}" class="seatCharts-cell-con seatCharts-seat-con available war">
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>	
			
		</div>
		</div>
		</fieldset>
</div>
<script type="text/javascript">
$(function(){
	ZVue.init();
	$('.seatCharts-cell.seatCharts-seat.cell-con').click(function(){
		$(this).toggleClass('selected');
		
		if($('.seatCharts-cell.seatCharts-seat.cell-con').filter(':not(.selected)').length>0)
			$('button.selectAll').html('全选');
		else
			$('button.selectAll').html('全部取消');
	});
	
	$('button.selectAll').click(function(){
		if($(this).html() == '全选'){
			$('.seatCharts-cell.seatCharts-seat.cell-con').filter(':not(.selected)').click();			
			$(this).html('全部取消');
		}else{
			$('.seatCharts-cell.seatCharts-seat.cell-con').filter('.selected').click();			
			$(this).html('全选'); 
		}
	})
	
})

</script>
