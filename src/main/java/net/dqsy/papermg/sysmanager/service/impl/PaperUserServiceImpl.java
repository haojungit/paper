package net.dqsy.papermg.sysmanager.service.impl;

import net.dqsy.papermg.sysmanager.dao.PaperUserDAO;
import net.dqsy.papermg.sysmanager.po.PaperUser;
import net.dqsy.papermg.sysmanager.service.PaperUserService;
import net.dqsy.papermg.util.MD5Encoder;
import net.dqsy.papermg.util.PagingSupport;
import net.dqsy.papermg.util.PaperManagerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperUserServiceImpl
        implements PaperUserService
{
    @Autowired
    private PaperUserDAO paperUserDAO;

    public boolean save(Object o) {
        try {
            PaperUser paperUser = (PaperUser) o;

            List list = this.paperUserDAO.findByProperty("PaperUser", "userName",
                    paperUser.getUserName(), 1, 1).getList();
            if (list.size() > 0)
                return false;
            paperUser.setPassWord(MD5Encoder.encode(paperUser.getPassWord()));
            this.paperUserDAO.save(paperUser);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Object o) {
        try {
            this.paperUserDAO.update((PaperUser) o);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PagingSupport find(String hql, int numberOfPage, int countOfPage)
            throws PaperManagerException {
        try {
            return this.paperUserDAO.find(hql, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PaperUser findById(int id) {
        try {
            return (PaperUser) this.paperUserDAO.findById(
                    "net.dqsy.papermg.sysmanager.po.PaperUser", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        try {
            return this.paperUserDAO.findByProperty("PaperUser", property, value,
                    numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, int value, int numberOfPage, int countOfPage) {
        try {
            return this.paperUserDAO.findByProperty("PaperUser", property, value,
                    numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage)
            throws PaperManagerException {
        try {
            this.paperUserDAO.findAll("PaperUser", numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean del(Object o) {
        try {
            this.paperUserDAO.delete((PaperUser) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List login(String userName, String passWord) {
        try {
            String pwd =
                    (String) find(
                            "select passWord from PaperUser where userName = '" +
                                    userName + "'", 1, 1).getList().get(0);
            if (pwd.equals(MD5Encoder.encode(passWord)))
                return find(
                        "from LoginVO where Username = '" + userName +
                                "' order by roleid,porder asc", 1, 999).getList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public String updatePassword(String userName, String oldPassWord, String newPassWord) {
        try {
            PaperUser paperUser =
                    (PaperUser) findByProperty("userName",
                            userName, 1, 1).getList().get(0);

            if (MD5Encoder.encode(oldPassWord).equals(paperUser.getPassWord())) {
                paperUser.setPassWord(MD5Encoder.encode(newPassWord));
                update(paperUser);
            } else {
                return "旧密码不正确!请重新输入";
            }
        } catch (IndexOutOfBoundsException iooe) {
            return "用户不存在!";
        } catch (PaperManagerException e) {
            e.printStackTrace();
            return "系统异常,请稍后重试!";
        }
        return "修改成功!";
    }
}