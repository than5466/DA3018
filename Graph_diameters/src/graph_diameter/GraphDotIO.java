package graph_diameter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GraphDotIO {
	private BufferedReader reader;
	private DotGraph<String> graph;
	private ArrayList<ArrayList<String>> lexedInput;

	public GraphDotIO(String filename) throws FileNotFoundException {
		this.reader = new BufferedReader(new FileReader(
				"/home/thoan741/Skrivbord/Programmering/DA3018/Graph_diameters/src/graph_diameter/" + filename));
		this.graph = new DotGraph<String>();
		this.lexedInput = new ArrayList<ArrayList<String>>();
	}

	public DotGraph<String> read_dot() throws IOException {

		String str;
		boolean Grammar;
		while ((str = reader.readLine()) != null) {
			Lexer(str);
		}
		if (lexedInput.get(0).size() != 3) {
			return null;
		}
		if (!lexedInput.get(0).get(2).equals("{")) {
			return null;
		}
		lexedInput.remove(0);
		if (lexedInput.get(lexedInput.size() - 1).size() != 1) {
			return null;
		}
		if (!lexedInput.get(lexedInput.size() - 1).get(0).equals("}")) {
			return null;
		}
		lexedInput.remove(lexedInput.size() - 1);
		Grammar = parse();
		if (Grammar) {
			return this.graph;
		}
		return null;

	}

	public void Lexer(String s) {
		String[] str = s.split(" ");
		ArrayList<String> x = new ArrayList<String>();
		for (String st : str) {
			if (!st.equals("")) {
				x.add(st);
			}
		}
		lexedInput.add(x);

		System.out.println(x.size());

	}

	public boolean parse() {
		boolean check = true;
		ArrayList<String> s;
		String str;
		while (lexedInput.size() != 0 && check) {
			s = lexedInput.get(0);
			lexedInput.remove(0);
			if (s.size() != 3) {
				check = false;
			} else if (!s.get(1).equals("--")) {
				check = false;
			}
			str = s.get(2);
			if (str.charAt(str.length()-1) != ';') {
				check = false;
			}
			graph.add(s.get(0), s.get(2).substring(0,s.get(2).length()-1));

		}
		return check;
	}

	public DotGraph<String> getGraph() {
		return this.graph;
	}

	public static void main(String[] args) throws IOException {
		try {
			GraphDotIO x = new GraphDotIO("circular.dot");
			DotGraph<String> b = x.read_dot();
			if (b != null) {
				System.out.println(b.GetEdgeSet());
			} else {
				System.out.println("Det blev fel");
			}
		} catch (IOException e) {
			System.out.println("Oj då, är du säker på att denna fil existerar?");
		}
	}

}
