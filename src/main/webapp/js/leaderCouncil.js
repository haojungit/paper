// 定义全局变量记录分页信息
var oPage = {
	pageIndex : 1,
	pageSize : 15
};

function leaderCouncilInfo() {
	var toolbar = [ {
		text : '审核操作',
		iconCls : 'icon-add',
		handler : function() {

			if ($('#leadercouncildg').datagrid('getSelected').state != 0) {
				$.messager.alert('系統提示','该课题已审批过,不能重复审批!','warning');
				return false;
			}
			$('#reviewReplyCouncil').dialog('open').dialog('setTitle',
					'答辩委员会审核');
		}
	} ];
	// 初始化dategrid
	$('#leadercouncildg')
			.datagrid(
					{
						url : null,

						pageList : [ 5, 15, 30, 50, 100 ],

						pagination : true,

						pageSize : 15,

						pageNumber : 1,

						toolbar : toolbar,

						singleSelect : true,

						view : detailview,

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
							$('#leadercouncildg').datagrid('loaded');
						},

						columns : [ [

								{
									field : 'councilId'
								},

								{
									field : 'department',
									title : '院系',
									width : 150,
									align : 'center',
									formatter : function(value, row, index) {
										return value;
									}
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
											return "<font color='#8E8E8E'>未审核</font>";
										} else if (value == 1) {
											return "<font color='#00A600'>已通过</font>";
										} else if (value == 2) {
											return "<font color='red'>未通过</font>";
										}
									}
								}

						] ]
					});

	$('#leadercouncildg').datagrid('hideColumn', 'councilId');

	$(document).ready(function() {
		reloadcdg();
		$('#leadercouncildg').datagrid('getPager').pagination({
			displayMsg : '当前显示从  {from} 到  {to} 共 {total} 条记录',
			onSelectPage : function(pPageIndex, pPageSize) {
				$('#leadercouncildg').datagrid('loading');
				// 改变oPage的参数值，用于下次查询传给数据层查询指定页码的数据
				oPage.pageIndex = pPageIndex;
				oPage.pageSize = pPageSize;
				reloadcdg();
			}
		});
	});

}

function reloadcdg() {
	councilDWR.getPage(oPage.pageIndex, oPage.pageSize, function(oData) {
		$('#leadercouncildg').datagrid('loading');
		$('#leadercouncildg').datagrid('loadData', {
			"total" : oData.totalCount,
			"rows" : oData.list
		});
	});
}

function examineReplyCpuncil(state) {

	if (!$('#reviewReplyCouncilFrom').form('validate'))
		return false;

	$('#leadercouncildg').datagrid('loading');

	var paperCouncil = {};
	paperCouncil.councilId = $('#leadercouncildg').datagrid('getSelected').councilId;
	paperCouncil.deanSug = $("#deanSug").val();
	paperCouncil.deanDate = parseDate($('#deanDate').datebox('getValue'));
	paperCouncil.state = state;

	councilDWR.approve(paperCouncil, function(msg) {
		$.messager.show({
			title : '系统提示',
			msg : msg
		});
		reloadcdg();
		$('#reviewReplyCouncil').dialog('close');
	});
}
