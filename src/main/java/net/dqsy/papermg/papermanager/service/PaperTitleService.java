package net.dqsy.papermg.papermanager.service;

import net.dqsy.papermg.papermanager.po.PaperTitle;
import net.dqsy.papermg.sysmanager.po.PaperStudent;
import net.dqsy.papermg.util.BaseService;
import net.dqsy.papermg.util.PagingSupport;
import net.dqsy.papermg.sysmanager.po.PaperStudent;
import net.dqsy.papermg.util.PagingSupport;

import java.util.List;

public abstract interface PaperTitleService extends BaseService
{
  public abstract String save(PaperTitle paramPaperTitle);

  public abstract String saveStudentDTP(int paramInt1, int paramInt2, PaperTitle paramPaperTitle);

  public abstract boolean updateState(int paramInt1, int paramInt2);

  public abstract boolean updateStateByBatch(List<Integer> paramList, int paramInt);

  public abstract boolean approvePaperTitle(PaperTitle paramPaperTitle, int paramInt);

  public abstract String chooseTitle(int paramInt, PaperStudent paramPaperStudent);

  public abstract PagingSupport findByTeacherId(int paramInt1, int paramInt2, int paramInt3);

  public abstract List findTeacherName();

  public abstract PagingSupport findByTitleState(int paramInt1, int paramInt2, int paramInt3);

  public abstract List findTitleByStudentId(int paramInt1, int paramInt2);

  public abstract List findTitleByTeacherId(int paramInt1, int paramInt2);

  public abstract PagingSupport findByStudentMajor(String paramString, int paramInt1, int paramInt2);

  public abstract List findStudentInfoByTitleId(int paramInt);

  public abstract int findPaperTitState(int paramInt);

  public abstract List<String> findFaculty();

  public abstract List findMajorByFaculty(String paramString);

  public abstract List findGrade();

  public abstract PagingSupport findPageByFMG(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);
}