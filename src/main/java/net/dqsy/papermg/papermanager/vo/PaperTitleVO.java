package net.dqsy.papermg.papermanager.vo;

import java.io.Serializable;

public class PaperTitleVO
  implements Serializable
{
  private int paperTitleId;
  private String paperTitleName;
  private int paperTitleState;
  private String paperTeacherName;

  public PaperTitleVO(int paperTitleId, String paperTitleName, String paperTeacherName)
  {
    this.paperTitleId = paperTitleId;
    this.paperTitleName = paperTitleName;
    this.paperTeacherName = paperTeacherName;
  }

  public PaperTitleVO(int paperTitleId, String paperTitleName, int paperTitleState)
  {
    this.paperTitleId = paperTitleId;
    this.paperTitleName = paperTitleName;
    this.paperTitleState = paperTitleState;
  }

  public PaperTitleVO(int paperTitleId, int paperTitleState, String paperTitleName, String paperTeacherName)
  {
    this.paperTitleId = paperTitleId;
    this.paperTitleName = paperTitleName;
    this.paperTitleState = paperTitleState;
    this.paperTeacherName = paperTeacherName;
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

  public String getPaperTeacherName() {
    return this.paperTeacherName;
  }

  public void setPaperTeacherName(String paperTeacherName) {
    this.paperTeacherName = paperTeacherName;
  }

  public String toString()
  {
    return "ThesisProposalVO [paperTitleId=" + this.paperTitleId + 
      ", paperTitleName=" + this.paperTitleName + ", paperTeacherName=" + 
      this.paperTeacherName + "]";
  }

  public int getPaperTitleState() {
    return this.paperTitleState;
  }

  public void setPaperTitleState(int paperTitleState) {
    this.paperTitleState = paperTitleState;
  }
}