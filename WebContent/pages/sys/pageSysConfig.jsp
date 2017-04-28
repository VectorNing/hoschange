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
			<i class="fa fa-table fa-fw "></i> 
				物资管理
			<span>> 
				回收单元管理
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
					<h2>系统配置</h2>
				</header> 
			
				<div>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body ">
					<form id="searchForm" role="form" class="form-inline">
					
		                </form>
		                
		                
		                <hr>
		                
		                
					  	  <div class="btn-group " id="sysConfigTableToolbar" role="group">
                                <button type="button" class="btn btn-default" onclick="edit();">
                                        <i class="glyphicon glyphicon-wrench" aria-hidden="true"></i> 修改
                                 </button>
                           </div>
							<table id="sysConfigBootstrapTable" data-mobile-responsive="true" ></table>
						</div>
					</div>
				</div>
		</article>
	</div>
</section>

<div class="modal fade" id="sysConfigModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">  
    <div class="modal-dialog">  
        <div class="modal-content" id="modal-content" >
        	
        </div>  
    </div>  
</div>  

<script>

var jqTableDom=$("#sysConfigBootstrapTable");
function initData(){
	var jqFormDom=$("#searchForm");
	var jqToolbarDom=$("#sysConfigTableToolbar");
	var url="sys/querySysConfig";
	var columns=[
		{checkbox:true,width:10},
		{field:'name',title:'名字',width:600/* ,sortable:true */},
		{field:'nowConfig',title:'配置 ',width:600 ,formatter:zfesUtil.formatConfig },
		{field:'description',title:'描述 ',width:600 },
	//	{field:'configName',title:'configName ',width:600 },
	];
	bstable=zfesBstable.laodTable(jqTableDom,jqFormDom,jqToolbarDom,url,columns);
}


$(function(){
	initData();
});

function search(){
	zfesBstable.reload(jqTableDom);
}

function edit(){
	if(zfesBstable.isOneRow(jqTableDom)){
		 var id=zfesBstable.getRowId(jqTableDom);
		 $("#sysConfigModal").modal({
			    remote: "sys/enterEditSysConfig?id="+id
			});
	 }
}

</script>