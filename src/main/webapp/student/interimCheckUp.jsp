<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script src="<%=basePath%>js/studentInterimCheckUp.js"></script>
<script src="<%=basePath%>js/public.js"></script>

<script src="<%=basePath%>dwr/interface/PaperMidcheck.js"></script>
<script src="<%=basePath%>dwr/interface/titleDWR.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>
<script src="<%=basePath%>dwr/interface/msgService.js"></script>
<script src="<%=basePath%>js/msg.js"></script>
<jsp:include page="../commonalityPage_top.jsp"></jsp:include>

<td valign="top" bgcolor="#F7F8F9">
	<form>
		<h3 class="title">
			大庆师范学院<br />本科生毕业论文（设计）中期检查表
		</h3>
		<table border="1" WIDTH="100%" BORDERCOLORLIGHT="#b1c9ff"
			BORDERCOLORDARK="#b1c9ff">

			<jsp:include page="publicInfo.jsp"></jsp:include>

			<tr>
				<td colspan="4" style="height: 30px;">&nbsp;计划完成时间：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="interimCheckFinishDate" class="easyui-datebox" required
					data-options="formatter:myformatter,parser:myparser,editable:false"></input>
				</td>
			</tr>
			<tr>
				<td colspan="6" class="left15">进展情况：<br /> <textarea
						id="interimCheckProgress" name="textarea" rows="10" cols="90"></textarea>
					<p style="text-align: right; padding: 0px 175px 10px 0px;">学生(签字
						)：</p>
					<p style="text-align: right; padding: 0px 20px 10px 0px;">
						&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;期： <input id="interimCheckProgressDate"
							class="easyui-datebox" required
							data-options="formatter:myformatter,parser:myparser,editable:false"></input>
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="left15">指导教师意见：<br> <textarea
						disabled id="tComment" name="textarea" rows="10" cols="90"></textarea>
					<p style="text-align: right; padding: 0px 175px 10px 0px;">指导教师(签字
						)：</p>
					<p style="text-align: right; padding: 0px 20px 10px 0px;">
						&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;期： <input id="tCommentDate"
							class="easyui-datebox" required disabled
							data-options="formatter:myformatter,parser:myparser,editable:false"></input>
					</p>
			</tr>
		</table>

		<div class="noprint">
			<div style="float: left;">
				<input type="button" value="提交" onclick="interImCheckUpSave()" />
			</div>
			<div style="float: left;">
				<input type="button" value="打印输出" onclick="window.print()" />
			</div>
			<div style="float: left;">
				<input type="button" value="发送消息" onclick="sendToMyTeacher('老师，我填写了中期检查，请批阅。');"/>
			</div>
		</div>


	</form>
</td>

<jsp:include page="../commonalityPage_buttom.jsp"></jsp:include>
<script>
	interimCheckUp();
</script>