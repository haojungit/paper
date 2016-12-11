<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/icon.css">

<script src="<%=basePath%>jquery-easyui/jquery-1.8.0.js"></script>
<script src="<%=basePath%>jquery-easyui/jquery.easyui.min.js"></script>
<script src="<%=basePath%>jquery-easyui/datagrid-detailview.js"></script>

<script src="<%=basePath%>js/councilList.js"></script>
<script src="<%=basePath%>js/public.js"></script>

<script src="<%=basePath%>dwr/interface/teacher.js"></script>
<script src="<%=basePath%>dwr/interface/titleDWR.js"></script>
<script src="<%=basePath%>dwr/interface/councilDWR.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>

<script src="<%=basePath%>dwr/interface/msgService.js"></script>
<script src="<%=basePath%>js/msg.js"></script>

<jsp:include page="../commonalityPage_top.jsp"></jsp:include>

<td valign="top" bgcolor="#F7F8F9">
	<h3 class="title">
		大庆师范学院大庆师范学院本科生毕业论文（设计）<br />院（系）答辩委员会成员申请表
	</h3>
	<form id="replyCommitteeForm">
		<table id="replyCommiteeTable" border="1" WIDTH="100%"
			BORDERCOLORLIGHT="#b1c9ff" BORDERCOLORDARK="#b1c9ff">

			<tr align="center" bordercolor="#003399" height="44px">
				<td colspan="7">院系：<select id="combo1" class="easyui-combobox"
					panelHeight="auto" style="width: 150px">
						<option value="请选择院系">正在获取数据...</option>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业：<select id="combo2"
					class="easyui-combobox" panelHeight="auto" style="width: 150px">
				</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年级：<select id="combo3"
					class="easyui-combobox" panelHeight="auto" style="width: 150px">
				</select>
				</td>
			</tr>
			<tr align="center" height="35px">
				<td rowspan="22" width="20" align="center">答辩委员会成员</td>
				<td>角色</td>
				<td>委员/组长</td>
				<td>成员/组员</td>
			</tr>
			<tr align="center" height="35px">
				<td>主席</td>
				<td><input id="leader" style="width: 250px" /></td>
			</tr>
			<tr align="center" valign="middle" height="39px">
				<td rowspan="20" align="center"><pre>答辩组</pre></td>
				<td><input id="leader1" style="width: 250px" /></td>
				<td><input id="member1" style="width: 250px" /></td>
			</tr>
		</table>
	</form>
	<table>
		<tr>
			<td><a href="javascript:void(0)" onclick="addTable()">+添加新的一行</a></td>
		</tr>
		<tr>
			<td><a href="javascript:void(0)" onclick="removeTable()">-移除最后一行</a></td>
		</tr>
	</table> <input type="hidden" id="RCID" />
	<div id="searchRCTeacher" style="display: none;">
		<div style="padding-top: 3px; margin-left: 3px">
			<input id="ssRCteacher" class="easyui-searchbox"
				data-options="prompt:'Please Input Value',searcher:doSearchTeacher"
				style="width: 300px;"></input>

			<div id="mmRCTeacher" style="width: 120px"></div>
		</div>
	</div>

	<div align="center">
		<input type="button" value="提交" onclick="addCouncil();"> 
		<input type="button" value="发送消息" onclick="sendToLeader('您好，我提交了答辩委员会成员申请表，请您审批。');">
	</div>
</td>
<script type="text/javascript">
	findFMG();
	showComboGrid('leader');
	showComboGrid('leader1');
	showComboGrid('member1');

	// 循环列名，生成搜索的下拉列表
	var fields = $('#leader').combogrid("grid").datagrid('getColumnFields');
	var muit = "";
	for ( var i = 2; i < fields.length; i++) {
		var opts = $('#leader').combogrid("grid").datagrid('getColumnOption',
				fields[i]);
		muit += "<div name='" + fields[i] + "'>" + opts.title + "</div>";
	};
	$('#mmRCTeacher').html($('#mmRCTeacher').html() + muit);
	$('#ssRCteacher').searchbox({
		menu : '#mmRCTeacher'
	});
</script>

<jsp:include page="../commonalityPage_buttom.jsp"></jsp:include>