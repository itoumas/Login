package Action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAction{

	//ログインできなかった場合の戻り値
	static final String NOTLOGIN = "notLogin";

	public String userLogin(String user_id, String password) throws SQLException {

		//MySQLにアクセスするためのユーザ名、パスワード、URL
		String user = "systena";
		String pass = "systena";
		String url = "jdbc:mysql://10.10.14.162:3306/systenaDB?useUnicode=true&characterEncoding=UTF-8";

		Connection connection = null;

		try{
			//JDBCをロード
			Class.forName("com.mysql.jdbc.Driver");

			//MySQLへアクセス
			Connection con = DriverManager.getConnection(url, user, pass);

			//プレースホルダーを指定してSQLを作成
			String query = "select NAME from USER where USER_ID = ? and PASSWORD = ?";

			PreparedStatement pstmt = con.prepareStatement(query);

			//パラメータセット
			pstmt.setString(1, user_id);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
			{
				return rs.getString("NAME");

			}else{

				return NOTLOGIN;

			}

		}catch (Exception e){

			throw new SQLException(e);

		}finally{

			try{
				if(connection != null){

					connection.close();
				}

			}catch (SQLException e){

			}
		}
	}
}
