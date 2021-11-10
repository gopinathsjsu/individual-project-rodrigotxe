package edu.sjsu.cmpe202.myMarket.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import edu.sjsu.cmpe202.myMarket.database.InMemoryDB;
import edu.sjsu.cmpe202.myMarket.helper.FileHandler;
import edu.sjsu.cmpe202.myMarket.model.Item;
import edu.sjsu.cmpe202.myMarket.model.Order;
import edu.sjsu.cmpe202.myMarket.model.OrderItem;

public class OrderController {
	
	private InMemoryDB db = InMemoryDB.getInstance();
	
	private Order currentOrder = new Order();
	
	private FileHandler file;
	
	private ArrayList<String> outputMessage = new ArrayList<>();
	
	private ArrayList<OrderItem> items = new ArrayList<>();
	
	private HashSet<String> creditCards = new HashSet<String>();
	
	private double totalPrice = 0;
	
	public OrderController ( String filePath ) {
		
		file = new FileHandler( filePath );
		
	}
	
	public boolean startOrder( ) {
		
		try {
			
			file.readFile( true );
			
		} catch (Exception e) {
			
			return false;
			
		}
		
		getItems( file.getFileContent(  ) );
		
		return true;
		
	}
	
	public boolean checkOrder( ) {
		
		checkItemStock();
	
		return outputMessage.isEmpty();
		
	}
	
	public void calculateTotalPrice( ) {
		
		items.forEach( ( item ) -> {
			
			totalPrice += item.getQuantity() * item.getPrice();
			
		});
		
		currentOrder.setTotalPrice(totalPrice);
		
	}
	
	public double getTotalPrice() {
		
		return currentOrder.getTotalPrice();
	
	}
	
	public void checkoutOrder(  ) {
		
		db.getOrders().add( currentOrder );
		
		for( OrderItem currentItem : items ) {
			
			Item item = db.getItems().get( currentItem.getName() );
			
			item.setQuantityStock( item.getQuantityStock() - currentItem.getQuantity() );
			
 		}
		
		for( String creditCard : creditCards ) {
			
			if( !db.getCreditCards().contains( creditCard ) ) {
				
				db.getCreditCards().add(creditCard);
				
			}
			
		}
		
		generateOutputFile( );
		
	} 
	
	public void printMessage( ) {
		
		for ( String line : outputMessage ) {
			
			System.out.println( line );
			
		}
		
	}
	
	private void getItems( ArrayList<String> fileContent ) {
		
		for( String line : fileContent ) {
			
			String[] item = line.split(",");
			
			if ( db.getItems().containsKey(item[0]) ) {

				items.add( new OrderItem( item[0], Integer.parseInt( item[1] ), item[2].replaceAll("\\s+", "") ) );

			} else {
				
				outputMessage.add("Item " + item[0] + " not found");
			
			}

		}
		
		if( !outputMessage.isEmpty() ) {
			
			items.clear();
			
		}
		
	}
	
	private boolean checkItemStock( ) {
		
		StringBuilder message = new StringBuilder();
		
		for( OrderItem currentItem : items ) {
			
			Item item = db.getItems().get( currentItem.getName() );
			
			if ( item.getQuantityStock() < currentItem.getQuantity() ) {
				
				if ( message.length() > 0 )

					message.append( ", ");
					
				message.append( currentItem.getName() + "(" + item.getQuantityStock() + ")" );
					
			} else {
				
				currentItem.setPrice( item.getPrice() );
				
				if( !creditCards.contains( currentItem.getCreditCard() ) )
				
					creditCards.add( currentItem.getCreditCard() );
				
			}
			
 		}
		
		if ( message.length() > 0 ) { 
			
			outputMessage.add("Please correct quantities.");
			
			outputMessage.add( message.toString() );
			
		}
		
		return message.length() == 0;
		
	}
	
	public void generateOutputFile( ) {
		
		if( outputMessage.isEmpty() ) {
			
			outputMessage.add( "Amt Paid" );
			
			outputMessage.add( Double.toString( currentOrder.getTotalPrice() ) );
			
			try {
				
				file.writeOutput( outputMessage, false );
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
		
		} else {
			
			try {
				
				file.writeOutput( outputMessage, true );
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	}

}
