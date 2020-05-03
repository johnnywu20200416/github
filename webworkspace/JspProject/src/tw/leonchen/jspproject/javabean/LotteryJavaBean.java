package tw.leonchen.jspproject.javabean;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class LotteryJavaBean {
	private Date date;
	private String player;
	private LinkedList<LinkedHashSet<Integer>> result = new LinkedList<LinkedHashSet<Integer>>();
	
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public void setPlayer(String player) {
		this.player = player;
	}
	
	
	public LinkedList<LinkedHashSet<Integer>> getResult(int number) {
		
		result.clear(); //確保之前的資料不會留在 result 裡面
		
		for(int i=1;i<=number;i++) {
			LinkedHashSet<Integer> record = new LinkedHashSet<Integer>();
			
			while(record.size()<6) {
				int luckyNum = (int)(Math.random()*42)+1;
				record.add(luckyNum);
			}
			
			result.add(record);
		}
		
		return result;
	}
	
	
}
