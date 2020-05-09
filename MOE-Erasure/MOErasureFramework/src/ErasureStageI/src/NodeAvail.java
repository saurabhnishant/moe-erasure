package ErasureStageI.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
 


public class NodeAvail {
	
	
	
	public ArrayList readFile() {
		
		ArrayList<ArrayList<Double>> finalUPDW = new ArrayList<ArrayList<Double>>();
		
		
		ArrayList upTimes = new ArrayList<Double>();
		ArrayList downTimes = new ArrayList<Double>();
		ArrayList nBrs = new ArrayList<Double>();
		
		ArrayList availability = new ArrayList<Double>();
		
	    BufferedReader br = null;
	    
	    try {
	      
	      br = new BufferedReader(new FileReader("./CCGRIDDATA/50Nodes/t1.txt"));
	      String line = null;
	      int nodeId = 0;
	      while ((line = br.readLine()) != null) {
	        
	        String[] values = line.split(",");
	        
	        /*
	         * Get the values of up time, down time , number of breakdowns 
	         */
	        
	        double uptime=Double.parseDouble(values[0]);
	        double downtime=Double.parseDouble(values[1]);
	        double numberOfBreakdowns=Double.parseDouble(values[2]);
	    
	        upTimes.add(uptime);
	        downTimes.add(downtime);
	        nBrs.add(numberOfBreakdowns);
	        
	        double MTBF, MTTR, avail = 0;
	        
	        avail = uptime/(uptime+downtime);
	        availability.add(avail);
	        
	        /*
	        if(downtime != 0 && numberOfBreakdowns != 0 ){
	        	MTBF = uptime/numberOfBreakdowns;
	        	MTTR = downtime/numberOfBreakdowns;	       
	        	avail = MTBF/(MTBF+MTTR);
	        }else{
	        	avail = 0.9999;
	        }*/
	        
	        
	        
	        
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
	    
	    finalUPDW.add(upTimes);
	    finalUPDW.add(downTimes);
	    finalUPDW.add(nBrs);
	   
	    
	    return finalUPDW;
	  }
	 /*
	  public static void main(String[] args) {
	    NodeAvail test = new NodeAvail();
	    ArrayList tests = test.readFile();
	  }
	  */
}
