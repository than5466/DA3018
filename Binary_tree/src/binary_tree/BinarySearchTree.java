package binary_tree;

import java.util.NoSuchElementException;
import java.util.Stack;

import java.util.Iterator;

/**
 * Store course information in a binary search tree
 * 
 */
public class BinarySearchTree implements Iterable<BinarySearchTree.BSTNode> {
	BSTNode root = null;

	public BinarySearchTree() {
	}

	/**
	 * Public interface for inserting data into the datastructure. Utilizes a
	 * private, more technical method.CourseInfo
	 * 
	 * @param courseCode,    eg "DA3018"
	 * @param courseName,    eg "Computer Science"
	 * @param courseCredits, eg 7,5
	 */

	public void insert(String courseCode, String courseName, double courseCredits) {
		BSTNode node = new BSTNode(courseCode, courseName, courseCredits);
		root = insert(root, node);
	}

	/**
	 * Insert 'node' into the tree pointed at by 'root'. If a node with the same
	 * code already exist, overwrite it with the new information.
	 * 
	 * @returns The node that should be the root of this subtree.
	 * @param root
	 * @param node
	 * 
	 */
	public BSTNode insert(BSTNode root, BSTNode node) {
		if (root == null) {
			return node; // The easy case. Let the current node be the root of a new (sub?)tree.
		} else {
			String currentKey = root.getCourseCode();
			BSTNode left = root.getLeftChild();
			BSTNode right = root.getRightChild();
			if (node.getCourseCode().compareTo(currentKey) < 0) { // left string "before" right string
				left = insert(left, node);
			} else if (node.getCourseCode().compareTo(currentKey) > 0) { // left string "after" right string
				right = insert(right, node);
			} else {
				root = node;
			}

			root.setChildren(left, right);
			return root;
		}
	}

	/**
	 * size: Count the number of nodes in the search tree
	 * 
	 * @return calls the size function defined under this one with the parameter
	 *         root.
	 */

	public int size() {

		return size(root);
	}

	/**
	 * 
	 * @param node
	 * @return returns 0 if the current node is null, otherwise returns 1 +
	 *         recursively the remove function on the right and left node node,
	 *         respectively.
	 */

	public int size(BSTNode node) {
		if (node == null) {
			return 0;
		} else {
			BSTNode left = node.getLeftChild();
			BSTNode right = node.getRightChild();
			return 1 + size(left) + size(right);
		}
	}

	/**
	 * find: Find a course given a course code
	 */

	public BSTNode find(String courseCode) {
		return find(courseCode, root);
	}

	/**
	 * A comparison is made between the current node and the one searched for. If
	 * the current node is of higher value than the one searched for, recursively
	 * call this function but with the left child of the current node, otherwise
	 * recursively call the function with the right child.
	 * 
	 * @param courseCode
	 * @param node
	 * @return
	 * 
	 *         If they're the same, return the current node. If it doesn't exist,
	 *         return null.
	 */

	public BSTNode find(String courseCode, BSTNode node) {
		if (node == null) {
			return null;
		}
		String currentCode = node.getCourseCode();
		int compare = currentCode.compareTo(courseCode);

		if (compare == 0) {
			return node;
		} else if (compare > 0) {
			return find(courseCode, node.getLeftChild());
		} else {
			return find(courseCode, node.getRightChild());
		}

	}

	/**
	 * remove: removes the current node searched for.
	 * 
	 * @param courseCode
	 */

	public void remove(String courseCode) {

		root = remove(courseCode, root);

	}

	/**
	 * 
	 * compares the course code of the node that should be deleted with the current.
	 * If the current node has a smaller value, recursively set the current nodes
	 * right child to the remove function applied to the right child, and do nothing
	 * with the left, and respectively call the remove on the left child if it has a
	 * higher value.
	 * 
	 * @param courseCode
	 * @param node
	 * @return return null if the node doesn't exist in the tree. Otherwise, if the
	 *         searched for node doesn't have a right child, return the left and if
	 *         no left child, return right. If however the node has a right, as well
	 *         as a left child, the left node is inserted into the right and then
	 *         returned.
	 */

