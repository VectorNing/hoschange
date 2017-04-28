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
<script>
	if (!window.jQuery) {
		document.write('<script src="js/libs/jquery-2.1.1.min.js"><\/script>');
	}
</script>
<script
	src="${pageContext.request.contextPath}/libs/js/libs/jquery-ui-1.10.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/libs/js/sweetalert/sweetalert.min.js"></script>
<!-- MAIN APP JS FILE -->
<script src="${pageContext.request.contextPath}/libs/js/app.config.js"></script>
<script src="${pageContext.request.contextPath}/js/zfes.alert.swal.js"></script>
<script src="${pageContext.request.contextPath}/libs/js/mespeak.js"></script>
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
	background: url(${pageContext.request.contextPath}/libs/img/img/bg3.png)
		no-repeat;
	background-attachment: fixed;
	background-size: cover;
}

.btn {
	width: 150px;
	height: 40px;
	border-radius: 5px;
	color: #009F5C;
	text-align: center;
	line-height: 40px;
	font-family: "微软雅黑";
	float: right;
	margin-top: 30px;
	margin-right: 50px;
	overflow: hidden;
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

#btn1, #btn2 {
	width: 150px;
	height: 40px;
	border-radius: 5px;
	color: #009F5C;
	text-align: center;
	line-height: 40px;
	font-family: "微软雅黑";
	border: 1px solid #009f5c;
}

#btn1 {
	position: absolute;
	bottom: 5px;
	right: 210px;
}

#btn2 {
	position: absolute;
	bottom: 5px;
	right: 50px;
}

#btn1:hover {
	border: 0px;
	background: #009F5C;
	color: #fff;
	cursor: pointer;
}

#btn2:hover {
	border: 0px;
	background: #009F5C;
	color: #fff;
	cursor: pointer;
}
</style>

</head>
<body>
	<%-- <div class="btn" id="btn" onclick="tempLogin2('2')">
		<img
			src="${pageContext.request.contextPath}/libs/img/img/maranger.png" />
		<p>管理员登陆</p>
	</div> --%>

	<!-- <div class="btn2" id="btn2" onclick="queryShoes()">
		<p>查询鞋信息</p>
	</div>

	<div class="btn1" id="btn1" onclick="tempLogin1('1')">
		<p>刷卡领取消毒鞋</p>

	</div> -->
	<audio id="xgbhMusicFir">
		<source
			src="${pageContext.request.contextPath}/libs/sound/xgbh_fir.mp3"
			type="audio/mp3">
	</audio>
	<audio id="xgbhMusicSec">
		<source
			src="${pageContext.request.contextPath}/libs/sound/xgbh_sec.mp3"
			type="audio/mp3">
	</audio>
	<audio id="xgbhMusicfal">
		<source
			src="${pageContext.request.contextPath}/libs/sound/xgbh_false.mp3"
			type="audio/mp3">
	</audio>
	<div id="xgNumPlay"></div>
	<!-- 模拟按钮成功 -->
	<button id="btnsksuc">刷卡成功</button>
	<!-- 模拟按钮失败 -->
	<button id="btnskfal">刷卡失败</button>
	<script type="text/javascript">
		var webSocket = new WebSocket('${webSocketServer}');
		webSocket.onopen = function(event) {
		}

		webSocket.onmessage = function(e) {
			var json = $.parseJSON(e.data);
			var method = json.Method;
			var parameter = json.Parameter;
			if ("WarningMessage" == method) {
				warningMessage(parameter);
			}
		}

		function warningMessage(parameter) {
			var message = parameter.ErrorMessage;
			var messageType = parameter.MessageType;
			alertSwal.simpleTitle(parameter.Operation);

		}

		var xgbh = "109";//鞋柜编号

		var timer = "";
		var theNumber = "";
		readcard();

		$(function() {

			//点击弹出指示信息及语音
			$("#btnsksuc")
					.click(
							function() {
								sweetAlert({
									title : "",
									text : "您的鞋柜编号为：<span id='streilizer' style='color: #4ac144;font-size:35px'><span>",
									type : "success",
									timer : 8000,
									confirmButtonColor : "#DD6B55",
									showConfirmButton : true,
									html : true
								});
								play(xgbh);
							});
			$("#btnskfal").click(function() {
				sweetAlert({
					title : "",
					text : "领取失败，请重新领取",
					type : "error",
					timer : 8000,
					confirmButtonColor : "#DD6B55",
					showConfirmButton : true,
					html : true
				});

				var audiofalse = document.getElementById("xgbhMusicfal");
				audiofalse.play();
			})
		});
		function play(xgbh) {
			$("#streilizer").html(xgbh);
			//开始播放第一段音频
			var audiof = document.getElementById("xgbhMusicFir");
			audiof.play();
			//第一段音频结束后执行报数方法
			audiof.addEventListener('ended', function() {
				readNum(xgbh);
			});
		}
		function readNum(str) {
			for (var i = 0; i < str.length; i++) {
				String1 = "<audio id='au" + i + "' type='audio/mp3'> <source src='${pageContext.request.contextPath}/libs/sound/";
				String1 += str[i] + ".wav'></source></audio>";
				$("#xgNumPlay").append(String1);

			}
			var e = $('#xgNumPlay').children().length - 1;

			var see = document.getElementById("au" + e);

			var audioo0 = document.getElementById("au0");
			var audioo1 = document.getElementById("au1");

			var audioo2 = document.getElementById("au2");
			audioo0.play();
			audioo0.addEventListener('ended', function() {
				audioo1.play();

			});
			see.addEventListener('ended', function() {
				readSec();

			});
			audioo1.addEventListener('ended', function() {
				audioo2.play();

			});

		}

		function readSec() {
			audios = document.getElementById("xgbhMusicSec");
			audios.play();
			$("#xgNumPlay").html("");
		}
	</script>
</body>
</html>