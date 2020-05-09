package ErasureStageI.conf;

import java.util.ArrayList;
import java.util.Random;

import ErasureStageI.src.Capacity;
import ErasureStageI.src.EncodingRate;
import ErasureStageI.src.NodeAvail;
import ErasureStageI.src.RebuildingCost;
import ErasureStageI.src.RedundancyFactor;
import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.encodings.solutionType.IntSolutionType;
import jmetal.util.JMException;
import jmetal.util.wrapper.XInt;


public class ErasStageIConfig extends Problem {
	
  /**
   * Constructor
   */
  public ErasStageIConfig() {
    numberOfVariables_  = 2; // m and n
    numberOfObjectives_ = 3;
    numberOfConstraints_= 0;
    problemName_        = "ErasStage1ptimization";
    lowerLimit_ = new double[numberOfVariables_];
    upperLimit_ = new double[numberOfVariables_];
    lowerLimit_[0] = 3;
    upperLimit_[0] = 49;
    lowerLimit_[1] = 2;
    upperLimit_[1] = 48;
    
 
   
      solutionType_ = new IntSolutionType(this);
      
      
 
  } // ConstrEx
     
  /** 
  * Evaluates a solution 
  * @param solution The solution to evaluate
   * @throws JMException 
  */
  public void evaluate(Solution solution) throws JMException {
		Variable[] decisionVariables  = solution.getDecisionVariables();
		int prcn = (int)decisionVariables[0].getValue();
		int prcm = (int)decisionVariables[1].getValue();
		if(prcn <= prcm) 
			{
			Random ran = new Random();
			int range = prcn - 2 + 1;
				decisionVariables[1].setValue(ran.nextInt(range)+2);
				solution.setDecisionVariables(decisionVariables);
			}
		prcm = (int)decisionVariables[1].getValue();
		
		if (prcm == prcn) {
			decisionVariables[1].setValue(2);
			solution.setDecisionVariables(decisionVariables);	
		}
		int numberofNodes = 50;
		double dataSize = 2000;
		
		
		int n = (int)decisionVariables[0].getValue();
		int m = (int)decisionVariables[1].getValue();
		double b = dataSize / m;
		
	// REDUNDANCY FACTOR __________________________________________________________	
		RedundancyFactor factor = new RedundancyFactor(numberofNodes, dataSize);
		double RFE = factor.redundancy(n, m, b, dataSize);
	
	// ENCODING RATE __________________________________________________________	
		
		EncodingRate rate = new EncodingRate(numberofNodes, dataSize);
		double ERE = rate.encoding(n, m, b, dataSize);
		
	// REBUILDING COST __________________________________________________________	
				
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
		
		//------------------------------------------------------------------
  
   solution.setObjective(0,RFE);
   solution.setObjective(1,ERE);
   solution.setObjective(2,rebuildCost);
   // solution.setObjective(1,(1/(perfOb/numberOfVariables_)));
  } // evaluate
} 
