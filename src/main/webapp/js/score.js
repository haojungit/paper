function scoreLoad() {
	var paperGuideStudentId = $('#studentPublicInfoStudentId').val();

	if (!isNaN(paperGuideStudentId))
		titleDWR.findTitleByStudentId(paperGuideStudentId, 7, function(data) {
			if (data.length == 1) {
				$('#studentPublicInfoTitleName').html(data[0].paperTitleName);
				$('#studentPublicInfoTeacherName')
						.val(data[0].paperTeacherName);
				$('#studentPublicInfoTitleId').val(data[0].paperTitleId);
				$('#studentPublicInfoTitleState').val(data[0].paperTitleState);
				getScore(data[0].paperTitleId);
			} else {
				$.messager.alert('系統提示', '暂时还没有论文指导记录!', 'info');
				return;
			}
		});
}

function getScore(titleId) {
	paperScoreDWR.findScoreByTitleId(titleId, function(data) {
		if (data == null) {
			$.messager.alert('系統提示', '没有找到成绩单!', 'error');
			return;
		}
		$("#t1").text(data.tcomment);
		$("#t2").text(data.tscore);
		$("#t3").text(data.paperTitle.paperTeacher.teacherName);
		$("#t4").text(data.paperTitle.paperRecord.teamAdvice);
		$("#t5").text(data.committeeScore);
		// $("#t6").text('负责人是谁');
		$("#t7").text(data.totalScore);
		$("#t8").text(data.committeeScore);
		$("#t9").text(data.paperTitle.paperRecord.councilAdvice);
	});
}
