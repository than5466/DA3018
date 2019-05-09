package recursive_descent;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Lexer a = new Lexer("(a,b);");
		ArrayList<String> b = a.tokens();

		System.out.println(b);
		
		TreeParser c = new TreeParser();
		Tree x = c.parse(b);
		
		try {

			System.out.println(x.height());
			
			System.out.print(x.n_leaves());
			
		}
		catch (Exception e) {
			System.out.println("Error,error.");
		}
	}
}
