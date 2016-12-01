package net.dqsy.papermg.papermanager.action;

import net.dqsy.papermg.papermanager.po.PaperScore;
import net.dqsy.papermg.papermanager.service.PaperScoreService;

public class PaperScoreAction
{
  private PaperScoreService paperScoreService;

  public void setPaperScoreService(PaperScoreService paperScoreService)
  {
    this.paperScoreService = paperScoreService;
  }

  public PaperScore findScoreByTitleId(int id) {
    try {
      return this.paperScoreService.findScoreByTitleId(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public int teacherInput(int titleId, String comment, double score) {
    try {
      return this.paperScoreService.teacherInput(titleId, comment, score);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }
  public int teamInput(int titleId, String comment, double score) {
    try {
      return this.paperScoreService.teamInput(titleId, comment, score);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }
  public int councilInput(int titleId, String comment) {
    try {
      return this.paperScoreService.councilInput(titleId, comment);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }
}