package ErasureStageI.conf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import datapars.Inputvariables;

import ErasureStageI.src.BandwidthGenerate;
import ErasureStageI.src.Capacity;
import ErasureStageI.src.Consumption;
import ErasureStageI.src.EncodingRate;
import ErasureStageI.src.Latency;
import ErasureStageI.src.NodeReliabilityCompute;
import ErasureStageI.src.ReadGenerate;
import ErasureStageI.src.RebuildingCost;
import ErasureStageI.src.RedundancyFactor;
import ErasureStageI.src.Reliability;
import ErasureStageI.src.Storage;
import ErasureStageI.src.WriteGenerate;
import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.encodings.solutionType.IntSolutionType;
import jmetal.encodings.variable.Int;
import jmetal.util.JMException;
import jmetal.util.wrapper.XInt;
//import secondStage.Capa;

/**
 * Configuration of MO Stage 2 
 */
public class ErasStage2Config extends Problem {
	
	
	
  /**
   * Constructor
   */
  public ErasStage2Config(int n) {
    numberOfVariables_  = 50; // nodes in the system
    numberOfObjectives_ = 3;
    numberOfConstraints_= n; // we use this to create the bit representation
    problemName_        = "ErasStage2ptimization";
    lowerLimit_ = new double[numberOfVariables_];
    upperLimit_ = new double[numberOfVariables_];
        
    for (int i = 0; i < numberOfVariables_; i++) {
        lowerLimit_[i] = 0 ; 
        upperLimit_[i] = 49 ; 
      } // for  

    
 
   
      solutionType_ = new IntSolutionType(this);
      
      
 
  } // ConstrEx
     
  /** 
  * Evaluates a solution 
  * @param solution The solution to evaluate
   * @throws JMException 
  */
  
