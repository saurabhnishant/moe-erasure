package ErasureStageI.exec;

import java.io.IOException;

import jmetal.util.JMException;



import ErasureStageI.src.ErasStageIMOE_main;


/**
 * @param Use stageI for initial upload sorting or stageII for full repository optimization 
 * @throws JMException 
 * @throws IOException 
 * @throws SecurityException
 * @throws ClassNotFoundException 
 * @throws ParseException
 * @authargs[0]or Modified for the Erasure MOO Framework.
 */



public class erasmoExecute {
	
	
	public void erasmoExecute_runtime(String stage){
		
		String compare = stage;
		
		
			try {
				if (compare.equals("stageI")){
					ErasStageIMOE_main.StageI();}
				else if (compare.equals("stageII")) {
					//StageIMOE_main.stageI();
				}
				else {
					throw new IllegalArgumentException("You have entered wrong arguments. The Arguments can be \"stageI\" or \"stageII\"");
				}
			} catch (SecurityException | ClassNotFoundException | JMException
					| IOException |  IllegalArgumentException e) {
				// 
				e.printStackTrace();
			}
			}
		
		
	}			
