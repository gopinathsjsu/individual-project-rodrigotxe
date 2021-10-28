package edu.sjsu.cmpe202.myMarket.database;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.sjsu.cmpe202.myMarket.model.Category;
import edu.sjsu.cmpe202.myMarket.model.CreditCard;
import edu.sjsu.cmpe202.myMarket.model.Item;
import edu.sjsu.cmpe202.myMarket.model.Order;

public class InMemoryDB {
	
	private static InMemoryDB instance;
	
	private HashMap<String, Item> items = new HashMap<>();
	
	private ArrayList<Order> orders = new ArrayList<>();
	
	private HashSet<CreditCard> cards = new HashSet<>();
	
	private InMemoryDB( ) {
		
		Category c1 = new Category("Essentials",    300);
		Category c2 = new Category("Luxury ",        10);
		Category c3 = new Category("Miscellaneous",   6);
		
		items.put( "Clothes", new Item("Clothes", 10, 2, c1) );
		items.put( "Soap",    new Item("Soap",  1.20, 10, c1) );
		items.put( "Milk",    new Item("Milk",  3.15, 20, c1) );
		
		items.put( "Perfume",   new Item("Perfume",      3, 10, c2) );
		items.put( "Chocolate", new Item("Chocolate", 3.50,  1, c2) );
		
		items.put( "Bedsheets", new Item("Bedsheets", 12.35, 15, c3) );
		items.put( "Footwear",  new Item("Footwear",     20, 25, c3) );
		
	}
	
	public static InMemoryDB getInstance( ) {
		
		if( instance == null ) {
			
			instance = new InMemoryDB();
			
		}
		
		return instance;
		
	}

	public HashMap<String, Item> getItems() {
		return items;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}
	
	public HashSet<CreditCard> getCreditCards() {
		return cards;
	}

}