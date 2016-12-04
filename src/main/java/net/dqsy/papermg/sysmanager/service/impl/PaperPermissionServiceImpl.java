package net.dqsy.papermg.sysmanager.service.impl;

import net.dqsy.papermg.sysmanager.dao.PaperPermissionDAO;
import net.dqsy.papermg.sysmanager.po.PaperPermission;
import net.dqsy.papermg.sysmanager.service.PaperPermissionService;
import net.dqsy.papermg.util.PagingSupport;
import net.dqsy.papermg.util.PaperManagerException;
import org.springframework.stereotype.Service;

@Service
public class PaperPermissionServiceImpl
        implements PaperPermissionService
{
    private PaperPermissionDAO paperPermissionDAO;

    public boolean save(Object o) {
        try {
            this.paperPermissionDAO.save((PaperPermission) o);
        } catch (PaperManagerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Object o) {
        try {
            this.paperPermissionDAO.update((PaperPermission) o);
        } catch (PaperManagerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PagingSupport find(String hql, int numberOfPage, int countOfPage) {
        try {
            return this.paperPermissionDAO.find(hql, numberOfPage, countOfPage);
        } catch (PaperManagerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PaperPermission findById(int id) {
        try {
            return (PaperPermission) this.paperPermissionDAO.findById(
                    "com.pactera.papermg.sysmanager.po.PaperPermission", id);
        } catch (PaperManagerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        try {
            return this.paperPermissionDAO.findByProperty("PaperPermission",
                    property, value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, int value, int numberOfPage, int countOfPage) {
        try {
            return this.paperPermissionDAO.findByProperty("PaperPermission",
                    property, value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        try {
            return this.paperPermissionDAO.findAll("PaperPermission", numberOfPage,
                    countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean del(Object o) {
        try {
            this.paperPermissionDAO.delete((PaperPermission) o);
        } catch (PaperManagerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void setPaperPermissionDAO(PaperPermissionDAO paperPermissionDAO) {
        this.paperPermissionDAO = paperPermissionDAO;
    }

    public boolean delsoftOrRenew(int pid) {
        try {
            PaperPermission permission = findById(pid);
            if (permission.getFlag().intValue() == 0)
                permission.setFlag(Integer.valueOf(1));
            else if (permission.getFlag().intValue() == 1) {
                permission.setFlag(Integer.valueOf(0));
            }
            this.paperPermissionDAO.update(permission);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}