import java.util.Scanner;                                                                       // Import the Scanner class.

public class Calculator {

    public static class stack {

        /*
        
        * Creating a new class named stack.
        * When initializing an instance of this class,
        * the variables below are defined as well.

        */

        double[] stackArray = new double[10000];
        double value;
        int pointer = 0;
        int full = stackArray.length;
        boolean error = false;


        private void push(double var) {
            /*

            * This is a method for pushing a variable
            * of type double onto the stack.
            * If the stack is full, an error flag
            * is raised.

             */
            if (pointer<full){
                stackArray[pointer] = var;
                pointer++;
                value = 0;
            }
            else {
                error = true;
            }
        }

        private void pop() {

            /*

            * A method for taking out the top
            * object from the stack.

             */
            pointer--;
            value = stackArray[pointer];
            stackArray[pointer] = 0;
        }

        private boolean is_empty() {
            /*

            * Returns true or false,
            * depending on whether
            * the stack is empty or not.

             */
            return pointer == 0;
        }

    }

    private static double calc(double var1, double var2, String operator){
        /*

        * Decides which of the calculation methods
        * below that should be used on the two variables.

         */

        switch (operator){
            case "+":
                return var1 + var2;
            case "-":
                return var1 - var2;
            case "*":
                return var1 * var2;
            case "/":
                return var1 / var2;
        }


    }

    public static void main(String[] args) {
        /*

        * This is the main program. Whenever we're running the file,
        * this is the function that decides what code should run and when.

         */
        stack S = new stack();                                                                  // We're initializing an object of the stack class.
        while (true) {
            Scanner input = new Scanner(System.in);                                             // Initialize an instance of the Scanner class.
            System.out.println("Enter command");
            String command = input.nextLine();                                                  // When we run this program, this is where our input is saved.
            String[] split_input = command.split(" ");                                    // Create an array of strings, split on blank spaces.


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
