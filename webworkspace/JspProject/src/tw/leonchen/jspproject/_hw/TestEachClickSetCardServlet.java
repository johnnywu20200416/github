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

@WebServlet("/_HW_Jsp/TestEachClickSetCardServlet")
public class TestEachClickSetCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private HttpSession session;
	private boolean hasA = false; //牌組內有 "A"被設為11 為true

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
		
		// 要改成用 cardJavaBean-------------------------------------------
		// 如果牌數量 < 10張，重置 52張牌 ?????
		int cardsLeft = pokerClick.getCardsLeft();
		ArrayList<Integer> pokerCards = pokerClick.getCards();
//		if (cardsLeft <= 10) {
//			cardsLeft = pokerClick.getCardsOrigin();
//			ArrayList<Integer> resetPoker = new ArrayList<Integer>();
//			for (int i = 0; i < cardsLeft; i++) {
//				resetPoker.add(i);
//			}
//			pokerClick.setCards(resetPoker);
//		}
		// --------------------------------------------------------------

		String getCards = request.getParameter("getCard"); // 從Jsp的Click進來做動作
		String computerAction = "No"; //電腦自動補牌的 while 的判斷條件

		if (getCards.equals("Yes")) {
		
			//玩家要牌，給他牌
			playerSetCards.add((int) pokerCards.remove((int) (Math.random() * cardsLeft--)));
			playerClick.setCard(playerSetCards);
			
			session.setAttribute("playerShow", playerClick.showCards());
			
			if(bomb(playerClick)<=21) { //沒有爆
				// 繼續
				response.sendRedirect("getCardClick2_includeClickProcess_circleServlet.jsp");
//				response.setHeader("Refresh", "5;URL=getCardClick2_includeClickProcess_circleServlet.jsp");
				//參考網址：https://stackoverflow.com/questions/2123514/java-lang-illegalstateexception-cannot-forward-sendredirect-create-session
				//避免 java.lang.IllegalStateException: 
				//Cannot call sendRedirect() after the response has been committed
				return;
		
			}else { //爆了
				whoWin(bomb(playerClick), bomb(computerClick));
				computerClick.setId(0);
				session.setAttribute("computerShow", computerClick.showCards());
			}

		} else {
			// Click "No"
			// 換莊家的回合
			while (computerAction.equals(getCards)) {
				
				if (bomb(computerClick) <= 16) { // totalCardValue <=16 "要" 補牌
					computerSetCards.add((int) pokerCards.remove((int) (Math.random() * cardsLeft--)));
					computerClick.setCard(computerSetCards);
					
				} else {
					//17 <= sum <= 21：停止補牌 => see result
					//sum > 21：BOOM => see result
					computerAction = "End"; //用來終止 while 迴圈
					computerClick.setId(0);
					whoWin(bomb(playerClick), bomb(computerClick));
					session.setAttribute("computerShow", computerClick.showCards());
				}
				
			} //end while
			
		} //end 最外層  if-else

		//讓 player && computer 影響的 poker數量 (cardsLeft) 都會被記錄
		pokerClick.setCardsLeft(cardsLeft);
		pokerClick.setCards(pokerCards);
		session.setAttribute("pokerAtt", pokerClick);
		response.sendRedirect("resultPage.jsp");
//		return;
	}

	private void whoWin(int playerBomb, int computerBomb) {
		PlayerJavaBean player = (PlayerJavaBean)session.getAttribute("playerAtt");
		int balance = player.getBalance();
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
		session.setAttribute("gameResult", result);
		session.setAttribute("playerAtt", player);
	}
	
	private int bomb(PlayerJavaBean character) {
		LinkedList<Integer> eachCard = character.getCard();
		int sum = 0;
		
		//初始化發牌的2張牌 的總牌值
		for(int i=0;i<2;i++) {
			sum = sumCardValue(sum, eachCard.get(i));
		}
		
		//後續拿牌 的總牌值
		for(int j=2;j<eachCard.size();j++) {
			sum = sumCardValue(sum, eachCard.get(j));
		}
		
		//----------------------------------------------------
		//如果總牌值超過21點，且牌組內有 "A"被設為11 的情況
		if(sum>21 && hasA) {
			sum = sum - 10;
			hasA = false;
		}
		//------------------------------------------------------
		//將結果存回，並依Id決定是存到player 還是 computer
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
				hasA = true;
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
		
		
//		cardValue = (cardValue == 0)?(((21-sum)>=11)?11:1):((cardValue >= 9)?10:(cardValue+1));
		sum += cardValue;
		return sum;
	}
}
