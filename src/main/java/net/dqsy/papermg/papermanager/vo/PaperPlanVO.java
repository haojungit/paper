package net.dqsy.papermg.papermanager.vo;

import java.util.Date;

public class PaperPlanVO {
    private Integer planId;
    private String planTask;
    private Date planStartTime;
    private Date planEndTime;

    public PaperPlanVO(Integer planId, String planTask, Date planStartTime, Date planEndTime) {
        this.planId = planId;
        this.planTask = planTask;
        this.planStartTime = planStartTime;
        this.planEndTime = planEndTime;
    }

    public Integer getPlanId() {
        return this.planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanTask() {
        return this.planTask;
    }

    public void setPlanTask(String planTask) {
        this.planTask = planTask;
    }

    public Date getPlanStartTime() {
        return this.planStartTime;
    }

    public void setPlanStartTime(Date planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Date getPlanEndTime() {
        return this.planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }
}