	public BSTNode remove(String courseCode, BSTNode node) {

		if (node == null) {
			return null;
		} else {

			BSTNode left = node.getLeftChild();
			BSTNode right = node.getRightChild();

			int compare = node.getCourseCode().compareTo(courseCode);
			if (compare == 0) {
				if (right == null) {

					return left;
				} else if (left == null) {

					return right;

				} else {
					return insert(right, left);

				}

			} else if (compare > 0) {
				left = remove(courseCode, left);
				node.setChildren(left, right);
				return node;
			} else {
				right = remove(courseCode, right);
				node.setChildren(left, right);
				return node;
			}

		}
	}

	/**
	 * Nodes in the search tree This class should be sufficient for the project.
	 * 
	 */
	public class BSTNode {
		private String courseCode;
		private String courseName;
		private double credits;
		private BSTNode left = null;
		private BSTNode right = null;

		/**
		 * Constructor
		 * 
		 */
		public BSTNode(String code, String name, double credits) {
			this.courseCode = code;
			this.courseName = name;
			this.credits = credits;
		}

		/**
		 * Specify the children of the current node
		 * 
		 * @param left
		 * @param right
		 */
		public void setChildren(BSTNode left, BSTNode right) {
			this.left = left;
			this.right = right;
		}

		public String getCourseCode() {
			return courseCode;
		}

		public String getCourseName() {
			return courseName;
		}

		public double getCredits() {
			return credits;
		}

		public BSTNode getLeftChild() {
			return left;
		}

		public BSTNode getRightChild() {
			return right;
		}

	}

	/**
	 * Creating a new instance of the iterator class defined below
	 * 
	 * 
	 */

	public Iterator<BSTNode> iterator() {
		return new BSTIterator(root);
	}

	/**
	 * Iterator class that iterates through a BST in order.
	 * 
	 */

	private class BSTIterator implements Iterator<BSTNode> {
		private BSTNode current;
		private BSTNode temp;
		// private BSTNode root;
		// private String previousCode;
		// private String finalCode;
		// private boolean go;
		//private String savedCode;
		// private int compare;
		private Stack<BSTNode> stack;

		/**
		 * 
		 * @param node - an object of the BSTNode class.
		 */

		public BSTIterator(BSTNode node) {
			this.current = node;
			// this.root = node;
			// this.previousCode = null;
			// this.finalCode = FinalCode(this.current);
			//this.savedCode = null;
			this.stack = new Stack<BSTNode>();

		}

		/**
		 * 
		 * @param node - an object of the BSTNode class
		 * @return the course code from the node which has the highest valued course
		 *         code.
		 */

		/*
		 * 
		 * 
		 * public String FinalCode(BSTNode node) { while (node.getRightChild() != null)
		 * { node = node.getRightChild(); } return node.getCourseCode(); }
		 * 
		 */

		/**
		 * returns false if the course code of the node which next() returned previously
		 * is the same as the highest valued one, true otherwise.
		 */

		/*
		 * 
		 * public boolean hasNext() { return this.previousCode != this.finalCode; }
		 * 
		 */

		/**
		 * returns the course code which the method nextInOrder decides. also updates
		 * the previousCode parameter to make sure that the iterator doesn't get stuck
		 * in an endless loop.
		 * 
		 * Also sets the current node to the original root, before calling the
		 * nextInOrder method.
		 */

		/*
		 * 
		 * public BSTNode next() { if (this.hasNext()) {
		 * 
		 * this.current = this.root; this.current = nextInOrder();
		 * 
		 * this.previousCode = this.current.getCourseCode(); return this.current;
		 * 
		 * } else { throw new NoSuchElementException();
		 * 
		 * }
		 * 
		 * }
		 * 
		 */

		/**
		 * Decides which node is the next, with the help of two functions goLeft and
		 * goRight. If the current node has a higher value than the one returned in the
		 * last iteration, the goLeft function is called until the current node isn't
		 * bigger than the previous. Then the right function is called. This is done
		 * until the next node for certainty isn't any deeper in the tree. The while
		 * loop is cancelled when the current child doesn't have a right child, but the
		 * current node has a smaller or equal value than the last one, or vice versa
		 * for the left child.
		 * 
		 * @return When the next course code for certain isn't any deeper in the tree,
		 *         the find function is used to locate the node which value is greater,
		 *         but with the smallest margin, than the previous code, and then
		 *         returns it.
		 * 
		 */

