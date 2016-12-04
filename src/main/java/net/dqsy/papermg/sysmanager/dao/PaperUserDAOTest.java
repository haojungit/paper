package net.dqsy.papermg.sysmanager.dao;

import net.dqsy.papermg.sysmanager.po.PaperUser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class PaperUserDAOTest {
    @Autowired
    PaperUserDAO paperUserDAO;

    @Before
    public void init() {
        ApplicationContext ac = new FileSystemXmlApplicationContext("src/applicationContext.xml");
        this.paperUserDAO = ((PaperUserDAO) ac.getBean("paperUserDAO"));
    }

    @Test
    public void testSave() {
        PaperUser paperUser = new PaperUser("X", "X");
        this.paperUserDAO.save(paperUser);
    }
}