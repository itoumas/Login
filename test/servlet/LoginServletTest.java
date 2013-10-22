package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class LoginServletTest {


	//モックオブジェクトを格納します。
	private LoginServlet loginServlet = null;
	private MockHttpServletRequest request = null;
	private MockHttpServletResponse response = null;
	private ServletContext servletContext = null;
	private RequestDispatcher requestDispacher = null;


	/**
	 * 各テスト前にモックオブジェクトを作成します。
	 */
	@Before
	public void setUp() {

		//LoginServletをspyでオブジェクト化します。
		loginServlet = spy(new LoginServlet());

		//モックオブジェクトを作成します。
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servletContext = mock(ServletContext.class);
		requestDispacher = mock(RequestDispatcher.class);
	}


	/**
	 * ログインが成功した場合のテスト
	 *
	 * @throws Exception
	 */
	@Test
	public void testDoPostSuccess() throws Exception {

		//getServletContextメソッドが呼び出されたら強制的にServletContextモックオブジェクトを返します。
		doReturn(servletContext).when(loginServlet).getServletContext();

		//getRequestDispacharが呼び出されたら強制的にRequestDispatcherモックオブジェクトを返します。
		doReturn(requestDispacher).when(servletContext).getRequestDispatcher("/WEB-INF/Welcome.jsp");	//パスを変えるとテスト失敗

		//リクエストにパラメータをセットします(DBに存在するIDとPASSWORD)。
		request.setParameter("user_id", "itou_id");		//存在しないIDとPASSWORDを使用するとテスト失敗
		request.setParameter("password", "itou_pass");

		//doPostメソッドを実行します。
		loginServlet.doPost(request, response);

		//モックメソッドが実行されたかを検証します。
		verify(loginServlet).getServletContext();
		verify(servletContext).getRequestDispatcher("/WEB-INF/Welcome.jsp");	//パスを変えるとテスト失敗
		verify(requestDispacher).forward(request, response);

		//requestにsetAttributeされているかの検証をします。
		assertEquals("itou_name", request.getAttribute("name"));
		assertNotNull(request.getAttribute("token"));

		//responseにsetContentTypeが行われているかの検証をします。
		assertEquals("text/html; charset=utf-8", response.getContentType());
	}


	/**
	 * ログインが失敗した場合のテスト
	 *
	 * @throws Exception
	 */
	@Test
	public void testDoPostFalse() throws Exception {

		//getServletContextメソッドが呼び出されたら強制的にServletContextモックオブジェクトを返します。
		doReturn(servletContext).when(loginServlet).getServletContext();

		//getRequestDispacharが呼び出されたら強制的にRequestDispatcherモックオブジェクトを返します。
		doReturn(requestDispacher).when(servletContext).getRequestDispatcher("/login.jsp");		//パスを変えるとテスト失敗

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
