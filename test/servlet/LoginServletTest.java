package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginServletTest {

	@Test
	public void testDoPost() throws Exception {

		// ************ ログイン失敗の場合 *************

		//LoginServletをspyでオブジェクト化します。
		LoginServlet loginServlet = spy(new LoginServlet());

		//モックオブジェクトを作成します。
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		ServletContext servletContext = mock(ServletContext.class);
		RequestDispatcher requestDispacher = mock(RequestDispatcher.class);

		//getServletContextメソッドが呼び出されたら強制的にServletContextモックオブジェクトを返します。
		doReturn(servletContext).when(loginServlet).getServletContext();

		//getRequestDispacharが呼び出されたら強制的にRequestDispatcherモックオブジェクトを返します。
		doReturn(requestDispacher).when(servletContext).getRequestDispatcher("/login.jsp");	//パスを変えるとテスト失敗

		//リクエストにパラメータをセットします(DBに存在しないIDとPASSWORD)。
		request.setParameter("user_id", "miss");	//存在するIDとPASSWORDを使用するとテスト失敗
		request.setParameter("password", "miss");

		//doPostメソッドを実行します。
		loginServlet.doPost(request, response);

		//モックメソッドが実行されたかを検証します。
		verify(loginServlet).getServletContext();
		verify(servletContext).getRequestDispatcher("/login.jsp");	//パスを変えるとテスト失敗
		verify(requestDispacher).forward(request, response);

		//requestにsetAttributeされているかの検証をします。
		assertEquals("ログインできません", request.getAttribute("errerMessage"));

		//responseにsetContentTypeが行われているかの検証をします。
		assertEquals("text/html; charset=utf-8", response.getContentType());
	}
}
