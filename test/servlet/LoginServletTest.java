package servlet;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;

import static org.mockito.Mockito.*;

public class LoginServletTest {

	@Test
	public void test() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		//ServletContextのモックオブジェクトを作成します。
		MockServletContext mockServletContext = new MockServletContext();

		//LoginServletをspyでオブジェクト化します。
		LoginServlet loginServlet = spy(new LoginServlet());

		//getServletContextメソッドが呼び出されたら強制的にmockServletContextを返します。
		doReturn(mockServletContext).when(loginServlet).getServletContext();

		request.setParameter("user_id", "itou");
		request.setParameter("password", "itou");

		loginServlet.doPost(request, response);

		//モックメソッドgetServletContextが実行されたかを検証します。
		verify(loginServlet).getServletContext();

//		verifyForwardPath("/WEB-INF/Welcome.jsp");
	}
}
