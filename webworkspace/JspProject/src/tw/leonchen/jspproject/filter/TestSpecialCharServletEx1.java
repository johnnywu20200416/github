package tw.leonchen.jspproject.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckSpecialCharAction.do")
public class TestSpecialCharServletEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		
		String userNewName = replaceSpecialChar(userName);
		String userNewPwd = replaceSpecialChar(userPwd);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.write("userName:" + userName + "<br/>");
		out.write("userPwd:" + userPwd + "<br/>");
		
		out.write("userNewName:" + userNewName + "<br/>");
		out.write("userNewPwd:" + userNewPwd + "<br/>");
		
		out.close();
	}

	private String replaceSpecialChar(String value) {
		value = value.replaceAll("<", "&lt;");
		value = value.replaceAll(">", "&gt;");
		return value;
	}

}
