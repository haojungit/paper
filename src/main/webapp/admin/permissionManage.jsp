<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="../commonalityPage_top.jsp"></jsp:include>
<link href="<%=basePath%>css/skin.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/icon.css">

<script src='<%=basePath%>/dwr/interface/permission.js'></script>
<script src='<%=basePath%>/dwr/engine.js'></script>
<script src='<%=basePath%>/dwr/util.js'></script>

<script src="<%=basePath%>jquery-easyui/jquery-1.8.0.js"></script>
<script src="<%=basePath%>jquery-easyui/jquery.easyui.min.js"></script>



<body style="width: 100%; height: 100%">
	<input id="teacherID" type="hidden" value="${teacher.teacherId}" />
	<table style="width: 100%; border: 0; cellpadding: 0; cellspacing: 0;">
		<tr>
			<td style="background: url(../images/mail_leftbg.gif) repeat-y" />
			<td>

				<table id="dg" class="easyui-datagrid"
					style="width: auto; height: auto;">

				</table>

				<div id="toolbar" align="right">
					<a id="newp" href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-add" plain="true" onclick="newPermission();">新建权限</a>
					<a id="editp" href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-edit" plain="true" onclick="editPermission();">编辑</a>
					<a id="delp" href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-remove" plain="true" onclick="delsoftOrRenew();">禁用/恢复</a>
				</div>
				<div id="permissiondlg" class="easyui-dialog"
					style="width: 450px; height: 220px; padding: 10px 20px"
					closed="true" buttons="#dlg-buttons" modal="true">
					<form id="fm" method="post" novalidate>
						<div class="fitem">
							<input name="id" type="hidden" size="45" id="ft1">
						</div>
						<div class="fitem">
							<label>权限描述:</label> <input name="description" type="text"
								size="45" id="ft2">
						</div>
						<div class="fitem">
							<label>权限链接:</label> <input name="permission" type="text"
								size="45" id="ft4">
						</div>
						<div id="dlg-buttons">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-ok" onclick="saveOrUpdate()">保存</a> <a
								href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-cancel"
								onclick="javascript:$('#permissiondlg').dialog('close')">取消</a>
						</div>
					</form>
				</div>
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

	<script type="text/javascript"
		src="<%=basePath%>js/permissionManage.js"></script>
</body>
</html>
