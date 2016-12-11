function getCouncilInfo() {
	// 定义全局变量记录分页信息

	var oPage = {
		pageIndex : 1,
		pageSize : 15
	};

	var toolbar = [ {
		text : '申报答辩委员会',
		iconCls : 'icon-add',
		handler : function() {
			top.mainFrame
					.addPaperTab('答辩委员会申请','答辩委员会申请', 'secretary/replyCommittee.jsp');
		}
	} ];

	// 初始化dategrid
	$('#councildg')
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

						},

						detailFormatter : function(rowIndex, rowData) {
							var str = "";
							var str1 = "";
							for ( var n in rowData.councilman) {
								if (rowData.councilman[n].chair == 1)
									str1 = rowData.councilman[n].paperTeacher.teacherName;
								else
									str += (rowData.councilman[n].paperTeacher.teacherName + ",");
							}

							return '<table><tr>'
									+ '<td style="border:0;padding:10px;width:50%;">'
									+ '<p>主席：'
									+ str1
									+ '</p><p>委员：'
									+ str
									+ '</p><p>院（系）审查意见：</p><p style="padding-left:15px;">'
									+ (rowData.deanSug == null ? ''
											: rowData.deanSug)
									+ '</p></td></tr></table>';
						},

						onLoadSuccess : function() {
							// 加载完数据关闭等待的div
							$('#councildg').datagrid('loaded');
						},
						columns : [ [

								{
									field : 'councilId'
								},

								{
									field : 'department',
									title : '院系',
									width : 200,
									align : 'center'
								},

								{
									field : 'major',
									title : '专业',
									width : 150,
									align : 'center'
								},

								{
									field : 'grade',
									title : '年级',
									width : 150,
									align : 'center'
								},

								{
									field : 'deanSug',
									title : '院系意见',
									width : 300,
									align : 'center'
								},

								{
									field : 'deanDate',
									title : '审批时间',
									width : 150,
									align : 'center',
									formatter : function(value, row, index) {
										if (value != null) {
											var y = value.getFullYear();
											var m = value.getMonth() + 1;
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
									field : 'state',
									title : '当前状态',
									width : 150,
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 0) {
											return "<font color='#5B5B5B'>未审核</font>";
										} else if (value == 1) {
											return "<font color='#00A600'>已通过</font>";
										} else if (value == 2) {
											return "<font color='red'>未通过</font>";
										}

									}
								}

						] ]
					});

	$('#councildg').datagrid('hideColumn', 'councilId');

	$(document).ready(function() {
		reloadcdg();
		$('#councildg').datagrid('getPager').pagination({
			beforePageText : '第',
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示从  {from} 到  {to} 共 {total} 条记录',
			onSelectPage : function(pPageIndex, pPageSize) {
				$('#councildg').datagrid('loading');
				// 改变oPage的参数值，用于下次查询传给数据层查询指定页码的数据
				oPage.pageIndex = pPageIndex;
				oPage.pageSize = pPageSize;
				reloadcdg();
			}
		});
	});

	function reloadcdg() {
		councilDWR.getPage(oPage.pageIndex, oPage.pageSize, function(oData) {
			$('#councildg').datagrid('loading');
			$('#councildg').datagrid('loadData', {
				"total" : oData.totalCount,
				"rows" : oData.list
			});
		});
	}
}

