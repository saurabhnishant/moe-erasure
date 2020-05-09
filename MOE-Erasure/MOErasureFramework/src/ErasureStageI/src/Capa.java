package ErasureStageI.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
 


public class Capa{
	
	
	
	public ArrayList readFile() {
		
				
		ArrayList cap = new ArrayList<Double>();
		
		
		
		
	    BufferedReader br = null;
	    
	    try {
	      
	      br = new BufferedReader(new FileReader("./CCGRIDDATA/50Nodes/capacity.txt"));
	      String line = null;
	      
	      while ((line = br.readLine()) != null) {
	        
	        String[] values = line.split(",");
	        
	        /*
	         * Get the values of up time, down time , number of breakdowns 
	         */
	       
	        double capaci=Double.parseDouble(values[0]);
	        
	        cap.add(capaci);
	            
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
	    
	    
	   
	    
	    return cap;
	  }
	
}
