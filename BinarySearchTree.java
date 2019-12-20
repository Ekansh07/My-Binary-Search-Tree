
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinarySearchTree<T extends Comparable<? super T>> {

	public int nodeCount() {
		return nodeCount(root);
	}

	private int nodeCount(BinaryTreeNode<T> firstBST) {
		if (firstBST == null)
			return 0;
		return 1 + nodeCount(firstBST.leftChild) + nodeCount(firstBST.rightChild);
	}
	
	public boolean isFull() {
		return isFull(root);
	}

	private boolean isFull(BinaryTreeNode<T> firstBST) {
		if (firstBST == null)
			return false; 

		if (firstBST.leftChild == null && firstBST.rightChild == null)
			return true;
		else if (firstBST.leftChild != null && firstBST.rightChild != null) {
			return (isFull(firstBST.leftChild) && isFull(firstBST.rightChild));
		} else
			return false;
	}

	public boolean compareStructure(BinaryTreeNode<T> SecondBSTRoot) {
		if (root == null || SecondBSTRoot == null)
			return false;
		return compareStructure(root, SecondBSTRoot);
	}

	private boolean compareStructure(BinaryTreeNode<T> firstBST, BinaryTreeNode<T> secondBST) {
		if (firstBST == null && secondBST == null)
			return true;
		else if (firstBST != null && secondBST != null) {
			return (compareStructure(firstBST.leftChild, secondBST.leftChild) && compareStructure(firstBST.rightChild, secondBST.rightChild));
		} else
			return false;
	}

	public boolean equals(BinaryTreeNode<T> secondTreeRoot) {
		return equals(root, secondTreeRoot);
	}

	private boolean equals(BinaryTreeNode<T> firstBST, BinaryTreeNode<T> secondBST) {
		if (firstBST == null && secondBST == null)
			return true;
		else if (firstBST != null && secondBST != null) {
			if (firstBST.data.equals(secondBST.data))
				return (equals(firstBST.leftChild, secondBST.leftChild) && equals(firstBST.rightChild, secondBST.rightChild));
			else
				return false;
		} else
			return false;
	}

	public BinarySearchTree<T> copy() {
		BinarySearchTree<T> newBST = new BinarySearchTree<>();
		newBST.root = copy(root);
		return newBST;
	}

	private BinaryTreeNode<T> copy(BinaryTreeNode<T> firstBST) {
		if (firstBST != null) {
			BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(firstBST.data, null, null);
			newNode.leftChild = copy(firstBST.leftChild);
			newNode.rightChild = copy(firstBST.rightChild);
			return new BinaryTreeNode<T>(firstBST.data, firstBST.leftChild, firstBST.rightChild);
		}
		return null;
	}

	public BinarySearchTree<T> mirror() {
		BinarySearchTree<T> mirroredBST = new BinarySearchTree<>();
		mirroredBST.root = mirror(root);
		return mirroredBST;
	}

	private BinaryTreeNode<T> mirror(BinaryTreeNode<T> firstBST) {
		if (firstBST != null) {
			BinaryTreeNode<T> mirroredBST = new BinaryTreeNode<T>(firstBST.data, null, null);
			mirroredBST.leftChild = mirror(firstBST.rightChild);
			mirroredBST.rightChild = mirror(firstBST.leftChild);
			return mirroredBST;
		}
		return null;
	}

	public boolean isMirror(BinarySearchTree<T> mirroredBST) {
		return isMirror(root, mirroredBST.root);
	}

	private boolean isMirror(BinaryTreeNode<T> firstBST, BinaryTreeNode<T> mirroredBST) {
		if (firstBST != null && mirroredBST != null) {
			if (firstBST.data.equals(mirroredBST.data))
				return (isMirror(firstBST.leftChild, mirroredBST.rightChild) && isMirror(firstBST.rightChild, mirroredBST.leftChild));
			else
				return false;
		} else if (firstBST == null && mirroredBST == null)
			return true;
		return false;
	}

	public void rotateRight(T node) {
		root = findNode(node, root, true);
	}

	private BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> firstBST) {
		System.out.println("Right Rotation called");
		BinaryTreeNode<T> rightRotatedTree = firstBST.leftChild;
		if (rightRotatedTree != null) {
			firstBST.leftChild = rightRotatedTree.rightChild;
			rightRotatedTree.rightChild = firstBST;
			return rightRotatedTree;
		}
		return firstBST;
	}

	public void rotateLeft(T node) {
		root = findNode(node, root, false);
	}

	private BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> firstBST) {
		BinaryTreeNode<T> leftRotatedTree = firstBST.rightChild;
		if (leftRotatedTree != null) {
			firstBST.rightChild = leftRotatedTree.leftChild;
			leftRotatedTree.leftChild = firstBST;
			return leftRotatedTree;
		}
		return firstBST;
	}
	
	private BinaryTreeNode<T> findNode(T node, BinaryTreeNode<T> firstBST, boolean isRotateRight) {
		if (firstBST == null) {
			System.out.println("\nNode not found in the Binary Search Tree. Hence Tree is not rotated.");
			return null;
		}
		int comp = node.compareTo(firstBST.data);
		if (comp < 0)
			firstBST.leftChild = findNode(node, firstBST.leftChild, isRotateRight);
		else if (comp > 0)
			firstBST.rightChild = findNode(node, firstBST.rightChild, isRotateRight);
		else {
			System.out.println("Null " + node);
			if (isRotateRight == true)
				return rotateRight(firstBST);
			else
				return rotateLeft(firstBST);
		}
		return firstBST;
	}
	
	public BinaryTreeNode<T> root;

	public static class BinaryTreeNode<T> {
		T data;
		BinaryTreeNode<T> leftChild;
		BinaryTreeNode<T> rightChild;

		public BinaryTreeNode(T data, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChild) {
			super();
			this.data = data;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
	}

	public BinarySearchTree() {
		super();
		root = null;
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean contains(T x) {
		return contains(x, root);
	}

	private boolean contains(T x, BinaryTreeNode<T> t) {
		if (t == null)
			return false;
		int comp = x.compareTo(t.data);
		if (comp < 0)
			return contains(x, t.leftChild);
		else if (comp > 0)
			return contains(x, t.rightChild);
		else
			return true;
	}

	public T findMin() {
		if (isEmpty())
			throw new EmptyStackException();
		return findMin(root).data;
	}

	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> t) {
		if (t.leftChild == null)
			return t;
		else
			return findMin(t.leftChild);
	}

	public T findMax() {
		if (isEmpty())
			throw new EmptyStackException();
		return findMax(root).data;
	}

	private BinaryTreeNode<T> findMax(BinaryTreeNode<T> t) {
		while (t.rightChild != null)
			t = t.rightChild;
		return t;
	}

	public void insert(T x) {
		root = insert(x, root);
	}

	public void remove(T x) {
		root = remove(x, root);
	}

	public void printTree() {
		printTreeInOrder(root);
		System.out.println();
	}

	private BinaryTreeNode<T> insert(T x, BinaryTreeNode<T> t) {
		if (t == null)
			return new BinaryTreeNode<T>(x, null, null);

		int comp = x.compareTo(t.data);
		if (comp < 0)
			t.leftChild = insert(x, t.leftChild);
		else if (comp > 0)
			t.rightChild = insert(x, t.rightChild);
		return t;
	}

	private BinaryTreeNode<T> remove(T x, BinaryTreeNode<T> t) {
		if (t == null)
			return t; 
		int comp = x.compareTo(t.data);
		if (comp < 0)
			t.leftChild = remove(x, t.leftChild);
		else if (comp > 0)
			t.rightChild = remove(x, t.rightChild);
		else if (t.leftChild == null || t.rightChild == null) {
			t.data = findMin(t.rightChild).data;
			t.rightChild = remove(t.data, t.rightChild);
		} else {
			t = (t.leftChild != null) ? t.leftChild : t.rightChild;
		}
		return t;
	}

	private void printTreeInOrder(BinaryTreeNode<T> t) {

		if (t != null) {
			printTreeInOrder(t.leftChild);
			System.out.print(t.data + " ");
			printTreeInOrder(t.rightChild);
		}
	}

	

	

	public void printLevelOrderTraversal() {
		printLevelOrderTraversal(root);
	}

	private void printLevelOrderTraversal(BinaryTreeNode<T> root) {
		if (root == null)
			return;
		Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
		queue.add(root);
		BinaryTreeNode<T> node;
		while (!queue.isEmpty()) {
			node = queue.poll();
			if (node.leftChild != null)
				queue.add(node.leftChild);
			if (node.rightChild != null)
				queue.add(node.rightChild);
			System.out.print(node.data + " ");
		}
	}

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		// Change the data for changing the first binary search tree.
		int dataForFirstTree[] = { 20, 30, 40, 25, 10, 15, 5 };
		
		BinarySearchTree<Integer> firstBST = new BinarySearchTree<>();
		for (int i = 0; i < dataForFirstTree.length; i++) {
			firstBST.insert(dataForFirstTree[i]);
		}
		System.out.println("The datas of the First Binary Search Tree using inorder traversal are: ");
		firstBST.printTree();

		System.out.println("\nThe node count of the tree is " + firstBST.nodeCount());

		System.out.println("\nIs the tree full? " + firstBST.isFull());

		// Change the data for changing the second binary search tree.
		int dataForSecondTree[] = { 30, 20, 10, 25, 40, 45, 35, 50 };
		BinarySearchTree<Integer> secondBST = new BinarySearchTree<>();
		for (int i = 0; i < dataForSecondTree.length; i++) {
			secondBST.insert(dataForSecondTree[i]);
		}

		System.out.println("The datas of the Second Binary Search Tree using inorder traversal are: ");
		secondBST.printTree();

		System.out.println("Do both the tree have same structure? " + firstBST.compareStructure(secondBST.root));

		System.out.println("\nAre both the trees equals? " + firstBST.equals(firstBST.root, secondBST.root));

		BinarySearchTree<Integer> copiedBST = firstBST.copy();
		System.out.println("\nThe copy of the first binary search tree is ");
		copiedBST.printTree();

		BinarySearchTree<Integer> mirroredBST = firstBST.mirror();
		System.out.println("\nThe mirror of the first binary search tree is ");
		mirroredBST.printTree();

		System.out.println("\nIs the mirrored binary search tree the mirror of first binary search tree? " + mirroredBST.isMirror(firstBST));
		
		System.out.println("\n Enter a node from first binary search tree on which you want to do right rotation. The nodes in First Binary Search tree are: ");
		firstBST.printTree();
		int rightRotateNode = scanner.nextInt();
		firstBST.rotateRight(rightRotateNode);
		System.out.println(
				"\nThe inorder traversal of the first binary search tree after performing right rotation on node " + rightRotateNode + " is:");
		firstBST.printTree();

		System.out.println("\n Enter a node from first binary search tree on which you want to do left rotation. The nodes in First Binary Search tree are: ");
		firstBST.printTree();
		int leftRotateNode = scanner.nextInt();
		firstBST.rotateLeft(leftRotateNode);
		System.out.println(
				"\nThe inorder traversal of the first binary search tree after performing left rotation on node " + leftRotateNode + " is:");
		firstBST.printTree();

		System.out.println("\nThe level order traversal of the first tree is");
		firstBST.printLevelOrderTraversal();
		

		scanner.close();
	}
}