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
<base target="main">
<link href="css/skin.css" rel="stylesheet" type="text/css">
<link href="css/print-styles.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>admin_top</title>
</head>
<body leftmargin="0" topmargin="0" class="noprint">
	<table width="100%" height="64" border="0" cellpadding="0"
		cellspacing="0" class="admin_topbg">
		<tr>
			<td width="61%" height="64"><img src="images/logo.gif"
				width="262" height="64"></td>
			<td width="39%" valign="top"><table width="100%" border="0"
					cellspacing="0" cellpadding="0">
					<c:if test="${user!=null}">
						<tr>
							<td width="74%" height="38" class="admin_txt"><b> <c:if
										test="${user.identity==\"同学\"}">
							${student.studentName
									}</c:if> <c:if test="${user.identity!=\"同学\"}">
										${teacher.teacherName
									}
										</c:if>
							</b> ${user.identity} &nbsp;&nbsp;&nbsp;你好,欢迎登陆 ！&nbsp;&nbsp;&nbsp; <b><a
									style="color: #FFF; cursor: pointer"
									onclick="top.mainFrame.addPaperTab('修改个人资料','修改个人资料','amendingPersonalDetails.jsp')">修改个人资料</a></b><b>&nbsp;&nbsp;&nbsp;&nbsp;
									<a style="color: #FFF; cursor: pointer"
									onclick="top.mainFrame.addPaperTab('修改密码','修改密码','amendingPassword.jsp')">修改密码</a>
							</b><b><a style="color: #FFF; cursor: pointer">&nbsp;&nbsp;&nbsp; 我的消息</a></b></td>
							<td width="22%"><a href="struts/logout" target="_top"><img
									src="images/out.gif" alt="" width="46" height="20" border="0"></a></td>
							<td width="4%">&nbsp;</td>
						</tr>
					</c:if>
					<!-- 没用过滤器前的JS模拟控制 -->
					<c:if test="${empty user}">
					您还没有登录,如果本页面不支持跳转请<a href='../../login.jsp'>点击这里</a>
						<script>
							top.location.href = '../../login.jsp';
						</script>
					</c:if>
					<tr>
						<td height="19" colspan="3">&nbsp;</td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>
