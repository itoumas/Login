package DAO;

import java.sql.Connection;
import Action.EditAction;

public class DeleteAction extends EditAction {

	//Connectionオブジェクトを格納
	Connection con = null;

	public String edit (String id, String user_id, String name, String password) throws Exception {

		Delete dao = new Delete();
		return dao.execute(id, user_id, name, password);
	}
}
