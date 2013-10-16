package Action;

import java.sql.SQLException;
import DAO.ConnectDao;

public class LoginAction {

	public String url;
	public ConnectDao dao;

	public LoginAction() {

		url = "jdbc:mysql://192.168.1.105:3306/hogehoge?useUnicode=true&characterEncoding=UTF-8";
	}

	public LoginAction(String url) {

		this.url = url;
	}

	public String userLogin(String user_id, String password) throws Exception {

		try {
			dao = new ConnectDao(url);

			//ID、パスワードでログイン
			return dao.login(user_id, password);

		} catch (Exception e) {

			throw new SQLException(e);
		}
	}
}
