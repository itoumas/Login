package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
<<<<<<< HEAD
 * DB接続と各種DB処理を行う一般クラスです。
=======
 * DB接続と各種DB処理を行う一般クラス。
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637
 *
 * @author itoumas
 */
public class ConnectDao {

	/**
<<<<<<< HEAD
	 * メッセージです。
=======
	 * メッセージ。
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637
	 */
	public static final String NOT_LOGIN = "ログインできません";
	protected static final String OK_MESSAGE = "完了！！";

	protected String NG_MESSAGE;

	//Connectionオブジェクトを格納
	Connection con = null;

	public String url;

	/**
<<<<<<< HEAD
	 * コンストラクタでURLを指定します。
	 * デフォルトURLかテスト用URLのどちらかが指定されます。
=======
	 * コンストラクタでURLを指定
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637
	 */
	public ConnectDao(String url) {

		this.url = url;
	}

	public ConnectDao() {

<<<<<<< HEAD
		url = "jdbc:mysql://192.168.1.105:3306/hogehoge?useUnicode=true&characterEncoding=UTF-8";
=======
		String url = "jdbc:mysql://10.10.14.228:3306/hogehoge?useUnicode=true&characterEncoding=UTF-8";
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637
	}

	/**
	 * MySQLに接続します。
	 *
	 * @throws Exception
	 */
<<<<<<< HEAD
	public void connect() throws Exception {

		//MySQLにアクセスするためのユーザ名、パスワード、URL
		String user = "root";
		String pass = "";
=======
	public void connect () throws Exception {

		//MySQLにアクセスするためのユーザ名、パスワード、URL
		String user = "systena";
		String pass = "systena";
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637

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
<<<<<<< HEAD
	public void close() {
=======
	public void close () {
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637

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
<<<<<<< HEAD
	public String login(String user_id, String password) throws Exception {
=======
	public String login (String user_id, String password) throws Exception {
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637

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
<<<<<<< HEAD
	protected PreparedStatement setupPstmt(String id, String user_id, String name, String password) throws Exception {
=======
	protected PreparedStatement setupPstmt (String id, String user_id, String name, String password) throws Exception {
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637

		return null;
	}

	/**
	 * 処理を実行します。
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
<<<<<<< HEAD
	public String execute(String id, String user_id, String name, String password) throws Exception {
=======
	public String execute (String id, String user_id, String name, String password) throws Exception {
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637

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

		return NG_MESSAGE = message();
	}

<<<<<<< HEAD
	protected String message() {
=======
	protected String message () {
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637

		return "";
	}
}
