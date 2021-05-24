
public class TestCode {
	
	public static void main(String args[]) {
		GenericStack<Integer> stack = new GenericStack<Integer>(5);
		 stack.push(1);      // inserting 1 in the stack
	        stack.push(2);      // inserting 2 in the stack
	 
	        stack.pop();        // removing the top element (2)
	        stack.pop();        // removing the top element (1)
	 
	        stack.push(3);      // inserting 3 in the stack
	 
	        System.out.println("The top element is " + stack.peek());
	        System.out.println("The stack size is " + stack.size());
	 
	        stack.pop();        // removing the top element (3)
	 
	        // check if the stack is empty
	        if (stack.isEmpty()) {
	            System.out.println("The stack is empty");
	        }
	        else {
	            System.out.println("The stack is not empty");
	        }
	        
	        
	        ArrayStack astack = new ArrayStack(5);
	        astack.push(1);      // inserting 1 in the stack
	        astack.push(2);      // inserting 2 in the stack
		 
	        astack.pop();        // removing the top element (2)
	        astack.pop();        // removing the top element (1)
		 
	        astack.push(3);      // inserting 3 in the stack
		 
		        System.out.println("The top element is " + stack.peek());
		        System.out.println("The stack size is " + stack.size());
		 
		        astack.pop();        // removing the top element (3)
		 
		        // check if the stack is empty
		        if (astack.isEmpty()) {
		            System.out.println("The stack is empty");
		        }
		        else {
		            System.out.println("The stack is not empty");
		        }
		        
		        GenericQueue<Integer> q = new GenericQueue<Integer>();
		        q.enqueue(1);
		        q.enqueue(2);
		        q.enqueue(3);
		 
		        System.out.println("The front element is " + q.peek());
		        q.dequeue();
		        System.out.println("The front element is " + q.peek());
		 
		        System.out.println("The queue size is " + q.size());
		 
		        q.dequeue();
		        q.dequeue();
	}
}
