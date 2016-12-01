<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui/themes/icon.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=basePath%>jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/msg.js"></script>
<script src="<%=basePath%>dwr/interface/msgService.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#bt1").click(function(){
		var sender = $("#t1").val();
		var receiver = $("#t2").val();
		var msgText = $("#t3").val();
		sendto(receiver,msgText);
	});
	
});
</script>

<body>
发送者用户名：<input type="text" id="t1" value="${user.userName}"/><br><br>
接收者用户名：<input type="text" id="t2"/><br><br>
发送内容：<input type="text" id="t3"><br><br>
<input id="bt1" type="button" value="发送"/>
</body>
</html>

