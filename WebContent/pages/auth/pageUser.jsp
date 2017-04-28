<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 用户管理 <span>> 用户管理 </span>
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
					<h2>用户列表</h2>
				</header>

				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
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


						<div class="btn-group " id="authUserTableToolbar" role="group">
							<!-- <a href="pages/auth/respAddUser.jsp" data-toggle="modal" data-target="#roleModal" class="btn btn-default">
									<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>角色
								</a> -->
							<button type="button" class="btn btn-default" onclick="add();">
								<i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加
							</button>
							<button type="button" class="btn btn-default" onclick="edit();">
								<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 维护
							</button>
							<button type="button" class="btn btn-default" onclick="cancel();">
								<i class="glyphicon glyphicon-remove" aria-hidden="true"></i> 注销
							</button>
							<button type="button" class="btn btn-default"
								onclick="enableAuthUser();">
								<i class="glyphicon glyphicon-ok" aria-hidden="true"></i> 启用
							</button>
							<button type="button" id="selectAll" data-state="1"
								class="btn btn-default" onclick="selectAll();">未维护人员</button>
							<button type="button" class="btn btn-default"
								onclick="assignRole();">
								<i class="glyphicon glyphicon-random" aria-hidden="true"></i>
								分配角色
							</button>
							<button type="button" class="btn btn-default"
								onclick="uploadExcel();">
								<i class="glyphicon glyphicon-list-alt" aria-hidden="true"></i> 导入Excel
							</button>
						</div>
						<table id="ahthUserBootstrapTable" data-mobile-responsive="true"></table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

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
	var jqTableDom = $("#ahthUserBootstrapTable");
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
		}, {
			field : 'sex',
			title : '性别',
			width : 160
		}, {
			field : 'enabled',
			title : '用户状态',
			width : 60,
			formatter : zfesUtil.formatBool
		} ];
		bstable = zfesBstable.laodTable(jqTableDom, jqFormDom, jqToolbarDom,
				url, columns);
	}
	function attachModalHidden() {
		$("#userModal").on('hide.bs.modal', function() {
			zfesBstable.refresh(jqTableDom);
		});
	}
	$(function() {
		initData();
		attachModalHidden();
	});

	function selectAll() {
		if ($("#selectAll").attr("data-state") == 1) {
			$("#enabled").val(0);
			$("#selectAll").html("已维护人员");
			$("#selectAll").attr("data-state", 0);
		} else {
			$("#enabled").val(1);
			$("#selectAll").html("未维护人员");
			$("#selectAll").attr("data-state", 1);
		}
		initData();
	}

	function search() {
		zfesBstable.reload(jqTableDom);
	}

	function add() {
		$("#userModal").modal({
			remote : "authUser/enterAddAuthUser"
		});
	}

	function edit() {
		if (zfesBstable.isOneRow(jqTableDom)) {
			var id = zfesBstable.getRowId(jqTableDom);
			$("#userModal").modal({
				remote : "authUser/enterEditAuthUserAll?id=" + id
			});
		}
	}

	function userDetail() {
		if (zfesBstable.isOneRow(jqTableDom)) {
			var id = zfesBstable.getRowId(jqTableDom);
			$("#userModal").modal({
				remote : "authUser/enterAuthUserDetail?id=" + id
			});
		}
	}

	function cancel() {
		if (zfesBstable.isSelectedRow(jqTableDom)) {
			var row = zfesBstable.getSelections(jqTableDom);
			for (var i = 0; i < row.length; i++) {
				if (row[i].enabled == '0') {
					var warn = row[i].loginName + "用户已经是停用状态";
					alertSwal.warningTitle(warn);
					return false;
				}
			}
			var ids = zfesBstable.getRowIds(jqTableDom);
			var flag = "0";
			alertSwal.confirm("注销", "是否注销该用户", function(isComfirm) {
				if (isComfirm) {
					var ajaxUrl = "authUser/disableBatchAuthUser";
					var param = {
						"ids" : ids.join(',')
					};/* ,"flag":flag */
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						setTimeout(function() {
							alertSwal.successText(data.message);
							zfesBstable.reload(jqTableDom);
						}, 100);
					});
				}
			});
		}
	}

	function enableAuthUser() {
		if (zfesBstable.isSelectedRow(jqTableDom)) {
			var row = zfesBstable.getSelections(jqTableDom);
			for (var i = 0; i < row.length; i++) {
				if (row[i].enabled == '1') {
					var warn = row[i].loginName + "用户已经是启用状态";
					alertSwal.warningTitle(warn);
					return false;
				}
			}
			var ids = zfesBstable.getRowIds(jqTableDom);
			var flag = "1";
			alertSwal.confirm("启用", "是否启用该用户", function(isComfirm) {
				if (isComfirm) {
					var ajaxUrl = "authUser/enableBatchAuthUser";
					var param = {
						"ids" : ids.join(',')
					};/* ,"flag":flag */
					zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
						setTimeout(function() {
							alertSwal.successText(data.message);
							zfesBstable.reload(jqTableDom);
						}, 100);
					});
				}
			});
		}
	}

	function assignRole() {
		if (zfesBstable.isOneRow(jqTableDom)) {
			var id = zfesBstable.getRowId(jqTableDom);
			$("#userModalAssign").modal({
				remote : "authUser/enterAssignUserToRole?id=" + id
			}).modal('show').on('hide.bs.modal', function() {
				zfesBstable.reload(jqTableDom);
			});
			;
		}
	}
	function uploadExcel(){
		$("#userModal").modal({
			remote : "authUser/uploadExcel"
		});
	}

</script>