package ErasureStageI.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.*;


public class RebuildingCost {
	
	
	int threshold; 
	int width;
	int m;
	int n;
	
	
	public RebuildingCost(int N, double S){
		
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
		
	}
	
	public static double SystemCapacity(double[] SC){		
		//double SystemCapacity = 0;		
		//double SystemCapacity = DoubleStream.of(SC).sum();
		double SystemCapacity = Arrays.stream(SC).sum();
		return SystemCapacity;
	}
	
	public double MeanTimeToFailure(ArrayList uT, ArrayList dT, ArrayList nbR){
		
		double upTimeNode, downTimeNode,numberofBreakDown,mttf;
		double meanTimeFailureOfSystem = 0;
		ArrayList meanTimeFailureofNodes = new ArrayList<Double>();
		

		for(int i=0;i<uT.size();i++){
			 
			 
			 upTimeNode = (double) uT.get(i);
			 downTimeNode = (double) dT.get(i);
			 numberofBreakDown = (double) nbR.get(i);
			 
			 if(downTimeNode==0 && numberofBreakDown==0){
				 mttf = 0;
				 
				 meanTimeFailureOfSystem = meanTimeFailureOfSystem + mttf;
			 }else{
				 mttf = (upTimeNode-downTimeNode)/numberofBreakDown;
				 
				 meanTimeFailureOfSystem = meanTimeFailureOfSystem + mttf;
			 }			 
			 meanTimeFailureofNodes.add(mttf);			 
		 }		 
				
		return meanTimeFailureOfSystem;
		
	}
	
	public double RebuildCost(int m, double SC, double mttfs){
		double RC = 0;
		RC = (m * SC)/mttfs;
		
		return RC;
	}
	
	public static void main(String[] args){
		
		int numberofNodes = 50;
		double dataSize = 50000;
		int m = 20;
		
		RebuildingCost cost = new RebuildingCost(numberofNodes, dataSize);
		
		Capacity city = new Capacity();
		ArrayList cities = city.readFile();
		double ci = 0;
		double[] SC = new double[cities.size()];
			
		for(int sci = 0; sci<numberofNodes;sci++){
			ci = (double) cities.get(sci);
			SC[sci] = ci;
			
		}
		
		NodeAvail test = new NodeAvail();
		
		ArrayList meaning = test.readFile();
		ArrayList upTime = (ArrayList) meaning.get(0);
		ArrayList downTime = (ArrayList) meaning.get(1);
		ArrayList breakDown = (ArrayList) meaning.get(2);
				
		double SystemCapacity = cost.SystemCapacity(SC);
		double meanTimeFailureOfSystem = cost.MeanTimeToFailure(upTime, downTime, breakDown);
		double rebuildCost = cost.RebuildCost(m, SystemCapacity, meanTimeFailureOfSystem);
		System.out.println(rebuildCost);
		
	}

}
