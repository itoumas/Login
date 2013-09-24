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

	//Connectionオブジェクトを格納
	Connection con = null;

	public void connect () {

		//MySQLにアクセスするためのユーザ名、パスワード、URL
		String user = "systena";
		String pass = "systena";
		String url = "jdbc:mysql://10.10.14.117:3306/systenaDB?useUnicode=true&characterEncoding=UTF-8";

		 final Logger logger = Logger.getLogger("");
		logger.info("DAOクラス");

		try {
			//JDBCをロード
			Class.forName("com.mysql.jdbc.Driver");

			//MySQLへアクセス
			con = DriverManager.getConnection(url, user, pass);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void close () {

		try {
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	//ログインメソッド
	public String login (String user_id, String password) {

		//プレースホルダーを指定してSQLを作成
		String query = "select NAME from USER where USER_ID = ? and PASSWORD = ?";

		final Logger logger = Logger.getLogger("");
		logger.info(user_id);
		logger.info(password);

		try {
			connect();

			PreparedStatement pstmt = con.prepareStatement(query);

			//パラメータセット
			pstmt.setString(1, user_id);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				return rs.getString("NAME");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		close();

		return NOT_LOGIN;
	}

}
