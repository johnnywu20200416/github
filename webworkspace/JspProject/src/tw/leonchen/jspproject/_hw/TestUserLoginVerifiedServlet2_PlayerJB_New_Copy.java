package tw.leonchen.jspproject._hw;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.leonchen.jspproject._hw.dao.IPlayerDao;
import tw.leonchen.jspproject._hw.dao.PlayerDaoFactory;

@WebServlet("/_HW_Jsp/TestUserLoginVerifiedServlet2_PlayerJB_New_copy")
public class TestUserLoginVerifiedServlet2_PlayerJB_New_Copy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private HttpSession session;
	private PlayerJavaBean player = new PlayerJavaBean();
	private PlayerJavaBean computer = new PlayerJavaBean();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
//		PlayerJavaBean player = new PlayerJavaBean();
//		PlayerJavaBean computer = new PlayerJavaBean();
		//---前置作業-----------------------------------------------
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		session = request.getSession();

		//判斷是否為同一玩家，重新遊玩
		//參考網址：https://blog.csdn.net/lisheng19870305/article/details/40738169
		if(session.getAttribute("playerAtt")==null) {
//			player = new PlayerJavaBean();
//			computer = new PlayerJavaBean(); //建立computer
	
			try {
				IPlayerDao dao = PlayerDaoFactory.createPlayerDao();

				player.setName(request.getParameter("userNameForm"));
				player.setPwd(request.getParameter("userPwdForm"));

				if(dao.verifiedUserProfiles(player)) {
					out.write("Login Success<br/>welcome, " + player.getName() + "<br/>");
					
					if(player.getBalance()<10) {
						out.write("Pay me my money~<br/>In~Cash~<br/>");
						request.getRequestDispatcher("moneyCheck.jsp").forward(request, response);
					}
						
					
					
					//後續要做判斷用的
					computer.setId(-1);
					session.setAttribute("playerAtt", initialized(player)); //playerAtt => playerAttribute
					session.setAttribute("computerAtt", initialized(computer));
					
					session.setAttribute("playerShow", player.showCards());
					session.setAttribute("computerShow", computer.showCards());
					
					
					
//					response.sendRedirect("getCardClick2_includeClickProcess_circleServlet.jsp");
					//為了要延遲3秒再轉
					response.setHeader("Refresh", "3;URL=getCardClick2_includeClickProcess_circleServlet.jsp");
					
					
				}else {
					out.write("Login failed.<br/>");
					response.setHeader("Refresh", "3;URL=userLogin.jsp");
//					request.getRequestDispatcher("userLogin.jsp").forward(request, response);;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
//			  catch (ServletException e) {
//				e.printStackTrace();
//			}
			
		}else { //session is not new
			
			if(((PlayerJavaBean)session.getAttribute("playerAtt")).getBalance()<10) {
				out.write("Pay me my money~<br/>In~Cash~<br/>");
				request.getRequestDispatcher("moneyCheck.jsp").forward(request, response);
			}else {
				
			
			
			computer.setId(-1);
			session.setAttribute("playerAtt", initialized(player)); //playerAtt => playerAttribute
			session.setAttribute("computerAtt", initialized(computer));
			
			session.setAttribute("playerShow", player.showCards());
			session.setAttribute("computerShow", computer.showCards());
			
			
			
//			response.sendRedirect("getCardClick2_includeClickProcess_circleServlet.jsp");
			//為了要延遲3秒再轉
//			out.write("play again without login<br/>");
			response.setHeader("Refresh", "5;URL=getCardClick2_includeClickProcess_circleServlet.jsp");
			}
		}

		
		out.close();
		
	}
	
	
	private PlayerJavaBean initialized(PlayerJavaBean character) {
		int CardSize = character.getCard().size();
		
		//bomb值 初始化
		character.setBomb(0);
		
//		if(CardSize!=0) {
//			character.getCard("").clear();
//			CardSize = 0;
//		}
//		
//		if(character.getId()<0) {
//			character.setCard(26);
//			character.setCard(39);
//		}else {
//			character.setCard(0);
//			character.setCard(13);
//		}

		
		//---------------------	
		
		LinkedList<Integer> setCards = new LinkedList<Integer>();
		
		CardJavaBean poker = new CardJavaBean();
		int cardsLeft = poker.getCardsLeft();
		ArrayList<Integer> pokerCards = poker.getCards();

		if(cardsLeft<=10) {
			poker.setCards(pokerCards);
			cardsLeft = poker.getCardsOrigin();
		}
		
		//牌初始化：player, computer各2張
		if(CardSize!=0) {
			character.getCard().clear();
			CardSize = 0;
		}
		for(;CardSize<2;CardSize++) {
			setCards.add((int)pokerCards.remove((int)(Math.random() * cardsLeft--)));
		}
		character.setCard(setCards);
		
		
		poker.setCards(pokerCards);
		poker.setCardsLeft(cardsLeft);
		
		session.setAttribute("pokerAtt", poker);
		
		return character;
	}
	
}
