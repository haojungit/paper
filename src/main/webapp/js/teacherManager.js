function getTeacherInfo() {
	// 定义全局变量记录分页信息

	var oPage = {

		pageIndex : 1,

		pageSize : 15

	};

	var toolbar = [
			{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#teacherSave').unbind("click");

					$('#addTeacher').dialog('open').dialog('setTitle', '增加');
					$('#teacherform').form('clear');
					$('#teacherNumber').removeAttr("disabled");
					$('#teacherSave').click(function() {
						addTeacher();
					});
				}
			},
			{
				id : 'alertT',
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					$('#teacherSave').unbind("click");

					var row = $('#teacherdg').datagrid('getSelected');
					if (row) {
						$('#addTeacher').dialog('open')
								.dialog('setTitle', '修改');
						$('#teacherform').form('clear');
						$('#teacherform').form('load', row);
						$('#teacherNumber').attr("disabled", "disabled");
						$('#teacherSave').click(function() {
							$('#teacherSave').attr('disabled', 'disabled');
							updateTeacher(row.teacherId);
						});
					} else
						$.messager.alert('系統提示', '请先选择一行!', 'warning');
				}
			},
			{
				id : 'RDT',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					$('#teacherSave').unbind("click");

					var row = $('#teacherdg').datagrid('getSelected');
					if (row)
						updateTeacherFlag(row.teacherId);
					else
						$.messager.alert('系統提示', '请先选择一行!', 'warning');
				}
			},
			'-',
			{
				text : '分配角色',
				iconCls : 'icon-assign',
				handler : function() {
					$("#selectL").html('');
					$("#selectR").html('');
					var row = $('#teacherdg').datagrid('getSelected');
					if (!row) {
						$.messager.alert('系統提示', '请先选择一行!', 'warning');
						return;
					}

					var userName = row.teacherName;
					$('#assigningRoles').dialog('open').dialog('setTitle',
							'分配角色');
					$('#ART').html(
							'分配角色给 : <a id="TUN" style="font-size:21px;color:red;">'
									+ userName + '</a>');
					$('#teacherManageUserId').val(row.paperUser.userId);
					$.post("../struts/getAllRole.action",

					function(data) {

						var html = "";
						$.each(data, function(i, item) {
							if (item.roleName != "学生")
								html += '<option value="' + item.roleId + '">'
										+ item.roleName + '</option>';
						});
						$('#selectL').html(html);

						$.post("../struts/getUserRole.action", {
							userId : row.paperUser.userId
						}, function(data) {
							var leftSel = $("#selectL");
							var rightSel = $("#selectR");
							leftSel.find("option").each(function() {
								for ( var i = 0; i < data.length; i++)
									if (data[i] == this.value)
										$(this).remove().appendTo(rightSel);
							});
						});
					});
				}
			},
			'-',
			{
				text : '批量导入',
				iconCls : 'icon-import',
				handler : function() {
					$('#importTeacher').dialog('open').dialog('setTitle',
							'批量导入');
				}
			} ];

	teacher.findAll(oPage.pageIndex, oPage.pageSize, function(oData) {
		$('#teacherdg').datagrid('loading');
		// 使用loadDate方法加载Dao层返回的数据
		$('#teacherdg').datagrid('loadData', {
			"total" : oData.totalCount,
			"rows" : oData.list
		});
	});

	// 初始化dategrid

	$('#teacherdg')
			.datagrid(
					{

						url : null,

						pageList : [ 15, 30, 50, 100 ],

						pagination : true,

						pageSize : 15,

						pageNumber : 1,

						toolbar : toolbar,

						singleSelect : true,

						onSelect : function(rowIndex, rowData) {
							if (rowData.flag == 0) {
								$('#alertT').linkbutton("disable");
								// $('#resetPsw').linkbutton("disable");
								if ($('#RDT').html() != '<span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left">恢复</span></span>')
									;
								$('#RDT')
										.html(
												'<span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left">恢复</span></span>');
							} else {
								$('#alertT').linkbutton("enable");
								// $('#resetPsw').linkbutton("enable");
								if ($('#RDT').html() != '<span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left">删除</span></span>')
									;
								$('#RDT')
										.html(
												'<span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left">删除</span></span>');
							}
						},

						onLoadSuccess : function() {
							// 加载完数据关闭等待的div
							$('#teacherdg').datagrid('loaded');
							$(".datagrid-toolbar").append($('#searchTeacher'));
						},

						columns : [ [

						{
							field : 'teacherId'
						},

						{
							field : 'ck',
							checkbox : true
						},

						{
							field : 'teacherNumber',
							title : '工号',
							width : 100,
							align : 'center'
						},

						{
							field : 'teacherName',
							title : '姓名',
							width : 80,
							align : 'center'
						},

						{
							field : 'teacherSex',
							title : '性别',
							width : 50,
							align : 'center'
						},

						{
							field : 'teacherAge',
							title : '年龄',
							width : 50,
							align : 'center'
						}, {
							field : 'teacherPhone',
							title : '电话',
							width : 100,
							align : 'center'
						},

						{
							field : 'teacherUnits',
							title : '单位',
							width : 100,
							align : 'center'
						},

						{
							field : 'teacherMajor',
							title : '专业',
							width : 100,
							align : 'center'
						},

						{
							field : 'teacherEducation',
							title : '学历',
							width : 100,
							align : 'center'
						},

						{
							field : 'teacherJobTitle',
							title : '职称',
							width : 100,
							align : 'center'
						},

						{
							field : 'teacherDirection',
							title : '研究方向',
							width : 110,
							align : 'center'
						},

						{
							field : 'flag',
							title : '状态',
							width : 110,
							align : 'center',
							formatter : function(value, row, index) {
								if (value == 0)
									return "已删除";
								else
									return "正常";
							}
						}

						] ]

					});

	$('#teacherdg').datagrid('hideColumn', 'teacherId');

	$('#teacherdg').datagrid('getPager').pagination({

		displayMsg : '当前显示从 [{from}] 到 [{to}] 共[{total}]条记录',

		onSelectPage : function(pPageIndex, pPageSize) {

			$('#teacherdg').datagrid('loading');

			// 改变oPage的参数值，用于下次查询传给数据层查询指定页码的数据

			oPage.pageIndex = pPageIndex;

			oPage.pageSize = pPageSize;

			// 异步获取数据到javascript对象，入参为查询条件和页码信息
			teacher.findAll(oPage.pageIndex, oPage.pageSize, function(oData) {
				// 使用loadDate方法加载DWR返回的数据
				$('#teacherdg').datagrid('loadData', {
					"total" : oData.totalCount,
					"rows" : oData.list
				});
			});
		}
	});
}

