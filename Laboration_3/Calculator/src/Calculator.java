import java.util.Scanner;

public class Calculator {

    public static class stack {

        double[] stackArray = new double[10000];
        double value;
        int pointer = 0;
        int full = 10000;


        private void push(double var) {
            if (pointer<full){
                stackArray[pointer] = var;
                pointer++;
                value = 0;
            }
            else {
                System.out.println("Stack is full!");
            }
        }

        private void pop() {
            pointer--;
            value = stackArray[pointer];
            stackArray[pointer] = 0;
        }

        private boolean is_empty() {
            return pointer == 0;
        }

    }

    private static double calc(double var1, double var2, String operator){
        if (operator.equals("+")){
            return var1 + var2;
        }
        else if (operator.equals("-")){
            return var1 - var2;
        }
        else if (operator.equals("*")){
            return var1 * var2;
        }
        else {
            return var1 / var2;
        }

    }

    public static void main(String[] args) {
        stack S = new stack();
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter command");
            String command = input.nextLine();
            String[] splitted_input = command.split(" ");


            for (int i = 0; i < splitted_input.length; i++) {
                if (splitted_input[i].equals("+")|| splitted_input[i].equals("-") ||
                        splitted_input[i].equals("*") || splitted_input[i].equals("/")) {
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
                            S.push(calc(second_op,first_op,splitted_input[i]));
                        }

                    }

                }
                else if (splitted_input[i].equals("=")) {
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
                        double d = Double.parseDouble(splitted_input[i]);
                        S.push(d);
                    }
                    catch (Exception e) {
                        System.out.println("The only allowed symbols are numbers and a few operators. (+,-,*,/,=)");
                    }
                }
            }
        }
    }
}
