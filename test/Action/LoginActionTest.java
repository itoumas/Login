package Action;

import static org.junit.Assert.*;
import org.junit.Test;

public class LoginActionTest extends LoginAction {

	@Test
	public void testUserLogin() throws Exception {

		String url = "jdbc:mysql://10.10.14.228:3306/testDB?useUnicode=true&characterEncoding=UTF-8";

		LoginAction loginAction = new LoginAction();
		assertEquals("IDとパスワードを用いてログイン", "itou",loginAction.userLogin("itou", "itou", url));
		assertEquals("IDとパスワードを用いてログイン", "ログインできません",loginAction.userLogin("ccc", "ccc", url));
	}
}
