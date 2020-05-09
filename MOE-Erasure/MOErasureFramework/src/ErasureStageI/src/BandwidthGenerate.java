package ErasureStageI.src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BandwidthGenerate {
	
public ArrayList readFile() {
		
		ArrayList<ArrayList<Double>> finalBandwidth = new ArrayList<ArrayList<Double>>();
		
		/*
		 * Change this number
		 */
		
		
		
		
	    BufferedReader br = null;
	    
	    try {
	      
	      br = new BufferedReader(new FileReader("./CCGRIDDATA/50Nodes/bandwidth1.txt"));
	      String line = null;
	      int nodeId = 0;
	      while ((line = br.readLine()) != null) {
	        
	        String[] values = line.split(",");
	        
	        ArrayList bandwidthCycle = new ArrayList<Double>();
	        
	        /*
	         * Get the values of bandwidth 
	         */
	        
	        
	        for(int min = 0; min < values.length ; min ++){
	        	
	        	double band=Double.parseDouble(values[min]);	
	            bandwidthCycle.add(band);
	        	
	        	
	        	
	        	
	        }
	        
	        
	        finalBandwidth.add(bandwidthCycle);        
	        
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
	    
	  //  finalUPDW.add(upTimes);
	  //  finalUPDW.add(downTimes);
	  //  finalUPDW.add(nBrs);
	   
	    
	    return finalBandwidth;
	  }
	 /*
	  public static void main(String[] args) {
	    BandwidthGenerate test = new BandwidthGenerate();
	    ArrayList tests = test.readFile();
	    System.out.println(tests.size());
	  }
	  
	*/
}
