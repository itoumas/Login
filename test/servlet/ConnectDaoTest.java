package servlet;

import static org.junit.Assert.*;

import org.junit.Test;

import DAO.ConnectDao;

public class ConnectDaoTest {

	ConnectDao connectDao = new ConnectDao();

	@Test
	public void testLogin() throws Exception {

		assertEquals("ログイン成功", "itou", connectDao.login("itou", "itou"));
		assertEquals("ログイン不可能", "ログインできません", connectDao.login("aaa", "aaa"));
	}

	@Test
	public void testExecute() throws Exception {

		assertEquals("ログイン成功", "itou", connectDao.login("itou", "itou"));
		assertEquals("ログイン不可能", "ログインできません", connectDao.login("aaa", "aaa"));
	}
}
