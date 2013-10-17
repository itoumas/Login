package Action;

import java.sql.Connection;
import DAO.Insert;

public class InsertAction extends EditAction {

	//Connectionオブジェクトを格納します。
	Connection con = null;

	public String url;

	public InsertAction(String url) {
		this.url = url;
	}

	public InsertAction() {
		super();
	}

	public String edit(String id, String user_id, String name, String password) throws Exception {

		Insert dao = new Insert(url);
		return dao.execute(id, user_id, name, password);
	}
}
