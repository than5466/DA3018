package recursive_descent;

import java.util.ArrayList;

public class TreeParser {
	
	public  TreeParser() {
		
	}
	
	public Tree parse(ArrayList<String> s) {
		
		int maxHeight = 0;
		int currentHeight = 0;
		int leaves = 0;
		boolean rootError = true;
		
		for (int i = s.size()-1; i>=0;i --) {
			switch (s.get(i)) {
			case ")":
				currentHeight ++;
				maxHeight = Math.max(maxHeight, currentHeight);
				break;
			
			case "(":
				currentHeight --;
				if (currentHeight == 0) {
					return null;
				}
				break;
			
			
			case ";":
				if (i == s.size()-1) {
					rootError = false;
					currentHeight ++;
					maxHeight ++;
				}
				else {
					rootError = true;
				}

				if (rootError){
					return null;
				}
				break;
			case ":":
				break;
			case ",":
				break;
			default:
				leaves ++;
				break;
				
			}
		}
		if (currentHeight != 1) {
			return null;
		}
		return new Tree(maxHeight,leaves);
	}


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
}