function findFMG() {
	titleDWR.findFaculty(function(tlist) {
		$('#combo1').combobox({
			data : tlist,
			valueField : 'studentFaculty',
			textField : 'studentFaculty',
			editable : false,
			onChange : function(newVal, oldVal) {
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
}

function showComboGrid(RCID) {

	// 定义全局变量记录分页信息

	var oPage = {

		pageIndex : 1,

		pageSize : 15

	};

	var toolbar = [ {
		text : '查询',
		iconCls : 'icon-add',
		handler : function() {
			$('#searchRCTeacher').css('display', 'inline');
			$('#searchRCTeacher').remove(".datagrid-toolbar");
			$(this).parents(".datagrid-toolbar").append($('#searchRCTeacher'));
			$('#RCID').val(RCID);
		}
	} ];

	$('#' + RCID)
			.combogrid(
					{

						url : null,

						required : true,

						panelWidth : 550,

						idField : 'teacherId',

						textField : 'teacherName',

						pageList : [ 15, 30, 50, 100 ],

						pagination : true,

						editable : false,

						pageSize : 15,

						pageNumber : 1,

						toolbar : toolbar,

						onLoadSuccess : function() {
							// 加载完数据关闭等待的div
							$("#" + RCID).combogrid("grid").datagrid('loaded');

							if (RCID.indexOf('leader') == -1) {
								$('#' + RCID).combogrid('options').multiple = true;
								$("#" + RCID).combogrid("grid").datagrid(
										'options').singleSelect = false;
							} else {
								$('#' + RCID).combogrid('options').multiple = false;
							}
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
							field : 'teacherName',
							title : '姓名',
							width : 80,
							align : 'center'
						},

						{
							field : 'teacherAge',
							title : '年龄',
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
							field : 'teacherEducation',
							title : '学历',
							width : 100,
							align : 'center'
						},

						{
							field : 'teacherUnits',
							title : '单位',
							width : 100,
							align : 'center'
						},

						] ]

					});

	teacher.findAll(oPage.pageIndex, oPage.pageSize, function(oData) {

		$("#" + RCID).combogrid("grid").datagrid('loading');
		// 使用loadDate方法加载Dao层返回的数据
		$("#" + RCID).combogrid("grid").datagrid('loadData', {
			"total" : oData.totalCount,
			"rows" : oData.list
		});
	});

	$("#" + RCID).combogrid("grid").datagrid('hideColumn', 'teacherId');

	$("#" + RCID).combogrid("grid").datagrid('getPager').pagination({

		displayMsg : '第 [{from}] - [{to}]条记录  共[{total}]条记录',

		onSelectPage : function(pPageIndex, pPageSize) {

			$("#" + RCID).combogrid("grid").datagrid('loading');

			// 改变oPage的参数值，用于下次查询传给数据层查询指定页码的数据

			oPage.pageIndex = pPageIndex;

			oPage.pageSize = pPageSize;

			// 异步获取数据到javascript对象，入参为查询条件和页码信息
			teacher.findAll(oPage.pageIndex, oPage.pageSize, function(oData) {
				// 使用loadDate方法加载DWR返回的数据
				$("#" + RCID).combogrid("grid").datagrid('loadData', {
					"total" : oData.totalCount,
					"rows" : oData.list
				});
			});
		}
	});

}

function doSearchTeacher(value, name) {

	var RCID = $('#RCID').val();

	var options = $("#" + RCID).combogrid("grid").datagrid('getPager').data(
			'pagination').options;
	if (value == '' || value.length == 0)
		teacher.findAll(options.pageNumber, options.pageSize, function(oData) {
			// 使用loadDate方法加载DWR返回的数据
			$("#" + RCID).combogrid("grid").datagrid('loadData', {
				"total" : oData.totalCount,
				"rows" : oData.list
			});
		});
	else
		teacher.findByProperty(name, value, options.pageNumber,
				options.pageSize, function(oData) {
					// 使用loadDate方法加载DWR返回的数据
					$("#" + RCID).combogrid("grid").datagrid('loadData', {
						"total" : oData.totalCount,
						"rows" : oData.list
					});
				});
}

function addTable() {

	var index = $('#replyCommiteeTable').children('tbody').children('tr').length - 2;
	html = '<tr id="counciList' + index + '"  align="center" height="35px">'
			+ '<td><input id="leader' + index
			+ '" style="width: 250px" /></td><td><input id="member' + index
			+ '" style="width: 250px" /></td></tr>';
	$('#replyCommiteeTable').append(html);
	showComboGrid('leader' + index);
	showComboGrid('member' + index);
}

function removeTable() {

	var index = $('#replyCommiteeTable').children('tbody').children('tr').length - 3;
	$('#counciList' + index).remove();
}

function addCouncil() {

	if (!$('#replyCommitteeForm').form('validate'))
		return false;

	var leaders = [];
	var members = [];
	var length = $('#replyCommiteeTable').children('tbody').children('tr').length - 2;

	var paperCouncil = {};
	paperCouncil.department = $("#combo1").combobox("getText");
	paperCouncil.major = $("#combo2").combobox("getText");
	paperCouncil.grade = $("#combo3").combobox("getText");
	paperCouncil.deansug = "";
	paperCouncil.state = 0;

	for ( var i = 1; i < length; i++) {
		leaders.push($('#leader' + i).combogrid('getValue'));
		members.push($('#member' + i).combogrid('getValues').join(','));
	}

	/*
	 * var rows = $('#member1').combogrid("grid").datagrid('getSelections'); for (
	 * var i = 0; i < rows.length; i++) { alert(rows[i].teacherName); }
	 * 
	 * return;
	 */

	leaders.push($('#leader').combogrid('getValue'));

	councilDWR.apply(paperCouncil, leaders, members, function(data) {
		$.messager.show({
			title : '系统提示',
			msg : data
		});

	});

}
