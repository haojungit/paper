function instructorGetStudentInfo() {
	// 定义全局变量记录分页信息

	var oPage = {
		pageIndex : 1,
		pageSize : 15
	};

	var toolbar = [ {
		id : 'resetPsw',
		text : '密码重置',
		iconCls : 'icon-reset',
		handler : function() {
			var row = $('#instructorstudentdg').datagrid('getSelected');
			if (row)
				resetStudentPassword(row.studentId);
			else
				$.messager.alert('系統提示','请先选择一行!','warning');
		}
	} ];

	student.findAll(oPage.pageIndex, oPage.pageSize, function(oData) {
		$('#instructorstudentdg').datagrid('loading');
		// 使用loadDate方法加载Dao层返回的数据
		$('#instructorstudentdg').datagrid('loadData', {
			"total" : oData.totalCount,
			"rows" : oData.list
		});
	});

	// 初始化dategrid

	$('#instructorstudentdg')
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
							$('#instructorstudentdg').datagrid('loaded');
							$(".datagrid-toolbar").append(
									$('#instructorSearchStudent'));
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
							width : 100,
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

	$('#instructorstudentdg').datagrid('hideColumn', 'studentId');

	$('#instructorstudentdg').datagrid('getPager').pagination({

		displayMsg : '当前显示从 [{from}] 到 [{to}] 共[{total}]条记录',

		onSelectPage : function(pageIndex, pageSize) {

			$('#instructorstudentdg').datagrid('loading');

			// 改变oPage的参数值，用于下次查询传给数据层查询指定页码的数据

			oPage.pageIndex = pageIndex;

			oPage.pageSize = pageSize;

			// 异步获取数据到javascript对象，入参为查询条件和页码信息
			student.findAll(oPage.pageIndex, oPage.pageSize, function(oData) {
				// 使用loadDate方法加载DWR返回的数据
				$('#instructorstudentdg').datagrid('loadData', {
					"total" : oData.totalCount,
					"rows" : oData.list
				});
			});
		}
	});
}

function resetStudentPassword(studentID) {

	$('#instructorstudentdg').datagrid('loading');

	student.resetStudentPassword(studentID, function(msg) {
		if (msg != true) {
			$.messager.alert('系統提示','重置失败!','error');
			return;
		} else {
			studentReload();
			$.messager.alert('系統提示','重置成功!','info');
		}
	});

}

function studentReload() {
	var options = $('#instructorstudentdg').datagrid('getPager').data(
			'pagination').options;
	student.findAll(options.pageNumber, options.pageSize, function(oData) {
		// 使用loadDate方法加载DWR返回的数据
		$('#instructorstudentdg').datagrid('loadData', {
			"total" : oData.totalCount,
			"rows" : oData.list
		});
	});
}

function doSearchStudent(value, name) {
	var options = $('#instructorstudentdg').datagrid('getPager').data(
			'pagination').options;
	if (value == '' || value.length == 0)
		student.findAll(options.pageNumber, options.pageSize, function(oData) {
			// 使用loadDate方法加载DWR返回的数据
			$('#instructorstudentdg').datagrid('loadData', {
				"total" : oData.totalCount,
				"rows" : oData.list
			});
		});
	else
		student.findByProperty(name, value, options.pageNumber,
				options.pageSize, function(oData) {
					// 使用loadDate方法加载DWR返回的数据
					$('#instructorstudentdg').datagrid('loadData', {
						"total" : oData.totalCount,
						"rows" : oData.list
					});
				});
}
