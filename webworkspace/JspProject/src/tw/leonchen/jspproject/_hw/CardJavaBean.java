package tw.leonchen.jspproject._hw;

import java.util.ArrayList;

public class CardJavaBean {
	private int cardsOrigin = 52;
	private int cardsLeft = 0;
	private ArrayList<Integer> card = new ArrayList<Integer>();

	public int getCardsOrigin() {
		return cardsOrigin;
	}

	//用不到
//	public void setCardsOrigin(int cardsOrigin) {
//		this.cardsOrigin = cardsOrigin;
//	}

	public int getCardsLeft() {
		return cardsLeft;
	}

	public void setCardsLeft(int cardsLeft) {
		this.cardsLeft = cardsLeft;
	}

	public ArrayList<Integer> getCards() {
		return card;
	}

	public void setCards(ArrayList<Integer> card) {
			this.card = card;	
	}

}
