package net.dqsy.papermg.sysmanager.service;

import net.dqsy.papermg.sysmanager.po.PaperTeacher;
import net.dqsy.papermg.util.BaseService;
import net.dqsy.papermg.sysmanager.po.PaperTeacher;

import java.util.List;

public abstract interface PaperTeacherService extends BaseService
{
  public abstract PaperTeacher findByNumber(String paramString);

  public abstract boolean updateTeacherFlag(PaperTeacher paramPaperTeacher, int paramInt);

  public abstract String importTeacher(String paramString);

  public abstract List findTeacherName(String paramString);
}