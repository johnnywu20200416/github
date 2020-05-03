package tw.leonchen.jspproject._hw;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.leonchen.jspproject._hw.dao.IPlayerDao;
import tw.leonchen.jspproject._hw.dao.PlayerDaoFactory;

@WebServlet("/_HW_Jsp/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processLogout(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processLogout(request, response);
	}

	private void processLogout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		PlayerJavaBean player = (PlayerJavaBean)session.getAttribute("playerAtt");
		int balance2Update = player.getBalance();
		
		//這裡要補 DAO 的程式
//		IPlayerDao dao = PlayerDaoFactory.createPlayerDao();
//		try {
//			dao.updateBalance(player);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		session.invalidate();
//		out.write("Session Invalidate.");
		
		response.sendRedirect("userLogin.jsp");
//		response.setHeader("Refresh", "10;URL=userLogin.jsp");
	}
}
