function getRoleInfo() {

	var toolbar = [
			{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#roleSave').unbind("click");
					$('#addRole').dialog('open').dialog('setTitle', '添加角色');
					$('#roleform').form('clear');
					$('#roleSave').click(function() {
						saveRole();
					});
				}
			},
			{
				id : 'alertRole',
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					$('#roleSave').unbind("click");
					var row = $('#roledg').datagrid('getSelected');
					if (row.flag == 3) {
						$.messager.alert('系統提示', '系统角色不可操作!', 'error');
						return;
					}
					if (row) {
						$('#addRole').dialog('open').dialog('setTitle', '修改角色');
						$('#roleform').form('clear');
						$('#roleform').form('load', row);
						$('#roleSave').click(function() {
							updateRole();
						});
					} else
						$.messager.alert('系統提示', '请先选择一行!', 'warning');
				}
			},
			/*
			 * { text : '删除', iconCls : 'icon-remove', handler : function() {
			 * $('#roleSave').unbind("click"); var row =
			 * $('#roledg').datagrid('getSelected'); if (row)
			 * $.messager.confirm('删除', '确定删除此角色?', function(r) { if (r) {
			 * $.post('../struts/deleteRole', { roleId : row.roleId },
			 * function(result) { if (!result) alert("操作失败！"); else {
			 * $('#addRole').dialog('close'); $('#roledg').datagrid('reload'); }
			 * }); } }); } },
			 */
			'-',
			{
				id : 'roleAP',
				text : '分配权限',
				iconCls : 'icon-power',
				handler : function() {
					$("#permissions_selectL").html('');
					$("#permissions_selectR").html('');
					var row = $('#roledg').datagrid('getSelected');
					if (!row) {
						$.messager.alert('系統提示', '请先选择一行!', 'warning');
						return;
					}

					var roleName = row.roleName;
					$('#assigningPermissions').dialog('open').dialog(
							'setTitle', '分配权限');
					$('#ARPermissions').html(
							'分配权限给 : <a id="TUP" style="font-size:21px;color:red;">'
									+ roleName + '</a>');
					$('#roleManageRoleId').val(row.roleId);
					$.post("../struts/getAllPermission.action",

					function(data) {

						var html = "";
						$.each(data, function(i, item) {
							html += '<option value="' + item.id + '">'
									+ item.description + '</option>';
						});
						$('#permissions_selectL').html(html);

						$.post("../struts/getRolePermission.action", {
							roleId : row.roleId
						}, function(data) {
							var leftSel = $("#permissions_selectL");
							var rightSel = $("#permissions_selectR");
							leftSel.find("option").each(function() {
								for ( var i = 0; i < data.length; i++)
									if (data[i] == this.value)
										$(this).remove().appendTo(rightSel);
							});
						});

					});
				}
			} ];

	// 初始化dategrid

	$('#roledg')
			.datagrid(
					{

						url : '../struts/getAllRole',

						toolbar : toolbar,

						singleSelect : true,

						onLoadSuccess : function() {
							// 加载完数据关闭等待的div
							$('#roledg').datagrid('loaded');
						},

						onSelect : function(rowIndex, rowData) {
							if (rowData.flag == 3) {
								$('#alertRole').linkbutton("disable");
								// $('#roleAP').linkbutton("disable");
							} else {
								$('#alertRole').linkbutton("enable");
								// $('#roleAP').linkbutton("enable");
							}
						},

						rowStyler : function(index, row) {
							if (row.flag == 3) {
								return 'background-color:#C4E1FF;color:#000000;font-weight:bold;';
							}
							if (row.flag == 0) {
								return 'background-color:#FF9797;color:#000000;font-weight:bold;';
							}
						},

						columns : [ [

						{
							field : 'roleId'
						},

						{
							field : 'ck',
							checkbox : true
						},

						{
							field : 'roleName',
							title : '角色名称',
							width : 150,
							align : 'center'
						},

						{
							field : 'description',
							title : '角色描述',
							width : 500,
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
						}

						] ]

					});

	$('#roledg').datagrid('hideColumn', 'roleId');
}

function saveRole() {

	$('#roleSave').linkbutton("disable");

	$('#roleform').form('submit', {
		url : "../struts/addRole",
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			if (result) {
				$('#addRole').dialog('close'); // close the dialog
				$('#roledg').datagrid('reload'); // reload the user data
			} else
				$.messager.alert('系統提示', '操作失败!', 'error');

			$('#roleSave').linkbutton("enable");
		}
	});
}

function updateRole() {

	$('#roleSave').linkbutton("disable");

	$('#roleform').form('submit', {
		url : "../struts/updateRole",
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			if (result) {
				$('#addRole').dialog('close'); // close the dialog
				$('#roledg').datagrid('reload'); // reload the user data
			} else
				$.messager.alert('系統提示', '操作失败!', 'error');
			$('#roleSave').linkbutton("enable");
		}
	});
}

function roleMultiselect2side() {

	var leftSel = $("#permissions_selectL");
	var rightSel = $("#permissions_selectR");

	$("#permissions_toright").bind(
			"click",
			function() {
				var permissionlist = [];
				leftSel.find("option:selected").each(function() {
					$(this).remove().appendTo(rightSel);
					permissionlist.push(this.value);
				});
				ARRolePermissions('../struts/addRolePermissions.action', $(
						'#roleManageRoleId').val(), permissionlist.join(','),
						rightSel);

			});

	$("#permissions_toleft").bind(
			"click",
			function() {
				var permissionlist = [];
				rightSel.find("option:selected").each(function() {
					$(this).remove().appendTo(leftSel);
					permissionlist.push(this.value);
				});

				ARRolePermissions('../struts/removeRolePermissions.action', $(
						'#roleManageRoleId').val(), permissionlist.join(','),
						leftSel);

			});

	leftSel.dblclick(function() {
		$(this).find("option:selected").each(
				function() {

					ARRolePermission('../struts/addRolePermission.action', $(
							'#roleManageRoleId').val(), this, rightSel);

				});
	});

	rightSel.dblclick(function() {
		$(this).find("option:selected").each(
				function() {

					ARRolePermission('../struts/removeRolePermission.action',
							$('#roleManageRoleId').val(), this, leftSel);

				});
	});
}

function ARRolePermission(url, roleId, object, targetSel) {

	$('#roledg').datagrid('loading');
	loading(true, $("#assigningPermissions"), '正在处理，请稍候...');
	$.ajax({
		type : "post",// 发送方式
		url : url,// 路径
		data : {
			roleId : roleId,
			permissionId : object.value
		},
		success : function(msg) {
			if (msg == "SUCCESS")
				$(object).remove().appendTo(targetSel);
			else
				$.messager.alert('系統提示', '操作失败!', 'error');
			loading(false, $("#assigningPermissions"));
			$('#roledg').datagrid('loaded');
		}
	});
}

function ARRolePermissions(url, roleId, permissionlist, targetSel) {

	$('#roledg').datagrid('loading');
	loading(true, $("#assigningPermissions"), '正在处理，请稍候...');
	$.ajax({
		type : "post",// 发送方式
		url : url,// 路径
		data : {
			roleId : roleId,
			permissionlist : permissionlist
		},
		success : function(msg) {
			if (msg != "SUCCESS")
				$.messager.alert('系統提示', '操作失败!', 'error');
			loading(false, $("#assigningPermissions"));
			$('#roledg').datagrid('loaded');
		}
	});
}