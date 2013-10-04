package Action;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginActionTest extends LoginAction {

	@Test
	public void testUserLogin() throws Exception {

		LoginAction loginAction = new LoginAction();
		assertEquals("IDとパスワードを用いてログイン", "itou",loginAction.userLogin("itou", "itou"));
		assertEquals("IDとパスワードを用いてログイン", "ログインできません",loginAction.userLogin("aaa", "aaa"));
	}
}
