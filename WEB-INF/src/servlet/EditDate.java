package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditDate  extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//MySQLにアクセスするためのユーザ名、パスワード、URL
		String user = "systena";
		String pass = "systena";
		String url = "jdbc:mysql://10.10.14.162:3306/systenaDB?useUnicode=true&characterEncoding=UTF-8";

		//JSPから受け取ったIDとパスワード
		String id = request.getParameter("id");
		String user_id = request.getParameter("user_id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String btn = request.getParameter("btn");

		ServletContext sc = getServletContext();

		Connection connection = null;

		try{
			//JDBCへロード
			Class.forName("com.mysql.jdbc.Driver");

			//MySQLへアクセス
			Connection con = DriverManager.getConnection(url, user, pass);

			if(btn.equals("Delete")){

				String query = "delete from USER where ID = ?";

				PreparedStatement pstmt = con.prepareStatement(query);

				//パラメータセット
				pstmt.setString(1, id);

				int rs = pstmt.executeUpdate();

//				rs.close();
				pstmt.close();

			}else if(btn.equals("Insert")){

				String query = "insert into USER(USER_ID, NAME, PASSWORD) values (?, ?, ?)";

				PreparedStatement pstmt = con.prepareStatement(query);

				//パラメータセット
				pstmt.setString(1, user_id);
				pstmt.setString(2, name);
				pstmt.setString(3, password);

				int rs = pstmt.executeUpdate();

//				rs.close();
				pstmt.close();

			}else if(btn.equals("Update")){

				String query = "update USER set USER_ID = ? , NAME = ? , PASSWORD = ? where ID = ?";

				PreparedStatement pstmt = con.prepareStatement(query);

				//パラメータセット
				pstmt.setString(1, user_id);
				pstmt.setString(2, name);
				pstmt.setString(3, password);
				pstmt.setString(4, id);

				int rs = pstmt.executeUpdate();

//				rs.close();
				pstmt.close();
			}

			//もとのページに戻る
//			request.setAttribute("name", message);
			response.setContentType("text/html; charset=utf-8");
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/Welcome.jsp");
			rd.forward(request, response);

		} catch (Exception e){

			throw new ServletException(e);

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
