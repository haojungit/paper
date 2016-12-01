function getTitleInfo(teacherID) {
	// 定义全局变量记录分页信息

	var oPage = {
		pageIndex : 1,
		pageSize : 15
	};

	$("#msgBt").click(function() {
		sendToLeader('您好，我新申报了论文课题，请您审批。');
	});

	var toolbar = [
			{
				id : 'allowTopic',
				text : '通过',
				iconCls : 'icon-add',
				handler : function() {
					var row = $('#titledg').datagrid('getSelected');

					if (row)
						if (row.paperTitleState.paperTitleState != 0) {
							$.messager.alert('系統提示', '此题目不是学生自拟题目!', 'Error');
							return;
						} else
							titleDWR.updateState(row.paperTitleId, 1, function(
									msg) {
								if (msg) {
									$.messager.alert('系統提示', '操作成功!', 'info');
								} else
									$.messager.alert('系統提示', '操作失败!', 'Error');
								myTopicsReload(teacherID);
							});
					else
						$.messager.alert('系統提示', '请先选择一行!', 'warning');

				}
			},

			{
				id : 'notAllowTopic',
				text : '不通过',
				iconCls : 'icon-add',
				handler : function() {
					var row = $('#titledg').datagrid('getSelected');

					if (row)
						if (row.paperTitleState.paperTitleState != 0) {
							$.messager.alert('系統提示', '此题目不是学生自拟题目!', 'Error');
							return;
						} else
							titleDWR.updateState(row.paperTitleId, 3, function(
									msg) {
								if (msg) {
									$.messager.alert('系統提示', '操作成功!', 'info');
								} else
									$.messager.alert('系統提示', '操作失败!', 'Error');
								myTopicsReload(teacherID);
							});
					else
						$.messager.alert('系統提示', '请先选择一行!', 'warning');

				}
			},

			{
				text : '申报新题目',
				iconCls : 'icon-add',
				handler : function() {
					top.mainFrame.addPaperTab('课题申报', '课题申报',
							'teacher/declarePaperTopics.jsp');
				}
			} ];

	titleDWR.findByTeacherId(teacherID, oPage.pageIndex, oPage.pageSize,
			function(oData) {
				$('#titledg').datagrid('loading');
				// 使用loadDate方法加载Dao层返回的数据
				$('#titledg').datagrid('loadData', {
					"total" : oData.totalCount,
					"rows" : oData.list
				});
			});

	// 初始化dategrid

	$('#titledg')
			.datagrid(
					{

						url : null,

						pageList : [ 15, 30, 50, 100 ],

						pagination : true,

						pageSize : 15,

						pageNumber : 1,

						toolbar : toolbar,

						singleSelect : true,

						view : detailview,

						onClickRow : function(rowIndex, rowData) {
							if (rowData.paperTitleState.paperTitleState == 0) {
								$('#allowTopic').linkbutton("enable");
								$('#notAllowTopic').linkbutton("enable");
							} else {
								$('#allowTopic').linkbutton("disable");
								$('#notAllowTopic').linkbutton("disable");
							}
						},

						detailFormatter : function(rowIndex, rowData) {
							return '<table><tr>'
									+ '<td style="border:0;padding:10px;width:50%;">'
									+ '<p>题目介绍: </p><p>'
									+ rowData.paperTitleIntroduce
									+ '</p><td style="border:0"><br/><p>系（教研室）意见: </p><p>'
									+ ((rowData.paperTitleDeanSug == null) ? ''
											: rowData.paperTitleDeanSug)
									+ '</p></td></tr></table>';
						},

						onLoadSuccess : function() {
							// 加载完数据关闭等待的div
							$('#titledg').datagrid('loaded');
						},

						columns : [ [

								{
									field : 'paperTitleId'
								},

								{
									field : 'paperTitleState',
									title : '题目状态',
									width : 220,
									align : 'center',
									formatter : function(value, row, index) {
										if (value != null)
											return value.paperTitleStateDescription;
									}
								},
								{
									field : 'paperStudent',
									title : '选题学生',
									width : 100,
									align : 'center',
									formatter : function(value, row, index) {
										if (value != null)
											return value.studentName;
										else
											return '暂无';
									}
								},

								{
									field : 'paperTitleName',
									title : '题目名称',
									width : 200,
									align : 'center'
								},

								{
									field : 'paperTitleKeywords',
									title : '关键字',
									width : 200,
									align : 'center'
								},

								{
									field : 'paperTitletype',
									title : '题目类型',
									width : 120,
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 'A')
											return '理论研究型';
										else if (value == 'B')
											return '实验研究型';
										else if (value == 'C')
											return '应用型';
										else if (value == 'D')
											return '其它';
									}
								},

								{
									field : 'paperTitleSource',
									title : '题目来源',
									width : 120,
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 'A')
											return '结合教师科研';
										else if (value == 'B')
											return '结合教育教学';
										else if (value == 'C')
											return '结合实验室建设';
										else if (value == 'D')
											return '结合生产实际';
										else if (value == 'E')
											return '自拟';
									}
								},

								{
									field : 'paperTitlePlatform',
									title : '开发平台',
									width : 100,
									align : 'center'
								},

								{
									field : 'paperTitleLimitMajor',
									title : '限选学生专业',
									width : 100,
									align : 'center'
								},

								{
									field : 'paperTitleIntroduce',
									title : '题目介绍',
									width : 100,
									align : 'center'
								},

								{
									field : 'paperTitleReportDate',
									title : '申报时间',
									width : 100,
									align : 'center',
									formatter : function(value, row, index) {
										if (value != null) {
											var y = value.getFullYear();
											var m = value.getMonth();
											var d = value.getDate();
											return y + '-'
													+ (m < 10 ? ('0' + m) : m)
													+ '-'
													+ (d < 10 ? ('0' + d) : d);
										} else
											return '';
									}
								},

								{
									field : 'paperTitleDeanSug',
									title : '系（教研室）意见',
									width : 110,
									align : 'center'
								},

								{
									field : 'paperTitleApproveDate',
									title : '审批时间',
									width : 110,
									align : 'center',
									formatter : function(value, row, index) {
										if (value != null) {
											var y = value.getFullYear();
											var m = value.getMonth();
											var d = value.getDate();
											return y + '-'
													+ (m < 10 ? ('0' + m) : m)
													+ '-'
													+ (d < 10 ? ('0' + d) : d);
										} else
											return '';
									}
								}

						] ]

					});

	$('#titledg').datagrid('hideColumn', 'paperTitleId');

	$('#titledg').datagrid('getPager').pagination(
			{

				displayMsg : '当前显示从 [{from}] 到 [{to}] 共[{total}]条记录',

				onSelectPage : function(pPageIndex, pPageSize) {

					$('#titledg').datagrid('loading');

					// 改变oPage的参数值，用于下次查询传给数据层查询指定页码的数据

					oPage.pageIndex = pPageIndex;

					oPage.pageSize = pPageSize;

					titleDWR.findByTeacherId(teacherID, oPage.pageIndex,
							oPage.pageSize, function(oData) {
								$('#titledg').datagrid('loading');
								// 使用loadDate方法加载Dao层返回的数据
								$('#titledg').datagrid('loadData', {
									"total" : oData.totalCount,
									"rows" : oData.list
								});
							});
				}
			});
}

