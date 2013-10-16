package Action;

import java.sql.SQLException;
import DAO.ConnectDao;

public class LoginAction {

<<<<<<< HEAD
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
=======
	public String userLogin (String user_id, String password, String url) throws Exception {

		ConnectDao dao = null;
//		String url = "jdbc:mysql://10.10.14.228:3306/hogehoge?useUnicode=true&characterEncoding=UTF-8";

		try {
			//MySQLに接続
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637
			dao = new ConnectDao(url);

			//ID、パスワードでログイン
			return dao.login(user_id, password);

		} catch (Exception e) {

			throw new SQLException(e);
		}
	}
}
