
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

class Tree<K> {
	Tree<K> left, right;
	K currentValue;

	Tree(K value, Tree<K> left, Tree<K> right) {
		this.left = left;
		this.right = right;
		this.currentValue = value;
	}
}






/**
 * Methods for traversing a binary tree
 */
public class TreeTraversal {
	
	class Node {
		Node left, right;
		int value;

		Node(int value, Node left, Node right) {
			this.left = left;
			this.right = right;
			this.value = value;
		}
	}

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
	
	boolean isIdentical(Node root1, Node root2)
	{
	    if(root1 == null && root2 == null)
	        return true;
	    if((root1 == null && root2 != null) || (root2 == null && root1 == null))
	        return false;
	    if(root1.value == root2.value && 
	    (isIdentical(root1.left, root2.left) &&
	    isIdentical(root1.right, root2.right)))
	        return true;
	   return false;
	}
	
	/*
	 *  The idea is to do in order traversal of the binary tree.
	 *  While doing inorder traversal, keep track of the previously visited node in a variable,
	 *  say prev. For every visited node, make it next to the prev and previous of this node as prev.
	 */
	Node prev = null;
    Node head;
    //Function to convert binary tree to doubly linked list and return it.
    Node bToDLL(Node root)
    {
	    if(root == null)
	        return head;
	    bToDLL(root.left);
	    if(prev == null)
	        head = root;
	    else {
	        root.left = prev;
	        prev.right = root;
	    }
	    prev = root;
	    bToDLL(root.right);
	    return head;
    }
	
	int getSum(Node root) {
        if(root == null)
            return 0;
        else return getSum(root.left) + root.value + getSum(root.right);
    }
    boolean isSumTree(Node root) {
        // Is leaf node
        if(root == null || (root.left == null || root.right == null))
            return true;
        int left_sum = getSum(root.left);
        int right_sum = getSum(root.right);
        // If node and all children satisfy propert, true
        if((left_sum + right_sum == root.value) &&
            isSumTree(root.left) && isSumTree(root.right))
            return true;
        return false;
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
	
	/*
    Given a tree and a sum,
    return true if there is a path
    from the root down to a leaf,
    such that adding up all
    the values along the path
    equals the given sum.

    Strategy: subtract the node
    value from the sum when
    recurring down, and check to
    see if the sum is 0 when
    you run out of tree.
    */
	
	boolean hasPathWithGivenSum(Tree<Integer> t, int s) {
	    //If not found or tree is empty
	    if(t == null)
	        return false;
	    int newSum = s - t.currentValue;
	    //If current is leaf and correct sum -> true
	    if(newSum == 0 && t.left == null && t.right == null)
	        return true;
	    return hasPathWithGivenSum(t.left, newSum) ||
	        hasPathWithGivenSum(t.right, newSum); 
	}


	
	boolean isEqualSymetric(TreeNode root1, TreeNode root2)
	{
	    if(root1 == null && root2 == null)
	        return true;
        if(root1 == null || root2 == null)
            return false;
        return (root1.currentValue.equals(root2.currentValue)) &&
            isEqualSymetric(root1.left, root2.right) &&
	        isEqualSymetric(root1.right, root2.left);
	}
	boolean isTreeSymmetric(TreeNode t) {
	    if(t == null)
	        return true;
	    return isEqualSymetric(t.left, t.right);
	}


	static void postOrder(TreeNode node) {
		if (node == null)
			return;
		inOrder(node.left);
		inOrder(node.right);
		System.out.println(node.currentValue);
	}
	
	/*
	 * Same exept process right subtrees first
	 */
	static void postOrderReverse(TreeNode node) {
		if (node == null)
			return;
		inOrder(node.right);
		inOrder(node.left);
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
