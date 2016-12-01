package net.dqsy.papermg.papermanager.service;

import net.dqsy.papermg.papermanager.po.PaperTitle;
import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class PaperTitleServiceTest
{
  public PaperTitleService paperTitleService;

  @Before
  public void init()
  {
    ApplicationContext ac = new FileSystemXmlApplicationContext(
      "src/applicationContext.xml");
    this.paperTitleService = ((PaperTitleService)ac.getBean("paperTitleService"));
  }

  @Test
  public void testSave() {
    PaperTitle paperTitle = (PaperTitle)this.paperTitleService.findById(1);

    System.out.println(paperTitle.getPaperTitleName());
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