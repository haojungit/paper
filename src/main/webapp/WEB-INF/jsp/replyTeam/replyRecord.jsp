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
<script src='<%=basePath%>js/My97DatePicker/WdatePicker.js'></script>
<script src="<%=basePath%>js/replyRecord.js"></script>

<script src="<%=basePath%>dwr/interface/titleDWR.js"></script>
<script src="<%=basePath%>dwr/interface/paperRecordDWR.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>
<script type="text/javascript">
	function myformatter(date) {
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '年' + (m < 10 ? ('0' + m) : m) + '月'
				+ (d < 10 ? ('0' + d) : d)+"日";
	}
	function myparser(s) {
		if (!s)
			return new Date();
		var ss = (s.split('-'));
		var y = parseInt(ss[0], 10);
		var m = parseInt(ss[1], 10);
		var d = parseInt(ss[2], 10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
			return new Date(y, m - 1, d);
		} else {
			return new Date();
		}
	}
</script>
</head>
<body style="width: 100%; height: 100%">
	<input id="teacherID" type="hidden" value="${teacher.teacherId}" />
	<table style="width: 100%; border: 0; cellpadding: 0; cellspacing: 0;">
		<tr>
			<td style="background: url(../images/mail_leftbg.gif) repeat-y" />
			<td>
				<table id="titledg" style="width: auto; height: auto;">

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
	<div id="tb" style="padding: 5px; height: auto" align="center">

		<div>
			院系: <select id="combo1" class="easyui-combobox" panelHeight="auto"
				style="width: 200px">
				<option value="请选择院系">正在获取数据...</option>
			</select> 专业: <select id="combo2" class="easyui-combobox" panelHeight="auto"
				style="width: 200px">

			</select> 年级: <select id="combo3" class="easyui-combobox" panelHeight="auto"
				style="width: 100px">

			</select> <a href="#" id="bt1" class="easyui-linkbutton" iconCls="icon-search"
				onclick="getPage();">查找</a> <a href="#" id="bt2"
				class="easyui-linkbutton" iconCls="icon-search" onclick="showdlg();">录入答辩记录</a>
				<a href="#" id="bt2"
				class="easyui-linkbutton" iconCls="icon-search" onclick="showdlg();">打印</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog" title="录入答辩记录"
		data-options="iconCls:'icon-save'"
		style="width: 800px; height: 350px; padding: 10px" closed="true">
		答辩时间：<br>
		<input id="t1" class="Wdate" type="text" onfocus="WdatePicker()"/>
		<br><br>答辩地点：<br>
		<textarea rows="1" cols="80" id="t2"></textarea>
		<br><br>答辩记录：<br>
		<textarea rows="9" cols="80" id="t3"></textarea>
		<br><br>答辩小组评议意见：<br>
		<textarea rows="9" cols="80" id="t4"></textarea>
		<br><br>小组评议时间：<br>
		<input id="t5" class="Wdate" type="text" onfocus="WdatePicker()"/>
		<br><br>成绩：<br>
		<input type="text" id="t6" /><br>
		<br><br>答辩委员会意见：<br>
		<textarea rows="9" cols="80" id="t7"></textarea>
		<br><br>委员会评议时间：<br>
		<input id="t8" class="Wdate" type="text" onfocus="WdatePicker()"/>
		<br>
		<div align="center">
			<button onclick="addPaperTopics();">提交</button>
			<button onclick="alert('bbbb');">关闭</button>
		</div>
		<br>
		<br>
		<br>
	</div>

</body>
<script>
	allTopicInfo($('#teacherID').val());
</script>
</html>
