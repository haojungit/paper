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

<script src="<%=basePath%>js/leaderCouncil.js"></script>
<script src="<%=basePath%>js/public.js"></script>

<script src="<%=basePath%>dwr/interface/councilDWR.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>

</head>
<body style="width: 100%; height: 100%">
	<input id="councilID" type="hidden" value="${teacher.teacherId}" />
	<table style="width: 100%; border: 0; cellpadding: 0; cellspacing: 0;">
		<tr>
			<td style="background: url(../images/mail_leftbg.gif) repeat-y" />
			<td>
				<table id="leadercouncildg" class="easyui-datagrid"
					style="width: auto; height: auto;">
					<thead>

					</thead>
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
	<div id="reviewReplyCouncil" class="easyui-dialog"
		style="width: 600px; height: auto; padding: 10px 20px" closed="true"
		buttons="#reviewReplyCouncil-buttons">
		<form id="reviewReplyCouncilFrom" method="post" novalidate>
			<div class="fitem">
				系（教研室）主任意见： <br />
				<textarea id="deanSug" class="easyui-validatebox" required rows="7"
					cols="75"></textarea>
				<br />
				<p align="left" style="margin-bottom: 10px;">
					审批日期 ： <input id="deanDate" class="easyui-datebox" required
						data-options="formatter:myformatter,parser:myparser,editable:false"></input>
				</p>
			</div>
		</form>
	</div>

	<div id="reviewReplyCouncil-buttons">
		<a id="reviewReplyCouncil-pass" href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-ok" onclick="examineReplyCpuncil(1)">通过</a><a
			id="reviewReplyCouncil-noPass" href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-cancel" onclick="examineReplyCpuncil(2)">不通过</a>
		<a id="chatbutton" href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-assign">发送消息</a> <a href="javascript:void(0)"
			class="easyui-linkbutton"
			onclick="javascript:$('#reviewReplyCouncil').dialog('close');"
			iconCls="icon-cancel">取消</a>
	</div>

</body>
<script>
	leaderCouncilInfo();
</script>
</html>
