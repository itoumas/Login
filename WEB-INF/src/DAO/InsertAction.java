package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import Action.EditAction;

public class InsertAction extends EditAction {

	static final String OK_MESSAGE = "完了！！";

	//Connectionオブジェクトを格納
	Connection con = null;

	public String edit (String id, String user_id, String name, String password) throws Exception {

		String query = "insert into USER(USER_ID, NAME, PASSWORD) values (?, ?, ?)";

		connect();

		PreparedStatement pstmt = con.prepareStatement(query);

		//パラメータセット
		pstmt.setString(1, user_id);
		pstmt.setString(2, name);
		pstmt.setString(3, password);

		pstmt.executeUpdate();

		pstmt.close();
		close();

		pstmt.close();
		close();

		return OK_MESSAGE;
	}

	//DBに接続
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

	//DBから切断
	public void close () {

		try {
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}


}
