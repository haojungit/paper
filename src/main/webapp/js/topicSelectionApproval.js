function topicSelectionApproval() {
	// 定义全局变量记录分页信息

	var oPage = {

		pageIndex : 1,

		pageSize : 15

	};

	var toolbar = [
			{
				text : '通过',
				iconCls : 'icon-add',
				handler : function() {

					$('#topicSelectionApprovaldg').datagrid('loading');
					var paperTitleIds = [];
					var selectedRow = $('#topicSelectionApprovaldg').datagrid(
							'getSelections');
					if (selectedRow.length > 0) {
						for ( var i = 0; i < selectedRow.length; i++) {
							paperTitleIds.push(selectedRow[i].paperTitleId);
						}
						titleDWR.updateStateByBatch(paperTitleIds, 5, function(
								msg) {
							if (msg)
								$.messager.alert('系統提示', '选中论文已通过!', 'info');
							else
								$.messager
										.alert('系統提示', '系统异常,请稍后重试!', 'error');
							topicSelectionApprovaldgReload();
						});
					} else {
						$.messager.alert('系統提示', '请先选择一行或多行数据!', 'warning');
						$('#topicSelectionApprovaldg').datagrid('loaded');
					}
				}
			},
			{
				text : '不通过',
				iconCls : 'icon-add',
				handler : function() {

					$('#topicSelectionApprovaldg').datagrid('loading');
					var paperTitleIds = [];
					var selectedRow = $('#topicSelectionApprovaldg').datagrid(
							'getSelections');
					if (selectedRow.length > 0) {
						for ( var i = 0; i < selectedRow.length; i++) {
							paperTitleIds.push(selectedRow[i].paperTitleId);
						}
						titleDWR.updateStateByBatch(paperTitleIds, 2, function(
								msg) {
							if (msg)
								$.messager.alert('系統提示', '选中论文已驳回!', 'info');
							else
								$.messager
										.alert('系統提示', '系统异常,请稍后重试!', 'error');
							topicSelectionApprovaldgReload();
						});
					} else {
						$.messager.alert('系統提示', '请先选择一行或多行数据!', 'warning');
						$('#topicSelectionApprovaldg').datagrid('loaded');
					}
				}
			} ];

	titleDWR.findByTitleState(4, oPage.pageIndex, oPage.pageSize, function(
			oData) {
		$('#topicSelectionApprovaldg').datagrid('loading');
		// 使用loadDate方法加载Dao层返回的数据
		$('#topicSelectionApprovaldg').datagrid('loadData', {
			"total" : oData.totalCount,
			"rows" : oData.list
		});
	});

	// 初始化dategrid
	$('#topicSelectionApprovaldg').datagrid({

		url : null,

		pageList : [ 15, 30, 50, 100 ],

		pagination : true,

		pageSize : 15,

		pageNumber : 1,

		toolbar : toolbar,

		singleSelect : false,

		/* view : detailview, */

		onClickRow : function(rowIndex, rowData) {

		},

		/*
		 * detailFormatter : function(rowIndex, rowData) { return '<table><tr>' + '<td style="border:0;padding:10px;width:50%;">' + '<p>题目介绍:
		 * </p><p>' + rowData.paperTitleIntroduce + '</p><td style="border:0"></td></tr></table>'; },
		 */

		onLoadSuccess : function() {
			// 加载完数据关闭等待的div
			$('#topicSelectionApprovaldg').datagrid('loaded');
		},

		columns : [ [

		{
			field : 'paperTitleId'
		},

		{
			field : 'ck',
			checkbox : true
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
					return '出错了,未读取到学生信息!';
			}
		},

		{
			field : 'paperTeacher',
			title : '申报教师',
			width : 100,
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

		/*
		 * { field : 'paperTitleIntroduce', title : '题目介绍', width : 100, align :
		 * 'center' }
		 */

		] ]

	});

	$('#topicSelectionApprovaldg').datagrid('hideColumn', 'paperTitleId');

	$('#topicSelectionApprovaldg').datagrid('getPager').pagination(
			{

				displayMsg : '当前显示从 [{from}] 到 [{to}] 共[{total}]条记录',

				onSelectPage : function(pPageIndex, pPageSize) {

					$('#topicSelectionApprovaldg').datagrid('loading');

					// 改变oPage的参数值，用于下次查询传给数据层查询指定页码的数据

					oPage.pageIndex = pPageIndex;

					oPage.pageSize = pPageSize;

					titleDWR.findByTitleState(4, oPage.pageIndex,
							oPage.pageSize, function(oData) {
								$('#topicSelectionApprovaldg').datagrid(
										'loading');
								// 使用loadDate方法加载Dao层返回的数据
								$('#topicSelectionApprovaldg').datagrid(
										'loadData', {
											"total" : oData.totalCount,
											"rows" : oData.list
										});
							});
				}
			});
}

function topicSelectionApprovaldgReload() {

	var options = $('#topicSelectionApprovaldg').datagrid('getPager').data(
			'pagination').options;

	titleDWR.findByTitleState(4, options.pageNumber, options.pageSize,
			function(oData) {
				$('#topicSelectionApprovaldg').datagrid('loading');
				// 使用loadDate方法加载Dao层返回的数据
				$('#topicSelectionApprovaldg').datagrid('loadData', {
					"total" : oData.totalCount,
					"rows" : oData.list
				});
			});
}
