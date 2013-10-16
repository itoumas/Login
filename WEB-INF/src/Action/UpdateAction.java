package Action;

import java.sql.Connection;

import DAO.Update;

public class UpdateAction extends EditAction {

<<<<<<< HEAD
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
=======
	//Connectionオブジェクトを格納
	Connection con = null;

	public String edit (String id, String user_id, String name, String password, String url) throws Exception {
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637

		Update dao = new Update(url);
		return dao.execute(id, user_id, name, password);
	}
}
