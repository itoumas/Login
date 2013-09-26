package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import Action.EditAction;

public class DeleteAction extends EditAction {

	//データの編集が失敗した場合のメッセージ
	static final String DELTE_MESSAGE = "削除できませんでした";

	static final String OK_MESSAGE = "完了！！";

	//Connectionオブジェクトを格納
	Connection con = null;

	public String edit (String id, String user_id, String name, String password) throws Exception {

		ConnectDao dao = new ConnectDao();

		return dao.delete(id);
	}
}
