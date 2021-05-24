import java.util.ArrayList;

public class GenericStack<Type> {
	
	int maxSize;
	
	ArrayList<Type> elements;
	
	
	public GenericStack(int maxSize) {
		this.maxSize = maxSize;
		elements = new ArrayList<Type>();
	}
	
	public int size() {
		return elements.size();
	}
	

	public Type pop() {
		if(isEmpty())
			return null;
		Type element = elements.get(elements.size() - 1);
		elements.remove(element);
		return element;
	}
	
	public Type peek() {
		if(isEmpty())
			return null;
		Type element = elements.get(elements.size() - 1);
		return element;
	}
	
	public void push(Type element) {
		if(!isFull())
			elements.add(element);
		else throw new IndexOutOfBoundsException();
	}
	
	public boolean isFull() {
		return elements.size() == maxSize;
	}
	
	public boolean isEmpty() {
		return elements.size() == 0;
	}
}
