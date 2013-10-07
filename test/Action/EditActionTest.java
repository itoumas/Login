package Action;

import static org.junit.Assert.*;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.Test;

import DAO.ConnectDaoMock;

public class EditActionTest extends EditAction {

	Connection con = null;
	String testUrl = "";

	protected void setUp() {

		String user = "systena";
		String pass = "systena";
		String url = "jdbc:mysql://10.10.14.228:3306/hogehoge?useUnicode=true&characterEncoding=UTF-8";
		String deleteQuery = "delete from testtable)";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			Statement stmt = con.createStatement();
			stmt.executeQuery(deleteQuery);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testInsert() throws Exception {

		InsertAction insertAction = new InsertAction();

//		assertEquals("データの追加をする(すでに同じデータがある)", "追加できませんでした", insertAction.edit("", "itou", "itou", "itou"));
//		assertEquals("データの追加をする(必須項目が未入力)", "追加できませんでした", insertAction.edit("", "", "itou2", "itou2"));
	}

	@Test
	public void testDelete() throws Exception {

		DeleteAction deleteAction = new DeleteAction();

		assertEquals("データの削除をする(IDを未指定)", "削除できませんでした", deleteAction.edit("", "", "", ""));
		assertEquals("データの削除をする(存在しないIDを指定)", "削除できませんでした", deleteAction.edit("4", "", "", ""));
	}

	@Test
	public void testUpdate() throws Exception {

		UpdateAction updateAction = new UpdateAction();

		assertEquals("データの更新する", "完了！！", updateAction.edit("3", "systena", "systena", "systena"));
		assertEquals("データの更新する(存在しないIDの更新)", "更新できませんでした", updateAction.edit("4", "systena", "systena", "systena"));
	}

	protected void tearDown() {

	}

}
