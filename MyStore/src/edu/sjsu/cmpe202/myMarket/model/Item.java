package edu.sjsu.cmpe202.myMarket.model;

public class Item {
	
	private String name;
	
	private double price;
	
	private int quantityStock;
	
	private Category categoty;

	public Item( ) { }
	
	public Item(String name, double price, int quantityStock, Category categoty) {
	
		this.name = name;
		this.price = price;
		this.quantityStock = quantityStock;
		this.categoty = categoty;
		
	}
	
	public Item(String name, int quantity) {
		
		this.name = name;
		this.quantityStock = quantity;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantityStock() {
		return quantityStock;
	}

	public void setQuantityStock(int quantityStock) {
		this.quantityStock = quantityStock;
	}

	public Category getCategoty() {
		return categoty;
	}

	public void setCategoty(Category categoty) {
		this.categoty = categoty;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", price=" + price + ", quantityStock=" + quantityStock + ", categoty=" + categoty
				+ "]";
	}
	
}
