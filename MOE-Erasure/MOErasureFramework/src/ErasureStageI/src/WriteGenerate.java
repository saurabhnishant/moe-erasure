package ErasureStageI.src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WriteGenerate {
	
public ArrayList readFile() {
		
		ArrayList<ArrayList<Integer>> finalWrite = new ArrayList<ArrayList<Integer>>();
		
		/*
		 * Change this number
		 */
		
		
		
		
	    BufferedReader br = null;
	    
	    try {
	      
	      br = new BufferedReader(new FileReader("./CCGRIDDATA/50Nodes/write.txt"));
	      String line = null;
	      int nodeId = 0;
	      while ((line = br.readLine()) != null) {
	        
	        String[] values = line.split(",");
	        
	        ArrayList WriteCycle = new ArrayList<Integer>();
	        
	        /*
	         * Get the values of bandwidth 
	         */
	        
	        
	        for(int min = 0; min < values.length ; min ++){
	        	
	        	Integer band = Integer.parseInt(values[min]);
	            WriteCycle.add(band);
	        	
	        	
	        	
	        	
	        }
	        
	        
	        finalWrite.add(WriteCycle);        
	        
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
	    
	 
	   
	    
	    return finalWrite;
	  }
	 /*
	  public static void main(String[] args) {
	    WriteGenerate test = new WriteGenerate();
	    ArrayList tests = test.readFile();
	    System.out.println(tests.size());
	  }
	  */
	
	

	
}
