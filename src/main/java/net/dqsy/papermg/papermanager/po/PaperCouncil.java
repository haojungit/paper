package net.dqsy.papermg.papermanager.po;

import java.util.Date;
import java.util.Set;

public class PaperCouncil {
    private Integer councilId;
    private String department;
    private String major;
    private String grade;
    private String deanSug;
    private Date deanDate;
    private String DeanOfficeSug;
    private Date DeanOfficeDate;
    private Set councilman;
    private Integer state;

    public Integer getCouncilId() {
        return this.councilId;
    }

    public void setCouncilId(Integer councilId) {
        this.councilId = councilId;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Set getCouncilman() {
        return this.councilman;
    }

    public void setCouncilman(Set councilman) {
        this.councilman = councilman;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDeanSug() {
        return this.deanSug;
    }

    public void setDeanSug(String deanSug) {
        this.deanSug = deanSug;
    }

    public Date getDeanDate() {
        return this.deanDate;
    }

    public void setDeanDate(Date deanDate) {
        this.deanDate = deanDate;
    }

    public String getDeanOfficeSug() {
        return this.DeanOfficeSug;
    }

    public void setDeanOfficeSug(String deanOfficeSug) {
        this.DeanOfficeSug = deanOfficeSug;
    }

    public Date getDeanOfficeDate() {
        return this.DeanOfficeDate;
    }

    public void setDeanOfficeDate(Date deanOfficeDate) {
        this.DeanOfficeDate = deanOfficeDate;
    }
}