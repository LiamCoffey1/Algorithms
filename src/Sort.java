
public class Sort {
	public static void main(String args[]) {
		int[] unsorted = new int[] {4,1,3,9,7};
		unsorted = selectionSort(unsorted);
		for(int i : unsorted) {
			System.out.println(i);
		}
	}
	/*
	 * Scan along array multiple times
	 * in each pass swap any pair where i > i+1
	 * each pass keep track of how many swaps
	 * if swaps == 0 - array sorted, terminate
	 */
	static int[] bubbleSort(int[] arr) {
		int numSwaps = 1;
		while(numSwaps != 0) {
			int tempSwaps = 0;
			for(int i = 0; i < arr.length-1; i++) {
				if(arr[i] > arr[i+1]) {
					int temp = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = temp;
					tempSwaps++;
				}
			}
			numSwaps = tempSwaps;
		}
		return arr;
	}
	
	/*
	 *  Two constructs:
	 *  Unsorted part, sorted part
	 *  Bound marked by index j
	 *  Find minimum element in unsorted part
	 *  and swap with current bound j
	 *  bound j moves up on each time a min is found
	 *  i.e next element is in final sorted position
	 */
	static void select(int arr[], int i)
	{
	    int minIndex = i;
        for(int j = i; j <arr.length; j++) {
            if(arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        int temp = arr[minIndex];
        arr[minIndex] = arr[i];
        arr[i] = temp;
	}
	
	static int[] selectionSort(int arr[])
	{
	    int j = 0;
	    while(j != arr.length) {
	        select(arr,j);
	        j++;
	    }
	    return arr;
	}
	/*
	 * Scan along array, for each val check left val
	 * if left > right, swap, keep going for same val
	 * until no more swaps
	 * Continue with rest of vals
	 */
	static int[] insertionSort(int[] arr) {
		int newIndex;
		for(int i = 1; i < arr.length; i++) {
			newIndex = i;
			int currentValue = arr[i];
			while(newIndex > 0 && currentValue < arr[newIndex-1]) {
				int temp = arr[newIndex-1];
				arr[newIndex-1] = arr[newIndex];
				arr[newIndex] = temp;
				newIndex--;
			}
			arr[newIndex] = currentValue;
		}
		return arr;
	}
	
	
	/*
	 * A noob programmer was given two simple tasks: sum and sort the elements of the given array
a = [a1, a2, ..., an]. He started with summing and did it easily, but decided to store the sum he found in some random position of the original array which was a bad idea. Now he needs to cope with the second task, sorting the original array a, and it's giving him trouble since he modified it.

Given the array shuffled, consisting of elements a1, a2, ..., an, a1 + a2 + ... + an in random order, return the sorted array of original elements a1, a2, ..., an.
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

	
	
	/*
	 * Some people are standing in a row in a park. There are trees between them which cannot be moved. Your task is to rearrange the people by their heights in a non-descending order without moving the trees. People can be very tall!

Example

For a = [-1, 150, 190, 170, -1, -1, 160, 180], the output should be
sortByHeight(a) = [-1, 150, 160, 170, -1, -1, 180, 190].
	 */
	
	int[] sortByHeight(int[] a) {
	    int newIndex;
	    int startIndex;
	    for(int i = 1; i < a.length; i++) {
	       newIndex = i;
	       startIndex = i;
	        int currentValue = a[i];
	        if(currentValue != -1) {
	            while(newIndex > 0 && (currentValue < a[newIndex-1] || a[newIndex-1] == -1)) {
	                if(a[newIndex-1] == -1)
	                    newIndex--;
	                else {
	                    int temp = a[newIndex-1];
	                    a[newIndex-1] = a[startIndex];
	                    a[startIndex] = temp;
	                    newIndex--;
	                    startIndex = newIndex;
	                }
	            }
	        }
	    }
	    return a;
	}

	
	/*
	 * Given an array of strings, sort them in the order of
	 *  increasing lengths. If two strings have the same length,
	 *  their relative order must be the same as in the initial
	 *   array.
	 */
	
	String[] sortByLength(String[] inputArray) {
        int newIndex;
		for(int i = 1; i < inputArray.length; i++) {
			newIndex = i;
			int currentLength = inputArray[i].length();
            String currentValue = inputArray[i];
			while(newIndex > 0 && currentLength < inputArray[newIndex-1].length()) {
				String temp = inputArray[newIndex-1];
				inputArray[newIndex-1] = inputArray[newIndex];
				inputArray[newIndex] = temp;
				newIndex--;
			}
			inputArray[newIndex] = currentValue;
		}
		return inputArray; 
}

	
	
	
}
