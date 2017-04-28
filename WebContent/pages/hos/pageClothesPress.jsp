<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 物资管理 <span>> 储物柜管理 </span>
		</h1>
	</div>
</div>
<section id="widget-grid">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0"
				data-widget-editbutton="false">
				<!-- -->
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2>储物柜列表</h2>
				</header>

				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
						<form id="searchForm" role="form" class="form-inline">


							<div class="form-group">
								<label>编号</label> <input name="number" type="text"
									placeholder="" class="form-control">
							</div>

							<button type="button" class="btn btn-default pull-right m-t-n-xs"
								onclick="search();">
								<strong>查询</strong>
							</button>
						</form>


						<hr>


						<div class="btn-group " id="sterilizerTableToolbar" role="group">
							<button type="button" class="btn btn-default" onclick="add();">
								<i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加
							</button>
							<button type="button" class="btn btn-default" onclick="edit();">
								<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改
							</button>
							<button type="button" class="btn btn-default"
								onclick="deleteSterilizer();">
								<i class="glyphicon glyphicon-trash" aria-hidden="true"></i> 注销
							</button>
							<button type="button" class="btn btn-default"
								onclick="setClothesUser();">
								<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
								指定储物柜使用人
							</button>
							<button type="button" class="btn btn-default"
								onclick="openOrLock();">
								<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
								锁柜、开柜
							</button>
						</div>
						<table style="table-layout: fixed;"
							id="clothesPressBootstrapTable" data-mobile-responsive="true"></table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

<!-- <!-- Dynamic sterilizerContainerModal Modal -->
<div class="modal fade" id="sterilizerContainerModal" tabindex="-1"
	role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 900px;">
		<div class="modal-content"></div>
	</div>
</div>

<!-- Dynamic Modal -->
<div class="modal fade" id="clothesPressModal" tabindex="-1"
	role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content"></div>
	</div>
</div>


<script>
	var jqTableDom = $("#clothesPressBootstrapTable");
	function initData() {
		var jqFormDom = $("#searchForm");
		var jqToolbarDom = $("#sterilizerTableToolbar");
		var url = "hosClothesPress/queryClothesPress";
		var columns = [ {
			checkbox : true
		}, {
			field : 'number',
			title : '编号',
			width : 160,
			sortable : true
		}, {
			field : 'columns',
			title : '小柜总数',
			width : 160,
			sortable : true,
			formatter : function(value, row, index) {
				return (row.total)
			}
		}, {
			field : 'count',
			title : '使用中',
			width : 160
		}, {
			field : 'name',
			title : '存放位置',
			width : 160
		},
		//{field:'state',title:'状态',width:100,formatter:zfesUtil.formatStateUse},
		{
			field : 'description',
			title : '描述',
			width : 160,
			formatter : zfesUtil.formatterOmit
		} ];

		bstable = zfesBstable.laodTable(jqTableDom, jqFormDom, jqToolbarDom,
				url, columns, {
					rowStyle : function(row, index) {
						//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
						var strclass = "";
						/*  if (row.state == "1" && row.cnt < 10) {
						     strclass = 'danger';//还有一个active
						 }
						 else {
						     return {};
						 } */
						return {
							classes : strclass
						}
					}
				});
	}
	function attachModalHidden() {
		$("#clothesPressModal").on('hide.bs.modal', function() {
			zfesBstable.refresh(jqTableDom);
		});
		$("#sterilizerContainerModal").on('hide.bs.modal', function() {
			zfesBstable.refresh(jqTableDom);
		});
	}

	$(function() {
		initData();
		attachModalHidden();
	});

	function search() {
		zfesBstable.reload(jqTableDom);
	}

	function edit() {
		if (zfesBstable.isOneRow(jqTableDom)) {
			var row = zfesBstable.getSelections(jqTableDom);
			var id = row[0].id;
			var url = "hosClothesPress/enterEditHosClothesPress?id=" + id;
			$("#clothesPressModal").modal({
				remote : url
			});
		}
	}
	function add() {
		var url = "hosClothesPress/enterAddHosClothesPress";
		$("#clothesPressModal").modal({
			remote : url
		});
	}
	function deleteSterilizer() {
		if (zfesBstable.isOneRow(jqTableDom)) {
			var row = zfesBstable.getSelections(jqTableDom);
			var id = row[0].id;
			var param = {
				"id" : id
			};
			var url = "hosClothesPress/selectClothesFromClothesPressById";
			var ajaxUrl = "hosClothesPress/deleteClothesPressById";
			zfesAjax.ajaxTodo(url, param, function(data) {
				var count = data.strData
				var wain;
				if (count > 0) {
					wain = "该衣柜还有" + count + "个柜子在使用，禁止销毁该衣柜"
					alertSwal.warningText(wain);
					return false;
				} else {
					wain = "是否确认销毁该消毒柜？"
				}
				alertSwal.confirm("确认注销", wain, function(isComfirm) {
					if (isComfirm) {
						zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
							alertSwal.successText(data.message);
							zfesBstable.refresh(jqTableDom);
						});
					}
				});
			});
		}
	}
	function setClothesUser(){
		if(zfesBstable.isOneRow(jqTableDom)){
			var row = zfesBstable.getSelections(jqTableDom);
			if(row[0].state == 0){
				alertSwal.warningTitle("不能将未使用的储物柜与医护人员绑定");
				return;
			}
			var number = row[0].number;
			var total = row[0].total;
			var url = "hosClothesPress/enterSetClothesPressAndUser?steNumber="+number+"&total="+total;
			$("#sterilizerContainerModal").modal({
			    remote: url
			});
		}
		
	}
	function openOrLock(){
		if(zfesBstable.isOneRow(jqTableDom)){
			var row = zfesBstable.getSelections(jqTableDom);
			if(row[0].state == 0){
				alertSwal.warningTitle("不能打开或锁定未使用的储物柜");
				return;
			}
			var number = row[0].number;
			var total = row[0].total;
			var url = "hosClothesPress/enterOpenOrLockClothesPress?steNumber="+number+"&total="+total;
			$("#sterilizerContainerModal").modal({
			    remote: url
			});
		}
	}
</script>