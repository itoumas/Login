package servlet;

import static org.junit.Assert.*;

import org.junit.Test;

import DAO.ConnectDao;

public class ConnectDaoTest {

	String url  = "jdbc:mysql://10.10.14.228:3306/testDB?useUnicode=true&characterEncoding=UTF-8";
	ConnectDao connectDao = new ConnectDao(url);

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
