<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/skin.css">
<script type="text/javascript"
	src="<%=basePath%>jquery-easyui/jquery-1.8.0.js"></script>
<script type="text/javascript"
	src="<%=basePath%>jquery-easyui/jquery.easyui.min.js"></script>

<body style="background-color: #EEF2FB;" class="noprint">
	<div class="easyui-accordion" style="width: 195px; height: auto;">
		<c:forEach items="${navi}" var="role">
			<div class="easyui-accordionTitleStyle" title="${role.key}"
				style="overflow: auto;">
				<c:forEach items="${role.value}" var="permission">
					<div class="easyui-accordionStyle">
						<a href="javascript:void(0)"
							onclick="top.mainFrame.addPaperTab('${permission.description}','${permission.description}','${permission.permission}')">${permission.description}</a>
					</div>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
</body>

<script>
	/* 	$(".panel-header").mouseover(function() {
	 $(this).css('background-color', '#a5cd4e;');
	 });
	 $(".panel-header").mouseout(function() {
	 $(this).css('background-color', '#4b4d51');
	 }); */
	$(".easyui-accordionStyle").mouseover(function() {
		$(this).css('background-color', '#efefef');
	});
	$(".easyui-accordionStyle").mouseout(function() {
		$(this).css('background-color', '#e5e5e5');
	});
</script>
</html>
