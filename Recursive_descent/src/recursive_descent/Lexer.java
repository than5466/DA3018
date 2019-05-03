package recursive_descent;

import java.util.ArrayList;

public class Lexer {
	ArrayList<String> tokens;
	String string;
	boolean comment;
	String searchString = ",();:[] ";

	public Lexer(String str) {
		this.string = str;
		this.tokens = new ArrayList<String>();
		this.comment = false;
	}

	/*
	 * public ArrayList<String> tokens() { for (int i = 0; this.string.length() > i;
	 * i++) { if (this.comment == true) { if (this.string.substring(i, i +
	 * 1).equals("]")) { this.comment = false; } } else { if
	 * (this.string.substring(i, i + 1).equals("[")) { this.comment = true; } else {
	 * int j = i; while (!containsChar(this.searchString, this.string.charAt(j)) &&
	 * j < this.string.length() - 1) { j++; } if (j != i) { if (j ==
	 * this.string.length() - 1) { if (containsChar(this.searchString,
	 * this.string.charAt(j))) { if (this.string.charAt(j) == ' ') {
	 * this.tokens.add(this.string.substring(i, j));
	 * 
	 * } else { this.tokens.add(this.string.substring(i, j));
	 * this.tokens.add(this.string.substring(j, j + 1)); } } else {
	 * 
	 * this.tokens.add(this.string.substring(i, j + 1)); } } else {
	 * 
	 * this.tokens.add(this.string.substring(i, j)); if (this.string.charAt(j) == '
	 * ') {
	 * 
	 * } else { this.tokens.add(this.string.substring(j, j + 1)); }
	 * 
	 * }
	 * 
	 * } else { if (this.string.charAt(i) == ' ') {
	 * 
	 * } else { this.tokens.add(this.string.substring(i, i + 1)); }
	 * 
	 * } i = j; } } } return this.tokens;
	 * 
	 * }
	 */
	
	public ArrayList<String> tokens() {
		for (int i = 0; this.string.length() > i; i++) {
			if (this.comment == true) {
				if (this.string.substring(i, i + 1).equals("]")) {
					this.comment = false;
				}
			} else {
				if (this.string.substring(i, i + 1).equals("[")) {
					this.comment = true;
				} else {
					int j = i;
					while (!containsChar(this.searchString, this.string.charAt(j)) && j < this.string.length() - 1) {
						j++;
					}
					if (j != i) {
						if (j == this.string.length() - 1) {
							if (containsChar(this.searchString, this.string.charAt(j))) {
								if (this.string.charAt(j) == ' ') {
									this.tokens.add(this.string.substring(i, j));

								} else {
									this.tokens.add(this.string.substring(i, j));
									this.tokens.add(this.string.substring(j, j + 1));
								}
							} else {

								this.tokens.add(this.string.substring(i, j + 1));
							}
						} else {

							this.tokens.add(this.string.substring(i, j));
							if (this.string.charAt(j) == ' ') {

							} else {
								this.tokens.add(this.string.substring(j, j + 1));
							}

						}

					} else {
						if (this.string.charAt(i) == ' ') {

						} else {
							this.tokens.add(this.string.substring(i, i + 1));
						}

					}
					i = j;
				}
			}
		}
		return this.tokens;

	}

	public boolean containsChar(String s, char search) {
		if (s.length() == 0)
			return false;
		else
			return s.charAt(0) == search || containsChar(s.substring(1), search);
	}

	public int nextIndex(String s) {
		for (int i = 1; i < s.length(); i++) {
			if (this.containsChar(this.searchString, s.charAt(i))) {
				return i;
			} else if (i == s.length() - 1) {
				return s.length();
			}
		}

		return 0;

	}
}
