function reviewPaperTopicsLoad() {
	titleDWR.findTeacherName(function(data) {
		if (data.length == 0) {
			$.messager.alert('系統提示', '暂时没有需要审批的题目!', 'info');
			top.mainFrame.closePaperTab('题目审批');
			return;
		}
		$('#paperTeacherNames').combobox({
			data : data,
			valueField : 'teacherId',
			textField : 'teacherName'
		});
	});

}

function leaderGetTitleInfo() {

	// 定义全局变量记录分页信息
	var oPage = {
		pageIndex : 1,
		pageSize : 15
	};

	var toolbar = [
			{
				text : '审批题目',
				iconCls : 'icon-add',
				handler : function() {
					var row = $('#leaderTitledg').datagrid('getSelected');
					if (row) {
						$('#reviewPaperTopics').dialog('open').dialog(
								'setTitle', '课题审批');
						$('#reviewPaperTopicsMSG').html(
								'审批题目：<a style="font-size:15px;color:red">'
										+ row.paperTitleName + '</a>');
						$('#reviewPaperTopics-pass').click(function() {
							reviewPaperTopicsSubmit(row.paperTitleId, 2);
						});
						$('#reviewPaperTopics-noPass').click(function() {
							reviewPaperTopicsSubmit(row.paperTitleId, 3);
						});

					} else
						$.messager.alert('系統提示', '请先选择一个题目!', 'warning');
				}
			},
			{
				text : '发送消息',
				iconCls : 'icon-add',
				handler : function() {
					var row = $('#leaderTitledg').datagrid('getSelected');
					if (row) {
						send(row.paperTeacher.teacherNumber,
								row.paperTeacher.teacherName,
								"系統提示：現在您可以给这位老师发送消息了。");
					} else
						$.messager.alert('系統提示', '请先选择一个题目!', 'warning');
				}
			},
			{
				text : '打印',
				iconCls : 'icon-print',
				handler : function() {
					top.mainFrame.addPaperTab('printTopics', '打印教师申报题目审批表',
							'leader/printReviewPaperTopics.jsp');
				}
			} ];

	titleDWR.findByTitleState(1, oPage.pageIndex, oPage.pageSize, function(
			oData) {
		$('#leaderTitledg').datagrid('loading');
		// 使用loadDate方法加载Dao层返回的数据
		$('#leaderTitledg').datagrid('loadData', {
			"total" : oData.totalCount,
			"rows" : oData.list
		});
	});

	// 初始化dategrid

	$('#leaderTitledg').datagrid(
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
				},

				detailFormatter : function(rowIndex, rowData) {
					return '<table><tr>'
							+ '<td style="border:0;padding:10px;">'
							+ '<p>题目介绍: </p><p>' + rowData.paperTitleIntroduce
							+ '</p></tr></table>';
				},
				onLoadSuccess : function() {
					// 加载完数据关闭等待的div
					$('#leaderTitledg').datagrid('loaded');
					$(".datagrid-toolbar").append($('#selectPaperTeacher'));
				},
				columns : [ [
						{
							field : 'paperTitleId'
						},
						{
							field : 'paperTeacher',
							title : '申报教师',
							width : 80,
							align : 'center',
							formatter : function(value, row, index) {
								if (value != null)
									return value.teacherName;
								return '出错了,未读取到教师信息!';
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
							width : 90,
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
							width : 90,
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
									var m = value.getMonth() + 1;
									var d = value.getDate();
									return y + '-' + (m < 10 ? ('0' + m) : m)
											+ '-' + (d < 10 ? ('0' + d) : d);
								} else
									return '';
							}
						} ] ]

			});

	$('#leaderTitledg').datagrid('hideColumn', 'paperTitleId');

	$('#leaderTitledg').datagrid('getPager').pagination(
			{

				displayMsg : '当前显示从 [{from}] 到 [{to}] 共[{total}]条记录',
				onSelectPage : function(pPageIndex, pPageSize) {
					$('#leaderTitledg').datagrid('loading');
					// 改变oPage的参数值，用于下次查询传给数据层查询指定页码的数据
					oPage.pageIndex = pPageIndex;
					oPage.pageSize = pPageSize;
					titleDWR.findByTitleState(1, oPage.pageIndex,
							oPage.pageSize, function(oData) {
								$('#leaderTitledg').datagrid('loading');
								// 使用loadDate方法加载Dao层返回的数据
								$('#leaderTitledg').datagrid('loadData', {
									"total" : oData.totalCount,
									"rows" : oData.list
								});
							});
				}
			});
}

function reviewPaperTopicsSubmit(paperTitleId, state) {

	$('#leaderTitledg').datagrid('loading');
	if (!$('#reviewPaperTopicsFrom').form('validate')) {
		$('#leaderTitledg').datagrid('loaded');
		return false;
	}

	var paperTitleDeanSug = $('#paperTitleDeanSug').val();
	var paperTitleApproveDate = parseDate($('#paperTitleApproveDate').datebox(
			'getValue'));

	titleDWR.findById(paperTitleId, function(paperTitle) {

		paperTitle.paperTitleDeanSug = paperTitleDeanSug;
		paperTitle.paperTitleApproveDate = paperTitleApproveDate;

		if (paperTitle.paperStudent != null)
			if (paperTitle.paperStudent.studentId > 0)
				state = 5;
		titleDWR.approvePaperTitle(paperTitle, state, function(msg) {
			if (msg != true) {
				$.messager.alert('系統提示', '操作失败!', 'error');
				return;
			} else {
				// 课题审批通过
				paperTitleReload();
				$('#reviewPaperTopics').dialog('close');
				$.messager.alert('系統提示', '操作成功!', 'info');
			}
		});
	});
}

function paperTitleReload() {

	var options = $('#leaderTitledg').datagrid('getPager').data('pagination').options;

	titleDWR.findByTitleState(1, options.pageNumber, options.pageSize,
			function(oData) {
				$('#leaderTitledg').datagrid('loading');
				// 使用loadDate方法加载Dao层返回的数据
				$('#leaderTitledg').datagrid('loadData', {
					"total" : oData.totalCount,
					"rows" : oData.list
				});
			});
	reviewPaperTopicsLoad();
}

function printReviewPaperTopics() {

	var rows = $('#leaderTitledg').datagrid('getRows');
	if (rows) {
		for ( var i = 0; i < rows.length; i++)
			top.mainFrame.printTopics.printReviewPaperTopics(
					rows[i].paperTitleName, rows[i].paperTitletype,
					rows[i].paperTitleSource, rows[i].paperTeacher.teacherName,
					rows[i].paperTeacher.teacherJobTitle,
					rows[i].paperTeacher.teacherEducation);
	} else {
		$.messager.alert('系統提示', '没有需要打印的数据!', 'error');
		top.mainFrame.closePaperTab('打印教师申报题目审批表');
	}
}