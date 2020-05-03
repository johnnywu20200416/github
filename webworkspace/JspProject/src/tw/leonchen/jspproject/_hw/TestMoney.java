package tw.leonchen.jspproject._hw;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/_HW_Jsp/TestMoney")
public class TestMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int count = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processMoney(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processMoney(request, response);
	}

	private void processMoney(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String checkMoney = request.getParameter("checkMoney");
		String money = request.getParameter("money");
		
		PlayerJavaBean playerMoney = (PlayerJavaBean)session.getAttribute("playerAtt");
		
		if(checkMoney.equals("Top up")) {
			
			if(money!="" && (int)Integer.parseInt(money)>=10) {
				playerMoney.setBalance((int)Integer.parseInt(money));
				session.setAttribute("playerAtt", playerMoney);
//				out.write("Let's play a game");
				response.sendRedirect("http://localhost:8080/JspProject/_HW_Jsp/TestUserLoginVerifiedServlet2_PlayerJB_New");
//				response.setHeader("Refresh", "5;URL=http://localhost:8080/JspProject/_HW_Jsp/TestUserLoginVerifiedServlet2_PlayerJB_New");
//				response.setHeader("Refresh", "5;URL=getCardClick2_includeClickProcess_circleServlet.jsp");
			}else {
				out.write("top up failed<br/>Will be exit in " + (2-count) + " times<br/>");
				count++;
				
				if(count==3) {
					out.write("Bye~Bye~<br/>");
					session.invalidate();
					response.setHeader("Refresh", "3;URL=userLogin.jsp");
				}else {
					response.setHeader("Refresh", "3;URL=moneyCheck.jsp");
				}
			}
			
		}else {
//			response.sendRedirect("http://localhost:8080/JspProject/_HW_Jsp/TestUserLoginVerifiedServlet2_PlayerJB_New");
			out.write("Bye~Bye~No<br/>");
//			session.invalidate();
//			response.sendRedirect("userLogin.jsp");
			response.setHeader("Refresh", "3;URL=http://localhost:8080/JspProject/_HW_Jsp/Logout");
//			request.getRequestDispatcher("Logout.java").forward(request, response);
		}
	
		out.close();
	}

}
