package servlet;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.mockito.Mockito.*;

public class EditServletTest extends EditServlet {

	@Before
	public void setUp() {

	}

	@Test
	public void test() throws ServletException, IOException {

		//EditServletをspyでオブジェクト化します。
		EditServlet editServlet = spy(new EditServlet());

		//モックオブジェクトを作成します。
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		HttpSession httpSession = mock(HttpSession.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
		ServletContext servletContext = mock(ServletContext.class);


		// ********* セッションが生成されていない状態のテスト ********

		//requestにセッションをセットします。
		//request.setSession(httpSession);

		//getServletContextメソッドが呼び出されたら強制的にservletContextを返します。
		doReturn(servletContext).when(editServlet).getServletContext();

		doReturn(requestDispatcher).when(servletContext).getRequestDispatcher("/login.jsp");

		//doPostを実行します。
		editServlet.doPost(request, response);

		//モックメソッドが実行されたか検証します。
		verify(servletContext).getRequestDispatcher("/login.jsp");
		verify(requestDispatcher).forward(request, response);

		//responseにsetContentTypeが行われているかの検証をします。
		assertEquals("text/html; charset=utf-8", response.getContentType());
	}
}
