<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<div class="row">
	<input type="hidden" id="scheduleId" value="${id }">
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
					<h2>用户列表</h2>
				</header>

				<div>
					<div class="widget-body ">
						<form id="searchForm" role="form" class="form-inline">

							<div class="form-group">
								<label>姓名</label> <input name="userName" type="text"
									placeholder="" class="form-control">
							</div>
							<input type="hidden" id="enabled" value='1' name="type" />
							<button type="button" class="btn btn-default pull-right m-t-n-xs"
								onclick="search();">

								<strong>查询</strong>
							</button>
						</form>
						<hr>

						<table id="ahthUserBootstrapTable" data-mobile-responsive="true"></table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

<footer>
	<button type="button" class="btn btn-primary" onclick="save()">保存</button>
	<button type="button" class="btn btn-default" data-dismiss="modal">
		取消</button>
</footer>

<!-- Dynamic Modal -->
<div class="modal fade" id="userModal" tabindex="-1" role="dialog"
	aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" id="modal-content""></div>
	</div>
</div>

<div class="modal fade" id="userModalAssign" tabindex="-1" role="dialog"
	aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" id="modal-content" style="width: 900px;">

		</div>
	</div>
</div>

<script>
	var jqTableDom2 = $("#ahthUserBootstrapTable");
	function initData() {
		var jqFormDom = $("#searchForm");
		var jqToolbarDom = $("#authUserTableToolbar");
		var url = "authUser/queryAuthUserSet";
		var columns = [ {
			checkbox : true
		}, {
			field : 'userName',
			title : '姓名',
			width : 160
		}, {
			field : 'loginName2',
			title : '卡号',
			width : 160
		}, {
			field : 'jobnumber',
			title : '工号',
			width : 160
		}, {
			field : 'shoesSize',
			title : '鞋子尺码',
			width : 160
		}, {
			field : 'clothesSize',
			title : '衣服尺码',
			width : 160
		} ];
		bstable = zfesBstable.laodTable(jqTableDom2, jqFormDom, jqToolbarDom,
				url, columns);
	}
	function attachModalHidden() {
		$("#userModal").on('hide.bs.modal', function() {
			zfesBstable.refresh(jqTableDom);
		});
	}
	$(function() {
		initData();
		//attachModalHidden();
	});

	function search() {
		zfesBstable.reload(jqTableDom2);
	}
	function save() {
		var scheduleId = $("#scheduleId").val();
		var ids = zfesBstable.getRowIds(jqTableDom);
		ids = ids.join(',');
		var ajaxUrl = "hosScheduling/addUserToSchedul";
		var param = {
			"scheduleId" : scheduleId,
			"ids" : ids
		};
		zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
			alertSwal.successText(data.message);
		});
	}
</script>