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
											'<tr><td colspan="2" style="font-size: 25px; color: red; text-align: center">您还没有上传论文模版！</td>');
						else
							$('#uploadPaperTemplateTable')
									.prepend(
											'<tr><td colspan="2" style="font-size: 20px; color: red; padding-left: 20px;">当前论文模板为：</td></tr><tr><td colspan="2" style="text-align: center;"><a style="font-size: 15px;" href="../struts/getPaperTemplate.action">'
													+ list + '</a></td></tr>');

					}, 'json');
</script>
<jsp:include page="../commonalityPage_top.jsp"></jsp:include>
<td>
	<form action="../struts/uploadPaperTemplate.action" theme="simple"
		method="post" enctype="multipart/form-data">
		<table id="uploadPaperTemplateTable" align="center" width="70%"
			border="1" style="margin-top: 20px;">
			<tr>
				<td colspan="2" style="height: 30px; padding-left: 20px;"></td>
			</tr>
			<tr>
				<td colspan="2" style="height: 30px; padding-left: 20px;"><input
					type="submit" value="开始上传 " /> <input type="file" name="upload" /></td>
			</tr>
			<tr>
				<td colspan="2" style="height: 30px; padding-left: 20px;">说明：论文模板总数量为1个,上传论文模板请上传doc或docx格式的Word文档!</td>
			</tr>
		</table>
	</form>
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
