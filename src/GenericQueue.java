import java.util.ArrayList;
import java.util.List;

public class GenericQueue<Type> {
	
	List<Type> elements = new ArrayList<Type>();
	
	public void enqueue(Type element) {
		elements.add(element);
	}
	public Type dequeue() {
		return elements.remove(0);
	}
	public Type peek() {
		return elements.get(0);
	}
	public boolean isEmpty() {
		return elements.size() == 0;
	}
	public int size() {
		return elements.size();
	}
}
