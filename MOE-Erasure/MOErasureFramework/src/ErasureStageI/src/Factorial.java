package ErasureStageI.src;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;
public class Factorial {
	
	public BigInteger factor(int n){
    	BigInteger fact= BigInteger.ONE;
    	 

         for (int i = 2; i <= n; i++)
            {
                 fact = fact.multiply(new BigInteger(String.valueOf(i)));
         }
         
         return fact;
    	
    }
	
	
		
}
	






