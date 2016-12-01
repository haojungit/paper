/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : paper

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2015-12-22 21:41:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `paper_apply`
-- ----------------------------
DROP TABLE IF EXISTS `paper_apply`;
CREATE TABLE `paper_apply` (
  `AplyID` int(11) NOT NULL AUTO_INCREMENT,
  `titleId` int(11) NOT NULL,
  `applyReason` varchar(1024) DEFAULT NULL,
  `applyDate` varchar(24) DEFAULT NULL,
  `Score` varchar(24) DEFAULT NULL,
  `allowReply` varchar(1) DEFAULT NULL,
  `inpuDate` varchar(24) DEFAULT NULL,
  `flag` int(1) DEFAULT NULL,
  PRIMARY KEY (`AplyID`),
  KEY `FK_paper_apply` (`titleId`),
  CONSTRAINT `FK_paper_apply` FOREIGN KEY (`titleId`) REFERENCES `paper_title` (`PaperTitleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_apply
-- ----------------------------

-- ----------------------------
-- Table structure for `paper_council`
-- ----------------------------
DROP TABLE IF EXISTS `paper_council`;
CREATE TABLE `paper_council` (
  `CouncilId` int(11) NOT NULL AUTO_INCREMENT,
  `Department` varchar(32) DEFAULT NULL,
  `Major` varchar(32) DEFAULT NULL,
  `Grade` varchar(32) DEFAULT NULL,
  `deanSug` varchar(512) DEFAULT NULL,
  `deanDate` varchar(24) DEFAULT NULL,
  `DeanOfficeSug` varchar(512) DEFAULT NULL,
  `DeanOfficeDate` varchar(24) DEFAULT NULL,
  `State` int(8) DEFAULT '0',
  PRIMARY KEY (`CouncilId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_council
-- ----------------------------

-- ----------------------------
-- Table structure for `paper_councilman`
-- ----------------------------
DROP TABLE IF EXISTS `paper_councilman`;
CREATE TABLE `paper_councilman` (
  `CouncilmanId` int(11) NOT NULL AUTO_INCREMENT,
  `CouncilId` int(8) NOT NULL,
  `TeacherId` int(11) NOT NULL,
  `GroupMember` varchar(64) DEFAULT NULL,
  `GroupMembersName` varchar(64) DEFAULT NULL,
  `Chair` int(8) DEFAULT '0',
  PRIMARY KEY (`CouncilmanId`),
  KEY `FK_paper_councilman_idx` (`CouncilId`),
  KEY `FK_paper_councilman1_idx` (`TeacherId`),
  CONSTRAINT `FK_paper_councilman` FOREIGN KEY (`CouncilId`) REFERENCES `paper_council` (`CouncilId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_paper_councilman1` FOREIGN KEY (`TeacherId`) REFERENCES `paper_teacher` (`teacherId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_councilman
-- ----------------------------

-- ----------------------------
-- Table structure for `paper_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `paper_dictionary`;
CREATE TABLE `paper_dictionary` (
  `DictionaryId` int(11) NOT NULL AUTO_INCREMENT,
  `DictionaryKey` int(11) DEFAULT NULL,
  `DictionaryValue` varchar(1024) DEFAULT NULL,
  `Type` varchar(512) DEFAULT NULL,
  `flag` int(11) DEFAULT '1',
  PRIMARY KEY (`DictionaryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for `paper_guidancerecord`
-- ----------------------------
DROP TABLE IF EXISTS `paper_guidancerecord`;
CREATE TABLE `paper_guidancerecord` (
  `GuidId` int(11) NOT NULL AUTO_INCREMENT,
  `titleId` int(11) DEFAULT NULL,
  `Guidance` varchar(1024) DEFAULT NULL,
  `GuidanceType` varchar(8) DEFAULT NULL,
  `GuidanceDate` varchar(24) DEFAULT NULL,
  `GuidanceUpDate` varchar(24) DEFAULT NULL,
  `filePath` varchar(256) DEFAULT NULL,
  `flag` int(8) DEFAULT '1',
  `remark` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`GuidId`),
  KEY `titleId` (`titleId`),
  CONSTRAINT `paper_guidancerecord_ibfk_1` FOREIGN KEY (`titleId`) REFERENCES `paper_title` (`PaperTitleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_guidancerecord
-- ----------------------------

-- ----------------------------
-- Table structure for `paper_midcheck`
-- ----------------------------
DROP TABLE IF EXISTS `paper_midcheck`;
CREATE TABLE `paper_midcheck` (
  `McId` int(11) NOT NULL AUTO_INCREMENT,
  `TitleId` int(11) NOT NULL,
  `FinishDate` varchar(24) DEFAULT NULL,
  `Progress` varchar(1024) DEFAULT NULL,
  `ProgressDate` varchar(24) DEFAULT NULL,
  `Tcomment` varchar(1024) DEFAULT NULL,
  `TcommentDate` varchar(24) DEFAULT NULL,
  `flag` int(11) DEFAULT '1',
  PRIMARY KEY (`McId`),
  KEY `FK1_paper_midcheck` (`TitleId`),
  CONSTRAINT `FK1_paper_midcheck` FOREIGN KEY (`TitleId`) REFERENCES `paper_title` (`PaperTitleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_midcheck
-- ----------------------------

-- ----------------------------
-- Table structure for `paper_permission`
-- ----------------------------
DROP TABLE IF EXISTS `paper_permission`;
CREATE TABLE `paper_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(255) NOT NULL COMMENT '网页链接',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '权限描述',
  `flag` int(1) NOT NULL DEFAULT '1' COMMENT '逻辑删除标记，1正常，0屏蔽',
  `state` int(11) DEFAULT '999' COMMENT '状态标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_permission
-- ----------------------------
INSERT INTO `paper_permission` VALUES ('1', 'admin/studentManage.jsp', '学生管理', '3', '999');
INSERT INTO `paper_permission` VALUES ('2', 'secretary/teacherManage.jsp', '教师管理', '3', '999');
INSERT INTO `paper_permission` VALUES ('3', 'admin/roleManage.jsp', '角色管理', '3', '999');
INSERT INTO `paper_permission` VALUES ('4', 'admin/permissionManage.jsp', '权限管理', '3', '999');
INSERT INTO `paper_permission` VALUES ('5', 'leader/reviewPaperTopics.jsp', '题目审批', '3', '2');
INSERT INTO `paper_permission` VALUES ('6', 'leader/paperStatistics.jsp', '论文统计', '3', '999');
INSERT INTO `paper_permission` VALUES ('7', 'leader/replycouncilList.jsp', '答辩委员会审核', '3', '999');
INSERT INTO `paper_permission` VALUES ('8', 'secretary/allTopics.jsp', '选题汇总', '3', '999');
INSERT INTO `paper_permission` VALUES ('9', 'secretary/exportAchievement.jsp', '导出成绩', '3', '999');
INSERT INTO `paper_permission` VALUES ('10', 'secretary/uploadPaperTemplate.jsp', '上传论文模板', '3', '999');
INSERT INTO `paper_permission` VALUES ('11', 'secretary/councilList.jsp', '答辩委员会列表', '3', '999');
INSERT INTO `paper_permission` VALUES ('12', 'teacher/myTopics.jsp', '我的课题', '3', '999');
INSERT INTO `paper_permission` VALUES ('13', 'teacher/writingTask.jsp', '写任务书', '3', '6');
INSERT INTO `paper_permission` VALUES ('14', 'teacher/paperGuide.jsp', '论文指导', '3', '9');
INSERT INTO `paper_permission` VALUES ('15', 'teacher/interimCheckUp.jsp', '中期检查', '3', '999');
INSERT INTO `paper_permission` VALUES ('17', 'replyTeam/excellentPaperRecommend.jsp', '论文推荐', '3', '999');
INSERT INTO `paper_permission` VALUES ('18', 'student/topicSelection.jsp', '论文选题', '3', '3');
INSERT INTO `paper_permission` VALUES ('19', 'student/thesisProposal.jsp', '开题报告', '3', '7');
INSERT INTO `paper_permission` VALUES ('20', 'student/paperGuide.jsp', '论文指导', '3', '9');
INSERT INTO `paper_permission` VALUES ('21', 'student/replyApply.jsp', '答辩申请', '3', '999');
INSERT INTO `paper_permission` VALUES ('22', 'teacher/teacherReplyRecord.jsp', '答辩记录', '3', '999');
INSERT INTO `paper_permission` VALUES ('23', 'student/score.jsp', '查看成绩', '3', '999');
INSERT INTO `paper_permission` VALUES ('24', 'student/declarePaperTopics.jsp', '自拟题目', '3', '999');
INSERT INTO `paper_permission` VALUES ('25', 'amendingPersonalDetails.jsp', '修改个人资料', '3', '999');
INSERT INTO `paper_permission` VALUES ('26', 'amendingPassword.jsp', '修改密码', '3', '999');
INSERT INTO `paper_permission` VALUES ('27', 'secretary/topicSelectionApproval.jsp', '选题审批', '3', '5');
INSERT INTO `paper_permission` VALUES ('28', 'student/myTask.jsp', '任务书', '3', '6');
INSERT INTO `paper_permission` VALUES ('29', 'student/interimCheckUp.jsp', '中期检查', '3', '999');
INSERT INTO `paper_permission` VALUES ('30', 'student/downloadPaperTemplate.jsp', '下载论文模版', '3', '999');
INSERT INTO `paper_permission` VALUES ('31', 'teacher/replyApply.jsp', '答辩审批', '3', '999');
INSERT INTO `paper_permission` VALUES ('32', 'teacher/thesisProposal.jsp', '开题报告', '3', '8');
INSERT INTO `paper_permission` VALUES ('35', 'replyTeam/replyRecord.jsp', '答辩记录', '3', '999');
INSERT INTO `paper_permission` VALUES ('38', 'instructor/studentManage.jsp', '审核学生信息', '3', '1');
INSERT INTO `paper_permission` VALUES ('40', 'teacher/enteringScore.jsp', '录入成绩', '1', null);
INSERT INTO `paper_permission` VALUES ('41', 'replyTeam/teamScore1.jsp', '录入成绩（答辩小组）', '1', null);
INSERT INTO `paper_permission` VALUES ('42', 'leader/graphics.jsp', '成绩统计图', '1', null);
INSERT INTO `paper_permission` VALUES ('43', 'leader/leaderScore.jsp', '成绩单评审', '1', null);
INSERT INTO `paper_permission` VALUES ('46', 'replyTeam/teamRecommend.jsp', '优秀论文推荐', '1', null);
INSERT INTO `paper_permission` VALUES ('47', 'secretary/onfile.jsp', '归档论文', '1', null);
INSERT INTO `paper_permission` VALUES ('48', 'secretary/reReply.jsp', '答辩失败管理', '1', null);

-- ----------------------------
-- Table structure for `paper_plan`
-- ----------------------------
DROP TABLE IF EXISTS `paper_plan`;
CREATE TABLE `paper_plan` (
  `PlanId` int(11) NOT NULL AUTO_INCREMENT,
  `PlanWritingTaskId` int(11) NOT NULL,
  `PlanTask` varchar(1024) NOT NULL,
  `PlanStartTime` varchar(24) NOT NULL,
  `PlanEndTime` varchar(24) NOT NULL,
  `PlanFlag` int(1) DEFAULT '1',
  `Plancol` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`PlanId`),
  KEY `FK_paper_plan_idx` (`PlanWritingTaskId`),
  CONSTRAINT `FK_paper_plan` FOREIGN KEY (`PlanWritingTaskId`) REFERENCES `paper_writingtask` (`WritingTaskID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_plan
-- ----------------------------

-- ----------------------------
-- Table structure for `paper_recommend`
-- ----------------------------
DROP TABLE IF EXISTS `paper_recommend`;
CREATE TABLE `paper_recommend` (
  `RecommendId` int(11) NOT NULL AUTO_INCREMENT,
  `RecommendTitleId` int(11) DEFAULT NULL,
  `RecommendReplyTeamComment` varchar(1024) DEFAULT NULL,
  `RecommendReplyTeamDate` varchar(24) DEFAULT NULL,
  `RecommendFacultyComment` varchar(1024) DEFAULT NULL,
  `RecommendFacultyDate` varchar(24) DEFAULT NULL,
  `RecommendUnitsComment` varchar(1024) DEFAULT NULL,
  `RecommendUnitsDate` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`RecommendId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_recommend
-- ----------------------------

-- ----------------------------
-- Table structure for `paper_record`
-- ----------------------------
DROP TABLE IF EXISTS `paper_record`;
CREATE TABLE `paper_record` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `PaperTitleId` int(11) DEFAULT NULL,
  `councilman` int(11) DEFAULT NULL,
  `ReplyDate` varchar(24) DEFAULT NULL,
  `Site` varchar(64) DEFAULT NULL,
  `Record` varchar(1024) DEFAULT NULL,
  `TeamAdvice` varchar(1024) DEFAULT NULL,
  `TeamDate` varchar(24) DEFAULT NULL,
  `Score` double(8,1) DEFAULT '0.0',
  `CouncilAdvice` varchar(1024) DEFAULT NULL,
  `CounDate` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_record
-- ----------------------------

-- ----------------------------
-- Table structure for `paper_role`
-- ----------------------------
DROP TABLE IF EXISTS `paper_role`;
CREATE TABLE `paper_role` (
  `RoleId` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(32) NOT NULL COMMENT '角色名称',
  `description` varchar(150) DEFAULT NULL COMMENT '描述',
  `flag` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_role
-- ----------------------------
INSERT INTO `paper_role` VALUES ('1', '管理员', '系统管理', '3');
INSERT INTO `paper_role` VALUES ('2', '院长', '教学管理', '3');
INSERT INTO `paper_role` VALUES ('3', '答辩组', '答辩管理', '3');
INSERT INTO `paper_role` VALUES ('4', '教学秘书', '教学秘书', '3');
INSERT INTO `paper_role` VALUES ('5', '指导教师', '论文管理', '3');
INSERT INTO `paper_role` VALUES ('6', '辅导员', '学生管理', '3');
INSERT INTO `paper_role` VALUES ('7', '学生', '论文申报', '3');

-- ----------------------------
-- Table structure for `paper_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `paper_role_permission`;
CREATE TABLE `paper_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `porder` int(4) DEFAULT NULL COMMENT '排序',
  `flag` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_paper_role_permission` (`role_id`),
  KEY `FK2_paper_role_permission` (`permission_id`),
  CONSTRAINT `FK2_paper_role_permission` FOREIGN KEY (`permission_id`) REFERENCES `paper_permission` (`id`),
  CONSTRAINT `FK_paper_role_permission` FOREIGN KEY (`role_id`) REFERENCES `paper_role` (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_role_permission
-- ----------------------------
INSERT INTO `paper_role_permission` VALUES ('11', '1', '1', '1', '3');
INSERT INTO `paper_role_permission` VALUES ('12', '2', '1', '2', '3');
INSERT INTO `paper_role_permission` VALUES ('13', '3', '1', '3', '3');
INSERT INTO `paper_role_permission` VALUES ('14', '4', '1', '4', '3');
INSERT INTO `paper_role_permission` VALUES ('15', '5', '2', '2', '3');
INSERT INTO `paper_role_permission` VALUES ('16', '6', '2', '1', '3');
INSERT INTO `paper_role_permission` VALUES ('17', '7', '2', '3', '3');
INSERT INTO `paper_role_permission` VALUES ('18', '8', '4', '3', '3');
INSERT INTO `paper_role_permission` VALUES ('20', '10', '4', '6', '3');
INSERT INTO `paper_role_permission` VALUES ('21', '11', '4', '5', '3');
INSERT INTO `paper_role_permission` VALUES ('22', '12', '5', '1', '3');
INSERT INTO `paper_role_permission` VALUES ('23', '13', '5', '2', '3');
INSERT INTO `paper_role_permission` VALUES ('24', '14', '5', '4', '3');
INSERT INTO `paper_role_permission` VALUES ('25', '15', '5', '5', '3');
INSERT INTO `paper_role_permission` VALUES ('28', '18', '7', '1', '3');
INSERT INTO `paper_role_permission` VALUES ('29', '19', '7', '3', '3');
INSERT INTO `paper_role_permission` VALUES ('30', '20', '7', '4', '3');
INSERT INTO `paper_role_permission` VALUES ('31', '21', '7', '6', '3');
INSERT INTO `paper_role_permission` VALUES ('33', '23', '7', '8', '3');
INSERT INTO `paper_role_permission` VALUES ('34', '24', '7', '9', '3');
INSERT INTO `paper_role_permission` VALUES ('52', '2', '4', '1', '3');
INSERT INTO `paper_role_permission` VALUES ('53', '27', '4', '2', '3');
INSERT INTO `paper_role_permission` VALUES ('54', '28', '7', '2', '3');
INSERT INTO `paper_role_permission` VALUES ('55', '29', '7', '5', '3');
INSERT INTO `paper_role_permission` VALUES ('56', '30', '7', '10', '3');
INSERT INTO `paper_role_permission` VALUES ('57', '31', '5', '6', '3');
INSERT INTO `paper_role_permission` VALUES ('59', '32', '5', '3', '3');
INSERT INTO `paper_role_permission` VALUES ('60', '35', '3', '1', '3');
INSERT INTO `paper_role_permission` VALUES ('138', '38', '6', null, '1');
INSERT INTO `paper_role_permission` VALUES ('139', '40', '5', '7', '1');
INSERT INTO `paper_role_permission` VALUES ('140', '41', '3', null, '1');
INSERT INTO `paper_role_permission` VALUES ('146', '46', '3', null, '1');
INSERT INTO `paper_role_permission` VALUES ('151', '47', '4', null, '1');
INSERT INTO `paper_role_permission` VALUES ('152', '43', '2', null, '1');
INSERT INTO `paper_role_permission` VALUES ('153', '42', '2', null, '1');
INSERT INTO `paper_role_permission` VALUES ('154', '48', '4', null, '1');
INSERT INTO `paper_role_permission` VALUES ('155', '47', '2', null, '1');

-- ----------------------------
-- Table structure for `paper_score`
-- ----------------------------
DROP TABLE IF EXISTS `paper_score`;
CREATE TABLE `paper_score` (
  `ScoreID` int(11) NOT NULL AUTO_INCREMENT COMMENT '成绩单物理主键',
  `PaperTitleId` int(11) DEFAULT NULL COMMENT '课题id',
  `Tcomment` varchar(1024) DEFAULT NULL COMMENT '老师意见',
  `Tscore` float DEFAULT NULL COMMENT '老师给分',
  `TDate` varchar(24) DEFAULT NULL,
  `CommitteeComment` varchar(1024) DEFAULT NULL COMMENT '答辩小组意见',
  `CommitteeScore` float DEFAULT NULL COMMENT '答辩小组给分',
  `CommitteeDate` varchar(24) DEFAULT NULL,
  `CouncilComment` varchar(1024) DEFAULT NULL COMMENT '答辩委员会意见',
  `TotalScore` float DEFAULT NULL COMMENT '总成绩',
  `CouncilDate` varchar(24) DEFAULT NULL,
  `Remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `flag` int(11) DEFAULT '1',
  PRIMARY KEY (`ScoreID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_score
-- ----------------------------

-- ----------------------------
-- Table structure for `paper_student`
-- ----------------------------
DROP TABLE IF EXISTS `paper_student`;
CREATE TABLE `paper_student` (
  `studentId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `studentName` varchar(32) NOT NULL,
  `studentSex` varchar(3) DEFAULT NULL,
  `studentFaculty` varchar(32) DEFAULT NULL,
  `studentMajor` varchar(32) DEFAULT NULL,
  `studentDirection` varchar(32) DEFAULT NULL,
  `studentGrade` varchar(32) DEFAULT NULL,
  `studentAge` int(3) DEFAULT NULL,
  `studentPhone` varchar(16) DEFAULT NULL,
  `studentNumber` varchar(32) NOT NULL,
  `flag` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_student
-- ----------------------------
INSERT INTO `paper_student` VALUES ('1', '2', '荆志宝', '男', '', '', '', '', null, '', '201204160110', '1');

-- ----------------------------
-- Table structure for `paper_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `paper_teacher`;
CREATE TABLE `paper_teacher` (
  `teacherId` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `teacherName` varchar(32) NOT NULL,
  `teacherSex` varchar(3) DEFAULT NULL,
  `teacherUnits` varchar(32) DEFAULT NULL,
  `teacherDirection` varchar(32) DEFAULT NULL,
  `teacherEducation` varchar(32) DEFAULT NULL,
  `teacherJobTitle` varchar(32) DEFAULT NULL,
  `teacherPhone` varchar(16) DEFAULT NULL,
  `teacherNumber` varchar(32) NOT NULL,
  `teacherAge` int(3) DEFAULT NULL,
  `teacherFaculty` varchar(32) DEFAULT NULL,
  `teacherMajor` varchar(32) DEFAULT NULL,
  `flag` int(1) NOT NULL DEFAULT '1',
  `CouncilId` int(8) DEFAULT NULL,
  `ChairId` int(8) DEFAULT '0',
  `paper_teachercol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`teacherId`),
  KEY `index2` (`teacherName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_teacher
-- ----------------------------
INSERT INTO `paper_teacher` VALUES ('1', '1', 'admin', '男', '大庆师范学院计算机系', '计算机科学与技术', '硕士', '教授', '13321212121', 'admin', '24', null, '软件技术', '3', null, '0', null);

-- ----------------------------
-- Table structure for `paper_thesisproposal`
-- ----------------------------
DROP TABLE IF EXISTS `paper_thesisproposal`;
CREATE TABLE `paper_thesisproposal` (
  `ThesisProposalReportId` int(11) NOT NULL AUTO_INCREMENT,
  `TitleId` int(11) NOT NULL,
  `ThesisProposalBackground` varchar(1024) DEFAULT NULL,
  `ThesisProposalResearchContent` varchar(1024) DEFAULT NULL,
  `ThesisProposalResearchMethod` varchar(1024) DEFAULT NULL,
  `ThesisProposalResearchSchedule` varchar(1024) DEFAULT NULL,
  `ThesisProposalReference` varchar(1024) DEFAULT NULL,
  `ThesisProposalTeacherSug` varchar(512) DEFAULT NULL,
  `teacherSugDate` varchar(24) DEFAULT NULL,
  `ThesisProposalDeanSug` varchar(512) DEFAULT NULL,
  `deanSugDate` varchar(24) DEFAULT NULL,
  `ThesisProposalRemark` varchar(1024) DEFAULT NULL,
  `ThesisProposalFlag` int(1) DEFAULT '1',
  PRIMARY KEY (`ThesisProposalReportId`),
  KEY `FK_paper_report` (`TitleId`),
  CONSTRAINT `FK_paper_report` FOREIGN KEY (`TitleId`) REFERENCES `paper_title` (`PaperTitleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_thesisproposal
-- ----------------------------

-- ----------------------------
-- Table structure for `paper_title`
-- ----------------------------
DROP TABLE IF EXISTS `paper_title`;
CREATE TABLE `paper_title` (
  `PaperTitleId` int(8) NOT NULL AUTO_INCREMENT COMMENT 'title主键',
  `StudentId` int(8) DEFAULT NULL COMMENT '学生id',
  `TeacherId` int(8) NOT NULL COMMENT '老师id',
  `PaperTitleName` varchar(56) NOT NULL COMMENT '题目名称',
  `PaperTitleKeywords` varchar(64) NOT NULL COMMENT '关键字',
  `PaperTitletype` varchar(1) DEFAULT NULL COMMENT '题目类型',
  `PaperTitleSource` varchar(1) DEFAULT NULL COMMENT '题目来源',
  `PaperTitlePlatform` varchar(56) DEFAULT NULL,
  `PaperTitleLimitMajor` varchar(45) NOT NULL,
  `PaperTitleIntroduce` varchar(1024) DEFAULT NULL,
  `PaperTitleReportDate` varchar(24) DEFAULT NULL,
  `PaperTitleDeanSug` varchar(1024) DEFAULT NULL,
  `PaperTitleApproveDate` varchar(24) DEFAULT NULL,
  `PaperTitleState` int(1) NOT NULL,
  `PaperTitleRemark` varchar(1024) DEFAULT NULL,
  `PaperTitleFlag` int(1) DEFAULT '1',
  `PaperTitlecol` varchar(45) DEFAULT NULL,
  `PaperTitlecol1` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PaperTitleId`),
  KEY `s_idx` (`PaperTitleState`),
  KEY `FK_paper_teacher_idx` (`StudentId`),
  KEY `FK_paper_title3_idx` (`TeacherId`),
  CONSTRAINT `FK_paper_title1` FOREIGN KEY (`PaperTitleState`) REFERENCES `paper_title_state` (`paperTitleStateId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_paper_title2` FOREIGN KEY (`StudentId`) REFERENCES `paper_student` (`studentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_paper_title3` FOREIGN KEY (`TeacherId`) REFERENCES `paper_teacher` (`teacherId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_title
-- ----------------------------

-- ----------------------------
-- Table structure for `paper_title_state`
-- ----------------------------
DROP TABLE IF EXISTS `paper_title_state`;
CREATE TABLE `paper_title_state` (
  `paperTitleStateId` int(11) NOT NULL AUTO_INCREMENT,
  `paperTitleState` int(1) NOT NULL,
  `paperTitleStateDescription` varchar(32) NOT NULL,
  PRIMARY KEY (`paperTitleStateId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_title_state
-- ----------------------------
INSERT INTO `paper_title_state` VALUES ('1', '0', '学生自拟题目,等待教师申报');
INSERT INTO `paper_title_state` VALUES ('2', '1', '申报中');
INSERT INTO `paper_title_state` VALUES ('3', '2', '申报通过');
INSERT INTO `paper_title_state` VALUES ('4', '3', '申报失败');
INSERT INTO `paper_title_state` VALUES ('5', '4', '学生选题申请中');
INSERT INTO `paper_title_state` VALUES ('6', '5', '学生已选题,等待下任务书');
INSERT INTO `paper_title_state` VALUES ('7', '6', '已下发任务书,等待学生填写开题报告');
INSERT INTO `paper_title_state` VALUES ('8', '7', '学生已经填写开题报告，等待批阅');
INSERT INTO `paper_title_state` VALUES ('9', '8', '开题报告批阅完成,开始论文指导');
INSERT INTO `paper_title_state` VALUES ('10', '9', '第一次指导');
INSERT INTO `paper_title_state` VALUES ('11', '10', '第二次指导');
INSERT INTO `paper_title_state` VALUES ('12', '11', '第三次指导');
INSERT INTO `paper_title_state` VALUES ('13', '12', '第四次指导');
INSERT INTO `paper_title_state` VALUES ('14', '13', '第五次指导');
INSERT INTO `paper_title_state` VALUES ('15', '14', '第六次指导');
INSERT INTO `paper_title_state` VALUES ('16', '15', '保留状态');
INSERT INTO `paper_title_state` VALUES ('17', '16', '论文指导结束,准备申请答辩');
INSERT INTO `paper_title_state` VALUES ('18', '17', '申请答辩通过,准备开始答辩');
INSERT INTO `paper_title_state` VALUES ('19', '-1', '课题草稿');

-- ----------------------------
-- Table structure for `paper_user`
-- ----------------------------
DROP TABLE IF EXISTS `paper_user`;
CREATE TABLE `paper_user` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(32) NOT NULL DEFAULT '',
  `PassWord` varchar(32) NOT NULL DEFAULT '',
  `identity` varchar(32) NOT NULL DEFAULT '不明人士',
  `flag` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_user
-- ----------------------------
INSERT INTO `paper_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', '3');
INSERT INTO `paper_user` VALUES ('2', '201204160110', '078e4fc93a1c8f35afe67f322f1b4e9b', '同学', '1');

-- ----------------------------
-- Table structure for `paper_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `paper_user_role`;
CREATE TABLE `paper_user_role` (
  `UserRoleId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `RoleId` int(11) NOT NULL,
  `flag` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`UserRoleId`),
  KEY `FK_paper_user_role` (`RoleId`),
  KEY `FK2_paper_user_role` (`UserId`),
  CONSTRAINT `FK2_paper_user_role` FOREIGN KEY (`UserId`) REFERENCES `paper_user` (`UserId`),
  CONSTRAINT `FK_paper_user_role` FOREIGN KEY (`RoleId`) REFERENCES `paper_role` (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_user_role
-- ----------------------------
INSERT INTO `paper_user_role` VALUES ('1', '1', '1', '3');
INSERT INTO `paper_user_role` VALUES ('2', '1', '2', '1');
INSERT INTO `paper_user_role` VALUES ('3', '1', '3', '1');
INSERT INTO `paper_user_role` VALUES ('4', '1', '4', '1');
INSERT INTO `paper_user_role` VALUES ('6', '1', '6', '1');
INSERT INTO `paper_user_role` VALUES ('7', '2', '7', '1');

-- ----------------------------
-- Table structure for `paper_writingtask`
-- ----------------------------
DROP TABLE IF EXISTS `paper_writingtask`;
CREATE TABLE `paper_writingtask` (
  `WritingTaskID` int(11) NOT NULL AUTO_INCREMENT,
  `WritingTaskTitleId` int(11) NOT NULL,
  `WritingTaskStartTime` varchar(24) DEFAULT NULL,
  `WritingTaskEndTime` varchar(24) DEFAULT NULL,
  `WritingTaskContent` varchar(1024) DEFAULT NULL,
  `WritingTaskReference` varchar(1024) DEFAULT NULL,
  `WritingTaskFlag` int(1) DEFAULT '1',
  `WritingTaskcol` varchar(1024) DEFAULT NULL,
  `WritingTaskcol1` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`WritingTaskID`),
  KEY `FK_paper_writingtask` (`WritingTaskTitleId`),
  CONSTRAINT `FK_paper_writingtask` FOREIGN KEY (`WritingTaskTitleId`) REFERENCES `paper_title` (`PaperTitleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_writingtask
-- ----------------------------

-- ----------------------------
-- Table structure for `public_messagetable`
-- ----------------------------
DROP TABLE IF EXISTS `public_messagetable`;
CREATE TABLE `public_messagetable` (
  `MsgId` int(11) NOT NULL AUTO_INCREMENT,
  `Sender` varchar(32) DEFAULT NULL,
  `Receiver` varchar(32) DEFAULT NULL,
  `SendDate` varchar(32) DEFAULT NULL,
  `MsgTitle` varchar(128) DEFAULT NULL,
  `MsgText` varchar(512) DEFAULT NULL,
  `Flag` int(8) DEFAULT NULL,
  `Remark` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`MsgId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of public_messagetable
-- ----------------------------

-- ----------------------------
-- View structure for `login_view`
-- ----------------------------
DROP VIEW IF EXISTS `login_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `login_view` AS select `paper_user`.`UserId` AS `UserId`,`paper_user`.`UserName` AS `Username`,`paper_user`.`PassWord` AS `password`,`paper_user`.`identity` AS `identity`,`paper_user`.`flag` AS `flag`,`paper_student`.`studentId` AS `studentid`,`paper_student`.`studentName` AS `studentname`,`paper_student`.`studentAge` AS `studentAge`,`paper_student`.`studentPhone` AS `studentPhone`,`paper_student`.`studentSex` AS `studentsex`,`paper_student`.`studentFaculty` AS `studentFaculty`,`paper_student`.`studentMajor` AS `studentMajor`,`paper_student`.`studentDirection` AS `studentDirection`,`paper_student`.`studentGrade` AS `studentGrade`,`paper_student`.`studentNumber` AS `studentNumber`,`paper_teacher`.`teacherId` AS `teacherid`,`paper_teacher`.`teacherAge` AS `teacherAge`,`paper_teacher`.`teacherPhone` AS `teacherPhone`,`paper_teacher`.`teacherName` AS `teacherName`,`paper_teacher`.`teacherSex` AS `teacherSex`,`paper_teacher`.`teacherUnits` AS `teacherUnits`,`paper_teacher`.`teacherFaculty` AS `teacherFaculty`,`paper_teacher`.`teacherDirection` AS `teacherDirection`,`paper_teacher`.`teacherEducation` AS `teacherEducation`,`paper_teacher`.`teacherJobTitle` AS `teacherJobTitle`,`paper_teacher`.`teacherNumber` AS `teacherNumber`,`paper_teacher`.`teacherMajor` AS `teacherMajor`,`paper_role`.`RoleId` AS `roleid`,`paper_role`.`RoleName` AS `RoleName`,`paper_role`.`description` AS `description`,`paper_role_permission`.`porder` AS `porder`,`paper_permission`.`id` AS `id`,`paper_permission`.`permission` AS `permission`,`paper_permission`.`description` AS `pdescription` from ((((((`paper_user` left join `paper_student` on((`paper_user`.`UserId` = `paper_student`.`userId`))) left join `paper_teacher` on((`paper_user`.`UserId` = `paper_teacher`.`userID`))) left join `paper_user_role` on((`paper_user`.`UserId` = `paper_user_role`.`UserId`))) left join `paper_role` on((`paper_user_role`.`RoleId` = `paper_role`.`RoleId`))) left join `paper_role_permission` on((`paper_user_role`.`RoleId` = `paper_role_permission`.`role_id`))) left join `paper_permission` on((`paper_role_permission`.`permission_id` = `paper_permission`.`id`))) ;
