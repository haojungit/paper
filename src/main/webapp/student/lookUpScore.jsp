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

<script src='<%=basePath%>dwr/interface/paperScoreDWR.js'></script>
<script src='<%=basePath%>dwr/interface/titleDWR.js'></script>
<script src='<%=basePath%>dwr/engine.js'></script>
<script src='<%=basePath%>dwr/util.js'></script>

<script src="<%=basePath%>js/public.js"></script>
<script src="<%=basePath%>js/studentTask.js"></script>
<script src="<%=basePath%>dwr/interface/msgService.js"></script>
<script src="<%=basePath%>js/msg.js"></script>

<jsp:include page="../commonalityPage_top.jsp"></jsp:include>
<script type="text/javascript">
	
</script>
<td valign="top" bgcolor="#F7F8F9">
	<h3 class="title">大庆师范学院本科生毕业论文（设计）成绩表</h3>
	<table border="1" WIDTH="100%" BORDERCOLORLIGHT="#b1c9ff"
		BORDERCOLORDARK="#b1c9ff">

		<jsp:include page="publicInfo.jsp"></jsp:include>

		<tr>
			<td colspan="6" class="left15">指导教师评语：<br /> <textarea
					name="textarea" rows="10" cols="90"></textarea><br />
				<p>
					<span>成绩：<input type="text" /></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>指导教师（签字）：<input
						type="text" /></span>
				</p>
				<p align="right">
					<input type="text" class="print-w30 " />&nbsp;年&nbsp;<input
						type="text" class="print-w30 " />&nbsp;月&nbsp;<input type="text"
						class="print-w30 " />&nbsp;日
				</p>
			</td>
		</tr>

		<tr>
			<td colspan="6" class="left15">答辩委员会（小组）评语：<br /> <textarea
					name="textarea" rows="10" cols="90"></textarea><br />
				<p>
					<span>成绩：<input type="text" /></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>
						答辩委员会（小组）负责人（签字）：<input type="text" />
					</span>
				</p>
				<p align="right">
					<input type="text" class="print-w30 " />&nbsp;年&nbsp;<input
						type="text" class="print-w30 " />&nbsp;月&nbsp;<input type="text"
						class="print-w30 " />&nbsp;日
				</p>
			</td>
		</tr>





		<td colspan="3"><p>
				毕业论文（设计）综合成绩：<input type="text" /> 成绩核定人（签字）：<input type="text" />
			</p>
			<p align="right"></p></td>
		</tr>


		<tr>
			<td colspan="6" class="left15">答辩委员会审评意见：<br /> <textarea
					name="textarea" rows="10" cols="90"></textarea><br />
				<p align="center">
					答辩委员会主席（签字）：<input type="text" /></span>
				</p>
				<p align="right">
					<input type="text" class="print-w30 " />&nbsp;年&nbsp;<input
						type="text" class="print-w30 " />&nbsp;月&nbsp;<input type="text"
						class="print-w30 " />&nbsp;日
				</p>
			</td>
		</tr>

	</table>
	<div class="left5 print-li24">注：本成绩单一式两份，一份存学生档案，一份附在论文后存入所属院（系）教学档案。</div>
	<div class="noprint">
		<div style="float: left;">
			<input type="button" value="增加" />
		</div>
		<div style="float: left;">
			<input type="button" value="打印输出" onclick="window.print()" />
		</div>
		<div style="float: left;">
			<input type="button" value="发送消息" onclick="sendToMyTeacher('老师，我已经填写了开题报告，请批阅。');"/>
		</div>
	</div>
</td>
<script type="text/javascript">
	myScoreInfo();
</script>
<jsp:include page="../commonalityPage_buttom.jsp"></jsp:include>
