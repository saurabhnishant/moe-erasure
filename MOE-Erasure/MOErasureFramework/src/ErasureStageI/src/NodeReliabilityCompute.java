package ErasureStageI.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
 


public class NodeReliabilityCompute {
	
	
	
	public ArrayList readFile() {
		
		ArrayList<ArrayList<Double>> finalUPDW = new ArrayList<ArrayList<Double>>();
		
		
		ArrayList upTimes = new ArrayList<Double>();
		ArrayList downTimes = new ArrayList<Double>();
		ArrayList nBrs = new ArrayList<Double>();
		
		ArrayList availability = new ArrayList<Double>();
		
	    BufferedReader br = null;
	    
	    try {
	      
	      br = new BufferedReader(new FileReader("./CCGRIDDATA/50Nodes/t1avail.txt"));
	      String line = null;
	      int nodeId = 0;
	      while ((line = br.readLine()) != null) {
	        
	        String[] values = line.split(",");
	        
	        double avail=Double.parseDouble(values[0]);
	        
	        
	        availability.add(avail);
	        /*
	         * Get the values of up time, down time , number of breakdowns 
	         */
	        /*
	  
	        double uptime=Double.parseDouble(values[0]);
	        double downtime=Double.parseDouble(values[1]);
	        double numberOfBreakdowns=Double.parseDouble(values[2]);
	    
	        upTimes.add(uptime);
	        downTimes.add(downtime);
	        nBrs.add(numberOfBreakdowns);
	        
	        double MTBF, MTTR, avail = 0;
	        
	       
	        
	        
	        if(downtime != 0 && numberOfBreakdowns != 0 ){
	        	MTBF = uptime/numberOfBreakdowns;
	        	MTTR = downtime/numberOfBreakdowns;	       
	        	avail = MTBF/(MTBF+MTTR);
	        	availability.add(avail);
	        }else{
	        	avail = 0.999;
	        	availability.add(avail);
	        }
	        
	        */
	        
	        
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
	    
	    
	   
	    
	    return availability;
	  }
	 /*
	  public static void main(String[] args) {
	    NodeReliabilityCompute compu = new NodeReliabilityCompute();
	    ArrayList compute = compu.readFile();
	    
	    System.out.println(compute.size());
	  }
	  */
}
