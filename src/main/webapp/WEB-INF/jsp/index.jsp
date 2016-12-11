<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
</head>
<frameset rows="64,*" frameborder="NO" border="0" framespacing="0">
	<frame src="/top" noresize="noresize" frameborder="NO"
           name="topFrame" scrolling="no" marginwidth="0" marginheight="0"
           target="main" />
	<frameset cols="200,*" rows="860,*" id="frame">
		<frame src="/left" id="leftFrame" name="leftFrame"
               noresize="noresize" marginwidth="0" scrolling="auto" marginheight="0"
               frameborder="0" scrolling="no" target="main" />
		<frame src="/right" name="mainFrame" marginwidth="0"
               marginheight="0" frameborder="0" scrolling="auto" target="_self" />
	</frameset>
</frameset>
<body>
</body>
</html>