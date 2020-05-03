package tw.leonchen.jspproject.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestJdbcConnServletEx1")
public class TestJdbcConnServletEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String urlStr = "jdbc:sqlserver://localhost:1433;databaseName=LeonPower;user=watcher;password=P@ssW0rd";
			Connection conn = DriverManager.getConnection(urlStr);
			
			Statement state = conn.createStatement();
			String sqlStr = "select * from Customer";
			ResultSet rs = state.executeQuery(sqlStr);
			
			while(rs.next()) {
				out.print(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + "<br/>");
			}
			
			rs.close();
			state.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
