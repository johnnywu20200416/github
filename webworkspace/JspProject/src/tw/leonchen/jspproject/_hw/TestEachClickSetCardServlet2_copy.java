package tw.leonchen.jspproject._hw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/_HW_Jsp/TestEachClickSetCardServlet_copy")
public class TestEachClickSetCardServlet2_copy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private HttpSession session;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processEachClick(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processEachClick(request, response);
	}

	private void processEachClick(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		session = request.getSession();

		PlayerJavaBean playerClick = (PlayerJavaBean) session.getAttribute("playerAtt");
		PlayerJavaBean computerClick = (PlayerJavaBean) session.getAttribute("computerAtt");
		CardJavaBean pokerClick = (CardJavaBean) session.getAttribute("pokerAtt");

		LinkedList<Integer> playerSetCards = playerClick.getCard();
		LinkedList<Integer> computerSetCards = computerClick.getCard();
		
		// 要改成用 cardJavaBean----------------------------
		// 如果牌數量 < 10張，重置 52張牌 ?????
//		CardJavaBean poker = new CardJavaBean();
		int cardsLeft = pokerClick.getCardsLeft();
		ArrayList<Integer> pokerCards = pokerClick.getCards();
		if (cardsLeft <= 10) {
			pokerClick.setCards(pokerCards);
			cardsLeft = pokerClick.getCardsOrigin();
		}
		// ---------------------------------------------

		String getCards = request.getParameter("getCard"); // 從Jsp的Click進來做動作
		String computerAction = "No";

		if (getCards.equals("Yes")) {
		
			
			//玩家要牌，給他牌
			playerSetCards.add((int) pokerCards.remove((int) (Math.random() * cardsLeft--)));
//			playerClick.setCard((int) pokerCards.remove((int) (Math.random() * cardsLeft--)));
			playerClick.setCard(playerSetCards);
			
			session.setAttribute("playerShow", playerClick.showCards());
			
			
			
			if(bomb(playerClick)<=21) { //沒有
				// 繼續
				response.sendRedirect("getCardClick2_includeClickProcess_circleServlet.jsp");
//				out.write("給 開司 一張牌<br/>");
//				response.setHeader("Refresh", "10;URL=getCardClick2_includeClickProcess_circleServlet.jsp");
				
			}else { //爆了
				
				out.write("Player lose");

				whoWin(bomb(playerClick), bomb(computerClick));
//				session.setAttribute("gameResult", "computer");
				
				computerClick.setId(0);
				
				session.setAttribute("computerShow", computerClick.showCards());
				
				response.sendRedirect("resultPage.jsp");
//				response.setHeader("Refresh", "15;URL=resultPage.jsp");
			}

		} else {
			// Click "No"
			// 換莊家的回合
			out.write("莊家：輪到我的回合<br/>");
			int sum;

			while (computerAction.equals(getCards)) {
				
				
				if (bomb(computerClick) <= 16) { // totalCardValue <=16 "要" 補牌
//					out.write("Try to get more card<br/>");
					computerSetCards.add((int) pokerCards.remove((int) (Math.random() * cardsLeft--)));
					computerClick.setCard(computerSetCards);
//					computerClick.setCard((int) pokerCards.remove((int) (Math.random() * cardsLeft--)));
//					out.write("get:" + computerClick.getCard("").getLast());
					

				} else {
					//17 <= sum <= 21：停止補牌 => see result
					//sum > 21：BOOM => see result
					
					computerAction = "End";
					computerClick.setId(0);
					
					//判斷誰贏
					
					whoWin(bomb(playerClick), bomb(computerClick));
					
					session.setAttribute("computerShow", computerClick.showCards());
					
//					out.write("last value= " + computerClick.getCard(""));
//					out.write("<br/>Let's see result<br/>");
					
//					session.setAttribute("pokerAtt", pokerClick);
//					response.setHeader("Refresh", "15;URL=resultPage.jsp");
					response.sendRedirect("resultPage.jsp");
				}

			} //end while

		} //end 最外層  if-else

		//讓 player && computer 影響的 poker數量 (cardsLeft) 都會被記錄
		pokerClick.setCardsLeft(cardsLeft);
		pokerClick.setCards(pokerCards);
		session.setAttribute("pokerAtt", pokerClick);
//		response.sendRedirect("resultPage.jsp");
	}

	private void whoWin(int playerBomb, int computerBomb) {
		PlayerJavaBean player = (PlayerJavaBean)session.getAttribute("playerAtt");
		int balance = player.getBalance();
		
		out.write("playerBomb:" + playerBomb + "<br/>");
		out.write("computerBomb:" + computerBomb + "<br/>");
		
		String result = "";
		
		if(playerBomb <= 21 && computerBomb <= 21 && playerBomb==computerBomb) {
			result = "tied";
		}
		
		if(playerBomb>21 || (computerBomb<=21 && computerBomb>playerBomb)) {
			result = "computer";
			balance -= 10;
		}
		
		if(playerBomb<=21 && (computerBomb>21 || playerBomb>computerBomb)) {
			result = "player";
			balance += 10;
		}
		
		player.setBalance(balance);
		
		out.write("Balance=" + balance + "<br/>");
		session.setAttribute("gameResult", result);
		session.setAttribute("playerAtt", player);
	}
	
	
	private int bomb(PlayerJavaBean character) {
		LinkedList<Integer> eachCard = character.getCard();
		int sum = 0;
		
		for(int i=0;i<2;i++) {
			sum = sumCardValue(sum, eachCard.get(i));
		}
		
		for(int j=2;j<eachCard.size();j++) {
			sum = sumCardValue(sum, eachCard.get(j));
		}
		
		if(sum>21) {
			for(int k=0;k<eachCard.size();k++) {
				if(eachCard.get(k)%13==0) {
					sum = sum -10;
					break;
				}
			}
		}
		
		character.setBomb(sum);
		if(character.getId()<0) {
			session.setAttribute("computerAtt", character);
		}else if(character.getId()>0) {
			session.setAttribute("playerAtt", character);
		}
		
		return sum;
		
	}
	
	

	private int sumCardValue(int sum, int cardValue) {
		cardValue %= 13;
		// 設定牌面的值
		if (cardValue == 0) {
			// get A
			if ((21 - sum) >= 11) {
				cardValue = 11;
			} else {
				cardValue = 1;
			}
		} else if (cardValue >= 9) {
			// get 10, J, Q, K
			cardValue = 10;
		} else {
			// get 2~9
			++cardValue;
		}
		
		
		sum += cardValue;
	

		return sum;
	}
}
