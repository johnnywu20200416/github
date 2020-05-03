package tw.leonchen.jspproject.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/TestCookieServletEx1.do")
public class TestCookieServletEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie cookieToClient = new Cookie("myName", "leonChen");
		cookieToClient.setMaxAge(30*24*60*60); //單位是 second
		response.addCookie(cookieToClient);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//-------------------------------------------------------------
//		HttpSession session = request.getSession();
//		Integer count = (Integer)session.getAttribute("count");
//		
//		if(count==null) {
//			count = 0;
//		}
//		
//		count++;
//		session.setAttribute("count", count);
//		out.write("count:" + count + "<br/>");
//		
//		out.write("<a href='" + response.encodeURL("TestCookieServletEx1.do") + "'>Link</a>");
//		
		//-----------------------------------------------------------------
		
		
		Cookie[] cookies = request.getCookies();
		for(int i=0;i<cookies.length;i++) {
			out.write("Name:" + cookies[i].getName() + "<br/>");
			out.write("Value:" + cookies[i].getValue() + "<br/>");
		}
		
		out.write("cookies:" + cookies + "<br/>");
		out.write("Cookie is ready.<br/>");
		
		out.close();
	}

}
