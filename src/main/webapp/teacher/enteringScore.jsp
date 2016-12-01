<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script src="<%=basePath%>js/public.js"></script>

<script src='<%=basePath%>js/My97DatePicker/WdatePicker.js'></script>
<script src="<%=basePath%>dwr/interface/paperScoreDWR.js"></script>
<script src="<%=basePath%>dwr/interface/PaperMidcheck.js"></script>
<script src="<%=basePath%>dwr/interface/titleDWR.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>

<script src="<%=basePath%>js/teacherScore.js"></script>

<jsp:include page="../commonalityPage_top.jsp"></jsp:include>

<td valign="top" bgcolor="#F7F8F9">
	<form>
		<h3 class="title">
			大庆师范学院<br />本科生毕业论文（设计）成绩表
		</h3>
		<table border="1" WIDTH="100%" BORDERCOLORLIGHT="#b1c9ff"
			BORDERCOLORDARK="#b1c9ff">

			<jsp:include page="publicInfo.jsp"></jsp:include>

			<tr>
				<td colspan="6" class="left15">指导教师评语：<br> <textarea id="teacher_t"
						name="textarea" rows="10" cols="90"></textarea><br>
					<p>
						<span>成绩：<input type="text" id="teacher_s"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>指导教师（签字）：</span>
					</p>
					<p align="right">
						<input id="writingTaskStrartTime" type="text"
					class="Wdate easyui-validatebox"/>
					
					</p>
				</td>
			</tr>

			<tr>
				<td colspan="6" class="left15">答辩委员会（小组）评语：<br> <textarea id="team_t"
						name="textarea" rows="10" cols="90" disabled="disabled"></textarea><br>
					<p>
						<span>成绩：<input type="text" disabled="disabled" id="team_s"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>
							答辩委员会（小组）负责人（签字）：
						</span>
					</p>
					<p align="right">
						<input type="text" class="print-w30 ">&nbsp;年&nbsp;<input
							type="text" class="print-w30 ">&nbsp;月&nbsp;<input
							type="text" class="print-w30 ">&nbsp;日
					</p>
				</td>
			</tr>

			<tr>
				<td colspan="3"><p>
						毕业论文（设计）综合成绩：<input type="text" id="totalScore"> 成绩核定人（签字）：
					</p>
					<p align="right"></p></td>
			</tr>

			<tr>
				<td colspan="6" class="left15">答辩委员会审评意见：<br> <textarea id="council_t"
						name="textarea" rows="10" cols="90" disabled="disabled"></textarea><br>
					<p align="center">
						答辩委员会主席（签字）：<input type="text" disabled="disabled">
					</p>
					<p align="right">
						<input type="text" class="print-w30 ">&nbsp;年&nbsp;<input
							type="text" class="print-w30 ">&nbsp;月&nbsp;<input
							type="text" class="print-w30 ">&nbsp;日
					</p>
				</td>
			</tr>
		</table>
		<div class="left5 print-li24">注：本成绩单一式两份，一份存学生档案，一份附在论文后存入所属院（系）教学档案。</div>
		<div class="noprint">
			<div style="float: left;">
				<input type="button" value="提交" id="bt_save">
			</div>
			<div style="float: left;">
				<input type="button" value="打印输出">
			</div>
			<div style="float: left;">
				<input type="button" value="发送消息">
			</div>
		</div>
	</form>
</td>
<<script type="text/javascript">
teacherScoreInfo();
</script>

<jsp:include page="../commonalityPage_buttom.jsp"></jsp:include>