package net.dqsy.papermg.papermanager.service.impl;

import net.dqsy.papermg.papermanager.dao.PaperWritingTaskDAO;
import net.dqsy.papermg.papermanager.po.PaperPlan;
import net.dqsy.papermg.papermanager.po.PaperTitle;
import net.dqsy.papermg.papermanager.po.PaperWritingTask;
import net.dqsy.papermg.papermanager.service.PaperPlanService;
import net.dqsy.papermg.papermanager.service.PaperTitleService;
import net.dqsy.papermg.papermanager.service.PaperWritingTaskService;
import net.dqsy.papermg.util.PagingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperWritingTaskServiceImpl
        implements PaperWritingTaskService
{
    @Autowired
    private PaperWritingTaskDAO paperWritingTaskDAO;
    @Autowired
    private PaperTitleService paperTitleService;
    @Autowired
    private PaperPlanService paperPlanService;

    public boolean save(Object o) {
        try {
            this.paperWritingTaskDAO.save((PaperWritingTask) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveWritingTask(int titleId, PaperWritingTask paperWritingTask, List<PaperPlan> list) {
        try {
            PaperTitle paperTitle = (PaperTitle) this.paperTitleService
                    .findById(titleId);
            paperWritingTask.setPaperTitle(paperTitle);
            this.paperWritingTaskDAO.save(paperWritingTask);
            PaperPlan paperPlan = null;
            for (int i = 0; i < list.size(); i++) {
                paperPlan = (PaperPlan) list.get(i);
                paperPlan.setPaperWritingTask(paperWritingTask);
                this.paperPlanService.save(paperPlan);
            }
            this.paperTitleService.updateState(titleId, 6);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Object o) {
        try {
            this.paperWritingTaskDAO.update((PaperWritingTask) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PagingSupport find(String hql, int numberOfPage, int countOfPage) {
        try {
            return this.paperWritingTaskDAO.find(hql, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PaperWritingTask findById(int id) {
        try {
            return (PaperWritingTask) this.paperWritingTaskDAO.findById(
                    "com.pactera.papermg.papermanager.po.PaperWritingTask", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List findByTieleId(int titleId) {
        return this.paperWritingTaskDAO
                .find("select new com.pactera.papermg.papermanager.vo.WritingTaskVO(writingTaskID,writingTaskStartTime,writingTaskEndTime,writingTaskContent,writingTaskReference) from PaperWritingTask where paperTitle.paperTitleId = " +
                        titleId, 1, 1).getList();
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        try {
            return this.paperWritingTaskDAO.findByProperty("PaperWritingTask",
                    property, value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, int value, int numberOfPage, int countOfPage) {
        try {
            return this.paperWritingTaskDAO.findByProperty("PaperWritingTask",
                    property, value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        try {
            return this.paperWritingTaskDAO.findAll("PaperWritingTask",
                    numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean del(Object o) {
        try {
            this.paperWritingTaskDAO.delete((PaperWritingTask) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}