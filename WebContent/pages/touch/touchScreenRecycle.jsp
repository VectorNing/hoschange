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
			href="javascript:login('123456789');"> 回收</a> <a
			class="btn btn-primary btn-rounded btn-block"
			href="javascript:login('1234567');"> 清理回收桶</a>

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
			var recycleNum = "SSXH-001";
			//登录
			var loginname = carNum;
			var ajaxUrl = "${pageContext.request.contextPath}/touch/login";
			var param = {
				"loginname" : $.trim(loginname)
			};
			zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
				var type = data.strData;//人员类别 
				if (type == 0) {
					callbackSeriesTimes(carNum, recycleNum);
				} else {
					emptyRecycle(recycleNum);
				}
			});
		}

		function callbackSeriesTimes(carNum, recycleNum) {
			//硬件带回手术室id 设备编号 和 用户身份证号或id 
			//	var theaterNumber="SSS-001";//更衣室
			//	 		var warNumber="SSXH-YG-001";
			var url = "${pageContext.request.contextPath}/touch/callbackSeriesTimes";
			var param = {
				recycleNumber : recycleNum,
				deviceType : 3,//3是回收桶
				cardNum : '123456789'
			};
			zfesAjax.ajaxTodo(url, param, function(data) {
				console.log(data);
				var message = "";
				var isOrNoShoe = data.isOrNoShoe;//在设备签到签退  1：衣柜 2 鞋柜
				var sign = data.sign;//当前状态 0 签到(下一步-签退)，1签退(下一步-签到)
				var recordId = data.recordId;//刷卡id 用于签退
				if (isOrNoShoe == 2) {//如果有鞋柜，回收
					if (sign == 1) {//当前状态 0 签到(下一步-回收)，1签退
						//没有领取，不操作
						alertSwal.warning("没有签到");
					} else {
						//回收
						recoverySign(carNum, recycleNum);
					}
				} else {//没有鞋柜， 签退并回收
					if (sign == 1) {//当前状态 0 签到(下一步-签退回收)，1签退
						//没有领取
						alertSwal.warning("没有签到");
					} else {
						//签退并回收
						signOutRecovery(carNum, recordId, recycleNum);
					}
				}
			});
		}

		//回收手术衣
		function recoverySign(cardNum, recycleNum) {
			//调用回收单元 感应接口，是否感应到衣物回收

			var recoveryState = 1;//是否回收 0:未回收 1:已回收
			var url = "${pageContext.request.contextPath}/touch/recoverySign";
			var param = {
				cardNum : cardNum,
				state : recoveryState,
				recycleNum : recycleNum
			};
			zfesAjax.ajaxTodo(url, param, function(data) {
				//playqtts();
				alertSwal.success(data.message);
			});
		}

		//回收并且签退
		function signOutRecovery(carNum, recordId, recycleNum) {
			//调用回收桶 感应接口，是否感应到衣物回收

			var recoveryState = 1;//是否回收 0:未回收 1:已回收
			var url = "${pageContext.request.contextPath}/touch/signOutRecovery";
			var param = {
				recordId : recordId,
				cardNum : carNum,
				state : recoveryState,
				recycleNum : recycleNum
			};
			zfesAjax.ajaxTodo(url, param, function(data) {
				alertSwal.success(data.message);
				//setTimeout(showInfo(data.message), 1000);
				//setTimeout(closeInfo, 5000);
			});
			//连接设备时再次调用刷卡
			//				readcard();
		}

		function emptyRecycle(recycleNum) {
			var url = "${pageContext.request.contextPath}/hosRecycle/emptyRecycle";
			var param = {
				recycleNum : recycleNum
			};
			zfesAjax.ajaxTodo(url, param, function(data) {
				alertSwal.success(data.message);
			});
		}
	</script>
</body>
</html>