package servlet;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		ServletContext sc = getServletContext();

		Connection connection = null;

		try{

			Class.forName("com.mysql.jdbc.Driver");

/*
			//JNDI参照コンテキストを取得
			InitialContext initCtx = new InitialContext();

			//Tomcatで管理されているDB接続をJNDI経由で取得
			DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jbdc/localDB");
			connection = ds.getConnection();

			log("DBに接続しました");
*/
			if((id.equals("itou")) && (password.equals("itou")))
			{
				String name = "伊藤";

				request.setAttribute("name", name);
				response.setContentType("text/html; charset=utf-8");
				RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/Welcome.jsp");
				rd.forward(request, response);

			}else{

				RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
				response.setContentType("text/html; charset=utf-8");
				rd.forward(request, response);
			}

		} catch (Exception e){

			throw new ServletException(e);

		}finally{

			try{
				connection.close();

				log("DBへの接続を閉じました");

			}catch (SQLException e){

				throw new ServletException(e);
			}
		}
	}
}


