<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
<head>
<meta charset="utf-8">
<title>神思旭辉</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- #CSS Links -->
<!-- Basic Styles -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/libs/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/libs/css/style.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/libs/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/libs/css/smartadmin-production.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/libs/js/sweetalert/sweetalert.css">

<style type="text/css">
.main {
	position: absolute;
	top: 50%;
	left: 50%;
	width: 400px;
	height: 300px;
	margin-top: -150px;
	margin-left: -200px;
	text-align: center;
}

.main>a.btn {
	line-height: 140px;
	font-size: 40px;
}

.main>a.pwdLogin {
	position: fixed;
	bottom: 10px;
	right: 10px;
	padding: 10px;
}

body.signin {
	height: auto;
	background: url(../libs/img/login-background.jpg) no-repeat center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	color: rgba(255, 255, 255, .95)
}

#infoModal {
	position: absolute;
	width: 500px;
	height: 250px;
	background: rgba(100, 100, 255, 0.8);
	left: 50%;
	top: 50%;
	transform: translate(-150px, -100px);
	display: none;
}

#infoModal>p {
	text-align: center;
	font-size: 50px;
	line-height: 100px;
}

#infoModal>p>.dot {
	display: inline-block;
	font-size: 200px;
	line-height: 10px;
	margin: -20px auto;
}

#select_infoModal {
	position: absolute;
	width: 350px;
	height: auto;
	background: rgba(100, 100, 255, 0.8);
	left: 50%;
	top: 50%;
	transform: translate(-150px, -100px);
	display: none;
}

#select_infoModal>p {
	text-align: center;
	font-size: 30px;
	line-height: 100px;
}
</style>
</head>

<body class="signin">
	<header id="header">
		<div id="logo-group" style="width: 99%">
			<span id="logo"> <img
				src="${pageContext.request.contextPath}/libs/img/logo.png"
				alt="SmartAdmin"></span>
		</div>
	</header>
	<div class="main">

		<a class="btn btn-primary btn-rounded btn-block"
			href="javascript:login('SSXH-XG-001');"> 回收</a> <a
			class="btn btn-primary btn-rounded btn-block"
			href="javascript:login('SSXH-XG-002');"> 清理回收桶</a>

	</div>
	<div class="row">
		<!-- 		<img src="libs/img/index.jpg" class="img-responsive" alt="Cinque Terre" width="99%" height="500">  -->
	</div>
	<!-- Dynamic Modal -->
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="remoteModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

	<div class="modal fade" id="swing_modal" tabindex="-1" role="dialog"
		aria-labelledby="remoteModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<p>请刷卡</p>
			</div>
		</div>
	</div>


	<div id="infoModal">
		<p class="info">请刷卡</p>
		<p>
			<span class="dot">.</span>
		</p>
	</div>

	<div id="select_infoModal">
		<p class="info"></p>
	</div>
	<!-- BOOTSTRAP JS -->
	<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
	<script
		src="${pageContext.request.contextPath}/libs/js/libs/jquery-2.1.1.min.js"></script>
	<script>
		if (!window.jQuery) {
			document
					.write('<script src="js/libs/jquery-2.1.1.min.js"><\/script>');
		}
	</script>
	<script
		src="${pageContext.request.contextPath}/libs/js/libs/jquery-ui-1.10.3.min.js"></script>
	<script>
		if (!window.jQuery.ui) {
			document
					.write('<script src="js/libs/jquery-ui-1.10.3.min.js"><\/script>');
		}
	</script>
	<script
		src="${pageContext.request.contextPath}/libs/js/bootstrap/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/libs/js/sweetalert/sweetalert.min.js"></script>
	<!-- MAIN APP JS FILE -->
	<script
		src="${pageContext.request.contextPath}/libs/js/plugin/jquery-validate/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/libs/js/app.config.js"></script>
	<script src="${pageContext.request.contextPath}/js/zfes.alert.swal.js"></script>
	<script src="${pageContext.request.contextPath}/js/zfes.ajax.js"></script>
	<script src="${pageContext.request.contextPath}/js/zfes.core.js"></script>
	<script src="${pageContext.request.contextPath}/libs/js/app.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/md5-min.js"></script>

	<script type="text/javascript">
		function login(carNum) {
			//刷卡接口
			var recycleNum = carNum;
			//登录
			
			$.ajax({
				url : "${pageContext.request.contextPath}/touch/openDoor",
				type : 'POST',
				dataType : "json",
				data :{
					"machineNumber" : recycleNum
				},
				cache : false,
				success : function(data) {
					alert(data);
				},
				error : function(xhr, textStatus) {
					alertSwal.confirm("登录超时","是否重新登录",function(){window.location.href = "${pageContext.request.contextPath}/login.jsp"});
				},
			});
		}
		function recycle(data){
			var recycleNum = "SSXH-001";
			$.ajax({
				url : "${pageContext.request.contextPath}/touch/recordMachine",
				type : 'POST',
				dataType : "json",
				data :{
					"loginname" : data.loginname,
					"deviceNumber" : recycleNum,
					"type" : data.type
				},
				cache : false,
				success : function(data) {
					alert("3");
					alert(data);
				},
				error : function(xhr, textStatus) {
					alertSwal.confirm("登录超时","是否重新登录",function(){window.location.href = "${pageContext.request.contextPath}/login.jsp"});
				},
			});
		}
	</script>
</body>
</html>