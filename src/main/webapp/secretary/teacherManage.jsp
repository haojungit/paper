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

<script src='<%=basePath%>/dwr/interface/teacher.js'></script>
<script src='<%=basePath%>/dwr/engine.js'></script>
<script src='<%=basePath%>/dwr/util.js'></script>
<script src='<%=basePath%>js/teacherManager.js'></script>
<script src='<%=basePath%>js/public.js'></script>

<body class="Manages">
	<table style="width: 100%; border: 0; cellpadding: 0; cellspacing: 0;">
		<tr>
			<td style="background: url(../images/mail_leftbg.gif) repeat-y" />
			<td valign="top" bgcolor="#F7F8F9">
				<table id="teacherdg" style="width: auto; height: auto;">

				</table>

				<div id="addTeacher" class="easyui-dialog"
					style="width: 600px; height: 330px; padding: 10px 20px"
					closed="true" buttons="#addTeacher-buttons">
					<div class="ftitle">教师信息</div>
					<form id="teacherform" method="post" novalidate>
						<div class="fitem">
							<label>* 工号:</label> <input id="teacherNumber"
								name="teacherNumber" class="easyui-validatebox" required="true">
						</div>
						<div class="fitem">
							<label>* 姓名:</label> <input id="teacherName" name="teacherName"
								class="easyui-validatebox" required="true">
						</div>
						<div class="fitem">
							<label>性别:</label> <input id="teacherSex" name="teacherSex"
								class="easyui-validatebox">
						</div>
						<div class="fitem">
							<label>年龄:</label> <input id="teacherAge" name="teacherAge"
								class="easyui-validatebox" validType="number[1,2]">
						</div>
						<div class="fitem">
							<label>电话 :</label> <input id="teacherPhone" name="teacherPhone"
								class="easyui-validatebox" validType="mobile">
						</div>
						<div class="fitem">
							<label>单位 :</label> <input id="teacherUnits" name="teacherUnits"
								class="easyui-validatebox">
						</div>
						<div class="fitem">
							<label>专业 :</label> <input id="teacherMajor" name="teacherMajor"
								class="easyui-validatebox">
						</div>
						<div class="fitem">
							<label>学历 :</label> <input id="teacherEducation"
								name="teacherEducation" class="easyui-validatebox">
						</div>
						<div class="fitem">
							<label>职称 :</label> <input id="teacherJobTitle"
								name="teacherJobTitle" class="easyui-validatebox">
						</div>
						<div class="fitem">
							<label>研究方向 :</label> <input id="teacherDirection"
								name="teacherDirection" class="easyui-validatebox">
						</div>
					</form>
				</div>

				<div id="addTeacher-buttons">
					<a id="teacherSave" href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-ok">保存</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-cancel"
						onclick="javascript:$('#addTeacher').dialog('close')">取消</a>
				</div>

				<div id="assigningRoles" class="easyui-dialog"
					style="width: 550px; height: 350px; padding: 10px 20px"
					closed="true" buttons="#assigningRoles-buttons">
					<div id="ART" class="ftitle"></div>
					<input type="hidden" id="teacherManageUserId" />
					<div class="select_side" style="padding-left: 30px;">
						<p>待选区</p>
						<select id="selectL" name="selectL" multiple="multiple">
						</select>
					</div>
					<div class="select_opt">
						<p id="toright" title="添加">></p>
						<p id="toleft" title="移除"><</p>
					</div>
					<div class="select_side">
						<p>已选区</p>
						<select id="selectR" name="selectR" multiple="multiple">
						</select>
					</div>
				</div>

				<div id="assigningRoles-buttons">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-ok"
						onclick="javascript:$('#assigningRoles').dialog('close')">完成</a>
				</div>

				<div id="importTeacher" class="easyui-dialog"
					style="width: 430px; height: 200px; padding: 10px 20px"
					closed="true">
					<form id="importTeacherForm" method="post"
						enctype="multipart/form-data">
						导入Excel:<br /> <br /> <input name="upload" type="file" required
							class="easyui-validatebox" validType="excel" /><input
							type="button" value="上传" onclick="teacherUploadExcel();" /><input
							type="button" value="导入" onclick="importTeacher();" />
					</form>
					模版下载：<br /> <br /> <a
						href="../upload/bulkImportTemplate/teacher.xls">Excel 2003版本</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="../upload/bulkImportTemplate/teacher.xlsx">Excel 2007版本</a>
				</div>

				<div id="searchTeacher" style="display: inline;">
					<div style="padding-top: 3px; margin-left: 3px">
						<input id="ssteacher" class="easyui-searchbox"
							data-options="prompt:'Please Input Value',searcher:doSearchTeacher"
							style="width: 300px;"></input>

						<div id="mmTeacher" style="width: 120px"></div>
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
	getTeacherInfo();
	multiselect2side();

	// 循环列名，生成搜索的下拉列表
	var fields = $('#teacherdg').datagrid('getColumnFields');
	var muit = "";
	for ( var i = 2; i < fields.length; i++) {
		var opts = $('#teacherdg').datagrid('getColumnOption', fields[i]);
		muit += "<div name='" + fields[i] + "'>" + opts.title + "</div>";
	};
	$('#mmTeacher').html($('#mmTeacher').html() + muit);
	$('#ssteacher').searchbox({
		menu : '#mmTeacher'
	});
</script>
