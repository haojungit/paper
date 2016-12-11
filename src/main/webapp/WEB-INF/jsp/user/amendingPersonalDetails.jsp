<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<script src='<%=basePath%>/dwr/interface/teacher.js'></script>
<script src='<%=basePath%>/dwr/interface/student.js'></script>
<script src='<%=basePath%>/dwr/engine.js'></script>
<script src='<%=basePath%>/dwr/util.js'></script>

<script src="<%=basePath%>jquery-easyui/jquery-1.8.0.js"></script>
<script src="<%=basePath%>jquery-easyui/jquery.easyui.min.js"></script>
<body>
	<table style="width: 100%; border: 0; cellpadding: 0; cellspacing: 0;">
		<tr>
			<td style="background: url(images/mail_leftbg.gif) repeat-y" />
			<td valign="top" bgcolor="#F7F8F9"
				style="text-align: center; margin: 0 auto; width: 100%"><c:if
					test="${user.identity!=\"同学\"}">
					<c:if test="${!empty teacher}">
						<form id="teacherAmendingPersonalDetails" method="post"
							style="padding-top: 50px;" novalidate>

							<label>工号:</label> <input id="apTeacherNumber"
								disabled="disabled" value="${teacher.teacherNumber}" /> <label>姓名:</label>
							<input id="apTeacherName" class="easyui-validatebox" required
								value="${teacher.teacherName}" /> <br /> <label>性别:</label> <input
								id="apTeacherSex" class="easyui-validatebox"
								value="${teacher.teacherSex}" /> <label>年龄:</label> <input
								id="apTeacherAge" class="easyui-validatebox" validType="number[1,2]"
								value="${teacher.teacherAge}" /> <br /> <label>电话:</label> <input
								id="apTeacherPhone" class="easyui-validatebox"
								validType="mobile" value="${teacher.teacherPhone}" /> <label>院系
								:</label> <input id="apTeacherUnits" class="easyui-validatebox"
								value="${teacher.teacherUnits}"> <br /> <label>专业
								:</label> <input id="apTeacherMajor" class="easyui-validatebox"
								value="${teacher.teacherMajor}" /> <label>学历 :</label> <input
								id="apTeacherEducation" class="easyui-validatebox"
								value="${teacher.teacherEducation}" /> <br /> <label>职称:</label>
							<input id="apTeacherJobTitle" class="easyui-validatebox"
								value="${teacher.teacherJobTitle}" /> <label
								style="margin-left: -20px;">研究方向:</label> <input
								id="apTeacherDirection" class="easyui-validatebox"
								value="${teacher.teacherDirection}" /> <br /> <br /> <br />
							<input style="margin-left: 40px;" type="button" value="提交"
								onclick='apUpdateTeacher(${teacher.teacherId})' /> <input
								style="margin-left: 40px;" type="reset" value="重置" />
						</form>
					</c:if>
					<c:if test="${empty teacher}">
						老师您好！系统异常，请重新登录！
					</c:if>
				</c:if> <c:if test="${user.identity==\"同学\"}">
					<c:if test="${!empty student}">
						<form id="amendingPersonalDetails" method="post"
							style="padding-top: 50px;" novalidate>

							<label>姓名:</label> <input id="apStudentName"
								class="easyui-validatebox" required
								value="${student.studentName}"> <label>性别:</label> <input
								id="apStudentSex" class="easyui-validatebox"
								value="${student.studentSex}"> <br /> <label>年龄:</label>
							<input id="apStudentAge" class="easyui-validatebox"
								validType="number[1,2]" value="${student.studentAge}"> <label>电话
								:</label> <input id="apStudentPhone" class="easyui-validatebox"
								validType="mobile" value="${student.studentPhone}"> <br />
							<label>年级 :</label> <input id="apStudentGrade"
								class="easyui-validatebox" value="${student.studentGrade}">
							<label>院系 :</label> <input id="apStudentFaculty"
								class="easyui-validatebox" value="${student.studentFaculty}">
							<br /> <label>专业 :</label> <input id="apStudentMajor"
								class="easyui-validatebox" value="${student.studentMajor}">
							<label style="margin-left: -27px;">研究方向 :</label> <input
								id="apStudentDirection" class="easyui-validatebox"
								value="${student.studentDirection}"> <br /> <br /> <br />
							<input style="margin-left: 40px;" type="button" value="提交"
								onclick='apUpdateStudent(${student.studentId})' /> <input
								style="margin-left: 40px;" type="reset" value="重置" />
						</form>
					</c:if>
					<c:if test="${empty student}">
						同学您好！系统异常，请重新登录！
					</c:if>
				</c:if></td>
			<td style="background: url(images/mail_rightbg.gif) repeat-y">&nbsp;</td>
		</tr>
		<tr class="noprint">
			<td valign="bottom"
				style="background: url(images/buttom_left2.gif) no-repeat; width: 5px; height: 17px;" />
			<td
				style="background: url(images/buttom_bgs.gif) repeat-x; width: 17px; height: 17px;" />
			<td valign="bottom"
				style="background: url(images/buttom_right2.gif) no-repeat; width: 5px; height: 17px;" />
		</tr>
	</table>
