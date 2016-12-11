<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../commonalityPage_top.jsp"></jsp:include>

<td valign="top" bgcolor="#F7F8F9">
	<h3 class="title">大庆师范学院本科生优秀毕业论文（设计）推荐表</h3>
	<table border="1" WIDTH="100%" BORDERCOLORLIGHT="#b1c9ff"
		BORDERCOLORDARK="#b1c9ff">

		<jsp:include page="../teacher/publicInfo.jsp"></jsp:include>

		<tr>
			<td colspan="6" class="left15">答辩小组推荐意见（须说明特点及推荐原因）：<br /> <textarea
					name="textarea" rows="10" cols="90"></textarea>

			</td>
		</tr>

		<tr>
			<td colspan="6" align="right">组长（签字）：<input type="text" />
			</td>
		</tr>

		<tr>
			<td colspan="6" align="right"><input type="text"
				style="width: 50px" />年<input type="text" style="width: 50px" />月<input
				type="text" style="width: 50px" />日</td>
		</tr>

		<tr>
			<td colspan="6" class="left15">系（教研室）意见：<br /> <textarea
					name="textarea" rows="10" cols="90"></textarea>

			</td>
		</tr>

		<tr>
			<td colspan="6" class="left15">院（系）意见：<br /> <textarea
					name="textarea" rows="10" cols="90"></textarea>

			</td>
		</tr>

		<tr>
			<td colspan="6" align="right">主管教学领导（签字）：<input type="text" />
			</td>
		</tr>
		<tr>
			<td colspan="6" align="right"><input type="text"
				style="width: 50px" />年<input type="text" style="width: 50px" />月<input
				type="text" style="width: 50px" />日</td>
		</tr>
	</table>
	<div class="left5">注：此表一式二份，一份系（院）存档，一份报教务处实践教学科。</div>
	<div class="noprint">
		<div style="float: left; font-size: 12px">
			<input type="button" value="提交" />
		</div>
		<div style="float: left; font-size: 12px">
			<input type="button" value="打印输出" onclick="window.print()"/>
		</div>
		<div style="float: left; font-size: 12px">
			<input type="button" value="发送消息" />
		</div>
	</div>
</td>

<jsp:include page="../commonalityPage_buttom.jsp"></jsp:include>