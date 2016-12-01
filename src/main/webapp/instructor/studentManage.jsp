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
<script src='<%=basePath%>js/instructorstudentManage.js'></script>
<script src='<%=basePath%>js/public.js'></script>

<body class="Manages">
	<input type="hidden" value="${teacher.teacherUnits }" />
	<table style="width: 100%; border: 0; cellpadding: 0; cellspacing: 0;">
		<tr>
			<td style="background: url(../images/mail_leftbg.gif) repeat-y" />
			<td valign="top" bgcolor="#F7F8F9">
				<table id="instructorstudentdg" style="width: auto; height: auto;">

				</table>

				<div id="instructorSearchStudent" style="display: inline;">
					<div style="padding-top: 3px; margin-left: 3px">
						<input id="instructorssstudent" class="easyui-searchbox"
							data-options="prompt:'Please Input Value',searcher:doSearchStudent"
							style="width: 300px;"></input>

						<div id="instructormmStudent" style="width: 120px"></div>
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

<script>
	instructorGetStudentInfo();

	// 循环列名，生成搜索的下拉列表
	var fields = $('#instructorstudentdg').datagrid('getColumnFields');
	var muit = "";
	for ( var i = 2; i < fields.length; i++) {
		var opts = $('#instructorstudentdg').datagrid('getColumnOption',
				fields[i]);
		muit += "<div name='" + fields[i] + "'>" + opts.title + "</div>";
	};
	$('#instructormmStudent').html($('#instructormmStudent').html() + muit);
	$('#instructorssstudent').searchbox({
		menu : '#instructormmStudent'
	});
</script>
