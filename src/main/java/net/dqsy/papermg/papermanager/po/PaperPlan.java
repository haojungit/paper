package net.dqsy.papermg.papermanager.po;

import java.io.Serializable;
import java.util.Date;

public class PaperPlan
  implements Serializable
{
  private Integer planId;
  private PaperWritingTask paperWritingTask;
  private String planTask;
  private Date planStartTime;
  private Date planEndTime;
  private Integer planFlag;
  private String plancol;

  public PaperPlan()
  {
  }

  public PaperPlan(String planTask, Date planStartTime, Date planEndTime)
  {
    this.planTask = planTask;
    this.planStartTime = planStartTime;
    this.planEndTime = planEndTime;
  }

  public Date getPlanStartTime() {
    return this.planStartTime;
  }

  public void setPlanStartTime(Date planStartTime) {
    this.planStartTime = planStartTime;
  }

  public Integer getPlanId() {
    return this.planId;
  }

  public void setPlanId(Integer planId) {
    this.planId = planId;
  }

  public PaperWritingTask getPaperWritingTask() {
    return this.paperWritingTask;
  }

  public void setPaperWritingTask(PaperWritingTask paperWritingTask) {
    this.paperWritingTask = paperWritingTask;
  }

  public String getPlanTask() {
    return this.planTask;
  }

  public void setPlanTask(String planTask) {
    this.planTask = planTask;
  }

  public Date getPlanEndTime() {
    return this.planEndTime;
  }

  public void setPlanEndTime(Date planEndTime) {
    this.planEndTime = planEndTime;
  }

  public Integer getPlanFlag() {
    return this.planFlag;
  }

  public void setPlanFlag(Integer planFlag) {
    this.planFlag = planFlag;
  }

  public String getPlancol() {
    return this.plancol;
  }

  public void setPlancol(String plancol) {
    this.plancol = plancol;
  }
}