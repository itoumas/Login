package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * DB接続と各種DB処理を行う一般クラス。
 *
 * @author itoumas
 */
public class ConnectDao {

	/**
	 * メッセージ。
	 */
	public static final String NOT_LOGIN = "notLogin";
	static final String DELTE_MESSAGE = "削除できませんでした";
	static final String INSERT_MESSAGE = "追加できませんでした";
	static final String UPDATE_MESSAGE = "更新できませんでした";
	static final String OK_MESSAGE = "完了！！";

	//Connectionオブジェクトを格納
	Connection con = null;

	/**
	 * MySQLに接続します。
	 *
	 * @throws Exception
	 */
	public void connect () throws Exception{

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
			throw e;
		}
	}

	/**
	 * MySQLとの接続を切断します。
	 */
	public void close () {

		try {
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * ログイン処理を行います。
	 *
	 * @param user_id
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public String login (String user_id, String password) throws Exception {

		//プレースホルダーを指定してSQLを作成
		String query = "select NAME from USER where USER_ID = ? and PASSWORD = ?";

		PreparedStatement pstmt = null;

		connect();

		try {

			pstmt = this.con.prepareStatement(query);

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

			pstmt.close();
			close();
		}

		return NOT_LOGIN;
	}

	/**
	 * ステートメントを準備します。
	 *
	 * @param id
	 * @param user_id
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception
	 */
	protected PreparedStatement setupPstmt (String id, String user_id, String name, String password) throws Exception {
		return null;
	}

	/**
	 * 処理を実行します。
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String execute (String id, String user_id, String name, String password) throws Exception {

		PreparedStatement pstmt = null;

		connect();

		try{
			pstmt = setupPstmt(id, user_id, name, password);

			int rs = pstmt.executeUpdate();
			//指定されたIDにデータがあった場合、正常に処理が完了したことを伝える
			if(rs != 0){

				return OK_MESSAGE;
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			pstmt.close();
			close();
		}

		return DELTE_MESSAGE;
	}


/*-------------------------------------------------
	main() {
		Delete delete = new Delete();
		delete.execute();
	}


	class Delete extends ConnectDao {
		/**
		 * プリペアドステートメントを準備します。
		 *
		 *
		protected PreparedStatement setupPstmt(String id) throws Exception {
			String query = "delete from USER where ID = ?";
			PreparedStatement pstmt = con.prepareStatement(query);

			//パラメータセット
			pstmt.setString(1, id);
			return pstmt;
		}
	}


	/**
	 * 処理を実行します。
	 *
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 *
	public String execute(String id, String user_id, String name, String password) throws Exception {

		PreparedStatement pstmt = null;

		connect();

		try{

			pstmt = setupPstmt(id);

			int rs = pstmt.executeUpdate();
			//指定されたIDにデータがあった場合、正常に処理が完了したことを伝える
			if(rs != 0){

				return OK_MESSAGE;
			}

		} catch (Exception e) {

			e.printStackTrace();
//			throw e;

		} finally {

			pstmt.close();
			close();
		}

		return DELTE_MESSAGE;
	}

	protected PreparedStatement setupPstmt(String id) throws Exception {
		return null;
	}


 ------------------------------------------------- */


	public String insert (String user_id, String name, String password) throws Exception {

		String query = "insert into USER(USER_ID, NAME, PASSWORD) values (?, ?, ?)";
		PreparedStatement pstmt = null;

		connect();

		try {

			pstmt = con.prepareStatement(query);

			//パラメータセット
			pstmt.setString(1, user_id);
			pstmt.setString(2, name);
			pstmt.setString(3, password);

			int rs = pstmt.executeUpdate();

			pstmt.close();

			if(rs != 0){

				return OK_MESSAGE;
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		} finally {

			pstmt.close();
			close();
		}

		return INSERT_MESSAGE;

	}

	public String update (String id, String user_id, String name, String password) throws Exception {

		String query = "update USER set USER_ID = ? , NAME = ? , PASSWORD = ? where ID = ?";
		PreparedStatement pstmt = null;

		connect();

		try {
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
			throw e;

		} finally {

			pstmt.close();
			close();
		}

		return DELTE_MESSAGE;
	}
}


