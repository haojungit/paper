function interimCheckUp() {
	var interimCheckUpStudentId = $('#studentPublicInfoStudentId').val();
	if (!isNaN(interimCheckUpStudentId))
		titleDWR.findTitleByStudentId(interimCheckUpStudentId, 8,
				function(data) {
					if (data.length == 1 && data[0].paperTitleState > 8
							&& data[0].paperTitleState <= 16) {
						$('#studentPublicInfoTitleName').html(
								data[0].paperTitleName);
						$('#studentPublicInfoTeacherName').val(
								data[0].paperTeacherName);
						$('#studentPublicInfoTitleId')
								.val(data[0].paperTitleId);
						$('#studentPublicInfoTitleState').val(
								data[0].paperTitleState);
						studentInterimCheckUpHtml(data[0].paperTitleId);
					} else {
						$.messager.alert('系統提示', '暂时没有需要填写的中期检查!', 'info');
						top.mainFrame.closePaperTab('中期检查');
						return;
					}
				});
}

function studentInterimCheckUpHtml(titleId) {
	PaperMidcheck.findByTitleId(titleId, function(data) {
		if (data.length == 1) {
			$('#interimCheckProgress').attr('disabled', true);
			$('#interimCheckFinishDate').datebox({
				disabled : true
			});
			$('#interimCheckProgressDate').datebox({
				disabled : true
			});
			$('#interimCheckProgress').val(data[0].progress);
			$('#interimCheckFinishDate').datebox('setValue',
					myformatter(data[0].finishDate));
			$('#interimCheckProgressDate').datebox('setValue',
					myformatter(data[0].progressDate));
			if (data[0].tcommentDate != null) {
				$('#tComment').val(data[0].tcomment);
				$('#tCommentDate').datebox('setValue',
						myformatter(data[0].tcommentDate));
			}
		}
	});
}

function interImCheckUpSave() {

	var titleId = $('#studentPublicInfoTitleId').val();
	var midcheck = {};
	midcheck.finishDate = parseDate($('#interimCheckFinishDate').datebox(
			'getValue'));
	midcheck.progress = $('#interimCheckProgress').val();
	midcheck.progressDate = parseDate($('#interimCheckProgressDate').datebox(
			'getValue'));
	PaperMidcheck.findByTitleId(titleId, function(data) {
		if (data.length == 1) {
			$.messager.alert('系統提示', '请不要重复提交!', 'warning');
			return false;
		}
		PaperMidcheck.saveWithTitleId(titleId, midcheck, function(msg) {
			if (msg) {
				if (msg) {
					$.messager.alert('系統提示', '提交成功!', 'info');
					$('#interimCheckProgress').attr('disabled', true);
					$('#interimCheckFinishDate').datebox({
						disabled : true
					});
					$('#interimCheckProgressDate').datebox({
						disabled : true
					});
				} else
					$.messager.alert('系統提示', '提交失败!', 'error');
			}
		});
	});
}