package java8;

import java.math.BigDecimal;

/**
 * Solves birthday problem for n people
 * @author Liam
 *
 */
public class BirthdayProblem {
	
	/**
	 * Gets factorial from 365
	 * @param n how many people
	 * @return factorial from 365 to 365-n
	 */
	public static double factorial(int n) {
		if(n==1) // base case n = 1
			return 365; // 365 * factorial 364 ... 365-n + 1
		else { // loop
			return ((365-n)+1)*factorial(n-1); // factorial from 364 to 365-n + 1
		}
	}

	public static void main(String[] args) {
		int n = 23; // amount
		BigDecimal d = new BigDecimal(Math.pow(1.0/365.0, n)); // base 1/365 to power of n
		BigDecimal d1 = new BigDecimal(factorial(n)); // factorial from 365 to 365-n
		double probability = 1-(d.multiply(d1).doubleValue()); // compliment base by factorial
		System.out.println((double)Math.round(probability*10000)/10000); // round to 4 decimals
	}

}