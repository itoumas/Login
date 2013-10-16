package DAO;

import java.sql.PreparedStatement;

public class Delete extends ConnectDao {

<<<<<<< HEAD
	public Delete(String url) {
		super(url);
	}

=======
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637
	protected PreparedStatement setupPstmt(String id, String user_id, String name, String password) throws Exception {

		String query = "delete from USER where ID = ?";
		PreparedStatement pstmt = con.prepareStatement(query);

		//パラメータセット
		pstmt.setString(1, id);

		return pstmt;
	}

	private static final String MESSAGE = "削除できませんでした";

	protected String message() {

		return MESSAGE;
	}
}
