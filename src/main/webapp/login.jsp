<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录到毕业论文管理系统</title>

<script type="text/javascript">
	function checkLogin() {
		if ($('#userName').val() == '' || $('#userName').val().length < 4) {
			alert("用户名或密码不能为空或长度不得小于4个字符");
			$('#userName').focus();
			return false;
		} else if ($('#passWord').val() == ''
				|| $('#passWord').val().length < 4) {
			alert("用户名或密码不能为空或长度不得小于4个字符");
			$('#passWord').focus();
			return false;
		} else if ($('#checkCode').val() == null
				|| $('#checkCode').val().length < 4) {
			$('#checkCode').focus();
			document.getElementById('checkCodeMsg').innerHTML = '必须输入验证码';
			return false;
		}
		$('#myform').submit();
	}
</script>
<script language="JavaScript">
	function correctPNG() {
		var arVersion = navigator.appVersion.split("MSIE");
		var version = parseFloat(arVersion[1]);
		if ((version >= 5.5) && (document.body.filters)) {
			for ( var j = 0; j < document.images.length; j++) {
				var img = document.images[j];
				var imgName = img.src.toUpperCase();
				if (imgName.substring(imgName.length - 3, imgName.length) == "PNG") {
					var imgID = (img.id) ? "id='" + img.id + "' " : "";
					var imgClass = (img.className) ? "class='" + img.className
							+ "' " : "";
					var imgTitle = (img.title) ? "title='" + img.title + "' "
							: "title='" + img.alt + "' ";
					var imgStyle = "display:inline-block;" + img.style.cssText;
					if (img.align == "left")
						imgStyle = "float:left;" + imgStyle;
					if (img.align == "right")
						imgStyle = "float:right;" + imgStyle;
					if (img.parentElement.href)
						imgStyle = "cursor:hand;" + imgStyle;
					var strNewHTML = "<span "
							+ imgID
							+ imgClass
							+ imgTitle
							+ " style=\""
							+ "width:"
							+ img.width
							+ "px; height:"
							+ img.height
							+ "px;"
							+ imgStyle
							+ ";"
							+ "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
							+ "(src=\'" + img.src
							+ "\', sizingMethod='scale');\"></span>";
					img.outerHTML = strNewHTML;
					j = j - 1;
				}
			}
		}
	}
	window.attachEvent("onload", correctPNG);
</script>
<script language="javascript">
	function myReload() {
		document.getElementById("snowCheckCode").src = document
				.getElementById("snowCheckCode").src
				+ "?nocache=" + new Date().getTime();
	}
</script>
<link href="css/skin.css" rel="stylesheet" type="text/css">
<script src="jquery-easyui/jquery-1.8.0.js"></script>
<style>
body,p,h1,h2,h3,h4,h5,h6,blockquote,ul,ol,li,dl,dt,dd,pre,form,fieldset,legend,button,input,textarea,table,tr,th,td
	{
	margin-top: 0px;
	margin-left: 0px;
	margin-bottom: 0px;
	margin-right: 0px;
	padding-left: 0px;
	padding-bottom: 0px;
	padding-right: 0px;
	padding-top: 0px;
}

body {
	font: 12px/1.5 Tahoma, Helvetica, Arial, sans-serif;
}

ul,ol,li {
	list-style: none;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
}

h1,h2,h3,h4,h5,h6,button,input,select,textarea {
	font-size: 100%;
	font-weight: normal;
}

img {
	border: none;
}

input {
	vertical-align: middle;
}

.log_body {
	width: 100%;
	height: 100%;
}

.log_top {
	width: 360px;
	height: 100px;
	margin: 0 auto;
}

.log_left {
	width: 444px;
	height: 238px;
	float: left;
}

.log_left {
	width: 444px;
	height: 238px;
	float: left;
	margin-left: 10px;
	text-align: right;
}

.log_right {
	width: 270px;
	height: 200px;
	float: left;
	margin-left: 40px;
	text-align: right;
}

.log_right input {
	height: 30px;
	font-size: 20px;
}

.login_top {
	width: 100%;
	height: 41px;
	background: url(images/login-top-bg.gif) repeat-x;
}

.log_main {
	background: url(images/login_bg.jpg) repeat-x;
}

.log_mainbody {
	width: 900px;
	height: 500px;
	margin: 0 auto;
	padding-top: 30px;
}

.login_bottom {
	width: 100%;
	height: 20px;
	background: url(images/login-buttom-bg.gif) repeat-x;
	text-align: center;
	color: #FFF;
}

.wel {
	width: 242px;
	height: 138px;
	float: right;
}
</style>
</head>
<body>
	<div class="log_body">
		<div class="login_top"></div>
		<div class="log_main">
			<div class="log_mainbody">
				<div class="log_top" style="margin-bottom: 40px;">
					<img src="images/logo.png" />
				</div>
				<div class="log_left">
					<img src="images/home2.jpg" />
				</div>
				<div class="log_right">
					<form id="myform" name="myform" action="struts/login" method="post">
						<label>用户名：</label><input style="width: 210px;" id="userName"
							name="userName"><br /> <br /> <label>密 码：</label> <input
							size="25" style="width: 210px;" type="password" id="passWord"
							name="passWord"> <br /> <br /> <label>验证码：</label><input
							id="checkCode" name="checkCode" type="text" id="checkCode"
							title="验证码不区分大小写" style="width: 100px;" maxlength="4" />
						&nbsp;&nbsp;<img src="SnowCheckCode"
							style="width: 100px; height: 30px; border: 0; vertical-align: middle; margin: 0; cursor: pointer;"
							name="snowCheckCode" id="snowCheckCode" onClick="myReload()" />
						<label id="checkCodeMsg" style="color: red;"> <c:out
								value="${sessionScope.checkCodeError}"></c:out>
						</label> &nbsp;&nbsp; <a style="cursor: pointer;" onClick="myReload()">看不清？换张图片</a><br />
						<br />
						<button
							style="width: 210px; height: 30px; line-height: 30px; background: url(images/btn.png) no-repeat; border: 0px; color: #FFF; cursor: pointer;"
							tabindex="5" onclick="return checkLogin()">登 录</button>
					</form>
				</div>
			</div>
		</div>
		<div class="login_bottom">Copyright &copy; 2012-2015</div>
	</div>
</body>
</html>
