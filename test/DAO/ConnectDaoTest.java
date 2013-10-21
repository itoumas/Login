package DAO;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import Action.LoginActionTest;
import DAO.ConnectDao;

public class ConnectDaoTest {

	Connection con = null;

	//テスト用URLです。
	public final static String URL = "jdbc:mysql://localhost:3306/testDB?useUnicode=true&characterEncoding=UTF-8";
	public final static String USER = "root";
	public final static String PASS = "";

	@Before
	public void setUp() {

		//テストデータ作成用のSQLです。
		String deleteQuery = "delete from USER";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = con.createStatement();

			//テスト用テーブルのデータをすべて削除します。
			stmt.executeUpdate(deleteQuery);

			//テストデータの投入します。
			stmt.executeUpdate("insert into USER(USER_ID, NAME, PASSWORD) values('itou_id', 'itou_name', 'itou_pass')");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testLogin() throws Exception {

		ConnectDao connectDao = new ConnectDao(LoginActionTest.URL);

		assertEquals("ログイン成功", "itou_name", connectDao.login("itou_id", "itou_pass"));
		assertEquals("ログイン不可能", "ログインできません", connectDao.login("aaa", "aaa"));
	}
}
