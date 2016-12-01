// 定义全局变量记录分页信息
var oPage = {
	pageIndex : 1,
	pageSize : 15
};
function getPage() {

	var f = $('#combo1').combobox("getText");
	var m = $('#combo2').combobox("getText");
	var g = $('#combo3').combobox("getText");
	if (f == "" || m == "" || g == "") {
		// jQuery.messager.alert('提示:','请选择好院系专业和年级再进行搜索。','info');
		jQuery.messager.alert('提示:', '请选择好院系专业和年级再进行搜索。', 'warning');
		// alert("请选择好院系专业和年级再进行搜索。");
		return;
	}
	$('#titledg').datagrid('loading');
	titleDWR.findPageByFMG(f, m, g, oPage.pageIndex, oPage.pageSize, function(
			oData) {

		// 使用loadDate方法加载Dao层返回的数据
		$('#titledg').datagrid('loadData', {
			"total" : oData.totalCount,
			"rows" : oData.list
		});
		$('#titledg').datagrid('loaded');
	});
}

function allTopicInfo(teacherID) {

	titleDWR.findFaculty(function(tlist) {
		$('#combo1').combobox({
			data : tlist,
			valueField : 'studentFaculty',
			textField : 'studentFaculty',
			editable : false,
			onChange : function(newVal, oldVal) {
				// $("#bt1").linkbutton("disabled");
				titleDWR.findMajorByFaculty(newVal, function(mjlist) {
					$('#combo2').combobox({
						data : mjlist,
						editable : false,
						valueField : 'studentMajor',
						textField : 'studentMajor'
					});
					$("#combo2").combobox('select', mjlist[0].studentMajor);
				});
			}
		});
		$("#combo1").combobox('select', tlist[0].studentFaculty);
	});
	titleDWR.findGrade(function(glist) {
		$('#combo3').combobox({
			data : glist,
			editable : false,
			valueField : 'studentGrade',
			textField : 'studentGrade'
		});
		$("#combo3").combobox('select', glist[0].studentGrade);
	});

	// 初始化dategrid
	$('#titledg').datagrid(
			{
				url : null,
				loadMsg : "正在加载数据，请稍等...",
				pageList : [ 15, 30, 50, 100 ],
				pagination : true,
				pageSize : 15,
				pageNumber : 1,
				toolbar : "#tb",
				singleSelect : true,
				view : detailview,
				onClickRow : function(rowIndex, rowData) {
				},
				detailFormatter : function(rowIndex, rowData) {
					if (rowData.paperRecord == null) {
						return '暂无扩展信息';
					}
					return '答辩记录：' + rowData.paperRecord.record + '<br>'
							+ '答辩小组意见：' + rowData.paperRecord.teamAdvice + '';
				},
				onExpandRow : function(index, row) {

				},
				onLoadSuccess : function() {
					$('#titledg').datagrid('loaded');
				},
				columns : [ [ {
					field : 'paperTitleId'
				}, {
					field : 'paperStudent',
					title : '学号',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						if (value != null)
							return value.studentNumber;
						else
							return '暂无';
					}
				}, {
					field : 'a',
					title : '姓名',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						return row.paperStudent.studentName;
					}
				}, {
					field : 'paperTitleName',
					title : '题目名称',
					width : 200,
					align : 'center'
				}, {
					field : 'paperTeacher',
					title : '指导教师',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						if (row.paperTeacher == null) {
							return '无';
						} else {
							return row.paperTeacher.teacherName;
						}

					}
				}, {
					field : 'paperRecord',
					title : '答辩时间',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						if (row.paperRecord == null) {
							return '无';
						} else {
							return row.paperRecord.replyDate;
						}

					}
				}, {
					field : 'pr1',
					title : '答辩地点',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						if (row.paperRecord == null) {
							return '无';
						} else {
							return row.paperRecord.site;
						}
					}
				}, {
					field : 'pr2',
					title : '记录内容',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						if (row.paperRecord == null) {
							return '无';
						} else {
							return row.paperRecord.record;
						}

					}
				}, {
					field : 'pr6',
					title : '成绩',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						if (row.paperRecord == null) {
							return '无';
						} else {
							return row.paperRecord.score;
						}

					}
				} ] ]
			});

	$('#titledg').datagrid('hideColumn', 'paperTitleId');

	$('#titledg').datagrid('getPager').pagination({
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示从 [{from}] 到 [{to}] 共[{total}]条记录',
		onSelectPage : function(pPageIndex, pPageSize) {
			$('#titledg').datagrid('loading');
			// 改变oPage的参数值，用于下次查询传给数据层查询指定页码的数据
			oPage.pageIndex = pPageIndex;
			oPage.pageSize = pPageSize;
			getPage();
		}
	});
}

function showdlg() {
	var row = $('#titledg').datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示:', '请选择一行要修改的数据!', 'info');
		return;
	}
	if (row.paperRecord != null) {
		$.messager.alert('提示:', '这位同学已经录入过答辩记录!', 'warning');
		return;
	}
	$('#dlg').dialog('open');
}

function addPaperTopics() {

	var row = $('#titledg').datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示:', '请选择一行要操作的数据!', 'warning');
		return;
	}
	var replyRecord = {};
	replyRecord.replyDate = $("#t1").val();
	replyRecord.site = $("#t2").val();
	replyRecord.record = $('#t3').val();
	replyRecord.teamAdvice = $('#t4').val();
	replyRecord.teamDate = $("#t5").val();
	replyRecord.score = $('#t6').val();
	replyRecord.councilAdvice = $('#t7').val();
	replyRecord.counDate = $("#t8").val();

	paperRecordDWR.writeRecord(row.paperTitleId, replyRecord, function(data) {
		if (data == 1) {
			$.messager.alert('提示:', '答辩记录录入成功!', 'info');
		} else {
			$.messager.alert('提示:', '操作失败!', 'error');
		}
		$('#dlg').dialog('close');
		getPage();
	});

}

function showRecord() {

	var row = $('#titledg').datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示:', '请选择一行要操作的数据!', 'warning');
		return;
	}
	var replyRecord = {};
	replyRecord.replyDate = $("#t1").datebox("getValue");
	replyRecord.site = $("#t2").val();
	replyRecord.record = $('#t3').val();
	replyRecord.teamAdvice = $('#t4').val();
	replyRecord.teamDate = $("#t5").datebox("getValue");
	replyRecord.score = $('#t6').val();
	replyRecord.councilAdvice = $('#t7').val();
	replyRecord.counDate = $("#t8").datebox("getValue");

}
