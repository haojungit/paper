package net.dqsy.papermg.papermanager.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PaperWritingTask
  implements Serializable
{
  private Integer writingTaskID;
  private PaperTitle paperTitle;
  private Date writingTaskStartTime;
  private Date writingTaskEndTime;
  private String writingTaskContent;
  private String writingTaskReference;
  private Integer writingTaskFlag;
  private String writingTaskcol;
  private String writingTaskcol1;
  private Set paperPlans = new HashSet(0);

  public PaperWritingTask()
  {
  }

  public PaperWritingTask(Date writingTaskStartTime, Date writingTaskEndTime, String writingTaskContent, String writingTaskReference)
  {
    this.writingTaskStartTime = writingTaskStartTime;
    this.writingTaskEndTime = writingTaskEndTime;
    this.writingTaskContent = writingTaskContent;
    this.writingTaskReference = writingTaskReference;
  }

  public Integer getWritingTaskID() {
    return this.writingTaskID;
  }

  public void setWritingTaskID(Integer writingTaskID) {
    this.writingTaskID = writingTaskID;
  }

  public PaperTitle getPaperTitle() {
    return this.paperTitle;
  }

  public void setPaperTitle(PaperTitle paperTitle) {
    this.paperTitle = paperTitle;
  }

  public Date getWritingTaskStartTime() {
    return this.writingTaskStartTime;
  }

  public void setWritingTaskStartTime(Date writingTaskStartTime) {
    this.writingTaskStartTime = writingTaskStartTime;
  }

  public Date getWritingTaskEndTime() {
    return this.writingTaskEndTime;
  }

  public void setWritingTaskEndTime(Date writingTaskEndTime) {
    this.writingTaskEndTime = writingTaskEndTime;
  }

  public String getWritingTaskContent() {
    return this.writingTaskContent;
  }

  public void setWritingTaskContent(String writingTaskContent) {
    this.writingTaskContent = writingTaskContent;
  }

  public String getWritingTaskReference() {
    return this.writingTaskReference;
  }

  public void setWritingTaskReference(String writingTaskReference) {
    this.writingTaskReference = writingTaskReference;
  }

  public Integer getWritingTaskFlag() {
    return this.writingTaskFlag;
  }

  public void setWritingTaskFlag(Integer writingTaskFlag) {
    this.writingTaskFlag = writingTaskFlag;
  }

  public String getWritingTaskcol() {
    return this.writingTaskcol;
  }

  public void setWritingTaskcol(String writingTaskcol) {
    this.writingTaskcol = writingTaskcol;
  }

  public String getWritingTaskcol1() {
    return this.writingTaskcol1;
  }

  public void setWritingTaskcol1(String writingTaskcol1) {
    this.writingTaskcol1 = writingTaskcol1;
  }

  public Set getPaperPlans()
  {
    return this.paperPlans;
  }

  public void setPaperPlans(Set paperPlans)
  {
    this.paperPlans = paperPlans;
  }
}