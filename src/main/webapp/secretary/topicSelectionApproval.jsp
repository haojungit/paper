<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script src="<%=basePath%>jquery-easyui/datagrid-detailview.js"></script>

<script src="<%=basePath%>js/topicSelectionApproval.js"></script>

<script src="<%=basePath%>dwr/interface/titleDWR.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>

</head>
<body style="width: 100%; height: 100%">
	<table style="width: 100%; border: 0; cellpadding: 0; cellspacing: 0;">
		<tr>
			<td style="background: url(../images/mail_leftbg.gif) repeat-y" />
			<td>
				<table id="topicSelectionApprovaldg" 
					style="width: auto; height: auto;">

				</table>
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
<script>
	topicSelectionApproval();
</script>
</html>
