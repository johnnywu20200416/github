package tw.leonchen.jspproject.shareresource;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestContextParamRetrieveServletEx1")
public class TestContextParamRetrieveServletEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	private String code, reply;
	
	
	@Override
	public void init() throws ServletException {
		context = getServletContext();
		code = (String)context.getAttribute("Code");
		reply = (String)context.getAttribute("Reply");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		
//		PrintWriter out = response.getWriter();
//		out.write("code:" + code + "<br/>");
//		out.write("reply:" + reply + "<br/>");
		
		ServletOutputStream out = response.getOutputStream();
		out.println("code:" + code + "<br/>");
		out.println("reply:" + reply + "<br/>");
		
		out.close();
		
	}
	

}
