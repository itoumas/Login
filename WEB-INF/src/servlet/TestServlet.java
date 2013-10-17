package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestServlet extends HttpServlet {

	public void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ログインに成功したのでセッションを開始します。
		HttpSession session = request.getSession(true);

		//session.setAttribute("userName", "aaaaa");
	}
}
