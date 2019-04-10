import java.util.Scanner;

public class Calculator {

    public static class stack {

        double[] myArray = new double[10000];
        double value;
        int pointer = 0;
        int full = 10000;


        public void push(double var) {
            if (pointer<full){
                myArray[pointer] = var;
                pointer++;
                value = 0;
            }
            else {
                System.out.println("Stack is full!");
            }
        }

        public void pop() {
            pointer--;
            value = myArray[pointer];
            myArray[pointer] = 0;
        }

        public boolean is_empty() {
            if (pointer == 0) {
                return true;
            } else {
                return false;
            }
        }

    }

    public static double calc(double var1, double var2, String operator){
        if (operator.equals("+")){
            return var2 + var1;
        }
        else if (operator.equals("-")){
            return var2 - var1;
        }
        else if (operator.equals("*")){
            return var2 * var1;
        }
        else {
            return var2 / var1;
        }

    }

    public static void main(String[] args) {
        stack S = new stack();
        while (true) {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter command");
            String command = myObj.nextLine();
            String[] splitted = command.split(" ");


            for (int i = 0; i < splitted.length; i++) {
                if (splitted[i].equals("+")|| splitted[i].equals("-") || splitted[i].equals("*") ||
                        splitted[i].equals("/")) {
                    if(S.is_empty()){
                        System.out.println("Stack is empty, cannot compute");
                    }
                    else{
                        S.pop();
                        double first = S.value;
                        if (S.is_empty()){
                            S.push(first);
                            System.out.println("Only one operand in stack, there needs to be atleast two.");
                        }
                        else{
                            S.pop();
                            double second = S.value;
                            S.push(calc(first,second,splitted[i]));
                        }

                    }

                }
                else if (splitted[i].equals("=")) {
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
                        double d = Double.parseDouble(splitted[i]);
                        S.push(d);
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }
}
