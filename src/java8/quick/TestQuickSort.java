package java8.quick;

import java.util.Arrays;

public class TestQuickSort {

	public static void main(String args[]) {
		int[] TEST_ARRAY = {4,2,6,2,7,5,21,3,5,99};
		int[] result = new QuickSort(TEST_ARRAY)
				.recQuickSort(0, 3);
		System.out.println(Arrays.toString(result));
	}
}
