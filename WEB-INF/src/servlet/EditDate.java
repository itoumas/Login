package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.EditAction;
import Factory.Factory;

public class EditDate extends HttpServlet {

	public void doPost (HttpServletRequest request, HttpServletResponse response) {

		//JSPから受け取ったIDとパスワード
		String id = request.getParameter("id");
		String user_id = request.getParameter("user_id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String btn = request.getParameter("btn");

		try {
			request.setCharacterEncoding("UTF-8");

			ServletContext sc = getServletContext();

			//ファクトリ
			Factory fact = new Factory();
			EditAction editAction = fact.factory(btn);

			String message = editAction.edit(id, user_id, name, password);

			//セッションから名前を取り出す
			HttpSession session = request.getSession(false);

			//セッションが生成されていない状態で処理を行おうとした場合、ログイン画面へ飛ばす
			if (session == null) {

				response.setContentType("text/html; charset=utf-8");
				RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/login.jsp");
				rd.forward(request, response);
			}

			//セッションに保存されたユーザ名をレスポンスと送るために取り出す
			String userName = (String)session.getAttribute("userName");

			//処理後もとのページに戻る
			request.setAttribute("name", userName);
			request.setAttribute("message", message);
			response.setContentType("text/html; charset=utf-8");
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/Welcome.jsp");
			rd.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}
