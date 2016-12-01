function replayApplyLoad() {
	var replyApplyStudentId = $('#replyApplyStudentId').val();

	if (!isNaN(replyApplyStudentId))
		titleDWR.findTitleByStudentId(replyApplyStudentId, 15, function(data) {
			if (data.length == 1) {
				$('#replyApplyTitleName').val(data[0].paperTitleName);
				$('#replyApplyTitleId').val(data[0].paperTitleId);
				replayApplyHtml(data[0].paperTitleId);
			} else {
				$.messager.alert('系統提示', '暂时没有需要填写的答辩申请!', 'info');
				top.mainFrame.closePaperTab('答辩申请');
				return;
			}
		});
}

function replayApplyHtml(titleId) {
	paperApply.findByTitleId(titleId, function(data) {
		if (data) {
			var len = data.length - 1;
			if (len >= 0) {
				$('#replyApplyReason').attr('disabled', true);
				$('#replyApplyDate').datebox({
					disabled : true
				});
				$('#replyApplyReason').val(data[len].applyReason);
				$('#replyApplyDate').datebox('setValue',
						myformatter(data[len].applyDate));
				if (data[len].score != null) {
					$('#replyApplySubmit').attr('disabled', true);
					var scoreArray = data[len].score.split(",");
					if (scoreArray.length == 7) {
						for ( var i = 0; i < 7; i++)
							$('#score' + (i + 1)).val(scoreArray[i]);
					}
					$('#allowReply').val(data[len].allowReply);
					if (data[len].allowReply == "是")
						$('#newReplyApply').attr('disabled', true);
					$('#replyInpuDate').datebox('setValue',
							myformatter(data[len].inpuDate));

				}
			}
		}
	});
}

function replyApplySave() {

	if (!$('#replyApplyForm').form('validate'))
		return false;

	var titleId = $('#replyApplyTitleId').val();
	var replyAppl = {};
	replyAppl.applyReason = $('#replyApplyReason').val();
	replyAppl.applyDate = parseDate($('#replyApplyDate').datebox('getValue'));

	PaperMidcheck.findByTitleId(titleId, function(data) {
		if (data.length == 0) {
			alert('还没有填写中期检查,不能申请答辩!');
			return false;
		}

		paperApply.findByTitleId(titleId, function(data) {
			var len = data.length - 1;
			if (len < 0) {

			} else if (data[len].applyReason != ''
					&& data[len].allowReply == null) {
				$.messager.alert('系統提示', '亲,不要急,要先等教师审批哦!', 'info');
				return false;
			} else if (data[len].allowReply == "是") {
				$.messager.alert('系統提示', '已申请成功,请不要重复提交!', 'info');
				return false;
			} else if ($('#replyApplyReason').attr('disabled') == "disabled") {
				$.messager.alert('系統提示', '提交失败,如需重新发起答辩请点击 发起新申请 按钮!', 'info');
				return false;
			}
			paperApply.saveWithTitleId(titleId, replyAppl, function(msg) {
				if (msg) {
					if (msg) {
						$.messager.alert('系統提示', '提交成功!', 'info');
						$('#replyApplyReason').attr('disabled', true);
						$('#replyApplyDate').datebox({
							disabled : true
						});
					} else
						$.messager.alert('系統提示', '提交失败!', 'error');
				}
			});
		});
	});
}

function newReplyApply() {

	var titleId = $('#replyApplyTitleId').val();
	paperApply.findByTitleId(titleId, function(data) {
		var len = data.length - 1;
		if (data[len].allowReply != "否") {
			alert('亲,只有申请失败才能发起新申请哦!');
			return false;
		}

		$('#replyApplyReason').attr('disabled', false);
		$('#replyApplyDate').datebox({
			disabled : false
		});
		$('#replyApplyReason').val('');
		$('#replyApplyDate').datebox('setValue', myformatter(new Date()));

		for ( var i = 0; i < 7; i++)
			$('#score' + (i + 1)).val('');

		$('#allowReply').val('');
		$('#replyInpuDate').datebox('setValue', myformatter(new Date()));
	});
	$('#replyApplySubmit').removeAttr('disabled');
}