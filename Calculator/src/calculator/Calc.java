package calculator;

public class Calc {
	/**
	 * A class which loads an object of the stack class,
	 * and has methods to compute arithemetic operations.
	 */

	double operand;
	double ans;
	boolean error_flag;
	Stack stack = new Stack();

	public void show() {
		/**
		 * Prints the top element of the stack. If no element is in the stack,
		 * an error message is shown.
		 */
		if (stack.is_empty()) {
			System.out.println("Stack is empty, nothing to show.");
		} else {
			double ans = stack.pop();
			System.out.println(ans);
			stack.push(ans);
		}
	}

	public void performCalc(String operator) {
		/**
		 * retrieves the top 2 elements from the stack. Based on the
		 * string this method takes as an argument, different
		 * arithmetic operations is used between these elements before
		 * pushing the result back onto the stack.
		 */
		error_flag = false;
		if (stack.is_empty()) {
			error_flag = true;

		} else {
			double temp1 = stack.pop();
			if (stack.is_empty()) {
				stack.push(temp1);
				error_flag = true;
			} 
			else {
				double temp2 = stack.pop();
				switch (operator) {
				case "+":
					ans = temp2 + temp1;
					break;
				case "-":
					ans = temp2 - temp1;
					break;
				case "*":
					ans = temp2 * temp1;
					break;
				case "/":
					ans = temp2 / temp1;
					break;
				}
				stack.push(ans);

			}

		}

	}

}
