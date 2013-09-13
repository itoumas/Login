package servlet;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		ServletContext sc = getServletContext();

		PrintWriter out = response.getWriter();

		if((id.equals("itou")) && (password.equals("itou")))
		{
			String name = "伊藤";

			request.setAttribute("name", name);
			response.setContentType("text/html; charset=utf-8");
			RequestDispatcher rd = sc.getRequestDispatcher("/Welcome.jsp");
			rd.forward(request, response);

		}else{

			RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
			response.setContentType("text/html; charset=utf-8");
			rd.forward(request, response);
		}
	}
}
