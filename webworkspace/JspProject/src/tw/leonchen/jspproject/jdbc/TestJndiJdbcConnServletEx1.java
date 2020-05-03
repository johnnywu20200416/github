package tw.leonchen.jspproject.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TestJndiJdbcConnServletEx1.do")
public class TestJndiJdbcConnServletEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jndiJdbcConnSQLServer/OrderSystem");
			Connection conn = ds.getConnection();
			
			boolean openStatus = !conn.isClosed();
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.write("Connection Open Status:" + openStatus + "<br/>");
			
			out.close();
			conn.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
