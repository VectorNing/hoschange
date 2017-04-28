<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<t:DataDict type="sign" var="sign"></t:DataDict>
<t:DataDict type="device" var="device"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<style>
#theaterNumber{
display:inline-block !important;
margin-top:15px;
}
</style>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> 
				物资管理 
			<span>> 
				刷卡记录管理
			</span>
		</h1>
	</div>
</div>
<section id="widget-grid">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-editbutton="false">
			<!-- -->	<header>
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2>刷卡记录列表</h2>
				</header> 
			
				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
		                <form id="searchForm" role="form" class="form-inline">		                		                
								<div class="form-group">
								<div class="form-group">
									<label>姓名</label> <label class="select"> <input type="text" name="userName" class="form-control"/>
									</label>
								</div>
								<div class="form-group selectshs">
									<label  class="select" style="">手术室</label>
									<div  id="theater_Number" style="display:inline-block;vertical-align:middle;">
										<label class="select"> 
											<km-select host="baseServer"  url="hosTheater/loadTheByNumberForDict" id="theaterNumber" name="theaterNumber" label=""></km-select>
										</label>	
									</div>
								</div>
							</div>
		                       <input type="hidden" value="0" name="sign" id="enabled"><!-- 1代表有效 -->
		                    <button type="button"  class="btn btn-default pull-right m-t-n-xs" onclick="search();"><strong>查询</strong></button>
		                </form>
		                <hr>
		                
		                
					  	  <div class="btn-group " id="hosShoesTableToolbar" role="group">
                                 <button type="button" class="btn btn-default" onclick="edit();">
                                        <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 签退
                                 </button>
                                  <button type="button" class="btn btn-default" data-state="0" id="selectRecord" onclick="selectRecord();">
                                       <!--  <i class="glyphicon glyphicon-pencil" aria-hidden="true"> --></i> 查询所有
                                 </button>
                           </div>
							<table id="blackListRuleTable" data-mobile-responsive="true" ></table>
						</div>
					</div>
				</div>
		</article>
	</div>
</section>

<!-- Dynamic Modal -->  
<div class="modal fade" id="ruleModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">  
    <div class="modal-dialog">  
        <div class="modal-content" id="modal-content">
        	
        </div>  
    </div>  
</div>  



<script>

var jqTableDom=$("#blackListRuleTable");
function initData(){
	var jqFormDom=$("#searchForm");
	var jqToolbarDom=$("#hosShoesTableToolbar");
	var url="hosRecord/selectHosRecordSet";
	var columns=[
					{checkbox:true},
// 					{field:'id',title:'编号',width:80},
					{field:'userName',title:'姓名',width:120},
					{field:'signInTime',title:'签到时间',width:120},
					{field:'callbackTime',title:'签退时间',width:120},
					{field:'name',title:'手术区',width:120},
					{field:'sign',title:'状态',width:120,formatter:zfesUtil.signNoYes},
					//{field:'isCallback',title:'是否回收',width:50,formatter:zfesUtil.formatYesNo}
				];
	bstable=zfesBstable.laodTable(jqTableDom,jqFormDom,jqToolbarDom,url,columns);
}
function selectRecord(){
	if($("#selectRecord").attr("data-state")==0){
		$("#enabled").val(null);
		$("#selectRecord").html("查询未签退");
		$("#selectRecord").attr("data-state",1);
	}else{
		$("#enabled").val(0);
		$("#selectRecord").html("查询所有");
		$("#selectRecord").attr("data-state",0);
	}
	initData();
}
$(function(){
	initData();
	ZVue.init();
	attachModalHidden();
});
function attachModalHidden(){
	$("#ruleModal").on('hide.bs.modal', function () {
		zfesBstable.refresh(jqTableDom);
	});
}
function search(){
	zfesBstable.reload(jqTableDom);
}

function edit(){
	if(zfesBstable.isOneRow(jqTableDom)){
		var row = zfesBstable.getSelections(jqTableDom);
		var sign = row[0].sign;
		if(sign==1){
			alertSwal.warningText("当前已经签退成功，无需签退");
			return ;
		}
		var id = row[0].id;
		var isCallback = row[0].isCallback;
		var userId = row[0].userId;
		var theaterNumber = row[0].theaterNumber;
		var url="hosRecord/updateHosRecordService";
		var params = {"id":id,"isCallback":isCallback,"userId":userId,"theaterNumber":theaterNumber};
		zfesAjax.ajaxTodo(url, params, function(data) {
			alertSwal.successText(data.message);
			zfesBstable.refresh(jqTableDom);
		});
	}
}
</script>