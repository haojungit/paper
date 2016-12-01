<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script src="<%=basePath%>jquery-easyui/jquery-1.8.0.js"></script>
<script src="<%=basePath%>jquery-easyui/jquery.easyui.min.js"></script>
<script>
	$
			.post(
					"../struts/getNameList.action",

					function(list) {
						if (list == null || list == '')
							$('#uploadPaperTemplateTable')
									.prepend(
											'<a style="font-size: 20px; color: red; text-align: center">教学秘书还没有上传论文模版！</a>');
						else
							$('#uploadPaperTemplateTable').prepend(
									'<a style="font-size: 15px;" href="../struts/getPaperTemplate.action">'
											+ list + '</a>');

					}, 'json');
</script>
<jsp:include page="../commonalityPage_top.jsp"></jsp:include>
<td width="100%">
	<div id="uploadPaperTemplateTable" align="center"
		style="margin: 20px;"></div>
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
