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
<script src="<%=basePath%>js/score.js"></script>

<script src="<%=basePath%>dwr/interface/paperGuideDWR.js"></script>
<script src="<%=basePath%>dwr/interface/titleDWR.js"></script>
<script src="<%=basePath%>dwr/interface/paperScoreDWR.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>
<script src="<%=basePath%>dwr/interface/msgService.js"></script>
<script src="<%=basePath%>js/msg.js"></script>
<jsp:include page="../commonalityPage_top.jsp"></jsp:include>

<td valign="top" bgcolor="#F7F8F9" style="width: 100%; height: 100%">
	<h3 class="title">
		大庆师范学院本科生毕业论文（设计）成绩表
	</h3>
	<table border="1px" width="100%">

		<jsp:include page="publicInfo.jsp" />
<tr>
			
			<td colspan="6" class="left15">
			<!-- 
			指导教师评语：<br/> 
			<font color="red" id="t1"></font> 
			-->
				<p>
					<p>指导教师给分：<font color="red" id="t2"></font></p>
					<span>指导教师：<font color="red" id="t3"></font>
					</span>
				</p>
			</td>
		</tr>

		<tr>
			<td colspan="6" class="left15">
			答辩委员会（小组）评语：<br /> 
			<font color="red" id="t4"></font><br />
				<p>
					<p>成绩：<font color="red" id="t5"></font></p>
					<!-- <p>答辩委员会（小组）负责人：<font color="red" id="t6"></p> -->
				</p>
			</td>
		</tr>

		<td colspan="5">
			<p>
				毕业论文（设计）综合成绩：<font color="red" id="t7"></font></p>
			<!-- <p>成绩核定人（签字）： -->
			<p align="right"></p></td>
		</tr>


		<tr>
			<td colspan="6" class="left15">答辩委员会审评意见：<br /> 
			<font color="red" id="t9"></font><br />
				<p align="center">
					答辩委员会主席（签字）：
				</p>
				<p align="right">
					<font color="red" id="t3"></font>
				</p>
			</td>
		</tr>
		
	</table>
<div class="noprint">
	<div style="float: left; font-size: 12px">
		<input type="button" value="打印输出" onclick="window.print()"/>
	</div>
	<div style="float: left; font-size: 12px">
		<input type="button" value="发送消息" onclick="sendToMyTeacher('老师，我查看了了成绩。');"/>
	</div>
</div>
</td>

<jsp:include page="../commonalityPage_buttom.jsp"></jsp:include>


<script>
scoreLoad();
</script>