package edu.sjsu.cmpe202.myMarket.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import edu.sjsu.cmpe202.myMarket.database.InMemoryDB;
import edu.sjsu.cmpe202.myMarket.helper.FileHandler;
import edu.sjsu.cmpe202.myMarket.model.Category;
import edu.sjsu.cmpe202.myMarket.model.CreditCard;
import edu.sjsu.cmpe202.myMarket.model.Item;
import edu.sjsu.cmpe202.myMarket.model.Order;

public class OrderController {
	
	private InMemoryDB db = InMemoryDB.getInstance();
	
	private Order currentOrder = new Order();
	
	private FileHandler orderFile;
	
	private HashMap<String, Integer> categoryLimit = new HashMap<>();
	
	private HashMap<String, Integer> categoryTotal = new HashMap<>();
	
	private ArrayList<String> outputMessage = new ArrayList<>();
	
	private HashSet<Item> items = new HashSet<>();
	
	private String creditCardNumber;
	
	private double totalPrice = 0;
	
	public OrderController ( String filePath ) {
		
		orderFile = new FileHandler( filePath );
		
	}
	
	public void startOrder( ) {
		
		try {
			
			orderFile.readFile();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		getItems( orderFile.getFileContent(  ) );
		
	}
	
	public boolean checkOrder( ) {
		
		checkItemStock();
		checkItemCategory();
		
		return outputMessage.isEmpty();
		
	}
	
	public void calculateTotalPrice( ) {
		
		items.forEach( (item) -> {
			
			totalPrice += item.getQuantityStock() * item.getPrice();
			
		});
		
		
		currentOrder.setTotalPrice(totalPrice);
		
	}
	
	public double getTotalPrice() {
		
		return currentOrder.getTotalPrice();
	
	}
	
	public void enterCreditCardNumber() {
		
		CreditCard creditCard= new CreditCard(creditCardNumber);
		
		currentOrder.setCreditCard( creditCard );
		
		if( ! db.getCreditCards().contains( creditCard ) )
		
			db.getCreditCards().add( creditCard );
		
	}
	
	public void checkoutOrder(  ) {
		
		db.getOrders().add( currentOrder );
		
		generateOutputFile( );
		
	} 
	
	public void printMessage( ) {
		
		for ( String line : outputMessage ) {
			
			System.out.println( line );
			
		}
		
	}
	
	private void getItems( ArrayList<String> fileContent ) {
		
		for( String line : fileContent ) {
			
			String[] item = line.split(",", 2);

			if ( item.length == 2 ) {

				if ( db.getItems().containsKey(item[0]) ) {

					item[1] = item[1].replaceAll("\\s+", "");

					items.add( new Item( item[0], Integer.parseInt( item[1] ) ) );

				}

			} else {

				creditCardNumber = line;

			}

		}  
		
	}
	
	private boolean checkItemStock( ) {
		
		StringBuilder message = new StringBuilder();
		
		for( Item currentItem : items ) {
			
			Item item = db.getItems().get( currentItem.getName() );
			
			sumItemQuantityByCategory( item.getCategoty(), currentItem.getQuantityStock() );
			
			if ( item.getQuantityStock() < currentItem.getQuantityStock() ) {
				
				if ( message.length() > 0 )

					message.append( ", ");
					
				message.append( currentItem.getName() + "(" + item.getQuantityStock() + ")" );
					
			} else {
				
				currentItem.setPrice( item.getPrice() );
				
			}
			
 		}
		
		if ( message.length() > 0 ) { 
			
			outputMessage.add("Please correct quantities.");
			
			outputMessage.add( message.toString() );
			
		}
		
		return message.length() == 0;
		
	}
	
	private boolean checkItemCategory(  ) {
		
		StringBuilder message = new StringBuilder();
		
		for( Map.Entry<String, Integer> entry : categoryLimit.entrySet() ) {
			   
			if( categoryTotal.get( entry.getKey() ) > entry.getValue() ) {
				
				if ( message.length() > 0 )

					message.append( ", ");
					
				message.append( entry.getKey() + "(" + entry.getValue() + ")" );
				
			}
			
		}
		
		if ( message.length() > 0 ) { 
			
			outputMessage.add("Please check the quantity limit of each Category.");
			
			outputMessage.add( message.toString() );
			
		}
		
		return message.length() == 0;
		
	}
	
	private void sumItemQuantityByCategory( Category category, int quantity ) {
		
		int newQuantity = categoryTotal.getOrDefault( category.getName(), 0 ) + quantity ;
		
		categoryTotal.put( category.getName(), newQuantity );
		
		categoryLimit.putIfAbsent(category.getName(), category.getQuantityPerOrder() );
		
	}
	
	public void generateOutputFile( ) {
		
		StringBuffer line = new StringBuffer();
		
		if( outputMessage.isEmpty() ) {
			
			for( Item currentItem : items ) {
				
				line.append( currentItem.getName() );
				line.append( "," );
				line.append( currentItem.getQuantityStock() );
				line.append( "," );
				line.append( currentItem.getPrice() );
				line.append( "," );
				line.append( currentItem.getPrice() * currentItem.getQuantityStock() );
				
				outputMessage.add(line.toString());
				
				line.setLength(0);
				
			}
			
			outputMessage.add( Double.toString(currentOrder.getTotalPrice() ) );
			
			try {
				
				orderFile.writeCheckoutFile( outputMessage );
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
		
		} else {
			
			try {
				
				orderFile.writeMessage(outputMessage);
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	}

}
