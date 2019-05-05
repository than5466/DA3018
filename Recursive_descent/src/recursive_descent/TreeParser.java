package recursive_descent;

import java.util.ArrayList;

public class TreeParser {

	public TreeParser() {

	}

	public Tree parse(ArrayList<String> s) {

		int maxHeight = 0;
		int currentHeight = 0;
		int leaves = 0;
		boolean rootError = true;
		Tree tree = new Tree();

		for (int i = s.size() - 1; i >= 0; i--) {
			switch (s.get(i)) {
			case ")":
				currentHeight++;
				maxHeight = Math.max(maxHeight, currentHeight);
				break;

			case "(":
				currentHeight--;
				if (currentHeight == 0) {
					return null;
				}
				break;

			case ";":
				if (i == s.size() - 1) {
					rootError = false;
					currentHeight++;
					maxHeight++;
				} else {
					rootError = true;
				}

				if (rootError) {
					return null;
				}
				
				break;
			case ":":
				break;
			case ",":
				break;
			default:
				leaves++;
				break;

			}
		}
		
		if (currentHeight != 1) {
			return null;
		}
		
		tree.setHeight(maxHeight);
		tree.setLeaves(leaves);
		return tree;
	}

}
