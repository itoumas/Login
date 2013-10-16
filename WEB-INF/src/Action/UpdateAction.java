package Action;

import java.sql.Connection;

import DAO.Update;

public class UpdateAction extends EditAction {

	//Connectionオブジェクトを格納します。
	Connection con = null;

	public String url;

	public UpdateAction(String url) {
		this.url = url;
	}

	public UpdateAction() {
		super();
	}

	public String edit (String id, String user_id, String name, String password) throws Exception {

		Update dao = new Update(url);
		return dao.execute(id, user_id, name, password);
	}
}
