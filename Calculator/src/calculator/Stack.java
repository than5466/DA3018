package calculator;

public class Stack {
	/**
     * Create a new class named Stack.
     * When initializing an instance of this class,
     * all the variables and methods below are
     * available for that object.
     */

    double[] stackArray = new double[10000];
    int length = stackArray.length;
    double value;
    double prev;
    int pointer = 0;


    public void push(double var) {
        /**
         * This is a method for pushing a variable
         * of type double onto the stack.
         * If the stack is full, an error flag
         * is raised.
         */
            stackArray[pointer] = var;
            pointer++;
    }

    public void pop() {

        /**
         * A method for taking out the top
         * object from the stack.
         */
    		prev = value;
            pointer--;
            value = stackArray[pointer];
            stackArray[pointer] = 0;
    }

    public boolean is_empty() {
        /**
         * Simply returns true or false,
         * depending on whether
         * the stack is empty or not.
         */
        return pointer == 0;
    }
}
