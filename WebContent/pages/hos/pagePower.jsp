<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>

</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 物资管理 <span>> 手术权限管理 </span>
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
					<h2>手术权限列表</h2>
				</header>

				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
						<form id="searchForm" role="form" class="form-inline">
							<div class="form-group">
								<label>姓名</label> <input name="number" type="text"
									placeholder="" class="form-control">
							</div>
							<div class="form-group">
								<label>开始时间</label> <input id="startselecttime" name="begintime"
									type="text" placeholder="" class="form-control laydate-icon">
							</div>
							<div class="form-group">
								<label>结束时间</label> <input id="endselecttime" name="endtime"
									type="text" placeholder="" class="form-control laydate-icon">
							</div>
							<button type="button" class="btn btn-default pull-right m-t-n-xs"
								onclick="search();">
								<strong>查询</strong>
							</button>
						</form>


						<hr>


						<div class="btn-group " id="powerTableToolbar" role="group">
							<button type="button" class="btn btn-default" onclick="gentai();">
								<i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 添加跟台
							</button>

							<button type="button" class="btn btn-default" onclick="linshi();">
								<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
								添加临时权限
							</button>
						</div>
						<table id="powerBootstrapTable" data-mobile-responsive="true"></table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

<!-- Dynamic pagePowerModal Modal -->
<div class="modal fade" id="pagePowerModal" tabindex="-1" role="dialog"
	aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="max-width: 550px;">
		<div class="modal-content"></div>
	</div>
</div>



<script>
$('#pagePowerModal').on('hide.bs.modal', function () {
	location.reload();
	});
	//开始时间、结束时间限制
	var maxt='2999-06-16 00:00:00';
var mint='2010-00-00 00:00:00';
var start = {
		elem: '#startselecttime',
		format: 'YYYY-MM-DD hh:mm:ss',
		min: mint, //设定最小日期为当前日期
		max: maxt, //最大日期
		istime: true,
		istoday: false,
		choose: function(datas) {
			end.min = datas; //开始日选好后，重置结束日的最小日期						
		}
	};
	var end = {
			elem: '#endselecttime',
			format: 'YYYY-MM-DD hh:mm:ss',
			min: mint, 
			max: maxt,
			istime: true,
			istoday: false,
			choose: function(datas) {
			start.max=datas;
				start.end = datas; //结束日选好后，重置开始日的最大日期
			}
	};
	 var jqTableDom = $("#powerBootstrapTable");
	function initData() {
		var jqFormDom = $("#searchForm");
		var jqToolbarDom = $("#powerTableToolbar");
		var url = "hosPower/queryHosPowerSet";
		var columns = [ {
			checkbox : true
		}, {
			field : 'username',
			title : '姓名',
			width : 160,
			sortable : true
		}, {
			field : 'jobnumber',
			title : '工号',
			width : 160
		}, {
			field : 'begintime',
			title : '起始时间',
			width : 160
		}, {
			field : 'endtime',
			title : '结束时间',
			width : 160
		}, {
			field : 'roomname',
			title : '手术室名称',
			width : 160
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
		$("#pagePowerModal").on('hide.bs.modal', function() {
			zfesBstable.refresh(jqTableDom);
		});
		
	} 
	
	
	$(function() {
		 initData();
		attachModalHidden(); 
		$("#startselecttime").click(function(){
			laydate(start);	
		});
		setInterval(function(){is();},1000);
		function is(){
			if (($("#startselecttime").val()=='')&&($("#endselecttime").val()=='')) {
				start.choose(mint);end.choose(maxt);
			}
		}
		$("#endselecttime").click(function(){
			laydate(end);	
		});
		
		
	});

	 function search() {
		zfesBstable.reload(jqTableDom);
	}

	function gentai() {
		var url = "${pageContext.request.contextPath}/pages/hos/modeOne/pagePowerAdd.jsp";
		$("#pagePowerModal").modal({
			remote : url
		});
	}
	function linshi() {
		var url = "${pageContext.request.contextPath}/pages/hos/modeOne/pagePowerAddTemporaries.jsp";
		$("#pagePowerModal").modal({
			remote : url
		});
	} 	 
</script>