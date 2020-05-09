package ErasureStageI.conf;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;




/**
 * Class representing the input variables of the Erasure MO-FRAMEWORK
 */

public class InputvariablesErasure {

static int n; 

static int m; 
 

/* Returns the number of fragments from the KB*/
public static void storeN (int ni) {
	 
	 n = ni ;
}

public static void storeM (int mi) {
	 
	 m = mi ;
}

public static int getN () {
	 
	 return n;
}

public static int getM () {
	 
	 return m;
}





}
