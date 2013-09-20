package Action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditAction {

	//MySQLにアクセスするためのユーザ名、パスワード、URL
	String user = "systena";
	String pass = "systena";
	String url = "jdbc:mysql://10.10.14.162:3306/systenaDB?useUnicode=true&characterEncoding=UTF-8";

	public void edit(String id, String user_id, String name, String password, String btn) {

		Connection connection = null;

		try{
			//JDBCをロード
			Class.forName("com.mysql.jdbc.Driver");

			//MySQLへアクセス
			Connection con = DriverManager.getConnection(url, user, pass);

			//データを削除処理
			if(btn.equals("Delete")){

				String query = "delete from USER where ID = ?";

				PreparedStatement pstmt = con.prepareStatement(query);

				//パラメータセット
				pstmt.setString(1, id);

				pstmt.executeUpdate();

				pstmt.close();

			//データの追加処理
			}else if(btn.equals("Insert")){

				String query = "insert into USER(USER_ID, NAME, PASSWORD) values (?, ?, ?)";

				PreparedStatement pstmt = con.prepareStatement(query);

				//パラメータセット
				pstmt.setString(1, user_id);
				pstmt.setString(2, name);
				pstmt.setString(3, password);

				pstmt.executeUpdate();

				pstmt.close();

			//データの更新処理
			}else if(btn.equals("Update")){

				String query = "update USER set USER_ID = ? , NAME = ? , PASSWORD = ? where ID = ?";

				PreparedStatement pstmt = con.prepareStatement(query);

				//パラメータセット
				pstmt.setString(1, user_id);
				pstmt.setString(2, name);
				pstmt.setString(3, password);
				pstmt.setString(4, id);

				pstmt.executeUpdate();

				pstmt.close();
			}

		}catch (Exception e){


		}finally{

		try{
			if(connection != null){

				connection.close();
			}

			}catch (SQLException e){

			}
		}
	}
}