/**
 * 添加教师信息
 * 
 * @param infoID
 *            新行的行号
 */
function addTeacher(infoID) {

	if (!$('#teacherform').form('validate'))
		return false;

	$('#teacherSave').linkbutton("disable");

	var paperTeacher = {};
	paperTeacher.teacherNumber = $('#teacherNumber').val();
	paperTeacher.teacherName = $('#teacherName').val();
	paperTeacher.teacherSex = $('#teacherSex').val();
	paperTeacher.teacherAge = $('#teacherAge').val();
	paperTeacher.teacherPhone = $('#teacherPhone').val();
	paperTeacher.teacherUnits = $('#teacherUnits').val();
	paperTeacher.teacherMajor = $('#teacherMajor').val();
	paperTeacher.teacherEducation = $('#teacherEducation').val();
	paperTeacher.teacherJobTitle = $('#teacherJobTitle').val();
	paperTeacher.teacherDirection = $('#teacherDirection').val();

	teacher.findByNumber(paperTeacher.teacherNumber, function(date) {
		if (date == null)
			teacher.save(paperTeacher, function(msg) {
				if (msg != true) {
					$.messager.alert('系統提示', '保存失败!', 'error');
					return;
				} else {
					teacherReload();
					$('#addTeacher').dialog('close'); // close the dialog
				}
				$('#teacherSave').linkbutton("enable");
			});
		else {
			$.messager.alert('系統提示', '教师工号已存在', 'error');
			return;
		}
	});
}

