package ErasureStageI.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
 


public class Consumption {
	
	
	
	public ArrayList readFile() {
		
				
		ArrayList consum = new ArrayList<Double>();
		
		
		
		
	    BufferedReader br = null;
	    
	    try {
	      
	      br = new BufferedReader(new FileReader("./CCGRIDDATA/50Nodes/consumption.txt"));
	      String line = null;
	      
	      while ((line = br.readLine()) != null) {
	        
	        String[] values = line.split(",");
	        
	        /*
	         * Get the values of up time, down time , number of breakdowns 
	         */
	       
	        double conso=Double.parseDouble(values[0]);
	        
	        consum.add(conso);
	            
	      }
	      
	    }
	    
	    catch (FileNotFoundException ex) {
	      ex.printStackTrace();
	    }
	    catch (IOException ex) {
	      ex.printStackTrace();
	    }
	    finally {
	      try {
	        if (br != null)
	          br.close();
	      }
	      catch (IOException ex) {
	        ex.printStackTrace();
	      }
	    }
	    
	    
	   
	    
	    return consum;
	  }
	/*
	public static void main(String[] args) {
	    Consumption consume = new Consumption();
	    ArrayList con = consume.readFile();
	    System.out.println(con.size());
	  }
	*/
}
