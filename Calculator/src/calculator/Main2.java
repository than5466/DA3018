package calculator;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Calc calculator = new Calc();

		while (true) {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter RPN command");
			String[] split_input = input.nextLine().split(" ");

			for (String var : split_input) {

				if (var.equals("+") || var.equals("-") || var.equals("*") || var.equals("/")) {
					calculator.performCalc(var);

				} else if (var.equals("=")) {
					calculator.show();
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
					}
				}

			}
		}
	}
}
