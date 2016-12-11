<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script src="<%=basePath%>js/teacherThesisProposal.js"></script>
<script src="<%=basePath%>js/public.js"></script>

<script src="<%=basePath%>dwr/interface/thesisProposal.js"></script>
<script src="<%=basePath%>dwr/interface/titleDWR.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>
<script src="<%=basePath%>dwr/interface/msgService.js"></script>
<script src="<%=basePath%>js/msg.js"></script>
</head>

<jsp:include page="../commonalityPage_top.jsp"></jsp:include>
<td valign="top" bgcolor="#F7F8F9">
	<h3 class="title">
		大庆师范学院<br />本科生毕业论文（设计）开题报告表
	</h3>
	<form id="teacherThesisProposalForm">
		<table border="1px" width="100%">

			<jsp:include page="publicInfo.jsp"></jsp:include>

			<tr>
				<td colspan="6" class="left15">一、选题的背景与意义<br /> <textarea
						class="easyui-validatebox" validType="" disabled
						id="thesisProposalBackground" cols="90" rows="14"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="6" class="left15">二、研究的主要内容和预期目标<br /> <textarea
						class="easyui-validatebox" validType="" disabled
						id="thesisProposalResearchContent" cols="90" rows="14"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="6" class="left15">三、拟采用的研究方法、步骤<br /> <textarea
						class="easyui-validatebox" validType="" disabled
						id="thesisProposalResearchMethod" cols="90" rows="15"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="6" class="left15">四、研究的总体安排与进度<br /> <textarea
						class="easyui-validatebox" validType="" disabled
						id="thesisProposalResearchSchedule" cols="90" rows="12"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="6" class="left15">五、已查阅参考文献<br /> <textarea
						class="easyui-validatebox" validType="" disabled
						id="thesisProposalReference" cols="90" rows="12"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="6" class="left15">六、指导教师意见：<br /> <textarea
						class="easyui-validatebox" id="thesisProposalTeacherSug" required
						name="" cols="90" rows="10"></textarea><br />
					<p align="right" style="padding-right: 200px;">指导教师（签字）：</p>

					<p align="right" style="padding-right: 50px; margin-bottom: 20px;">
						日期 ： <input class="easyui-datebox" id="teacherSugDate" required
							data-options="formatter:myformatter,parser:myparser,editable:false"></input>
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="6" class="left15" valign="top">七、系（教研室）意见：
					<p style="padding-top: 60px;"></p>
					<p align="right" style="padding-right: 200px;">系（教研室主任）（签字）：</p>

					<p align="right" style="padding-right: 200px; margin-bottom: 20px;">
						日期 ：</p>
				</td>
			</tr>
		</table>
	</form>
	<p>
		注：1.开题报告应根据教师下达的毕业论文（设计）任务书，在教师指导下由学生独立撰写。<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在毕业论文（设计）开始二周内完成，交指导教师审阅，并接受教务处抽查。
	</p>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.此表需正反面打印。</p>
	<div class="noprint">
		<div style="float: left;">
			<input id="thesisProposalSubmit" type="button"
				onclick="thesisProposalUpdate()" value="提交" />
		</div>
		<div style="float: left;">
			<input type="button" value="打印输出" onclick="window.print()" />
		</div>
		<div style="float: left;">
			<input type="button" value="发送消息" onclick="sendto($('#publicInfoStudentNumber').val(),'我看了你的开题报告。');"/>
		</div>
	</div>
</td>

<jsp:include page="../commonalityPage_buttom.jsp"></jsp:include>


<script>
	teacherThesisProposalLoad();
</script>