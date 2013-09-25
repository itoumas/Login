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

	//データの編集が失敗した場合のメッセージ
	static final String DELTE_MESSAGE = "削除できませんでした";
	static final String UPDATE_MESSAGE = "更新できませんでした";

	static final String OK_MESSAGE = "完了！！";

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

			PreparedStatement pstmt = this.con.prepareStatement(query);

			//パラメータセット
			pstmt.setString(1, user_id);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				return rs.getString("NAME");
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			close();
		}

		return NOT_LOGIN;
	}

	public String delete (String id) throws Exception {

		String query = "delete from USER where ID = ?";

		connect();

		//PreparedStatementで事前にSQLをコンパイルする
		PreparedStatement pstmt = con.prepareStatement(query);

		//パラメータセット
		pstmt.setString(1, id);

		int rs = pstmt.executeUpdate();

		pstmt.close();
		close();

		//指定されたIDにデータがあった場合、正常に処理が完了したことを伝える
		if(rs != 0){

			return OK_MESSAGE;

		} else {

			return DELTE_MESSAGE;
		}
	}

	public String insert (String user_id, String name, String password) throws Exception {

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

		return OK_MESSAGE;
	}

	public String update (String id, String user_id, String name, String password) throws Exception {

		connect();

		String query = "update USER set USER_ID = ? , NAME = ? , PASSWORD = ? where ID = ?";

		PreparedStatement pstmt = con.prepareStatement(query);

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

		} else {

			return DELTE_MESSAGE;
		}
	}
}


