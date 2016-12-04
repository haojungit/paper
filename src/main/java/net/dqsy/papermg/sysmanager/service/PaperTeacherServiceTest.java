package net.dqsy.papermg.sysmanager.service;

import net.dqsy.papermg.sysmanager.po.PaperTeacher;
import net.dqsy.papermg.sysmanager.util.POIReadExcel_teacher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.File;
import java.text.ParseException;
import java.util.List;

public class PaperTeacherServiceTest {
    @Autowired
    public PaperTeacherService paperTeacherService;
    POIReadExcel_teacher pTeacher = new POIReadExcel_teacher();
    PaperTeacher paperTeacher = new PaperTeacher();

    @Before
    public void init() {
        ApplicationContext ac = new FileSystemXmlApplicationContext(
                "src/applicationContext.xml");
        this.paperTeacherService =
                ((PaperTeacherService) ac
                        .getBean("paperTeacherService"));
    }

    @Test
    public void testSave() {
        List list = null;
        try {
            list = this.pTeacher.readExcel("e:" + File.separator + "teacher.xlsx");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            this.paperTeacher = ((PaperTeacher) list.get(i));
            this.paperTeacherService.save(
                    new PaperTeacher(this.paperTeacher
                            .getTeacherNumber(), this.paperTeacher.getTeacherName(),
                            this.paperTeacher.getTeacherSex(), this.paperTeacher.getTeacherAge(),
                            this.paperTeacher.getTeacherPhone(), this.paperTeacher
                            .getTeacherUnits(), this.paperTeacher.getTeacherMajor(),
                            this.paperTeacher.getTeacherEducation(), this.paperTeacher
                            .getTeacherJobTitle(), this.paperTeacher
                            .getTeacherDirection()));
        }
    }

    public void testUpdate() {
    }

    public void testFind() {
        Assert.fail("Not yet implemented");
    }

    public void testFindById() {
        System.out.println(this.paperTeacherService.findById(1));
    }

    public void testFindByProperty() {
        Assert.fail("Not yet implemented");
    }

    public void testFindAll() {
        this.paperTeacherService.findAll(1, 10);
    }

    public void testDelete() {
        Assert.fail("Not yet implemented");
    }
}