package calculator;
import java.util.Scanner;

public class Main {
	
	public static double calc(double var1, double var2, String operator) {
		/**
		 * This function decides which of the four elementary arithmetic operations should be used.
		 */
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
		 /**
         * This is the main program. Whenever we run the program, this is the code that executes.
         * First an object of the Stack class is initialized. Then an endless loop is started
         * where and Scanner class is created every loop which asks us for an input. This input is split into
         * an array of strings, and each string is first compared to the available operators. If it's equal to any of the
         * operators, a calculation is made. If the stack contains less than two doubles, an error message is displayed
         * and the calculation is cancelled. If the comparison fails, an attempt to convert the string into a double is made. 
         * If this also fails, an error message is sent to system out. 
         */
        Stack S = new Stack();                                                                 
        while (true) {
            Scanner input = new Scanner(System.in);                                             
            System.out.println("Enter command");
            String[] split_input = input.nextLine().split(" ");                                 


            for (String var : split_input) {                                                    
                if (var.equals("+")|| var.equals("-") ||                                        
                        var.equals("*") || var.equals("/")) {
                    if(S.is_empty()){
                        System.out.println("Stack is empty, cannot compute");
                    }
                    else{
                        double first_op = S.pop();
                        if (S.is_empty()){
                            S.push(first_op);
                            System.out.println("Only one operand in stack, there needs to be atleast two.");
                        }
                        else{
                            S.pop();
                            double second_op = S.pop();
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

