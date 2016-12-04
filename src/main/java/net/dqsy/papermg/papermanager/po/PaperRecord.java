package net.dqsy.papermg.papermanager.po;

public class PaperRecord {
    private int id;
    private PaperTitle paperTitle;
    private String replyDate;
    private String site;
    private String record;
    private String teamAdvice;
    private String councilAdvice;
    private String teamDate;
    private String counDate;
    private double score;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReplyDate() {
        return this.replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getSite() {
        return this.site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getRecord() {
        return this.record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public PaperTitle getPaperTitle() {
        return this.paperTitle;
    }

    public void setPaperTitle(PaperTitle paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getTeamAdvice() {
        return this.teamAdvice;
    }

    public void setTeamAdvice(String teamAdvice) {
        this.teamAdvice = teamAdvice;
    }

    public String getCouncilAdvice() {
        return this.councilAdvice;
    }

    public void setCouncilAdvice(String councilAdvice) {
        this.councilAdvice = councilAdvice;
    }

    public String getTeamDate() {
        return this.teamDate;
    }

    public void setTeamDate(String teamDate) {
        this.teamDate = teamDate;
    }

    public String getCounDate() {
        return this.counDate;
    }

    public void setCounDate(String counDate) {
        this.counDate = counDate;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}