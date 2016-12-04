package net.dqsy.papermg.papermanager.po;

import java.io.Serializable;
import java.util.Date;

public class PaperMidcheck
        implements Serializable
{
    private Integer mcId;
    private PaperTitle paperTitle;
    private String Progress;
    private Date ProgressDate;
    private Date FinishDate;
    private String Tcomment;
    private Date TcommentDate;
    private Integer flag;

    public PaperMidcheck() {
    }

    public PaperMidcheck(String progress, Date finishDate) {
        this.Progress = progress;
        this.FinishDate = finishDate;
    }

    public PaperMidcheck(String progress, Date finishDate, String tcomment, Date tcommentDate) {
        this.Progress = progress;
        this.FinishDate = finishDate;
        this.Tcomment = tcomment;
        this.TcommentDate = tcommentDate;
    }

    public PaperMidcheck(Integer mcId, String progress, Date finishDate) {
        this.mcId = mcId;
        this.Progress = progress;
        this.FinishDate = finishDate;
    }

    public PaperMidcheck(Integer mcId, String progress, Date finishDate, Date progressDate, String tcomment, Date tcommentDate) {
        this.mcId = mcId;
        this.Progress = progress;
        this.FinishDate = finishDate;
        this.ProgressDate = progressDate;
        this.Tcomment = tcomment;
        this.TcommentDate = tcommentDate;
    }

    public Integer getMcId() {
        return this.mcId;
    }

    public void setMcId(Integer mcId) {
        this.mcId = mcId;
    }

    public PaperTitle getPaperTitle() {
        return this.paperTitle;
    }

    public void setPaperTitle(PaperTitle paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getProgress() {
        return this.Progress;
    }

    public void setProgress(String progress) {
        this.Progress = progress;
    }

    public Date getFinishDate() {
        return this.FinishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.FinishDate = finishDate;
    }

    public String getTcomment() {
        return this.Tcomment;
    }

    public void setTcomment(String tcomment) {
        this.Tcomment = tcomment;
    }

    public Date getTcommentDate() {
        return this.TcommentDate;
    }

    public void setTcommentDate(Date tcommentDate) {
        this.TcommentDate = tcommentDate;
    }

    public Integer getFlag() {
        return this.flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getProgressDate() {
        return this.ProgressDate;
    }

    public void setProgressDate(Date progressDate) {
        this.ProgressDate = progressDate;
    }
}