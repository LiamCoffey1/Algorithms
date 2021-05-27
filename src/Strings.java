import java.util.Arrays;
import java.util.HashMap;

public class Strings {

	public static void main(String args[]) {
		Strings strs = new Strings();
		String input = "1,2,3";
		strs.reverseWords();
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
