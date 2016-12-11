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

<script src="<%=basePath%>js/public.js"></script>
<script src="<%=basePath%>js/studentReplayApply.js"></script>

<script src="<%=basePath%>dwr/interface/PaperMidcheck.js"></script>
<script src="<%=basePath%>dwr/interface/paperApply.js"></script>
<script src="<%=basePath%>dwr/interface/titleDWR.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>
<script src="<%=basePath%>dwr/interface/msgService.js"></script>
<script src="<%=basePath%>js/msg.js"></script>
<jsp:include page="../commonalityPage_top.jsp"></jsp:include>

<td valign="top" bgcolor="#F7F8F9">
	<h3 class="title">大庆师范学院本科生毕业论文（设计）学生申请答辩表</h3> <input type='hidden'
	id="replyApplyTitleId" /> <input type='hidden'
	id="replyApplyStudentId" value="${student.studentId}" />
	<form id="replyApplyForm">
		<table border="1" WIDTH="100%" BORDERCOLORLIGHT="#b1c9ff"
			BORDERCOLORDARK="#b1c9ff">
			<tr>
				<td class="left5">论文（设计）题目：</td>
				<td><input id="replyApplyTitleName" type="text"
					style="width: 70%;" disabled="disabled" /></td>
				<td class="left5">院 （系）</td>
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
				<td colspan="4" class="left15">申请理由<br /> <textarea
						class="easyui-validatebox" required validType="length[1,1000]"
						id="replyApplyReason" cols="90" rows="8"></textarea><br />
					<p align="right" style="padding-right: 175px;">学生（签字）：</p>
					<p align="right" style="padding-right: 20px; margin: 5px;">
						申请日期：<input id="replyApplyDate" class="easyui-datebox" required
							data-options="formatter:myformatter,parser:myparser,editable:false"></input>
					</p>
				</td>
			</tr>
		</table>
	</form>
	<h3 class="title">本科生毕业论文（设计）指导教师评审表</h3>
	<table border="1" WIDTH="100%" BORDERCOLORLIGHT="#b1c9ff"
		BORDERCOLORDARK="#b1c9ff">
		<tr>
			<td align="center" width="30px;">序号</td>
			<td align="center">评审项目</td>
			<td align="center">指 标</td>
			<td align="center" width="30px;">分<br />值
			</td>
			<td align="center">得<br /> 分
			</td>
		</tr>
		<tr>
			<td align="center">1</td>
			<td align="center">工作态度任务量</td>
			<td class="left10">工作态度端正，遵循科学规律，作风严谨。能在规定的期限内，圆
				满的完成毕业论文（设计）中所规定的全部任务。</td>
			<td align="center">20</td>
			<td align="center"><input id="score1" type="text"
				style="width: 50px" disabled /></td>
		</tr>
		<tr>
			<td align="center">2</td>
			<td align="center">调查论证</td>
			<td class="left10">能独立查阅与论文相关的中、英文献和调研工作。具有独立工作
				能力，对获取的相关信息具有正确的分类、归纳、总结、运用能力。</td>
			<td align="center">10</td>
			<td align="center"><input id="score2" type="text"
				style="width: 50px" disabled /></td>
		</tr>
		<tr>
			<td align="center">3</td>
			<td align="left">实验技能、 手段，设计方案、实施</td>
			<td align="left" class="left10">能独立、正确地操作实验，同时在实验中能依据现有的或改良的技
				术手段，获得准确、可靠的实验数据。设计方案合理，工艺可靠，具有 可操作性、实用性。程序运行可靠。</td>
			<td align="center">20</td>
			<td align="center"><input id="score3" type="text"
				style="width: 50px" disabled /></td>
		</tr>
		<tr>
			<td align="center">4</td>
			<td align="center">分析与解决问题的能力</td>
			<td class="left10">能综合的运用所学的专业知识，以正确的观点提出问题，能进行精
				辟透彻的分析。并能紧密的结合论文（设计）需求，提出独特的见解， 使论文（设计）具有鲜明的创新性、应用性。</td>
			<td align="center">20</td>
			<td align="center"><input id="score4" type="text"
				style="width: 50px" disabled /></td>
		</tr>
		<tr>
			<td align="center">5</td>
			<td align="center">论文（设计） 质量</td>
			<td class="left10">思路清晰、书写规范，论据充分，符合推理，实验数据可靠，结构
				严谨。设计中流程规范，图表清晰，布局合理，尺寸规范，文字注</td>
			<td align="center">20</td>
			<td align="center"><input id="score5" type="text"
				style="width: 50px" disabled /></td>
		</tr>
		<tr>
			<td align="center">6</td>
			<td align="center">创新</td>
			<td class="left10">具有较强的创新意识，能够在继承前人所取得的成果的基础上，有
				自己独特的认识与见解，并具有一定的实际应用价值。</td>
			<td align="center">10</td>
			<td align="center"><input id="score6" type="text"
				style="width: 50px" disabled /></td>
		</tr>

		<tr>
			<td colspan="3" class="left5">是否同意参加答辩 <input id="allowReply"
				type="text" style="width: 50px" disabled /></td>

			<td align=center>总<br />分
			</td>
			<td><input id="score7" type="text" style="width: 50px" disabled /></td>
		</tr>
		<tr height="40px">
			<td colspan="2" class="left5" style="border-right: 0px">指导教师（签字）：</td>
			<td colspan="4" style="border-left: 0px"><p
					style="text-align: right; padding-right: 20px;">
					日期：<input id="replyInpuDate" class="easyui-datebox" required
						disabled tipPosition="left"
						data-options="formatter:myformatter,parser:myparser,editable:false"></input>
				</p></td>
		</tr>
	</table>
	<div class="noprint">

		<div style="float: left;">
			<input id="replyApplySubmit" type="button" value="提交"
				onclick="replyApplySave()" />
		</div>
		<div style="float: left;">
			<input id="newReplyApply" type="button" value="发起新申请"
				onclick="newReplyApply()" />
		</div>
		<div style="float: left;">
			<input type="button" value="打印输出" onclick="window.print()" />
		</div>
		<div style="float: left;">
			<input type="button" value="发送消息" onclick="sendToMyTeacher('老师，我提交了答辩申请，请批阅。');"/>
		</div>
	</div>
</td>

<jsp:include page="../commonalityPage_buttom.jsp"></jsp:include>

<script>
	replayApplyLoad();
</script>