package net.dqsy.papermg.sysmanager.service;

import net.dqsy.papermg.sysmanager.po.PaperStudent;
import net.dqsy.papermg.util.BaseService;
import net.dqsy.papermg.sysmanager.po.PaperStudent;

import java.util.List;

public abstract interface PaperStudentService extends BaseService
{
  public abstract PaperStudent findByNumber(String paramString);

  public abstract List findStudentMajor(String paramString);

  public abstract boolean updateStudentFlag(PaperStudent paramPaperStudent, int paramInt);

  public abstract boolean resetStudentPassword(int paramInt);

  public abstract String importStudent(String paramString);
}