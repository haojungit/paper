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

<script src="<%=basePath%>dwr/interface/userDWR.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>

<script src="<%=basePath%>jquery-easyui/jquery-1.8.0.js"></script>
<script src="<%=basePath%>jquery-easyui/jquery.easyui.min.js"></script>
<body>
	<table style="width: 100%; border: 0; cellpadding: 0; cellspacing: 0;">
		<tr>
			<td style="background: url(images/mail_leftbg.gif) repeat-y" />
			<td valign="top" bgcolor="#F7F8F9"
				style="text-align: center; margin: 0 auto; width: 100%">
				<form id="amendingPersonalPassword" method="post"
					style="padding-top: 30px;">
					<input id="adUserName" value="${user.userName }" type="hidden" />

					<label>旧密码:</label> <input id="oldPassword" type="password"
						class="easyui-validatebox" required="true"> <br /> <label>新密码:</label>
					<input id="newPassword" type="password" class="easyui-validatebox">
					<br /> <label style="margin-left: -25px;">新密码确认:</label> <input
						type="password" id="newPassword2" class="easyui-validatebox"
						validType="number"> <br /> <label>验证码 :</label> <input
						class="easyui-validatebox" validType="mobile"> <br /> <br />
					<input style="margin-left: 50px; width: 70px" type="button"
						value="提交" onclick="amendingPersonalPassword()" /> <input
						style="width: 70px" type="reset" value="重置" /> <br />
				</form>
			</td>
			<td style="background: url(images/mail_rightbg.gif) repeat-y">&nbsp;</td>
		</tr>
		<tr class="noprint">
			<td valign="bottom"
				style="background: url(images/buttom_left2.gif) no-repeat; width: 5px; height: 17px;" />
			<td
				style="background: url(images/buttom_bgs.gif) repeat-x; width: 17px; height: 17px;" />
			<td valign="bottom"
				style="background: url(images/buttom_right2.gif) no-repeat; width: 5px; height: 17px;" />
		</tr>
	</table>
</body>
<script>
	function amendingPersonalPassword() {
		var adUserName = $('#adUserName').val();
		var oldPassword = $('#oldPassword').val();
		var newPassword = $('#newPassword').val();
		var newPassword2 = $('#newPassword2').val();
		if (newPassword != newPassword2) {
			alert('两次输入的密码不一样,请确认后重新输入！');
			return false;
		}
		userDWR.updatePassword(adUserName, oldPassword, newPassword, function(
				msg) {
			alert(msg);
		});
	}
</script>