function addPaperTopics() {

	if (!$('#declarePaperTopicsForm').form('validate'))
		return false;

	var paperTitle = {};
	paperTitle.paperTitleName = $("#paperTitleName").val();
	paperTitle.paperTitleKeywords = $("#paperTitleKeywords").val();
	paperTitle.paperTitletype = $('#paperTitletype').combobox('getValue');
	paperTitle.paperTitleSource = $('#paperTitleSource').combobox('getValue');
	paperTitle.paperTitlePlatform = $("#paperTitlePlatform").val();
	paperTitle.paperTitleLimitMajor = $('#paperTitleLimitMajor').combobox(
			'getValue');
	paperTitle.paperTitleIntroduce = $("#paperTitleIntroduce").val();
	paperTitle.paperTitleReportDate = parseDate($('#paperTitleReportDate')
			.datebox('getValue'));

	if (paperTitle.paperTitleLimitMajor.length == 0
			|| paperTitle.paperTitleLimitMajor == '') {
		$.messager.alert("提示", '请选择限选学生专业!', 'info');
		return;
	}

	titleDWR.save(paperTitle, function(msg) {
		if (msg == '课题申报成功！') {
			$("#paperTitleName").attr("value", "");
			$("#paperTitleKeywords").attr("value", "");
			$("#paperTitleIntroduce").attr("value", "");

		}
		$.messager.alert("提示", msg, 'info');
	});
}

function updatePaperTopics() {

	if (!$('#declarePaperTopicsForm').form('validate'))
		return false;

	var paperTitle = {};
	paperTitle.paperTitleName = $("#paperTitleName").val();
	paperTitle.paperTitleKeywords = $("#paperTitleKeywords").val();
	paperTitle.paperTitletype = $('#paperTitletype').combobox('getValue');
	paperTitle.paperTitleSource = $('#paperTitleSource').combobox('getValue');
	paperTitle.paperTitlePlatform = $("#paperTitlePlatform").val();
	paperTitle.paperTitleLimitMajor = $('#paperTitleLimitMajor').combobox(
			'getValue');
	paperTitle.paperTitleIntroduce = $("#paperTitleIntroduce").val();
	paperTitle.paperTitleReportDate = parseDate($('#paperTitleReportDate')
			.datebox('getValue'));

	if (paperTitle.paperTitleLimitMajor.length == 0
			|| paperTitle.paperTitleLimitMajor == '') {
		$.messager.alert("提示", '请选择限选学生专业!', 'info');
		return;
	}

	titleDWR.save(paperTitle, function(msg) {
		if (msg == '课题申报成功！') {
			$("#paperTitleName").attr("value", "");
			$("#paperTitleKeywords").attr("value", "");
			$("#paperTitleIntroduce").attr("value", "");

		}
		$.messager.alert("提示", msg, 'info');
	});
}

function myTopicsReload(teacherID) {

	$('#titledg').datagrid('loading');
	var options = $('#titledg').datagrid('getPager').data('pagination').options;

	titleDWR.findByTeacherId(teacherID, options.pageNumber, options.pageSize,
			function(oData) {
				$('#titledg').datagrid('loading');
				// 使用loadDate方法加载Dao层返回的数据
				$('#titledg').datagrid('loadData', {
					"total" : oData.totalCount,
					"rows" : oData.list
				});
			});
}