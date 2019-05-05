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
	 * 
	 * @return
	 */

	public ArrayList<String> tokens() {
		return tokens(this.inputString);

	}
	/**
	 * 
	 * @param s
	 * @return
	 */
	
	public ArrayList<String> tokens(String s) {
		ArrayList<String> lexedList = new ArrayList<String>();
		boolean commentStatus = false;
		for (int i = 0; s.length() > i; i++) {
			if (commentStatus) {
				if (s.charAt(i) == ']') {
					commentStatus = false;
				}
			} else if (s.charAt(i) == '[') {
				commentStatus = true;
			} else if (containsChar(this.searchString, s.charAt(i))) {
				if (s.charAt(i) == ' ') {

				} else {
					lexedList.add(s.substring(i, i + 1));
				}

			} else {
				int j = i + nextIndex(s.substring(i));

				lexedList.add(s.substring(i, j));

				i = j-1;
			}
		}

		return lexedList;
	}
	/**
	 * 
	 * @param s	
	 * @param search
	 * @return
	 * Recursively compares every index of the string s with the char c. Returns true if one of these
	 * comparisons returns true, otherwise false.
	 */

	public boolean containsChar(String s, char c) {
		if (s.length() == 0)
			return false;
		else
			return s.charAt(0) == c || containsChar(s.substring(1), c);
	}
	
	/**
	 * 
	 * @param s 
	 * @return
	 * given a string s, returns the first index in the string containing one of the symbols in searchString.
	 * Returns the length of the string if none exists.
	 */

	public int nextIndex(String s) {
		for (int i = 1; i < s.length(); i++) {
			if (this.containsChar(this.searchString, s.charAt(i))) {
				return i;
			} 
		}

		return s.length();

	}
}
