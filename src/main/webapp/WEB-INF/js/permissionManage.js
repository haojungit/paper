$('#dg').datagrid({
	title : "权限管理",
	toolbar : "#toolbar",
	url : null,
	pageList : [ 10, 20, 30, 40 ],
	pagination : true,
	rownumbers : false,
	singleSelect : true,
	remoteSort : true,
	fitColumns : true,
	onClickRow : function(rowIndex, rowData) {

	},
	columns : [ [ {
		field : 'id'
	}, {
		field : 'description',
		title : '权限描述',
		width : 100,
		align : 'center'
	}, {
		field : 'permission',
		title : '链接',
		width : 100,
		align : 'center'
	}, {
		field : 'flag',
		title : '状态',
		width : 100,
		align : 'center',
		formatter : function(value, row, index) {
			if (value == 3) {
				return '正常（系统）';
			}
			return value == 0 ? "已禁用" : "正常";
		}
	} ] ],
	rowStyler : function(index, row) {
		if (row.flag == 3) {
			return 'background-color:#C4E1FF;color:#000000;font-weight:bold;';
		}
		if (row.flag == 0) {
			return 'background-color:#FF9797;color:#000000;font-weight:bold;';
		}
	}

});
var f_page = 1;
var f_rows = 10;
function getPage(page, rows) {
	f_page = page;
	f_rows = rows;
	$('#dg').datagrid('loading'); // 打开等待div
	permission.getPage(page, rows, function(data) {
		$("#dg").datagrid('loaded'); // 关闭等待div
		$('#dg').datagrid('loadData', {
			"total" : data.totalCount,
			"rows" : data.list
		});
		$('#permissiondlg').dialog('close');
		return data;
	});
}

$(document).ready(function() {
	getPage(1, 10);
	// 设置分页显示栏内容
	var p = $('#dg').datagrid('getPager');
	$(p).pagination({
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onSelectPage : function(pPageIndex, pPageSize) {
			getPage(pPageIndex, pPageSize);
		}
	});

});

function delPermission() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		if (row.flag == 1) {
			$.messager.confirm('提示', '您将要禁用一个权限，确定禁用吗?', function(r) {
				if (r) {
					$("#dg").datagrid('loading');
					permission.delsoftOrRenew(row.id, function(yn) {
						if (yn == 1) {
							$.messager.alert("提示", '操作成功!', 'info');
							getPage(f_page, f_rows);
						}
						$("#dg").datagrid('loaded');
					});
				}
			});
		} else if (row.flag == 0) {
			$("#dg").datagrid('loading');
			permission.delsoftOrRenew(row.id, function(yn) {
				if (yn == 1) {
					$.messager.alert("提示", '恢复成功!', 'info');
					getPage(f_page, f_rows);
				}
				$("#dg").datagrid('loaded');
			});
		}

	}
}
function delsoftOrRenew() {
	var row = $('#dg').datagrid('getSelected');
	if (!row) {
		$.messager.alert("提示", '请选择一行要操作的数据!', 'warning');
	}
	if (row.flag == 3) {
		$.messager.alert("提示", '系统默认权限不可操作!', 'error');
	}
	if (row) {
		if (row.flag == 1) {
			$.messager.confirm('提示', '您将要禁用一个权限，确定禁用吗?', function(r) {
				if (r) {
					$("#dg").datagrid('loading');
					permission.delsoftOrRenew(row.id, function(yn) {
						if (yn == 1) {
							$.messager.alert("提示", '操作成功!', 'info');
							getPage(f_page, f_rows);
						}
						$("#dg").datagrid('loaded');
					});
				}
			});
		} else if (row.flag == 0) {
			$("#dg").datagrid('loading');
			permission.delsoftOrRenew(row.id, function(yn) {
				if (yn == 1) {
					$.messager.alert("提示", '恢复成功!', 'info');
					getPage(f_page, f_rows);
				}
				$("#dg").datagrid('loaded');
			});
		}

	}
}
function newPermission() {
	$('#permissiondlg').dialog('open').dialog('setTitle', '添加论文题目');
	$('#fm').form('clear');
}
function savePermission(p) {
	permission.save(p, function(data) {
		if (data == 1) {
			$.messager.alert("提示", '恢复成功!', 'info');
			getPage(f_page, f_rows);
		} else {
			alert("添加失败");
		}
	});
}
function updatePermission(p) {
	permission.update(p, function(data) {
		if (data == 1) {
			$.messager.alert('提示', '修改成功!', 'info');
			getPage(f_page, f_rows);

		} else {
			$.messager.alert("提示", '修改失败!', 'error');
		}
	});
}

function saveOrUpdate() {
	var tid = $("#ft1").attr("value");
	var permission = {};
	permission.id = $("#ft1").val();
	permission.description = $("#ft2").val();
	permission.pno = $("#ft3").val();
	permission.permission = $("#ft4").val();
	if (tid == "") {
		savePermission(permission);
	} else {
		updatePermission(permission);
	}
}
function editPermission() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		if (row.flag == 3) {
			$.messager.alert("提示", '系统默认权限不可操作!', 'error');
			return;
		}
		$('#permissiondlg').dialog('open').dialog('setTitle', '修改权限内容');
		$('#fm').form('load', row);
	} else {
		$.messager.alert('提示:', '请选择一行要修改的数据!', 'info');
	}
}

$('#dg').datagrid({
	onDblClickRow : function() {
		editPermission();
	}
});