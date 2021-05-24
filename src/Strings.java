import java.util.HashMap;

public class Strings {

	public static void main(String args[]) {
		Strings strs = new Strings();
		String input = "1,2,3";
		strs.reverseWords();
	}
	
	void reverseWords() {
		String test = "Hello i am liam";
		String reversed = reverseRecurse(test);
		StringBuilder finalString = new StringBuilder("");
		String[] words = reversed.split(" ");
		for(String word : words) {
			finalString.append(reverseRecurse(word) + " ");
		}
		System.out.println(finalString);
	}
	
	void occurrence() {
		String input = "test";
		char toFind = 't';
		int count = 0;
		for(char c : input.toCharArray())
			if(c == toFind)
				count++;
	}
	void printArray(int[] arr) {
		for(int ele : arr) 
			System.out.print("[" + ele + "]");
		System.out.println();
	}
	
	void isPalindrome() {
		String pal = "miaaim";
		boolean isPal = true;
		for(int i = 0; i < pal.length() >> 1; i++) {
			if(pal.charAt(i) != pal.charAt(pal.length() - i-1))
				isPal = false;
		}
		System.out.println("Is palindrome: " + isPal);
	}
	
	void onlyDigitsIter() {
		String test1 =  "1235a4";
		boolean onlyDigit = true;
		for(int i = 0; i < test1.length(); i++) {
			int charNumber = (int)test1.charAt(i);
			if( !(charNumber >= 48 && charNumber <= 57)) {
				onlyDigit = false;
			}
		}
		System.out.println("Is only digit: " + onlyDigit);
	}
	
	/**
	 * Chop off a character at the end and append to final
	 * result
	 * Base case, when only one char left in string
	 * @param input string
	 * @return reversed string
	 */
	String reverseRecurse(String input) {
		if(input.length() == 1)
			return "" + input.charAt(0);
		else return input.charAt(input.length()-1) + reverseRecurse(input.substring(0, input.length()-1));
	}
	
	/**
	 * IsAnagram
	 * Idea, store counts of each char
	 * If found and not found previously - 1
	 * If found and found previously - 0
	 * counts should all be 0 by the end of iters
	 */
	HashMap<Character, Integer> charCounts = new HashMap<Character, Integer>();
	void check(char c) {
		if(charCounts.containsKey(c))
			charCounts.put(c, 0);
		else charCounts.put(c, 1);
	}
	void isAnagram() {
		boolean isAnagram = true;
		String str1 = "Liam", str2 = "iamL";
		if(str1.length() == str2.length()) { // If not equal definitely not anagram
			for(int i = 0 ; i < str1.length(); i++) {
				check(str1.charAt(i));
				check(str2.charAt(i));
			}
			for(Integer count : charCounts.values())
				if(count != 0)
					isAnagram = false;
		} 
		else isAnagram = false;
		System.out.println("Is an anagram: " + isAnagram);
	}
	
}
