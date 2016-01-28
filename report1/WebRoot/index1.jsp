<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
  <!-- Page banner -->
		<TABLE class=banner-area cellSpacing=0 cellPadding=0 width="100%" border=0>
			<TBODY>
				<TR>
					<TD width=115><a href="http://www.eclipse.org/">
						<IMG src="webcontent/birt/images/EclipseBannerPic.jpg" alt="Eclipse Logo" width="115" height="50" border=0></a>
					</TD>
					<TD>
						<IMG src="webcontent/birt/images/gradient.jpg" alt="gradient banner" width="300" height="50" border=0>
					</TD>
					<TD vAlign=center align=right width=250>
						<a class="birt" href="http://www.eclipse.org/birt">
							<!-- Temporary BIRT header -->
							<SPAN style="PADDING-RIGHT: 10px; FONT-WEIGHT: bold; FONT-SIZE: 20px; COLOR: #e8e8ff; FONT-FAMILY: arial, sans-serif">
								BIRT
							</SPAN>
						</a> 
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<!-- Table with menu & content -->
		<TABLE cellSpacing=0 cols=2 cellPadding=0 border=0 width="100%">
			<TBODY>
				<TR>
					<TD class=content style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px" >
						<!-- Page title -->
						<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
							<TBODY>
								<TR>
									<TD vAlign=top>
										<span class="indextop">BIRT viewer has been installed.</span><p>&nbsp;
									</TD>
									<TD class=jump style="PADDING-LEFT: 10px" align=right rowSpan=2>
										<IMG src="webcontent/birt/images/Idea.jpg" alt="Idea" width="120" height="86">
									</TD>
								</TR>
								<TR>
									<TD>&nbsp;</TD>
								</TR>
							</TBODY>
						</TABLE>
						<!-- Content area -->
						<p>Thank you for your choosing BIRT (Business Intelligence Reporting Tool).</p>

						<p><a href="<%= request.getContextPath( ) + "/frameset?__report=qm_report_DA_HZF.rptdesign" %>">HZF-DA</a></P>
                                                <p><a href="<%= request.getContextPath( ) + "/frameset?__report=qm_report_DA_HZF.rptdesign" %>">HZF-DL</a></P>
                                                <p><a href="<%= request.getContextPath( ) + "/frameset?__report=qm_report_DA_HZF.rptdesign" %>">HZF-FA</a></P>
                                                <p><a href="<%= request.getContextPath( ) + "/frameset?__report=qm_report_HZS.rptdesign" %>">HZS</a></P>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
  </body>
</html>
