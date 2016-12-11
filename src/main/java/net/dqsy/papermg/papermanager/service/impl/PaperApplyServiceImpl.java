package net.dqsy.papermg.papermanager.service.impl;

import net.dqsy.papermg.papermanager.dao.PaperApplyDAO;
import net.dqsy.papermg.papermanager.po.PaperApply;
import net.dqsy.papermg.papermanager.po.PaperTitle;
import net.dqsy.papermg.papermanager.service.PaperApplyService;
import net.dqsy.papermg.papermanager.service.PaperTitleService;
import net.dqsy.papermg.util.PagingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PaperApplyServiceImpl
        implements PaperApplyService
{
    @Autowired
    private PaperApplyDAO paperApplyDAO;
    @Autowired
    private PaperTitleService paperTitleService;

    public boolean save(Object o) {
        try {
            this.paperApplyDAO.save((PaperApply) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveWithTitleId(int titleId, PaperApply paperApply) {
        try {
            PaperTitle paperTitle = (PaperTitle) this.paperTitleService
                    .findById(titleId);
            paperApply.setPaperTitle(paperTitle);
            this.paperApplyDAO.save(paperApply);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateWithTitleId(int titleId, PaperApply paperApply) {
        try {
            List list = find(
                    "from PaperApply where paperTitle.paperTitleId= " + titleId,
                    null, 1, 999).getList();

            PaperApply pa = (PaperApply) list.get(list.size() - 1);

            pa.setAllowReply(paperApply.getAllowReply());
            pa.setScore(paperApply.getScore());
            pa.setInpuDate(paperApply.getInpuDate());
            update(pa);
            if (paperApply.getAllowReply().equals("是"))
                this.paperTitleService.updateState(titleId, 17);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List findByTitleId(int titleId) {
        try {
            return find(
                    "select new com.pactera.papermg.papermanager.po.PaperApply(aplyId,applyReason,applyDate,score,allowReply,inpuDate) from PaperApply where paperTitle.paperTitleId = " +
                            titleId, null, 1, 999).getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Object o) {
        try {
            this.paperApplyDAO.update((PaperApply) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PagingSupport find(String hql, HashMap<String, Object> map, int numberOfPage, int countOfPage) {
        try {
            return this.paperApplyDAO.find(hql, map, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PaperApply findById(int id) {
        try {
            return (PaperApply) this.paperApplyDAO.findById(
                    "com.pactera.papermg.papermanager.po.PaperApply", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        try {
            return this.paperApplyDAO.findByProperty("PaperApply", property, value,
                    numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, int value, int numberOfPage, int countOfPage) {
        try {
            return this.paperApplyDAO.findByProperty("PaperApply", property, value,
                    numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        try {
            return this.paperApplyDAO.findAll("PaperApply", numberOfPage,
                    countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean del(Object o) {
        try {
            this.paperApplyDAO.delete((PaperApply) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}