package Action;

import static org.junit.Assert.*;
<<<<<<< HEAD

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Before;
=======
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637
import org.junit.Test;

public class LoginActionTest extends LoginAction {

<<<<<<< HEAD
	Connection con = null;

	//テスト用URLです。
	public final static String URL = "jdbc:mysql://192.168.1.105:3306/hogehoge?useUnicode=true&characterEncoding=UTF-8";
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
			stmt.executeUpdate("insert into USER(USER_ID, NAME, PASSWORD) values('aaa_id', 'aaa_name', 'aaa_pass')");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testUserLogin() throws Exception {

		LoginAction loginAction = new LoginAction(url);

		//IDとパスワードを使用して、正しいユーザネームを取得できるかのテストです。
		assertEquals("IDとパスワードを用いてログイン", "itou_name",loginAction.userLogin("itou_id", "itou_pass"));
		assertEquals("IDとパスワードを用いてログイン", "aaa_name",loginAction.userLogin("aaa_id", "aaa_pass"));

		assertEquals("IDとパスワードを用いてログイン", "ログインできません",loginAction.userLogin("ccc", "ccc"));
		assertEquals("IDとパスワードを用いてログイン", "ログインできません",loginAction.userLogin("ddd", "ddd"));
=======
	@Test
	public void testUserLogin() throws Exception {

		String url = "jdbc:mysql://10.10.14.228:3306/testDB?useUnicode=true&characterEncoding=UTF-8";

		LoginAction loginAction = new LoginAction();
		assertEquals("IDとパスワードを用いてログイン", "itou",loginAction.userLogin("itou", "itou", url));
		assertEquals("IDとパスワードを用いてログイン", "ログインできません",loginAction.userLogin("ccc", "ccc", url));
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637
	}
}
