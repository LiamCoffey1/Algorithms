import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Edge {
	int cost;
	WGraphNode desitination;
	public Edge(int cost, WGraphNode destination) {
		this.cost = cost;
		this.desitination = destination;
	}
}
class WGraphNode {
	int value;
	boolean visited = false;
	int cost = 0;
	List<Edge> edges;

	public WGraphNode(int value) {
		this.value = value;
		edges = new LinkedList<Edge>();
	}

	void addEdge(Edge... e) {
		for (Edge edge : e) {
			edges.add(edge);
		}
	}

}



public class WeightedGraph {
	static Queue<WGraphNode> queueNodes = new LinkedList<WGraphNode>();
	static String breadthFirst(int iter, int cost, int targetValue, LinkedList<WGraphNode> pathTaken) {
		// No more unvisited nodes
		if (queueNodes.isEmpty())
			return "Not Found";
		
		// Examine node
		WGraphNode current = queueNodes.remove();
		if(current.value == targetValue)
			return "Found in " + iter + " iterations. Cost: " + cost;
		int newCost = cost;
		pathTaken.add(current);
		// Add nodes(queue) that current is connected to and mark visited
		for (Edge connected : current.edges) {
			WGraphNode node = connected.desitination;
			if (node != null && !node.visited) {
				queueNodes.add(node);
				node.visited = true;
				cost += connected.cost;
			}
		}
		
		//progress printing
		System.out.println("Iteration: " + iter);
		
		return breadthFirst(++iter, cost, targetValue, pathTaken);
	}
	public static void main(String args[]) {
		
		LinkedList<WGraphNode> pathTaken = new LinkedList<WGraphNode>();
				
		WGraphNode n40 = new WGraphNode(40);
		WGraphNode n20 = new WGraphNode(20);
		WGraphNode n10 = new WGraphNode(10);
		WGraphNode n30 = new WGraphNode(30);
		WGraphNode n50 = new WGraphNode(50);
		WGraphNode n70 = new WGraphNode(70);
		WGraphNode n60 = new WGraphNode(60);

		n40.addEdge(new Edge(2, n10), new Edge(2, n20));
		n10.addEdge(new Edge(2, n30));
		n20.addEdge(new Edge(2, n10), new Edge(2, n30), new Edge(2, n60), new Edge(2, n50));
		n30.addEdge(new Edge(2, n60));
		n50.addEdge(new Edge(2, n70));
		n60.addEdge(new Edge(2, n70));
		
		System.out.println("***********************Breadth First********************\n");
		queueNodes.add(n40);
		System.out.println(breadthFirst(1, 0, 50, pathTaken));
		System.out.println("***********************%%%%%%%%%%%%%%*******************\n");
		
		
		
	}
}
