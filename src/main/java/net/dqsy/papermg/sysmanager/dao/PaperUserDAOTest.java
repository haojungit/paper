package net.dqsy.papermg.sysmanager.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import net.dqsy.papermg.sysmanager.po.PaperUser;

public class PaperUserDAOTest {
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