function updateTeacher(teacherID) {

	if (!$('#teacherform').form('validate'))
		return false;

	$('#teacherSave').linkbutton("disable");

	var teacherName = $('#teacherName').val();
	var teacherSex = $('#teacherSex').val();
	var teacherAge = $('#teacherAge').val();
	var teacherPhone = $('#teacherPhone').val();
	var teacherUnits = $('#teacherUnits').val();
	var teacherMajor = $('#teacherMajor').val();
	var teacherEducation = $('#teacherEducation').val();
	var teacherJobTitle = $('#teacherJobTitle').val();
	var teacherDirection = $('#teacherDirection').val();

	teacher.findById(teacherID, function(paperTeacher) {
		paperTeacher.teacherName = teacherName;
		paperTeacher.teacherSex = teacherSex;
		paperTeacher.teacherAge = teacherAge;
		paperTeacher.teacherPhone = teacherPhone;
		paperTeacher.teacherUnits = teacherUnits;
		paperTeacher.teacherMajor = teacherMajor;
		paperTeacher.teacherEducation = teacherEducation;
		paperTeacher.teacherJobTitle = teacherJobTitle;
		paperTeacher.teacherDirection = teacherDirection;
		teacher.update(paperTeacher, function(msg) {
			if (!msg) {
				$.messager.alert('系統提示', '更新失败', 'error');
				return;
			} else {
				teacherReload();
				$('#addTeacher').dialog('close');
			}
			$('#teacherSave').linkbutton("enable");
		});
	});
}

function updateTeacherFlag(teacherID) {

	teacher.findById(teacherID, function(paperTeacher) {
		var flag = 1;
		if (paperTeacher.flag == 1) {
			flag = 0;
			$.messager.confirm('警告', '如删除此教师,会同时删除教师的用户和角色,是否确认?', function(r) {
				if (r) {
					$('#teacherdg').datagrid('loading');
					teacher.updateTeacherFlag(paperTeacher, flag,
							function(msg) {
								if (msg != true) {
									$.messager.alert('系統提示', '操作失败', 'error');
									return;
								} else
									teacherReload();
							});
				} else {
					$('#teacherdg').datagrid('loaded');
					return;
				}
			});
		} else {
			flag = 1;
			$('#teacherdg').datagrid('loading');
			teacher.updateTeacherFlag(paperTeacher, flag, function(msg) {
				if (msg != true) {
					$.messager.alert('系統提示', '操作失败', 'error');
					return;
				} else
					teacherReload();
			});
		}
	});
}

function teacherReload() {
	$('#teacherdg').datagrid('loading');
	var options = $('#teacherdg').datagrid('getPager').data('pagination').options;
	teacher.findAll(options.pageNumber, options.pageSize, function(oData) {
		// 使用loadDate方法加载DWR返回的数据
		$('#teacherdg').datagrid('loadData', {
			"total" : oData.totalCount,
			"rows" : oData.list
		});
	});
}
/*
 * function deleteTeacher(teacherID) {
 * 
 * $.messager.confirm('Confirm', '确定删除此教师?', function(r) { if (r) {
 * teacher.findById(teacherID, function(paperTeacher) { if (paperTeacher ==
 * null) { alert("教师信息不存在！！"); return false; } teacher.del(paperTeacher,
 * function(msg) { if (msg != true) { alert("删除失败！"); return; } else
 * teacherReload(); }); }); } }); }
 */
