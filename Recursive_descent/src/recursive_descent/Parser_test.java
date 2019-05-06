package recursive_descent;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.Test;

class Parser_test {
	
	public static BufferedReader reader(String str) {
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(
					"/home/thoan741/Skrivbord/Programmering/DA3018/Recursive_descent/src/recursive_descent/test/" + str));
		} catch (FileNotFoundException e) {
			br = null;
		}

		return br;

	}
	
	public static String reading(BufferedReader br) {
		try {
			String s = "";
			String str;
			
			while ((str = br.readLine()) != null) {
				s = s + str;
			}
			
			return s;
		}
		catch (Exception e){
			return null;
		}
	}
	
	String b1 = reading(reader("bad1.tree"));
	String b2 = reading(reader("bad2.tree"));
	String b3 = reading(reader("bad3.tree"));
	String e = reading(reader("eggs.tree"));
	String two = reading(reader("two.tree"));
	String leaf = reading(reader("leaf.tree"));
	String four = reading(reader("four.tree"));
	String five = reading(reader("five.tree"));
	

	@Test
	void testFiles() {
		Lexer B1 = new Lexer(b1);
		Lexer B2 = new Lexer(b2);
		Lexer B3 = new Lexer(b3);
		Lexer E = new Lexer(e);
		Lexer TWO = new Lexer(two);
		Lexer LEAF = new Lexer(leaf);
		Lexer FOUR = new Lexer(four);
		Lexer FIVE = new Lexer(five);
		
		TreeParser parser1 = new TreeParser();
		TreeParser parser2 = new TreeParser();
		TreeParser parser3 = new TreeParser();
		TreeParser parser4 = new TreeParser();
		TreeParser parser5 = new TreeParser();
		TreeParser parser6 = new TreeParser();
		TreeParser parser7 = new TreeParser();
		TreeParser parser8 = new TreeParser();
		
		Tree x1 = parser1.parse(B1.tokens());
		Tree x2 = parser2.parse(B2.tokens());
		Tree x3 = parser3.parse(B3.tokens());
		Tree x4 = parser4.parse(E.tokens());
		Tree x5 = parser5.parse(TWO.tokens());
		Tree x6 = parser6.parse(LEAF.tokens());
		Tree x7 = parser7.parse(FOUR.tokens());
		Tree x8 = parser8.parse(FIVE.tokens());
		
		assertEquals(null,x1);
		assertEquals(null,x2);
		assertEquals(null,x3);
		
		assertEquals(4,x4.height());
		assertEquals(2,x5.n_leaves());
		assertEquals(1,x6.n_leaves());
		assertEquals(3,x7.height());
		assertEquals(5,x8.height());
		assertEquals(5,x8.n_leaves());
		
		
		
	}
	
	@Test
	void test() {
		Lexer a = new Lexer("((a,b), (c ,d));[oviktigt]");
		Lexer b = new Lexer("([kommentar](a,b),[En liten kommentar] (c ,d));");
		
		assertEquals(a.tokens(),b.tokens());
	}

}
