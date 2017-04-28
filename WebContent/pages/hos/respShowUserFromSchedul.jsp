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
					<h2>该手术参与人员</h2>
				</header>

				<div>
					<div class="widget-body ">
						<table id="ahthUserBootstrapTable" data-mobile-responsive="true"></table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

<script>
	var jqTableDom1 = $("#ahthUserBootstrapTable");
	function initData() {
		var scheduleId=$("#scheduleId").val();
		var jqFormDom = $("#searchForm");
		var jqToolbarDom = $("#authUserTableToolbar");
		var url = "hosScheduling/showUserFromSchedul?scheduleId="+scheduleId;
		var columns = [ {
			field : 'userName',
			title : '姓名',
			width : 160
		},  {
			field : 'jobnumber',
			title : '工号',
			width : 160
		} ];
		bstable = zfesBstable.laodTableTwo(jqTableDom1, jqFormDom, jqToolbarDom,
				url, columns);
	}
	function attachModalHidden() {
		$("#schedulingModal").on('hide.bs.modal', function() {
			zfesBstable.refresh(jqTableDom1);
		});
	}
	$(function() {
		initData();
		//attachModalHidden();
	});

</script>