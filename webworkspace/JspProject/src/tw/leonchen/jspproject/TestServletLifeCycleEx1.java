package tw.leonchen.jspproject;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServletLifeCycleEx1")
public class TestServletLifeCycleEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init()"); // 會顯示在console裡面
	}

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("service()");
		HttpServletRequest res = (HttpServletRequest)request;
		HttpServletResponse rep = (HttpServletResponse)response;
		service(res, rep);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("doGet()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("doPost()");
	}

	public void destroy() {
		System.out.println("destroy()");
	}

}
