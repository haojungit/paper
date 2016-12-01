<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../commonalityPage_top.jsp"></jsp:include>

<td valign="top" bgcolor="#F7F8F9">
	<h3 class="title">大庆师范学院本科生毕业论文（设计）答辩记录表</h3>
	<form>

		<table border="1" WIDTH="100%" BORDERCOLORLIGHT="#b1c9ff"
			BORDERCOLORDARK="#b1c9ff">


			<jsp:include page="../teacher/publicInfo.jsp"></jsp:include>


			<tr>
				<td colspan="2" class="left5">答辩时间：<input type="text"
					style="width: 50px" />&nbsp;年&nbsp;<input type="text"
					style="width: 50px" />&nbsp;月&nbsp;<input type="text"
					style="width: 50px" />&nbsp;日&nbsp;
				</td>


				<td class="left5" colspan="4">答辩地点：<input type="text" />
				</td>
			</tr>

			<tr>
				<td colspan="6" class="left15">答辩记录：<br /> <textarea
						name="textarea" rows="10" cols="90"></textarea>
				</td>
			</tr>

		</table>

		<table border="1" WIDTH="100%" BORDERCOLORLIGHT="#b1c9ff"
			BORDERCOLORDARK="#b1c9ff">
			<tr align="center" height="34">
				<td rowspan="6"><pre>
答
辩
小
组
成
员
</pre></td>
				<td>成员姓名</td>
				<td>职称/学历</td>
				<td>专业研究领域</td>
				<td>所在单位</td>
				<td>签 名</td>
			</tr>
			<tr align="center">
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
			</tr>

			<tr align="center">
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
			</tr>
			<tr align="center">
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
			</tr>
			<tr align="center">
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
			</tr>
			<tr align="center">
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
			</tr>
			<tr>
				<td colspan="6" class="left15">答辩小组评议意见/成绩：<br /> <textarea
						name="textarea" rows="10" cols="90"></textarea><br />
					<p>
						<span>成绩：<input type="text" /></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>组长签字：<input
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
				<td colspan="6" class="left15">答辩委员会审评意见：<br /> <textarea
						name="textarea" rows="10" cols="90"></textarea><br />
					<p align="right">
						答辩委员会主席（签字）：<input type="text" />
					</p>
					<p align="right">
						<input type="text" class="print-w30 " />&nbsp;年&nbsp;<input
							type="text" class="print-w30 " />&nbsp;月&nbsp;<input type="text"
							class="print-w30 " />&nbsp;日
					</p>
				</td>
			</tr>
		</table>
		
		<div class="noprint">
			<div style="float: left;">
				<input type="button" value="提交" />
			</div>
			<div style="float: left;">
				<input type="button" value="打印输出" onclick="window.print()"/>
			</div>
			<div style="float: left;">
				<input type="button" value="发送消息" onclick="sendto($('#publicInfoStudentNumber').val(),'我看过了你的答辩记录。');"/>
			</div>
		</div>
	</form>
</td>


<jsp:include page="../commonalityPage_buttom.jsp"></jsp:include>