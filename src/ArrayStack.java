public class ArrayStack {
	
	int maxSize;
	int[] elements;
	int head = -1;
	
	
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		elements = new int[maxSize];
	}
	
	public int size() {
		return head + 1;
	}
	

	public int pop() {
		if(isEmpty())
			return -1;
		return elements[head--];
	}
	
	public int peek() {
		if(isEmpty())
			return -1;
		return elements[head];
	}
	
	public void push(int element) {
		if(!isFull())
			elements[++head] = element;
		else throw new IndexOutOfBoundsException();
	}
	
	public boolean isFull() {
		return head == maxSize - 1;
	}
	
	public boolean isEmpty() {
		return head == -1;
	}
}
