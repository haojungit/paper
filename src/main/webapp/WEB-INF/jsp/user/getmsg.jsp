<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui/themes/icon.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=basePath%>jquery-easyui/jquery.easyui.min.js"></script>

<script src="<%=basePath%>dwr/interface/msgService.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>

<script>
var j$ = jQuery.noConflict();
    j$(document).ready(function () {
    	setInterval(function(){
    		var uid = j$("#msguserid").val();

    		msgService.getNewMsg(uid,function(data){
    			for(var n in data){
    				j$.messager.show({  
                        title:'收到新消息',
                        msg:''+data[n].messContent+"",  
                        timeout:120000,
                        showType:'slide'  
                    });
    				msgService.overRead(data[n].msgId);
    			}
    		
    		});
    	},3000);
    	
    });
   
</script>

<input type="hidden" id="msguserid" value="${user.userId}"/>
