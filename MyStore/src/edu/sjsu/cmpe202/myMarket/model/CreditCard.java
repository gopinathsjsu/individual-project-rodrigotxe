package edu.sjsu.cmpe202.myMarket.model;

public class CreditCard {
	
	private String creditCardNumber;
	
	public CreditCard( String creditCardNumber ) {
		
		this.creditCardNumber = creditCardNumber;
		
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
}
