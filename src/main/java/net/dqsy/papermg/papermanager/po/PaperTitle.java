package net.dqsy.papermg.papermanager.po;

import net.dqsy.papermg.sysmanager.po.PaperStudent;
import net.dqsy.papermg.sysmanager.po.PaperTeacher;

import java.io.Serializable;
import java.util.Date;

public class PaperTitle
        implements Serializable
{
    private int paperTitleId;
    private PaperStudent paperStudent;
    private PaperTeacher paperTeacher;
    private String paperTitleName;
    private String paperTitleKeywords;
    private String paperTitletype;
    private String paperTitleSource;
    private String paperTitlePlatform;
    private String paperTitleLimitMajor;
    private String paperTitleIntroduce;
    private Date paperTitleReportDate;
    private String paperTitleDeanSug;
    private Date paperTitleApproveDate;
    private PaperTitleState paperTitleState;
    private String paperTitleRemark;
    private int paperTitleFlag;
    private String paperTitlecol;
    private String paperTitlecol1;
    private PaperRecord paperRecord;
    private PaperScore paperScore;

    public PaperTitle() {
    }

    public PaperTitle(String paperTitleName, String paperTitleKeywords, String paperTitletype, String paperTitleSource, String paperTitlePlatform, String paperTitleLimitMajor, String paperTitleIntroduce, Date paperTitleReportDate, PaperTitleState paperTitleState) {
        this.paperTitleName = paperTitleName;
        this.paperTitleKeywords = paperTitleKeywords;
        this.paperTitletype = paperTitletype;
        this.paperTitleSource = paperTitleSource;
        this.paperTitlePlatform = paperTitlePlatform;
        this.paperTitleLimitMajor = paperTitleLimitMajor;
        this.paperTitleIntroduce = paperTitleIntroduce;
        this.paperTitleReportDate = paperTitleReportDate;
        this.paperTitleState = paperTitleState;
    }

    public int getPaperTitleId() {
        return this.paperTitleId;
    }

    public void setPaperTitleId(int paperTitleId) {
        this.paperTitleId = paperTitleId;
    }

    public PaperStudent getPaperStudent() {
        return this.paperStudent;
    }

    public void setPaperStudent(PaperStudent paperStudent) {
        this.paperStudent = paperStudent;
    }

    public PaperTeacher getPaperTeacher() {
        return this.paperTeacher;
    }

    public void setPaperTeacher(PaperTeacher paperTeacher) {
        this.paperTeacher = paperTeacher;
    }

    public String getPaperTitleName() {
        return this.paperTitleName;
    }

    public void setPaperTitleName(String paperTitleName) {
        this.paperTitleName = paperTitleName;
    }

    public String getPaperTitleKeywords() {
        return this.paperTitleKeywords;
    }

    public void setPaperTitleKeywords(String paperTitleKeywords) {
        this.paperTitleKeywords = paperTitleKeywords;
    }

    public String getPaperTitletype() {
        return this.paperTitletype;
    }

    public void setPaperTitletype(String paperTitletype) {
        this.paperTitletype = paperTitletype;
    }

    public String getPaperTitleSource() {
        return this.paperTitleSource;
    }

    public void setPaperTitleSource(String paperTitleSource) {
        this.paperTitleSource = paperTitleSource;
    }

    public String getPaperTitlePlatform() {
        return this.paperTitlePlatform;
    }

    public void setPaperTitlePlatform(String paperTitlePlatform) {
        this.paperTitlePlatform = paperTitlePlatform;
    }

    public String getPaperTitleLimitMajor() {
        return this.paperTitleLimitMajor;
    }

    public void setPaperTitleLimitMajor(String paperTitleLimitMajor) {
        this.paperTitleLimitMajor = paperTitleLimitMajor;
    }

    public String getPaperTitleIntroduce() {
        return this.paperTitleIntroduce;
    }

    public void setPaperTitleIntroduce(String paperTitleIntroduce) {
        this.paperTitleIntroduce = paperTitleIntroduce;
    }

    public String getPaperTitleDeanSug() {
        return this.paperTitleDeanSug;
    }

    public void setPaperTitleDeanSug(String paperTitleDeanSug) {
        this.paperTitleDeanSug = paperTitleDeanSug;
    }

    public String getPaperTitleRemark() {
        return this.paperTitleRemark;
    }

    public void setPaperTitleRemark(String paperTitleRemark) {
        this.paperTitleRemark = paperTitleRemark;
    }

    public String getPaperTitlecol() {
        return this.paperTitlecol;
    }

    public void setPaperTitlecol(String paperTitlecol) {
        this.paperTitlecol = paperTitlecol;
    }

    public String getPaperTitlecol1() {
        return this.paperTitlecol1;
    }

    public void setPaperTitlecol1(String paperTitlecol1) {
        this.paperTitlecol1 = paperTitlecol1;
    }

    public Date getPaperTitleReportDate() {
        return this.paperTitleReportDate;
    }

    public void setPaperTitleReportDate(Date paperTitleReportDate) {
        this.paperTitleReportDate = paperTitleReportDate;
    }

    public Date getPaperTitleApproveDate() {
        return this.paperTitleApproveDate;
    }

    public void setPaperTitleApproveDate(Date paperTitleApproveDate) {
        this.paperTitleApproveDate = paperTitleApproveDate;
    }

    public PaperTitleState getPaperTitleState() {
        return this.paperTitleState;
    }

    public void setPaperTitleState(PaperTitleState paperTitleState) {
        this.paperTitleState = paperTitleState;
    }

    public int getPaperTitleFlag() {
        return this.paperTitleFlag;
    }

    public void setPaperTitleFlag(int paperTitleFlag) {
        this.paperTitleFlag = paperTitleFlag;
    }

    public PaperRecord getPaperRecord() {
        return this.paperRecord;
    }

    public void setPaperRecord(PaperRecord paperRecord) {
        this.paperRecord = paperRecord;
    }

    public PaperScore getPaperScore() {
        return this.paperScore;
    }

    public void setPaperScore(PaperScore paperScore) {
        this.paperScore = paperScore;
    }
}