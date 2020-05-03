package tw.leonchen.jspproject._hw.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.leonchen.jspproject._hw.PlayerJavaBean;

@WebServlet("/_HW_Jsp/TestUserGetCardsServlet")
public class TestUserGetCardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processGetCard(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processGetCard(request, response);
	}

	private void processGetCard(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//player_3設定
				PlayerJavaBean player_card = new PlayerJavaBean();
				player_card.setName("get");
				player_card.setPwd("cards");
				
				//-----------------------------------------
		
				ArrayList card = new ArrayList();
				int cardsLeft = 52, randomNum, playerGetNumber;
				//建立52張牌
				for (int i = 0; i < cardsLeft; i++) {
					card.add(i);
				}
				
				PlayerJavaBean playerClick = (PlayerJavaBean) session.getAttribute("playerAtt");
				LinkedList<Integer> playerSetCards = playerClick.getCard();
				
				String getCards = request.getParameter("getCard");
				if(getCards.equals("Yes")) {
					
					for(int i=0;i<3;i++) {
//					//從牌堆取牌
//					randomNum = (int) (Math.random() * cardsLeft--);
//					playerGetNumber = (int) (card.remove(randomNum));
//					//給player
//					player_card.setCard(playerGetNumber);
//					//檢查執行結果
//					out.write("playerGetNumber:" + playerGetNumber + "<br/>");
//					out.write("player_card:" + player_card.getCard() + "<br/>");
					
					//前三行變成一行
					
					playerSetCards.add((int)card.remove((int)(Math.random() * cardsLeft--)));
					}
					
					playerClick.setCard(playerSetCards);
					
					out.write("------------------------------------------------<br/>");
					showCards(player_card);
					
				}else {
					out.write("Bye~");
				}
				
				
//		String getCards = request.getParameter("getCard");
//		if(getCards.equals("Yes")) {
//			//要給player繼續拿牌....
//			
//			out.write("給 開司 一張牌<br/>");
//			out.write("<a href='http://localhost:8080/JspProject/_HW_Jsp/getCardClick.jsp'>back to lastPage</a>");
//		}else {
//			//中斷給牌動作....
//			
//			out.write("莊家：輪到我的回合<br/>");
//			out.write("<a href='http://localhost:8080/JspProject/_HW_Jsp/getCardClick.jsp'>back to lastPage</a>");
//		}
				
	}
	
	//為了用JSP ${}，先修改getCard
	private void showCards(PlayerJavaBean player) {
		char flower[] = { '\u2660', '\u2665', '\u2666', '\u2663' }; //花色
		String number[] = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
		
		int suit;
		int value;
		String outputOriginCardValue = "";
		LinkedList<Integer> playerCards = player.getCard();
		
		for(int i=0;i<playerCards.size();i++) {
			int cardNum = playerCards.get(i);
			suit = cardNum / 13;
			value = cardNum % 13;
			out.write("Card" + (i+1) + ": " + flower[suit] + number[value] + "<br/>");
			//之後改成
			//String pokerReal = (flower[suit] + number[value]);  ???
			//String flower[] = {英文字} => 用<img src="(放pokerReal)"> =>直接輸出圖片????
		}
		
//		System.out.print(flower[suit] + number[value] + "\t");
	}

}
