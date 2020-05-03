package tw.leonchen.jspproject.shareresource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class TestContextParamListenerEx2 implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("System Destroyed.");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String codeValue = context.getInitParameter("Code");
		String replyValue = context.getInitParameter("Reply");
		
		context.setAttribute("Code", codeValue);
		context.setAttribute("Reply", replyValue);
	}

}
