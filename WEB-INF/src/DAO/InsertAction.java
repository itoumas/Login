package DAO;

import java.sql.Connection;
import Action.EditAction;

public class InsertAction extends EditAction {

	//Connectionオブジェクトを格納
	Connection con = null;

	public String edit (String id, String user_id, String name, String password) throws Exception {

		Insert dao = new Insert();
		return dao.execute(id, user_id, name, password);
	}
}
