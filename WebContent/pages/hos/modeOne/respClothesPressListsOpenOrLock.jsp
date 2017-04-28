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
<style>
.col-md-8>.seatCharts-cell {
	height: 500px;
	width: 600px;
	overflow: auto;
}

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
</style>

<div id="container_div">

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">开柜、锁柜</h4>
	</div>
	<div class="modal-body no-padding">
		<fieldset>
			<div class="row">
				<div class="col-md-12">
					<div style="height: 90%; overflow: auto;">
						<div class="col-md-6 col-xs-6 col-sm-6 col-lg-6">
							<c:forEach items="${rooms}" var="r" varStatus="vs">
								<input type="hidden" value="${r.steNumber}"
									id="steNumber_number" />
								<div class="seatCharts-cell" data-id="${r.steNumber }"
									style="height: 554px;">
									<div class="seatCharts-cell seatCharts-seat">
										<div class="steTitle" style="position: relative;">
											<%-- <button class="selectAll btn btn-success" data-id="${r.steNumber}" state="0">全选</button> --%>
											<p>储物柜</p>
											<b style="color: black">${r.steNumber}</b>
										</div>

										<div data-id="1" data-state="0" data-col="${r.columns}"
											data-row="${r.rows}"
											class="seatCharts-cell seatCharts-seat available">
											<%-- 								<div class="colSelector" data-id="${r.steNumber}">
									
								</div>
								<div class="rowSelector" data-id="${r.steNumber}">
									
								</div> --%>
											<c:forEach items="${r.hosClothesPress}" var="co"
												varStatus="sa">
												<!-- 小柜为空的状态 -->
												<c:if test="${co.state==0}">
													<div data-id="${co.id}" data-state="${co.state}"
														data-number="${co.doorNumber}"
														data-steNumber="${r.steNumber}"
														data-yesOrNoLock="${co.yesOrNoLock }"
														data-row="${ Math.ceil((sa.count)/(r.columns))}"
														data-col="${(sa.count)%(r.columns )}"
														data-size="${co.clothSize}"
														class="seatCharts-cell seatCharts-seat cell-con">
														<div class="cell">
															<p class="number">${co.doorNumber}</p>
															<p class="size">
																<c:choose>
																	<c:when test="${co.yesOrNoBinding == '1'}">
																		<p style="color: red;">${co.userName}</p>
																	</c:when>
																	<c:otherwise>
																		<c:if test="${co.clothSize == ''}"></c:if>
																		<c:if test="${co.clothSize !=null}">
																			<sx:dataDic type="cloth" value="${co.clothsSize}" />
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
												<!-- 小柜有物品状态 -->
												<c:if test="${co.state ==1}">
													<div data-id="${co.id}" data-state="${co.state}"
														data-number="${co.doorNumber}"
														data-steNumber="${r.steNumber}"
														data-yesOrNoLock="${co.yesOrNoLock }"
														data-row="${ Math.ceil((sa.count)/(r.columns))}"
														data-col="${(sa.count)%(r.columns )}"
														data-size="${co.clothSize}"
														class="seatCharts-cell seatCharts-seat cell-con">
														<div class="cell">
															<p class="number">${co.doorNumber}</p>
															<p class="size">
																<c:choose>
																	<c:when test="${co.yesOrNoBinding == '1'}">
																		<p style="color: red;">${co.userName}</p>
																	</c:when>
																	<c:otherwise>
																		<c:if test="${co.clothSize == ''}"></c:if>
																		<c:if test="${co.clothSize !=null}">
																			<sx:dataDic type="cloth" value="${co.clothsSize}" />
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
												<!-- 存放使用人的状态 -->
												<c:if test="${co.state ==2}">
													<div data-id="${co.id}" data-state="${co.state}"
														data-size="${co.clothSize}"
														data-number="${co.doorNumber}"
														data-yesOrNoLock="${co.yesOrNoLock }"
														data-steNumber="${r.steNumber}"
														data-row="${ Math.ceil((sa.count)/(r.columns))}"
														data-col="${(sa.count)%(r.columns )}"
														class="seatCharts-cell seatCharts-seat cell-con">
														<div class="cell">
															<p class="number">${co.doorNumber}</p>
															<p class="size">
																<c:choose>
																	<c:when test="${co.yesOrNoBinding == '1'}">
																		<p style="color: red;">${co.userName}</p>
																	</c:when>
																	<c:otherwise>
																		<c:if test="${co.clothSize == ''}"></c:if>
																		<c:if test="${co.clothSize !=null}">
																			<sx:dataDic type="cloth" value="${co.clothsSize}" />
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
													<br />
												</c:if>
											</c:forEach>
										</div>
									</div>
								</div>
							</c:forEach>							
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
								<p style="margin-left: 44px; margin-top: -26px;">空柜</p>
								<br />
								<div data-id="52" data-state="0" data-yesOrNoLock="1"
									data-row="1.0" data-col="1" data-size="1"
									class="seatCharts-cell seatCharts-seat cell-con"
									style="float: right; margin-right: 143px; margin-top: -213px;">
									<div class="cell">
										<p class="size"></p>

									</div>
									<div
										class="seatCharts-cell-con seatCharts-seat-con available war">
									</div>
								</div>
								<p style="float: right; margin-right: 148px; margin-top: -169px;">锁定中</p>
								<br />
								<!-- <div data-id="52" data-state="1" data-yesOrNoLock="0"
									data-row="1.0" data-col="1" data-size="1"
									class="seatCharts-cell seatCharts-seat cell-con"
									style="float: right; margin-right: 143px; margin-top: -213px;">
									<div class="cell">
										<p class="size"></p>

									</div>
									<div
										class="seatCharts-cell-con seatCharts-seat-con available war">
									</div>
								</div>
								<p
									style="float: right; margin-right: 148px; margin-top: -169px;">已分配</p> -->
								<br />
								<!-- data-number="1" data-stenumber="xxx" -->
								<div data-id="52" data-state="2" data-yesOrNoLock="0"
									data-row="1.0" data-col="1" data-size="1"
									class="seatCharts-cell seatCharts-seat cell-con"
									style="float: right; margin-right: 40px; margin-top: -373px;">
									<div class="cell">
										<p class="size"></p>

									</div>
									<div
										class="seatCharts-cell-con seatCharts-seat-con available war">
									</div>
								</div>
								<p style="float: right; margin-right: 46px; margin-top: -329px;">使用中</p>
							</div>
					</div>
				</div>

			</div>
		</fieldset>
	</div>
	<%-- <div class="modal-body no-padding">
		<fieldset>
			<div class="row">
				<div class="col-md-12">
					<div style="height: 90%; overflow: auto;">
						<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12">
							<c:forEach items="${rooms}" var="r" varStatus="vs">
								<input type="hidden" value="${r.steNumber}"
									id="steNumber_number" />
								<div class="seatCharts-cell" data-id="${r.steNumber }"
									style="height: 554px;">
									<div class="seatCharts-cell seatCharts-seat">
										<div class="steTitle" style="position: relative;">
											<button class="selectAll btn btn-success"
												data-id="${r.steNumber}" state="0">全部选中</button>
											<p>衣柜</p>
											<b style="color: black">${r.steNumber}</b>
										</div>

										<div data-id="1" data-state="0" data-col="${r.columns}"
											data-row="${r.rows}"
											class="seatCharts-cell seatCharts-seat available">
											<div class="colSelector" data-id="${r.steNumber}">
												<input id="1" class="input_checkbox" type="checkbox"
													value="1" /> <input id="2" class="input_checkbox"
													type="checkbox" value="2" /> <input id="3"
													class="input_checkbox" type="checkbox" value="3" /> <input
													id="4" class="input_checkbox" type="checkbox" value="4" />
												<input id="0" class="input_checkbox" type="checkbox"
													value="0" />

											</div>
											<c:forEach items="${r.hosClothesPress}" var="co"
												varStatus="sa">
												<!-- 小柜为空的状态 -->
												<c:if test="${co.state==0}">
													<div data-id="${co.id}" data-state="${co.state}"
														data-number="${co.doorNumber}"
														data-steNumber="${r.steNumber}"
														data-yesOrNoLock="${co.yesOrNoLock }"
														data-row="${ Math.ceil((sa.count)/(r.columns))}"
														data-col="${(sa.count)%(r.columns )}"
														data-size="${co.clothSize}"
														class="seatCharts-cell seatCharts-seat cell-con">
														<div class="cell">
															<p class="number">${co.doorNumber}</p>
															<p class="size">
																<c:choose>
																	<c:when test="${co.yesOrNoBinding == '1'}">
																		<p style="color: red;">${co.userName}</p>
																	</c:when>
																	<c:otherwise>
																		<c:if test="${co.clothSize == ''}"></c:if>
																		<c:if test="${co.clothSize !=null}">
																			<sx:dataDic type="cloth" value="${co.clothSize}" />
																		</c:if>
																	</c:otherwise>
																</c:choose>
															</p>
															<p>状态:<c:if test="${co.state == 0}">无</c:if><c:if test="${co.state == 1}">有</c:if></p>
														</div>
														<div
															class="seatCharts-cell-con seatCharts-seat-con available war">
														</div>
													</div>
												</c:if>
												<!-- 小柜有物品状态 -->
												<c:if test="${co.state ==1}">
													<div data-id="${co.id}" data-state="${co.state}"
														data-number="${co.doorNumber}"
														data-steNumber="${r.steNumber}"
														data-yesOrNoLock="${co.yesOrNoLock }"
														data-row="${ Math.ceil((sa.count)/(r.columns))}"
														data-col="${(sa.count)%(r.columns )}"
														data-size="${co.clothSize}"
														class="seatCharts-cell seatCharts-seat cell-con">
														<div class="cell">
															<p class="number">${co.doorNumber}</p>
															<p class="size">
																<c:if test="${co.clothSize == ''}"></c:if>
																<c:if test="${co.clothSize !=null}">
																	<sx:dataDic type="cloth" value="${co.clothSize}" />
																</c:if>
															</p>
															<p>状态:<c:if test="${co.state == 0}">无</c:if><c:if test="${co.state == 1}">有</c:if></p>
														</div>
														<div
															class="seatCharts-cell-con seatCharts-seat-con unavailable ">
														</div>
													</div>
												</c:if>
												<!-- 存放使用人的状态 -->
												<c:if test="${co.state ==2}">
													<div data-id="${co.id}" data-state="${co.state}"
														data-size="${co.shoesSize}"
														data-number="${co.doorNumber}"
														data-yesOrNoLock="${co.yesOrNoLock }"
														data-steNumber="${r.steNumber}"
														data-row="${ Math.ceil((sa.count)/(r.columns))}"
														data-col="${(sa.count)%(r.columns )}"
														class="seatCharts-cell seatCharts-seat cell-con">
														<div class="cell">
															<p class="number">${co.doorNumber}</p>
															<p class="size">
																<c:if test="${co.clothSize == ''}"></c:if>
																<c:if test="${co.clothSize !=null}">
																	<sx:dataDic type="cloth" value="${co.clothSize}" />
																</c:if>
															</p>
															<p>状态:<c:if test="${co.state == 0}">无</c:if><c:if test="${co.state == 1}">有</c:if></p>
														</div>
														<div
															class="seatCharts-cell-con seatCharts-seat-con unavailable ">
														</div>
													</div>
												</c:if>
												<c:if test="${(sa.count)%(r.columns)==0}">
													<br />
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

								 <!-- <div data-id="52" data-state="1" data-yesOrNoLock="0"
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
								<p
									style="float: right; margin-right: 188px; margin-top: -169px;">已分配</p>
								<br />  -->
								<!-- data-number="1" data-stenumber="xxx" -->
								<div data-id="52" data-state="2" data-yesOrNoLock="0"
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
								<p style="float: right; margin-right: 188px; margin-top: -169px;">使用中</p>
								<div data-id="52" data-state="0" data-yesOrNoLock="1"
									data-row="1.0" data-col="1" data-size="1"
									class="seatCharts-cell seatCharts-seat cell-con"
									style="float: right; margin-right: 95px; margin-top: -213px;">
									<div class="cell">
										<p class="size"></p>

									</div>
									<div
										class="seatCharts-cell-con seatCharts-seat-con available war">
									</div>
								</div>
								<p style="float: right; margin-right: 98px; margin-top: -169px;">锁定中</p>
							</div>
						</div>
					</div>

				</div>
		</fieldset>
	</div> --%>
	<hr>
	<footer style="text-align: center;">
		<button type="button" id="openBtn" class="btn btn-primary"
			onclick="openClothesPress();">打开小柜</button>
		<button type="button" id="lockBtn" class="btn btn-primary"
			onclick="lock();">锁定小柜</button>
		<button type="button" id="lockAllBtn" class="btn btn-primary"
			onclick="stopLock();">取消锁定</button>
	</footer>
	<hr>
