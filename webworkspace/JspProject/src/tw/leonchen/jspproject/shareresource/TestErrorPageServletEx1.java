package tw.leonchen.jspproject.shareresource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/errorPage.do") //要換成 LoginSystemServletEx1 的 rd = request.getRequestDispatcher("errorPage.do") 的 errorPage.do
public class TestErrorPageServletEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<String> errMsg = (LinkedList<String>)request.getAttribute("errorMsg");
		String msg = errMsg.get(0); //如果只有一個，可以用.get(0)，如果不知道有幾個資料會帶過來，就用迴圈取值
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.write("P2 msg:" + msg + "<br/>");
		
		out.close();
	}

}
