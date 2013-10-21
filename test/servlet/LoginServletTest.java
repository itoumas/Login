package servlet;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class LoginServletTest {

	@Test
	public void test() throws Exception {
		String test = "";
	    MockHttpServletRequest request = new MockHttpServletRequest();
	    MockHttpServletResponse response = new MockHttpServletResponse();

	    LoginServlet servlet = new LoginServlet();
	    servlet.doPost(request, response);
	}
}
