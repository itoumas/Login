package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectDao {

	//ログインできなかった場合の戻り値
	public static final String NOT_LOGIN = "notLogin";

	public Connection Connect () {

		//MySQLにアクセスするためのユーザ名、パスワード、URL
		String user = "systena";
		String pass = "systena";
		String url = "jdbc:mysql://10.10.14.117:3306/systenaDB?useUnicode=true&characterEncoding=UTF-8";

		Connection con = null;

		 final Logger logger = Logger.getLogger(url);
		logger.info("DAOクラス");

		try {
			//JDBCをロード
			Class.forName("com.mysql.jdbc.Driver");

			//MySQLへアクセス
			con = DriverManager.getConnection(url, user, pass);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return con;
	}

	public void Close (Connection con) {

		try {
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public String login (Connection con, String user_id, String password) {


		//プレースホルダーを指定してSQLを作成
		String query = "select NAME from USER where USER_ID = ? and PASSWORD = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);

			//パラメータセット
			pstmt.setString(1, user_id);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				return rs.getString("NAME");

			} else {

				return NOT_LOGIN;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return NOT_LOGIN;

	}

}
