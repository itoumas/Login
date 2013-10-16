package Action;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

public class DeleteActionTest {

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
			stmt.executeUpdate("insert into USER values(1, 'itou_id', 'itou_name', 'itou_pass')");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() throws Exception {

		DeleteAction deleteAction = new DeleteAction(LoginActionTest.URL);

		assertEquals("データの削除をする", "完了！！", deleteAction.edit("1", "", "", ""));
		assertEquals("データの削除をする(IDを未指定)", "削除できませんでした", deleteAction.edit("", "", "", ""));
		assertEquals("データの削除をする(存在しないIDを指定)", "削除できませんでした", deleteAction.edit("2", "", "", ""));

		ResultSet rs = stmt.executeQuery("select * from USER where ID = 1");

		assertFalse("テストデータが削除されテーブルが空かの確認", rs.next());
	}
}
