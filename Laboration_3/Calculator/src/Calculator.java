import java.util.Scanner;

public class Calculator {

    public static class stack {

        double[] myArray = new double[10000];
        double value;
        int pointer = 0;


        public void push(double var) {
            this.myArray[pointer] = var;
            this.pointer++;
        }

        public void pop() {
            this.pointer--;
            this.value = this.myArray[this.pointer];
            this.myArray[this.pointer] = 0;
        }

        public boolean is_empty() {
            if (this.pointer != 0) {
                return true;
            } else {
                return false;
            }
        }

        public void show(){
            this.pop();
            System.out.println(this.value);
            this.push(this.value);
        }

        public void calc() {
            this.pop();
            double a = this.value;
            this.pop();
            double b = this.value;
            this.push(a+b);
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
                if (splitted[i].equals("+")) {
                    S.calc();

                }

                else if (splitted[i].equals("=")){
                    S.show();
                }
                else {

                    double d = Double.parseDouble(splitted[i]);
                    S.push(d);
                }
            }
        }
    }
}
