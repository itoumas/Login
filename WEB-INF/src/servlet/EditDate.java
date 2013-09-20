package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.EditAction;

public class EditDate  extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		//JSPから受け取ったIDとパスワード
		String id = request.getParameter("id");
		String user_id = request.getParameter("user_id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String btn = request.getParameter("btn");

		ServletContext sc = getServletContext();

		EditAction editAction = new EditAction();

		editAction.edit(id, user_id, name, password, btn);

		//セッションから名前を取り出す
		HttpSession session = request.getSession(false);

		//セッションが生成されていない状態で処理を行おうとした場合、ログイン画面へ飛ばす
		if (session == null){
			response.setContentType("text/html; charset=utf-8");
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/login.jsp");
			rd.forward(request, response);
		}

		//セッションに保存されたユーザ名をレスポンスと送るために取り出す
		String userName = (String)session.getAttribute("userName");

		//処理後もとのページに戻る
		request.setAttribute("name", userName);
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/Welcome.jsp");
		rd.forward(request, response);
	}
}
