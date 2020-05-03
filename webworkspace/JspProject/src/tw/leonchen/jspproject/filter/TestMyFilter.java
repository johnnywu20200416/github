package tw.leonchen.jspproject.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/myFilterAction.do")
public class TestMyFilter implements Filter {

	private ServletContext context;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		context = filterConfig.getServletContext();
	}	

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		arg1.setContentType("text/html;charset=UTF-8");
		PrintWriter out = arg1.getWriter();
		
		Integer countValue = (Integer)context.getAttribute("count");
		
		if(countValue==null) { //如果context裡面沒有 "count" 這個屬性，則 context.getAttribute("count") 會回傳 null
			countValue = 0;
		}
		
		countValue++;
		context.setAttribute("count", countValue);
		
		out.write("You're " + countValue + " visitors<br/>"); //Servlet執行之前
		
		
		arg2.doFilter(arg0, arg1); //分界
		
		
		out.write("finished"); //Servlet執行之後
		out.close();
	}
	
	@Override
	public void destroy() {
		context = null;
	}

}
