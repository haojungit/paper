function downLoadPaper() {

	$('#guidId').val($('#paperGuideDownLoad').val());
	$('#downloadPaperForm').submit();
}

function ValidateUploadPaperForm() {

	if (!$('#uploadPaperForm').form('validate')) {
		$.messager.alert('系統提示', '请选择Word(doc或docx)文件!', 'warning');
		return false;
	}

	if (($('#studentPublicInfoTitleState').val() - 8) >= 6) {
		$.messager.alert('系統提示', '全部指导已结束,不可上传!', 'error');
		return false;
	}

	paperGuideDWR
			.findWithTitleId(
					$('#studentPublicInfoTitleId').val(),

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
								$('#studentPublicInfoTitleId').val());
						$('#uploadPaperForm').submit();
					});
}

function StudentPaperGuideLoad() {
	var paperGuideStudentId = $('#studentPublicInfoStudentId').val();

	if (!isNaN(paperGuideStudentId))
		titleDWR.findTitleByStudentId(paperGuideStudentId, 7, function(data) {
			if (data.length == 1) {
				$('#studentPublicInfoTitleName').html(data[0].paperTitleName);
				$('#studentPublicInfoTeacherName')
						.val(data[0].paperTeacherName);
				$('#studentPublicInfoTitleId').val(data[0].paperTitleId);
				$('#studentPublicInfoTitleState').val(data[0].paperTitleState);
				StudentprintGuide($('#studentPublicInfoTitleState').val());
			} else {
				$.messager.alert('系統提示', '暂时还没有论文指导记录!', 'info');
				top.mainFrame.closePaperTab('论文指导');
				return;
			}
		});
}

function StudentprintGuide(titleState) {
	var html = "";
	var html1 = "";
	paperGuideDWR
			.findWithTitleId(
					$('#studentPublicInfoTitleId').val(),

					function(data) {
						for ( var i = 0; i < data.length; i++) {
							if (data[i].guidance != null
									&& data[i].guidance != '')
								html += '<tr style="border:1px;"><td class="left15">第<a style="color:red">&nbsp;'
										+ (i + 1)
										+ '&nbsp;</a>次指导：<br /><br /><a style="color:red;padding-left:20px;">'
										+ data[i].guidance
										+ '</a><br /><br /><br /> <span class="print">指导方式：<a style="color:red">'
										+ data[i].guidanceType
										+ '</a></span><br /><br /> <span class="print fl">指导教师（签字）： </span><span'
										+ 'class="print fr" style="padding-right: 200px; padding-bottom: 10px;">'
										+ '<br/><br/>日期：<a style="color:red">'
										+ myformatter(data[i].guidanceDate)
										+ '</a><br /><br />'
										+ '</span></td></tr>';
							html1 += '<option selected="selected" value="'
									+ data[i].guidId + '">第' + (i + 1)
									+ '次指导</option>';
						}
						$('#paperGuides').html(html);
						$('#paperGuideDownLoad').html(html1);
						$('#paperGuideDatebox' + (i + 1)).datebox({
							required : true,
							formatter : myformatter,
							parser : myparser,
							editable : false
						});
					});
}
