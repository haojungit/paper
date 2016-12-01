function teacherScoreInfo() {
	titleDWR.findTitleByTeacherId($('#publicInfoTeacherId').val(), 8,
			
	function(data) {
		/*if (data == null || data.length == 0) {
			alert('您还没有中期检查需要填写！');
			top.mainFrame.closePaperTab('中期检查');
			return;
		}*/
		$("#bt_save").click(function(){
			
		});
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
										showScore(rec.paperTitleId);
									} else
										alert('学生信息读取失败！');
								});
					}
				});
	});
}

function showScore(titleId) {
	paperScoreDWR.findScoreByTitleId(titleId,function(data){
		$("#teacher_t").val(data.tcomment);
		$("#teacher_s").val(data.tscore);
		$("#team_t").val(data.committeeComment);
		$("#team_s").val(data.committeeScore);
		$("#totalScore").val(data.totalScore);
		$("#council_t").val(data.council_t);
	});
}

function savaOrUpdate() {

	
}