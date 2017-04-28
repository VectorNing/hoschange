<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
<head>
<meta charset="utf-8">
<title>神思旭辉</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- #CSS Links -->
<!-- Basic Styles -->
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/libs/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/libs/css/style.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/libs/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/libs/css/smartadmin-production.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/libs/js/sweetalert/sweetalert.css">
<style type="text/css">
.main{
    position:absolute;
    top:50%;
    left:50%;
    width:400px;
    height:160px;
    margin-top:-150px;
    margin-left:-200px;

    text-align:center;
}
.main>a.btn{
	line-height:140px;
    font-size:40px;
}
.main>a.pwdLogin{
	position: fixed;
	bottom: 10px;
	right: 10px;
	padding: 10px;
}
body.signin{height:auto;background:url(../libs/img/login-background.jpg) no-repeat center fixed;-webkit-background-size:cover;-moz-background-size:cover;-o-background-size:cover;background-size:cover;color:rgba(255,255,255,.95)}

#infoModal{
	position:absolute;
	width: 300px;
	height: 200px;
	background: rgba(100,100,255,0.8);
	left: 50%;
	top:50%;
	transform: translate(-150px,-100px);
	display:none;
}
#infoModal>p{
	text-align:center;
	font-size: 50px;
	line-height: 100px;
}
#infoModal>p>.dot{
	display:inline-block;
	font-size: 200px;
	line-height: 10px;
	margin:-20px auto;
	
}
label {
	color: black;
}
.refresh{
	position: fixed;
	bottom: 10px;
	left: 10px;
	padding: 10px;
}
</style>
</head>

<body class="signin">
<div class="main">
		  <a class="btn-primary refresh" href="javascript:refresh();"> 刷新</a>
	</div>
<div class="modal fade" id="WriteGuidModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true" >  
    <div class="modal-dialog">  
        <div class="modal-content">
        	<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="mlabel" style="color: black;">添加手术衣柜编号</h4>
				<div class="modal-body">
					<section>
						<div class="row">
							<label class="col-sm-2 control-label" style="margin-top: 5px;">编号</label>
							<div class="col col-10">
								<label class="input"> 
									<input type="text" name="number" value="" id="warNumber" class="form-control">
								</label>
							</div>
						</div>
					</section>
				</div>
				
				<div class="modal-footer">
						<button id="addBtn" type="button" class="btn btn-primary" onclick="addWarNumber()">添加</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
			</div>
        </div>  
    </div>  
</div> 

<!-- BOOTSTRAP JS -->		
<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
<script src="${pageContext.request.contextPath}/libs/js/libs/jquery-2.1.1.min.js"></script>
<script> if (!window.jQuery) { document.write('<script src="js/libs/jquery-2.1.1.min.js"><\/script>');} </script>
<script src="${pageContext.request.contextPath}/libs/js/libs/jquery-ui-1.10.3.min.js"></script>
<script> if (!window.jQuery.ui) { document.write('<script src="js/libs/jquery-ui-1.10.3.min.js"><\/script>');} </script>
<script src="${pageContext.request.contextPath}/libs/js/bootstrap/bootstrap.min.js"></script>
<!-- JQUERY VALIDATE -->
<script src="${pageContext.request.contextPath}/libs/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<!-- MAIN APP JS FILE -->
<script src="${pageContext.request.contextPath}/libs/js/plugin/pace/pace.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/js/app.config.js"></script> 
<script src="${pageContext.request.contextPath}/libs/js/app.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/js/sweetalert/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/js/zfes.alert.swal.js"></script>
<script src="${pageContext.request.contextPath}/js/zfes.ajax.js"></script>
<script src="${pageContext.request.contextPath}/js/zfes.core.js"></script>
<script src="${pageContext.request.contextPath}/js/md5-min.js"></script>
<script src="${pageContext.request.contextPath}/pages/touch/changeclothesapi.js"></script>
<script type="text/javascript">
  
  $(function(){
	  loadWarNumber();
  });
  
  function loadWarNumber(){
// 	  var warNumber = callApi("ReadGuid");
	  warNumber="SSXH-YG-001";
// 	  warNumber="";
	  if(warNumber == null || warNumber == ""){
		  
		  $("#WriteGuidModal").modal('show');
	  }else{
		  window.location.href="${pageContext.request.contextPath}/touch/enterTouchScreenWardorbe?warNumber="+warNumber;
	  }
  }
 	 $("#WriteGuidModal").on("hidden.bs.modal", function() {
 	    $(this).removeData("bs.modal");
 	});
  function addWarNumber(){
	  var warNumber = $("#warNumber").val();
	  callApi("WriteGuid",warNumber);
      $("#warNumber").val("");
 	  $("#WriteGuidModal").modal('hide');
	  setTimeout(function(){
	 		loadWarNumber();
	  	},2000);
  }
  
  function refresh(){
	  window.location.href="${pageContext.request.contextPath}/touch/touchWardorbeGoIndex";
  }
</script>
</body>
</html>