package DAO;

import java.sql.Connection;
import Action.EditAction;

public class UpdateAction extends EditAction {

	//Connectionオブジェクトを格納
	Connection con = null;

	public String edit (String id, String user_id, String name, String password) throws Exception {

		ConnectDao dao = new ConnectDao();
		return dao.update(id, user_id, name, password);
	}
}