function multiselect2side() {

	var leftSel = $("#selectL");
	var rightSel = $("#selectR");

	$("#toright").bind(
			"click",
			function() {
				var rolelist = [];
				leftSel.find("option:selected").each(function() {
					$(this).remove().appendTo(rightSel);
					rolelist.push(this.value);
				});
				ARUserRoles('../struts/addUserRoles.action', $(
						'#teacherManageUserId').val(), rolelist.join(','),
						rightSel);
			});

	$("#toleft").bind(
			"click",
			function() {
				var rolelist = [];
				rightSel.find("option:selected").each(function() {
					$(this).remove().appendTo(leftSel);
					rolelist.push(this.value);
				});

				ARUserRoles('../struts/removeUserRoles.action', $(
						'#teacherManageUserId').val(), rolelist.join(','),
						leftSel);
			});

	leftSel.dblclick(function() {
		$(this).find("option:selected").each(
				function() {
					ARUserRole('../struts/addUserRole.action', $(
							'#teacherManageUserId').val(), this, rightSel);
				});
	});

	rightSel.dblclick(function() {
		$(this).find("option:selected").each(
				function() {
					ARUserRole('../struts/removeUserRole.action', $(
							'#teacherManageUserId').val(), this, leftSel);
				});
	});
}

function ARUserRole(url, userId, object, targetSel) {

	$('#teacherdg').datagrid('loading');
	loading(true, $("#assigningRoles"), '正在处理，请稍候...');
	$.ajax({
		type : "post",// 发送方式
		url : url,// 路径
		data : {
			roleId : object.value,
			userName : userId
		},
		success : function(msg) {
			if (msg == "SUCCESS")
				$(object).remove().appendTo(targetSel);
			else
				$.messager.alert('系統提示', '操作失败', 'error');
			loading(false, $("#assigningRoles"));
			$('#teacherdg').datagrid('loaded');
		}
	});
}

function ARUserRoles(url, userId, rolelist, targetSel) {

	$('#teacherdg').datagrid('loading');
	loading(true, $("#assigningRoles"), '正在处理，请稍候...');
	$.ajax({
		type : "post",// 发送方式
		url : url,// 路径
		data : {
			rolelist : rolelist,
			userId : userId
		},
		success : function(msg) {
			if (msg != "SUCCESS")
				$.messager.alert('系統提示', '操作失败', 'error');
			$('#teacherdg').datagrid('loaded');
			loading(false, $("#assigningRoles"));
		}
	});
}

function teacherUploadExcel() {

	if (!$('#importTeacherForm').form('validate')) {
		$.messager.alert('系統提示', '请选择Excel(xls或xlsx)文件', 'warning');
		return;
	}

	loading(true, $("#importTeacher"), '正在上传,请稍候...');

	$('#importTeacherForm').form('submit', {
		url : "../struts/uploadExcel",
		success : function(result) {
			$.messager.alert('系統提示', result.replace(/"/g, ''), 'info');
			loading(false, $("#importTeacher"));
		}
	});

}

function importTeacher() {

	loading(true, $("#importTeacher"), '正在导入,请稍候...');

	$('#importTeacherForm').form('submit', {
		url : "../struts/importTeacher",
		success : function(result) {
			$.messager.alert('系統提示', result.replace(/"/g, ''), 'info');
			teacherReload();
			loading(false, $("#importTeacher"));
		}
	});

}

function doSearchTeacher(value, name) {
	var options = $('#teacherdg').datagrid('getPager').data('pagination').options;
	if (value == '' || value.length == 0)
		teacher.findAll(options.pageNumber, options.pageSize, function(oData) {
			// 使用loadDate方法加载DWR返回的数据
			$('#teacherdg').datagrid('loadData', {
				"total" : oData.totalCount,
				"rows" : oData.list
			});
		});
	else
		teacher.findByProperty(name, value, options.pageNumber,
				options.pageSize, function(oData) {
					// 使用loadDate方法加载DWR返回的数据
					$('#teacherdg').datagrid('loadData', {
						"total" : oData.totalCount,
						"rows" : oData.list
					});
				});
}
