package net.dqsy.papermg.sysmanager.action;

import net.dqsy.papermg.sysmanager.po.PaperStudent;
import net.dqsy.papermg.sysmanager.service.PaperStudentService;
import net.dqsy.papermg.util.PagingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/student")
public class PaperStudentController {

    @Autowired
    PaperStudentService paperStudentService;

    @RequestMapping("/manage")
    public String studentMange() {
        return "/admin/studentManage";

    }

    @RequestMapping("save")
    @ResponseBody
    public boolean save(PaperStudent paperStudent) {
        return this.paperStudentService.save(paperStudent);
    }

    @RequestMapping("update")
    @ResponseBody
    public boolean update(PaperStudent paperStudent, HttpSession session) {
        PaperStudent paperStudentS = (PaperStudent) session
                .getAttribute("student");

        if (paperStudentS != null) {
            if (paperStudentS.getStudentId().equals(
                    paperStudent.getStudentId())) {
                session.setAttribute("student", paperStudent);
            }
        }

        PaperStudent oldStudent = (PaperStudent) this.paperStudentService.findById(paperStudent.getStudentId());

        oldStudent.setStudentName(paperStudent.getStudentName());
        oldStudent.setStudentSex(paperStudent.getStudentSex());
        oldStudent.setStudentAge(paperStudent.getStudentAge());
        oldStudent.setStudentNumber(paperStudent.getStudentNumber());
        oldStudent.setStudentGrade(paperStudent.getStudentGrade());
        oldStudent.setStudentFaculty(paperStudent.getStudentFaculty());
        oldStudent.setStudentMajor(paperStudent.getStudentMajor());
        oldStudent.setStudentDirection(paperStudent.getStudentDirection());
        return this.paperStudentService.update(oldStudent);
    }
//
//    public String importStudent(String path) {
//        return this.paperStudentService.importStudent(path);
//    }
    @RequestMapping("/updateStudentFlag")
    @ResponseBody
    public boolean updateStudentFlag(int StudentId, int flag) {
        PaperStudent paperStudent = (PaperStudent) this.paperStudentService.findById(StudentId);
        return this.paperStudentService.updateStudentFlag(paperStudent, flag);
    }
//
//    public boolean resetStudentPassword(int studentId) {
//        return this.paperStudentService.resetStudentPassword(studentId);
//    }
//
//    public PagingSupport find(String hql, int numberOfPage, int countOfPage) {
//        return this.paperStudentService.find(hql, numberOfPage, countOfPage);
//    }

    @RequestMapping("findById")
    @ResponseBody
    public PaperStudent findById(int id) {
        return (PaperStudent) this.paperStudentService.findById(id);
    }
//
//    public List findStudentMajor(String teacherUnits) {
//        return this.paperStudentService.findStudentMajor(teacherUnits);
//    }
//
    @RequestMapping("findByNumber")
    @ResponseBody
    public PaperStudent findByNumber(String number) {
        return this.paperStudentService.findByNumber(number);
    }
//
//    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
//        return this.paperStudentService.findByProperty(property, value,
//                numberOfPage, countOfPage);
//    }
//
    @RequestMapping("findAll")
    @ResponseBody
    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        return this.paperStudentService.findAll(numberOfPage, countOfPage);
    }
//
//    public boolean del(PaperStudent paperStudent) {
//        return this.paperStudentService.del(paperStudent);
//    }

}