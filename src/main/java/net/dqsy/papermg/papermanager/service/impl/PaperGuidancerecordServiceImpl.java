package net.dqsy.papermg.papermanager.service.impl;

import net.dqsy.papermg.papermanager.dao.PaperGuidancerecordDAO;
import net.dqsy.papermg.papermanager.po.PaperGuidancerecord;
import net.dqsy.papermg.papermanager.po.PaperTitle;
import net.dqsy.papermg.papermanager.service.PaperGuidancerecordService;
import net.dqsy.papermg.papermanager.service.PaperTitleService;
import net.dqsy.papermg.util.PagingSupport;
import net.dqsy.papermg.util.PaperManagerException;
import net.dqsy.papermg.papermanager.dao.PaperGuidancerecordDAO;
import net.dqsy.papermg.util.PaperManagerException;

import java.util.List;

public class PaperGuidancerecordServiceImpl
  implements PaperGuidancerecordService
{
  private PaperGuidancerecordDAO paperGuidancerecordDAO;
  private PaperTitleService paperTitleService;

  public PaperGuidancerecordDAO getPaperGuidancerecordDAO()
  {
    return this.paperGuidancerecordDAO;
  }

  public void setPaperGuidancerecordDAO(PaperGuidancerecordDAO paperGuidancerecordDAO)
  {
    this.paperGuidancerecordDAO = paperGuidancerecordDAO;
  }

  public boolean save(Object o)
  {
    try {
      this.paperGuidancerecordDAO.save((PaperGuidancerecord)o);
      return true;
    } catch (PaperManagerException e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean saveWithTitleId(int titleId, PaperGuidancerecord paperGuidancerecord)
  {
    try
    {
      PaperTitle paperTitle = (PaperTitle)this.paperTitleService
        .findById(titleId);
      paperGuidancerecord.setPaperTitle(paperTitle);
      this.paperGuidancerecordDAO.save(paperGuidancerecord);

      return true;
    } catch (PaperManagerException e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean updateWithTitleId(int titleId, PaperGuidancerecord paperGuidancerecord)
  {
    try
    {
      List list = find(
        "from PaperGuidancerecord where paperTitle.paperTitleId = " + 
        titleId + "order by guidId ASC", 1, 999)
        .getList();
      PaperGuidancerecord paperGuidancerecord1 = 
        (PaperGuidancerecord)list
        .get(list.size() - 1);
      paperGuidancerecord1.setGuidance(paperGuidancerecord.getGuidance());
      paperGuidancerecord1.setGuidanceType(paperGuidancerecord
        .getGuidanceType());
      paperGuidancerecord1.setGuidanceDate(paperGuidancerecord
        .getGuidanceDate());
      this.paperGuidancerecordDAO.update(paperGuidancerecord1);
      int state = this.paperTitleService.findPaperTitState(titleId);
      if ((state != -1) && (state >= 8) && (state <= 15))
        this.paperTitleService.updateState(titleId, state + 1);
      else {
        throw new PaperManagerException();
      }
      return true;
    } catch (PaperManagerException e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean update(Object o)
  {
    try {
      this.paperGuidancerecordDAO.update((PaperGuidancerecord)o);
      return true;
    } catch (PaperManagerException e) {
      e.printStackTrace();
    }
    return false;
  }

  public PagingSupport find(String hql, int numberOfPage, int countOfPage)
  {
    try {
      return this.paperGuidancerecordDAO.find(hql, numberOfPage, countOfPage);
    } catch (PaperManagerException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List findWithTitleId(int titleId)
  {
    try
    {
      return find(
        "select new com.pactera.papermg.papermanager.po.PaperGuidancerecord(guidId,guidance,guidanceType,guidanceDate) from PaperGuidancerecord where paperTitle.paperTitleId = " + 
        titleId, 1, 999).getList();
    } catch (PaperManagerException e) {
      e.printStackTrace();
    }
    return null;
  }

  public PaperGuidancerecord findById(int id)
  {
    try {
      return (PaperGuidancerecord)this.paperGuidancerecordDAO.findById(
        "com.pactera.papermg.papermanager.po.PaperGuidancerecord", 
        id);
    } catch (PaperManagerException e) {
      e.printStackTrace();
    }
    return null;
  }

  public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage)
  {
    try
    {
      return this.paperGuidancerecordDAO.findByProperty("PaperGuidancerecord", 
        property, value, numberOfPage, countOfPage);
    } catch (PaperManagerException e) {
      e.printStackTrace();
    }
    return null;
  }

  public PagingSupport findByProperty(String property, int value, int numberOfPage, int countOfPage)
  {
    try
    {
      return this.paperGuidancerecordDAO.findByProperty("PaperGuidancerecord", 
        property, value, numberOfPage, countOfPage);
    } catch (PaperManagerException e) {
      e.printStackTrace();
    }
    return null;
  }

  public PagingSupport findAll(int numberOfPage, int countOfPage)
  {
    try {
      return this.paperGuidancerecordDAO.findAll("PaperGuidancerecord", 
        numberOfPage, countOfPage);
    } catch (PaperManagerException e) {
      e.printStackTrace();
    }
    return null;
  }

  public boolean del(Object o)
  {
    try {
      this.paperGuidancerecordDAO.delete((PaperGuidancerecord)o);
      return true;
    } catch (PaperManagerException e) {
      e.printStackTrace();
    }
    return false;
  }

  public PaperTitleService getPaperTitleService() {
    return this.paperTitleService;
  }

  public void setPaperTitleService(PaperTitleService paperTitleService) {
    this.paperTitleService = paperTitleService;
  }
}