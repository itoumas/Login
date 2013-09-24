package servlet;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.LoginAction;
import DAO.ConnectDao;

public class LoginServlet extends HttpServlet {

	//ログインできなかった場合の戻り値
	public static final String NOT_LOGIN = "notLogin";

	public void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//JSPから受け取ったIDとパスワード
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");

		ServletContext sc = getServletContext();

		LoginAction loginAction = new LoginAction();

		try {
			String getResponse = loginAction.userLogin(user_id, password);

			if (getResponse.equals(ConnectDao.NOT_LOGIN)) {

				request.setAttribute("errerMessage", getResponse);
				response.setContentType("text/html; charset=utf-8");
				RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);

			} else {

				//ログインに成功したのでセッションを開始する
				HttpSession session = request.getSession(true);

				//セッションにデータを格納
				session.setAttribute("userName", getResponse);

				request.setAttribute("name", getResponse);
				response.setContentType("text/html; charset=utf-8");
				RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/Welcome.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}



