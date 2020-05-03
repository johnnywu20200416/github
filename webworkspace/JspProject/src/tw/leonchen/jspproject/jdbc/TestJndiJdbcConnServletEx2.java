package tw.leonchen.jspproject.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TestJndiJdbcConnServletEx2.do")
public class TestJndiJdbcConnServletEx2 extends HttpServlet {
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
			DataSource ds = (DataSource)context.lookup("java:comp/env/jndiJdbcConnSQLServer/OrderSystem");
			conn = ds.getConnection();
			
			boolean openStatus = !conn.isClosed();
			out.write("Connection Open Status:" + openStatus + "<br/>");
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
			
		createConn();
		
		//.....
		try {
			//boolean loginStatus = processVerifiedProfiles("mary", "test123");
			boolean loginStatus = processVerifiedProfiles(userName, userPwd);
			out.write("Login Status:" + loginStatus + "<br/>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		closeConn();
		
		out.close();
	}
	
	
	private boolean processVerifiedProfiles(String userName, String userPwd) throws SQLException {
		String sqlStr = "select * from Profiles where userName = ? and userPwd = ?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		preState.setString(1, userName);
		preState.setString(2, userPwd);
		ResultSet rs = preState.executeQuery();
		
		boolean checkStatus = rs.next();
		
		rs.close();
		preState.close();
		
		return checkStatus;
	}

	public void closeConn() {
		
		if(conn!=null) {
			try {
				conn.close();
				out.write("Connection Closed" + "<br/>");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	

}
