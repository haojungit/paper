package net.dqsy.papermg.sysmanager.service.impl;

import net.dqsy.papermg.sysmanager.dao.PaperRolePermissionDAO;
import net.dqsy.papermg.sysmanager.po.PaperRolePermission;
import net.dqsy.papermg.sysmanager.service.PaperRolePermissionService;
import net.dqsy.papermg.util.PagingSupport;
import net.dqsy.papermg.util.PaperManagerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperRolePermissionServiceImpl
        implements PaperRolePermissionService
{
    @Autowired
    private PaperRolePermissionDAO paperRolePermissionDAO;

    public boolean save(Object o) {
        try {
            this.paperRolePermissionDAO.save((PaperRolePermission) o);
        } catch (PaperManagerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Object o) {
        try {
            this.paperRolePermissionDAO.update((PaperRolePermission) o);
        } catch (PaperManagerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PagingSupport find(String hql, int numberOfPage, int countOfPage) {
        try {
            return this.paperRolePermissionDAO.find(hql, numberOfPage, countOfPage);
        } catch (PaperManagerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PaperRolePermission findById(int id) {
        try {
            return (PaperRolePermission) this.paperRolePermissionDAO
                    .findById(
                            "com.pactera.papermg.sysmanager.po.PaperRolePermission",
                            id);
        } catch (PaperManagerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        try {
            return this.paperRolePermissionDAO.findByProperty("PaperRolePermission",
                    property, value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, int value, int numberOfPage, int countOfPage) {
        try {
            return this.paperRolePermissionDAO.findByProperty("PaperRolePermission",
                    property, value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        try {
            return this.paperRolePermissionDAO.findAll("PaperRolePermission",
                    numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean del(Object o) {
        try {
            this.paperRolePermissionDAO.delete((PaperRolePermission) o);
            return true;
        } catch (PaperManagerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public PagingSupport getRolePermission(int roleID) {
        try {
            return this.paperRolePermissionDAO.find(
                    "select paperPermission.id from PaperRolePermission where paperRole.roleId = " +
                            roleID, 1, 999);
        } catch (PaperManagerException e) {
            e.printStackTrace();
        }
        return null;
    }


}