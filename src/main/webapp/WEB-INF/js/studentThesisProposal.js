function studentThesisProposalLoad() {
	var thesisProposalStudentId = $('#studentPublicInfoStudentId').val();
	if (!isNaN(thesisProposalStudentId))
		titleDWR.findTitleByStudentId(thesisProposalStudentId, 5,
				function(data) {
					if (data.length == 1) {
						$('#studentPublicInfoTitleName').html(
								data[0].paperTitleName);
						$('#studentPublicInfoTeacherName').val(
								data[0].paperTeacherName);
						$('#studentPublicInfoTitleId')
								.val(data[0].paperTitleId);
						$('#studentPublicInfoTitleState').val(
								data[0].paperTitleState);
						showthesisProposal();

						$("#msgButton").click(function() {
							sendToMyTeacher('msgText');
						});

					} else {
						$.messager.alert('系統提示', '暂时没有需要填写的开题报告!', 'info');
						top.mainFrame.closePaperTab('开题报告');
						return;
					}
				});

}

function showthesisProposal() {
	thesisProposal.findByTitleId($('#studentPublicInfoTitleId').val(),

	function(data) {
		if ($('#studentPublicInfoTitleState').val() > 6) {

			$('#thesisProposalSubmit').attr('disabled', true);

			$('#thesisProposalBackground').attr('disabled', true);
			$('#thesisProposalResearchContent').attr('disabled', true);
			$('#thesisProposalResearchMethod').attr('disabled', true);
			$('#thesisProposalResearchMethod').attr('disabled', true);
			$('#thesisProposalResearchSchedule').attr('disabled', true);
			$('#thesisProposalReference').attr('disabled', true);

			$('#thesisProposalBackground')
					.val(data[0].thesisProposalBackground);
			$('#thesisProposalResearchContent').val(
					data[0].thesisProposalResearchContent);
			$('#thesisProposalResearchMethod').val(
					data[0].thesisProposalResearchMethod);
			$('#thesisProposalResearchSchedule').val(
					data[0].thesisProposalResearchSchedule);
			$('#thesisProposalReference').val(data[0].thesisProposalReference);

			if ($('#studentPublicInfoTitleState').val() > 7) {
				$('#thesisProposalTeacherSug').val(
						data[0].thesisProposalTeacherSug);
				$('#teacherSugDate').datebox('setValue',
						myformatter(data[0].teacherSugDate));
			}
		}
	});
}

function thesisProposalSubmit() {

	$('#thesisProposalSubmit').attr('disabled', true);

	var titleID = $('#studentPublicInfoTitleId').val();

	if (!$('#studentThesisProposalForm').form('validate'))
		return false;

	thesisProposal.findByTitleId($('#studentPublicInfoTitleId').val(),

	function(data) {
		if (data.length == 1) {
			$.messager.alert('系統提示', '请不要重复提交!', 'warning');
			return false;
		}
	});

	var paperThesisProposal = {};
	paperThesisProposal.thesisProposalBackground = $(
			'#thesisProposalBackground').val();
	paperThesisProposal.thesisProposalResearchContent = $(
			'#thesisProposalResearchContent').val();
	paperThesisProposal.thesisProposalResearchMethod = $(
			'#thesisProposalResearchMethod').val();
	paperThesisProposal.thesisProposalResearchSchedule = $(
			'#thesisProposalResearchSchedule').val();
	paperThesisProposal.thesisProposalReference = $('#thesisProposalReference')
			.val();

	thesisProposal.saveWithTitleId(titleID, paperThesisProposal, function(msg) {
		if (msg) {
			$('#thesisProposalBackground').attr('disabled', true);
			$('#thesisProposalResearchContent').attr('disabled', true);
			$('#thesisProposalResearchMethod').attr('disabled', true);
			$('#thesisProposalResearchMethod').attr('disabled', true);
			$('#thesisProposalResearchSchedule').attr('disabled', true);
			$('#thesisProposalReference').attr('disabled', true);
			$.messager.alert('系統提示', '提交成功!', 'info');
		} else
			$.messager.alert('系統提示', '提交失败!', 'error');
	});
}