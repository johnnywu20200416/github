package tw.leonchen.jspproject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/myGenericServlet.action")
public class TestGenericServletEx1 extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8"); //設定文字的格式和編碼：如果沒加，IE可以正常換行，但是chrome和firefox會當成字串輸出
		PrintWriter out = response.getWriter();
		
		out.write("hi, mary<br/>Have a nice day."); //用<br/>換行，println()=>不會幫忙換行，變成和print()一樣
		
		out.close();
	}

}
