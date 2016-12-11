<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="jquery-easyui/jquery-1.8.0.js"></script>
<link rel="stylesheet" href="css/style.css" type="text/css"
	media="screen" />
</head>
<body style="background-color: #EEF2FB;" class="noprint">
	<div style="width: 190px; margin: 0 auto;">
		<ul class="accordion">

			<c:forEach items="${user.paperUserRoles}" var="userRole">
				<%-- <c:if test="${userRole.paperRole.description == \"系统管理\" }"> --%>
				<li class="files"><a href="javascript:void(0)">${userRole.paperRole.description}</a>
					<c:if test="${userRole.paperRole.paperRolePermissions != null }">
						<ul class="sub-menu">
							<%
								int i = 0;
							%>
							<c:forEach items="${userRole.paperRole.paperRolePermissions}"
								var="rolePermission">
								<li><a href="javascript:void(0)"
									onclick="top.mainFrame.addPaperTab('${rolePermission.paperPermission.description}','${rolePermission.paperPermission.permission}')"><em><%=++i%></em>${rolePermission.paperPermission.description}</a></li>
							</c:forEach>
						</ul>
					</c:if></li>
			</c:forEach>
		</ul>
	</div>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							// Store variables
							var accordion_head = $('.accordion > li > a'), accordion_body = $('.accordion li > .sub-menu');
							// Open the first tab on load
							accordion_head.first().addClass('active').next()
									.slideDown('normal');
							// Click function
							accordion_head.bind('click', function(event) {
								// Disable header links
								event.preventDefault();
								// Show and hide the tabs on click
								if ($(this).attr('class') != 'active') {
									accordion_body.slideUp('normal');
									$(this).next().stop(true, true)
											.slideToggle('normal');
									accordion_head.removeClass('active');
									$(this).addClass('active');
								}
							});

						});
	</script>
</body>
</html>
