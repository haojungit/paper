package net.dqsy.papermg.papermanager.service.impl;

import net.dqsy.papermg.papermanager.dao.PaperRecordDAO;
import net.dqsy.papermg.papermanager.dao.PaperTitleDAO;
import net.dqsy.papermg.papermanager.po.PaperRecord;
import net.dqsy.papermg.papermanager.po.PaperTitle;
import net.dqsy.papermg.papermanager.service.PaperRecordService;
import net.dqsy.papermg.util.PagingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperRecordServiceImpl
        implements PaperRecordService
{
    @Autowired
    private PaperRecordDAO paperRecordDAO;
    @Autowired
    private PaperTitleDAO paperTitleDAO;

    public boolean save(Object o) {
        try {
            this.paperRecordDAO.save((PaperRecord) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Object o) {
        try {
            this.paperRecordDAO.update((PaperRecord) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PagingSupport find(String hql, int numberOfPage, int countOfPage) {
        try {
            return this.paperRecordDAO.find(hql, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object findById(int id) {
        try {
            return this.paperRecordDAO.findById("com.pactera.papermg.papermanager.po.PaperRecord", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        try {
            return this.paperRecordDAO.findByProperty("PaperRecord", property, value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, int value, int numberOfPage, int countOfPage) {
        try {
            return this.paperRecordDAO.findByProperty("PaperRecord", property, value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        try {
            return this.paperRecordDAO.findAll("PaperRecord", numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean del(Object o) {
        try {
            this.paperRecordDAO.delete(o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean writeRecord(int titleId, PaperRecord record) {
        try {
            PaperTitle title = (PaperTitle) this.paperTitleDAO.findById("com.pactera.papermg.papermanager.po.PaperTitle", titleId);
            record.setPaperTitle(title);
            this.paperRecordDAO.save(record);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}