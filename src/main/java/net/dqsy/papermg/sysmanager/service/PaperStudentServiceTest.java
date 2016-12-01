package net.dqsy.papermg.sysmanager.service;

import net.dqsy.papermg.sysmanager.dao.PaperUserDAO;
import net.dqsy.papermg.sysmanager.po.PaperStudent;
import net.dqsy.papermg.sysmanager.po.PaperUser;
import net.dqsy.papermg.sysmanager.util.POIReadExcel_student;
import java.io.File;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.List;

import net.dqsy.papermg.sysmanager.po.PaperStudent;
import net.dqsy.papermg.sysmanager.util.POIReadExcel_student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class PaperStudentServiceTest
{
  public PaperStudentService paperStudentService;
  public PaperUserDAO paperUserDAO;
  POIReadExcel_student pStudent = new POIReadExcel_student();
  PaperStudent paperStudent = new PaperStudent();
  PaperUser paperUser = new PaperUser();

  @Before
  public void init() {
    ApplicationContext ac = new FileSystemXmlApplicationContext(
      "src/applicationContext.xml");
    this.paperStudentService = 
      ((PaperStudentService)ac
      .getBean("paperStudentService"));
    this.paperUserDAO = ((PaperUserDAO)ac.getBean("paperUserDAO"));
  }
  @Test
  public void testSave() {
    List list = null;
    try {
      list = this.pStudent.readExcel("e:" + File.separator + "student.xlsx");
    } catch (ParseException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < list.size(); i++) {
      PaperStudent paperStudent = (PaperStudent)list.get(i);
      System.out.println(paperStudent.getStudentNumber());
      System.out.println(paperStudent.getStudentName());
      System.out.println(paperStudent.getStudentSex());
      System.out.println(paperStudent.getStudentAge());
      System.out.println(paperStudent.getStudentPhone());
      System.out.println(paperStudent.getStudentGrade());
      System.out.println(paperStudent.getStudentFaculty());
      System.out.println(paperStudent.getStudentMajor());
      System.out.println(paperStudent.getStudentDirection());

      this.paperStudentService.save(
        new PaperStudent(paperStudent
        .getStudentNumber(), paperStudent.getStudentName(), 
        paperStudent.getStudentSex(), paperStudent
        .getStudentGrade(), paperStudent
        .getStudentFaculty(), paperStudent
        .getStudentMajor(), paperStudent
        .getStudentDirection()));
    }
  }

  public void testUpdate()
  {
    Assert.fail("Not yet implemented");
  }

  public void testFind()
  {
    Assert.fail("Not yet implemented");
  }

  public void testFindById()
  {
    Assert.fail("Not yet implemented");
  }

  public void testFindByProperty()
  {
    Assert.fail("Not yet implemented");
  }

  public void testFindAll()
  {
    Assert.fail("Not yet implemented");
  }

  public void testDelete()
  {
    Assert.fail("Not yet implemented");
  }
}