package java8.quick;


public class QuickSort {
	
	public int[] array;
	
	public QuickSort(int[] array) {
		this.array = array;
	}
	
	
	public void swap(int position1, int position2) {
		int temp = array[position2];
		array[position2] = array[position1];
		array[position1] = temp;
	}
	
	public int partitionIt(int left, int right, long pivot) {
		 int leftPtr = left-1; 
		 int rightPtr = right; 
		 while(true) {
			 while( array[++leftPtr] < pivot ){ }
			 while(rightPtr > 0 && array[--rightPtr] > pivot){ }
			 if(leftPtr >= rightPtr)
				 break; 
			 else 
				 swap(leftPtr, rightPtr);
		 }
		 swap(leftPtr, right); 
		 return leftPtr; 
	}

	public int[] recQuickSort(int left, int right) {
		if(right-left <= 0)
			return array;
		else {
			long pivot = array[right];
			int partion = partitionIt(left,right,pivot);
			recQuickSort(left,partion-1);
			recQuickSort(partion+1,right);
		}
		return array;
	}
	

}




