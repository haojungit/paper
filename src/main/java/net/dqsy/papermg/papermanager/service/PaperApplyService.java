package net.dqsy.papermg.papermanager.service;

import net.dqsy.papermg.papermanager.po.PaperApply;
import net.dqsy.papermg.util.BaseService;
import java.util.List;

public abstract interface PaperApplyService extends BaseService
{
  public abstract boolean saveWithTitleId(int paramInt, PaperApply paramPaperApply);

  public abstract boolean updateWithTitleId(int paramInt, PaperApply paramPaperApply);

  public abstract List findByTitleId(int paramInt);
}