<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%> 
<t:DataDict type="shoes" var="shoes"></t:DataDict> 
<t:DataDict type="cloth" var="cloth"></t:DataDict> 
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 物资管理 <span>> 库存监控 </span>
		</h1>
	</div>
</div>
<input type="hidden" value="${isOrNoSterilizer }" id="isOrNoSterilizer" />
<section id="widget-grid">

	<div class="row">
		<div class="col-md-4" id="HosSterilizer">
			<div class="box">
				<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0"
					data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>消毒鞋监控</h2>
					</header>
					<div class="box-header with-border">
						<form id="authSteSearchFormA" role="form" class="form-inline">
							<hr>
						</form>
					</div>
					<div class="box-body">
						<div class="row">
							<div class="col-md-12">
								<div class="btn-group " id="tableToolbarA" role="group"></div>

								<table id="ahthSteTableA" data-mobile-responsive="true"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



		<div class="col-md-4" id="HosWardrobe">
			<div class="box">
				<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0"
					data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>手术衣监控</h2>
					</header>
					<div class="box-header with-border">
						<form id="authSteSearchFormB" role="form" class="form-inline">
						</form>
						<hr>
					</div>
					<div class="box-body">
						<div class="row">
							<div class="col-md-12">
								<div class="btn-group " id="tableToolbarB" role="group"></div>

								<table id="ahthSteTableB" data-mobile-responsive="true"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="col-md-4" id="HosRecycle">
			<div class="box">
				<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0"
					data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>回收桶监控</h2>
					</header>
					<div class="box-header with-border">
						<form id="authSteSearchFormC" role="form" class="form-inline">
							<hr>
						</form>
					</div>
					<div class="box-body">
						<div class="row">
							<div class="col-md-12">
								<div class="btn-group " id="tableToolbarC" role="group"></div>

								<table id="ahthSteTableC" data-mobile-responsive="true"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>
</section>

<!-- Dynamic sterilizerContainerModal Modal -->
<div class="modal fade" id="sterilizerContainerModal" tabindex="-1"
	role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog" id="modal-dialog1" style="width: 900px;">
		<div class="modal-content" id="modal-content1"></div>
	</div>
</div>


<!-- Dynamic allotModel -->
<div class="modal fade" id="allotModel" tabindex="-2" role="dialog"
	aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 900px;">
		<div class="modal-content"></div>
	</div>
