package edu.sjsu.cmpe202.myMarket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.sjsu.cmpe202.myMarket.controller.OrderController;
import edu.sjsu.cmpe202.myMarket.controller.StockController;

public class MarketApplication {

	public static void main(String[] args) throws Exception {
		
		MarketApplication market = new MarketApplication();
		
		market.startMarket(args);
		
	}
	
	private void startMarket( String[] args ) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		if( args.length == 0 ) {
			
			System.out.println( "Please enter stock file to start" );
			
			System.exit(0);
			
		}  
		
		// In case the user input a file with space in the name
		for( String arg : args ) {
			
			if( !stringBuilder.isEmpty() ) stringBuilder.append(" ");
			
			stringBuilder.append(arg);
			
		}
		
		StockController stockController = new StockController( stringBuilder.toString() );
		
		stockController.createStock();
		
		while( true ) {
			
			String path = handleMainMenu( );
			
			if( path.equals("") ) break;
			
			startNewOrder( path );
			
		}
		
		System.out.println( "Thank you for using MyMarket - Automated Purchasing System." );
		
	}
	
	private String handleMainMenu( ) {
		
		BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
		
		System.out.println( "Enter the order file path to initiate a new order or leave it blank if you have no more orders: " );
		
		String response = "";
		
		try {
			
			response = reader.readLine();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		return response;
		
	}
	
	private void startNewOrder( String path ) {
		
		OrderController orderController = new OrderController( path );
		
		if( orderController.startOrder() ) {
			
			if( orderController.checkOrder() ) {
				
				orderController.calculateTotalPrice();
				
				orderController.checkoutOrder();
					
				System.out.println( "The order was created with a total of $" + orderController.getTotalPrice() );
				
				
			} else {
				
				System.out.println( "An error has occurred. Please check the generated file." );
				
				orderController.generateOutputFile( );

			}
			
		} else {
			
			System.out.println( "The order file was not found, Please check the file path." );
			
		}
		
	}

}