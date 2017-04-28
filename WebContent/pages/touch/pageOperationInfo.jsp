<%@ page contentType="text/html; charset=utf-8" language="java"%>
<script src="../../libs/js/jquery.min.js"></script>
<script src="../../libs/js/jquery.SuperSlide.2.1.1.js"></script><!DOCTYPE html>
<%
	String id = request.getParameter("id");
%>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>手术室自助更衣系统黑名单</title>
		<script type="text/javascript" src="jquery.2.2.4.min.js"></script>

		<style type="text/css">
			html,
			body {
				width: 100%;
				height: 100%;
				overflow: hidden;
			}
			
			* {
				padding: 0;
				margin: 0;
			}
			
			article,
			aside,
			details,
			figcaption,
			figure,
			footer,
			header,
			hgroup,
			main,
			nav,
			section,
			summary {
				display: block;
			}
			
			audio,
			canvas,
			video {
				display: inline-block;
			}
			
			audio:not([controls]) {
				display: none;
				height: 0;
			}
			
			li {
				list-style-type: none;
			}
			
			body {
				background: url(../../libs/img/0.png) no-repeat scroll 0 0;
				background-size: cover;
			}
			
			.header {
				width: 100%;
				height: 10%;
				text-align: center;
			}
			
			.header h1 {
				font-family: 华文行楷;
				margin-top: 3%;
				font-size: 6vw;
				word-spacing: 8px;
				display: block;
				height: 100%;
				font-weight: 100;
				color: #4d5151;
				;
			}
			
			.header h1 span {
				font-family: Microsoft YaHei;
			}
			
			.marquee_content {
				height: 67%;
				margin: 1vw auto;
				width: 90%;
				font-size: 100%;
				margin-top: 8%;
				overflow: hidden;
			}
			
			.marquee_content_header {
				height: 6%;
				width: 100%;
			}
			
			.marquee_content_header li {
				float: left;
				width: 50%;
				height: 155%;
				font-size: 2vw;
			}
			
			.txtMarquee {
				height: 100%;
				width: 62%;
				margin: 0 auto;
				position: relative;
			}
			
			.tempWrap {
				width: 100%;
				height: 98% !important;
			}
						
			.xxblock {
				width: 38%;
				margin-top: 11%;
				float: left;
				text-align: center;
				border: 0.1vh #a8a2a2 dashed;
				position: relative;
			}
			
			.tp img {
				height: 94%;
				width: 89%;
				vertical-align: middle;
			}
			
			.xxblock>div>span {
				font-family: 华文行楷;
				color: #5f5c5c;
				;
				font-size: 3vw;
				font-weight: 100;
				vertical-align: middle;
			}
			
			.xxRight {
				float: right;
			}
			
			.xxblock>div {
				width: 95%;
				margin: 1vw auto;
				margin-top: 9vw;
			}
			
			.xxblock>div>p {
				font-size: 4vw;
				color: #5f5c5c;
			}
			
			.tp {
				height: 37%;
				width: 24%;
				background-color: #04AC91;
				border: solid 0.1vh #04AC91;
				border-radius: 50%;
				position: absolute;
				top: 0%;
				left: 69%;
				text-align: center;
				z-index: 2;
			}
			
			.tpleft {
				background-color: #16C2A6;
				border-color: #16C2A6;
				left: 7%;
			}
			
			#patientName {
				color: #16C2A6;
			}
			
			#patientState {
				color: #04AC91;
			}
		</style>
	</head>

	<body>
		<header class="header">
			<h1><span>xx</span>医院手术信息</h1>
		</header>
		<section class="marquee_content">

			<div class="txtMarquee">
				<div class="tp tpleft">
					<img src="../../libs/img/renw.png" />
				</div>
				<section class="xxblock xxLeft">
					<div>

						<span>病人姓名</span>
						<p id="patientName"></p>
					</div>
				</section>
				<div class="tp">
					<img src="../../libs/img/zhuangt.png" />
				</div>
				<section class="xxblock xxRight">

					<div>

						<span>手术状态</span>
						<p id="patientState"></p>
					</div>
				</section>
			</div>

			</div>
		</section>

	</body>

</html>
<script type="text/javascript">
	<%-- $(function() {
		var operationId = <%=id%>;
		alert(operationId);
		var url = "${pageContext.request.contextPath}/touch/queryOperationInfo";
		/* $.getJSON(url,function(data){
			$("#patientName").html(data.patientname);
			$("#patientState").html(data.patientstate);
		}); */
	}); --%>
	function myrefresh(){
		window.location.reload();
	}
	setInterval(function(){myrefresh()},1200000); //指定20分钟刷新一次 
</script>