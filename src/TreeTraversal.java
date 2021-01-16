
/**
 * Tree Node with string value
 */
class TreeNode {
	TreeNode left, right;
	String currentValue;

	TreeNode(String value, TreeNode left, TreeNode right) {
		this.left = left;
		this.right = right;
		this.currentValue = value;
	}
}


/**
 * Methods for traversing a binary tree
 */
public class TreeTraversal {

	static void levelOrder(TreeNode node, int height) {
		for (int i = 1; i < height; i++) {
			System.out.println("Increase level to: " + i);
			printLevel(node, i);
		}
	}

	static void printLevel(TreeNode  node, int level) {
		if (node == null)
			return;
		if (level == 1) {
			System.out.println(node.currentValue);
		} else {
			printLevel(node.left, level - 1);
			printLevel(node.right, level - 1);
		}
	}

	static void inOrder(TreeNode  node) {
		if (node == null)
			return;
		inOrder(node.left);
		System.out.println(node.currentValue);
		inOrder(node.right);
	}
	
	static void inorderTwoTrees(TreeNode  node1, TreeNode  node2) {
		//Both trees exhausted
		if (node1 == null && node2 == null)
			return;
		//First tree exhausted
		if(node1 == null) {
			inorderTwoTrees(null, node2.left);
			System.out.println(node2.currentValue);
			inorderTwoTrees(null, node2.right);
		} else if(node2 == null) { // second tree exhausted
			inorderTwoTrees(node1.left, null);
			System.out.println(node1.currentValue);
			inorderTwoTrees(node1.right, null);
		} else { // neither trees exhausted
			inorderTwoTrees(node1.left, node2.left);
			System.out.println(node1.currentValue + " " + node2.currentValue);
			inorderTwoTrees(node1.right, node2.right);
		}
		
	}

	static void postOrder(TreeNode node) {
		if (node == null)
			return;
		inOrder(node.left);
		inOrder(node.right);
		System.out.println(node.currentValue);
	}

	static void preOrder(TreeNode  node) {
		if (node == null)
			return;
		System.out.println(node.currentValue);
		inOrder(node.left);
		inOrder(node.right);
	}
	public static void main(String args[]) {
		System.out.println("**********************");
		System.out.println("Tree Stuff");
		TreeNode nodes = new TreeNode("A",
				new TreeNode("B",
						new TreeNode("D", null, null),
						null
				),
				new TreeNode("C",
						new TreeNode("E", null, null),
						new TreeNode("F", null, null)
				)
		);
		System.out.println("**********************");
		System.out.println("Inorder");
		inOrder(nodes);
		System.out.println("**********************");
		System.out.println("postOrder");
		postOrder(nodes);
		System.out.println("**********************");
		System.out.println("preOrder");
		preOrder(nodes);
		System.out.println("**********************");
		System.out.println("levelOrder");
		levelOrder(nodes, 4);
		System.out.println("**********************");
		System.out.println("inorderTwoTrees");
		TreeNode secondNodes = new TreeNode("A",
				new TreeNode("B",
						new TreeNode("D", null, null),
						null
				),
				new TreeNode("C",
						new TreeNode("E", null, null),
						null
				)
		);
		inorderTwoTrees(nodes, secondNodes);
		
	}
}
