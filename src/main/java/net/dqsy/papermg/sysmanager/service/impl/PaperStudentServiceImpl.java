package net.dqsy.papermg.sysmanager.service.impl;

import net.dqsy.papermg.sysmanager.dao.PaperStudentDAO;
import net.dqsy.papermg.sysmanager.po.PaperRole;
import net.dqsy.papermg.sysmanager.po.PaperStudent;
import net.dqsy.papermg.sysmanager.po.PaperUser;
import net.dqsy.papermg.sysmanager.po.PaperUserRole;
import net.dqsy.papermg.sysmanager.service.PaperRoleService;
import net.dqsy.papermg.sysmanager.service.PaperStudentService;
import net.dqsy.papermg.sysmanager.service.PaperUserRoleService;
import net.dqsy.papermg.sysmanager.service.PaperUserService;
import net.dqsy.papermg.sysmanager.util.POIReadExcel_student;
import net.dqsy.papermg.util.MD5Encoder;
import net.dqsy.papermg.util.PagingSupport;
import net.dqsy.papermg.util.PaperManagerException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PaperStudentServiceImpl
        implements PaperStudentService
{
    @Autowired
    private PaperStudentDAO paperStudentDAO;
    @Autowired
    private PaperUserService paperUserService;
    @Autowired
    private PaperRoleService paperRoleService;
    @Autowired
    private PaperUserRoleService paperUserRoleService;
    private Logger logger = Logger.getLogger(getClass());

    public boolean save(Object o) {
        try {
            this.logger.info("saving PaperStudent instance");

            PaperStudent paperStudent = (PaperStudent) o;

            PaperUser paperUser = new PaperUser(
                    paperStudent.getStudentNumber(),
                    paperStudent.getStudentNumber(), "同学", Integer.valueOf(1));

            List list = this.paperStudentDAO.findByProperty("PaperStudent",
                    "studentNumber", paperStudent.getStudentNumber(), 1, 1)
                    .getList();

            if (list.size() > 0) {
                return false;
            }

            if (this.paperUserService.save(paperUser)) {
                paperStudent.setPaperUser(paperUser);

                paperStudent.setFlag(Integer.valueOf(1));

                this.paperStudentDAO.save(paperStudent);

                paperUser =
                        (PaperUser) this.paperUserService
                                .findByProperty("userName",
                                        paperStudent.getStudentNumber(), 1, 1)
                                .getList().get(0);

                PaperRole paperRole =
                        (PaperRole) this.paperRoleService
                                .findByProperty("roleName", "学生", 1, 1).getList()
                                .get(0);

                if ((paperRole != null) && (paperUser != null)) {
                    this.paperUserRoleService.save(
                            new PaperUserRole(paperUser,
                                    paperRole, Integer.valueOf(1)));
                }
                this.logger.info("save successful");
                return true;
            }
        } catch (PaperManagerException e) {
            this.logger.error("save failed", e);
            return false;
        }
        return false;
    }

    public String importStudent(String path) {
        POIReadExcel_student pStudent = new POIReadExcel_student();
        List list = null;

        int importSuccess = 0;
        int importError = 0;
        try {
            list = pStudent.readExcel(path);
            for (int i = 0; i < list.size(); i++) {
                boolean flag = save(list.get(i));
                if (!flag)
                    importError++;
                else
                    importSuccess++;
            }
            return "成功:" + importSuccess + "个,失败:" + importError + "个";
        } catch (Exception e) {
            this.logger.error("save failed", e);
            e.printStackTrace();
        }
        return "导入失败,请确认您的Excel文件填写标准是否与模板标准一致!";
    }

    public boolean update(Object o) {
        try {
            this.paperStudentDAO.update((PaperStudent) o);
        } catch (PaperManagerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean resetStudentPassword(int studentId) {
        try {
            PaperStudent paperStudent = findById(studentId);
            PaperUser paperUser =
                    (PaperUser) this.paperUserService
                            .findByProperty("userName",
                                    paperStudent.getStudentNumber(), 1, 1).getList()
                            .get(0);
            paperUser.setPassWord(MD5Encoder.encode(paperUser.getUserName()));
            this.paperUserService.update(paperUser);
        } catch (PaperManagerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateStudentFlag(PaperStudent paperStudent, int flag) {
        try {
            PaperUser paperUser =
                    (PaperUser) this.paperUserService
                            .findByProperty("userName",
                                    paperStudent.getStudentNumber(), 1, 1).getList()
                            .get(0);

            PaperUserRole paperUserRole = null;

            List list = this.paperUserRoleService.find(
                    "from PaperUserRole where paperUser.userId = " +
                            paperUser.getUserId(), null, 1, 999).getList();

            if ((paperStudent == null) || (paperUser == null)) {
                throw new PaperManagerException();
            }
            paperStudent.setFlag(Integer.valueOf(flag));
            update(paperStudent);

            paperUser.setFlag(Integer.valueOf(flag));
            this.paperUserService.update(paperUser);

            for (int i = 0; i < list.size(); i++) {
                paperUserRole = (PaperUserRole) list.get(i);
                paperUserRole.setFlag(Integer.valueOf(flag));
                this.paperUserRoleService.update(paperUserRole);
            }
        } catch (PaperManagerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PagingSupport find(String hql, HashMap<String, Object> map, int numberOfPage, int countOfPage) {
        try {
            return this.paperStudentDAO.find(hql, map, numberOfPage, countOfPage);
        } catch (PaperManagerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PaperStudent findById(int id) {
        try {
            return (PaperStudent) this.paperStudentDAO.findById(
                    "net.dqsy.papermg.sysmanager.po.PaperStudent", id);
        } catch (PaperManagerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        try {
            return this.paperStudentDAO.findByProperty("PaperStudent", property,
                    value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, int value, int numberOfPage, int countOfPage) {
        try {
            return this.paperStudentDAO.findByProperty("PaperStudent", property,
                    value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        try {
            return this.paperStudentDAO.findAll("PaperStudent", numberOfPage,
                    countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean del(Object o) {
        try {
            this.paperStudentDAO.delete((PaperStudent) o);
        } catch (PaperManagerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PaperStudent findByNumber(String number) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("number", number);
            String hql = "from PaperStudent where studentNumber = :number";

            List list = this.paperStudentDAO.find(
                    hql, map, 1, 1)
                    .getList();
            if (list.size() == 1)
                return (PaperStudent) list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public List findStudentMajor(String teacherUnits) {
        try {
            return this.paperStudentDAO
                    .find("select new net.dqsy.papermg.sysmanager.vo.StudentMajorVO(studentMajor) from PaperStudent where studentFaculty = '" +
                            teacherUnits + "' group by studentMajor", null, 1, 9999)
                    .getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}