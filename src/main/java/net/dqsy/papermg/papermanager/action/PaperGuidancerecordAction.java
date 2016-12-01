package net.dqsy.papermg.papermanager.action;

import net.dqsy.papermg.papermanager.po.PaperGuidancerecord;
import net.dqsy.papermg.papermanager.service.PaperGuidancerecordService;
import net.dqsy.papermg.papermanager.service.PaperGuidancerecordService;

import java.util.List;

public class PaperGuidancerecordAction
{
  private PaperGuidancerecordService paperGuidancerecordService;

  public boolean saveWithTitleId(int titleId, PaperGuidancerecord paperGuidancerecord)
  {
    return this.paperGuidancerecordService.saveWithTitleId(titleId, 
      paperGuidancerecord);
  }

  public boolean updateWithTitleId(int titleId, PaperGuidancerecord paperGuidancerecord)
  {
    return this.paperGuidancerecordService.updateWithTitleId(titleId, 
      paperGuidancerecord);
  }

  public void setPaperGuidancerecordService(PaperGuidancerecordService paperGuidancerecordService)
  {
    this.paperGuidancerecordService = paperGuidancerecordService;
  }

  public List findWithTitleId(int titleId)
  {
    return this.paperGuidancerecordService.findWithTitleId(titleId);
  }
}