  public void evaluate(Solution solution) throws JMException {
		//empty for now
} //
  public void eva(Solution solution,int nO, int mO) throws JMException {
	  Variable[] decisionVariables  = solution.getDecisionVariables();
	  
		int n = nO;
		int m = mO;
		int N = numberOfVariables_;
		double dataSize = 2000;
		double ChunkSize = dataSize / m;
		
		
		// check if n and m were mutated or crossedover
				int compare = (int)decisionVariables[N].getValue();
				if (compare!=n){
					decisionVariables[N].setValue(n);
				}
				
				if ((int)decisionVariables[N+1].getValue()!=m){
					decisionVariables[N+1].setValue(m);
				}
				int counter = 0;
				for(int x = 0;x<N;x++){
					int tempt = (int)decisionVariables[x].getValue();
					if (tempt == 0){counter++;}
				}
	  
		if (counter!=n){	
			if (counter<n){
				int diff = n-counter;
				int v = 0;
				while (v<diff){
					int f = 0 + (int)(Math.random() * N); 
					if ((int)decisionVariables[f].getValue()==1 && v<diff){
						decisionVariables[f].setValue(0);
						v++;
					}
				}	
			}
			if (counter>n){
				int diff = counter-n;
				int v = 0;
				while (v<diff){
					int f = 0 + (int)(Math.random() * N); 
					if ((int)decisionVariables[f].getValue()==0 && v<diff){
						decisionVariables[f].setValue(1);
						v++;
					}
				}		
			}

		
		}
	   

	  
	// _______________________________STORAGE_______________________________________
	  
		Storage storage = new Storage(); 

		ArrayList chunkNewExistence = new ArrayList<Integer>(N);
		
		
		for(int x = 0;x<N;x++){
			int tempt = (int)decisionVariables[x].getValue();
			chunkNewExistence.add(x, tempt);
		}

		
		
		/*
		 * Historic Data Computation. Does not change
		 */
		
		/*
		 * Data Size of each data item
		 */
		
		
		ArrayList DataItemSize = new ArrayList<Double>();
		
	    DataItemSize.add(dataSize);
		
		
		/*
		 * n,m for historic data items
		 */
		
		ArrayList<ArrayList<Integer>> valueOfmn = new ArrayList<ArrayList<Integer>>();
		
		
		
		ArrayList valueofmnDataItemsNew = new ArrayList<Integer>();
		valueofmnDataItemsNew.add(n); valueofmnDataItemsNew.add(m);
		
		valueOfmn.add(valueofmnDataItemsNew);
		
		/*
		 * chunk Size for each data item
		 */
		
		ArrayList chunkSize = new ArrayList<Double>();
		for(int size = 0; size<DataItemSize.size(); size++){
			double itemSize = (double) DataItemSize.get(size);
			ArrayList sizeInd = (ArrayList) valueOfmn.get(size);
			int thresholdInd = (int) sizeInd.get(1); 
			double finalChunkSize = itemSize / thresholdInd;
			chunkSize.add(size, finalChunkSize);
			
		}
		
		
		
		/*
		 * Storage Consumption at each node
		 */
		Consumption consume = new Consumption();
	    ArrayList storageConsumption = consume.readFile();
		
		/*
		 * Data Item 1 Existence and Chunk Size
		 */
		
		
		ArrayList<ArrayList<Integer>> chunksExistence = new ArrayList<ArrayList<Integer>>();
		
		/*
		 * Fifth new data item
		 */
		chunksExistence.add(chunkNewExistence);
				
		//double SC = storage.StorageConsumption(storageConsumption);
		double SC = 0;		
		
		
		// Changing here : --------------------------------------------------------------------------------------------
		
		Capacity city1 = new Capacity();
		
		ArrayList cities1 = city1.readFile();
		double ci1 = 0;
	    double[] SC1 = new double[cities1.size()];
				
		for(int sci1 = 0; sci1<50;sci1++){
					ci1 = (double) cities1.get(sci1);
					SC1[sci1] = ci1;
					
				}
				
		double SystemCapacity1 = 0;		
				
				
		for(int tu=0;tu<chunkNewExistence.size();tu++){
					
					
		int cuItem = (int) chunkNewExistence.get(tu); 
					
		if(cuItem==0){
			double cuItemCap = SC1[tu];		
			SystemCapacity1 = SystemCapacity1 + cuItemCap; 
			} else{
						SystemCapacity1 = SystemCapacity1;
			  }
					
		}					
				
				
				
	 // Changing here : --------------------------------------------------------------------------------------------
				
				
				
		double DS = storage.dataStorage(chunksExistence, chunkSize);

		double SCE = storage.storageCostEfficiency(SC, DS)/SystemCapacity1;
				
		
		
//______________________________________________LATENCY____________________________________________________________________
		
		Latency latency = new Latency();
		
		
		
		/*
		 * Minimum Bandiwdth Generation
		 */
		
		BandwidthGenerate generator = new BandwidthGenerate();
	    ArrayList minimumBandwidthSystem = generator.readFile();
		
				
		/*
		 * Read Requests and Write Requests
		 */

	    ReadGenerate readGen = new ReadGenerate();
	    ArrayList readRequests = readGen.readFile();
	    
	    WriteGenerate writeGen = new WriteGenerate();
	    ArrayList writeRequests = writeGen.readFile();
	    
		
		
		ArrayList readSumNode = new ArrayList<Integer>();
		ArrayList writeSumNode = new ArrayList<Integer>();
		
		for(int min=0;min<chunksExistence.size();min++){
			ArrayList readrequestNode = (ArrayList) readRequests.get(min);
			ArrayList writeRequestNode = (ArrayList) writeRequests.get(min);
			
			int readSum = 0;
			int writeSum = 0;
			
			for(int max=0;max<N;max++){
			
				int readExtra = (int) readrequestNode.get(max);
				int writeExtra = (int) writeRequestNode.get(max);
			
				readSum = readSum + readExtra;
				writeSum = writeSum + writeExtra;
				
				
			}
			
			readSumNode.add(readSum);
			writeSumNode.add(writeSum);
			
		}
		
		
		
		ArrayList<ArrayList<Double>> readRequestPercentage = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> writeRequestPercentage = new ArrayList<ArrayList<Double>>();
		
		
		ArrayList readrequestNodePercentage = new ArrayList<Double>();
		ArrayList writeRequestNodePercentage = new ArrayList<Double>();
		
		for(int min=0;min<chunksExistence.size();min++){
			//ArrayList readrequestNodePercentage = new ArrayList<Double>();
			//ArrayList writeRequestNodePercentage = new ArrayList<Double>();
			
			ArrayList readRequesting = (ArrayList) readRequests.get(min);
			ArrayList writeRequesting = (ArrayList) writeRequests.get(min);
			
			double numberRead = (int) readSumNode.get(min);
			double numberWrite = (int) writeSumNode.get(min);
			
			for(int max=0;max<N;max++){
				double r = (int) readRequesting.get(max);
				double w = (int) writeRequesting.get(max);
				
				double percRead = (r * 100) / numberRead;
				double percWrite = (w * 100) / numberWrite;
				
				
				
				readrequestNodePercentage.add(percRead);
				writeRequestNodePercentage.add(percWrite);
				
			}
			readRequestPercentage.add(readrequestNodePercentage);
			writeRequestPercentage.add(writeRequestNodePercentage);
		}
		
		
		double lat = latency.latencyBlock(N, chunkNewExistence, ChunkSize, minimumBandwidthSystem, readrequestNodePercentage, writeRequestNodePercentage)/1000;	
		
//__________________________________________________RELIA____________________________________________________________
		Reliability reliability = new Reliability();
		NodeReliabilityCompute compu = new NodeReliabilityCompute();
	    ArrayList compute = compu.readFile();
	    
	    
	    int ne = 0;
	    int ke = 0; 
	    ArrayList induCom = new ArrayList<Double>();
	    
	    for(int t=0;t<chunksExistence.size();t++){
	    	
			ArrayList chunkingInd = (ArrayList) chunksExistence.get(t);
			ArrayList valueOfmnInd = (ArrayList) valueOfmn.get(t);
			
			
			for(int i =0; i<chunkingInd.size(); i++){
				double items = (int) chunkingInd.get(i);
				ne = (int) valueOfmnInd.get(0);
				int threshold = (int) valueOfmnInd.get(1);
				ke = ne - threshold; 
				if(items==0){
					double indu = (double) compute.get(i);
					induCom.add(indu);					
				} 
								
			}
			
	    }	
	    
	    double avail = reliability.availabilityKoutOfN(ne, ke, induCom);
				
			
		
		solution.setObjective(0,SCE);
		solution.setObjective(1, lat);
		solution.setObjective(2,1-avail);
		
		
		}
		
		
		
		
} // evaluate
 
