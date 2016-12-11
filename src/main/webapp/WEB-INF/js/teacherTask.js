function teacherTaskLoad() {
	titleDWR.findTitleByTeacherId($('#publicInfoTeacherId').val(), 4,

	function(data) {
		if (data == null || data.length == 0) {
			$.messager.alert('系統提示', '您还没有任务书需要填写!', 'info');
			top.mainFrame.closePaperTab('写任务书');
			return;
		}
		$('#publicInfoSelect').combobox(
				{
					data : data,
					valueField : 'paperTitleId',
					textField : 'paperTitleName',
					formatter : function(row) {
						var s = '';
						if (row.paperTitleState == 6) {
							s = '<span style="font-weight:bold;color:red">'
									+ row.paperTitleName + '</span>';
						} else {
							s = '<span style="font-weight:bold">'
									+ row.paperTitleName + '</span>';
						}
						return s;
					},
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
								writingTaskHtml(rec.paperTitleId);
							} else
								$.messager.alert('系統提示', '学生信息读取失败!', 'error');
						});
					}
				});
	});
}

function writingTaskHtml(titleId) {
	writingTask.findByTieleId(titleId, function(data) {

		for ( var i = 3; i < 7; i++) {
			$('#planTask' + i).val('');
			$('#writingTaskPlanStartTime' + i).val('');
			$('#writingTaskPlanEndTime' + i).val('');
		}

		if (data.length == 1) {

			$('#writingTaskSubmit').attr('disabled', true);
			$('#writingTaskStrartTime').attr('disabled', true);
			$('#writingTaskEndTime').attr('disabled', true);
			$('#writingTaskReference').attr('disabled', true);
			$('#writingTaskContent').attr('disabled', true);

			$('#writingTaskStrartTime').val(
					myformatter(data[0].writingTaskStartTime));
			$('#writingTaskEndTime').val(
					myformatter(data[0].writingTaskEndTime));
			$('#writingTaskReference').val(data[0].writingTaskReference);
			$('#writingTaskContent').val(data[0].writingTaskContent);

			paperPlan.findByWritingTaskID(data[0].writingTaskID,

			function(data) {
				for ( var i = 1; i <= data.length; i++) {
					$('#planTask' + i).attr('disabled', true);
					$('#writingTaskPlanStartTime' + i).attr('disabled', true);
					$('#writingTaskPlanEndTime' + i).attr('disabled', true);

					$('#planTask' + i).val(data[i - 1].planTask);
					$('#writingTaskPlanStartTime' + i).val(
							myformatter(data[i - 1].planStartTime));
					$('#writingTaskPlanEndTime' + i).val(
							myformatter(data[i - 1].planEndTime));
				}
			});
		} else {
			$('#writingTaskSubmit').attr('disabled', false);

			$('#writingTaskStrartTime').attr('disabled', false);
			$('#writingTaskEndTime').attr('disabled', false);
			$('#writingTaskReference').attr('disabled', false);
			$('#writingTaskContent').attr('disabled', false);

			$('#writingTaskStrartTime').val('');
			$('#writingTaskEndTime').val('');
			$('#writingTaskReference').val('');
			$('#writingTaskContent').val('');

			for ( var i = 1; i < 7; i++) {
				$('#planTask' + i).attr('disabled', false);
				$('#writingTaskPlanStartTime' + i).attr('disabled', false);
				$('#writingTaskPlanEndTime' + i).attr('disabled', false);

				$('#planTask' + i).val('');
				$('#writingTaskPlanStartTime' + i).val('');
				$('#writingTaskPlanEndTime' + i).val('');
			}
		}
	});
}

function saveWritingTask() {

	var titleId = $('#publicInfoSelect').combobox('getValue');

	if (isNaN(titleId)) {
		$.messager.alert('系統提示', '请先选择一个题目!', 'warning');
		return false;
	}

	if (!$('#writingTaskForm').form('validate'))
		return false;

	writingTask
			.findByTieleId(
					titleId,

					function(data) {
						if (data.length == 1) {
							$.messager.alert('系統提示', '此论文已提交过任务书,请不要重复提交!',
									'warning');
							return false;
						}

						var paperWritingTask = {};
						var list = [];

						paperWritingTask.writingTaskStartTime = parseDate($(
								'#writingTaskStrartTime').val());
						paperWritingTask.writingTaskEndTime = parseDate($(
								'#writingTaskEndTime').val());
						paperWritingTask.writingTaskContent = $(
								'#writingTaskContent').val();
						paperWritingTask.writingTaskReference = $(
								'#writingTaskReference').val();

						for ( var i = 1; i < 7; i++) {

							var writingTaskPlan = {};

							var planTask = $('#planTask' + i).val();

							var planStartTime = $(
									'#writingTaskPlanStartTime' + i).val();
							var writingTaskPlanEndTime = $(
									'#writingTaskPlanEndTime' + i).val();

							if (planTask.length > 1 && planStartTime.length > 1
									&& writingTaskPlanEndTime.length > 1) {

								writingTaskPlan.planTask = planTask;
								writingTaskPlan.planStartTime = parseDate(planStartTime);
								writingTaskPlan.planEndTime = parseDate(writingTaskPlanEndTime);

								list.push(writingTaskPlan);
							}
						}

						writingTask.saveWritingTask(titleId, paperWritingTask,
								list, function(msg) {
									if (msg) {
										$('#writingTaskStrartTime').attr(
												'disabled', true);
										$('#writingTaskEndTime').attr(
												'disabled', true);
										$('#writingTaskReference').attr(
												'disabled', true);
										$('#writingTaskContent').attr(
												'disabled', true);

										for ( var i = 1; i < 7; i++) {
											$('#planTask' + i).attr('disabled',
													true);
											$('#writingTaskPlanStartTime' + i)
													.attr('disabled', true);
											$('#writingTaskPlanEndTime' + i)
													.attr('disabled', true);
										}
										$.messager.alert('系統提示', '任务书提交成功!',
												'warning');
									} else
										$.messager.alert('系統提示', '任务书提交失败!',
												'warning');
								});
					});
}