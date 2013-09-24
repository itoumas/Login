package Action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import DAO.ConnectDao;

public class LoginAction {

	public String userLogin (String user_id, String password) throws Exception {

		ConnectDao dao = null;
		Connection con = null;

		try {
			//MySQLに接続
			dao = new ConnectDao();
			con = dao.Connect();

			//ID、パスワードでログイン
			return dao.login(con, user_id, password);

		} catch (Exception e) {

			throw new SQLException(e);

		} finally {

			if (con != null) {

				dao.Close(con);
			}
		}
	}
}
