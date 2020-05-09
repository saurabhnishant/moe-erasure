package ErasureStageI.src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadGenerate {
	
public ArrayList readFile() {
		
		ArrayList<ArrayList<Integer>> finalRead = new ArrayList<ArrayList<Integer>>();
		
		/*
		 * Change this number
		 */
		
		
		
		
	    BufferedReader br = null;
	    
	    try {
	      
	      br = new BufferedReader(new FileReader("./CCGRIDDATA/50Nodes/read.txt"));
	      String line = null;
	      int nodeId = 0;
	      while ((line = br.readLine()) != null) {
	        
	        String[] values = line.split(",");
	        
	        ArrayList ReadCycle = new ArrayList<Integer>();
	        
	        /*
	         * Get the values of bandwidth 
	         */
	        
	        
	        for(int min = 0; min < values.length ; min ++){
	        	
	        	Integer band = Integer.parseInt(values[min]);
	        	// double band=Double.parseDouble(values[min]);	
	            ReadCycle.add(band);
	        	
	        	
	        	
	        	
	        }
	        
	        
	        finalRead.add(ReadCycle);        
	        
	      }
	      
	    }
	    
	    catch (NumberFormatException ex) {
		      ex.printStackTrace();
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
	    
	 
	   
	    
	    return finalRead;
	  }
	 /*
	  public static void main(String[] args) {
	    ReadGenerate test = new ReadGenerate();
	    ArrayList tests = test.readFile();
	    System.out.println(tests.size());
	  }
	  
	*/
	

}
