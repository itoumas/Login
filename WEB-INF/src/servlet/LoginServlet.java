package servlet;

import java.sql.Statement;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import sun.rmi.runtime.Log;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//MySQLにアクセスするためのユーザ名、パスワード、URL
		String user = "systena";
		String pass = "systena";
		String url = "jdbc:mysql://10.10.14.162:3306/systenaDB?useUnicode=true&characterEncoding=UTF-8";

		//JSPから受け取ったIDとパスワード
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");

		ServletContext sc = getServletContext();

		Connection connection = null;

		try{
			log("ID:" + user_id);
			log("パスワード" + password);

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

			log("クエリ" + query);

			if(rs.next())
			{
				String getName = rs.getString("NAME");

				log("名前" + getName);
/*
				HttpSession session = request.getSession(false);
				if (session == null){
					//セッションが存在しないので開始する
					session = request.getSession(true);
				}
*/
				request.setAttribute("name", getName);
				response.setContentType("text/html; charset=utf-8");
				RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/Welcome.jsp");
				rd.forward(request, response);

			}else{

				String errerMessage = "ログインできません";

				request.setAttribute("errerMessage", errerMessage);
				response.setContentType("text/html; charset=utf-8");
				RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e){

			throw new ServletException(e);

		}finally{
/*
			try{
				connection.close();

				log("DBへの接続を閉じました");

			}catch (SQLException e){

				throw new ServletException(e);
			}
*/
		}
	}
}


