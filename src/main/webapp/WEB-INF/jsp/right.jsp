<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 下面这句可解决消息框闪烁问题，但是会造成IE6下错位 -->
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
<link href="<%=basePath%>css/skin.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery-easyui/themes/icon.css">
<script src="<%=basePath%>jquery-easyui/jquery-1.8.0.js"></script>
<script src="<%=basePath%>jquery-easyui/jquery.easyui.min.js"></script>

<script src="<%=basePath%>js/msg.js"></script>


<body style="position: relative;">
	<input type="hidden" id="msguserid" value="${user.userId}" />

	<input id="description" type="hidden" value="${paperRole.description}" />
	<input id="roleId" type="hidden" value="${paperRole.roleId}" />

	<div id="paperTab" class="easyui-tabs"
		style="width: auto; scrolling: no; height: 550px;">

		<div title="Home" style="background-color: #EEE;">
			<div id="HomeImage" style="height: 80%; text-align: center;"></div>
		</div>

	</div>
	<div id="ddd">
		<!-- <div style="margin:10px 0;">  
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectPanel()">Select</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="addPanel()">Add</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="removePanel()">Remove</a>  
    </div>   -->
		<div class="easyui-accordion" style="width: auto; height: auto;"
			id="aa"></div>
	</div>
	<script type="text/javascript">
		var h = window.innerHeight || document.documentElement.clientHeight
				|| document.body.clientHeight || document.body.scrollHeight;
		$('#paperTab').height(h);
	</script>
	<script type="text/javascript">
	
		function addPaperTab(name, title, link) {

			var content = '<iframe name="' + name
					+ '" scrolling="auto" frameborder="0"  src="' + link
					+ '" style="width:100%;height:100%;"></iframe>';

			if ($("#paperTab").tabs('exists', title)) {

				$("#paperTab").tabs('select', title);

			} else {

				$("#paperTab").tabs('add', {

					title : title,

					content : content,

					closable : true
				});
			}

		}

		function closePaperTab(title) {

			$("#paperTab").tabs('close', title);
		}

		function addHomeImages(roleName, imageName) {
			var html = '<div style="font-size:20px;color:#243c4f; font-family:幼圆,楷体,黑体,Arial,sans-serifs; font-weight:normal;width:100%;margin-top:5px;margin-bottom:10px; ">'
					+ roleName
					+ '流程图</div ><div style="margin:7px auto;width:60%; padding:7px;background-color:#FFF ;border-top:1px solid #E9E9E9; border-left:2px solid #E9E9E9; border-right:1px solid black; border-bottom:1px solid black; "><img src="images/'+imageName+'.jpg" /></div>';
			$('#HomeImage').html(html);
		}

		addHomeImages($('#description').val(), $('#roleId').val());

		//listenNewMsg();
	</script>

</body>