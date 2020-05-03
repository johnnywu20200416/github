package tw.leonchen.jspproject.javabean;

import java.util.LinkedList;

public class ShoppingCartJavaBean {
	private LinkedList<String> list = new LinkedList<String>();
	private String mySubmit;
	private String item;

	
	public String getMySubmit() {
		return mySubmit;
	}

	public void setMySubmit(String mySubmit) {
		this.mySubmit = mySubmit;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	public LinkedList<String> getProductList() {
		return list;
	}
	
	public void processRequest() {
		if(mySubmit==null || mySubmit.equals("add")) {
			addItem(item);
		}else if(mySubmit.equals("delete")) {
			removeItem(item);
		}
		
		mySubmit = null; //把舊的資料 or 已經處理完的資料 刪掉
		item = null; //把舊的資料 or 已經處理完的資料 刪掉
	}

	private void removeItem(String name) {
		list.remove(name);
	}

	private void addItem(String name) {
		list.add(name);
	}

}
