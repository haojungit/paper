function getStudentInfo() {
	// 定义全局变量记录分页信息

	var oPage = {
		pageIndex : 1,
		pageSize : 15
	};

	var toolbar = [ {
		id : 'addS',
		text : '增加',
		iconCls : 'icon-add',
		handler : function() {
			$('#studentSave').unbind("click");

			$('#addStudent').dialog('open').dialog('setTitle', '增加学生信息');
			$('#studentform').form('clear');
			$('#studentNumber').removeAttr("disabled");
			$('#studentSave').click(function() {
				addStudent();
			});
		}
	}, {
		id : 'alertS',
		text : '修改',
		iconCls : 'icon-edit',
		handler : function() {
			$('#studentSave').unbind("click");

			var row = $('#studentdg').datagrid('getSelected');
			if (row) {
				$('#addStudent').dialog('open').dialog('setTitle', '修改学生信息');
				$('#studentform').form('clear');
				$('#studentform').form('load', row);
				$('#studentNumber').attr("disabled", "disabled");
				$('#studentSave').click(function() {
					updateStudent(row.studentId);
				});
			} else
				$.messager.alert('系統提示', '请先选择一行!', 'warning');
		}
	}, {
		id : 'RDS',
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var row = $('#studentdg').datagrid('getSelected');
			if (row)
				updateStudentFlag(row.studentId);
			else
				$.messager.alert('系統提示', '请先选择一行!', 'warning');
		}
	}, {
		id : 'resetPsw',
		text : '密码重置',
		iconCls : 'icon-reset',
		handler : function() {
			var row = $('#studentdg').datagrid('getSelected');
			if (row)
				resetStudentPassword(row.studentId);
			else
				$.messager.alert('系統提示', '请先选择一行!', 'warning');
		}
	}, '-', {
		text : '批量导入',
		iconCls : 'icon-import',
		handler : function() {
			$('#importStudent').dialog('open').dialog('setTitle', '批量导入');
		}
	} ];

	student.findAll(oPage.pageIndex, oPage.pageSize, function(oData) {
		$('#studentdg').datagrid('loading');
		// 使用loadDate方法加载Dao层返回的数据
		$('#studentdg').datagrid('loadData', {
			"total" : oData.totalCount,
			"rows" : oData.list
		});
	});

	// 初始化dategrid

	$('#studentdg')
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
								$('#alertS').linkbutton("disable");
								$('#resetPsw').linkbutton("disable");
								if ($('#RDS').html() != '<span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left">恢复</span></span>')
									;
								$('#RDS')
										.html(
												'<span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left">恢复</span></span>');
							} else {
								$('#alertS').linkbutton("enable");
								$('#resetPsw').linkbutton("enable");
								if ($('#RDS').html() != '<span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left">删除</span></span>')
									;
								$('#RDS')
										.html(
												'<span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left">删除</span></span>');
							}
						},

						onLoadSuccess : function() {
							// 加载完数据关闭等待的div
							$('#studentdg').datagrid('loaded');
							$(".datagrid-toolbar").append($('#searchStudent'));
						},

						columns : [ [

						{
							field : 'studentId'
						},

						{
							field : 'ck',
							checkbox : true
						},

						{
							field : 'studentNumber',
							title : '学号',
							width : 100,
							align : 'center'
						},

						{
							field : 'studentName',
							title : '姓名',
							width : 80,
							align : 'center'
						},

						{
							field : 'studentSex',
							title : '性别',
							width : 50,
							align : 'center'
						},

						{
							field : 'studentAge',
							title : '年龄',
							width : 50,
							align : 'center'
						},

						{
							field : 'studentPhone',
							title : '电话',
							width : 100,
							align : 'center'
						},

						{
							field : 'studentGrade',
							title : '年级',
							width : 100,
							align : 'center'
						},

						{
							field : 'studentFaculty',
							title : '院系',
							width : 180,
							align : 'center'
						},

						{
							field : 'studentMajor',
							title : '专业',
							width : 100,
							align : 'center'
						},

						{
							field : 'studentDirection',
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
									return "删除";
								else
									return "正常";
							}
						}

						] ]

					});

	$('#studentdg').datagrid('hideColumn', 'studentId');

	$('#studentdg').datagrid('getPager').pagination({

		displayMsg : '当前显示从 [{from}] 到 [{to}] 共[{total}]条记录',

		onSelectPage : function(pageIndex, pageSize) {

			$('#studentdg').datagrid('loading');

			// 改变oPage的参数值，用于下次查询传给数据层查询指定页码的数据

			oPage.pageIndex = pageIndex;

			oPage.pageSize = pageSize;

			// 异步获取数据到javascript对象，入参为查询条件和页码信息
			student.findAll(oPage.pageIndex, oPage.pageSize, function(oData) {
				// 使用loadDate方法加载DWR返回的数据
				$('#studentdg').datagrid('loadData', {
					"total" : oData.totalCount,
					"rows" : oData.list
				});
			});
		}
	});
}

/**
 * 添加学生信息
 * 
 * @param infoID
 *            新行的行号
 */
