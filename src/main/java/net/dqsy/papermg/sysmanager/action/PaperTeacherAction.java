package net.dqsy.papermg.sysmanager.action;

import net.dqsy.papermg.sysmanager.po.PaperTeacher;
import net.dqsy.papermg.sysmanager.service.PaperTeacherService;
import net.dqsy.papermg.util.PagingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Scope("prototype")
public class PaperTeacherAction {

    @Autowired
    PaperTeacherService paperTeacherService;

    public boolean save(PaperTeacher paperTeacher) {
        return this.paperTeacherService.save(paperTeacher);
    }

    public boolean update(PaperTeacher paperTeacher, HttpSession session) {
        PaperTeacher paperTeacherS = (PaperTeacher) session
                .getAttribute("teacher");

        if (paperTeacherS != null) {
            if (paperTeacherS.getTeacherId().equals(
                    paperTeacher.getTeacherId())) {
                session.setAttribute("teacher", paperTeacher);
            }
        }
        return this.paperTeacherService.update(paperTeacher);
    }

    public PagingSupport find(String hql, int numberOfPage, int countOfPage) {
        return this.paperTeacherService.find(hql, numberOfPage, countOfPage);
    }

    public PaperTeacher findById(int id) {
        return (PaperTeacher) this.paperTeacherService.findById(id);
    }

    public PaperTeacher findByNumber(String number) {
        return this.paperTeacherService.findByNumber(number);
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        return this.paperTeacherService.findByProperty(property, value,
                numberOfPage, countOfPage);
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        return this.paperTeacherService.findAll(numberOfPage, countOfPage);
    }

    public List findTeacherName(String teacherUnits) {
        return this.paperTeacherService.findTeacherName(teacherUnits);
    }

    public boolean updateTeacherFlag(PaperTeacher paperTeacher, int flag) {
        return this.paperTeacherService.updateTeacherFlag(paperTeacher, flag);
    }

    public void setPaperTeacherService(PaperTeacherService paperTeacherService) {
        this.paperTeacherService = paperTeacherService;
    }
}