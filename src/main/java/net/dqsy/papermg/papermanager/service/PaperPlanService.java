package net.dqsy.papermg.papermanager.service;

import net.dqsy.papermg.util.BaseService;
import java.util.List;

public abstract interface PaperPlanService extends BaseService
{
  public abstract List findByWritingTaskID(int paramInt);
}