package net.dqsy.papermg.papermanager.service;

import net.dqsy.papermg.papermanager.po.PaperTitleState;
import net.dqsy.papermg.util.BaseService;
import net.dqsy.papermg.papermanager.po.PaperTitleState;

public abstract interface PaperTitleStateService extends BaseService
{
  public abstract PaperTitleState findByState(int paramInt);
}