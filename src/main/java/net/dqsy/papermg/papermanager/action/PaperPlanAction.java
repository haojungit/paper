package net.dqsy.papermg.papermanager.action;

import net.dqsy.papermg.papermanager.service.PaperPlanService;
import net.dqsy.papermg.papermanager.service.PaperPlanService;

import java.util.List;

public class PaperPlanAction
{
  private PaperPlanService paperPlanService;

  public void setPaperPlanService(PaperPlanService paperPlanService)
  {
    this.paperPlanService = paperPlanService;
  }

  public List findByWritingTaskID(int writingTaskID)
  {
    return this.paperPlanService.findByWritingTaskID(writingTaskID);
  }
}