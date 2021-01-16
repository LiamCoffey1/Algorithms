
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Represents nodes in an unweighted directed graph
 */
class GraphNode {
	int value;
	boolean visited = false;
	List<GraphNode> connections;

	public GraphNode(int value) {
		this.value = value;
		connections = new LinkedList<GraphNode>();
	}

	void pointsTo(GraphNode... g) {
		for (GraphNode node : g) {
			connections.add(node);
		}
	}

}

public class GraphSearch {
	static Queue<GraphNode> queueNodes = new LinkedList<GraphNode>();
	static Stack<GraphNode> stackNodes = new Stack<GraphNode>();

	static void printNodes(String label, Object data) {
		@SuppressWarnings("unchecked")
		List<GraphNode> nodes = (List<GraphNode>) data;
		System.out.println("Current " + label + ":");
		if (nodes.size() > 0) {
			for (GraphNode current : nodes) {
				System.out.print("| " + current.value + " | ");
			}
			System.out.println("\n");
		} else {
			System.out.println("| |\n");
		}
	}

	/*
	 * Recursive Breadth first search using queue Visit a node then visits its
	 * connections and visit the connections connections... until no more nodes
	 * possible
	 * 
	 * idea: 
	 *  1. take first element and add to queue 
	 *  2. repeat following until queue empty 
	 *  3. examine and remove head of queue 
	 *  4. push all nodes reachable from current node to queue and mark as visited
	 * 
	 */
	static String breadthFirst(int iter, int targetValue, LinkedList<GraphNode> pathTaken) {
		// No more unvisited nodes
		if (queueNodes.isEmpty())
			return "Not Found";
		
		// Examine node
		GraphNode current = queueNodes.remove();
		if(current.value == targetValue)
			return "Found in " + iter + " iterations.";
		
		pathTaken.add(current);
		// Add nodes(queue) that current is connected to and mark visited
		for (GraphNode connected : current.connections) {
			if (connected != null && !connected.visited) {
				queueNodes.add(connected);
				connected.visited = true;
			}
		}
		
		//progress printing
		System.out.println("Iteration: " + iter);
		printNodes("Queue", queueNodes);
		printNodes("Visited Nodes", pathTaken);
		
		return breadthFirst(++iter, targetValue, pathTaken);
	}

	/*
	 * Recursive Depth first search using stack Go to the farthest depth and work
	 * backwards
	 * idea: 
	 * 1. take first element and add to stack 
	 * 2. repeat following until stack empty 
	 * 3. pop stack (examine value) 
	 * 4. push all nodes reachable
	 *    from current node to stack and mark as visited
	 * 
	 */
	static String depthFirst(int iter, int targetValue, LinkedList<GraphNode> pathTaken) {
		// No more unvisited nodes
		if (stackNodes.isEmpty())
			return "Not Found";
		
		// Examine node
		GraphNode current = stackNodes.pop();
		if(current.value == targetValue)
			return "Found in " + iter + " iterations.";
		
		pathTaken.add(current);
		// Add nodes(stack) that current is connected to and mark visited
		for (GraphNode connected : current.connections) {
			if (connected != null && !connected.visited) {
				stackNodes.push(connected);
				connected.visited = true;
			}
		}
		
		//progress printing
		System.out.println("Iteration: " + iter);
		printNodes("Stack", stackNodes);
		printNodes("Visited Nodes", pathTaken);
		
		return depthFirst(++iter, targetValue, pathTaken);
	}

	/*
	 * Representation of a Directed unweighted graph
	 */
	static GraphNode generateGraph() {
		GraphNode n40 = new GraphNode(40);
		GraphNode n20 = new GraphNode(20);
		GraphNode n10 = new GraphNode(10);
		GraphNode n30 = new GraphNode(30);
		GraphNode n50 = new GraphNode(50);
		GraphNode n70 = new GraphNode(70);
		GraphNode n60 = new GraphNode(60);

		n40.pointsTo(n10, n20);
		n10.pointsTo(n30);
		n20.pointsTo(n10, n30, n60, n50);
		n30.pointsTo(n60);
		n50.pointsTo(n70);
		n60.pointsTo(n70);
		return n40;
	}

	public static void main(String args[]) {

		LinkedList<GraphNode> pathTaken = new LinkedList<GraphNode>();
		GraphNode root = generateGraph();
		
		// queueNodes.add(root);
		System.out.println("***********************Breadth First********************\n");
		queueNodes.add(root);
		System.out.println(breadthFirst(1, 50, pathTaken));
		System.out.println("***********************%%%%%%%%%%%%%%*******************\n");
		

		System.out.println("************************Depth First*********************\n");
		//reset structures
		pathTaken = new LinkedList<GraphNode>();
		root = generateGraph();
		stackNodes.add(root);
		System.out.println(depthFirst(1, 50, pathTaken));
		System.out.println("***********************%%%%%%%%%%%%%%*******************\n");

	}
}
