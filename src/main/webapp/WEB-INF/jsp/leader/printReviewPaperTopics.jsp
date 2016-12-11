<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script src="<%=basePath%>jquery-easyui/jquery-1.8.0.js"></script>
</head>
<jsp:include page="../commonalityPage_top.jsp"></jsp:include>
<td width="100%">

	<h3 class="title print">大庆师范学院本科生毕业论文（设计）教师申报题目审批表</h3>

	<table width="100%">
		<tr valign="center" height="30px;">
			<td align="left" width="33%" style="padding-left: 3px;">院(系)：</td>
			<td align="center" style="padding-right: 50px;" width="33%">专业：</td>
			<td align="center" width="33%">年级：</td>
		</tr>
	</table>
	<table border="1" width="100%" BORDERCOLORLIGHT="#b1c9ff"
		BORDERCOLORDARK="#b1c9ff">
		<tbody id="printReviewPaperTopics">
			<tr align="center">
				<td rowspan="2">序号</td>
				<td rowspan="2">题目名称</td>
				<td rowspan="2">类型</td>
				<td rowspan="2">来源</td>
				<td colspan="3">申报人</td>
				<td rowspan="2">备注</td>
			</tr>

			<tr align="center">

				<td>姓名</td>
				<td>职称</td>
				<td>学历</td>

			</tr>
		</tbody>
	</table>
	<div class="print-li24">
		<div style="height: 40px; padding: 3px;">
			<p
				style="padding-left: 3px; line-height: 30px; display: inline; float: left">填表人：</p>
			<p style="line-height: 30px; float: right">填表日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;</p>
		</div>
		<p style="padding-left: 3px; line-height: 30px;">院（系）主管教学领导签字：</p>

		<p style="padding-left: 3px;">
			注：1.类型：A—理论研究型；B—实验研究型；C—应用型；D—其它。<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.来源：A—结合教师科研；B—结合教育教学；C—结合实验室建设；D—结合生产实际；E—自拟。<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.此表需加盖院（系）公章确认，于毕业论文（设计）开始前报送教务处教学实践科，并发送邮件到19597332@qq.com。
		</p>
	</div>

	<div class="noprint">
		<div style="float: left; font-size: 12px">
			<input type="button" value="打印输出" onclick="window.print()" />
		</div>
	</div>
</td>
<td style="background: url(../images/mail_rightbg.gif) repeat-y">&nbsp;</td>
</tr>
<tr class="noprint">
	<td valign="bottom"
		style="background: url(../images/buttom_left2.gif) no-repeat; width: 5px; height: 17px;" />
	<td
		style="background: url(../images/buttom_bgs.gif) repeat-x; width: 17px; height: 17px;" />
	<td valign="bottom"
		style="background: url(../images/buttom_right2.gif) no-repeat; width: 5px; height: 17px;" />
</tr>
</table>
</body>
</html>

<script>
	top.mainFrame.题目审批.printReviewPaperTopics();

	function printReviewPaperTopics(TitleName, Titletype, TitleSource,
			teacherName, teacherJobTitle, teacherEducation) {
		var index = $('#printReviewPaperTopics').children('tr').length - 1;
		var html = '<tr align="center" height="25px"><td>' + index + '</td><td>' + TitleName
				+ '</td><td>' + TitleName + '</td><td>' + TitleSource
				+ '</td><td>' + teacherName + '</td><td>' + teacherJobTitle
				+ '</td><td>' + teacherEducation + '</td><td></td></tr>';
		$('#printReviewPaperTopics').append(html);
	}
</script>