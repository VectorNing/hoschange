<%@ page contentType="text/html; charset=utf-8" language="java"%>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 物资管理 <span>> 手术排班查询 </span>
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
					<h2>手术排班列表</h2>
				</header>

				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
						<form id="searchForm" role="form" class="form-inline">

							<!--  <div class="form-group">
		                         <label>起始时间</label>
		                         <input name="shoeSize" type="text" placeholder="" class="form-control">
		                    </div>
		                    <div class="form-group">
		                         <label>结束时间</label>
		                         <input name="number" type="text" placeholder="" class="form-control">
		                    </div> -->
							<div class="form-group">
								<label>手术单号</label> <input name="operationNumber" type="text"
									placeholder="" class="form-control">
							</div>

							<div class="form-group">
								<label>手术室</label> <input name="name" type="text" placeholder=""
									class="form-control">
							</div>
							<button type="button" class="btn btn-default pull-right m-t-n-xs"
								onclick="search();">
								<strong>查询</strong>
							</button>
						</form>


						<hr>


						<div class="btn-group " id="schedulingTableToolbar" role="group">
							<button type="button" class="btn btn-default" onclick="add();">
								<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
								添加人员
							</button>
							<button type="button" class="btn btn-default" onclick="show();">
								<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
								该手术参与人员
							</button>
							<button type="button" id="selectAll" data-state="1"
								class="btn btn-default" onclick="selectAll();">历史排班查询</button>
							<!--  <button type="button" class="btn btn-default">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> 删除
                                  </button> -->
						</div>
						<table id="schedulingBootstrapTable" data-mobile-responsive="true"></table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

<!-- Dynamic Modal -->
<div class="modal fade" id="schedulingModal" tabindex="-1" role="dialog"
	aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" id="modal-content"></div>
	</div>
</div>



<script>
	var jqTableDom = $("#schedulingBootstrapTable");
	function initData(type) {
		var jqFormDom = $("#searchForm");
		var jqToolbarDom = $("#schedulingTableToolbar");
		var url = "hosScheduling/queryHosSchedulingSet?type="+type;	
		
		var columns = [ {
			checkbox : true
		}, {
			field : 'operationNumber',
			title : '手术单号',
			width : 160
		}, {
			field : 'name',
			title : '手术室',
			width : 160
		}, {
			field : 'operationtime',
			title : '手术时间',
			width : 160
		} ];
		bstable = zfesBstable.laodTable(jqTableDom, jqFormDom, jqToolbarDom,
				url, columns);
	}
	function attachModalHidden() {
		$("#schedulingModal").on('hide.bs.modal', function() {
			zfesBstable.refresh(jqTableDom);
		});
	}
	$(function() {
		initData(0);
		attachModalHidden();
	});

	function search() {
		zfesBstable.reload(jqTableDom);
	}
	function add() {
		if (zfesBstable.isOneRow(jqTableDom)) {
			var id = zfesBstable.getRowId(jqTableDom);
			$("#schedulingModal").modal({
				remote : "hosScheduling/enterAddUserToSchedul?id=" + id
			});
		}

	}
	function show() {
		if (zfesBstable.isOneRow(jqTableDom)) {
			var id = zfesBstable.getRowId(jqTableDom);
			$("#schedulingModal").modal({
				remote : "hosScheduling/enterShowUserFromSchedul?id=" + id
			});
		}

	}
	function selectAll(){
		if($("#selectAll").attr("data-state")==1){
			$("#enabled").val(null);
			$("#selectAll").html("当前排班查询");
			$("#selectAll").attr("data-state",0);
			initData(1);//查询当前
		}else{
			$("#enabled").val(1);
			$("#selectAll").html("历史排班查询");
			$("#selectAll").attr("data-state",1);
			initData(0);//查询历史
		}
		
	}
</script>