		/*
		 * 
		 * public BSTNode nextInOrder() { if (this.previousCode == null) { while
		 * (this.current.getLeftChild() != null) { this.current =
		 * this.current.getLeftChild(); } return this.current;
		 * 
		 * } this.go = true; this.compare =
		 * this.current.getCourseCode().compareTo(this.previousCode); this.savedCode =
		 * this.current.getCourseCode(); while (this.go) { if (this.compare > 0) {
		 * this.current = goLeft(); this.compare =
		 * this.current.getCourseCode().compareTo(this.previousCode); if (this.compare >
		 * 0) { this.go = false; } } else { this.current = goRight(); this.compare =
		 * this.current.getCourseCode().compareTo(this.previousCode); if (this.compare
		 * <= 0) { this.go = false; }
		 * 
		 * } }
		 * 
		 * return find(savedCode, this.root); }
		 * 
		 */

		/**
		 * While the current node is of a higher value than the previous, set current to
		 * the left child. if a node with a higher value is found, set the savedCode to
		 * the course name of the code.
		 * 
		 * @return returns current node whenever the node doesn't have a left child or
		 *         the current node has a lower or equal value than the one found in the
		 *         previous iteration.
		 */

		/*
		 * 
		 * public BSTNode goLeft() { while (this.compare > 0 &&
		 * this.current.getLeftChild() != null) { this.current =
		 * this.current.getLeftChild(); this.compare =
		 * this.current.getCourseCode().compareTo(this.previousCode); if (this.compare >
		 * 0) { this.savedCode = this.current.getCourseCode(); }
		 * 
		 * } return this.current;
		 * 
		 * }
		 */

		/**
		 * While the current node is of a lower or equal value than the one from the
		 * previous iteration, set current to the right child. if a node with a higher
		 * value is found, set the savedCode to the course name of the code.
		 * 
		 * @return returns current node whenever th node doesn't have a right child or
		 *         the current node has a higher value than the one found in the
		 *         previous iteration.
		 */

		/*
		 * 
		 * public BSTNode goRight() { while (this.compare <= 0 &&
		 * this.current.getRightChild() != null) { this.current =
		 * this.current.getRightChild(); this.compare =
		 * this.current.getCourseCode().compareTo(this.previousCode); if (this.compare >
		 * 0) { this.savedCode = this.current.getCourseCode(); } } return this.current;
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 */

		/**
		 * hasNext() returns true unless the stack is empty, as well as the current node
		 * is null.
		 */

		public boolean hasNext() {
			if (!stack.isEmpty() || this.current != null) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * Pre order traversal of the binary tree with the help of a stack. Each
		 * iteration the current nodes children (if it has any) gets pushed to the
		 * stack, with the right child being pushed first. The current node is then
		 * returned, and the next iteration current node is set to the top object on the
		 * stack.
		 * 
		 */

		/*
		 * public BSTNode next() { if (hasNext()) { if (this.current == null) {
		 * this.current = stack.pop(); } if (this.current.getRightChild() != null) {
		 * this.stack.push(this.current.getRightChild()); } if
		 * (this.current.getLeftChild() != null) {
		 * this.stack.push(this.current.getLeftChild()); }
		 * 
		 * temp = this.current;
		 * 
		 * this.current = null; return temp;
		 * 
		 * } else { throw new NoSuchElementException(); } } } }
		 */
		
		/*
		 * In-order traversal. 
		 * 
		 */

		public BSTNode next() {
			if (hasNext()) {
				if (this.current == null) {
					this.current = this.stack.pop();
					this.temp = this.current;
					this.current = this.temp.getRightChild();
					if (this.current != null) {

						this.stack.push(this.current);
						this.current = this.current.getLeftChild();

					}
					return this.temp;
				} else {
					while (this.current.getLeftChild() != null) {
						this.stack.push(this.current);
						this.current = this.current.getLeftChild();

					}
					this.temp = this.current;
					this.current = this.current.getRightChild();
					return this.temp;
				}

			} else {
				throw new NoSuchElementException();
			}
		}
	}
}
