package ErasureStageI.src;

public class RedundancyFactor {
	
	int threshold; 
	int width;
	float chunkSize;
	double dataSize;
	int m;
	int n;
	float b;
	
	
	
	public RedundancyFactor(int N, double S){
		
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
	
	public double redundancy(int n, int m, double b, double S){
		
		double RF ;
		RF = (S * n)/(Math.pow(m, 2)*b);
		return RF;
		
	}
	
	
	
	public static void main(String[] args){
		int numberofNodes = 50;
		double dataSize = 50000;
		int n = 49;
		int m = 20;
		double b = dataSize / m;
		
		
		
		RedundancyFactor factor = new RedundancyFactor(numberofNodes, dataSize);
		double RFE = factor.redundancy(n, m, b, dataSize);
		System.out.println(RFE);
		
		
	}


}
