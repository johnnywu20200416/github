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
public class TestContextParamListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("System Destroyed.");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("System Initialized.");
		
		ServletContext context = sce.getServletContext();
		String myResourceFile = context.getInitParameter("myResourceFile");
		String myDbServer = context.getInitParameter("myDbServer");
		
		System.out.println("myResourceFile:" + myResourceFile);
		System.out.println("myDbServer:" + myDbServer);
		
//		InputStream is1 = context.getResourceAsStream(myResourceFile);
//		InputStreamReader isr1 = new InputStreamReader(is1);
//		BufferedReader br1 = new BufferedReader(isr1);
		
		//上面三行合併
		BufferedReader br1 = new BufferedReader(new InputStreamReader(context.getResourceAsStream(myResourceFile)));
		
		String data;
		
		try {
			while((data = br1.readLine())!=null) {
				System.out.println("data:" + data);
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally { //finally：最後一定要 close，不管如何都要做
			try {
				br1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
