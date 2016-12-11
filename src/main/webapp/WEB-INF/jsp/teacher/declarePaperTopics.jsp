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

<script src="<%=basePath%>jquery-easyui/jquery-1.8.0.js"></script>
<script src="<%=basePath%>jquery-easyui/jquery.easyui.min.js"></script>

<script src='<%=basePath%>dwr/interface/titleDWR.js'></script>
<script src='<%=basePath%>dwr/interface/student.js'></script>
<script src="<%=basePath%>dwr/interface/msgService.js"></script>
<script src='<%=basePath%>dwr/engine.js'></script>
<script src='<%=basePath%>dwr/util.js'></script>

<script src='<%=basePath%>js/myTopics.js'></script>
<script src="<%=basePath%>js/public.js"></script>
<script src="<%=basePath%>js/msg.js"></script>

<jsp:include page="../commonalityPage_top.jsp"></jsp:include>

<td valign="top" bgcolor="#F7F8F9">
	<h3 class="title print">
		大庆师范学院<br />本科生毕业论文（设计）题目申报表
	</h3>
	<form id="declarePaperTopicsForm">
		<table border="1" WIDTH="100%" BORDERCOLORLIGHT="#b1c9ff"
			BORDERCOLORDARK="#b1c9ff" class="print">

			<c:if test="${!empty teacher}">
				<tr>
					<td class="left5">申报人姓名</td>
					<td><input type="text" disabled="disabled"
						value="${teacher.teacherName}" /></td>
					<td class="left5">所在院系</td>
					<td><input id="DPTTeacherUnits" type="text"
						disabled="disabled" value="${teacher.teacherUnits}" /></td>
					<td class="left5">所学专业</td>
					<td><input type="text" disabled="disabled"
						value="${teacher.teacherMajor}" /></td>
				</tr>

				<tr>
					<td class="left5">职称</td>
					<td><input type="text" disabled="disabled"
						value="${teacher.teacherJobTitle}" /></td>
					<td class="left5">研究方向</td>
					<td colspan="3"><input type="text" disabled="disabled"
						value="${teacher.teacherDirection}" /></td>
				</tr>
			</c:if>

			<c:if test="${empty teacher}">
			登录超时,如果本页面不支持跳转请<a href='../login.jsp'>点击这里</a>
				<script>
					top.location.href = '../../../login.jsp';
				</script>
			</c:if>
			<tr>
				<td class="left5">题目名称 <a style="color: red">*</a></td>
				<td><input id="paperTitleName" type="text"
					class="easyui-validatebox" validType="" required /></td>

				<td class="left5">限选学生专业<a style="color: red">*</a></td>
				<td class="left5" id="paperTitleLimitMajorTD"><select
					id="paperTitleLimitMajor" class="easyui-combobox"
					data-options="editable:false" style="width: 155px; height: auto;"></select></td>

				<td class="left5">题目类型</td>
				<td class="left5"><select class="easyui-combobox"
					data-options="editable:false" id="paperTitletype"
					style="width: 155px;">
						<option selected value="A">A-理论研究型</option>
						<option value="B">B-实验研究型</option>
						<option value="C">C-应用型</option>
						<option value="D">D-其它</option>
				</select></td>
			</tr>

			<tr>
				<td class="left5">题目关键字<a style="color: red">*</a></td>
				<td><input id="paperTitleKeywords" class="easyui-validatebox"
					required type="text" type="text" /></td>

				<td class="left5">题目来源</td>
				<td class="left5"><select class="easyui-combobox"
					data-options="editable:false" id="paperTitleSource"
					style="width: 155px; height: auto;">
						<option selected value="A">A-结合教师科研</option>
						<option value="B">B-结合教育教学</option>
						<option value="C">C-结合实验室建设</option>
						<option value="D">D-结合生产实际</option>
						<option value="E">E—自拟</option>
				</select></td>

				<td class="left5">题目平台</td>
				<td><input id="paperTitlePlatform" type="text" /></td>
			</tr>

			<tr>
				<td colspan="6" class="left15">题目介绍（含内容、要求、工作量、需要学生数等） 内容：<br />
					<textarea class="easyui-validatebox" validType="" required
				id="paperTitleIntroduce" rows="17" cols="100"></textarea><br />
					<p align="right" style="margin-right: 165px;">申报人（签字）：</p>
					<p align="right" style="margin-right: 10px; margin-bottom: 10px;">
						申报日期 ： <input id="paperTitleReportDate" class="easyui-datebox"
							required
							data-options="formatter:myformatter,parser:myparser,editable:false"></input>
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="6" class="left15">
					<div style="display: none" class="onprint">
						系（教研室）主任意见：<br />
						<textarea name="textarea" rows="8" cols="100"></textarea>
						<br>
						<p align="right" style="margin-right: 165px; margin-bottom: 10px;">系（教研室）主任（签字）：</p>
						<p align="right" style="margin-right: 165px; margin-bottom: 10px;">日期：</p>
				</td>
				</div>
			</tr>
		</table>
	</form> <pre class="print-li24">
   注：1.每项内容指导教师都必须认真填写。
       2.题目类型：A—理论研究型；B—实验研究型；C—应用型；D—其它。
       3.题目来源：A—结合教师科研；B—结合教育教学；C—结合实验室建设；D—结合生产实际；E—自拟。

</pre>
	<div class="noprint">
		<div style="float: left;">
			<input type="button" value="提交" onclick="addPaperTopics()" />
		</div>
		<div style="float: left;">
			<input type="button" value="发送消息" onclick="sendToLeader('您好，我新申报了论文课题，请您审批。');"/>
		</div>
		<div style="float: left;">
			<input type="button" value="打印" onclick="window.print()" />
		</div>
	</div>
</td>
<jsp:include page="../commonalityPage_top.jsp"></jsp:include>
<script>
	student.findStudentMajor($('#DPTTeacherUnits').val(), function(data) {
		if (data.length == 0) {
			alert('您的院系下还没有学生专业数据,请联系相关领导！');
			top.mainFrame.closePaperTab('课题申报');
			return;
		}
		$('#paperTitleLimitMajor').combobox({
			data : data,
			valueField : 'valueField',
			textField : 'studentMajor'
		});
	});
</script>