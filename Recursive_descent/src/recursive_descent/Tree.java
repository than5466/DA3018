package recursive_descent;

import java.util.ArrayList;

public class Tree {
	int height;
	int leaves;
	treeNode root;
	
	public Tree() {
		this.root = null;
		
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	public void setLeaves(int n) {
		this.leaves = n;
	}
	
	
	
	public int height() {
		return this.height;
	}
	
	public int n_leaves() {
		return this.leaves;
	}
	
	public class treeNode {
		String value;
		ArrayList<treeNode> children = new ArrayList<treeNode>();
		
		
		public treeNode(String s) {
			this.value = s;
		}
		
		public void addChild(String s) {
			this.children.add(new treeNode(s));
		}
		public int n_childs() {
			return this.children.size();
		}
	}
}
