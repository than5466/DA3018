package recursive_descent;

import java.util.ArrayList;

public class Lexer {
	String inputString;
	String search = ",();:[] ";
	
	/**
	 * 
	 * @param str
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
		boolean ifComment = false;
		for (int i = 0; s.length() > i; i++) {
			if (ifComment) {
				if (s.charAt(i) == ']') {
					ifComment = false;
				}
			} else if (s.charAt(i) == '[') {
				ifComment = true;
			} else if (containsChar(this.search, s.charAt(i))) {
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
	 */

	public int nextIndex(String s) {
		for (int i = 1; i < s.length(); i++) {
			if (this.containsChar(this.search, s.charAt(i))) {
				return i;
			} else if (i == s.length() - 1) {
				return s.length();
			}
		}

		return 0;

	}
}
