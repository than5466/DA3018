package recursive_descent;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Lexer a = new Lexer("(a,(c,(b,(d,e))));");
		ArrayList<String> b = a.tokens();

		System.out.println(b);
		
		TreeParser c = new TreeParser();
		Tree x = c.parse(b);
		
		if (x != null ) {

			System.out.println(x.height());
			
			System.out.print(x.n_leaves());
			
		}
		else {
			System.out.println("Error,error.");
		}
	}
}
