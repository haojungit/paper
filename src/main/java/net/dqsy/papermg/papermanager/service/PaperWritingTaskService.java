package net.dqsy.papermg.papermanager.service;

import net.dqsy.papermg.papermanager.po.PaperPlan;
import net.dqsy.papermg.papermanager.po.PaperWritingTask;
import net.dqsy.papermg.util.BaseService;
import java.util.List;

public abstract interface PaperWritingTaskService extends BaseService
{
  public abstract boolean saveWritingTask(int paramInt, PaperWritingTask paramPaperWritingTask, List<PaperPlan> paramList);

  public abstract List findByTieleId(int paramInt);
}