package Action;

import java.sql.Connection;
import DAO.Delete;

public class DeleteAction extends EditAction {

<<<<<<< HEAD
	//Connectionオブジェクトを格納します。
	Connection con = null;

	public String url;

	public DeleteAction(String url) {
		this.url = url;
	}

	public DeleteAction() {
		super();
	}

	public String edit(String id, String user_id, String name, String password) throws Exception {

		Delete dao = new Delete(url);
		return dao.execute(id, user_id, name, password);
=======
	//Connectionオブジェクトを格納
	Connection con = null;

	public String edit (String id, String user_id, String name, String password, String url) throws Exception {

//		Delete dao = new Delete(url);
//		return dao.execute(id, user_id, name, password);
		return "";
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637
	}
}
