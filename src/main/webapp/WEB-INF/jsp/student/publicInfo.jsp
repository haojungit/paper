<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link href="<%=basePath%>css/skin.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/print-styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/icon.css">

<script src="<%=basePath%>jquery-easyui/jquery-1.8.0.js"></script>
<script src="<%=basePath%>jquery-easyui/jquery.easyui.min.js"></script>

<tr>
	<td colspan="4" class="left5" style="height: 40px;">论文（设计）题目：<a
		style="font-weight: bold; font-size: 15px;"
		id="studentPublicInfoTitleName"></a> <input type="hidden"
		id="studentPublicInfoStudentId" value="${student.studentId}" /> <input
		type="hidden" id="studentPublicInfoTitleId" /><input
		type="hidden" id="studentPublicInfoTitleState" />
	</td>
</tr>

<c:if test="${!empty student}">
	<tr>
		<td width="25%" class="left5">学生姓名</td>
		<td width="25%" class="left5"><input type="text"
			value="${student.studentName}" disabled="disabled" /></td>
		<td width="25%" class="left5">院（系）</td>
		<td width="25%" class="left5"><input type="text"
			value="${student.studentFaculty}" disabled="disabled" /></td>
	</tr>
	<tr>
		<td class="left5">专业</td>
		<td><input type="text" disabled="disabled"
			value="${student.studentMajor}" /></td>
		<td class="left5">年级</td>
		<td><input type="text" disabled="disabled"
			value="${student.studentGrade}" /></td>
	</tr>
	<tr>
		<td class="left5">学号</td>
		<td><input type="text" disabled="disabled"
			value="${student.studentNumber}" /></td>
		<td class="left5">指导教师姓名</td>
		<td><input type="text" disabled="disabled"
			id="studentPublicInfoTeacherName" /></td>
	</tr>

</c:if>
<c:if test="${empty student}">
			登录超时,如果本页面不支持跳转请<a href='../login.jsp'>点击这里</a>
	<script>
		top.location.href = '../../../login.jsp';
	</script>
</c:if>