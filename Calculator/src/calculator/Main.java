package calculator;
import java.util.Scanner;

public class Main {
	
	public static double calc(double var1, double var2, String operator) {
		switch (operator){
        case "+":
            return var1 + var2;
        case "-":
            return var1 - var2;
        case "*":
            return var1 * var2;
        default:
            return var1 / var2;
            }
	}

	public static void main(String[] args) {
		 /*
         * This is the main program. Whenever we're running the file,
         * this is the function that decides what code should run and when.
         */
        Stack S = new Stack();                                                                  // We're initializing an object of the stack class.
        while (true) {
            Scanner input = new Scanner(System.in);                                             // Initialize an instance of the Scanner class.
            System.out.println("Enter command");
            String[] split_input = input.nextLine().split(" ");                                 // Create an array of strings, split on blank spaces.


            for (String var : split_input) {                                                    // Loop through every object in the array of strings.
                if (var.equals("+")|| var.equals("-") ||                                        // If the string is equal to one of the operators, the calc function is called.
                        var.equals("*") || var.equals("/")) {
                    if(S.is_empty()){
                        System.out.println("Stack is empty, cannot compute");
                    }
                    else{
                        S.pop();
                        double first_op = S.value;
                        if (S.is_empty()){
                            S.push(first_op);
                            System.out.println("Only one operand in stack, there needs to be atleast two.");
                        }
                        else{
                            S.pop();
                            double second_op = S.value;
                            S.push(calc(second_op,first_op,var));
                        }

                    }

                }
                else if (var.equals("=")) {                                                    // Send the value of the object of the top of the stack to stdout.
                    if (S.is_empty()){
                        System.out.println("Stack is empty");

                    }
                    else {
                        S.pop();
                        System.out.println(S.value);
                        S.push(S.value);
                    }
                }

                else {

                    try {                                                                    // Convert the string to a double if possible, then push onto the stack.
                        double x = Double.parseDouble(var);
                        S.push(x);
                        if (S.error){
                            System.out.println("Stack is full. Clear it before pushing anything else");
                        }
                    }
                    catch (Exception e) {
                        System.out.println("The only allowed symbols are numbers and a few operators. (+,-,*,/,=)");
                    }
                }
            }
        }
	}
}

