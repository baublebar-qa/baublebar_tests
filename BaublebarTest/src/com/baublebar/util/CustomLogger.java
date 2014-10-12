package com.baublebar.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date; 

/**
 * Author - Maitri Acharya
 */
public class CustomLogger {  
   PrintWriter writer;   
   static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
   
   public CustomLogger(){
	  // PrintWriter writer = null;   
	   try {     
		   //Date date = new Date();   
		   if(writer == null) 
			  // writer = new PrintWriter(new FileWriter("./logs/" + classname + ".log", true));                                       
			   writer = new PrintWriter("./logs/file.log"); 
		   		
		   		if(writer.checkError()){
		   			System.out.println("problem");
	            }
		   		writer.append("r\n" + dateFormat.format(new Date()) + " - DEBUG: Logger Cretaed " );
		   		writer.flush();
	   } catch(IOException io) {      
		   io.printStackTrace(); 
	   }     
	}        
	   

	public void debug(String msg) {                                
		writer.append("\r\n" + dateFormat.format(new Date()) + " - DEBUG: " + msg);
		writer.flush();
    }                           
 
	public void warn(String msg) {  
		writer.append("\r\n" + dateFormat.format(new Date()) + " - WARN: " + msg); 
		writer.flush();
	}                               

	public void error(String msg) {  
		writer.append("\r\n" + dateFormat.format(new Date()) + " - ERROR: " + msg);   
		writer.flush();
	}                              

	public void closeLogger() {
		writer.close();               
	} 	
 
}