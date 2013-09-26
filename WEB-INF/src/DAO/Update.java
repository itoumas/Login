package DAO;

import java.sql.PreparedStatement;

public class Update extends ConnectDao {

	protected PreparedStatement setupPstmt(String id, String user_id, String name, String password) throws Exception {

		String query = "update USER set USER_ID = ? , NAME = ? , PASSWORD = ? where ID = ?";

		PreparedStatement pstmt = con.prepareStatement(query);

		//パラメータセット
		pstmt.setString(1, user_id);
		pstmt.setString(2, name);
		pstmt.setString(3, password);
		pstmt.setString(4, id);

		return pstmt;
	}
}
