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
<script src='<%=basePath%>js/studentManage.js'></script>
<script src='<%=basePath%>js/public.js'></script>

<body class="Manages">

	<table style="width: 100%; border: 0; cellpadding: 0; cellspacing: 0;">
		<tr>
			<td style="background: url(../images/mail_leftbg.gif) repeat-y" />
			<td valign="top" bgcolor="#F7F8F9">
				<table id="studentdg" style="width: auto; height: auto;">

				</table>
				<div id="addStudent" class="easyui-dialog"
					style="width: 600px; height: 330px; padding: 10px 20px"
					closed="true" buttons="#addStudent-buttons">
					<div class="ftitle">学生信息</div>
					<form id="studentform" method="post" novalidate>
						<div class="fitem">
							<label><a style="color: red">*</a> 学号:</label> <input
								id="studentNumber" name="studentNumber"
								validType="number[10,12]" class="easyui-validatebox" required>
						</div>
						<div class="fitem">
							<label><a style="color: red">*</a> 姓名:</label> <input
								id="studentName" name="studentName" class="easyui-validatebox"
								validType="CHSOREN[2,12]" required>
						</div>
						<div class="fitem">
							<label>性别:</label> <input id="studentSex" name="studentSex"
								class="easyui-validatebox" validType="Sex">
						</div>
						<div class="fitem">
							<label>年龄:</label> <input id="studentAge" name="studentAge"
								class="easyui-validatebox" validType="number[1,2]">
						</div>
						<div class="fitem">
							<label>电话 :</label> <input id="studentPhone" name="studentPhone"
								class="easyui-validatebox" validType="mobile">
						</div>
						<div class="fitem">
							<label>年级:</label> <input id="studentGrade" name="studentGrade"
								class="easyui-validatebox" validType="number[4,4]">
						</div>
						<div class="fitem">
							<label>院系 :</label> <input id="studentFaculty"
								name="studentFaculty" class="easyui-validatebox" validType="CHSOREN[3,32]">
						</div>
						<div class="fitem">
							<label>专业 :</label> <input id="studentMajor" name="studentMajor"
								class="easyui-validatebox" validType="CHSOREN[3,10]">
						</div>
						<div class="fitem">
							<label>研究方向 :</label> <input id="studentDirection"
								name="studentDirection" class="easyui-validatebox" validType="CHSOREN[3,10]">
						</div>
					</form>
				</div>

				<div id="addStudent-buttons" style="height: 40px;">
					<a id="studentSave" href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-ok">保存</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-cancel"
						onclick="javascript:$('#addStudent').dialog('close')">取消</a>
				</div>

				<div id="importStudent" class="easyui-dialog"
					style="width: 430px; height: 200px; padding: 10px 20px"
					closed="true">
					<form id="importStudentForm" method="post"
						enctype="multipart/form-data">
						导入Excel:<br /> <br /> <input name="upload" type="file" required
							class="easyui-validatebox" validType="excel" /><input
							type="button" value="上传" onclick="studentUploadExcel();" /> <input
							type="button" value="导入" onclick="importStudent();" />
					</form>
					模版下载：<br /> <br /> <a href="../upload/bulkImportTemplate/student.xls">Excel
						2003版本</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="../upload/bulkImportTemplate/student.xlsx">Excel 2007版本</a>
				</div>

				<div id="searchStudent" style="display: inline;">
					<div style="padding-top: 3px; margin-left: 3px">
						<input id="ssstudent" class="easyui-searchbox"
							data-options="prompt:'Please Input Value',searcher:doSearchStudent"
							style="width: 300px;"></input>

						<div id="mmStudent" style="width: 120px"></div>
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
	getStudentInfo();

	// 循环列名，生成搜索的下拉列表
	var fields = $('#studentdg').datagrid('getColumnFields');
	var muit = "";
	for ( var i = 2; i < fields.length; i++) {
		var opts = $('#studentdg').datagrid('getColumnOption', fields[i]);
		muit += "<div name='" + fields[i] + "'>" + opts.title + "</div>";
	};
	$('#mmStudent').html($('#mmStudent').html() + muit);
	$('#ssstudent').searchbox({
		menu : '#mmStudent'
	});
</script>
