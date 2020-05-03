package tw.leonchen.jspproject.javabean;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LotterySystem/TestLotterySystemServlet") //要注意 檔案間的 路徑位置
public class TestLotterySystemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String player = request.getParameter("player");
		String number = request.getParameter("number");
		
		
		if(player==null || player.length()==0 || number==null || number.length()==0) {
			response.sendRedirect("LotterySystem.jsp?test=1");
			
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
		
		
			try {
				int numberValue = Integer.parseInt(number);
				LotteryJavaBean LBean = new LotteryJavaBean();
				LBean.setPlayer(player);
				LBean.setDate(new Date());
			
				String myPlayer = LBean.getPlayer();
				Date date = LBean.getDate();
				LinkedList<LinkedHashSet<Integer>> result = LBean.getResult(numberValue);
			
				out.write("MyPlayer:" + myPlayer + "<br/>");
				out.write("Date:" + date + "<br/>");
				out.write("Result:" + result + "<br/>");
				out.close();
			
			}catch(Exception e) {
				response.sendRedirect("LotterySystem.jsp?test=2");
			}
		
		}
	}

}
