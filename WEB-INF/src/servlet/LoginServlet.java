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
/*
		//MySQLにアクセスするためのユーザ名、パスワード、URL
		String user = "systena";
		String pass = "systena";
		String url = "jdbc:mysql://10.10.14.162:3306/systenaDB?useUnicode=true&characterEncoding=UTF-8";
*/
		//JSPから受け取ったIDとパスワード
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");

		ServletContext sc = getServletContext();

//		Connection connection = null;

		LoginAction loginAction = new LoginAction();
		String getResponse = loginAction.userLogin(user_id, password);

		if(getResponse.equals("notLogin")){

			HttpSession session = request.getSession(false);

			//セッションが存在しないので開始する
			session = request.getSession(true);

			//セッションにデータを格納
			session.setAttribute("userName", getResponse);

			getResponse = (String)session.getAttribute("userName");

			request.setAttribute("errerMessage", getResponse);
			response.setContentType("text/html; charset=utf-8");
			RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);

		}else{

			request.setAttribute("name", getResponse);
			response.setContentType("text/html; charset=utf-8");
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/Welcome.jsp");
			rd.forward(request, response);
		}

/*			}

			rs.close();
			pstmt.close();

		} catch (Exception e){

			throw new ServletException(e);

		}finally{

			try{
				connection.close();

				log("DBへの接続を閉じました");

			}catch (SQLException e){

				throw new ServletException(e);
			}

		}*/
	}
}


