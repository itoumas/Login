package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import DAO.ConnectDao;


public class ConnectDaoMock extends ConnectDao {

	//Connectionオブジェクトを格納
	Connection con = null;

	/**
	 * MySQLに接続します。
	 *
	 * @throws Exception
	 */
	public void connect () throws Exception {

		//MySQLにアクセスするためのユーザ名、パスワード、URL
		String user = "systena";
		String pass = "systena";
		String url  = "jdbc:mysql://10.10.14.228:3306/testDB?useUnicode=true&characterEncoding=UTF-8";
		String sql  = "delete from testTBL";

		try {
			//JDBCをロード
			Class.forName("com.mysql.jdbc.Driver");

			//MySQLへアクセス
			con = DriverManager.getConnection(url, user, pass);

			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
	}
}
