package Action;

import java.sql.SQLException;
import DAO.ConnectDao;

public class LoginAction {

	public String userLogin (String user_id, String password) throws Exception {

		ConnectDao dao = null;

		try {
			//MySQLに接続
			dao = new ConnectDao();

			//ID、パスワードでログイン
			return dao.login(user_id, password);

		} catch (Exception e) {

			throw new SQLException(e);
		}
	}
}
