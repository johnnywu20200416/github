package tw.leonchen.jspproject.javabean;

import java.io.Serializable;

public class TestOrderSystemJavaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// JavaBean 的變數名稱 "開頭" 一定要  "小寫"
	private String orderNumber = "2025010101";
	private String customer = "mary";
	private String shipAddress = "taiwan";
	private String phone = "02-2354-2355";

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
