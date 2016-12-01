<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script src="<%=basePath%>dwr/interface/paperGuideDWR.js"></script>
<script src="<%=basePath%>dwr/interface/titleDWR.js"></script>
<script src="<%=basePath%>dwr/engine.js"></script>
<script src="<%=basePath%>dwr/util.js"></script>

<script src="<%=basePath%>js/public.js"></script>
<script src="<%=basePath%>js/teacherPaperGuide.js"></script>
<script src="<%=basePath%>dwr/interface/msgService.js"></script>
<script src="<%=basePath%>js/msg.js"></script>

<jsp:include page="../commonalityPage_top.jsp"></jsp:include>
<jsp:include page="../commonalityPage_top.jsp"></jsp:include>

<td valign="top" bgcolor="#F7F8F9" style="width: 100%">
	<h3 class="title">
		大庆师范学院<br />本科生毕业论文（设计）指导记录表
	</h3>

	<table border="1" WIDTH="100%" BORDERCOLORLIGHT="#b1c9ff"
		BORDERCOLORDARK="#b1c9ff">

		<jsp:include page="publicInfo.jsp"></jsp:include>

		<tr>
			<td colspan="6" style="border-bottom:0px"><form id="paperGuide" style="margin:0">
					<table id="paperGuides" WIDTH="100%"></table>
				</form></td>
		</tr>
		<tr class="noprint">
			<td colspan="6" align="left" style="border-top:0px"><a style="color: red">上传论文：</a>
				<form id="uploadPaperForm" style="margin: 5px; display: inline;"
					action="../struts/uploadPaper.action" theme="simple" method="post"
					enctype="multipart/form-data">
					<input type="button" value="开始上传"
						onclick="ValidateUploadPaperForm()"> <input type="hidden"
						name="titleId" id="guideTitleId" /> <input type="hidden"
						name="guideTimes" id="guideTimes" /> <input type="file"
						name="upload" class="easyui-validatebox" validType="doc" required /><br />

				</form></td>
		</tr>

		<tr class="noprint">
			<td colspan="6" align="left"><a style="color: red">下载论文：</a>
				<form id="downloadPaperForm" action="../struts/getPaper.action"
					style="margin: 5px; display: inline;">
					<input id="guidId" type="hidden" name="guidId" /> <input
						type="button" value="开始下载" onclick="downLoadPaper()" />
				</form> <select style="height: 20px" id="paperGuideDownLoad">

			</select></td>
		</tr>
	</table>
	<p>注：以实际指导次数为准，但不能低于四次记录。</p>
	<p class="noprint" style="padding-left:20px;">点击提交按钮后,将不可再向本次指导上传论文。点击全部指导按钮后,不可再进行论文指导。</p>
	<div class="noprint">
		<div style="float: left; font-size: 12px"> 
			<input id="paPerGuideSubmit" type="button" value="提交"
				onclick="savePaPerGuide()" />
		</div>
		<div style="float: left; font-size: 12px">
			<input type="button" value="打印输出" onclick="window.print()" />
		</div>
		<div style="float: left; font-size: 12px">
			<input type="button" value="发送消息" onclick="sendto($('#publicInfoStudentNumber').val(),'我看过了你上传的论文。');"/>
		</div>

		<div style="float: left;">
			<input id="finishPaPerGuideSubmit" type="button" value="全部指导完成"
				onclick="finishPaPerGuide()" />
		</div>
	</div>
</td>


<jsp:include page="../commonalityPage_buttom.jsp"></jsp:include>

<script>
	paperGuideLoad();
</script>