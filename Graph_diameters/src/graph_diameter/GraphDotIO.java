package graph_diameter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GraphDotIO {
	private BufferedReader reader;
	private DotGraph<String> graph;
	private String compareString;
	private String input;

	/**
	 * 
	 * @param s - name of the file to read
	 * @throws FileNotFoundException - A file with the given name is non-existent.
	 */

	public GraphDotIO(String s) throws FileNotFoundException {
		try {
			this.reader = new BufferedReader(new FileReader(
					"/home/thoan741/Skrivbord/Programmering/DA3018/Graph_diameters/src/graph_diameter/" + s));
		} catch (FileNotFoundException e){
			this.reader = null;
		}
		finally {

			this.graph = new DotGraph<String>();
			this.compareString = " {};\t";
			this.input = s;
		}

	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 * returns a graph made from the input, with the help of the lexer and parser definied below.
	 */

	public DotGraph<String> read_dot() throws IOException {
		String str;
		String s = "";
		ArrayList<String> LexedInput;
		if (this.reader == null) {
			s = this.input;
		} else {
			while ((str = reader.readLine()) != null) {
				s += str;
			}
		}
		LexedInput = Lexer(s);
		parse(LexedInput);
		return this.graph;

	}
	
	/**
	 * 
	 * @param s
	 * @return
	 * return a lexed expression.
	 */
	private ArrayList<String> Lexer(String s) {
		ArrayList<String> Lexed = new ArrayList<String>();
		int n;
		String str;
		while (s.length() > 0) {
			n = nextIndex(s);
			str = s.substring(0, n);
			s = s.substring(n);
			if (!str.equals(" ") && !str.equals("\t")) {
				Lexed.add(str);
			}
		}
		System.out.println("Lexed expression:\t" + Lexed);
		return Lexed;
	}
	
	/*
	 * return true if a substring contains a certain character.
	 */

	private boolean contains(char c, String s) {
		if (s.length() == 0) {
			return false;
		}
		return s.charAt(0) == c || contains(c, s.substring(1));

	}

	private int nextIndex(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (contains(s.charAt(i), this.compareString)) {
				return Math.max(i, 1);
			}
		}
		return s.length();
	}
	
	/**
	 * 
	 * @param s
	 * parsing the expression.
	 * the expression should be on the form: "str" "str" "{" expr "}",
	 * where  expr = "node" "--" "node" ";" || nothing
	 */

	private void parse(ArrayList<String> s) {
		if ((s.size() % 4) != 0 || s.size() < 4 || !s.get(2).equals("{") || !s.get(s.size() - 1).equals("}")) {
			this.graph = null;
			return;
		}
		s.remove(0);
		s.remove(0);
		s.remove(0);
		s.remove(s.size() - 1);
		while (s.size() > 0) {
			if (checkVertices(s.get(0), s.get(2))) {
				this.graph = null;
				return;

			}
			if (!s.get(1).equals("--") || !s.get(3).equals(";") || s.get(0).equals(s.get(2))) {
				this.graph = null;
				return;
			}
			this.graph.add(s.get(0), s.get(2));
			s.remove(0);
			s.remove(0);
			s.remove(0);
			s.remove(0);

		}
	}
	
	/**
	 * 
	 * @param V1
	 * @param V2
	 * @return
	 * true if V1 or V2 is not a node.
	 */

	public boolean checkVertices(String V1, String V2) {
		boolean checkFirst;
		boolean checkSecond;
		checkFirst = contains(V1.charAt(0), compareString);
		checkSecond = contains(V2.charAt(0), compareString);
		return checkFirst || checkSecond;
	}

	public DotGraph<String> getGraph() {
		return this.graph;
	}

	public static void main(String[] args) throws IOException {
		if (args.length == 1) {
			GraphDotIO x = new GraphDotIO(args[0]);
			DotGraph<String> graph = x.read_dot();
			if (graph != null) {
				System.out.println("Vertices and their edges: \t" + graph.GetGraph());
			} else {
				System.out.println("The given argument is neither an existing filename nor a correct expression.");
			}

		} else {
			if (args.length == 0) {
				System.out.println("No argument given");
			} else {
				System.out.println("Only one argument expected.");
			}
		}

	}

}
