package tw.leonchen.jspproject._hw.rest;

import java.util.LinkedList;

public class PlayerJavaBean_OriginCopy {
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

	//原本的 getCard()，改成 getCard(String output) ， 為了和下面的 public String getCard() 區別
	public LinkedList<Integer> getCard(String output) {
		return card;
	}

	//改寫給 JSP ${}用
	public String getCard() {
		return showCards();
	}
	
	public void setCard(int cardValue) {
		this.card.add(cardValue);
	}
	
	//新增測試：用來在Jsp中輸出
	public String showCards() {
		char flower[] = { '\u2660', '\u2665', '\u2666', '\u2663' }; //花色
		String number[] = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
		
		int suit;
		int value;
		String outputCard = "";
		LinkedList<Integer> playerCards = this.card;
		
		for(int i=0;i<playerCards.size();i++) {
			int cardNum = playerCards.get(i);
			suit = cardNum / 13;
			value = cardNum % 13;
			outputCard += "Card" + (i+1) + ": " + flower[suit] + number[value] + "<br/>";
		}	
		return outputCard;
		
	}

	public int getBomb() {
		return bomb;
	}

	public void setBomb(int bomb) {
		this.bomb = bomb;
	}
	
}
