package net.dqsy.papermg.papermanager.service;

import net.dqsy.papermg.papermanager.po.PaperMidcheck;
import net.dqsy.papermg.util.BaseService;
import java.util.List;

public abstract interface PaperMidcheckService extends BaseService
{
  public abstract boolean saveWithTitleId(int paramInt, PaperMidcheck paramPaperMidcheck);

  public abstract boolean updateWithTitleId(int paramInt, PaperMidcheck paramPaperMidcheck);

  public abstract List findByTitleId(int paramInt);
}