</div>

<input type="hidden" value="${rooms[0].steNumber}" id="steNumber_input" />
<input type="hidden" value="${rooms[0].rows}" id="rows_input" />
<input type="hidden" value="${rooms[0].columns}" id="columns_input" />
<input type="hidden" value="${rooms[0].total}" id="total_input" />
<script type="text/javascript">
	function getSelectIds() {
		var ids = [];
		$('.seatCharts-cell.seatCharts-seat.cell-con.selected').each(
				function() {
					ids.push($(this).attr('data-id'));
				})
		return ids;
	}
	function getSelectNumbers(){
		var numbers = [];
		$('.seatCharts-cell.seatCharts-seat.cell-con.selected').each(
		function(){
			numbers.push($(this).attr('data-number'));
		});
		return numbers;
	}
	function loadClothPress() {
		var steNumber = $("#steNumber_input").val();//储物柜编号
		var total = $("#total_input").val();
		var url = "hosClothesPress/enterOpenOrLockClothesPress?steNumber="
				+ steNumber + "&total=" + total;
		$("#container_div").load(url);
	}

	function openClothesPress() {
		if(isNoLocked($("#steNumber_input").val())){
			var numberss = getSelectNumbers();
			var numbers = numberss.join(",");
			var deviceNumber = $("#steNumber_input").val();
					var ajaxUrl = "${pageContext.request.contextPath}/hosClothesPress/openContainerClothesPressLog";
					var param = {"deviceNumber":deviceNumber,"numbers":numbers};
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						alertSwal.successTitle(data.message)
					});
		}else{
			alertSwal.warning("锁定中","请先取消锁定中小柜的选中状态后重试");
		}
		

	}
	function lock() {
		if(isOnUse($("#steNumber_input").val())){
					var idArr = getSelectIds();
					var ids = idArr.join(",");
					var ajaxUrl = "hosClothesPress/lockClothesPress";
					var param = {
						"ids" : ids
					};
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						alertSwal.successText(data.message);
						loadClothPress();
					});
		}else{
			alertSwal.warning("使用中","请先取消使用中小柜的选中状态后重试");
		}
		
	}
	function stopLock() {
		if(isAllLocked($("#steNumber_input").val())){
					var idArr = getSelectIds();
					var ids = idArr.join(",");
					var ajaxUrl = "hosClothesPress/stoplockClothesPress";
					var param = {
						"ids" : ids
					};
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						alertSwal.successText(data.message);
						loadClothPress();
					});
		}else{
			alertSwal.warning("未锁定","请先取消未锁定小柜的选中状态后重试");
		}
	
	}
	$('.war').hover(function() {
		$(this).addClass("focused");
	}, function() {
		$(this).removeClass("focused");
	});

	//小柜点击选中事件  steNumber大柜编号
	$('.seatCharts-cell.seatCharts-seat.cell-con').click(function() {
		var cell = $(this);
		if (cell.parent().attr("class") != "intro") {
			cell.toggleClass('selected');
		}
		var boxid = $(this).attr('data-steNumber');
		var row = $(this).attr('data-row');
		var col = $(this).attr('data-col');
		updateColSelector(boxid, col, row);
	});
	//点击小鞋柜后调用方法，判断是否要给checkbox改变状态
	function updateColSelector(boxid, colnum, rownum) {
		if (isColAllSelected(boxid, colnum)) {
			setColCheckState(boxid, colnum, true);
		} else {
			setColCheckState(boxid, colnum, false);
		}
	}
	//col是否为全选  data-state  有鞋:1，没鞋:0，使用中:2 
	function isColAllSelected(boxnum, colnum) {
		var cellList = $('[data-stenumber=' + boxnum + '][data-col=' + colnum
				+ ']');
		var countcol = 0;
		var selcol = 0;
		for (var i = 0; i < cellList.length; i++) {
			countcol++;
			if ($(cellList[i]).hasClass('selected')) {
				selcol++;
			}
		}
		if (countcol == selcol) {
			return true;
		}
		return false;
	}
	//setColCheckState 设置checkbox的选中状态
	function setColCheckState(boxnum, colnum, state) {
		var checkbox = $('.seatCharts-cell[data-id=' + boxnum + ']').find(
				'.colSelector').find('[value=' + colnum + ']');
		checkbox.prop('checked', state);
		updateAllSelectBtn(boxnum);
	}

	//选中结果输出
	/* function getSelectCell(){
	 var result=[];
	 var list = $('.seatCharts-cell.selected');
	 list.each(function(){
	 var boxid = $(this).attr('data-stenumber');
	 var dataid = $(this).attr('data-id');
	 var size = $(this).attr('data-size')
	 result.push({boxid:boxid,id:dataid,size:size});
	 });
	 return result;	
	 } */
	//全选按钮点击事件
	var selectedType = -1;
	$('.selectAll')
			.click(
					function() {
						var btnState = $(this).attr('state');
						if (btnState == 0 && selectedType == -1) {
							selectedType = 0;
						}
						//全选中按钮是选中时，把colcheckbox全部选中 state 0 全部选中 1 取消全选
						var table = $(this).closest('.seatCharts-seat');
						var colCheckboxList = table.find('.colSelector').find(
								'input');
						for (var i = 0; i < colCheckboxList.length; i++) {
							if (btnState == 0) {
								if (!$(colCheckboxList[i]).is(':checked'))
									$(colCheckboxList[i]).click();
							} else {
								if (!$(colCheckboxList[i]).is(':checked'))
									$(colCheckboxList[i]).click().click();
								else
									$(colCheckboxList[i]).click();
							}
						}
						var boxnum = $(this).attr('data-id');
						updateAllSelectBtn(boxnum);
						if ($('.seatCharts-cell.seatCharts-seat.cell-con.selected').length <= 0) {
							selectedType = -1;
						}
					});
	//改变全选按钮
	function changeAllSelectState(boxnum, state) {
		var btn = $('.selectAll[data-id=' + boxnum + ']');
		btn.attr('state', state);
		if (state == 0)
			btn.html('全部选中');
		else
			btn.html('取消全选');
	}
	//涉及到可能导致全选按钮状态改变的方法中都调用这个
	function updateAllSelectBtn(boxnum) {
		if (isSelectAll(boxnum)) {
			changeAllSelectState(boxnum, 1);
		} else {
			changeAllSelectState(boxnum, 0);
		}
	}
	//如果有checkbox没有被选中就返回false全选按钮显示为不是全选状态
	function isSelectAll(boxnum) {
		var checkList = $('.seatCharts-cell[data-id=' + boxnum + ']').find(
				'.colSelector').find('input');
		for (var i = 0; i < checkList.length; i++) {
			if (!$(checkList[i]).is(':checked')
					&& !$(checkList[i]).attr("disabled"))
				return false;
		}
		return true;
	}
	//checkbox状态改变事件
	$('.colSelector>input')
			.change(
					function(e) {
						var boxnum = $(this).closest('.colSelector').attr(
								'data-id');
						var colnum = $(this).val();
						if ($(this).is(':checked')) {
							selectCol(boxnum, colnum);
							if (isSelectAll(boxnum)) {
								changeAllSelectState(boxnum, 1);
							}
						} else {
							unSelectCol(boxnum, colnum);
							changeAllSelectState(boxnum, 0);
							if ($('.seatCharts-cell.seatCharts-seat.cell-con[data-state=0]').length == 0)
								selectedType = -1;
						}
					});
	//colcheckbox打勾则选中该列所有柜
	function selectCol(boxnum, colnum) {
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con[data-stenumber='
				+ boxnum + '][data-col=' + colnum + ']');
		selectCells.addClass('selected');
	}
	//colcheckbox取消则取消选中该列所有柜
	function unSelectCol(boxnum, colnum) {
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con[data-stenumber='
				+ boxnum + '][data-col=' + colnum + ']');
		selectCells.removeClass('selected');
	}
	//colSelect
	function changeColSelectState(boxnum) {
		var colSelectorCheckbox = $('.colSelector[data-id=' + boxnum
				+ '] input');
		for (var i = 0; i < colSelectorCheckbox.length; i++) {
			var colIndex = $(colSelectorCheckbox[i]).val();
			if (isColAllSelected(boxnum, colIndex))
				setColCheckState(boxnum, colIndex, true);
			else
				setColCheckState(boxnum, colIndex, false);
		}
	}
	//选中的是否有没有使用中的柜子  锁定小柜调用   有使用中的小柜时返回false 没有返回true
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
	//选中的是否没有锁定中的小柜    打开柜子调用   是的话返回true 不是返回false
	function isNoLocked(boxnum) {
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con.selected[data-stenumber='
				+ boxnum + ']');
		for (var i = 0; i < selectCells.length; i++) {
			if ($(selectCells[i]).attr("data-yesOrNoLock") == 1) {
				/* swal("无法解锁未锁定的小柜","请取消非锁定小柜的选中状态","warning"); */
				return false;
			}
		}
		return true;
	}
</script>
