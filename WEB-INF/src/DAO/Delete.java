package DAO;

import java.sql.PreparedStatement;

public class Delete extends ConnectDao {

	protected PreparedStatement setupPstmt(String id, String user_id, String name, String password) throws Exception {

		String query = "delete from USER where ID = ?";
		PreparedStatement pstmt = con.prepareStatement(query);

		//パラメータセット
		pstmt.setString(1, id);
		return pstmt;
	}
}
