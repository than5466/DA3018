package recursive_descent;

import java.util.ArrayList;

public class Tree {
	treeNode root;
	int height;
	int leaves;
	
	public Tree() {
		this.root = new treeNode();
	}
	
	public int height() {
		return height(this.root);
	}
	
	public int height(treeNode node) {
		if (node.n_children() == 0) {
			return 1;
		}
		return 1 + Math.max(height(node.get_child(0)), height(node.get_child(1)));
	}
	
	public int n_leaves() {
		return n_leaves(this.root);
	}
	
	public int n_leaves(treeNode node) {
		if (node.n_children() == 0) {
			return 1;
		}
		else {
			return n_leaves(node.get_child(0))+n_leaves(node.get_child(1));
		}
	}
	
	
	
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
		public void insertChild() {
			
			this.children.add(new treeNode());
		}
		public void setNode(String s) {
			this.value = s;
		}
	}
	
}