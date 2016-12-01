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

<script src="<%=basePath%>js/reviewPaperTopics.js"></script>
<script src="<%=basePath%>js/public.js"></script>
<script src="<%=basePath%>js/msg.js"></script>

<script src="<%=basePath%>dwr/interface/titleDWR.js"></script>
<script src="<%=basePath%>dwr/interface/msgService.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>
<script src="<%=basePath%>js/reviewPaperTopics.js"></script>
<script src="<%=basePath%>js/msg.js"></script>

</head>
<body class="Manages">
	<table style="width: 100%; border: 0; cellpadding: 0; cellspacing: 0;">
		<tr>
			<td style="background: url(../images/mail_leftbg.gif) repeat-y" />
			<td>
				<table id="leaderTitledg" style="width: auto; height: auto;">
				</table>
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

	<div id="reviewPaperTopics" class="easyui-dialog"
		style="width: 730px; height: auto; padding: 10px 20px" closed="true"
		buttons="#reviewPaperTopics-buttons">
		<div id="reviewPaperTopicsMSG" class="ftitle"></div>
		<form id="reviewPaperTopicsFrom" method="post" novalidate>
			<div class="fitem">
				系（教研室）主任意见： <br />
				<textarea id="paperTitleDeanSug" class="easyui-validatebox" required
					rows="10" cols="75"></textarea>
				<br />
				<p align="left" style="margin-bottom: 10px;">
					审批日期 ： <input id="paperTitleApproveDate" class="easyui-datebox"
						required
						data-options="formatter:myformatter,parser:myparser,editable:false"></input>
				</p>
			</div>
		</form>
	</div>

	<div id="reviewPaperTopics-buttons">
		<a id="reviewPaperTopics-pass" href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-ok">通过</a><a
			id="reviewPaperTopics-noPass" href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-cancel">不通过</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#reviewPaperTopics').dialog('close');"
			iconCls="icon-cancel">取消</a>
	</div>

	<div id="selectPaperTeacher" style="display: inline;">
		<div style="padding-top: 3px; margin-left: 3px">
			待审核列表(教师名称)： <select id="paperTeacherNames" class="easyui-combobox"
				data-options="editable:false" style="width: 155px; height: auto;"></select>

		</div>
	</div>
</body>
<script>
	reviewPaperTopicsLoad();
	leaderGetTitleInfo();
</script>
</html>
