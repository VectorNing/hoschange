<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="device" var="device"></t:DataDict>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 物资管理 <span>> 回收记录查询 </span>
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
					<h2>回收单元信息列表</h2>
				</header>

				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
						<form id="searchForm" role="form" class="form-inline">

							<!-- <div class="form-group">
		                         <label>类别</label>
		                        <label class="select"> 
		                             <select dict="device"  name="type" class="form-control" style="width: 140px;"></select>
		                         </label>
		                    </div> -->
							<div class="form-group">
								<div class="form-group">
									<label>使用人</label> <label class="select"> <input
										name="userName" type="text" placeholder=""
										class="form-control">
									</label>
								</div>
								<label>是否回收</label> <label class="select"> <select
									name="state" class="form-control" style="width: 140px;">
										<option value="">请选择...</option>
										<option value="1">是</option>
										<option value="0">否</option>
								</select>
								</label>
							</div>
							<!-- <div class="form-group">
								<label>存放位置</label> <label class="select"> <input
									name="name" type="text" placeholder="" class="form-control">
								</label>
							</div> -->

							<button type="button" class="btn btn-default pull-right m-t-n-xs"
								onclick="search();">
								<strong>查询</strong>
							</button>
						</form>


						<hr>


						<div class="btn-group " id="recoveryGoodsTableToolbar"
							role="group">
							<button type="button" class="btn btn-default"
								onclick="selectImageBase64();">
								<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>查看监控图像
							</button>
						</div>
						<table id="recoveryGoodsBootstrapTable"
							data-mobile-responsive="true"></table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

<div class="modal fade" id="rescoveryContainerModal" tabindex="-1"
	role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 1025px;">
		<div class="modal-content"></div>
	</div>
</div>
<script>
	var jqTableDom = $("#recoveryGoodsBootstrapTable");
	function initData() {
		var jqFormDom = $("#searchForm");
		var jqToolbarDom = $("#recoveryGoodsTableToolbar");
		var url = "hosRecoveryGoods/queryhosRecoveryGoodsSet";
		var columns = [ {
			checkbox : true
		}, {
			field : 'userName',
			title : '使用人',
			width : 100
		}, {
			field : 'type',
			title : '物品类别',
			width : 160,
			formatter : zfesUtil.formatterRecoveryGoods
		}, {
			field : 'size',
			title : '尺码',
			width : 160,
			formatter : function(value, rows, index) {
				if (rows.type == 1) {
					if (window.top['cloth']) {
						for (var i = 0; i < window.top['cloth'].length; i++) {
							if (window.top['cloth'][i].code == value)
								return window.top['cloth'][i].name;
						}
					}
				} else if (rows.type == 0) {
					if (window.top['shoes']) {
						for (var i = 0; i < window.top['shoes'].length; i++) {
							if (window.top['shoes'][i].code == value)
								return window.top['shoes'][i].name;
						}
					}
				}
			}
		}, {
			field : 'state',
			title : '是否回收 ',
			width : 160,
			formatter : zfesUtil.formatYesNo
		}, {
			field : 'recoveryTime',
			title : '回收时间',
			width : 160
		}, {
			field : 'name',
			title : '存放位置',
			width : 160
		/* ,sortable:true */} ];
		bstable = zfesBstable.laodTable(jqTableDom, jqFormDom, jqToolbarDom,
				url, columns);
	}

	$(function() {
		initData();
	});
	function selectImageBase64() {
		var id = zfesBstable.getRowId(jqTableDom);
		if (id != false) {
			var url = "hosRecoveryGoods/selectImageBase64?id=" + id;
			$("#rescoveryContainerModal").modal({
				remote : url
			});
		}
	}
	function search() {
		zfesBstable.reload(jqTableDom);
	}
</script>