package Action;

import DAO.ConnectDao;

public class EditAction {

	public String edit (String id, String user_id, String name, String password, String btn) throws Exception {

//		Connection connection = null;

		String message = "";

		try {
			ConnectDao dao = new ConnectDao();

			//データを削除処理
			if (btn.equals("Delete")) {

				message = dao.delete(id);

			//データの追加処理
			} else if(btn.equals("Insert")) {

				message = dao.insert(user_id, name, password);

			//データの更新処理
			} else if(btn.equals("Update")) {

				message = dao.update(id, user_id, name, password);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return message;
	}
}
