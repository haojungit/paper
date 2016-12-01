function topicSelection(studentMajor) {

	var oPage = {
		pageIndex : 1,
		pageSize : 15
	};

	var toolbar = [ {
		text : '选择题目',
		iconCls : 'icon-add',
		handler : function() {
			var row = $('#topicSelectiondg').datagrid('getSelected');
			if (row)
				titleDWR.chooseTitle(row.paperTitleId, function(msg) {
					topicSelectiondgReload(studentMajor);
					if (msg == "恭喜,选题成功!") {
						sendToMyTeacher("系统提示：选题成功，现在可以给指导教师发送消息。");
					}
					$.messager.alert('系统提示', msg);
				});
			else
				$.messager.alert('系統提示', '请先选择一个题目!', 'warning');
		}
	} ];

	titleDWR.findByStudentMajor(studentMajor, oPage.pageIndex, oPage.pageSize,
			function(oData) {
				$('#topicSelectiondg').datagrid('loading');
				$('#topicSelectiondg').datagrid('loadData', {
					"total" : oData.totalCount,
					"rows" : oData.list
				});
			});
	// 初始化dategrid

	$('#topicSelectiondg').datagrid(
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
							+ '</p><td style="border:0"></td></tr></table>';
				},
				onLoadSuccess : function() {
					// 加载完数据关闭等待的div
					$('#topicSelectiondg').datagrid('loaded');
				},
				columns : [ [ {
					field : 'paperTitleId'
				}, {
					field : 'paperTeacher',
					title : '指导教师',
					width : 150,
					align : 'center',
					formatter : function(value, row, index) {
						return row.paperTeacher.teacherName;
					}
				}, {
					field : 'paperTitleName',
					title : '题目名称',
					width : 240,
					align : 'center'
				}, {
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
				}, {
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
				}, {
					field : 'paperTitlePlatform',
					title : '开发平台',
					width : 100,
					align : 'center'
				}, {
					field : 'paperTitleIntroduce',
					title : '题目介绍',
					width : 300,
					align : 'center'
				} ] ]
			});

	$('#topicSelectiondg').datagrid('hideColumn', 'paperTitleId');

	$('#topicSelectiondg').datagrid('getPager').pagination(
			{

				displayMsg : '当前显示从 [{from}] 到 [{to}] 共[{total}]条记录',

				onSelectPage : function(pPageIndex, pPageSize) {

					$('#topicSelectiondg').datagrid('loading');

					oPage.pageIndex = pPageIndex;
					oPage.pageSize = pPageSize;

					titleDWR.findByStudentMajor(studentMajor, oPage.pageIndex,
							oPage.pageSize, function(oData) {
								$('#topicSelectiondg').datagrid('loading');
								$('#topicSelectiondg').datagrid('loadData', {
									"total" : oData.totalCount,
									"rows" : oData.list
								});
							});
				}
			});
}

function topicSelectiondgReload(studentMajor) {

	var options = $('#topicSelectiondg').datagrid('getPager')
			.data('pagination').options;

	titleDWR.findByStudentMajor(studentMajor, options.pageNumber,
			options.pageSize, function(oData) {
				$('#topicSelectiondg').datagrid('loading');
				$('#topicSelectiondg').datagrid('loadData', {
					"total" : oData.totalCount,
					"rows" : oData.list
				});
			});
}
