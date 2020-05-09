package ErasureStageI.src;

import java.util.Random;

public class EncodingRate {
	
	/*
	 * numberofNodes : N
	 * threshold : integer type : m < n
	 * width : integer type : n < N
	 * chunkSize : b < S
	 * dataSize : S
	 */
	int threshold; 
	int width;
	float chunkSize;
	double dataSize;
	int m;
	int n;
	float b;
	
	
	
	public EncodingRate(int N, double S){
		
		if (n > N && n == N) {
		      System.out.println("Fatal error:  Invalid value of n");
		      System.exit(0);
		    }
		
		if (m > n && m == n) {
		      System.out.println("Fatal error:  Invalid value of m");
		      System.exit(0);
		    }
				
		this.threshold = m;
		this.width = n;
		this.chunkSize = b;
		
		
	}
	
	public double encoding(int n, int m, double b, double S){
		
		double ER ;
		ER = (Math.pow(m, 2)*b)/(S * n);
		return ER;
		
	}
	
	
	
	public static void main(String[] args){
		
		int numberofNodes = 50;
		double dataSize = 50000;
		int n = 49;
		int m = 20;
		double b = dataSize / m;
		
		EncodingRate rate = new EncodingRate(numberofNodes, dataSize);
		double ERE = rate.encoding(n, m, b, dataSize);
		System.out.println(ERE);
		
		
	}

}
