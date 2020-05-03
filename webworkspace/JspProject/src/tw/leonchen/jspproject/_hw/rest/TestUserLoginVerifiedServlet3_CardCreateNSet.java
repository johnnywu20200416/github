package tw.leonchen.jspproject._hw.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import tw.leonchen.jspproject._hw.PlayerJavaBean;

@WebServlet("/_HW_Jsp/TestUserLoginVerifiedServlet3_CardCreateNSet")
public class TestUserLoginVerifiedServlet3_CardCreateNSet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PrintWriter out;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void createConn() {
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jndiJdbcConnSQLServer/PlayGames");
			conn = ds.getConnection();
			boolean connStatus = !conn.isClosed();
			out.write("Connection:" + connStatus + "<br/>");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//---前置作業-----------------------------------------------
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
//---Test Cards----------------------------------------------------	
		//player_3設定
		PlayerJavaBean player_3 = new PlayerJavaBean();
		player_3.setName("test");
		player_3.setPwd("cards");
		
		//-----------------------------------------
		//產生亂序庫克牌
		Set<Integer> poker = new LinkedHashSet<Integer>();
//		Set<Integer> pokerHS = new HashSet<Integer>();
		int randomPoker;
		
		while(poker.size()<52) {
			randomPoker = (int)(Math.random()*52);
//			randomPoker = (int)(Math.random()*52)+1;

			poker.add(randomPoker);
//			pokerHS.add(randomPoker);
		}
		out.write("Name:" + player_3.getName() + "<br/>");
		out.write("Pwd:" + player_3.getPwd() + "<br/>");
		out.write("first:" + poker.toArray()[0] + "<br/>");
		out.write("Poker:<br/>" + poker + "<br/>");
		
//		out.write("Poker:<br/>" + pokerHS + "<br/>");
		

			
//-----------------------------------------------------------------
		out.close();		
	}
	
	private boolean verifiedUserProfiles(PlayerJavaBean player) throws SQLException {
		boolean checkUserInfoStatus = false;
		String sqlStr = "select userId, userBalance from userProfiles where (userName = ? and userPwd = ?)";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		preState.setString(1, player.getName());
		preState.setString(2, player.getPwd());
		ResultSet rs = preState.executeQuery();
		
		if(checkUserInfoStatus = rs.next()) {
			player.setId(rs.getInt("userId"));
			player.setBalance(rs.getInt("userBalance"));
		}
		
		rs.close();
		preState.close();
		
		return checkUserInfoStatus;
	}
	
	
	private void closeConn() {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.write("Connection closed.<br/>");
		}
	}
	
}
