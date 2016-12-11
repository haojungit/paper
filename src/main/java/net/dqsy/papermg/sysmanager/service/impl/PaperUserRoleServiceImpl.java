package net.dqsy.papermg.sysmanager.service.impl;

import net.dqsy.papermg.sysmanager.dao.PaperUserRoleDAO;
import net.dqsy.papermg.sysmanager.po.PaperUserRole;
import net.dqsy.papermg.sysmanager.service.PaperUserRoleService;
import net.dqsy.papermg.util.PagingSupport;
import net.dqsy.papermg.util.PaperManagerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PaperUserRoleServiceImpl
        implements PaperUserRoleService
{
    @Autowired
    private PaperUserRoleDAO paperUserRoleDAO;

    public boolean save(Object o) {
        try {
            this.paperUserRoleDAO.save((PaperUserRole) o);
        } catch (PaperManagerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Object o) {
        try {
            this.paperUserRoleDAO.update((PaperUserRole) o);
        } catch (PaperManagerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PagingSupport find(String hql, HashMap<String, Object> map, int numberOfPage, int countOfPage) {
        try {
            return this.paperUserRoleDAO.find(hql, map, numberOfPage, countOfPage);
        } catch (PaperManagerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PaperUserRole findById(int id) {
        try {
            return (PaperUserRole) this.paperUserRoleDAO.findById(
                    "com.pactera.papermg.sysmanager.po.PaperUserRole", id);
        } catch (PaperManagerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        try {
            return this.paperUserRoleDAO.findByProperty("PaperUserRole", property,
                    value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, int value, int numberOfPage, int countOfPage) {
        try {
            return this.paperUserRoleDAO.findByProperty("PaperUserRole", property,
                    value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        try {
            return this.paperUserRoleDAO.findAll("PaperUserRole", numberOfPage,
                    countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean del(Object o) {
        try {
            this.paperUserRoleDAO.delete((PaperUserRole) o);
            return true;
        } catch (PaperManagerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public PagingSupport getUserRole(int userID) {
        try {
            return this.paperUserRoleDAO.find(
                    "select paperRole.roleId from PaperUserRole where paperUser.userId = " +
                            userID, null, 1, 999);
        } catch (PaperManagerException e) {
            e.printStackTrace();
        }
        return null;
    }
}