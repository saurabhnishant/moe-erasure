
package ErasureStageI.src;

import jmetal.core.Algorithm;
import jmetal.core.Operator;
import jmetal.core.Problem;
import jmetal.core.SolutionSet;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.operators.crossover.CrossoverFactory;
import jmetal.operators.mutation.MutationFactory;
import jmetal.operators.selection.SelectionFactory;
import ErasureStageI.conf.ErasStageIConfig;
import ErasureStageI.conf.InputvariablesErasure;
import jmetal.qualityIndicator.QualityIndicator;
import jmetal.util.Configuration;
import jmetal.util.JMException;
import jmetal.util.Ranking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

//import org.json.simple.parser.ParseException;




public class ErasStageIMOE_main {
  public static FileHandler fileHandler_ ; // FileHandler object if there is requirment for the objectives and variables to be stored in file
  public static Logger      logger_ ;
  /**
   * @param The arguments are sent through exec.moExecute.
   * @throws JMException 
   * @throws IOException 
   * @throws SecurityException
   * @throws ClassNotFoundException 
   * @throws ParseException
   */
  public static void StageI () throws 
                                  JMException, 
                                  SecurityException, 
                                  IOException, 
                                  ClassNotFoundException {
    Problem   problem   ; 
    Algorithm algorithm ; 
    Operator  crossover ; 
    Operator  mutation  ; 
    Operator  selection ; 
    
    HashMap  parameters ; // The parameters for the NSGA-II algorithm are stored in this HashMap
    QualityIndicator indicators ; // Object to get quality indicators
    
    // Logger object and file to store log messages
    logger_      = Configuration.logger_ ;
    fileHandler_ = new FileHandler("Stage1-NSGAII_main.log"); 
    logger_.addHandler(fileHandler_) ;
    
    
    indicators = null ;
    problem = new ErasStageIConfig();
    algorithm = new ErasStageIMOE(problem);

    // NSGA-II parameters
    algorithm.setInputParameter("populationSize",250);
    algorithm.setInputParameter("maxEvaluations",1000);
    

    // Crossover probability and distribution
    parameters = new HashMap();
    parameters.put("probability", 0.99) ;
    parameters.put("distributionIndex", 20.0) ;
    crossover = CrossoverFactory.getCrossoverOperator("SinglePointCrossover", parameters);                   

    // Mutation probability and distribution
    parameters = new HashMap() ;
    parameters.put("probability", 1.0/problem.getNumberOfVariables()) ;
    parameters.put("distributionIndex", 20.0) ;
    mutation = MutationFactory.getMutationOperator("BitFlipMutation", parameters);                    

    // Selection Operator 
    selection = SelectionFactory.getSelectionOperator("BinaryTournament2", parameters) ;                           

    // Add the operators to the algorithm
    algorithm.addOperator("crossover",crossover);
    algorithm.addOperator("mutation",mutation);
    algorithm.addOperator("selection",selection);

    // Add the indicator object to the algorithm
    algorithm.setInputParameter("indicators", indicators) ;
    
    // Execute the Algorithm
    long initTime = System.currentTimeMillis();
    SolutionSet population = algorithm.execute();
    long estimatedTime = System.currentTimeMillis() - initTime;
 
     
    // Result messages
    logger_.info("Total execution time for Stage 1: " +estimatedTime + "ms");
    logger_.info("Stage 1: Objectives values have been writen to file FUN");
    population.printObjectivesToFile("Stage1-FUN");
    logger_.info("Stage 1: Variables values have been writen to file VAR");
    population.printVariablesToFile("Stage1-VAR");
    
    indicators = new QualityIndicator(problem, "Stage1-FUN") ; 
 
      logger_.info("Quality indicators") ;
      logger_.info("Hypervolume: " + indicators.getHypervolume(population)) ;
      logger_.info("GD         : " + indicators.getGD(population)) ;
      logger_.info("IGD        : " + indicators.getIGD(population)) ;
      logger_.info("Spread     : " + indicators.getSpread(population)) ;
      logger_.info("Epsilon    : " + indicators.getEpsilon(population)) ;

 

    int [] [] putvar = population.putVariablesToString();
 
    
    /*----------------------------------------------------------------New section ----------------------------------------------- */
    /*
    ArrayList<ArrayList<Integer>> mnBig = new ArrayList<ArrayList<Integer>>();
	
	  
	for(int pp=3;pp<50;pp++){
		
		for (int kk=2;kk<pp;kk++){
			ArrayList mnP = new ArrayList<Integer>();
			
			mnP.add(0,pp);
			mnP.add(1,kk);
			mnBig.add(mnP);
						
		}
		
	}
	
	int[][] putvar = new int[mnBig.size()][2];
	System.out.println("Size:" + mnBig.size());
	System.out.println(mnBig);
	
	for(int tt = 0; tt<mnBig.size();tt++){
		ArrayList kyaInd = (ArrayList) mnBig.get(tt);
		int ni = (int) kyaInd.get(0);
		int mi = (int) kyaInd.get(1);
		putvar[tt][0] = ni;
		putvar[tt][1] = mi;	
		//System.out.println(putvar3[k][0]+","+putvar3[k][1]);
		
	}
	
	*/
	
	 /*----------------------------------------------------------------New section ----------------------------------------------- */
    
    	 
      int repeat = putvar.length;
      SolutionSet finalPopulation = new SolutionSet(1000000);
      for (int r = 0; r<repeat;r++){

   	 int n = putvar [r] [0];
   	 int m = putvar [r] [1];
   	 InputvariablesErasure.storeN(n);
   	 InputvariablesErasure.storeM(m);
   	 SolutionSet tempPopulation = new SolutionSet();
   	 tempPopulation = ErasStage2MOE_main.StageII(n,m,r);
   	 for (int k = 0; k<tempPopulation.getMaxSize();k++){
     finalPopulation.add(tempPopulation.get(k));}	
     }
      
      Ranking ranking = new Ranking(finalPopulation);
 ranking.getSubfront(0).printObjectivesToFile("FinalStageFUN"); 
// int [] [] putvar1 = ranking.getSubfront(0).putVariablesToString();
 ranking.getSubfront(0).printVariablesToFile("FinalStageVAR"); 
 int d = 1;
  } //StageII
} // StageIIMOE_main
