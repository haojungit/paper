package net.dqsy.papermg.papermanager.service;

import net.dqsy.papermg.papermanager.po.PaperGuidancerecord;
import net.dqsy.papermg.util.BaseService;
import java.util.List;

public abstract interface PaperGuidancerecordService extends BaseService
{
  public abstract boolean saveWithTitleId(int paramInt, PaperGuidancerecord paramPaperGuidancerecord);

  public abstract boolean updateWithTitleId(int paramInt, PaperGuidancerecord paramPaperGuidancerecord);

  public abstract List findWithTitleId(int paramInt);
}