package edu.sjsu.cmpe202.myMarket;

import edu.sjsu.cmpe202.myMarket.controller.OrderController;

public class MarketApplication {

	public static void main(String[] args) throws Exception {
		
		if( args.length == 0 ) {
			
			System.out.println( "I need a file to process" );
			
		} 
		
		OrderController orderController = new OrderController( args[0] );
		
		orderController.startOrder();
		
		if( orderController.checkOrder() ) {
			
			orderController.calculateTotalPrice();
			
			if( orderController.getTotalPrice() > 0 ) {
				
				orderController.enterCreditCardNumber( );
				
				orderController.checkoutOrder();
			
			}
			
			System.out.println( "Success!" );
			
		} else {
			
			orderController.printMessage();

		}
		
	}

}