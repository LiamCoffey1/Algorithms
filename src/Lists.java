import java.util.Stack;

class DELinkedList {
	Node start;
	Node end;
}

class LinkedList {
	Node head = null;
	
	public void insertEnd(int value) {
		if(head == null) {
			head = new Node(value);
			return;
		}
		Node last = head;
		while(last.next != null)
			last = last.next;
		last.next = new Node(value);
	}
	
	public LinkedList reverse() {
		Stack<Node> stack = new Stack<Node>();
		LinkedList newList = new LinkedList();
		newList.head = this.head;
		Node last = newList.head;
		while(last.next != null) {
			stack.push(last);
			last = last.next;
		}
		newList.head = last;
		while(!stack.isEmpty()) {
			last.next = stack.pop();
			last = last.next;
		}
		last.next = null;
		return newList;
	}
	
	public int size() {
		Node copy = head;
		int i = 0;
		while( copy != null) {
			copy = copy.next;
			i++;
		}
		return i;
	}
	
	public void insertStart(int value) {
		if(head == null) {
			head = new Node(value);
			return;
		}
		Node newNode = new Node(value);
		newNode.next = head;
		head = newNode;
	}
	
	public void remove(int key) {
		if(head.value == key) {
			head = head.next;
			return;
		}
		Node copy = head;
		while(copy.next != null) {
			if(copy.next.value == key && copy.next.next != null) {
				copy.next = copy.next.next;
				return;
			}
			copy = copy.next;
		}
		head = copy;
	}
	
	public void printList() {
		Node copy = head;
		while(copy != null) {
			System.out.print(" -> " + copy.value);
			copy = copy.next;
		}
		System.out.println();
	}
	
	public int getValueByIndex(int index) {
		Node copy = head;
		int i = index;
		while( i > 0) {
			copy = copy.next;
			i--;
		}
		return copy.value;
	}
	public Node getNodeByValue(int value) {
		Node copy = head;
		while(copy != null) {
			if(copy.value == value)
				return copy;
			copy = copy.next;
		}
		return null;
	}
	
}

class Node {
	int value;
	Node next;
	public Node(int value) {
		this.value = value;
		this.next = null;
	}
}
class DNode extends Node {
	
	public DNode(int value) {
		super(value);
	}

	Node previous;
}
public class Lists {
	public static void main(String args[]) {
		LinkedList list = new LinkedList();
		list.insertEnd(1);
		list.insertEnd(2);
		list.insertEnd(3);
		list.insertStart(6);
		list.remove(6);
		list.printList();
		LinkedList reversed = list.reverse();
		reversed.printList();
		list.printList();
		System.out.println(list.size());
	}
}