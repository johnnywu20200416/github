package tw.leonchen.jspproject.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/TestSessionServletEx1")
public class TestSessionServletEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		if(session.isNew()) {
			out.write("session id: " + session.getId() + "<br/>");
			out.write("Session Created !!");
			
			session.setAttribute("name", "john");
			session.setMaxInactiveInterval(5);
			
		}else {
//			session.invalidate();
//			out.write("Session Destroyed !!");
			String name = (String)session.getAttribute("name");
			out.write("name:" + name + "<br/>");
			out.write("Session Alive<br/>");
		}
		
		out.close();
	}

}
