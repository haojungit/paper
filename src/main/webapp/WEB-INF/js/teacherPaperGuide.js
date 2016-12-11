function downLoadPaper() {

	$('#guidId').val($('#paperGuideDownLoad').val());
	$('#downloadPaperForm').submit();
}

function beforePaperGuideUpload() {

	$('#uploadPaper').dialog('open').dialog('setTitle', '上传论文');
}

function ValidateUploadPaperForm() {
	if ($('#publicInfoStudentName').val() == ''
			|| $('#publicInfoStudentName').val().length < 2) {
		$.messager.alert('系統提示', '请先选择一个题目!', 'warning');
		return false;
	}
	if (!$('#uploadPaperForm').form('validate')) {
		$.messager.alert('系統提示', '请选择Word(doc或docx)文件!', 'warning');
		return false;
	}
	if (($('#publicInfoTitleState').val() - 8) >= 6) {
		$.messager.alert('系統提示', '已完成指导,不可上传!', 'warning');
		return false;
	}

	paperGuideDWR
			.findWithTitleId(
					$('#publicInfoSelect').combobox('getValue'),

					function(data) {
						if (data == null || data.length == 0)
							$('#guideTimes').val(1);
						else {
							if (data.length != 0
									&& (data[data.length - 1].guidance == null || data[data.length - 1].guidance == ''))
								$('#guideTimes').val(data.length);
							else
								$('#guideTimes').val(data.length + 1);
						}
						$('#guideTitleId').val(
								$('#publicInfoSelect').combobox('getValue'));
						$('#uploadPaperForm').submit();
					});
}

function finishPaPerGuide() {

	if (isNaN($('#publicInfoSelect').combobox('getValue'))) {
		$.messager.alert('系統提示', '请先选择一个题目!', 'warning');
		return false;
	}
	if (($('#publicInfoTitleState').val() - 8) < 4) {
		$.messager.alert('系統提示', '未达到4次指导标准,请指导四次以上再点击此按钮!', 'warning');
		return false;
	}
	if ($('#publicInfoTitleState').val() >= 16) {
		$.messager.alert('系統提示', '全部指导已完成!', 'info');
		paperGuideLoad();
		return false;
	} else
		paperGuideDWR.findWithTitleId($('#publicInfoSelect').combobox(
				'getValue'), function(data) {
			if (data[data.length - 1].guidId != null
					&& data[data.length - 1].guidanceType != null) {
				titleDWR.updateState($('#publicInfoSelect')
						.combobox('getValue'), 16, function(msg) {
					if (msg) {
						$.messager.alert('系統提示', '提交成功!', 'info');
						paperGuideLoad();
					} else
						$.messager.alert('系統提示', '提交失败!', 'error');
				});
			} else {
				$.messager.alert('系統提示', '请检查是否上传了论文但是并没有填写指导记录!', 'warning');
				return false;
			}
		});
}

function paperGuideLoad() {
	titleDWR.findTitleByTeacherId($('#publicInfoTeacherId').val(), 7, function(
			data) {
		if (data == null || data.length == 0) {
			$.messager.alert('系統提示', '您还没有指导记录需要填写!', 'info');
			top.mainFrame.closePaperTab('论文指导');
			return;
		}
		$('#publicInfoSelect').combobox(
				{
					data : data,
					valueField : 'paperTitleId',
					textField : 'paperTitleName',

					onSelect : function(rec) {

						titleDWR.findStudentInfoByTitleId(rec.paperTitleId,
								function(data) {
									if (data != null || data.length != 0) {
										$('#publicInfoStudentName').val(
												data[0].studentName);
										$('#publicInfoStudentNumber').val(
												data[0].studentNumber);
										$('#publicInfoStudentGrade').val(
												data[0].studentGrade);
										$('#publicInfoStudentFaculty').val(
												data[0].studentFaculty);
										$('#publicInfoStudentMajor').val(
												data[0].studentMajor);
										$('#publicInfoTitleState').val(
												rec.paperTitleState);
										printGuide();
									} else
										$.messager.alert('系統提示', '学生信息读取失败!',
												'error');
								});
					}
				});
	});
}

