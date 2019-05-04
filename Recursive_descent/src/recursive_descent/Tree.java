package recursive_descent;

public class Tree {
	int height;
	int leaves;
	
	public Tree(int h, int n) {
		this.height = h;
		this.leaves = n;
		
	}
	
	public int height() {
		return this.height;
	}
	
	public int n_leaves() {
		return this.leaves;
	}
}
