package Action;

public class EditAction {

<<<<<<< HEAD
	protected String url;

	/**
	 * テスト用URLが引数として無ければデフォルトのURLをDAOクラスに渡します。
	 */
	public EditAction() {
		url = "jdbc:mysql://192.168.43.210:3306/hogehoge?useUnicode=true&characterEncoding=UTF-8";
	}

	public String edit(String id, String user_id, String name, String password) throws Exception {
		return "";
=======
	public String edit (String id, String user_id, String name, String password, String url) throws Exception {

		return "";

>>>>>>> ba8539b9f620218559731891a1db7d8773abb637
	}
}
