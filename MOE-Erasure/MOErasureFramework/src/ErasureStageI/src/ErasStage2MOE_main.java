
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
import ErasureStageI.conf.ErasStage2Config;
import jmetal.qualityIndicator.QualityIndicator;
import jmetal.util.Configuration;
import jmetal.util.JMException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;




/** 
*ERASURE STAGE 2
*
*/ 

public class ErasStage2MOE_main {
  public static FileHandler fileHandler_ ; // FileHandler object if there is requirment for the objectives and variables to be stored in file
  public static Logger      logger_ ;
  /**
   * @param The arguments are sent through exec.moExecute.
   * @throws JMException 
   * @throws IOException 
   * @throws SecurityException
   * @throws ClassNotFoundException 
   * @throws ParseException
 * @return 
   */
  
  
  
  public static SolutionSet StageII (int n, int m, int run) throws 
                                  JMException, 
                                  SecurityException, 
                                  IOException, 
                                  ClassNotFoundException{
    Problem   problem   ; 
    Algorithm algorithm ; 
    Operator  crossover ; 
    Operator  mutation  ; 
    Operator  selection ; 
    
    HashMap  parameters ; // The parameters for the NSGA-II algorithm are stored in this HashMap
    QualityIndicator indicators ; // Object to get quality indicators
    
    // Logger object and file to store log messages
    logger_      = Configuration.logger_ ;
    fileHandler_ = new FileHandler("Stage2-NSGAII_main"+run+".log"); 
    logger_.addHandler(fileHandler_) ;
    
    
    indicators = null ;
    problem = new ErasStage2Config(n);
    algorithm = new ErasStage2MOE(problem);

    // NSGA-II parameters
    algorithm.setInputParameter("populationSize",1000);
    algorithm.setInputParameter("maxEvaluations",10000);
    

    // Crossover probability and distribution
    parameters = new HashMap();
    parameters.put("probability", 0.95) ;
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
    SolutionSet population = algorithm.execute1(n,m);
    long estimatedTime = System.currentTimeMillis() - initTime;
    
   
    
    // Result messages
    logger_.info("Total execution time for Stage 2: " +estimatedTime + "ms");
    logger_.info("Stage 1: Objectives values have been writen to file FUN");
    population.printObjectivesToFile("Stage2-FUN"+run);
    logger_.info("Stage 1: Variables values have been writen to file VAR");
    population.printVariablesToFile("Stage2-VAR"+run);
    
    indicators = new QualityIndicator(problem, "Stage2-FUN"+run) ; 
 
      logger_.info("Quality indicators") ;
      logger_.info("Hypervolume: " + indicators.getHypervolume(population)) ;
   //   logger_.info("GD         : " + indicators.getGD(population)) ;
     // logger_.info("IGD        : " + indicators.getIGD(population)) ;
      logger_.info("Spread     : " + indicators.getSpread(population)) ;
      //logger_.info("Epsilon    : " + indicators.getEpsilon(population)) ;

  //    if (algorithm.getOutputParameter("evaluations") != null) {
   //     Integer evals = (Integer)algorithm.getOutputParameter("evaluations") ;
     //   int evaluations = (Integer)evals.intValue();
     //   logger_.info("Speed      : " + evaluations + "quality") ;
    //  } // if
	  
 return population;
  } //StageII
} // StageIIMOE_main
