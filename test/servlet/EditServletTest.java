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
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.powermock.modules.junit4.PowerMockRunner;

import Factory.Factory;

import static org.mockito.Mockito.*;


/**
 *
 * EditServletクラスのテストを行います。
 *
 * @author itoumas
 *
 */
//PowerMockを利用する宣言をします。
//PowerMockクラスを呼び出すEditServletクラスを宣言します。
//@RunWith(PowerMockRunner.class)
@PrepareForTest(EditServlet.class)
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
		verify(session).removeAttribute("token");
		verify(requestDispatcher).forward(request, response);

		//responseにsetContentTypeが行われているかの検証をします。
		assertEquals("text/html; charset=utf-8", response.getContentType());

		assertEquals("serverToken", session.getAttribute("token"));
	}


	/**
	 * tokenが一致した場合のテストです。
	 * @throws Exception
	 */
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
		doReturn("testToken").when(request).getParameter("token"); //セッション側tokenと一致させないとテスト失敗

		//getRequestDispatcherメソッドが呼び出されたら強制的にrequestDispatcherを返します。
		doReturn(requestDispatcher).when(servletContext).getRequestDispatcher("/WEB-INF/Welcome.jsp");

		//getAttribute("name")が呼び出されたら強制的に"testName"を返します。
		//requestにuserNameがセットされたかの検証に使用します。
		doReturn("testName").when(session).getAttribute("userName");

		//リクエストにデータをセットします。
		doReturn("").when(request).getParameter("id");
		doReturn("test_id").when(request).getParameter("user_id");
		doReturn("test_name").when(request).getParameter("name");
		doReturn("test_pass").when(request).getParameter("password");
		doReturn("Delete").when(request).getParameter("btn");

//		EditAction editAction = mock(EditAction.class);

//		doReturn(editAction).when(fact).factory(anyString());
//		doReturn("testMessage").when(editAction).edit(anyString(), anyString(), anyString(), anyString());

		//Factoryクラスのモックオブジェクトを作成します。
		Factory fact = mock(Factory.class);
		//Factoryクラスがnewされた際にFactoryクラスのコンストラクタにmockを送ります。
		PowerMockito.whenNew(Factory.class).withAnyArguments().thenReturn(fact);

		//doPostを実行します。
		editServlet.doPost(request, response);

		//モックメソッドが実行されたか検証します。
		verify(servletContext).getRequestDispatcher("/WEB-INF/Welcome.jsp");
		verify(request).getSession(false);
		verify(request).setAttribute("name", "testName");
		verify(request).setAttribute("message", "削除できませんでした");
		verify(requestDispatcher).forward(request, response);

		//responseにsetContentTypeが行われているかの検証をします。
		assertEquals("text/html; charset=utf-8", response.getContentType());
	}
}
