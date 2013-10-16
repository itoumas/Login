package Action;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

public class InsertActionTest {

	Connection con = null;
	Statement stmt;

	@Before
	public void setUp() {

		//テストデータ作成用のSQLです。
		String deleteQuery = "delete from USER";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(LoginActionTest.URL, LoginActionTest.USER, LoginActionTest.PASS);
			stmt = con.createStatement();

			//テスト用テーブルのデータをすべて削除します。
			stmt.executeUpdate(deleteQuery);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testInsert() throws Exception {

		InsertAction insertAction = new InsertAction(LoginActionTest.URL);

		assertEquals("データの追加をする", "完了！！", insertAction.edit("", "itou_id", "itou_name", "itou_pass"));
		ResultSet rs = stmt.executeQuery("select * from USER");
		rs.next();
		assertEquals("InsertしたデータがDBに存在するか", "itou_id",   rs.getString("USER_ID"));
		assertEquals("InsertしたデータがDBに存在するか", "itou_name", rs.getString("NAME"));
		assertEquals("InsertしたデータがDBに存在するか", "itou_pass", rs.getString("PASSWORD"));

		assertEquals("データが登録されていないか", false,  rs.next());

/*
		assertEquals("データの追加をする(すでに同じデータがある)", "追加できませんでした", insertAction.edit("", "itou_id", "itou_name", "itou_pass"));
		rs = stmt.executeQuery("select count('') as cnt from USER");
		rs.next();
		assertEquals("データが登録されていないか", "1",  rs.getString("cnt"));
 */

	}
}
