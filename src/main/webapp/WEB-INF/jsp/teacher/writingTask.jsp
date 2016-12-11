<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script src='<%=basePath%>dwr/interface/titleDWR.js'></script>
<script src='<%=basePath%>dwr/interface/paperPlan.js'></script>
<script src='<%=basePath%>dwr/interface/writingTask.js'></script>
<script src='<%=basePath%>dwr/engine.js'></script>
<script src='<%=basePath%>dwr/util.js'></script>
<script src='<%=basePath%>js/My97DatePicker/WdatePicker.js'></script>
<script src="<%=basePath%>js/public.js"></script>
<script src="<%=basePath%>js/teacherTask.js"></script>

<jsp:include page="../commonalityPage_top.jsp"></jsp:include>

<td valign="top" bgcolor="#F7F8F9">
	<h3 class="title">
		大庆师范学院<br />本科生毕业论文（设计）任务书
	</h3>
	<form id="writingTaskForm">
		<table border="1" WIDTH="100%" BORDERCOLORLIGHT="#b1c9ff"
			BORDERCOLORDARK="#b1c9ff">

			<jsp:include page="publicInfo.jsp"></jsp:include>

			<tr height="34">
				<td colspan="6" class="left5">任务起止日期:&nbsp;&nbsp;&nbsp; <input
					id="writingTaskStrartTime" type="text"
					class="Wdate easyui-validatebox" required
					onFocus="WdatePicker({maxDate: '#F{$dp.$D(\'writingTaskEndTime\')||\'2200-10-01\'}' })" />
					&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp; <input
					id="writingTaskEndTime" class="Wdate easyui-validatebox" required
					type="text"
					onFocus="WdatePicker({minDate: '#F{$dp.$D(\'writingTaskStrartTime\')}' ,maxDate:'2200-10-01' })" />
			</tr>
			<tr>
				<td colspan="6" class="left15">论文（设计）的主要内容与要求：<br /> <textarea
						class="easyui-validatebox" validType="" required
						id="writingTaskReference" name="textarea" rows="8" cols="100"></textarea>

				</td>
			</tr>
			<tr>
				<td colspan="6" class="left15">主要参考文献：<br /> <textarea
						class="easyui-validatebox" validType="" required
						id="writingTaskContent" name="textarea" rows="6" cols="100"></textarea>

				</td>
			</tr>
		</table>
		<table border="1" WIDTH="100%" BORDERCOLORLIGHT="#b1c9ff"
			BORDERCOLORDARK="#b1c9ff" style="margin-top: 9pt">
			<tr align="center" height="34">
				<td colspan="4">论文进度安排</td>
			</tr>
			<tr height="34" align="center">
				<td width="7%">序号</td>
				<td width="43%">论文（设计）工作任务</td>
				<td width="25%">开始日期</td>
				<td width="25%">结束日期</td>
			</tr>
			<tr align="center">
				<td>1</td>
				<td><input style="width: 80%" id="planTask1" type="text"
					class="easyui-validatebox" required /></td>
				<td><input id="writingTaskPlanStartTime1"
					class="Wdate easyui-validatebox" required type="text"
					onFocus="WdatePicker({minDate: '#F{$dp.$D(\'writingTaskStrartTime\')}' ,maxDate:'#F{$dp.$D(\'writingTaskStrartTime\')}' })" />
				</td>
				<td><input id="writingTaskPlanEndTime1"
					class="Wdate easyui-validatebox" required type="text"
					onFocus="WdatePicker({minDate: '#F{$dp.$D(\'writingTaskPlanStartTime1\')}' ,maxDate:'#F{$dp.$D(\'writingTaskEndTime\')}' })" />
				</td>
			</tr>
			<tr align="center">
				<td>2</td>
				<td><input style="width: 80%" id="planTask2" type="text"
					class="easyui-validatebox" required /></td>
				<td><input id="writingTaskPlanStartTime2"
					class="Wdate easyui-validatebox" required type="text"
					onFocus="WdatePicker({minDate: '#F{$dp.$D(\'writingTaskPlanEndTime1\')}' ,maxDate:  '#F{$dp.$D(\'writingTaskEndTime\')}' })" />
				</td>
				<td><input id="writingTaskPlanEndTime2"
					class="Wdate easyui-validatebox" required type="text"
					onFocus="WdatePicker({minDate: '#F{$dp.$D(\'writingTaskPlanStartTime2\')}' ,maxDate:'#F{$dp.$D(\'writingTaskEndTime\')}' })" />
				</td>
			</tr>
			<tr align="center">
				<td>3</td>
				<td><input style="width: 80%" id="planTask3" type="text"
					class="easyui-validatebox" /></td>
				<td><input id="writingTaskPlanStartTime3" class="Wdate"
					type="text"
					onFocus="WdatePicker({minDate: '#F{$dp.$D(\'writingTaskPlanEndTime2\')}' ,maxDate: '#F{$dp.$D(\'writingTaskEndTime\')}' })" />
				</td>
				<td><input id="writingTaskPlanEndTime3" class="Wdate"
					type="text"
					onFocus="WdatePicker({minDate: '#F{$dp.$D(\'writingTaskPlanStartTime3\')}' ,maxDate:'#F{$dp.$D(\'writingTaskEndTime\')}' })" />
				</td>
			</tr>
			<tr align="center">
				<td>4</td>
				<td><input style="width: 80%" id="planTask4" type="text"
					class="easyui-validatebox" /></td>
				<td><input id="writingTaskPlanStartTime4" class="Wdate"
					type="text"
					onFocus="WdatePicker({minDate: '#F{$dp.$D(\'writingTaskPlanEndTime3\')}' ,maxDate: '#F{$dp.$D(\'writingTaskEndTime\')}' })" />
				</td>
				<td><input id="writingTaskPlanEndTime4" class="Wdate"
					type="text"
					onFocus="WdatePicker({minDate: '#F{$dp.$D(\'writingTaskPlanStartTime4\')}' ,maxDate:'#F{$dp.$D(\'writingTaskEndTime\')}' })" />
				</td>
			</tr>
			<tr align="center">
				<td>5</td>
				<td><input style="width: 80%" id="planTask5" type="text"
					class="easyui-validatebox" /></td>
				<td><input id="writingTaskPlanStartTime5" class="Wdate"
					type="text"
					onFocus="WdatePicker({minDate: '#F{$dp.$D(\'writingTaskPlanEndTime4\')}' ,maxDate: '#F{$dp.$D(\'writingTaskEndTime\')}' })" />
				</td>
				<td><input id="writingTaskPlanEndTime5" class="Wdate"
					type="text"
					onFocus="WdatePicker({minDate: '#F{$dp.$D(\'writingTaskPlanStartTime5\')}' ,maxDate:'#F{$dp.$D(\'writingTaskEndTime\')}' })" />
				</td>
			</tr>
			<tr align="center">
				<td>6</td>
				<td><input style="width: 80%" id="planTask6" type="text"
					class="easyui-validatebox" /></td>
				<td><input id="writingTaskPlanStartTime6" class="Wdate"
					type="text"
					onFocus="WdatePicker({minDate: '#F{$dp.$D(\'writingTaskPlanEndTime5\')}' ,maxDate: '#F{$dp.$D(\'writingTaskEndTime\')}' })" />
				</td>
				<td><input id="writingTaskPlanEndTime6" class="Wdate"
					type="text"
					onFocus="WdatePicker({minDate: '#F{$dp.$D(\'writingTaskPlanStartTime6\')}' ,maxDate:'#F{$dp.$D(\'writingTaskEndTime\')}' })" />
				</td>
			</tr>
			<tr>
				<td style="text-align: left; padding-left: 20px;" colspan="2"
					height="34px;">任务下达人（签字）：</td>
				<td style="text-align: left; padding: 15 5 5 20;" colspan="2"
					valign="top" rowspan="2">系(教研室)主任（签字）：<br />
					<p style="text-align: right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					</td>
			</tr>
			<tr>
				<td style="text-align: left; padding-left: 20px;" colspan="2"
					height="34px;">任务下达人（签字）：</td>
			</tr>

		</table>
	</form> <pre>
注：此任务书由指导教师填写，毕业论文（设计）开始前一周下发给学生

</pre>
	<div class="noprint">
		<div style="float: left;">
			<input id="writingTaskSubmit" type="button"
				onclick="saveWritingTask()" value="提交" />
		</div>

		<div style="float: left;">
			<input type="button" value="打印输出" onclick="window.print()" />
		</div>
	</div>
</td>

<jsp:include page="../commonalityPage_buttom.jsp"></jsp:include>

<script>
	teacherTaskLoad();
</script>