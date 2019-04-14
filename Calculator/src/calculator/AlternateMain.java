package calculator;

import java.util.Scanner;

public class AlternateMain {
	
	/**
	 * The difference between this and the original main method,
	 * is that this uses an object of the Calc class, which also inherits from the Stack
	 * class, whereas the original only inherits from the Stack class. This shortens the main program,
	 * since we can use the methods from the Calc class, not having to define them in this function.
	 */

	public static void main(String[] args) {
		Calc calculator = new Calc();

		while (true) {
			Scanner input = new Scanner(System.in);
			System.out.print("Input: ");
			String[] split_input = input.nextLine().split(" ");

			for (String var : split_input) {

				if (var.equals("+") || var.equals("-") || var.equals("*") || var.equals("/")) {
					calculator.performCalc(var);

				} else if (var.equals("=")) {
					calculator.show();
					if (calculator.error_flag) {
						System.out.println("There need to be be atleast 2 numbers in the stack for this operation.");
						break;
					}
				}

				else {
					try {
						double num = Double.parseDouble(var);
						if (calculator.stack.pointer < calculator.stack.length) {
							calculator.stack.push(num);
						} else {
							System.out.println("Stack is full. Clear it before pushing anything else");
						}
					} catch (Exception e) {
						System.out.println("The only allowed symbols are numbers and a few operators. (+,-,*,/,=)");
						break;
					}
				}

			}
		}
	}
}
