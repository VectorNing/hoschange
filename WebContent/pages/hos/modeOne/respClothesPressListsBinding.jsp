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
<script type="text/javascript">
	$(document).ready(function() {
		var temp = "";
		$('.seatCharts-cell.seatCharts-seat.cell-con').click(function() {
			var cell = $(this);
			if (temp != "") {
				temp.removeClass("select");
			}
			if (cell.parent().attr("class") != "intro") {
				cell.toggleClass('select');
				temp = cell;
			}
			/* $(".seatCharts-cell-con seatCharts-seat-con available war").css("background","#374BC8"); */
		});
	});
</script>
<style>
.col-md-8>.seatCharts-cell {
	height: 500px;
	width: 600px;
	overflow: auto;
}

.select {
	background-color: #ff6699;
}
</style>

<input type="hidden" value="${rooms[0].steNumber}" id="steNumber_input" />
<input type="hidden" value="${rooms[0].rows}" id="rows_input" />
<input type="hidden" value="${rooms[0].columns}" id="columns_input" />
<input type="hidden" value="${rooms[0].total}" id="total_input" />
<div id="container_div">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">设定</h4>
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
														data-size="${co.clothSize}" data-number="${co.doorNumber}"
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
								<p
									style="float: right; margin-right: 148px; margin-top: -169px;">锁定中</p>
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
						<div class="col-md-6 col-xs-6 col-sm-6 col-lg-6"
							style="margin-top: 40px;">

							<section id="widget-grid">
								<form id="searchForm1" role="form" class="form-inline">


									<div class="form-group">
										<label>姓名</label> <input name="userName" type="text"
											placeholder="" class="form-control">
									</div>
									<button type="button"
										class="btn btn-default pull-right m-t-n-xs"
										onclick="search();">
										<strong>查询</strong>
									</button>
								</form>
								<hr>
								<div class="row">
									<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="jarviswidget jarviswidget-color-blueDark"
											id="wid-id-0" data-widget-editbutton="false"
											style="border-top: 1px solid #cccccc;">
											<div>
												<div class="widget-body ">
													<table id="ahthUserBootstrapTable"
														data-mobile-responsive="true"></table>
												</div>
											</div>
										</div>
									</article>
								</div>
							</section>
						</div>
					</div>
				</div>

			</div>
		</fieldset>
	</div>
	<hr>
	<footer style="text-align: center;">
		<button type="button" id="saveBtn" class="btn btn-primary"
			onclick="binding();">绑定使用人</button>
		<button type="button" id="emptyBtn" class="btn btn-primary"
			onclick="stopBinding();">取消绑定</button>
	</footer>
	<hr>
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

	function loadClothPress() {
		var steNumber = $("#steNumber_input").val();//储物柜编号
		var total = $("#total_input").val();
		var url = "hosClothesPress/enterSetClothesPressAndUser?steNumber="
				+ steNumber + "&total=" + total;
		$("#container_div").load(url);
	}

	$(function() {
		initData();
		attachModalHidden();
	});

	function search() {
		zfesBstable.reload(jqTableDom1);
	}

	function getSelectId() {
		var id = $('.seatCharts-cell.seatCharts-seat.cell-con.select').attr(
				'data-id');
		if (id) {
			return id;
		} else {
			alertSwal.warningText("请选择小柜");
			return false;
		}
		;
	}
	function binding() {
		if (zfesBstable.isOneRow(jqTableDom1)) {
			var id = getSelectId();
			if (!id) {
				return false;
			}
			var row = zfesBstable.getSelections(jqTableDom1);
			var userId = row[0].id;
			var ajaxUrl = "hosClothesPress/bindingClothesPressAndUser";
			var param = {
				"id" : id,
				"userId" : userId
			};
			zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
				alertSwal.successText(data.message);
				loadClothPress();
			});

		}
	}
	function stopBinding() {
		var id = getSelectId();
		if (!id) {
			return false;
		}
		var ajaxUrl = "hosClothesPress/stopBindingClothesPressAndUser";
		var param = {
			"id" : id
		};
		zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
			alertSwal.successText(data.message);
			//window.location.reload();
			loadClothPress();
		});
	}
</script>
