package calculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackTest {
	Stack stack1 = new Stack();
	Stack stack2 = new Stack();

	@Test
	public void testEmpty() {
		stack2.push(5);
		boolean a = stack1.is_empty();
		boolean b = stack2.is_empty();
		
		assertEquals(true,a);
		assertEquals(false,b);
		
		
	}
	@Test
	public void testPopPush() {
		stack1.push(5);
		stack1.push(7);
		double c = stack1.stackArray[0];
		double d = stack1.stackArray[1];
		
		assertEquals(2,stack1.pointer);
		assertEquals(5,c, 0.0);
		assertEquals(7,d, 0.0);
		
		
		
	}
	@Test
	public void testCalc() {
		double A = Main.calc(5,5,"+");
		double B = Main.calc(5,5,"-");
		double C = Main.calc(5,5,"*");
		double D = Main.calc(5,5,"/");
		
		assertEquals(10,A,0.0);
		assertEquals(0,B,0.0);
		assertEquals(25,C,0.0);
		assertEquals(1,D,0.0);
	}
	
	@Test
	public void testcalc() {
		Calc a = new Calc();
		a.stack.push(5);
		a.stack.push(3);
		a.performCalc("+");
		double b = a.stack.pop();
		
		assertEquals(8,b,0.0);
	}

}
