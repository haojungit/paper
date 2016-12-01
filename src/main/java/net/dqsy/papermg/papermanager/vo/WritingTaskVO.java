package net.dqsy.papermg.papermanager.vo;

import java.io.Serializable;
import java.util.Date;

public class WritingTaskVO
  implements Serializable
{
  private int paperTitleId;
  private String paperTitleName;
  private int writingTaskID;
  private Date writingTaskStartTime;
  private Date writingTaskEndTime;
  private String writingTaskContent;
  private String writingTaskReference;

  public WritingTaskVO(int paperTitleId, String paperTitleName)
  {
    this.paperTitleId = paperTitleId;
    this.paperTitleName = paperTitleName;
  }

  public WritingTaskVO(int writingTaskID, Date writingTaskStartTime, Date writingTaskEndTime, String writingTaskContent, String writingTaskReference)
  {
    this.writingTaskID = writingTaskID;
    this.writingTaskStartTime = writingTaskStartTime;
    this.writingTaskEndTime = writingTaskEndTime;
    this.writingTaskContent = writingTaskContent;
    this.writingTaskReference = writingTaskReference;
  }

  public int getPaperTitleId() {
    return this.paperTitleId;
  }

  public void setPaperTitleId(int paperTitleId) {
    this.paperTitleId = paperTitleId;
  }

  public String getPaperTitleName() {
    return this.paperTitleName;
  }

  public void setPaperTitleName(String paperTitleName) {
    this.paperTitleName = paperTitleName;
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

  public int getWritingTaskID() {
    return this.writingTaskID;
  }

  public void setWritingTaskID(int writingTaskID) {
    this.writingTaskID = writingTaskID;
  }
}