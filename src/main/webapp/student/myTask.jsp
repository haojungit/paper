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
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/icon.css">

<script src="<%=basePath%>jquery-easyui/jquery-1.8.0.js"></script>
<script src="<%=basePath%>jquery-easyui/jquery.easyui.min.js"></script>

<script src='<%=basePath%>dwr/interface/paperPlan.js'></script>
<script src='<%=basePath%>dwr/interface/titleDWR.js'></script>
<script src='<%=basePath%>dwr/interface/writingTask.js'></script>
<script src='<%=basePath%>dwr/engine.js'></script>
<script src='<%=basePath%>dwr/util.js'></script>

<script src="<%=basePath%>js/public.js"></script>
<script src="<%=basePath%>js/studentTask.js"></script>

<jsp:include page="../commonalityPage_top.jsp"></jsp:include>

<td valign="top" bgcolor="#F7F8F9" width="100%">
	<h3 class="title">
		大庆师范学院<br />本科生毕业论文（设计）任务书
	</h3>
	<table border="1" WIDTH="100%" BORDERCOLORLIGHT="#b1c9ff"
		BORDERCOLORDARK="#b1c9ff">
		
		<jsp:include page="publicInfo.jsp" />

		<tr height="34">
			<td colspan="6" class="left5">任务起止日期:&nbsp;&nbsp;&nbsp;<a
				style="color: red;" id="writingTaskStrartTime"></a>&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;
				<a style="color: red;" id="writingTaskEndTime"></a>
			</td>
		</tr>
		<tr>
			<td colspan="6" class="left15">论文（设计）的主要内容与要求：<br /> <br /> <a
				style="color: red; margin-left: 20px;" id="writingTaskReference"></a><br />
				<br />
			</td>
		</tr>
		<tr>
			<td colspan="6" class="left15">主要参考文献：<br /> <br /> <a
				style="color: red; margin-left: 20px;" id="writingTaskContent"></a>
				<input type="hidden" id="writingTaskID"></input> <br /> <br />
			</td>
		</tr>
	</table>
	<table id="planTask" border="1" WIDTH="100%" BORDERCOLORLIGHT="#b1c9ff"
		BORDERCOLORDARK="#b1c9ff" style="margin-top: 9pt">
		<tr align="center" height="34">
			<td colspan="4">论文进度安排</td>
		</tr>
		<tr height="34" align="center">
			<td width="7%">序号</td>
			<td width="33%">论文（设计）工作任务</td>
			<td width="30%">开始日期</td>
			<td width="30%">结束日期</td>
		</tr>
	</table>
</td>
<jsp:include page="../commonalityPage_buttom.jsp" />


<script>
	studentTaskLoad();
</script>