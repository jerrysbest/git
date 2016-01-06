<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.io.PrintWriter"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'error.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h2>Exception:${exception }</h2>
    <a href="javascript:document.getElementById('show').style.display='block';void(0);">详细信息</a>
    <div id="show" style="color:red;display:none;">
      <% Exception ex=(Exception)request.getAttribute("exception"); %>
      <%ex.printStackTrace(new PrintWriter(out)); %>
    </div>
  </body>
</html>
