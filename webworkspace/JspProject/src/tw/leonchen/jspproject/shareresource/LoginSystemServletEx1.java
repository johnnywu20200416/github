package tw.leonchen.jspproject.shareresource;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginSystemServletEx1")
public class LoginSystemServletEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String userName, userPwd;
	private String systemUserName = "mary", systemUserPwd = "12345";
	private RequestDispatcher rd;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		userName = request.getParameter("userName");
		userPwd = request.getParameter("userPwd");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		LinkedList<String> errMsg = new LinkedList<String>();
		
		if(userName!=null && userName.length()!=0 && userPwd!=null && userPwd.length()!=0) {
			if(!userName.equalsIgnoreCase(systemUserName) || !userPwd.equalsIgnoreCase(systemUserPwd)) {
				errMsg.add("userName or UserPwd is not correct");
				
				request.setAttribute("errorMsg", errMsg);
				
				rd = request.getRequestDispatcher("errorPage.do");
				rd.forward(request, response);
			}
			
			out.write("welcome");
		}else {
			out.write("Please input userName and userPassword.");
		}
		
		out.close();
	}

}
