package servlet;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import Action.EditAction;
import Factory.Factory;

import static org.mockito.Mockito.*;


/**
 *
 * EditServletクラスのテストを行います。
 *
 * @author itoumas
 *
 */
public class EditServletTest extends EditServlet {


	/**
	 * モックオブジェクトの格納に使用します。
	 */
	EditServlet editServlet = null;
	HttpServletRequest request = null;
	MockHttpServletResponse response = null;
	HttpSession session = null;
	RequestDispatcher requestDispatcher = null;
	ServletContext servletContext = null;


	/**
	 * オブジェクトの作成とモックメソッドの戻り値を設定します。
	 */
	@Before
	public void setUp() {

		//EditServletをspyでオブジェクト化します。
		editServlet = spy(new EditServlet());

		//モックオブジェクトを作成します。
		request = mock(HttpServletRequest.class);
		response = new MockHttpServletResponse();
		session = mock(HttpSession.class);
		requestDispatcher = mock(RequestDispatcher.class);
		servletContext = mock(ServletContext.class);

		//getServletContextメソッドが呼び出されたら強制的にservletContextを返します。
		doReturn(servletContext).when(editServlet).getServletContext();
	}


	/**
	 * セッションが生成されていない場合のテストです。
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void doPostSessionNull() throws ServletException, IOException {

		//getRequestDispatcherメソッドが呼び出されたら強制的にrequestDispatcherを返します。
		doReturn(requestDispatcher).when(servletContext).getRequestDispatcher("/login.jsp");

		//doPostを実行します。
		editServlet.doPost(request, response);

		//モックメソッドが実行されたか検証します。
		verify(servletContext).getRequestDispatcher("/login.jsp");
		verify(request).getSession(false);
		verify(requestDispatcher).forward(request, response);

		//responseにsetContentTypeが行われているかの検証をします。
		assertEquals("text/html; charset=utf-8", response.getContentType());
	}


	/**
	 * セッションがnullの場合のテストです。
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void doPostSessionAttributeNull() throws ServletException, IOException {

		//getSessionメソッドが呼び出されたら強制的にhttpSessionを返します。
		doReturn(session).when(request).getSession(false);

		//getAttribute("userName")が呼び出されたら強制的にnullを返します。
		//セッションは生成されているがuserNameキーには値が格納されていない状況です。
		doReturn(null).when(session).getAttribute("userName");

		//getRequestDispatcherメソッドが呼び出されたら強制的にrequestDispatcherを返します。
		doReturn(requestDispatcher).when(servletContext).getRequestDispatcher("/login.jsp");

		//doPostを実行します。
		editServlet.doPost(request, response);

		//モックメソッドが実行されたか検証します。
		verify(servletContext).getRequestDispatcher("/login.jsp");
		verify(request).getSession(false);
		verify(session).getAttribute("userName");
		verify(requestDispatcher).forward(request, response);

		//responseにsetContentTypeが行われているかの検証をします。
		assertEquals("text/html; charset=utf-8", response.getContentType());
	}


	/**
	 * tokenが一致しない場合のテストです。
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void doPostTokenNotEquals() throws ServletException, IOException {

		//getSessionメソッドが呼び出されたら強制的にhttpSessionを返します。
		doReturn(session).when(request).getSession(false);

		//getAttribute("userName")が呼び出されたら強制的に文字列を返します。
		//セッションが正常に生成されており、userNameキーにも値が入っている状況です。
		doReturn("testSession").when(session).getAttribute("userName");

		//セッションに保持されたテスト用tokenを返します。
		doReturn("serverToken").when(session).getAttribute("token");

		//リクエストで送られてきたテスト用tokenを返します。
		//セッション側のtokenとは一致しません。
		doReturn("userToken").when(request).getParameter("token"); //セッション側tokenと一致させるとテスト失敗

		//getRequestDispatcherメソッドが呼び出されたら強制的にrequestDispatcherを返します。
		doReturn(requestDispatcher).when(servletContext).getRequestDispatcher("/login.jsp");

		//doPostを実行します。
		editServlet.doPost(request, response);

		//モックメソッドが実行されたか検証します。
		verify(servletContext).getRequestDispatcher("/login.jsp");
		verify(request).getSession(false);
		verify(session).getAttribute("userName");
		verify(requestDispatcher).forward(request, response);

		//responseにsetContentTypeが行われているかの検証をします。
		assertEquals("text/html; charset=utf-8", response.getContentType());
	}


	/**
	 * tokenが一致した場合のテストです。
	 * @throws Exception
	 */
	/*
	@Test
	public void doPostSuccess() throws Exception {

		//getSessionメソッドが呼び出されたら強制的にhttpSessionを返します。
		doReturn(session).when(request).getSession(false);

		//getAttribute("userName")が呼び出されたら強制的に文字列を返します。
		//セッションが正常に生成されており、userNameキーにも値が入っている状況です。
		doReturn("testSession").when(session).getAttribute("userName");

		//セッションに保持されたテスト用tokenを返します。
		doReturn("testToken").when(session).getAttribute("token");

		//リクエストで送られてきたテスト用tokenを返します。
		//セッション側のtokenとは一致しません。
		doReturn("testToken").when(request).getParameter("token"); //セッション側tokenと一致させるとテスト失敗

		//getRequestDispatcherメソッドが呼び出されたら強制的にrequestDispatcherを返します。
		doReturn(requestDispatcher).when(servletContext).getRequestDispatcher("/WEB-INF/Welcome.jsp");

//		Factory fact = mock(Factory.class);

//		EditAction editAction = mock(EditAction.class);

		String btn = null;
		String id = null;
		String user_id = null;
		String name = null;
		String password = null;

//		doReturn(editAction).when(fact).factory(btn);
//		doReturn("testMessage").when(editAction).edit(id, user_id, name, password);

		doReturn("").when(request).getParameter("id");
		doReturn("").when(request).getParameter("user_id");
		doReturn("").when(request).getParameter("name");
		doReturn("").when(request).getParameter("password");
		doReturn("").when(request).getParameter("btn");

		EditAction editAction = mock(EditAction.class);
		doReturn("").when(editAction).edit(id, user_id, name, password);

		//doPostを実行します。
		editServlet.doPost(request, response);

		//モックメソッドが実行されたか検証します。
		verify(servletContext).getRequestDispatcher("/WEB-INF/Welcome.jsp");
		verify(request).getSession(false);
		verify(session).getAttribute("userName");
		verify(requestDispatcher).forward(request, response);
//		verify(fact).factory(btn);

		//responseにsetContentTypeが行われているかの検証をします。
		assertEquals("text/html; charset=utf-8", response.getContentType());
	}*/
}
