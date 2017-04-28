<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%-- <%@ include file="/commons/include/head.jspf"%> --%>
<html>
<head>
<title>错误页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
</head>
<body >
	<div >
	${param.ex}：<%=request.getParameter("ex")+"----" %>
	</div>
</body>
</html>
