function teacherInterimCheckUpLoad() {

	$("#bt_checkupMsg").click(function() {
		sendto($('#publicInfoStudentNumber').val(), "我查看了你的中期检查表。");
	});

	titleDWR.findTitleByTeacherId($('#publicInfoTeacherId').val(), 8,

	function(data) {
		if (data == null || data.length == 0) {
			$.messager.alert('系統提示', '您还没有中期检查需要填写!', 'info');
			top.mainFrame.closePaperTab('中期检查');
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
										interimCheckUpHtml(rec.paperTitleId);
									} else
										$.messager.alert('系統提示', '学生信息读取失败!',
												'error');
								});
					}
				});
	});
}

function interimCheckUpHtml(titleId) {
	PaperMidcheck.findByTitleId(titleId,
			function(data) {
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
						$('#interImCheckSubmit').attr('disabled', true);
						$('#tComment').attr('disabled', true);
						$('#tCommentDate').datebox({
							disabled : true
						});
						$('#tComment').val(data[0].tcomment);
						$('#tCommentDate').datebox('setValue',
								myformatter(data[0].tcommentDate));
					} else {
						$('#tComment').attr('disabled', false);
						$('#tCommentDate').datebox({
							disabled : false
						});
						$('#tComment').val('');
						$('#tCommentDate').datebox('setValue',
								myformatter(new Date()));
					}
				} else {

					$('#interImCheckSubmit').attr('disabled', false);

					$('#tComment').attr('disabled', false);
					$('#tCommentDate').datebox({
						disabled : false
					});
					$('#interimCheckProgress').val('');
					$('#interimCheckFinishDate').datebox('setValue',
							myformatter(new Date()));
					$('#tComment').val('');
					$('#tCommentDate').datebox('setValue',
							myformatter(new Date()));
				}
			});
}

function interImCheckUpSave() {

	var titleId = $('#publicInfoSelect').combobox('getValue');
	if (isNaN(titleId)) {
		$.messager.alert('系統提示', '请选择一个题目!', 'warning');
		return;
	}
	var midcheck = {};
	midcheck.tcomment = $('#tComment').val();
	midcheck.tcommentDate = parseDate($('#tCommentDate').datebox('getValue'));

	PaperMidcheck.findByTitleId(titleId, function(data) {
		if (data.length == 0) {
			$.messager.alert('系統提示', '学生还未提交,请通知学生填写中期检查!', 'warning');
			return false;
		}
		if (data[0].tcomment != null) {
			$.messager.alert('系統提示', '请不要重复提交!', 'error');
			return;
		}
		PaperMidcheck.updateWithTitleId(titleId, midcheck, function(msg) {
			if (msg) {
				if (msg) {
					$.messager.alert('系統提示', '提交成功!', 'info');
					$('#tComment').attr('disabled', true);
					$('#tCommentDate').datebox({
						disabled : true
					});
				} else
					$.messager.alert('系統提示', '提交失败!', 'error');
			}
		});
	});
}