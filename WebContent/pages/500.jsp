<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title>服务器错误</title>
<style type="text/css" media="screen">
body {
	background-color: #f1f1f1;
	margin: 0;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

.container {
	margin: 50px auto 40px auto;
	width: 600px;
	text-align: center;
}

h1 {
	width: 800px;
	position: relative;
	left: -100px;
	letter-spacing: -1px;
	line-height: 60px;
	font-size: 60px;
	font-weight: 100;
	margin: 0px 0 50px 0;
	text-shadow: 0 1px 0 #fff;
}

p {
	color: rgba(0, 0, 0, 0.5);
	margin: 20px 0;
	line-height: 1.6;
}
</style>
</head>
<body>
	<div class="container">
		<h1>500</h1>
		<p>
			<strong>出错了</strong>
		</p>
		<div style="display: none;">
	</div>
	</div>
</body>
</html>
