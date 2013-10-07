package Action;

import java.sql.Connection;

import DAO.Update;

public class UpdateAction extends EditAction {

	//Connectionオブジェクトを格納
	Connection con = null;

	public String edit (String id, String user_id, String name, String password, String url) throws Exception {

		Update dao = new Update(url);
		return dao.execute(id, user_id, name, password);
	}
}
