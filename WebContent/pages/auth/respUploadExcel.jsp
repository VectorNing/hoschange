<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<style>
.formupload {
	margin: 0 auto;
}

.formupload label {
	display: block;
	padding: 20px 103px;
	position: relative;
}

.myinputfile {
	position: absolute;
	width: 1px;
	height: 1px
}

.custorm-style {
	display: block;
	width: 390px;
	height: 50px;
}

.custorm-style .left-button {
	width: 80px;
	line-height: 50px;
	background: #6D6A69;
	color: #fff;
	display: block;
	text-align: center;
	float: left;
}

.custorm-style .right-text {
	width: 300px;
	height: 50px;
	line-height: 50px;
	display: block;
	float: left;
	padding: 4px;
	border: 1px solid #6D6A69;
	overflow: hidden;
	-ms-text-overflow: ellipsis;
	text-overflow: ellipsis;
	white-space: nowrap;
}
</style>
<script src="${pageContext.request.contextPath}/libs/js/jquery-form.js"></script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">导入Excel</h4>
</div>
<div class="modal-body no-padding">
<iframe id="download_hidden_frame" name="download_hidden_frame" style="display:none"></iframe>
	<form id="uploadFrom" class="formupload" action="${pageContext.request.contextPath}/authUser/uploadUserInfoExcel"  target="download_hidden_frame" enctype="multipart/form-data" method="post">
	<label for="fileupload">
         <input id="fileupload" class="inputForFile myinputfile" style="margin:50px auto;" name="fileupload" title="请选择要上传的Excel" type="file"> 
        <span class="custorm-style">
            <span class="left-button">上传</span>
            <span class="right-text" id="rightText"></span>
        </span>
    </label>
		<div  class="smart-form">
				<footer>
					<button type="submit" class="btn btn-primary">
						上传
					</button>
					<button type="button" class="btn btn-default close-dialog" data-dismiss="modal">
						取消
					</button>
				</footer>
		</div>
	</form>
</div>
<script type="text/javascript">
$(function () {
   var options = {
        success: function (data) {
        	$(".close-dialog").click();
        	if(data.message == "上传成功"){
        		alertSwal.successTitle(data.message);
        	}else{
        		alertSwal.warningTitle(data.message);
        	}
        	
        }
    };

    $("#uploadFrom").ajaxForm(options);
}); 
var fileBtn = $("#fileupload");
fileBtn.on("change", function(){
    var index = $(this).val().lastIndexOf("\\");
    var sFileName = $(this).val().substr((index+1));
    $("#rightText").html(sFileName);
});

</script>