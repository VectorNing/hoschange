<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>神思旭辉</title>
<!-- BOOTSTRAP JS -->
<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
<script
	src="${pageContext.request.contextPath}/libs/js/libs/jquery-2.1.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/libs/js/libs/jquery-ui-1.10.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/libs/js/sweetalert/sweetalert.min.js"></script>
<!-- MAIN APP JS FILE -->
<script src="${pageContext.request.contextPath}/libs/js/app.config.js"></script>
<script src="${pageContext.request.contextPath}/js/zfes.alert.swal.js"></script>
<script src="${pageContext.request.contextPath}/js/zfes.ajax.js"></script>
<script src="${pageContext.request.contextPath}/js/zfes.core.js"></script>
<script src="${pageContext.request.contextPath}/libs/js/app.min.js"></script>
<script src="${pageContext.request.contextPath}/js/md5-min.js"></script>
<script
	src="${pageContext.request.contextPath}/libs/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/libs/js/sweetalert/sweetalert.css">

<style>
* {
	margin: 0px;
	padding: 0px;
}

body {
	background: url(${pageContext.request.contextPath}/libs/img/img/bg1.png)
		no-repeat;
	background-attachment: fixed;
	background-size: cover;
	overflow: hidden;
}

.btn {
	width: 150px;
	height: 40px;
	border-radius: 5px;
	color: #009F5C;
	text-align: center;
	line-height: 40px;
	font-family: "微软雅黑";
	overflow: hidden;
	float: right;
	margin-top: 30px;
	margin-right: 50px;
}

.btn:hover {
	border: 1px solid #009F5C;
	cursor: pointer;
}

.btn img {
	float: left;
	margin-left: 10px;
	margin-top: 4px;
}

.btn p {
	float: right;
	margin-right: 20px;
}

.num {
	font-family: "微软雅黑";
	font-size: 16px;
	position: absolute;
	bottom: 5px;
	left: 40px;
}

#queryOpe:hover {
	border: 0px;
	background: #009F5C;
	color: #fff;
	cursor: pointer;
}

.queryOpe {
	font-family: "微软雅黑";
	font-size: 16px;
	position: absolute;
	bottom: 5px;
	right: 40px;
	cursor: pointer;
	width: 150px;
	height: 40px;
	border-radius: 5px;
	color: #009F5C;
	text-align: center;
	line-height: 40px;
	font-family: "微软雅黑";
	border: 1px solid #009f5c;
}
</style>
</head>
<body>
	<%-- <div class="btn" onclick="tempLogin1()">
		<img
			src="${pageContext.request.contextPath}/libs/img/img/maranger.png" />
		<!--  <img alt="" src="${pageContext.request.contextPath}/libs/img/logo.png">  -->
		<p>管理员登陆</p>
	</div> --%>
	<!-- <div class="num" onclick="tempLogin()" id="warNumber">
		<p>手术衣设备编号：SSXH-YG-001</p>
	</div> -->
	<!-- <div class="queryOpe" onclick="queryOpe()" id="queryOpe">查询衣柜信息</div> -->
</body>


<script type="text/javascript">
var webSocket = new WebSocket('${webSocketServer}');
webSocket.onopen = function(event) {}

webSocket.onmessage = function(e) {
	var json=$.parseJSON(e.data);
	var method=json.Method;
	var parameter=json.Parameter;
	if("ChangePage"==method){
		changePage(parameter);
	}
	if("WarningMessage"==method){
		warningMessage(parameter);
	}
}
function changePage(parameter){
	var deviceID=parameter.DeviceID;
	window.location.href = "${pageContext.request.contextPath}/touch/allotWardrobe?warNumber="
		+ deviceID;
}

function warningMessage(parameter){
	var message=parameter.ErrorMessage;
	alertSwal.simpleTitle(message);
}

</script>
</html>