package edu.sjsu.cmpe202.myMarket.helper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileHandler {

	Path path;
	
	private ArrayList<String> fileContent = new ArrayList<>();

	public FileHandler( String filePath ) {
		
		this.path = Paths.get( filePath );

	}

	public void readFile( boolean ignoreFirstLine  ) throws IOException {
		
		if ( Files.notExists( path ) ) {
             
			new IOException();
			 
        }
		 
		BufferedReader bufferedReader = new BufferedReader( new FileReader( path.toFile() ) );

		String line = "";

		while ( ( line = bufferedReader.readLine() ) != null ) {
			
			if( ignoreFirstLine ) { ignoreFirstLine = false; continue; }

			fileContent.add( line );

		}
		
	}
	
	public ArrayList<String> getFileContent() {
		
		return fileContent;
		
	}
	
	public void writeCheckoutFile( ArrayList<String> message ) throws IOException {
		
		FileWriter errorFile = new FileWriter( path.getParent().toString() + "/output.csv");
		
		for( String line : message )
		
			errorFile.write( line + "\n" );
		
		errorFile.close();
		
	}
	
	public void writeMessage( ArrayList<String> message ) throws IOException {
		
		FileWriter errorFile = new FileWriter( path.getParent().toString() + "/errorLog.txt");
		
		for( String line : message )
		
			errorFile.write( line + "\n" );
		
		errorFile.close();
		
	}
	
}