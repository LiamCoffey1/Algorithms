import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Arrays {
	public static void main(String args[]) {
		Arrays arrs = new Arrays();
		int[] ret = findZigZags(new int[] {1,2,1,3,5,3,2,43,3,2,5,6,6,3,8,4,11,4,6,4,3,4});
		printArray(ret);
	}
	static void printArray(int[] arr) {
		for(int ele : arr) 
			System.out.print("[" + ele + "]");
		System.out.println();
	}
	
	int[][] rotateImage(int[][] a) {
	    int[][] newArray = new int[a.length][a.length];
	    for(int i = 0; i < a.length; i++) {
	        for(int j = 0; j < a.length; j++) {
	            newArray[i][j]= a[a.length-j-1][i];
	        }
	    }
	    return newArray;
	}
	
	
	static /*
	 * Given an array of integers arr and a positive integer m,
	 *  your task is to find the frequency of the most common element
	 *   within each continuous subarray of length m in arr.
		Return an array of these highest frequencies among subarray elements,
		ordered by their corresponding subarray's starting index.
			  You can look at the examples section for a better understanding.
	 */
	
	/*
	 * Let's say a triple (a, b, c) is a zigzag if either a < b > c or a > b < c.

		Given an array of integers numbers, your task is to check all the triples
		 of its consecutive elements for being a zigzag. More formally,
		  your task is to construct an array of length numbers.length - 2,
		   where the ith element of the output array equals 1 if
		    the triple (numbers[i], numbers[i + 1], numbers[i + 2]) is a zigzag,
		     and 0 otherwise.
	 */
	
	int[] findZigZags(int arr[]) {
		int[] zigzags = new int[arr.length-2];
		for(int i = 0; i < arr.length - 2; i++) {
			int a = arr[i];
			int b = arr[i+1];
			int c = arr[i+2];
			if((a < b && b > c) || (a > b && b < c))
				zigzags[i] = 1;
		}
		return zigzags;
	}
	
	
	/*
	 * Given an array of integers a and a set of queries of the form [l, r, x],
	 *  your task is to calculate the number of occurrences of the number x in the
	 *   inclusive subarray a[l..r] (0-based), for each query. Return the sum of the
	 *    answers for all queries as the result.

		Example
		
		For a = [1, 2, 1, 3, 1, 2, 1] and
		
		queries = [
		  [1, 3, 3],
		  [0, 4, 1],
		  [2, 5, 2],
		  [5, 6, 1]
		]
		the output should be countOnSubarrays(a, queries) = 6.
		
		The answer to the first query is 1: the number 3 appears 1 time in the subarray [2, 1, 3];
		The answer to the second query is 3: the number 1 appears 3 times in the subarray [1, 2, 1, 3, 1];
		The answer to the third query is 1: the number 2 appears 1 time in the subarray [1, 3, 1, 2];
		The answer to the fourth query is 1: the number 1 appears 1 time in the subarray [2, 1].
		So the answer is 1 + 3 + 1 + 1 = 6.
	 */
	int countOnSubarrays(int[] a, int[][] queries) {
	    int global_count = 0;
	    for(int i = 0; i < queries.length; i++) {
	        int l = queries[i][0];
	        int r = queries[i][1];
	        int x = queries[i][2];
	        int local_count = 0;
	        for(int j = l; j <= r; j++) {
	            if(a[j] == x)
	                local_count++;
	        }
	        global_count += local_count;
	    }
	    return global_count;
	}

	
	boolean constructorNames(String className, String methodName) {
	    String alphabet = "abcdefghijklmnopqrstuvwxyz";
	    if(className.length() != methodName.length())
	        return false;
	    if(className.length() == 1 && className.length() == 1 && !className.equals(methodName))
	        return false;
	    int[] frequencys1 = new int[27];
	    int[] frequencys2 = new int[27];
	    for(char c : className.toCharArray()) {
	        int index = alphabet.indexOf(c);
	        frequencys1[index]++;
	    }
	    for(char c2 : methodName.toCharArray()) {
	        int index = alphabet.indexOf(c2);
	        frequencys2[index]++;
	    }
	    java.util.Arrays.sort(frequencys1);
	    java.util.Arrays.sort(frequencys2);
	    for(int i = 0; i < frequencys1.length; i++) {
	        if(frequencys1[i] != frequencys2[i])
	            return false;
	    }
	    return true;
	}


	int[] occurrencesInSubarrays(int[] arr, int m) {
	    int[] frequencys = new int[10];
	    ArrayList<Integer> result = new ArrayList<Integer>();
	    if(arr.length <= m) {
	        frequencys = new int[10];
	        for(int k = 0; k < arr.length; k++) {
	            int current = arr[k];
	            frequencys[current]++;
	        }
	        int max = Integer.MIN_VALUE;
	        for(int j : frequencys) {
	            if(j > max)
	                max = j;
	        }
	        result.add(max);
	        return result.stream().mapToInt(i -> i).toArray();
	    }
	    for(int i = 0; i <= arr.length - m; i++) {
	        HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
	        int[] newfrequencys = new int[10];
	        for(int j = 0; j < m; j++) {
	            int current = arr[i + j];
	            System.out.println(arr[i + j]);
	            if(counts.get(current) != null)
	                counts.put(current, counts.get(current) + 1);
	            else counts.put(current, 1);
	        }
	        int max = Integer.MIN_VALUE;
	        for(int j : counts.values()) {
	            if(j > max)
	                max = j;
	        }
	        result.add(max);
	    }
	    return result.stream().mapToInt(i -> i).toArray();
	}



	int[] mostFrequentDigits(int[] a) {
	    int[] counts = new int[10];
	    for(int i : a) {
	        String x = "" + i;
	        for(char c : x.toCharArray()) {
	            int digit = Integer.parseInt("" + c);
	            counts[digit]++;
	        }
	    }
	    int maxCount = Integer.MIN_VALUE;
	    for(int j : counts) {
	        if(j > maxCount)
	            maxCount = j;
	    }
	    int numberWithMax = 0;
	    for(int k : counts) {
	        if(k == maxCount)
	            numberWithMax++;
	    }
	    int[] finalArr = new int[numberWithMax];
	    int pointer = 0;
	    for(int l = 0; l < counts.length; l++) {
	        if(counts[l] == maxCount) {
	            finalArr[pointer] = l;
	            pointer++;
	        }
	    } 
	    return finalArr;
	}







	/*You are given an array of integers a and two integers l and r. You task is to calculate a boolean array b, where b[i] = true if there exists an integer x, such that a[i] = (i + 1) * x and l <= x <= r. Otherwise, b[i] should be set to false.

	Example

	For a = [8, 5, 6, 16, 5], l = 1, and r = 3,
	 the output should be boundedRatio(a, l, r) = [false, false, true, false, true].

	For a[0] = 8, we need to find a value of x such that 1 * x = 8, but the only value
	 that would work is x = 8 which doesn't satisfy the boundaries 1 <= x <= 3, so b[0] = false.
	For a[1] = 5, we need to find a value of x such that 2 * x = 5, but there is no
	 integer value that would satisfy this equation, so b[1] = false.
	For a[2] = 6, we can choose x = 2 because 3 * 2 = 6 and 1 <= 2 <= 3, so b[2] = true.
	For a[3] = 16, there is no an integer 1 <= x <= 3, such that 4 * x = 16, so b[3] = false.
	For a[4] = 5, we can choose x = 1 because 5 * 1 = 5 and 1 <= 1 <= 3, so b[4] = true.
*/

	boolean[] boundedRatio(int[] a, int l, int r) {
	    boolean[] ret = new boolean[a.length];
	    for(int i = 0; i < a.length; i++) {
	        double x = a[i]/(i+1.0);
	        System.out.println(x);
	        if(x%1 != 0) {
	            System.out.println("non-int");
	            ret[i] = false;
	        
	        } else  
	        if(l <= x && x <= r)
	            ret[i] = true;
	    }
	    return ret;
	}




	/*You have been given a string s, which is supposed to be a sentence. However, someone
	 *  forgot to put spaces between the different words, and for some reason they capitalized
	 *   the first letter of every word. Return the sentence after making the following amendments:
	 *

	Put a single space between the words.
	Convert the uppercase letters to lowercase.*/

	String amendTheSentence(String s) {
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < s.length(); i++) {
	        char c = s.charAt(i);
	        if(Character.isUpperCase(c)) {
	            sb.append((i > 0 ? " " : "") + Character.toLowerCase(c));
	        }
	        else sb.append(c);
	    }
	    return sb.toString();
	}


	/*You have two integer arrays, a and b, and an integer target value v. Determine
	 *  whether there is a pair of numbers, where one number is taken from a and the othe
	 *  r from b, that can be added together to get a sum of v. Return true if such a pair
	 *   exists, otherwise return false.
	 */

	boolean sumOfTwo(int[] a, int[] b, int v) {
	    HashSet<Integer> aMap = new HashSet<Integer>(a.length);
	    for(int nums : a) {
	        aMap.add(nums);
	    }
	    for(int i : b) {
	        if(aMap.contains(v-i))
	            return true;
	    }
	    return false;
	}


	/*You have an array of integers nums and an array queries, where queries[i] is a pair of indices (0-based).
	 *  Find the sum of the elements in nums from the indices at queries[i][0] to queries[i][1] (inclusive)
	 *   for each query, then add all of the sums for all the queries together. Return that number modulo 10^9 + 7.
	 */
	int sumInRange(int[] nums, int[][] queries) {
		     if(nums.length==0 || queries.length==0) return 0;
		     int mod= 1000000007;
		     HashMap<Integer,Integer> map = new HashMap<>();
		     int sumPre = nums[0];
		     map.put(0,sumPre);
		     for(int i= 1; i< nums.length;i++){
		          sumPre= sumPre+nums[i];
		          map.put(i,sumPre%mod);
		     }   
		     int sum=0;
		     for(int i = 0; i< queries.length;i++){
		         if(queries[i][0]==0) 
		              sum = sum%mod +  map.get(queries[i][1])%mod;
		         else 
		              sum = sum%mod + map.get(queries[i][1])- map.get(queries[i][0]-1)%mod;      
		     }
		     return (sum+ mod)%mod;
		}

	boolean containsDuplicates(int[] a) {    
		    HashSet<Integer> set = new HashSet<Integer>();
		    for(int i =0; i< a.length; i++){
		        if(set.contains(a[i])) return true;
		        set.add(a[i]);
		    }
		    return false;
		}


	/*Kadanes Algorithm
	Some properties of this problem are:

	If the array contains all non-negative numbers, then the problem is trivial; a maximum
	 subarray is the entire array.
	If the array contains all non-positive numbers, then a solution is any subarray
	 of size 1 containing the maximal value of the array (or the empty subarray, if it is permitted).
	Several different sub-arrays may have the same maximum sum.*/

	int arrayMaxConsecutiveSum2(int[] inputArray) {
	    int local_max = 0;
	    int global_max = Integer.MIN_VALUE;
	    for(int i = 0; i < inputArray.length; i++) {
	        local_max = (inputArray[i] > (inputArray[i] + local_max)) ? inputArray[i] : (local_max + inputArray[i]);
	        if(local_max > global_max)
	            global_max = local_max;
	    }
	    return global_max;
	}





	
	int[][] rotateImageInPlace(int[][] a) {
	    int n = a.length;
	    for(int i = 0; i < n / 2; i++){
	        for(int j = i; j < n-i-1; j++){
	            int temp = a[i][j];
	            a[i][j] = a[n-j-1][i];
	            a[n-j-1][i] = a[n-1-i][n-1-j];
	            a[n-1-i][n-1-j] = a[j][n-1-i];
	            a[j][n-1-i] = temp;
	        }
	    }
	    return a;
	}

	
	char firstNotRepeatingCharacter(String s) {
	    String alphabet = "abcdefghijklmnopqrstuvwxyz";
	    int[] charCounts = new int[26];
	    for(char c : s.toCharArray()) {
	        charCounts[alphabet.indexOf(c)]++;
	        
	    }
	    for(char c2 : s.toCharArray()) {
	        if(charCounts[alphabet.indexOf(c2)] == 1)
	            return c2;
	    }
	    
	    return '_';
	}

	
	int firstDuplicate(int[] a) {
	    int max = Integer.MIN_VALUE;
	    for(int val : a)
	        if(val > max)
	            max = val;
	    int[] map = new int[max+1];
	    for(int i : a) {
	        if(map[i] != 0)
	            return i;
	        map[i]++;
	    }    
	    return -1;
	}

	
	public static ArrayList<Integer> duplicates(int arr[], int n) {
        java.util.Arrays.sort(arr);;
        ArrayList<Integer> dups = new ArrayList<Integer>();
        int lastDuplicate = -1;
        for(int i = 0; i < arr.length-1; i++) {
            if(arr[i] == arr[i+1] && lastDuplicate != arr[i]) {
                dups.add(arr[i]);
                lastDuplicate = arr[i];
            }
        }
        if(dups.size() == 0)
            dups.add(-1);
        return dups;
    }
	
	void findDuplicate() {
		int[] arr = new int[] {1,2,3,4,4,5,5,6,1};
		for(int i = 0; i < arr.length-1; i++) {
			if(arr[i] == arr[i+1])
				System.out.println("Found duplicate at index: " +  (i+1));
		}
	}
	
	void removeDuplicates() {
		int[] arr = new int[] {1,2,3,4,4,5,5,6,1,7,7,7,7,7,9};
		printArray(arr);
		int pose = 0;
		for(int i=1 ; i< arr.length; i++) {
			if(arr[i] != arr[i-1]) {
				pose++;
				arr[pose] = arr[i];
			}
		}
		System.out.println("Last element index: " + (pose-1));
		printArray(arr);
	}
	
	
	void largestSmallestUnsorted() {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int[] arr = new int[] {1,2,3,4,4,5,5,6,1};
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > max)
				max = arr[i];
			if(arr[i] < min)
				min = arr[i];
		}
		System.out.println("Max: " + max + " Min: " + min);
	}
	
	void missingNumber() {
		int[] arr = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,15,16};
		int expected = arr[0];
		for(int i = 0; i < arr.length; i++) {
			if( arr[i] != expected) {
				System.out.println("Missing Number:" + expected);
				return;
			}
			expected++;
		}
	}
	void missingNumberUnOrdered() {
		int[] arr = new int[] {3,7,1,4,5,6};
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if(arr[i] > max)
				max = arr[i];
		}
		int expectedSum = 0;
		while(max > 0) {
			expectedSum += max;
			max--;
		}
		int answer = expectedSum - sum;
		System.out.println(answer);
	}
	
	
	
	static void addIfNotDuplicate(int index, int[] array, ArrayList<Integer> list_ptr) {
		if( index == 0 || array[index] != array[index-1])
			list_ptr.add(array[index]);
	}
	//Function to return a list containing the union of the two arrays.
	// With repeating elements
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m)
    {
        ArrayList<Integer> union = new ArrayList<Integer>();
        int i = 0; int j = 0;
        
        // Terminate when one reaches end of array
		while(i < n && j < m) {
		    if(arr1[i] < arr2[j]) {
		    	addIfNotDuplicate(i, arr1, union);
		        i++;
		    } else if (arr1[i] > arr2[j]) {
		    	addIfNotDuplicate(j, arr2, union);
		        j++;
		    } else { // arr1[i] == arr2[j]
		    	addIfNotDuplicate(i, arr1, union);
		        i++;
		        j++;
		    }
		}
		//Fill Remaining
		while(i < n) {
			addIfNotDuplicate(i, arr1, union);
		    i++;
		}
		//Fill Remaining
		while(j < m) {
		    addIfNotDuplicate(j, arr2, union);
		    j++;
		}
		return union;
        
    }
    
    public static ArrayList<Integer> findIntersection(int arr1[], int arr2[], int n, int m)
    {
        ArrayList<Integer> intersection = new ArrayList<Integer>();
        int i = 0; int j = 0;
        
        // Terminate when one reaches end of array
		while(i < n && j < m) {
		    if(arr1[i] < arr2[j]) {
		        i++;
		    } else if (arr1[i] > arr2[j]) {
		        j++;
		    } else { // arr1[i] == arr2[j]
		    	addIfNotDuplicate(i, arr1, intersection);
		        i++;
		        j++;
		    }
		}
		return intersection;
        
    }
    
    int fibb(int n) {
        if(n <= 1) {
            return n;
        }
        return fibb(n -1 ) + fibb(n - 2);
    }
    /*
     * You are climbing a staircase that has n steps. You can take the steps either 1 or 2 at a time.
     *  Calculate how many distinct ways you can climb to the top of the staircase.
     */
    int climbingStairs(int n) {
        return fibb(n+1);
    }
    
    
    
    int[][] swapDiagonals(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            int temp = matrix[i][matrix.length-i-1];
            matrix[i][matrix.length-i-1] = matrix[i][i];
            matrix[i][i] = temp;
        }
        return matrix;
    }
    
    int[] extractMatrixColumn(int[][] matrix, int column) {
        int[] columnRet = new int[matrix.length];
        int pointer = 0;
        for(int i = 0; i < matrix.length; i++) {
            columnRet[pointer++] = matrix[i][column];
        }
        return columnRet;
    }

    
    boolean areIsomorphic(int[][] array1, int[][] array2) {
        if(array1.length != array2.length)
            return false;
        for(int i = 0; i < array1.length; i++) {
            if(array1[i].length != array2[i].length)
                return false;
        }
        return true;
    }

    

	int[][] reverseOnDiagonals(int[][] matrix) {
	    for(int i = 0; i < matrix.length/2; i++) {
	        int temp = matrix[i][i];
	        matrix[i][i] = matrix[matrix.length-i-1][matrix.length-i-1];
	        matrix[matrix.length-i-1][matrix.length-i-1] = temp;
	    }
	    for(int j = 0; j < matrix.length/2; j++) {
	        int temp = matrix[j][matrix.length-j-1];
	        matrix[j][matrix.length-j-1] = matrix[matrix.length-j-1][j];
	        matrix[matrix.length-j-1][j] = temp;
	    }
	    return matrix;
	}
    
    
    /*
     * Given a rectangular matrix and integers a and b, consider the union of the ath row and the
     *  bth (both 0-based) column of the matrix (i.e. all cells that belong either to the ath row
     *   or to the bth column, or to both). Return sum of all elements of that union.
     */
    int crossingSum(int[][] matrix, int a, int b) {
        int sum = 0;
        for(int i = 0; i< matrix.length; i++) {
            if(i != a) {
                sum += matrix[i][b];
            }
            else for(int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }
    
	/*
	 * Time, O(N)
	 * Extra Space O(N)
	 */
	void allPairsSum() {
		int sum = 10;
		int[] arr = new int[] {1,2,3,4,5,6,7,8,9,10};
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < arr.length; i++) {
			/*
			 * If a solution for current element and an already existing
			 * element exists, we found a pair for the sum
			 */
			if(map.containsKey(sum - arr[i]))
				System.out.println("Pair found: " + arr[i] + ", " + arr[map.get(sum - arr[i])]);
			/*
			 * Store current element
			 */
			else map.put(arr[i], i);
		}
	}

    
    boolean sudoku(int[][] grid) {
        //Check rows
        boolean[] values = new boolean[10];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                int value = grid[i][j];
                if(values[value] == true)
                    return false;
                values[grid[i][j]] = true;
            }
            values = new boolean[10];
        }
        //Check columns
        values = new boolean[10];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                int value = grid[j][i];
                if(values[value] == true)
                    return false;
                values[grid[j][i]] = true;
            }
            values = new boolean[10];
        }
        //Check 3x3 windows
        values = new boolean[10];
        for(int i = 0; i < 9; i+= 3) {
            for(int z = 0; z < 9; z+= 3) {
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 3; k++) {
                    int value = grid[j+z][k+i];
                    if(values[value] == true)
                        return false;
                    values[value] = true;
                }
            }
                    values = new boolean[10];
            }
        }
        return true;
    }


    
    
    /*
     * A noob programmer was given two simple tasks: sum and sort the elements of the given array
		a = [a1, a2, ..., an]. He started with summing and did it easily, but decided to store the
		 sum he found in some random position of the original array which was a bad idea. Now he needs
		  to cope with the second task, sorting the original array a, and it's giving him trouble since he modified it.

		Given the array shuffled, consisting of elements a1, a2, ..., an, a1 + a2 + ... + an in random order,
		 return the sorted array of original elements a1, a2, ..., an.
     */
    int[] shuffledArray(int[] shuffled) {
        int sum = 0;
        for(int i : shuffled) {
            sum += i;
        }
        for(int k = 0; k < shuffled.length; k++) {
            int value = shuffled[k];
            if(sum - value == value)  {
                shuffled[k] = Integer.MAX_VALUE;
                break;
            }
        }
        int[] sorted = new int[shuffled.length-1];
        int pointer = 0;
        int currentMin = Integer.MAX_VALUE;
        int minIndex = 0;
        while(pointer != sorted.length) {
            for(int j =0; j < shuffled.length; j++) {
                if(shuffled[j] < currentMin) {
                    currentMin = shuffled[j];
                    minIndex = j;
                }
            }
            sorted[pointer++] = currentMin;
            shuffled[minIndex] = Integer.MAX_VALUE;
            currentMin = Integer.MAX_VALUE;
        }
        return sorted;
    }

	      
	

	void reverseInPlace() {
		int[] arr = new int[] {1,2,3,4,6};
		printArray(arr);
		for(int i = 0; i < arr.length >> 1; i++) {
			int temp = arr[i];
			int opposite = arr.length - 1 - i;
			arr[i] = arr[opposite];
			arr[opposite] = temp;
		}
		printArray(arr);
	}
	
	
	/*
	 * Given a square matrix of integers m, your task is to rearrange its numbers in the following way:

		First, sort its values in ascending order of how frequently the number occurs in m. In the case of a tie,
		 sort the equally frequent numbers by their values, in ascending order.
		Second, place the sorted numbers diagonally, starting from the bottom right corner, like this:
	 */
	
	/*int[][] sortMatrixByOccurrences(int[][] m) {
	    int[] temp = new int[m.length ^ 2];
	    int pointer = 0;
	    for(int i = 0; i < m.length; i++) {
	        for(int j = 0; j < m.length; j++) {
	            temp[pointer++] = m[i][j];
	        }
	    }
	    
	}*/

	/**
	*
	*   [0,1,2,3] - 0
	    [0,1,2,3] - 1
	    [0,1,2,3] - 2
	    [0,1,2,3] - 3
	    
	    1 - [3,3]
	    
	    2 = [3,2]
	    3 = [2,3]
	    
	    4 = [3,1]
	    5 = [2,2]
	    6 = [1,3]
	    
	    7 = [3,0]
	    8 = [2,1]
	    9 = [1,2]
	    10 =[0,3]
	    
	    11 = [1,0]
	    12 = [0,1]
	    
	    13 = [0,0]
	*
	*
	*
	*
	/


	/*
	 * 	/*You are given a string s. Your task is to count the number of ways of splitting s into three non-empty parts a, b and c (s = a + b + c) in such a way that a + b, b + c and c + a are all different strings.

	NOTE: + refers to string concatenation.

	Example

	For s = "xzxzx", the output should be countWaysToSplit(s) = 5.

	Consider all the ways to split s into three non-empty parts:

	If a = "x", b = "z" and c = "xzx", then all a + b = "xz", b + c = "zxzx" and c + a = xzxx are different.
	If a = "x", b = "zx" and c = "zx", then all a + b = "xzx", b + c = "zxzx" and c + a = zxx are different.
	If a = "x", b = "zxz" and c = "x", then all a + b = "xzxz", b + c = "zxzx" and c + a = xx are different.
	If a = "xz", b = "x" and c = "zx", then a + b = b + c = "xzx". Hence, this split is not counted.
	If a = "xz", b = "xz" and c = "x", then all a + b = "xzxz", b + c = "xzx" and c + a = xxz are different.
	If a = "xzx", b = "z" and c = "x", then all a + b = "xzxz", b + c = "zx" and c + a = xxzx are different.
	Since there are five valid ways to split s, the answer is 5.

	Input/Output

	[execution time limit] 3 seconds (java)

	[input] string s

	A string to split.

	Guaranteed constraints:
	3 <= s.length <= 100.

	[output] integer

	The number of ways to split the given string.




	Consider a map of city streets, in the form of a grid.

	example

	You'd like to know if it's possible to make your way to the exit, under the following rules:

	You begin from the left side of the square in the top-left corner;
	The exit is on the right side of the square in the bottom-right corner;
	You must travel along a connected path between squares.
	You're given directions, a matrix of integers representing the grid of streets, where each integer corresponds to a different type of road square:

	0 stands for 0
	1 stands for 1
	2 stands for 2
	3 stands for 3
	4 stands for 4
	5 stands for 5
	Your task is to return true if it's possible to reach the exit, and false otherwise.

	Example

	For directions = [[0, 2, 1], [5, 4, 0]], the output should be trafficMap(directions) = true.

	The map looks as follows:

	example1

	It's possible to enter the top-left square from the left, travel along a connected path, and exit the right side of the bottom-right square. So the answer is true.

	For directions = [[0, 2, 1], [5, 4, 1]], the output should be trafficMap(directions) = false.

	The map looks as follows:

	example2

	It's possible to enter the top-left square from the left, but there's no connected path that leads to the bottom-right square. So the answer is false.

	For directions = [[0, 2, 1], [5, 4, 2]], the output should be trafficMap(directions) = false.

	The map looks as follows:

	example3

	The path leading to the bottom-right square exists, but it doesn't exit to the right.

	For directions = [[1], [4]], the output should be trafficMap(directions) = false.

	The map looks as follows:

	example4

	It's possible to travel along the pat
	 */

	/*
	 * 	/*Given an array of integers arr and a positive integer m, your task is to find the frequency of the most common element within each contiguous subarray of length m in arr.

	Return an array of these highest frequencies among subarray elements, ordered by their corresponding subarray's starting index. You can look at the examples section for a better understanding.


	Let's say a triple (a, b, c) is a zigzag if either a < b > c or a > b < c.

	Given an array of integers numbers, your task is to check all the triples of its consecutive elements for being a zigzag. More formally, your task is to construct an array of length numbers.length - 2, where the ith element of the output array equals 1 if the triple (numbers[i], numbers[i + 1], numbers[i + 2]) is a zigzag, and 0 otherwise.



	Suppose you are creating a new programming language. This language will support OOP, and to make it special, you want some features of it to differ from the standard.

	In some languages (such as C or Java), class constructor names are forced to have the same name as the class. Here you want to weaken this restriction, and to allow constructor names to be acceptable as long as they're close to the class name. We will consider two strings close if one can be obtained from the other, using the following operations:

	swap any two symbols in one of the strings,
	swap occurrences of any two existing symbols in one of the strings (for example, if your string contains both as and bs, you can change all as to bs and all the bs to as).
	Now you want to write a method that finds out whether the given methodName is considered close to the given className, by the definition above.

	Hint: One of the possible ways to solve the task might be the following. For both methodName and className build a map with the numbers of occurrences of each symbol. Then check whether the sets of the keys of both maps, containing symbols of the names, are equal. Since all occurrences of any two existing symbols can be freely swapped, you can, finally, check whether the multisets of the values of both maps are also equal.



	You are given an array of integers arr. Your task is to count the number of contiguous subarrays, such that each element of the subarray appears at least twice.

	Example

	For arr = [0, 0, 0], the output should be duplicatesOnSegment(arr) = 3.

	There are 3 subarrays that satisfy the criteria of containing only duplicate elements:

	arr[0..1] = [0, 0]
	arr[1..2] = [0, 0]
	arr[0..2] = [0, 0, 0]
	For arr = [1, 2, 1, 2, 3], the output should be duplicatesOnSegment(arr) = 1.

	There is only 1 applicable subarray: arr[0..3] = [1, 2, 1, 2]
	 */
	
}
