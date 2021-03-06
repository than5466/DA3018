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
		
		try {

		if (s.get(s.size() - 1).equals(";")) {  // Makes sure that there is a root before starting.

			s.remove(s.size() - 1);     // After checking for the root, remove it.
			if (s.size() == 0) {        // The empty tree is not allowed.
				return null;
			}
			s = parse_expr(this.tree.root, s);  // Run the parsing method. tree is a treestructure after this.
			if (this.tree.errorFlag) {     // Error occured.
				return null;
			}
			if (s.size() != 0) {     // The grammar expects the expression to be over, but more information follows.
									 // i.e all the parenthesis are closed, yet the Lexer contains more information.
				return null;
			}
			return this.tree;

		}
		return null;   // There was no root, or the root was misplaced.
		} catch (IndexOutOfBoundsException e){   // The ArrayList contains no elements.
			return null;
		}
	}
	
	/**
	 * The expected grammar of expressions is on the form
	 * 
	 * expr = LEAF
	 * 		   | '(' expr ',' expr ')'
	 * 
	 * This method makes sure that all of the expressions follow the norm above, using recursion.
	 * 
	 * @param node
	 * @param s
	 * @return
	 */
	
	public ArrayList<String> parse_expr(Tree.treeNode node, ArrayList<String> s) {
		if (s.get(0).equals("(")) {   // According to the grammar, left parenthesis is one of the allowed start of expressions.
			s.remove(0);              // Remove the parenthesis.
			node.addChild();       // Create a new child to the current node.  
			s = parse_expr(node.get_child(0), s);  // According to grammar, left parenthesis is followed by another expr.
			s = expectedChar(s,",");  // after one expr inside a parenthesis, a comma follows.
			if (this.tree.errorFlag) {
				return null;
			}
			
			node.addChild();    // Create another child to the current node, since the tree is binary.
			
			s = expectedChar(s,")"); // Lastly an opening parenthesis must end with a closing parenthesis.
			if (this.tree.errorFlag) {
				return null;
			}
			return s;             // returns an empty ArrayList, not used for anything.

		} else if (!s.get(0).equals(",") && !s.get(0).equals(":") && !s.get(0).equals(")") && !s.get(0).equals(";")) {
			// None of these signs are allowed to begin expr according to grammar, in this case we're on a node.
			node.setValue(s.get(0)); //set the value of the leaf
			s.remove(0); // Remove the node.
			return s;
		} else {
			this.tree.setError(); // The sequence began with a not allowed symbol. Error.
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
			if (strArray.get(0).equals(ch)) {    // The string matches the expected.
				strArray.remove(0);
				return strArray;           // return the ArrayList without the first element.
			} else {
				this.tree.setError();     // The string didn't match the expected. Error.
				return null;
			}
		} catch (IndexOutOfBoundsException e) {
			this.tree.setError();         // Index error.
			return null;
		}
	}

}