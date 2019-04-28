package binary_tree;

import java.io.*;

public class CourseInfo{
	
	
	
	/*

	public static BufferedReader reader(String str) throws FileNotFoundException {
		BufferedReader br;

		br = new BufferedReader(new FileReader(str));

		return br;

	}

	public static void main(String[] args) throws IOException {

		BinarySearchTree BST = new BinarySearchTree();
		if (args.length == 1 || args.length == 2) {
			BufferedReader f = reader(args[0]);
			f.readLine();

			String str;
			String[] strList;
			while ((str = f.readLine()) != null) {

				strList = str.split(",");

				BST.insert(strList[0], strList[1], Double.parseDouble(strList[2]));

			}
			if (args.length == 2) {
				BinarySearchTree.BSTNode g;
				g = BST.find(args[1]);
				System.out.print(g.getCourseCode() + "  ");
				System.out.print(g.getCourseName() + "  ");
				System.out.print(g.getCredits());
			}
		}
	}
}
		*/
		
		 
	public static void main(String[] args) {
		
		  BinarySearchTree courses = new BinarySearchTree();
		  
		  courses.insert("DA3018", "Computer Science", 7.5); 
		  courses.insert("MM2001", "Matematik I", 30.0); 
		  courses.insert("DA2004", "Programmeringsteknik", 7.5);
		  courses.insert("DA2005", "Progg",2.0); 
		  courses.insert("DA2003", "Progg",2.0);
		  
		  courses.insert("MM2001", "Matematik I", 20.0); 
		  courses.insert("MM2002", "Matematik II", 20.0); 
		  courses.insert("MM2004", "Matematik III", 20.0);
		  courses.insert("MM2003", "Matematik IV", 20.0);
		  
		  for (BinarySearchTree.BSTNode node: courses) {
		  System.out.println(node.getCourseCode());
		  }
		  
		  int n = courses.size();
		  System.out.printf("There are %d courses in the database.\n", n);
		  
		  double x = courses.find("MM2001").getCredits();
		  
		  System.out.println(x);
		  
		  courses.remove("DA3018"); 
		  courses.remove("DA2004"); 
		  courses.remove("MM2001");
		  courses.remove("MM2003"); 
		  courses.remove("MM2004"); 
		  courses.remove("MM2002");
		  courses.remove("DA2003");
		  courses.remove("DA2005");
		  
		  
		  
		  
		  
		  n = courses.size();
		  System.out.printf("There are %d courses in the database.\n", n); 
		  courses.insert("MM2001", "Matematik I", 30.0); 
		  String name = courses.find("MM2001").getCourseName(); 
		  System.out.printf("Name: %s\n", name);
		  
	}

}

		 
		
	