function teacherThesisProposalLoad() {
	titleDWR.findTitleByTeacherId($('#publicInfoTeacherId').val(), 6,

	function(data) {
		if (data == null || data.length == 0) {
			$.messager.alert('系統提示', '您还没有开题报告需要批阅!', 'info');
			top.mainFrame.closePaperTab('开题报告');
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
										showthesisProposal();
									} else
										$.messager.alert('系統提示', '学生信息读取失败!',
												'error');
								});
					}
				});
	});
}

function showthesisProposal() {
	thesisProposal.findByTitleId($('#publicInfoSelect').combobox('getValue'),

	function(data) {
		$('#thesisProposalBackground').val(data[0].thesisProposalBackground);
		$('#thesisProposalResearchContent').val(
				data[0].thesisProposalResearchContent);
		$('#thesisProposalResearchMethod').val(
				data[0].thesisProposalResearchMethod);
		$('#thesisProposalResearchSchedule').val(
				data[0].thesisProposalResearchSchedule);
		$('#thesisProposalReference').val(data[0].thesisProposalReference);
		if ($('#publicInfoTitleState').val() != 7) {

			$('#thesisProposalSubmit').attr('disabled', true);

			$('#thesisProposalTeacherSug').attr('disabled', true);
			$('#teacherSugDate').datebox({
				disabled : true
			});
			$('#thesisProposalTeacherSug')
					.val(data[0].thesisProposalTeacherSug);
			$('#teacherSugDate').datebox('setValue',
					myformatter(data[0].teacherSugDate));
		} else {

			$('#thesisProposalSubmit').attr('disabled', false);

			$('#thesisProposalTeacherSug').attr('disabled', false);
			$('#teacherSugDate').datebox({
				disabled : false
			});
			$('#thesisProposalTeacherSug').val('');
			$('#teacherSugDate').datebox('setValue', '');
		}
	});
}

function thesisProposalUpdate() {

	var titleId = $('#publicInfoSelect').combobox('getValue');

	if (isNaN(titleId)) {
		$.messager.alert('系統提示', '请先选择一个题目!', 'warning');
		return false;
	}

	if (!$('#teacherThesisProposalForm').form('validate'))
		return false;

	var tProposal = {};
	tProposal.thesisProposalTeacherSug = $('#thesisProposalTeacherSug').val();
	tProposal.teacherSugDate = parseDate($('#teacherSugDate').datebox(
			'getValue'));
	thesisProposal.findByTitleId(titleId, function(data) {
		if ($('#publicInfoTitleState').val() != 7) {
			$.messager.alert('系統提示', '请不要重复提交!', 'warning');
			return false;
		}
		thesisProposal.updateWithTitleId(titleId, tProposal, function(msg) {
			if (msg) {
				if (msg) {
					$.messager.alert('系統提示', '提交成功!', 'info');
					$('#thesisProposalTeacherSug').attr('disabled', true);
					$('#teacherSugDate').datebox({
						disabled : true
					});
				} else
					$.messager.alert('系統提示', '提交失败!', 'error');
			}
		});
	});
}