<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 用户管理 <span>> 角色管理 </span>
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
					<h2>角色列表</h2>
				</header>

				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
						<form id="searchForm" role="form" class="form-inline">


							<div class="form-group">
								<label>角色名称</label> <input name="name" type="text"
									placeholder="" class="form-control">
							</div>
							<!--  <div class="form-group">
		                    <label>是否启用</label>
		                          <select class="form-control" name="enabled" style="width: 170px;">
		                        		  <option value="">请选择</option>
		                                  <option value="1">启用</option>
		                                  <option value="0">禁用</option>
		                           </select>
		                    </div> -->
							<button type="button" class="btn btn-default pull-right m-t-n-xs"
								onclick="search();">
								<strong>查询</strong>
							</button>
						</form>


						<hr>


						<div class="btn-group " id="ahthRoleTableToolbar" role="group">
							<button type="button" class="btn btn-default" onclick="add();">
								<i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加
							</button>
							<button type="button" class="btn btn-default" onclick="edit();">
								<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改
							</button>
							<button type="button" class="btn btn-default"
								onclick="cancelBatch();">
								<i class="glyphicon glyphicon-remove" aria-hidden="true"></i> 注销
							</button>
							<!-- <button type="button" class="btn btn-default"
								onclick="activateBatch();">
								<i class="glyphicon glyphicon-ok" aria-hidden="true"></i> 启用
							</button> -->
							<button type="button" class="btn btn-default"
								onclick="assignPerm()">
								<i class="glyphicon glyphicon-random" aria-hidden="true"></i>
								分配权限
							</button>
						</div>
						<table id="ahthRoleBootstrapTable" data-mobile-responsive="true"></table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

<!-- Dynamic Modal -->
<div class="modal fade" id="roleModal" tabindex="-1" role="dialog"
	aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" id="modal-content"></div>
	</div>
</div>

<div class="modal fade" id="roleAssignPerm" tabindex="-1" role="dialog"
	aria-labelledby="remoteModalLabel" aria-hidden="true"
	style="margin-left: -400px;">
	<div class="modal-dialog">
		<div class="modal-content" id="modal-content" style="width: 1200px;">

		</div>
	</div>
</div>

<script>
	var jqTableDom = $("#ahthRoleBootstrapTable");
	function initData() {
		var jqFormDom = $("#searchForm");
		var jqToolbarDom = $("#ahthRoleTableToolbar");
		var url = "authRole/queryAuthRoleSet";
		var columns = [ {
			checkbox : true
		}, {
			field : 'name',
			title : '角色名称',
			width : 120,
			sortable : true
		},
		//{field:'code',title:'角色编码',width:160,sortable:true},
	/* 	{
			field : 'enabled',
			title : '角色状态',
			width : 60,
			formatter : zfesUtil.formatBool
		}, */
		{
			field : 'description',
			title : '描述',
			width : 200 ,
			formatter : zfesUtil.formatterOmit
		},{
			field : 'lqcs',
			title : '衣物领取次数',
			width : 100 ,
			formatter : zfesUtil.formatterOmit
		} ];
		bstable = zfesBstable.laodTable(jqTableDom, jqFormDom, jqToolbarDom,
				url, columns);
	}
	function attachModalHidden() {
		$("#roleModal").on('hide.bs.modal', function() {
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

	function add() {
		$("#roleModal").modal({
			remote : "authRole/enterAddRole"
		});
	}

	function edit() {
		if (zfesBstable.isOneRow(jqTableDom)) {
			var id = zfesBstable.getRowId(jqTableDom);
			$("#roleModal").modal({
				remote : "authRole/enterEditRole?id=" + id
			});

		}
	}

	function cancel() {
		if (zfesBstable.isOneRow(jqTableDom)) {
			var row = zfesBstable.getSelections(jqTableDom);
			if (row[0].enabled == '0') {
				alertSwal.warningTitle("该角色已经是停用状态");
				return false;
			}
			var id = zfesBstable.getRowId(jqTableDom);
			var flag = "0";
			alertSwal.confirm("注销", "是否注销该角色", function() {
				var ajaxUrl = "authRole/disableAuthRole";
				var param = {
					"id" : id,
					"flag" : flag
				};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					setTimeout(function() {
						alertSwal.successText(data.message);
						zfesBstable.refresh(jqTableDom);
					}, 100);
				});
			});
		}
	}

	function activate() {
		if (zfesBstable.isOneRow(jqTableDom)) {
			var row = zfesBstable.getSelections(jqTableDom);
			if (row[0].enabled == '1') {
				alertSwal.warningTitle("该角色已经是启用状态");
				return false;
			}
			var id = zfesBstable.getRowId(jqTableDom);
			var flag = "1";
			alertSwal.confirm("启用", "是否启用该角色", function() {
				var ajaxUrl = "authRole/enableAuthRole";
				var param = {
					"id" : id,
					"flag" : flag
				};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					setTimeout(function() {
						alertSwal.successText(data.message);
						zfesBstable.refresh(jqTableDom);
					}, 100);
				});
			});
		}
	}

	function assignPerm() {
		if (zfesBstable.isOneRow(jqTableDom)) {
			var id = zfesBstable.getRowId(jqTableDom);
			$("#roleAssignPerm").modal({
				remote : "authRole/permAssign?id=" + id
			}).modal('show').on('hide.bs.modal', function() {
				zfesBstable.refresh(jqTableDom);
			});
			;
		}
	}

	//----------------------------11-25批量注销启用-------------------

	function cancelBatch() {
		if (zfesBstable.isSelectedRow(jqTableDom)) {
			var row = zfesBstable.getSelections(jqTableDom);
			for (var i = 0; i < row.length; i++) {
				if (row[i].enabled == '0') {
					var warn = row[i].name + "角色已经是停用状态";
					alertSwal.warningTitle(warn);
					return false;
				}
			}
			var ids = zfesBstable.getRowIds(jqTableDom);
			alertSwal.confirm("注销", "是否注销角色", function() {
				var ajaxUrl = "authRole/disableBatchAuthRole";
				var param = {
					"ids" : ids.join(',')
				};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					setTimeout(function() {
						alertSwal.successText(data.message);
						zfesBstable.refresh(jqTableDom);
					}, 100);
				});
			});
		}
	}

	function activateBatch() {
		if (zfesBstable.isSelectedRow(jqTableDom)) {
			var row = zfesBstable.getSelections(jqTableDom);
			for (var i = 0; i < row.length; i++) {
				if (row[i].enabled == '1') {
					var warn = row[i].name + "角色已经是启用状态";
					alertSwal.warningTitle(warn);
					return false;
				}
			}
			var ids = zfesBstable.getRowIds(jqTableDom);
			alertSwal.confirm("启用", "是否启用角色", function() {
				var ajaxUrl = "authRole/enableBatchAuthRole";
				var param = {
					"ids" : ids.join(',')
				};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					setTimeout(function() {
						alertSwal.successText(data.message);
						zfesBstable.refresh(jqTableDom);
					}, 100);
				});
			});
		}
	}
</script>