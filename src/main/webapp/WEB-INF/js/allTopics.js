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
		$("#combo1").combobox('select', tlist[1].studentFaculty);
	});
	titleDWR.findGrade(function(glist) {
		$('#combo3').combobox({
			data : glist,
			editable : false,
			valueField : 'studentGrade',
			textField : 'studentGrade'
		});
		$("#combo3").combobox('select', glist[1].studentGrade);
	});

	// 初始化dategrid
	$('#titledg')
			.datagrid(
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
							/*
							 * return '<table><tr>' + '<td style="border:0;padding:10px;width:50%;">' + '<p>题目介绍:
							 * </p><p>' + rowData.paperTitleIntroduce + '</p><td style="border:0"><br/><p>系（教研室）意见:
							 * </p><p>' + ((rowData.paperTitleDeanSug == null) ? '' :
							 * rowData.paperTitleDeanSug) + '</p></td></tr></table>';
							 */
							return ''
							+ '<p>题目介绍: </p><p>'+ rowData.paperTitleIntroduce+ '</p>'
							+ '<p>系（教研室）意见:<font color="red">'+ ((rowData.paperTitleDeanSug == null) ? '': rowData.paperTitleDeanSug)+'</font></p>';
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
							field : 'paperTeacher',
							title : '申报人姓名',
							width : 100,
							align : 'center',
							formatter : function(value, row, index) {
								return row.paperTeacher.teacherName;
							}
						}, {
							field : 't1',
							title : '申报人职称',
							width : 100,
							align : 'center',
							formatter : function(value, row, index) {
								return row.paperTeacher.teacherJobTitle;
							}
						}, {
							field : 't2',
							title : '申报人学历',
							width : 100,
							align : 'center',
							formatter : function(value, row, index) {
								return row.paperTeacher.teacherEducation;
							}
						}, {
							field : 'paperTitleReportDate',
							title : '备注',
							width : 100,
							align : 'center',
							formatter : function(value, row, index) {

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
		$.messager.alert('系統提示','请选择限选学生专业!','info');
		return;
	}

	titleDWR.save(paperTitle, function(msg) {
		if (msg != true)
			$.messager.alert('系統提示','申报失败!','error');
		else
			$.messager.alert('系統提示','课题申报成功,您可以点击发送消息通知领导审核!','info');
	});
}
