package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginAction{

	public String userLogin(String user_id, String password) throws ServletException, IOException  {

		//MySQLにアクセスするためのユーザ名、パスワード、URL
		String user = "systena";
		String pass = "systena";
		String url = "jdbc:mysql://10.10.14.162:3306/systenaDB?useUnicode=true&characterEncoding=UTF-8";

		String getResponse = "";

//		HttpServletRequest request = null;

		Connection connection = null;

		try{
			//JDBCへロード
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
				String getName = rs.getString("NAME");

				return getName;

			}else{

				return getResponse = "notLogin";

			}

		}catch (Exception e){

			throw new ServletException(e);

		}finally{

			try{
				if(connection != null){

					connection.close();
				}

//				log("DBへの接続を閉じました");

			}catch (SQLException e){

				throw new ServletException(e);
			}
		}
	}
}
