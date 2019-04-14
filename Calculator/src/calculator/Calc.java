package calculator;

public class Calc {

	double operand;
	double ans;
	boolean error_flag;
	Stack stack = new Stack();

	public void show() {
		if (stack.is_empty()) {
			System.out.println("Stack is empty, nothing to show.");
		} else {
			stack.pop();
			System.out.println(stack.value);
			stack.push(stack.value);
		}
	}

	public void performCalc(String operator) {
		error_flag = false;
		if (stack.is_empty()) {
			error_flag = true;

		} else {
			stack.pop();
			operand = stack.value;
			if (stack.is_empty()) {
				stack.push(stack.value);
				error_flag = true;
			} 
			else {
				stack.pop();
				switch (operator) {
				case "+":
					ans = stack.value + operand;
					break;
				case "-":
					ans = stack.value - operand;
					break;
				case "*":
					ans = stack.value * operand;
					break;
				case "/":
					ans = stack.value / operand;
					break;
				}
				stack.push(ans);

			}

		}

	}

}