</body>

<script>
function apUpdateStudent(studentID) {

	if (!$('#amendingPersonalDetails').form('validate'))
		return false;

	var studentName = $('#apStudentName').val();
	var studentSex = $('#apStudentSex').val();
	var studentAge = $('#apStudentAge').val();
	var studentPhone = $('#apStudentPhone').val();
	var studentGrade = $('#apStudentGrade').val();
	var studentFaculty = $('#apStudentFaculty').val();
	var studentMajor = $('#apStudentMajor').val();
	var studentDirection = $('#apStudentDirection').val();

	student.findById(studentID, function(paperStudent) {
		paperStudent.studentName = studentName;
		paperStudent.studentSex = studentSex;
		paperStudent.studentAge = studentAge;
		paperStudent.studentPhone = studentPhone;
		paperStudent.studentGrade = studentGrade;
		paperStudent.studentFaculty = studentFaculty;
		paperStudent.studentMajor = studentMajor;
		paperStudent.studentDirection = studentDirection;
		student.update(paperStudent, function(msg) {
			if (msg != true) {
				alert("修改失败！");
				return;
			} else {
				alert("修改成功！");
			}
		});
	});
}

function apUpdateTeacher(teacherID) {

	if (!$('#teacherAmendingPersonalDetails').form('validate'))
		return false;
 
	var teacherName = $('#apTeacherName').val();
	var teacherSex = $('#apTeacherSex').val();
	var teacherAge = $('#apTeacherAge').val();
	var teacherPhone = $('#apTeacherPhone').val();
	var teacherUnits = $('#apTeacherUnits').val();
	var teacherMajor = $('#apTeacherMajor').val();
	var teacherEducation = $('#apTeacherEducation').val();
	var teacherJobTitle = $('#apTeacherJobTitle').val();
	var teacherDirection = $('#apTeacherDirection').val();

	teacher.findById(teacherID, function(paperTeacher) {
		paperTeacher.teacherName = teacherName;
		paperTeacher.teacherSex = teacherSex;
		paperTeacher.teacherAge = teacherAge;
		paperTeacher.teacherPhone = teacherPhone;
		paperTeacher.teacherUnits = teacherUnits;
		paperTeacher.teacherMajor = teacherMajor;
		paperTeacher.teacherEducation = teacherEducation;
		paperTeacher.teacherJobTitle = teacherJobTitle;
		paperTeacher.teacherDirection = teacherDirection;
		teacher.update(paperTeacher, function(msg) {
			if (!msg) {
				alert("更新失败！");
				return;
			} else {
				alert("更新成功！");
			}
		});
	});
}
</script>