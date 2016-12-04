package net.dqsy.papermg.sysmanager.action;

import net.dqsy.papermg.sysmanager.po.PaperStudent;
import net.dqsy.papermg.sysmanager.service.PaperStudentService;
import net.dqsy.papermg.util.PagingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Scope("prototype")
public class PaperStudentAction {

    @Autowired
    PaperStudentService paperStudentService;

    public boolean save(PaperStudent paperStudent) {
        return this.paperStudentService.save(paperStudent);
    }

    public boolean update(PaperStudent paperStudent, HttpSession session) {
        PaperStudent paperStudentS = (PaperStudent) session
                .getAttribute("student");

        if (paperStudentS != null) {
            if (paperStudentS.getStudentId().equals(
                    paperStudent.getStudentId())) {
                session.setAttribute("student", paperStudent);
            }
        }
        return this.paperStudentService.update(paperStudent);
    }

    public String importStudent(String path) {
        return this.paperStudentService.importStudent(path);
    }

    public boolean updateStudentFlag(PaperStudent paperStudent, int flag) {
        return this.paperStudentService.updateStudentFlag(paperStudent, flag);
    }

    public boolean resetStudentPassword(int studentId) {
        return this.paperStudentService.resetStudentPassword(studentId);
    }

    public PagingSupport find(String hql, int numberOfPage, int countOfPage) {
        return this.paperStudentService.find(hql, numberOfPage, countOfPage);
    }

    public PaperStudent findById(int id) {
        return (PaperStudent) this.paperStudentService.findById(id);
    }

    public List findStudentMajor(String teacherUnits) {
        return this.paperStudentService.findStudentMajor(teacherUnits);
    }

    public PaperStudent findByNumber(String number) {
        return this.paperStudentService.findByNumber(number);
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        return this.paperStudentService.findByProperty(property, value,
                numberOfPage, countOfPage);
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        return this.paperStudentService.findAll(numberOfPage, countOfPage);
    }

    public boolean del(PaperStudent paperStudent) {
        return this.paperStudentService.del(paperStudent);
    }

}