</div>
<script>
	var isOrNoSterilizer = parseInt($("#isOrNoSterilizer").val());

	$(function() {
		if (isOrNoSterilizer == 0) {
			$("#HosSterilizer").hide();
			$("#HosWardrobe").attr("class", "col-md-6");
			$("#HosRecycle").attr("class", "col-md-6");
		}
	})

	//var theaterId = $("#theaterId_div").val();
	var jqTableDomA = $("#ahthSteTableA");
	var jqFormDomA = $("#authSteSearchFormA");
	var jqToolbarDomA = $("#tableToolbarA");
	var initUrlA = "hosSterilizer/selectHosSterilizerMonitored";

	var jqTableDomB = $("#ahthSteTableB");
	var jqFormDomB = $("#authSteSearchFormB");
	var jqToolbarDomB = $("#tableToolbarB");
	var initUrlB = "hosWardrobe/selectHosWardrobeMonitored";

	var jqTableDomC = $("#ahthSteTableC");
	var jqFormDomC = $("#authSteSearchFormC");
	var jqToolbarDomC = $("#tableToolbarC");
	var initUrlC = "hosRecycle/queryHosRecycleMonitored";

	setInterval(initData, 600000);
	$(function() {
		initData();
	});
	function initData() {
		var columnsA = [
				{
					field : 'shoesSize',
					title : '鞋码',
					width : 160,
					sortable : true,
					formatter:function(value,row,index){
						if(window.top["shoes"]){
							for(var i=0; i<window.top["shoes"].length; i++){
								if (window.top["shoes"][i].code == value) return window.top["shoes"][i].name;
							}
						}
						return value;
					} 
				},
				{
					field : 'sumCount',
					title : '小柜总数',
					width : 160,
					sortable : true
				},
				{
					field : 'haveShoesCount',
					title : '可用小柜数',
					width : 160,
					formatter : function(value, row, index) {
						var msg = "";
						if (value == null || value == "") {
							value = 0;
						}
						if (value < 10)
							msg = '<b style="color:red;font-size:18px;">'
									+ value + '</b>';
						else
							msg = value;
						return msg;
					}
				}, {
					field : 'name',
					title : '存放位置',
					width : 160
				},

		];
		var columnsB = [
				{
					field : 'opeSize',
					title : '衣码',
					width : 160,
					sortable : true,
					formatter:function(value,row,index){
						if(window.top["cloth"]){
							for(var i=0; i<window.top["cloth"].length; i++){
								if (window.top["cloth"][i].code == value) return window.top["cloth"][i].name;
							}
						}
						return value;
					} 
				},
				{
					field : 'allOpeCount',
					title : '总数',
					width : 160,
					sortable : true
				},
				{
					field : 'opeCount',
					title : '可用数量',
					width : 160,
					sortable : true,
					formatter : function(value, row, index) {
						var msg = "";
						if (value < 10)
							msg = '<b style="color:red;font-size:18px;">'
									+ value + '</b>';
						else
							msg = value;
						return msg;
					}
				}, {
					field : 'room',
					title : '更衣室',
					width : 160
				}, {
					field : 'name',
					title : '手术区',
					width : 160
				}, ];

		var columnsC = [ {
			field : 'number',
			title : '编号',
			width : 160,
			sortable : true
		}, {
			field : 'type',
			title : '回收类型',
			width : 160,
			sortable : true,
			formatter : zfesUtil.formatterRecoveryGoods
		}, {
			field : 'recycle',
			title : '回收中',
			width : 160
		}, {
			field : 'name',
			title : '存放位置',
			width : 160
		},

		];

		if (isOrNoSterilizer != 0) {
			zfesBstable
					.laodTable(
							jqTableDomA,
							jqFormDomA,
							jqToolbarDomA,
							initUrlA,
							columnsA,
							{
								height : 500,
								rowStyle : function(row, index) {
									var strclass = "";
									if (row.haveShoesCount < 10) {
										strclass = 'danger';//还有一个active
									} else {
										return {};
									}
									return {
										classes : strclass
									}
								},
								/* onClickRow : function(row) {
									var number = row.number;
									var rows = row.rows;
									var columns = row.columns;
									var url = "hosSterilizer/enterSteLoadShoes?steNumber="
											+ number
											+ "&rows="
											+ rows
											+ "&columns=" + columns;
									$("#sterilizerContainerModal").modal({
										remote : url
									});
								} */
							});
		}

		zfesBstable
				.laodTable(
						jqTableDomB,
						jqFormDomB,
						jqToolbarDomB,
						initUrlB,
						columnsB,
						{
							height : 500,
							rowStyle : function(row, index) {
								var strclass = "";
								if (row.opeCount < 10) {
									strclass = 'danger';//还有一个active
								} else {
									return {};
								}
								return {
									classes : strclass
								}
							}
						/* 	onClickRow : function(row) {
								var number = row.number;
								// 								$("#allotModel")
								$("#sterilizerContainerModal")
										.modal(
												{
													remote : "hosWardrobe/enterLoadOperationPage?warNumber="
															+ number
												});
							} */
						});

		zfesBstable.laodTable(jqTableDomC, jqFormDomC, jqToolbarDomC, initUrlC,
				columnsC, {
					height : 500,
					rowStyle : function(row, index) {
						//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
						var strclass = "";
						if (row.state == "1" && row.cnt > 100) {
							strclass = 'danger';//还有一个active
						} else {
							return {};
						}
						return {
							classes : strclass
						}
					}
				});
		setTimeout(function() {
			$('.layui-layer .box').find('.pull-right:not(button)').remove();
		}, 1000);

	}
</script>