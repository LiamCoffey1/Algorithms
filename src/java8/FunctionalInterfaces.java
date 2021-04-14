package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/*
 * Class wich checks if a name is on a list through a functional Interface
 * @Author Liam Coffey
 */
public class FunctionalInterfaces {

	/*
	 * Functional Interface must contain one and only one abstract method
	 *
	 */
	@FunctionalInterface
	interface FunctionsInterface {
		boolean containsName(String name);
	}
	
	/*
	 * Checks if name is on list
	 * @param name, the name from input
	 */
	public static boolean checkName(String name) {
		Predicate<String> predicate = (p) -> p == name; // Converts the name into a predicate
		List<String> validNames = Arrays.asList("Bob", "Jane", "Collin", "Beth");
		if(validNames.parallelStream().anyMatch(predicate)) // if any of the names match our input
			return true;
		else
			return false;
	}
	
	/*
	 * Main method handles creating logic and output
	 * 
	 */
	public static void main(String args[]) {
		FunctionsInterface fi = (String var) -> checkName(var); // logic 
		System.out.println(fi.containsName("Neill") ? "Yes" : "No");
	}

}
