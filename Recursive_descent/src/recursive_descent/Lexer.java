package recursive_descent;

import java.util.ArrayList;

public class Lexer {
	String inputString;
	String searchString = ",();:[] ";

	/**
	 * 
	 * @param s
	 */

	public Lexer(String s) {
		this.inputString = s;
	}

	/**
	 * calls the token method with the string from Lexer.
	 * @return
	 */

	public ArrayList<String> tokens() {
		return tokens(this.inputString);

	}

	/**
	 * This method has time complexity O(n). All the methods used each iteration
	 * in the while loop, has a constant time complexity, except for nextIndex function. And the while loop
	 * is run at most n times. The nextIndex function has a time complexity of O(n), however, when used
	 * in this method, the variable i will increase linearly with n, and so the time complexity is
	 * still O(n).
	 * @param s
	 * @return
	 * An ArrayList, with each element being a leaf (as a string), or one of the special characters.
	 */

	private ArrayList<String> tokens(String s) {
		ArrayList<String> lexedList = new ArrayList<String>();
		boolean commentStatus = false;   // When this is set to true, nothing is saved until it is set to false again.
		int j;
		while (s.length() > 0) {
			j = 1;
			if (commentStatus) {
				if (s.charAt(0) == ']') {       // End comment on right hard parentesis.
					commentStatus = false;
				}
			} else if (s.charAt(0) == '[') {     // Start of a comment.
				commentStatus = true;
			} else if (containsChar(this.searchString, s.charAt(0))) {   // If the character is in the searchString above.
				if (s.charAt(0) == ' ') {    //Throw away if space, otherwise add to the ArrayList.

				} else {
					lexedList.add(s.substring(0, 1));
				}

			} else {
				j = nextIndex(s);      // set j to be the index of the first special character in the string.

				lexedList.add(s.substring(0, j));  // add the j characters long string.
				
				

			}
			s = s.substring(j);
		}

		return lexedList;   //return the lexed list.
	}

	/**
	 * Has time complexity O(n), depending on the length of the string s.
	 * When used in the tokens method, this method is called with a string of a constant
	 * length, so when used in the method above, this method has O(1) time complexity.
	 * @param s
	 * @param search
	 * @return Recursively compares every index of the string s with the char c.
	 *         Returns true if one of these comparisons returns true, otherwise
	 *         false.
	 */

	public boolean containsChar(String s, char c) {
		if (s.length() == 0)
			return false;
		else
			return s.charAt(0) == c || containsChar(s.substring(1), c);  // logical or. If one comparison is true, return true.
	}

	/**
	 * Time complexity O(n).
	 * @param s
	 * @return given a string s, returns the first index in the string containing
	 *         one of the symbols in searchString. Returns the length of the string
	 *         if none exists.
	 */

	public int nextIndex(String s) {
		for (int i = 1; i < s.length(); i++) {
			if (containsChar(this.searchString, s.charAt(i))) {
				return i;
			}
		}

		return s.length();

	}
}


/**
 * public ArrayList<String> tokens(String s) { ArrayList<String> lexedList = new
 * ArrayList<String>(); boolean commentStatus = false; for (int i = 0;
 * s.length() > i; i++) { if (commentStatus) { if (s.charAt(i) == ']') {
 * commentStatus = false; } } else if (s.charAt(i) == '[') { commentStatus =
 * true; } else if (containsChar(this.searchString, s.charAt(i))) { if
 * (s.charAt(i) == ' ') {
 * 
 * } else { lexedList.add(s.substring(i, i + 1)); }
 * 
 * } else { int j = i + nextIndex(s.substring(i));
 * 
 * lexedList.add(s.substring(i, j));
 * 
 * i = j - 1; } }
 * 
 * return lexedList; }
 */