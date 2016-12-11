package net.dqsy.papermg.papermanager.service.impl;

import net.dqsy.papermg.papermanager.dao.PaperTitleDAO;
import net.dqsy.papermg.papermanager.po.PaperTitle;
import net.dqsy.papermg.papermanager.po.PaperTitleState;
import net.dqsy.papermg.papermanager.service.PaperTitleService;
import net.dqsy.papermg.papermanager.service.PaperTitleStateService;
import net.dqsy.papermg.sysmanager.po.PaperStudent;
import net.dqsy.papermg.sysmanager.po.PaperTeacher;
import net.dqsy.papermg.sysmanager.service.PaperStudentService;
import net.dqsy.papermg.sysmanager.service.PaperTeacherService;
import net.dqsy.papermg.util.PagingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PaperTitleServiceImpl
        implements PaperTitleService
{
    @Autowired
    private PaperTitleDAO paperTitleDAO;
    @Autowired
    private PaperTitleStateService paperTitleStateService;
    @Autowired
    private PaperStudentService paperStudentService;
    @Autowired
    private PaperTeacherService paperTeacherService;

    public String save(PaperTitle paperTitle) {
        try {
            int flag = findByProperty("paperTitleKeywords",
                    paperTitle.getPaperTitleKeywords(), 1, 1).getList().size();
            if (flag != 0)
                return "检测到关键字相同,请检查后重新填写！";
            flag = findByProperty("paperTitleName",
                    paperTitle.getPaperTitleName(), 1, 1).getList().size();
            if (flag != 0)
                return "检测到相同题目与名称,请不要重复提交！";
            PaperTitleState paperTitleState = this.paperTitleStateService
                    .findByState(1);
            paperTitle.setPaperTitleState(paperTitleState);
            this.paperTitleDAO.save(paperTitle);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统异常,请联系系统管理员!";
        }
        return "课题申报成功！";
    }

    public String saveStudentDTP(int studentId, int teacherId, PaperTitle paperTitle) {
        try {
            int flag = findByProperty("paperStudent.studentId", studentId, 1, 1)
                    .getList().size();
            if (flag != 0)
                return "非法操作,你已经选过题目了！";
            flag = findByProperty("paperTitleName",
                    paperTitle.getPaperTitleName(), 1, 1).getList().size();
            if (flag != 0)
                return "检测到相同题目与名称,请更换后重试！";
            PaperStudent paperStudent = (PaperStudent) this.paperStudentService
                    .findById(studentId);
            PaperTeacher paperTeacher = (PaperTeacher) this.paperTeacherService
                    .findById(teacherId);
            PaperTitleState paperTitleState = this.paperTitleStateService
                    .findByState(0);
            paperTitle.setPaperStudent(paperStudent);
            paperTitle.setPaperTeacher(paperTeacher);
            paperTitle.setPaperTitleState(paperTitleState);
            this.paperTitleDAO.save(paperTitle);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统异常,请联系系统管理员!";
        }
        return "自拟课题申报成功！";
    }

    public boolean save(Object o) {
        return false;
    }

    public boolean update(Object o) {
        try {
            this.paperTitleDAO.update((PaperTitle) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PagingSupport find(String hql, HashMap<String, Object> map, int numberOfPage, int countOfPage) {
        try {
            return this.paperTitleDAO.find(hql, map, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object findById(int id) {
        try {
            return this.paperTitleDAO.findById(
                    "com.pactera.papermg.papermanager.po.PaperTitle", id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        try {
            return this.paperTitleDAO.findByProperty("PaperTitle", property, value,
                    numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, int value, int numberOfPage, int countOfPage) {
        try {
            return this.paperTitleDAO.findByProperty("PaperTitle", property, value,
                    numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List findTeacherName() {
        try {
            return this.paperTitleDAO
                    .find("select new com.pactera.papermg.papermanager.vo.TeacherNameVO(paperTeacher.teacherId,paperTeacher.teacherName) from PaperTitle where paperTitleState.paperTitleState = 1 group by paperTeacher.teacherName",
                            null, 1, 9999).getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByTitleState(int state, int numberOfPage, int countOfPage) {
        try {
            return this.paperTitleDAO.find(
                    "from PaperTitle where paperTitleState.paperTitleState = " +
                            state, null, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByStudentMajor(String studentMajor, int numberOfPage, int countOfPage) {
        try {
            return this.paperTitleDAO
                    .find("from PaperTitle where paperTitleState.paperTitleState = 2 and paperTitleLimitMajor = '" +
                            studentMajor + "'", null,  numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List findTitleByStudentId(int paperStudentId, int state) {
        try {
            return this.paperTitleDAO
                    .find("select new com.pactera.papermg.papermanager.vo.PaperTitleVO(paperTitleId,paperTitleState.paperTitleState,paperTitleName,paperTeacher.teacherName) from PaperTitle where paperTitleState.paperTitleState >" +
                            state +
                            " and paperStudent.studentId = " +
                            paperStudentId, null, 1, 99).getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List findTitleByTeacherId(int paperTeacherId, int state) {
        try {
            return this.paperTitleDAO
                    .find("select new com.pactera.papermg.papermanager.vo.PaperTitleVO(paperTitleId,paperTitleName,paperTitleState.paperTitleState) from PaperTitle where paperTitleState.paperTitleState > " +
                            state +
                            " and paperTeacher.teacherId = " +
                            paperTeacherId, null, 1, 99).getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int findPaperTitState(int titleId) {
        try {
            return
                    ((Integer) this.paperTitleDAO
                            .find("select paperTitleState.paperTitleState from PaperTitle where  paperTitleId = " +
                                    titleId, null, 1, 1).getList().get(0)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List findStudentInfoByTitleId(int titleId) {
        try {
            return this.paperTitleDAO
                    .find("select new com.pactera.papermg.sysmanager.po.PaperStudent(paperStudent.studentId,paperStudent.studentName,paperStudent.studentSex,paperStudent.studentFaculty,paperStudent.studentMajor,paperStudent.studentDirection,paperStudent.studentGrade,paperStudent.studentAge,paperStudent.studentPhone,paperStudent.studentNumber) from PaperTitle where paperTitleId = " +
                            titleId, null, 1, 1).getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByTeacherId(int paperTeacherId, int numberOfPage, int countOfPage) {
        try {
            return this.paperTitleDAO.findByProperty("PaperTitle",
                    "paperTeacher.teacherId", paperTeacherId, numberOfPage,
                    countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        try {
            return this.paperTitleDAO.findAll("PaperTitle", numberOfPage,
                    countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean del(Object o) {
        try {
            this.paperTitleDAO.delete(o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateState(int paperTitleId, int state) {
        try {
            PaperTitle paperTitle = (PaperTitle) findById(paperTitleId);

            PaperTitleState paperTitleState = this.paperTitleStateService
                    .findByState(state);
            paperTitle.setPaperTitleState(paperTitleState);

            if (state == 2)
                paperTitle.setPaperStudent(null);
            this.paperTitleDAO.update(paperTitle);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStateByBatch(List<Integer> paperTitleIds, int state) {
        for (int i = 0; i < paperTitleIds.size(); i++) {
            if (!updateState(((Integer) paperTitleIds.get(i)).intValue(), state))
                return false;
        }
        return true;
    }

    public boolean approvePaperTitle(PaperTitle paperTitle, int state) {
        try {
            PaperTitleState paperTitleState = this.paperTitleStateService
                    .findByState(state);

            paperTitle.setPaperTitleState(paperTitleState);

            this.paperTitleDAO.update(paperTitle);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String chooseTitle(int paperTitleId, PaperStudent paperStudent) {
        synchronized (PaperTitleServiceImpl.class) {
            try {
                PaperTitle paperTitle = (PaperTitle) findById(paperTitleId);

                if (paperTitle.getPaperStudent() != null) {
                    int nowStudentId = paperTitle.getPaperStudent()
                            .getStudentId().intValue();
                    int newStudentId = paperStudent.getStudentId().intValue();

                    if (nowStudentId == newStudentId)
                        return "你已经选择过了此课题，请不要重复提交!";
                    return "该课题已经被其他同学选走,请重新选题!";
                }
                int flag = findByProperty("paperStudent.studentId",
                        paperStudent.getStudentId().intValue(), 1, 1).getList().size();

                if (flag == 0) {
                    PaperTitleState paperTitleState = this.paperTitleStateService
                            .findByState(4);
                    paperTitle.setPaperStudent(paperStudent);
                    paperTitle.setPaperTitleState(paperTitleState);
                    this.paperTitleDAO.update(paperTitle);
                } else {
                    return "你已经选择过一个课题,请不要重复选择!";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "系统异常,选题失败!";
            }

            return "恭喜,选题成功!";
        }
    }

    public List<String> findFaculty() {
        String hql = "from PaperStudent group by studentFaculty";

        List list = this.paperTitleDAO.find(hql, null, 1, 999).getList();
        return list;
    }

    public List findMajorByFaculty(String faculty) {
        if ((faculty == null) || ("".equals(faculty))) {
            return null;
        }
        String hql = "from PaperStudent where studentFaculty='" + faculty +
                "' group by studentMajor";

        List list = this.paperTitleDAO.find(hql, null, 1, 999).getList();
        System.out.println(((PaperStudent) list.get(0)).getStudentMajor());
        return list;
    }

    public List findGrade() {
        try {
            String hql = "from PaperStudent group by studentGrade";

            List list = this.paperTitleDAO.find(hql, null, 1, 999).getList();
            System.out.println(((PaperStudent) list.get(0)).getStudentMajor());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findPageByFMG(String faculty, String major, String grade, int numberOfPage, int countOfPage) {
        String hql = "PaperTitle t where t.paperStudent.studentFaculty='" +
                faculty + "' and t.paperStudent.studentMajor='" + major +
                "' and t.paperStudent.studentGrade='" + grade + "'";
        try {
            PagingSupport ps = this.paperTitleDAO.findAll(hql, numberOfPage,
                    countOfPage);
            System.out.println("通过3种属性共找到数据条数：" + ps.getList().size());
            return ps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}