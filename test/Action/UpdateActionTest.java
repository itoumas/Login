package Action;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

public class UpdateActionTest {

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

			//テストデータの投入します。
			stmt.executeUpdate("insert into USER values(1, 'itou', 'itou', 'itou')");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() throws Exception {

		UpdateAction updateAction = new UpdateAction(LoginActionTest.URL);

		assertEquals("データの更新する", "完了！！", updateAction.edit("1", "itou_id", "itou_name", "itou_pass"));

		assertEquals("データの更新する(存在しないIDの更新)", "更新できませんでした", updateAction.edit("2", "systena", "systena", "systena"));
		assertEquals("データの更新する(IDの未指定)", "更新できませんでした", updateAction.edit("", "systena", "systena", "systena"));

		ResultSet rs = stmt.executeQuery("select * from USER where ID = 1");
		rs.next();

		assertEquals("UpdateしたデータがDBに存在するか", "itou_id",   rs.getString("USER_ID"));
		assertEquals("UpdateしたデータがDBに存在するか", "itou_name", rs.getString("NAME"));
		assertEquals("UpdateしたデータがDBに存在するか", "itou_pass", rs.getString("PASSWORD"));
	}
}
