package tw.leonchen.jspproject.rd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestRedirectServletEx1.do")
public class TestRedirectServletEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int count = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.sendRedirect("https://blog.xuite.net/google_leonchen2013/mycodinglife");
		response.setHeader("Refresh", "5;URL=https://blog.xuite.net/google_leonchen2013/mycodinglife");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.write("將在 N 秒後，轉移至另一個網頁");
		
		out.close();
		
//		processRefresh(request, response);
	}

	private void processRefresh(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Refresh", "3");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.write("count=" + (count++));
		
		out.close();
	}
	
}
