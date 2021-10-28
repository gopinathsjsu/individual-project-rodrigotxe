package edu.sjsu.cmpe202.myMarket.model;

public class Category {
	
	private String name;
	
	private int maxPerOrder;

	public Category( ) { }
	
	public Category(String name, int quantityPerOrder) {
		
		this.name = name;
		this.maxPerOrder = quantityPerOrder;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantityPerOrder() {
		return maxPerOrder;
	}

	public void setQuantityPerOrder(int quantityPerOrder) {
		this.maxPerOrder = quantityPerOrder;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", quantityPerOrder=" + maxPerOrder + "]";
	}
	
}
