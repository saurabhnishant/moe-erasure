package ErasureStageI.src;

import java.util.ArrayList;
import java.util.Arrays;

public class Storage {
	
	
	
	
	public double StorageConsumption(ArrayList nodeSC){
		
		double totalSC = 0;
		double nodeStorageConsumption;
		
		for(int i=0;i<nodeSC.size();i++){
			
			nodeStorageConsumption = (double) nodeSC.get(i);
			totalSC = totalSC + nodeStorageConsumption;
		}
		
		return totalSC;
		
	}
	
	public double chunkStorage(ArrayList chunking, double chunkSize2){
		
		double sum = 0;
		int chunkNodeStatus;
								
			for(int l = 0; l < chunking.size();l++){
				chunkNodeStatus = (int) chunking.get(l);
				
				if(chunkNodeStatus==0){
					sum = sum + (1 - chunkNodeStatus) * chunkSize2;
				}
				else if(chunkNodeStatus==1){
					sum = sum + (1 - chunkNodeStatus) * chunkSize2;
				}				
			}		
		return sum;
	}
	
	public double dataStorage(ArrayList chunking, ArrayList chunkSize2 ){
				
		double dataSum = 0;
		
		for(int k=0;k<chunking.size();k++){
			
			ArrayList chunkingInd = (ArrayList) chunking.get(k);
			double size = (double) chunkSize2.get(k);
			
			double chunkSum = chunkStorage(chunkingInd, size);
			dataSum = dataSum + chunkSum;
		}					
		return dataSum;
				
	}
	
	
	public double storageCostEfficiency(double SC, double DS){
		double storageEfficiency = 0;
		
		storageEfficiency = SC + DS;
		
		return storageEfficiency;
		
	}
	
	
	public static void main(String[] args){
		
		Storage storage = new Storage();
		
		int n = 5;
		int m = 2;
		int N = 50;
		double dataSize = 50000;
		double ChunkSize = dataSize / m;
		
		/*
		 * Multi-objective placement values
		 */
		
		Integer[] myElements = {0,3,6,7,9};
		ArrayList chunkPlacement = new ArrayList<Integer>(Arrays.asList(myElements));
		
		/*
		 * Creating ArrayList chunk Existence of new Data item, don't change
		 */
		ArrayList chunkNewExistence = new ArrayList<Integer>(N);
		for(int x = 0;x<N;x++){
			chunkNewExistence.add(1);
		}
		
		
		for(int s = 0; s<n;s++){
			int newPlace = (int) chunkPlacement.get(s);
			chunkNewExistence.set(newPlace, 0);
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
				
		double SC = storage.StorageConsumption(storageConsumption);
				
		
		
		double DS = storage.dataStorage(chunksExistence, chunkSize);

		double SCE = storage.storageCostEfficiency(SC, DS);
		
		System.out.println("The storage cost efficiency : " + SCE);
						
		
	}

}
