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
<script src='<%=basePath%>dwr/interface/student.js'></script>
<script src='<%=basePath%>dwr/engine.js'></script>
<script src='<%=basePath%>dwr/util.js'></script>
<script src='<%=basePath%>js/roleManage.js'></script>
<script src='<%=basePath%>js/public.js'></script>

<body class="Manages">
	<table style="width: 100%; border: 0; cellpadding: 0; cellspacing: 0;">
		<tr>
			<td style="background: url(../images/mail_leftbg.gif) repeat-y" />
			<td valign="top" bgcolor="#F7F8F9">
				<table id="roledg" style="width: auto; height: auto;">

				</table>

				<div id="addRole" class="easyui-dialog"
					style="width: 400px; height: 210px; padding: 10px 20px"
					closed="true" buttons="#addRole-buttons">
					<div class="ftitle">角色信息</div>
					<form id="roleform" method="post" novalidate>
						<input type="hidden" name="roleId">
						<div class="fitem">
							<label>* 角色名称:</label> <input id="roleName" name="roleName"
								class="easyui-validatebox" required="true">
						</div>
						<br />
						<div class="fitem">
							<label>角色描述:</label> <input id="description" name="description">
						</div>
					</form>
				</div>
				<div id="addRole-buttons">
					<a id="roleSave" href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-ok">保存</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-cancel"
						onclick="javascript:$('#addRole').dialog('close')">取消</a>
				</div>

				<div id="assigningPermissions" class="easyui-dialog"
					style="width: 550px; height: 350px; padding: 10px 20px"
					closed="true" buttons="#assigningPermissions-buttons">
					<div id="ARPermissions" class="ftitle"></div>
					<input type="hidden" id="roleManageRoleId" />
					<div class="select_side" style="padding-left: 30px;">
						<p>待选区</p>
						<select id="permissions_selectL" name="permissions_selectL"
							multiple="multiple">
						</select>
					</div>
					<div class="select_opt">
						<p id="permissions_toright" title="添加">></p>
						<p id="permissions_toleft" title="移除"><</p>
					</div>
					<div class="select_side">
						<p>已选区</p>
						<select id="permissions_selectR" name="permissions_selectR"
							multiple="multiple">
						</select>
					</div>
				</div>

				<div id="assigningPermissions-buttons">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-ok"
						onclick="javascript:$('#assigningPermissions').dialog('close')">完成</a>
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

<script>
	getRoleInfo();
	roleMultiselect2side();
</script>

