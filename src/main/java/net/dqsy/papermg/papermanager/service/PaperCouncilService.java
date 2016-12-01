package net.dqsy.papermg.papermanager.service;

import net.dqsy.papermg.papermanager.po.PaperCouncil;
import net.dqsy.papermg.util.BaseService;
import net.dqsy.papermg.papermanager.po.PaperCouncil;

import java.util.List;

public abstract interface PaperCouncilService extends BaseService
{
  public abstract String apply(PaperCouncil paramPaperCouncil, List<Integer> paramList, List<String> paramList1);

  public abstract String approve(PaperCouncil paramPaperCouncil);

  public abstract List findCouncilByManId(int paramInt);
}