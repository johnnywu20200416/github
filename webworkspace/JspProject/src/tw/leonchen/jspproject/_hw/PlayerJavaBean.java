package tw.leonchen.jspproject._hw;

import java.util.LinkedList;

public class PlayerJavaBean {
	private int id;
	private String name;
	private String pwd;
	private int balance;
	private LinkedList<Integer> card = new LinkedList<Integer>();
	private int bomb;

	public int getId() {
		return id;
	}

	public void setId(int userId) {
		this.id = userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String userName) {
		this.name = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String userPwd) {
		this.pwd = userPwd;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int userBalance) {
		this.balance = userBalance;
	}

	public LinkedList<Integer> getCard() {
		return card;
	}

//	//改寫給 JSP ${}用
//	public String getCard(String output) {
//		return showCards();
//	}
	
	public void setCard(LinkedList<Integer> card) {
		this.card = card;
	}
	
	public int getBomb() {
		return bomb;
	}

	public void setBomb(int bomb) {
		this.bomb = bomb;
	}
	
	//新增測試：用來在Jsp中輸出
	public String showCards() {
		char flower[] = { '\u2660', '\u2665', '\u2666', '\u2663', '\u2588'}; //花色
		String number[] = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
		
		int suit;
		int value;
		String outputCard = "";
		LinkedList<Integer> playerCards = this.card;
		int comId = this.id; //用來蓋住 computer 的第一張牌
			
		for(int i=0;i<playerCards.size();i++) {
			if(comId==-1) {
				outputCard += "Card1: " + flower[4] + "<br/>";
				comId = 0;
				continue;
			}
			int cardNum = playerCards.get(i);
			suit = cardNum / 13;
			value = cardNum % 13;
			outputCard += "Card" + (i+1) + ": " + flower[suit] + number[value] + "<br/>";
		}
		
		return outputCard;
		}

}
