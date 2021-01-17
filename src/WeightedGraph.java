import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


class DEdge {
	int cost;
	DGraphNode desitination;
	public DEdge(DGraphNode source, int cost, DGraphNode destination) {
		this.cost = cost;
		this.desitination = destination;
		this.desitination.addEdge(new DEdge(cost, source));
	}
	public DEdge(int cost, DGraphNode destination) {
		this.cost = cost;
		this.desitination = destination;
	}
}
class DGraphNode {
	int value;
	boolean visited = false;
	int cost = Integer.MAX_VALUE;
	List<DEdge> edges;

	public DGraphNode(int value) {
		this.value = value;
		edges = new LinkedList<DEdge>();
	}

	void addEdge(DEdge... e) {
		for (DEdge edge : e) {
			edges.add(edge);
		}
	}

}

public class WeightedGraph {


	static LinkedList<DGraphNode> freeNodes = new LinkedList<DGraphNode>();
	static LinkedList<DEdge> minimalEdges = new LinkedList<DEdge>();
	static DGraphNode generateDirectedGraph() {
		DGraphNode n40 = new DGraphNode(40);
		DGraphNode n20 = new DGraphNode(20);
		DGraphNode n10 = new DGraphNode(10);
		DGraphNode n30 = new DGraphNode(30);
		DGraphNode n50 = new DGraphNode(50);
		DGraphNode n70 = new DGraphNode(70);
		DGraphNode n60 = new DGraphNode(60);

		n40.addEdge(new DEdge(n40, 2, n10), new DEdge(n40, 5, n20));
		n10.addEdge(new DEdge(n10, 7, n30));
		n10.addEdge(new DEdge(n10, 7, n50));
		n20.addEdge(new DEdge(n20, 3, n10), new DEdge(n20, 9, n30), new DEdge(n20, 2, n60), new DEdge(n20, 5, n50));
		n30.addEdge(new DEdge(n30, 7, n60));
		n50.addEdge(new DEdge(n50, 8, n70));
		n60.addEdge(new DEdge(n60, 2, n70));
		n40.visited = true;
		freeNodes.add(n40);
		return n40;
	}

	static void prims(int n) {
		while (freeNodes.size() != n) {
			int minCost = Integer.MAX_VALUE;
			DEdge minEdge = null;
			for (DGraphNode node : freeNodes) {
				for (DEdge edge : node.edges) {
					if (!edge.desitination.visited && edge.cost < minCost) {
						minEdge = edge;
						minCost = edge.cost;
					}
				}
			}
			if (minEdge != null) {
				minEdge.desitination.visited = true;
				minimalEdges.add(minEdge);
				freeNodes.add(minEdge.desitination);
			}
		}
	}
	
	public static void main(String args[]) {
		
		System.out.println("\n***********************Shortest path graph*******************\n");
		generateDirectedGraph();
		prims(7);
		System.out.print("| Start, Cost: 0 |");
		for(DEdge minimal : minimalEdges) {
			System.out.print(" -> | Value: " + minimal.desitination.value + ", Cost : " + minimal.cost + " |");
		}
		System.out.println("\n\n***********************%%%%%%%%%%%%%%*******************\n");
		
	}
}