function printGuide() {
	var html = "";
	var html1 = "";
	paperGuideDWR
			.findWithTitleId(
					$('#publicInfoSelect').combobox('getValue'),
					function(data) {
						for ( var i = 0; i < data.length; i++) {
							if (data[i].guidance != null
									&& data[i].guidance != '')
								html += '<tr width="50px;"><td class="td">第<a style="color:red">&nbsp;'
										+ (i + 1)
										+ '&nbsp;</a>次指导：<p style="color:red;padding-left:15px;">'
										+ data[i].guidance
										+ '</p><br />指导方式：<p style="color:red;display:inline">'
										+ data[i].guidanceType
										+ '</p><br/><br/>日期：<p style="color:red;display:inline">'
										+ myformatter(data[i].guidanceDate)
										+ '</p><br /><br />指导教师（签字）：<br /></td></tr>';
							html1 += '<option selected="selected" value="'
									+ data[i].guidId + '">第' + (i + 1)
									+ '次指导</option>';
						}
						var times = $('#publicInfoTitleState').val() - 7;
						if ($('#publicInfoTitleState').val() < 14)
							html += '<tr><td class="left15">第<a style="color:red">&nbsp;'
									+ times
									+ '&nbsp;</a>次指导：<br /> <textarea id = "paperGuide'
									+ times
									+ '" name="textarea" rows="10" cols="100">'
									+ '</textarea><br /> <span class="print">指导方式：'
									+ '<select  style="height:20px" id="paperGuideCombox'
									+ times
									+ '"> <option selected="selected"  value="面谈">面谈</option>'
									+ '<option value="电话">电话</option><option value="电子邮件">电子邮件</option>'
									+ '</select></span><br /><br /> <span class="print fl">指导教师（签字）： </span><span'
									+ 'class="print fr" style="padding-right: 200px; padding-bottom: 10px;"><br/><br/>日期'
									+ '： <input id="paperGuideDatebox'
									+ times
									+ '"></input><br /><br />'
									+ '</span></td></tr>';

						$('#paperGuides').html(html);
						$('#paperGuideDownLoad').html(html1);
						$('#paperGuideDatebox' + times).datebox({
							required : true,
							formatter : myformatter,
							parser : myparser,
							editable : false
						});
					});
}

function savePaPerGuide() {

	var titleId = $('#publicInfoSelect').combobox('getValue');

	if (isNaN(titleId)) {
		$.messager.alert('系統提示', '请先选择一个题目!', 'warning');
		return false;
	}

	if (!$('#paperGuide').form('validate'))
		return false;

	paperGuideDWR.findWithTitleId($('#publicInfoSelect').combobox('getValue'),

	function(data) {
		if (data == null || data.length == 0) {
			$.messager.alert('系統提示', '学生还没有上传论文,请通知学生先上传论文!', 'warning');
			return;
		} else if (data[data.length - 1].guidance != null) {
			$.messager.alert('系統提示', '学生还没有上传论文,请通知学生先上传论文!', 'warning');
			return;
		} else {

			var index = $('#publicInfoTitleState').val() - 7;
			if (index <= 6 && index > 0) {
				var paperGuide = {};
				var titleId = $('#publicInfoSelect').combobox('getValue');

				paperGuide.guidance = $('#paperGuide' + index).val();

				if (paperGuide.guidance.length < 20) {
					alert('指导内容长度不可小于20!');
					$('#paperGuide' + index).focus();
					return;
				}
				paperGuide.guidanceType = $('#paperGuideCombox' + index).val();

				paperGuide.guidanceDate = parseDate($(
						'#paperGuideDatebox' + index).datebox('getValue'));
				paperGuideDWR.updateWithTitleId(titleId, paperGuide, function(
						msg) {
					if (msg) {
						alert('指导记录提交成功!');
						$('#publicInfoTitleState').val(index + 8);
						printGuide();
					} else
						alert('指导记录提交失败!');
				});
			} else
				alert('没有指导记录需要提交！');
		}
	});
}