package tw.leonchen.jspproject.selfpractice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SelfPractice/BookTest_2_2")
public class BookTest_2_2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		
		response.setContentType("text/html;charset=UTF-8");
		
		if(userName.equals("caterpillar") && userPwd.equals("123456")) {
			response.sendRedirect("http://localhost:8080/JspProject/SelfPractice/Book_2_2_loginSuccess.jsp");
		}else {
			response.sendRedirect("http://localhost:8080/JspProject/SelfPractice/Book_2_2_loginFail.jsp");
		}
	}

}
