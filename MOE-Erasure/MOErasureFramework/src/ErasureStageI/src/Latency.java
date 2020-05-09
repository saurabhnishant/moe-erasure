package ErasureStageI.src;

import java.util.ArrayList;
import java.util.Arrays;

public class Latency {
	
	
public double innerBlock(int ext, double b, double minBw, double read, double write){
		
		double result1 = (ext * b) / minBw;
		double result2 = read + write ;		
		double innerBlockResult = result1 * result2;
				
		return innerBlockResult ;
				
	}


	public double latencyBlock(int N, ArrayList chunking, double chunkSize, ArrayList minBwSize, ArrayList readSize, ArrayList writeSize){
		
		double latencyExist = 0;
		double latencySum = 0;
		double latencyDoubleSum = 0;
				
		for(int i =0; i<N; i++){
			
			double read = (double) readSize.get(i);
			double write = (double) writeSize.get(i);
			
			
			ArrayList bwSizeInd = (ArrayList) minBwSize.get(i);
						
			for(int j=0; j<N;j++){
				
				int items = (int) chunking.get(j);
				
				if(items==0){					
					
					double band = (double) bwSizeInd.get(j);
					latencyExist = innerBlock(1, chunkSize, band, read, write);					
				}else {				
					double band = (double) bwSizeInd.get(j);
					latencyExist = innerBlock(0, chunkSize, band, read, write);					
				}		
				latencySum = latencySum + latencyExist;
								
			}
			latencyDoubleSum = latencyDoubleSum + latencySum;
			
		}
	
		return latencyDoubleSum;
	}
	
	
	

}
