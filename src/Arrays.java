import java.util.HashMap;

public class Arrays {
	public static void main(String args[]) {
		Arrays arrs = new Arrays();
		arrs.missingNumberUnOrdered();
	}
	void printArray(int[] arr) {
		for(int ele : arr) 
			System.out.print("[" + ele + "]");
		System.out.println();
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
}