function addStudent(infoID) {

	if (!$('#studentform').form('validate'))
		return false;

	$('#studentSave').linkbutton("disable");

	$('#studentdg').datagrid('loading');
	var paperStudent = {};
	paperStudent.studentNumber = $('#studentNumber').val();
	paperStudent.studentName = $('#studentName').val();
	paperStudent.studentSex = $('#studentSex').val();
	paperStudent.studentAge = $('#studentAge').val();
	paperStudent.studentPhone = $('#studentPhone').val();
	paperStudent.studentGrade = $('#studentGrade').val();
	paperStudent.studentFaculty = $('#studentFaculty').val();
	paperStudent.studentMajor = $('#studentMajor').val();
	paperStudent.studentDirection = $('#studentDirection').val();

	student.findByNumber(paperStudent.studentNumber, function(date) {
		if (date == null)
			student.save(paperStudent, function(msg) {
				if (msg != true) {
					$.messager.alert('系統提示', '保存失败!', 'error');
					return;
				} else {
					studentReload();
					$('#addStudent').dialog('close'); // close the dialog
				}
				$('#studentSave').linkbutton("enable");
			});
		else {
			$.messager.alert('系統提示', '学生学号已存在!', 'error');
			return;
		}
	});
}

function updateStudent(studentID) {
	alert(studentID);

	if (!$('#studentform').form('validate'))
		return false;

	$('#studentSave').linkbutton("disable");

	$('#studentdg').datagrid('loading');
	var studentName = $('#studentName').val();
	var studentSex = $('#studentSex').val();
	var studentAge = $('#studentAge').val();
	var studentPhone = $('#studentPhone').val();
	var studentGrade = $('#studentGrade').val();
	var studentFaculty = $('#studentFaculty').val();
	var studentMajor = $('#studentMajor').val();
	var studentDirection = $('#studentDirection').val();

	student.findById(studentID, function(paperStudent) {
		paperStudent.studentName = studentName;
		paperStudent.studentSex = studentSex;
		paperStudent.studentAge = studentAge;
		paperStudent.studentPhone = studentPhone;
		paperStudent.studentGrade = studentGrade;
		paperStudent.studentFaculty = studentFaculty;
		paperStudent.studentMajor = studentMajor;
		paperStudent.studentDirection = studentDirection;
		student.update(paperStudent, function(msg) {
			if (msg != true) {
				$.messager.alert('系統提示', '更新失败!', 'error');
				return;
			} else {
				studentReload();
				$('#addStudent').dialog('close');
			}
			$('#studentSave').linkbutton("enable");
		});
	});
}

function updateStudentFlag(studentID) {

	student.findById(studentID, function(paperStudent) {
		var flag = 1;
		if (paperStudent.flag == 1) {
			flag = 0;
			$.messager.confirm('警告', '如删除此学生,会同时删除学生的用户和角色,是否确认?', function(r) {
				if (r) {
					$('#studentdg').datagrid('loading');
					student.updateStudentFlag(paperStudent, flag,
							function(msg) {
								if (msg != true) {
									$.messager.alert('系統提示', '操作失败!', 'error');
									return;
								} else
									studentReload();
							});
				} else {
					$('#studentdg').datagrid('loaded');
					return;
				}
			});
		} else {
			flag = 1;
			$('#studentdg').datagrid('loading');
			student.updateStudentFlag(paperStudent, flag, function(msg) {
				if (msg != true) {
					$.messager.alert('系統提示', '操作失败!', 'error');
					return;
				} else {
					studentReload();
					$('#addStudent').dialog('close');
				}
			});
		}
	});
}

function studentUploadExcel() {

	if (!$('#importStudentForm').form('validate')) {
		$.messager.alert('系統提示', '请选择Excel(xls或xlsx)文件!', 'warning');
		return;
	}

	loading(true, $("#importStudent"), '正在上传,请稍候...');

	$('#importStudentForm').form('submit', {
		url : "../struts/uploadExcel",
		success : function(result) {
			$.messager.alert('系統提示', result.replace(/"/g, ''), 'info');
			loading(false, $("#importStudent"));
		}
	});

}

function importStudent() {

	loading(true, $("#importStudent"), '正在导入,请稍候...');

	$('#importStudentForm').form('submit', {
		url : "../struts/importStudent",
		success : function(result) {
			$.messager.alert('系統提示', result.replace(/"/g, ''), 'info');
			studentReload();
			loading(false, $("#importStudent"));
		}
	});

}

function resetStudentPassword(studentID) {

	$('#studentdg').datagrid('loading');

	student.resetStudentPassword(studentID, function(msg) {
		if (msg != true) {
			$.messager.alert('系統提示', '重置失败!', 'error');
			return;
		} else {
			studentReload();
			$.messager.alert('系統提示', '重置成功!', 'info');
		}
	});

}

function studentReload() {
	var options = $('#studentdg').datagrid('getPager').data('pagination').options;
	student.findAll(options.pageNumber, options.pageSize, function(oData) {
		// 使用loadDate方法加载DWR返回的数据
		$('#studentdg').datagrid('loadData', {
			"total" : oData.totalCount,
			"rows" : oData.list
		});
	});
}

function doSearchStudent(value, name) {
	var options = $('#studentdg').datagrid('getPager').data('pagination').options;
	if (value == '' || value.length == 0)
		student.findAll(options.pageNumber, options.pageSize, function(oData) {
			// 使用loadDate方法加载DWR返回的数据
			$('#studentdg').datagrid('loadData', {
				"total" : oData.totalCount,
				"rows" : oData.list
			});
		});
	else
		student.findByProperty(name, value, options.pageNumber,
				options.pageSize, function(oData) {
					// 使用loadDate方法加载DWR返回的数据
					$('#studentdg').datagrid('loadData', {
						"total" : oData.totalCount,
						"rows" : oData.list
					});
				});
}
