package java8;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/*
 * Class that checks if the name matches the predicate and writes accordingly
 * @Author Liam Coffey
 */
public class Predicate_ {
	
	/*
	 * Checks if the name is in the list
	 * @param s, The name being checked
	 * @return boolean, predicate valid or invalid
	 */
	static boolean checkName(String s) {
		Predicate<String> name = (p) -> p == s; // Converts the name into a predicate
		List<String> validNames = Arrays.asList("Bob", "Jane", "Collin", "Beth");
		if(validNames.parallelStream().anyMatch(name)) // if any of the names match our input
			return true;
		else
			return false;
	}

	/*
	 * Main method declares predicate and checks
	 */
	public static void main(String args[]) {
		Predicate<String> valid = (p) -> checkName(p); // predicate valid if name is in list
		String name = "Bob";  // input
		System.out.println("Name " + (valid.test(name) ? "Valid": "Not Valid")); // output
	}
}
