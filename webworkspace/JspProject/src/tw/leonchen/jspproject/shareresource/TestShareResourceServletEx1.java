package tw.leonchen.jspproject.shareresource;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
		urlPatterns = {"/TestShareResourceServletEx1.do"},
		initParams = {
				@WebInitParam(name = "contentType", value="text/html;charset=UTF-8"),
				@WebInitParam(name = "bgColor", value="skyblue")
		}
)
public class TestShareResourceServletEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String contentType;
	private String bgColor;

	
	public void init() throws ServletException {
		contentType = getInitParameter("contentType");
		bgColor = getInitParameter("bgColor");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType(contentType);
		PrintWriter out = response.getWriter();
		
		out.write("<html>");
		out.write("<head>");
		out.write("<title>Welcome</title>");
		out.write("</head>");
		
		out.write("<body bgcolor='" + bgColor + "'>");
		out.write("Welcome, Amigo");
		out.write("</body>");
		out.write("</html>");
		
		out.close();
	}

}
