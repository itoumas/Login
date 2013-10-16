package servlet;

import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.EditAction;
import Factory.Factory;

public class EditServlet extends HttpServlet {

<<<<<<< HEAD
=======
	protected String url = "jdbc:mysql://10.10.14.228:3306/hogehoge?useUnicode=true&characterEncoding=UTF-8";

>>>>>>> ba8539b9f620218559731891a1db7d8773abb637
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		try
		{
			ServletContext sc = getServletContext();

			//セッションから名前を取り出す
			HttpSession session = request.getSession(false);

			//セッションが生成されていない状態で処理を行おうとした場合、ログイン画面へ飛ばします。
			if ((session == null) || (session.getAttribute("userName") == null)) {

				response.setContentType("text/html; charset=utf-8");
				RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);

				return;
			}

			//XSRF対策としてトークンが一致するか確認します。
			Object sessionToken = session.getAttribute("token");
			String stringToken = sessionToken.toString();

			String requestToken = request.getParameter("token");

			if (!(stringToken.equals(requestToken))) {

				//有効なセッションを削除します。
				session.removeAttribute("token");

				response.setContentType("text/html; charset=utf-8");
				RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);

				return;
			}

			request.setCharacterEncoding("UTF-8");

			//JSPから受け取ったIDとパスワード
			String id = request.getParameter("id");
			String user_id = request.getParameter("user_id");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String btn = request.getParameter("btn");

			//DBの操作を行う(ビジネスロジック)
			Factory fact = new Factory();
			EditAction editAction = fact.factory(btn);

<<<<<<< HEAD
			String message = editAction.edit(id, user_id, name, password);
=======
			String message = editAction.edit(id, user_id, name, password, url);
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637

			//セッションに保存されたユーザ名をレスポンスで送るために取り出します。
			String userName = (String)session.getAttribute("userName");

			//新たなトークンを作成します。
			UUID token = UUID.randomUUID ();
			session.setAttribute("token", token);
			request.setAttribute("token", token);

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
