package Action;

import java.sql.SQLException;
import DAO.ConnectDao;

public class LoginAction {

	public String userLogin (String user_id, String password, String url) throws Exception {

		ConnectDao dao = null;
//		String url = "jdbc:mysql://10.10.14.228:3306/hogehoge?useUnicode=true&characterEncoding=UTF-8";

		try {
			//MySQLに接続
			dao = new ConnectDao(url);

			//ID、パスワードでログイン
			return dao.login(user_id, password);

		} catch (Exception e) {

			throw new SQLException(e);
		}
	}
}
