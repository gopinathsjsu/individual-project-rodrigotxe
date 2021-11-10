package edu.sjsu.cmpe202.myMarket.controller;

import java.util.ArrayList;

import edu.sjsu.cmpe202.myMarket.database.InMemoryDB;
import edu.sjsu.cmpe202.myMarket.helper.FileHandler;
import edu.sjsu.cmpe202.myMarket.model.Item;

public class StockController {
	
	private InMemoryDB db = InMemoryDB.getInstance();
	
	private FileHandler file;
	
	public StockController ( String filePath ) {
		
		file = new FileHandler( filePath );
		
	}
	
	public void createStock( ) {
		
		try {
			
			file.readFile( true );
			
		} catch (Exception e) {
			
			System.out.println( "The stock file was not found, Please check the file path." );
			System.exit(0);
			
		}
		
		getItems( file.getFileContent(  ) );
		
	}
	
	
	private void getItems( ArrayList<String> fileContent ) {
		
		for( String line : fileContent ) {
			
			String[] item = line.split(",");

			db.getItems().put(item[1], new Item( item[0], item[1], Double.parseDouble( item[3] ), Integer.parseInt( item[2] ) ) );
			
		}  
		
	}
	
}