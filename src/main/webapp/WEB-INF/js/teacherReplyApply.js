function teacherReplyApplyLoad() {
	var replyApplyTeacherId = $('#publicInfoTeacherId').val();
	if (!isNaN(replyApplyTeacherId))
		titleDWR.findTitleByTeacherId(replyApplyTeacherId, 15, function(data) {
			if (data == null || data.length == 0) {
				$.messager.alert('系統提示', '您还没有答辩审批需要填写!', 'info');
				top.mainFrame.closePaperTab('答辩审批');
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
											$('#publicInfoStudentGrade').val(
													data[0].studentGrade);
											$('#publicInfoStudentFaculty').val(
													data[0].studentFaculty);
											$('#publicInfoStudentMajor').val(
													data[0].studentMajor);
											replayApplyHtml(rec.paperTitleId);

											$("#stuId").val(
													data[0].studentNumber);
										} else
											$.messager.alert('系統提示',
													'学生信息读取失败!', 'error');
									});
						}
					});
		});
}

function replayApplyHtml(titleId) {
	paperApply.findByTitleId(titleId, function(data) {
		if (data && data != '') {
			var len = data.length - 1;
			if (len >= 0) {

				$('#replyApplySubmit').attr('disabled', true);

				$('#replyApplyReason').attr('disabled', true);
				$('#replyApplyDate').datebox({
					disabled : true
				});
				$('#replyApplyReason').val(data[len].applyReason);
				$('#replyApplyDate').datebox('setValue',
						myformatter(data[len].applyDate));
				if (data[len].score != null) {
					var scoreArray = data[len].score.split(",");
					if (scoreArray.length == 7) {
						for ( var i = 0; i < 7; i++) {
							$('#score' + (i + 1)).val(scoreArray[i]);
							$('#score' + (i + 1)).attr('disabled', true);
						}
					}
					$('#allowReply').val(data[len].allowReply);
					$('#allowReply').attr('disabled', true);
					$('#replyInpuDate').datebox('setValue',
							myformatter(data[len].inpuDate));
					$('#replyInpuDate').datebox({
						disabled : true
					});
				} else {

					$('#replyApplySubmit').attr('disabled', false);

					for ( var i = 0; i < 7; i++) {
						$('#score' + (i + 1)).val('');
						$('#score' + (i + 1)).attr('disabled', false);
					}
					$('#allowReply').attr('disabled', false);
					$('#replyInpuDate').datebox('setValue',
							myformatter(new Date()));
					$('#replyInpuDate').datebox({
						disabled : false
					});
				}
			}
		} else {
			$.messager.alert('系統提示', '学生还未填写答辩申请,请选择其他题目!', 'error');
		}
	});
}

function replyApplyUpdate() {

	var titleId = $('#publicInfoSelect').combobox('getValue');
	if (isNaN(titleId)) {
		$.messager.alert('系統提示', '请选择一个题目!', 'warning');
		return;
	}
	if (!$('#replyApplyForm').form('validate'))
		return false;
	var replyAppl = {};
	var score = [];
	for ( var i = 1; i <= 7; i++) {
		score.push($('#score' + i).val());
	}
	replyAppl.score = score.join(',');
	replyAppl.allowReply = $('#allowReply').val();
	replyAppl.inpuDate = parseDate($('#replyInpuDate').datebox('getValue'));

	paperApply.findByTitleId(titleId, function(data) {
		if (!data || data == '') {
			$.messager.alert('系統提示', '学生还未填写答辩申请!', 'warning');
			return false;
		}
		if (data.length >= 1 && data[data.length - 1].allowReply == '是') {
			$.messager.alert('系統提示', '请不要重复提交!', 'warning');
			return false;
		} else if ($('#allowReply').attr('disabled') == "disabled") {
			$.messager.alert('系統提示', '老师您好,请不要重复提交,谢谢合作!', 'info');
			return false;
		}
		paperApply.updateWithTitleId(titleId, replyAppl, function(msg) {
			if (msg) {
				if (msg) {
					$.messager.alert('系統提示', '提交成功!', 'info');
					for ( var i = 0; i < 7; i++)
						$('#score' + (i + 1)).attr('disabled', true);
					$('#allowReply').attr('disabled', true);
					$('#replyInpuDate').datebox({
						disabled : true
					});
				} else
					$.messager.alert('系統提示', '提交失败!', 'error');
				$('#replyApplySubmit').attr('disabled', false);
			}
		});
	});
}