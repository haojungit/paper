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
<link href="<%=basePath%>css/print-styles.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/icon.css">

<script src="<%=basePath%>jquery-easyui/jquery-1.8.0.js"></script>
<script src="<%=basePath%>jquery-easyui/jquery.easyui.min.js"></script>

<c:if test="${!empty teacher}">
	<input id="publicInfoTeacherId" type="hidden"
		value="${teacher.teacherId}" />
	<input id="publicInfoTitleState" type="hidden" />
</c:if>
<c:if test="${empty teacher}">
		登录超时,如果本页面不支持跳转请<a href='../login.jsp'>点击这里</a>
	<script>
		alert("登陆超时!");
		top.location.href = '../../../login.jsp';
	</script>
</c:if>
<tr>
	<td height="34" class="left5" colspan="6">论文（设计）题目：<select
		id="publicInfoSelect" class="easyui-combobox" name="state"
		data-options="editable:false" style="width: 240px;">
			<option selected>请选择一个题目</option>
	</select>
	</td>
</tr>
<tr height="34" class="print-tc">
	<td class="left5">学生姓名</td>
	<td><input id="publicInfoStudentName" type="text" disabled /></td>
	<td class="left5">学号</td>
	<td><input id="publicInfoStudentNumber" type="text" disabled /></td>
	<td class="left5">年 级</td>
	<td><input id="publicInfoStudentGrade" type="text" disabled /></td>
</tr>
<tr height="34" class="print-tc">
	<td class="left5">院 （系）</td>
	<td><input id="publicInfoStudentFaculty" type="text" disabled /></td>
	<td class="left5">专 业</td>
	<td><input id="publicInfoStudentMajor" type="text" disabled /></td>

	<td class="left5">指导教师</td>
	<td><input type="text" value="${teacher.teacherName}" disabled /></td>

</tr>