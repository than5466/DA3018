package recursive_descent;

import java.util.ArrayList;

public class TreeParser {
	Tree tree;
	String searchString = ",();:[] ";

	public TreeParser() {
		this.tree = new Tree();

	}

	public Tree parse(ArrayList<String> s) {

		if (s.get(s.size() - 1).equals(";")) {

			s.remove(s.get(s.size() - 1));
			parse_sexpr(this.tree.root, s);
			return this.tree;

		}
		return null;

	}
	
	public ArrayList<String> parse_sexpr(Tree.treeNode node, ArrayList<String> s) {
		if (s.get(0).equals("(")) {
			s.remove(0);
			node.insertChild();
			s = parse_sexpr(node.get_child(0), s);
			if (s.get(0).equals(",")) {
				s.remove(0);
			} else {
				return null;
			}

			node.insertChild();
			s = parse_sexpr(node.get_child(1), s);
			if (s.get(0).equals(")")) {
				s.remove(0);
			} else {
				return null;
			}
			return s;

		} else if (!s.get(0).equals(",") && !s.get(0).equals(":") && !s.get(0).equals(")") && !s.get(0).equals(";")) {

			s.remove(0);
			return s;
		} else {
			return null;

		}
	}

}