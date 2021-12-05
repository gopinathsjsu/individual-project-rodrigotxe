package edu.sjsu.cmpe202.myMarket.database;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.sjsu.cmpe202.myMarket.model.Item;
import edu.sjsu.cmpe202.myMarket.model.Order;

public class InMemoryDB {
	
	private static InMemoryDB instance;
	
	private HashMap<String, Integer> categoryCap = new HashMap<>();
	
	private HashMap<String, Item> items = new HashMap<>();
	
	private HashSet<String> cards = new HashSet<>();
	
	private ArrayList<Order> orders = new ArrayList<>();
	
	private InMemoryDB( ) {
		
		categoryCap.put("Luxury", 3);
		categoryCap.put("Essential", 5);
		categoryCap.put("Misc", 6);
		
	}
	
	public static InMemoryDB getInstance( ) {
		
		if( instance == null ) 
			
			instance = new InMemoryDB();
			
		return instance;
		
	}

	public HashMap<String, Integer> getCategoryCap() {
		return categoryCap;
	}
	
	public HashMap<String, Item> getItems() {
		return items;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}
	
	public HashSet<String> getCreditCards() {
		return cards;
	}

}