package calculator;

public class Stack {
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


    public void push(double var) {
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

    public void pop() {

        /*
         * A method for taking out the top
         * object from the stack.
         */
        pointer--;
        value = stackArray[pointer];
        stackArray[pointer] = 0;
    }

    public boolean is_empty() {
        /*
         * Returns true or false,
         * depending on whether
         * the stack is empty or not.
         */
        return pointer == 0;
    }
}
