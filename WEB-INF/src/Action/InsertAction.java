package Action;

import java.sql.Connection;
import DAO.Insert;

public class InsertAction extends EditAction {

	//Connectionオブジェクトを格納
	Connection con = null;

	public String edit (String id, String user_id, String name, String password, String url) throws Exception {

		Insert dao = new Insert(url);
		return dao.execute(id, user_id, name, password);
	}
}
