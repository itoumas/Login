package Action;

import java.sql.Connection;
import DAO.Delete;

public class DeleteAction extends EditAction {

	//Connectionオブジェクトを格納
	Connection con = null;

	public String edit (String id, String user_id, String name, String password, String url) throws Exception {

		Delete dao = new Delete(url);
		return dao.execute(id, user_id, name, password);
	}
}
