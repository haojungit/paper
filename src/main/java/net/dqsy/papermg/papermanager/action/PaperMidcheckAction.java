package net.dqsy.papermg.papermanager.action;

import net.dqsy.papermg.papermanager.po.PaperMidcheck;
import net.dqsy.papermg.papermanager.service.PaperMidcheckService;
import net.dqsy.papermg.papermanager.service.PaperMidcheckService;

import java.util.List;

public class PaperMidcheckAction
{
  private PaperMidcheckService paperMidcheckService;

  public boolean saveWithTitleId(int titleId, PaperMidcheck paperMidcheck)
  {
    return this.paperMidcheckService.saveWithTitleId(titleId, paperMidcheck);
  }

  public boolean updateWithTitleId(int titleId, PaperMidcheck paperMidcheck)
  {
    return this.paperMidcheckService.updateWithTitleId(titleId, paperMidcheck);
  }

  public List findByTitleId(int titleId)
  {
    return this.paperMidcheckService.findByTitleId(titleId);
  }

  public void setPaperMidcheckService(PaperMidcheckService paperMidcheckService)
  {
    this.paperMidcheckService = paperMidcheckService;
  }
}