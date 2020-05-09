package ErasureStageI.src;

import java.util.*;


public class Bandwidth{
	
	static final Random generator = new Random();

	  
	static final double alpha = 1.2;
	static final double c = 0.5;
		
	
	public static double getPareto(double alpha, double c, Random rgen) {
	        return c*Math.pow(getUniform(rgen),-1/alpha);			
	   }
	
	public static double getUniform(Random rgen) {	      
	       return rgen.nextDouble();
	   }
	
	
	public static double getUniformInt(double min, double max, Random rgen) {

	       
	       double u = getUniform(rgen);
	       double value = min + u * (max -min);

	       return value;
	   }
	  
	
	
	public static void main(String[] args){
		
		int n = 50;
	    double[][] realise = new double[n][n];
	    
	    for (int i=0; i < n; i++) {	    	
	    	for(int j = 0; j<n; j++){
	    		
	    		if(i==j){
	    			realise[i][j] = 1;
	    			
	    		}
	    		else{
	    			realise[i][j] = getPareto(alpha,c,generator);
	    			
	    		}
	    		System.out.print(realise[i][j]);
	    		if(j<(n-1)){
	    			System.out.print(",");
	    		}
	    		
	    		
	    	}
	    	System.out.print("\n");
		        	
	    }				
	}
}

