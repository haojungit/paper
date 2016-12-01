package net.dqsy.papermg.papermanager.po;

import java.io.Serializable;

public class PaperTitleState
  implements Serializable
{
  private int paperTitleStateId;
  private int paperTitleState;
  private String paperTitleStateDescription;

  public PaperTitleState()
  {
  }

  public PaperTitleState(int paperTitleState, String paperTitleStateDescription)
  {
    this.paperTitleState = paperTitleState;
    this.paperTitleStateDescription = paperTitleStateDescription;
  }

  public int getPaperTitleStateId() {
    return this.paperTitleStateId;
  }

  public void setPaperTitleStateId(int paperTitleStateId) {
    this.paperTitleStateId = paperTitleStateId;
  }

  public int getPaperTitleState() {
    return this.paperTitleState;
  }

  public void setPaperTitleState(int paperTitleState) {
    this.paperTitleState = paperTitleState;
  }

  public String getPaperTitleStateDescription() {
    return this.paperTitleStateDescription;
  }

  public void setPaperTitleStateDescription(String paperTitleStateDescription) {
    this.paperTitleStateDescription = paperTitleStateDescription;
  }
}