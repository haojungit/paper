package net.dqsy.papermg.papermanager.service.impl;

import net.dqsy.papermg.papermanager.dao.PaperPlanDAO;
import net.dqsy.papermg.papermanager.po.PaperPlan;
import net.dqsy.papermg.papermanager.service.PaperPlanService;
import net.dqsy.papermg.util.PagingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PaperPlanServiceImpl
        implements PaperPlanService
{
    @Autowired
    private PaperPlanDAO paperPlanDAO;


    public boolean save(Object o) {
        try {
            this.paperPlanDAO.save((PaperPlan) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Object o) {
        try {
            this.paperPlanDAO.update((PaperPlan) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PagingSupport find(String hql, HashMap<String, Object> map, int numberOfPage, int countOfPage) {
        try {
            return this.paperPlanDAO.find(hql, map, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PaperPlan findById(int id) {
        try {
            return (PaperPlan) this.paperPlanDAO.findById(
                    "com.pactera.papermg.papermanager.po.PaperPlan", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List findByWritingTaskID(int writingTaskID) {
        return this.paperPlanDAO
                .find("select new com.pactera.papermg.papermanager.vo.PaperPlanVO(planId,planTask,planStartTime,planEndTime) from PaperPlan where paperWritingTask.writingTaskID = " +
                        writingTaskID, null, 1, 99).getList();
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        try {
            return this.paperPlanDAO.findByProperty("PaperPlan", property, value,
                    numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, int value, int numberOfPage, int countOfPage) {
        try {
            return this.paperPlanDAO.findByProperty("PaperPlan", property, value,
                    numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        try {
            return this.paperPlanDAO.findAll("PaperPlan", numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean del(Object o) {
        try {
            this.paperPlanDAO.delete((PaperPlan) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}