package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import Action.EditAction;

public class UpdateAction extends EditAction {

	//データの編集が失敗した場合のメッセージ
	static final String UPDATE_MESSAGE = "更新できませんでした";

	static final String OK_MESSAGE = "完了！！";

	//Connectionオブジェクトを格納
	Connection con = null;

	public String edit (String id, String user_id, String name, String password) throws Exception {

		connect();
		PreparedStatement pstmt = null;

		try {
			String query = "update USER set USER_ID = ? , NAME = ? , PASSWORD = ? where ID = ?";

			pstmt = con.prepareStatement(query);

			//パラメータセット
			pstmt.setString(1, user_id);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			pstmt.setString(4, id);

			int rs = pstmt.executeUpdate();

			pstmt.close();
			close();

			//指定されたIDにデータがなかった場合は更新失敗のメッセージを送る
			if (rs != 0) {

				return OK_MESSAGE;

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			pstmt.close();
			close();
		}

		return UPDATE_MESSAGE;
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
