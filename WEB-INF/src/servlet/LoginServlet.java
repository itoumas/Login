package servlet;

import java.sql.Statement;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		ServletContext sc = getServletContext();

		Connection connection = null;

		try{
			log("パスワード" + password);

			//JDBCへロード
			Class.forName("com.mysql.jdbc.Driver");

			//MySQLへアクセス
			Connection con = DriverManager.getConnection(url, user, pass);

			//SELECTの結果

			String query = "select NAME from USER where ID = " + id + " and PASSWORD = '" + password + "'";

			log("クエリ" + query);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if((id.equals("1")) && (password.equals("itou")))
			{
//				String name = "伊藤";
				String name = rs.getString("NAME");

				log("名前" + name);

				request.setAttribute("name", name);
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

		} catch (Exception e){

//			throw new ServletException(e);

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


