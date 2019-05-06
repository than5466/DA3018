package recursive_descent;

import java.util.ArrayList;

public class Tree {
	treeNode root;
	int height;
	int leaves;
	boolean errorFlag;
	
	/**
	 * Class for a tree.
	 */
	
	public Tree() {
		this.root = new treeNode();
		this.errorFlag = false;
	}
	
	public int height() {
		return height(this.root);
	}
	/**
	 * recursively counts the max height of the tree, using the max function.
	 * @param node
	 * @return
	 */
	
	public int height(treeNode node) {
		if (node.n_children() == 0) {
			return 1;
		}
		return 1 + Math.max(height(node.get_child(0)), height(node.get_child(1)));
	}
	
	public int n_leaves() {
		return n_leaves(this.root);
	}
	/**
	 * Counts the number of leaves in the tree.
	 * @param node
	 * @return
	 */
	
	public int n_leaves(treeNode node) {
		if (node.n_children() == 0) {
			return 1;
		}
		else {
			return n_leaves(node.get_child(0))+n_leaves(node.get_child(1));
		}
	}
	
	public void setError() {
		this.errorFlag = true;
	}
	
	/**
	 * Class for a tree node. uses ArrayList for children.
	 * 
	 *
	 */
	public class treeNode {
		String value;
		ArrayList<treeNode> children;
		
		public treeNode() {
			this.value = null;
			this.children = new ArrayList<treeNode>();
		}
		
		public int n_children() {
			return this.children.size();
		}
		
		public treeNode get_child(int i) {
			return children.get(i);
		}
		
		/**
		 * create a new child to the current node.
		 */
		public void insertChild() {
			
			this.children.add(new treeNode());
		}
		/**
		 * set the value of the current node. Only used on leafs.
		 * @param s
		 */
		public void setNode(String s) {
			this.value = s;
		}
	}
	
}