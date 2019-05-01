package binary_tree;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {
	BinarySearchTree BST;;

	@Test
	void sizeTest() {
		BST = new BinarySearchTree();

		
		assertEquals(0,BST.size());
		BST.insert("MM2001","Matematik I", 30.0);
		BST.insert("DA2001","Datalogi I", 15.0);
		BST.insert("MM5012","Linjär Algebra", 7.5);
		BST.insert("DA3018","Computer Science", 7.5);
		BST.insert("5","A", 7.5);
		BST.insert("2","B", 7.5);
		BST.insert("7","G", 7.5);
		BST.insert("4","D", 7.5);
		BST.insert("6","E", 7.5);
		
		BST.insert("5","C", 7.5);
		
		assertEquals(9,BST.size());
	}
	
	@Test
	void findTest() {
		BST = new BinarySearchTree();
		
		
		BST.insert("MM2001","Matematik I", 30.0);
		BST.insert("DA2001","Datalogi I", 15.0);
		BST.insert("MM5012","Linjär Algebra", 7.5);
		BST.insert("DA3018","Computer Science", 7.5);
		BST.insert("5","A", 7.5);
		BST.insert("2","B", 7.5);
		BST.insert("7","G", 7.5);
		BST.insert("4","D", 7.5);
		BST.insert("6","E", 7.5);

		assertEquals("A",BST.find("5").getCourseName());
		
		BST.insert("5","C", 7.5);
		

		assertEquals("C",BST.find("5").getCourseName());
		
		assertEquals("Matematik I",BST.find("MM2001").getCourseName());
		assertEquals(7.5,BST.find("7").getCredits());
		assertEquals(null,BST.find("MM2000"));
	}
	
	@Test
	void removeTest() {
		BST = new BinarySearchTree();
		
		
		BST.insert("MM2001","Matematik I", 30.0);
		BST.insert("DA2001","Datalogi I", 15.0);
		BST.insert("MM5012","Linjär Algebra", 7.5);
		BST.insert("DA3018","Computer Science", 7.5);
		BST.insert("5","A", 7.5);
		BST.insert("2","B", 7.5);
		BST.insert("7","G", 7.5);
		BST.insert("4","D", 7.5);
		BST.insert("6","E", 7.5);
		
		BST.insert("5","C", 7.5);
		
		assertEquals(9,BST.size());
		
		BST.remove("MM2001");
		
		assertEquals(8, BST.size());
		
		BST.remove("MM2001");
		
		assertEquals(8, BST.size());
		
		BST.remove("DA2001");
		BST.remove("MM5012");
		BST.remove("DA3018");
		BST.remove("5");
		BST.remove("2");
		BST.remove("7");
		BST.remove("4");
		BST.remove("6");
		
		BST.remove("5");
		
		assertEquals(0, BST.size());
	}
	
	void iteratorTest() {
		BST = new BinarySearchTree();
		int Size = 0;
		
		BST.insert("MM2001","Matematik I", 30.0);
		BST.insert("DA2001","Datalogi I", 15.0);
		BST.insert("MM5012","Linjär Algebra", 7.5);
		BST.insert("DA3018","Computer Science", 7.5);
		BST.insert("5","A", 7.5);
		BST.insert("2","B", 7.5);
		BST.insert("7","G", 7.5);
		BST.insert("4","D", 7.5);
		BST.insert("6","E", 7.5);
		
		BST.insert("5","C", 7.5);
		
		for (BinarySearchTree.BSTNode node : BST) {
			assertEquals(node, BST.find(node.getCourseCode()));
			Size ++;
			  }
		assertEquals(Size, BST.size());
		
	}

}
