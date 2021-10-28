package edu.sjsu.cmpe202.myMarket.model;

import java.time.LocalDateTime;
import java.util.HashSet;

public class Order {
	
	private LocalDateTime dataOrder = LocalDateTime.now();
	
	private HashSet<Item> items = new HashSet<>();
	
	private double totalPrice;
	
	private CreditCard creditCard;
	
	public Order( ) { }

	public LocalDateTime getDataOrder() {
		return dataOrder;
	}

	public void setDataOrder(LocalDateTime dataOrder) {
		this.dataOrder = dataOrder;
	}

	public HashSet<Item> getItems() {
		return items;
	}

	public void setItems(HashSet<Item> items) {
		this.items = items;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@Override
	public String toString() {
		return "Order [dataOrder=" + dataOrder + ", items=" + items + ", totalPrice=" + totalPrice + ", creditCard="
				+ creditCard + "]";
	}

}
