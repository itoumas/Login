package DAO;

import static org.junit.Assert.*;

import org.junit.Test;

import Action.LoginActionTest;
import DAO.ConnectDao;

public class ConnectDaoTest {

	ConnectDao connectDao = new ConnectDao(LoginActionTest.URL);

	@Test
	public void testLogin() throws Exception {

		assertEquals("ログイン成功", "itou", connectDao.login("itou", "itou"));
		assertEquals("ログイン不可能", "ログインできません", connectDao.login("ccc", "ccc"));
	}
}
