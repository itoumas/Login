package Action;

public class EditAction {

	protected String url;

	/**
	 * テスト用URLが引数として無ければデフォルトのURLをDAOクラスに渡します。
	 */
	public EditAction() {
		url = "jdbc:mysql://localhost:3306/hogehoge?useUnicode=true&characterEncoding=UTF-8";
	}

	public String edit(String id, String user_id, String name, String password) throws Exception {
		return "";
	}
}
