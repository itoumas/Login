package DAO;

import java.sql.PreparedStatement;

public class Insert extends ConnectDao {

	public Insert(String url) {
		super(url);
	}

	protected PreparedStatement setupPstmt(String id, String user_id, String name, String password) throws Exception {

		String query = "insert into USER(USER_ID, NAME, PASSWORD) values (?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(query);

		//パラメータセット
		pstmt.setString(1, user_id);
		pstmt.setString(2, name);
		pstmt.setString(3, password);

		return pstmt;
	}

	private static final String MESSAGE = "追加できませんでした";

	protected String message() {

		return MESSAGE;
	}
}
