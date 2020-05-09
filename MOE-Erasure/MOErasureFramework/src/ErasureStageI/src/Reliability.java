package ErasureStageI.src;


import ErasureStageI.src.Factorial;

import java.math.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Reliability {
	
	Factorial factorial;
	
public double availabilityKoutOfN(int n, int k, ArrayList induCom){
		
		double avail = 0;	
		double sum = 1;
		double pi =0;
		int node = 0;
		double sume = 0;
		
		for(int i=k;i<n;i++){
					
			pi = (double) induCom.get(node);
			DecimalFormat df = new DecimalFormat("#.###");      
			pi = Double.valueOf(df.format(pi));
			sum = sum * pi;
			//sume = Math.pow(sum,n) * Math.pow((1-sum),(n-k));
			node = node + 1;
			
		}
		
		
		return sum;
		
	}
	
	
	
	
	public BigDecimal exisitingAvailabilityNode(int N, ArrayList chunking, ArrayList valueOfmn, BigDecimal sumF){
		
		int items = 0;
		double indu = 0;	
		int n=0, threshold=0, k=0;
		
		BigDecimal dataProduct = new BigDecimal(1);
		BigDecimal round1 = new BigDecimal(0);
		BigDecimal dataDoubleProduct = new BigDecimal(1);
		BigDecimal dataTrippleProduct = new BigDecimal(1);
		BigDecimal availability = new BigDecimal(1);
		BigDecimal sumOne = new BigDecimal(1);
		
		for(int t=0;t<chunking.size();t++){
			
			ArrayList chunkingInd = (ArrayList) chunking.get(t);
			ArrayList valueOfmnInd = (ArrayList) valueOfmn.get(t);
								
			for(int i =0; i<chunkingInd.size(); i++){
				
				items = (int) chunkingInd.get(i);
				n = (int) valueOfmnInd.get(0);
				threshold = (int) valueOfmnInd.get(1);
				k = n - threshold;
				
				
				if(items==0){
					availability = sumF.multiply(sumOne);
					
				} else {
					availability = sumOne;
				}
				
				dataProduct = dataProduct.multiply(availability);
				
				
			}
			
			dataDoubleProduct = dataDoubleProduct.multiply(dataProduct);
			
		}
		
		return dataDoubleProduct;
	}
	
	
	

}