package recursive_descent;

import java.util.ArrayList;

public class TreeParser {
	Tree tree;
	String searchString = ",();:[] ";
	boolean errorFlag;

	public TreeParser() {
		this.tree = new Tree();
		this.errorFlag = false;

	}
	
	/**
	 * First makes sure that there is a root, then calls the parsing method defined below.
	 * @param s
	 * @return
	 * returns a tree structure if the parsing went fine, otherwise null.
	 */

	public Tree parse(ArrayList<String> s) {

		if (s.get(s.size() - 1).equals(";")) {

			s.remove(s.size() - 1);
			if (s.size() == 0) {
				return null;
			}
			ArrayList<String> a = parse_sexpr(this.tree.root, s);
			if (this.tree.errorFlag) {
				return null;
			}
			return this.tree;

		}
		return null;

	}
	
	/**
	 * The expected grammar of expressions is on the form
	 * 
	 * sexpr = LEAF | '(' sexpr ',' ')'
	 * 
	 * This method makes sure that all of the expressions follow the norm above, using recursion.
	 * 
	 * @param node
	 * @param s
	 * @return
	 */
	
	public ArrayList<String> parse_sexpr(Tree.treeNode node, ArrayList<String> s) {
		if (s.get(0).equals("(")) {
			s.remove(0);
			node.insertChild();
			s = parse_sexpr(node.get_child(0), s);
			s = expectedChar(s,",");
			
			node.insertChild();
			s = parse_sexpr(node.get_child(1), s);
			
			s = expectedChar(s,")");
			return s;

		} else if (!s.get(0).equals(",") && !s.get(0).equals(":") && !s.get(0).equals(")") && !s.get(0).equals(";")) {

			node.setNode(s.get(0));
			s.remove(0);
			return s;
		} else {
			this.tree.setError();
			return null;

		}
	}
	
	/**
	 * makes sure that the grammar is correct between expressions. 
	 * @param strArray
	 * @param ch
	 * @return
	 * returns the array without the first element if the first element is the one dictated by the grammar,
	 * otherwise return null.
	 */
	
	public ArrayList<String> expectedChar(ArrayList<String> strArray, String ch) {
		try {
			if (strArray.get(0).equals(ch)) {
				strArray.remove(0);
				return strArray;
			} else {
				this.tree.setError();
				return null;
			}
		} catch (Exception e) {
			this.tree.setError();
			return null;
		}
	}

}