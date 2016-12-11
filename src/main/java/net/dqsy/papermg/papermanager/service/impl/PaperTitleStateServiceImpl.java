package net.dqsy.papermg.papermanager.service.impl;

import net.dqsy.papermg.papermanager.dao.PaperTitleStateDAO;
import net.dqsy.papermg.papermanager.po.PaperTitleState;
import net.dqsy.papermg.papermanager.service.PaperTitleStateService;
import net.dqsy.papermg.util.PagingSupport;
import net.dqsy.papermg.util.PaperManagerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PaperTitleStateServiceImpl
        implements PaperTitleStateService
{
    @Autowired
    private PaperTitleStateDAO paperTitleStateDAO;

    public boolean save(Object o) {
        try {
            this.paperTitleStateDAO.save((PaperTitleState) o);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Object o) {
        try {
            this.paperTitleStateDAO.update((PaperTitleState) o);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PagingSupport find(String hql, HashMap<String, Object> map, int numberOfPage, int countOfPage) {
        try {
            return this.paperTitleStateDAO.find(hql, map, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object findById(int id) {
        try {
            return (PaperTitleState) this.paperTitleStateDAO.findById(
                    "com.pactera.papermg.papermanager.po.PaperTitleState", id);
        } catch (PaperManagerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        try {
            return this.paperTitleStateDAO.findByProperty("PaperTitleState",
                    property, value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, int value, int numberOfPage, int countOfPage) {
        try {
            return this.paperTitleStateDAO.findByProperty("PaperTitleState",
                    property, value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        try {
            return this.paperTitleStateDAO.findAll("PaperTitleState", numberOfPage,
                    countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean del(Object o) {
        try {
            this.paperTitleStateDAO.delete((PaperTitleState) o);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PaperTitleState findByState(int state) {
        try {
            return
                    (PaperTitleState) findByProperty(
                            "paperTitleState", state, 1, 1).getList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}