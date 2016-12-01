package net.dqsy.papermg.papermanager.service;

import net.dqsy.papermg.papermanager.po.PaperScore;
import net.dqsy.papermg.util.BaseService;

public abstract interface PaperScoreService extends BaseService
{
  public abstract PaperScore findScoreByTitleId(int paramInt);

  public abstract int teacherInput(int paramInt, String paramString, double paramDouble);

  public abstract int teamInput(int paramInt, String paramString, double paramDouble);

  public abstract int councilInput(int paramInt, String paramString);
}