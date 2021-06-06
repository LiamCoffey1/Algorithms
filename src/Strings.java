import java.util.Arrays;
import java.util.HashMap;

public class Strings {

	public static void main(String args[]) {
		Strings strs = new Strings();
		String input = "1,2,3";
		strs.reverseWords();
	}
	
	
	/*
	 * Given an array of strings, return another array containing all of its longest strings.
	 */
	String[] allLongestStrings(String[] inputArray) {
	    int maxLength = Integer.MIN_VALUE;
	    for(String s : inputArray) {
	        if(s.length() > maxLength)
	            maxLength = s.length();
	    }
	    int countsOfMax = 0;
	    for(String s1 : inputArray) {
	        if(s1.length() == maxLength)
	            countsOfMax++;
	    }
	    String[] s = new String[countsOfMax];
	    int pointer = 0;
	    for(String s3 : inputArray) {
	        if(s3.length() == maxLength)
	            s[pointer++] = s3;
	    }
	    return s;
	}

	
	/*
	 * Idea reverse whole sentence then reverse each word
	 */
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
	
	
	/**
	 * Given two strings s and t, both consisting of lowercase English letters and digits, your task is to calculate how many ways exactly one digit could be removed from one of the strings so that s is lexicographically smaller than t after the removal. Note that we are removing only a single instance of a single digit, rather than all instances (eg: removing 1 from the string a11b1c could result in a1b1c or a11bc, but not abc).

Also note that digits ar
	 * @param s
	 * @param t
	 * @return
	 */
	int removeOneDigit(String s, String t) {
	    int count = 0;
	    for(int i = 0; i < s.length(); i++) {
	        if(Character.isDigit(s.charAt(i))) {
	            String comparator = s.replace("" + s.charAt(i), "");
	            if(comparator.compareTo(t) < 0)
	                count++;
	        }
	    }
	    for(int j = 0; j < t.length(); j++) {
	         if(Character.isDigit(t.charAt(j))) {
	            String comparator = t.replace("" + t.charAt(j), "");
	            if(comparator.compareTo(s) > 0)
	                count++;
	         }
	    }
	    return count;
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
	
	public static boolean isAnagramSorting(String a,String b)
    {
        if(a.length() != b.length())
        	return true;
        char[] c = a.toCharArray();
        char[] d = b.toCharArray();
     
        Arrays.sort(c);
        Arrays.sort(d);
        for(int i = 0; i < c.length; i++) {
        	if (c[i] != d[i])
        		return false;
        }
        return true;
        
    }
	
	/**
	 * IsAnagram
	 * Idea, store counts of each char
	 * If found and not found previously - 1
	 * If found and found previously - 0
	 * counts should all be 0 by the end of iters
	 */
	public static boolean isAnagram(String a,String b)
    {
        
        HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
        if(a.length() != b.length())
            return false;
        for(int i = 0; i < a.length(); i++) {
            char first = a.charAt(i);
            char second = b.charAt(i);
            
            if(counts.get(first) != null && counts.get(first) != 0)
                counts.put(first, 0);
            else counts.put(first, 1);
            
            if(counts.get(second) != null && counts.get(second) != 0)
                counts.put(second, 0);
            else counts.put(second, 1);
            
        }
        for(int vals : counts.values()) {
            if(vals != 0)
                return false;
        }
        return true;
        
    }
	
}
