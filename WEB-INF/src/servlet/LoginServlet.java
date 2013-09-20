package servlet;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.LoginAction;

public class LoginServlet extends HttpServlet {

	//ログインできたかの判断に使用
	static final String NOTLOGIN = "notLogin";

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//JSPから受け取ったIDとパスワード
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");

		ServletContext sc = getServletContext();

		LoginAction loginAction = new LoginAction();

		try {
			String getResponse = loginAction.userLogin(user_id, password);

			if(getResponse.equals(NOTLOGIN)){

				request.setAttribute("errerMessage", getResponse);
				response.setContentType("text/html; charset=utf-8");
				RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);

			}else{

				//ログインに成功したのでセッションを開始する
				HttpSession session = request.getSession(true);

				//セッションにデータを格納
				session.setAttribute("userName", getResponse);

				request.setAttribute("name", getResponse);
				response.setContentType("text/html; charset=utf-8");
				RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/Welcome.jsp");
				rd.forward(request, response);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}



