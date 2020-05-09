package ErasureStageI.src;

import java.util.Random;


import java.util.Random;

public class SystemC {

	public static void main(String[] args) {

		for (int i = 0; i < 200; i++) {
			 System.out.print(getRandomNumberInRange(10, 120)+",");
			//System.out.println(givenUsingPlainJava_whenGeneratingRandomFloatBouned_thenCorrect()+",");
		}

	}

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public static double givenUsingPlainJava_whenGeneratingRandomFloatBouned_thenCorrect() {
	   
		double rangeMin = 0.99;
	    double rangeMax = 0.999999999;
	   // double generatedFloat = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
	   // return generatedFloat; 
		
		Random r = new Random();
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		
		return randomValue;
	}

}