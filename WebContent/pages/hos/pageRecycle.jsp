<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 物资管理 <span>> 回收桶管理 </span>
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
					<h2>回收桶列表</h2>
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


						<div class="btn-group " id="recycleTableToolbar" role="group">
							<button type="button" class="btn btn-default" onclick="add();">
								<i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加
							</button>
							<button type="button" class="btn btn-default" onclick="edit();">
								<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改
							</button>
							<button type="button" class="btn btn-default"
								onclick="deleteRecycle();">
								<i class="glyphicon glyphicon-trash" aria-hidden="true"></i> 注销
							</button>
						</div>
						<table id="recycleBootstrapTable" data-mobile-responsive="true"></table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

<!-- Dynamic sterilizerContainerModal Modal -->
<div class="modal fade" id="sterilizerContainerModal" tabindex="-1"
	role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 900px;">
		<div class="modal-content"></div>
	</div>
</div>

<!-- Dynamic Modal -->
<div class="modal fade" id="recycleModal" tabindex="-1" role="dialog"
	aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content"></div>
	</div>
</div>


<script>
	var jqTableDom = $("#recycleBootstrapTable");
	function initData() {
		var jqFormDom = $("#searchForm");
		var jqToolbarDom = $("#recycleTableToolbar");
		var url = "hosRecycle/queryHosRecycleSet";
		var columns = [ {
			checkbox : true
		}, {
			field : 'number',
			title : '编号',
			width : 160,
			sortable : true
		}, /* {
			field : 'state',
			title : '状态',
			width : 100,
			formatter : zfesUtil.formatStateUse
		}, */ {
			field : 'type',
			title : '回收物品',
			width : 100,
			formatter : zfesUtil.formatRecycleType
		}, {
			field : 'name',
			title : '存放位置',
			width : 160
		}, {
			field : 'recycle',
			title : '回收数量',
			width : 160
		}, {
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
	}
	function attachModalHidden() {
		$("#recycleModal").on('hide.bs.modal', function() {
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
			var url = "hosRecycle/enterEditHosRecycle?id=" + id;
			$("#recycleModal").modal({
				remote : url
			});
		}
	}
	function add() {
		var url = "hosRecycle/enterAddHosHosRecycle";
		$("#recycleModal").modal({
			remote : url
		});
	}
	function deleteRecycle() {
		if (zfesBstable.isOneRow(jqTableDom)) {
			var row = zfesBstable.getSelections(jqTableDom);
			var id = row[0].id;
			var number = row[0].number;
			var param = {
				"id" : id,
				"number" : number
			};
			var url = "hosRecycle/selectRecycleFromRecycleByNumber";
			var ajaxUrl = "hosRecycle/deleteHosRecycle";
			zfesAjax.ajaxTodo(url, param, function(data) {
				var count = data.strData
				var wain;
				if (count > 0) {
					wain = "该回收桶内还有" + count + "件衣物未清理，是否确认注销回收桶？"
				} else {
					wain = "是否确认注销该回收桶？"
				}
				alertSwal.confirm("确认删除", wain, function(isComfirm) {
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

	function select() {
		var s = $("#isSuperAdmin").val();
		if (s != 1) {
			$("#recycleTableToolbar").hide();
		}
	}

	/* function loadTheaterByUserId() {
	var url = "hosTheater/loadTheaterByUserId";
	$.ajax({
	       type: "get",
	       url: url,
	       data:{page:"pageRecycle"},
	       success: function (data) {
	    	   
	       },
	       error: function (XMLHttpRequest, textStatus, errorThrown) {
	       }
	});
	}  */
</script>