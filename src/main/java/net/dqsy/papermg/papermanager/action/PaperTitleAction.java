package net.dqsy.papermg.papermanager.action;

import net.dqsy.papermg.papermanager.po.PaperTitle;
import net.dqsy.papermg.papermanager.service.PaperTitleService;
import net.dqsy.papermg.sysmanager.po.PaperStudent;
import net.dqsy.papermg.sysmanager.po.PaperTeacher;
import net.dqsy.papermg.util.PagingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Scope("prototype")
public class PaperTitleAction {

    @Autowired
    private PaperTitleService paperTitleService;

    public String save(PaperTitle paperTitle, HttpSession session) {
        PaperTeacher paperTeacher = (PaperTeacher) session
                .getAttribute("teacher");

        if (paperTeacher != null) {
            paperTitle.setPaperTeacher(paperTeacher);
            return this.paperTitleService.save(paperTitle);
        }
        return "登陆超时,请重新登录!";
    }

    public String saveStudentDTP(int studentId, int teacherId, PaperTitle paperTitle) {
        return this.paperTitleService.saveStudentDTP(studentId, teacherId,
                paperTitle);
    }

    public String chooseTitle(int paperTitleId, HttpSession session) {
        PaperStudent paperStudent = (PaperStudent) session
                .getAttribute("student");

        if (paperStudent != null) {
            return this.paperTitleService.chooseTitle(paperTitleId, paperStudent);
        }
        return "登陆超时,请重新登录！";
    }

    public boolean update(PaperTitle paperTitle) {
        return this.paperTitleService.update(paperTitle);
    }

    public boolean approvePaperTitle(PaperTitle paperTitle, int state) {
        return this.paperTitleService.approvePaperTitle(paperTitle, state);
    }

    public boolean updateState(int paperTitleId, int state) {
        return this.paperTitleService.updateState(paperTitleId, state);
    }

    public boolean updateStateByBatch(List<Integer> paperTitleIds, int state) {
        return this.paperTitleService.updateStateByBatch(paperTitleIds, state);
    }

    public PaperTitle findById(int id) {
        return (PaperTitle) this.paperTitleService.findById(id);
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        return this.paperTitleService.findByProperty(property, value, numberOfPage,
                countOfPage);
    }

    public List findTeacherName() {
        return this.paperTitleService.findTeacherName();
    }

    public PagingSupport findByTeacherId(int paperTeacherId, int numberOfPage, int countOfPage) {
        return this.paperTitleService.findByTeacherId(paperTeacherId, numberOfPage,
                countOfPage);
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        return this.paperTitleService.findAll(numberOfPage, countOfPage);
    }

    public PagingSupport findByTitleState(int state, int numberOfPage, int countOfPage) {
        return this.paperTitleService.findByTitleState(state, numberOfPage,
                countOfPage);
    }

    public PagingSupport findByStudentMajor(String studentMajor, int numberOfPage, int countOfPage) {
        return this.paperTitleService.findByStudentMajor(studentMajor, numberOfPage,
                countOfPage);
    }

    public List findTitleByStudentId(int paperStudentId, int state) {
        return this.paperTitleService.findTitleByStudentId(paperStudentId, state);
    }

    public List findTitleByTeacherId(int paperTeacherId, int state) {
        return this.paperTitleService.findTitleByTeacherId(paperTeacherId, state);
    }

    public List findStudentInfoByTitleId(int titleId) {
        return this.paperTitleService.findStudentInfoByTitleId(titleId);
    }

    public List<String> findFaculty() {
        return this.paperTitleService.findFaculty();
    }

    public List findMajorByFaculty(String faculty) {
        return this.paperTitleService.findMajorByFaculty(faculty);
    }

    public List findGrade() {
        return this.paperTitleService.findGrade();
    }

    public PagingSupport findPageByFMG(String faculty, String major, String grage, int numberOfPage, int countOfPage) {
        return this.paperTitleService.findPageByFMG(faculty, major, grage,
                numberOfPage, countOfPage);
    }
}