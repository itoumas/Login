package Action;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginActionTest extends LoginAction {

	Connection con = null;
	Statement stmt = null;

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
			stmt = con.createStatement();

			//テスト用テーブルのデータをすべて削除します。
			stmt.executeUpdate(deleteQuery);

			//テストデータの投入します。
			stmt.executeUpdate("insert into USER(USER_ID, NAME, PASSWORD) values('itou_id', 'itou_name', 'itou_pass')");
			stmt.executeUpdate("insert into USER(USER_ID, NAME, PASSWORD) values('aaa_id', 'aaa_name', 'aaa_pass')");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testUserLogin() throws Exception {

		LoginAction loginAction = new LoginAction(URL);

		//IDとパスワードを使用して、正しいユーザネームを取得できるかのテストです。
		assertEquals("IDとパスワードを用いてログイン", "itou_name",loginAction.userLogin("itou_id", "itou_pass"));
		assertEquals("IDとパスワードを用いてログイン", "aaa_name",loginAction.userLogin("aaa_id", "aaa_pass"));

		assertEquals("IDとパスワードを用いてログイン", "ログインできません",loginAction.userLogin("ccc", "ccc"));
		assertEquals("IDとパスワードを用いてログイン", "ログインできません",loginAction.userLogin("ddd", "ddd"));
	}

	@After
	public void teatDown() throws SQLException {

		stmt.close();
		con.close();
	}
}
