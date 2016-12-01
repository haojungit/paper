function studentTaskLoad() {
	var myTaskStudentId = $('#studentPublicInfoStudentId').val();
	if (!isNaN(myTaskStudentId))
		titleDWR.findTitleByStudentId(myTaskStudentId, 5, function(data) {
			if (data.length == 1) {
				$('#studentPublicInfoTitleName').html(data[0].paperTitleName);
				$('#studentPublicInfoTeacherName')
						.val(data[0].paperTeacherName);
				$('#studentPublicInfoTitleId').val(data[0].paperTitleId);
				$('#studentPublicInfoTitleState').val(data[0].paperTitleState);
				findByTieleId();
			} else {
				$.messager.alert('系統提示', '您的导师还未填写任务书!', 'info');
				top.mainFrame.closePaperTab('任务书');
				return;
			}
		});
}

function findByTieleId() {
	writingTask.findByTieleId($('#studentPublicInfoTitleId').val(), function(
			data) {
		$('#writingTaskStrartTime').html(
				myformatter(data[0].writingTaskStartTime));
		$('#writingTaskEndTime').html(myformatter(data[0].writingTaskEndTime));
		$('#writingTaskReference').html(data[0].writingTaskReference);
		$('#writingTaskContent').html(data[0].writingTaskContent);
		findByWritingTaskID(data[0].writingTaskID);
	});
}

function findByWritingTaskID(writingTaskID) {

	paperPlan.findByWritingTaskID(writingTaskID, function(list) {
		var html = '';
		for ( var i = 0; i < list.length; i++) {
			html = '<tr align="center">';
			html += '<td height="34">' + (i + 1) + '</td>';
			html += '<td><a>' + list[i].planTask + '</a></td>';
			html += '<td><a>' + myformatter(list[i].planStartTime)
					+ '</a></td>';
			html += '<td><a>' + myformatter(list[i].planEndTime)
					+ '</a></td></tr>';
			$('#planTask').append(html);
		}
	});
}