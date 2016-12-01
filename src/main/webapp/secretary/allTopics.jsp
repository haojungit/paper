<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<link href="<%=basePath%>css/skin.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/icon.css">

<script src="<%=basePath%>jquery-easyui/jquery-1.8.0.js"></script>
<script src="<%=basePath%>jquery-easyui/jquery.easyui.min.js"></script>
<script src="<%=basePath%>jquery-easyui/datagrid-detailview.js"></script>

<script src="<%=basePath%>js/allTopics.js"></script>

<script src="<%=basePath%>dwr/interface/titleDWR.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>

</head>
<body style="width:100%;height:100%">
	<input id="teacherID" type="hidden" value="${teacher.teacherId}" />
	<table style="width: 100%; border: 0; cellpadding: 0; cellspacing: 0;">
		<tr>
			<td style="background: url(../images/mail_leftbg.gif) repeat-y" />
			<td>
				<table id="titledg"
					style="width: auto; height: auto;" >

				</table>
			<td style="background: url(../images/mail_rightbg.gif) repeat-y">&nbsp;</td>
		</tr>
		<tr class="noprint">
			<td valign="bottom"
				style="background: url(../images/buttom_left2.gif) no-repeat; width: 5px; height: 17px;" />
			<td
				style="background: url(../images/buttom_bgs.gif) repeat-x; width: 17px; height: 17px;" />
			<td valign="bottom"
				style="background: url(../images/buttom_right2.gif) no-repeat; width: 5px; height: 17px;" />
		</tr>
	</table>
	<div id="tb" style="padding:5px;height:auto" align="center">  
        
        <div>
          	 院系:   
           <select id="combo1" class="easyui-combobox" panelHeight="auto" style="width:100px">  
               <option value="请选择院系">正在获取数据...</option>
           </select>
           	专业:
           <select id="combo2" class="easyui-combobox" panelHeight="auto" style="width:100px">  
               
           </select>
           	年级:
           <select id="combo3" class="easyui-combobox" panelHeight="auto" style="width:100px">  
               
           </select>
           <a href="#" id="bt1" class="easyui-linkbutton" iconCls="icon-search" onclick="getPage();">查找</a> 
        </div>
    </div>  
	
</body>
<script>
	allTopicInfo($('#teacherID').val());
</script>
</html>
