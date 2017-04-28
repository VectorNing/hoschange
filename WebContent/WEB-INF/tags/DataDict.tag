<%@tag import="com.sesxh.hoschange.biz.sys.service.DictionaryService"%>
<%@tag pageEncoding="UTF-8"%>
<%@tag import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@tag import="org.springframework.context.ApplicationContext"%>
<%@ attribute name="type" required="true" rtexprvalue="true" description="字典代码"%>
<%@ attribute name="var" required="true" rtexprvalue="true" description="输出变量名"%>
<%
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
    DictionaryService dicService =  ctx.getBean(DictionaryService.class);
%>
<script type="text/javascript">
<%
String dicts="";
dicts = dicService.getDictByCode(type);
%>
  window.top.<%=var%> = <%=dicts%>;
</script>
