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
         * and a Scanner class object is created every loop which asks for an input to stdin. This input is split into
         * an array of strings, and each string is first compared to the available operators. If it's equal to any of the
         * operators, a calculation is made. If the stack contains less than two variables, an error message is displayed
         * and the calculation is cancelled. If the comparison fails, an attempt to convert the string into a double is made. 
         * This double is then pushed onto the stack. If this also fails, an error message is sent to stdout. 
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
                        S.pop();
                        if (S.is_empty()){
                            S.push(S.value);
                            System.out.println("Only one operand in stack, there needs to be atleast two.");
                        }
                        else{
                            S.pop();
                            S.push(calc(S.value,S.prev,var));
                        }

                    }

                }
                else if (var.equals("=")) {                                                 
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

                    try {                                                                    
                        double num = Double.parseDouble(var);
                        if (S.pointer < S.length) {
                        	S.push(num);
                        }
                        else{
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

