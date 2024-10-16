package edu.grinnell.csc207.util;

import java.io.PrintWriter;
import java.math.BigInteger;

// import edu.grinnell.csc207.util.BigFraction;

/**
 * a set of utility functions for bigFraction Claculators:)
 *
 * @author Jana Vadillo
 */
public class CalculatorUtil {
    /**
     * the different types of user input one can have
     */
    public enum userInput {
        /** asking you to store a variable */
        STORE,
        /** register: a lowercase value storing a BigFraction */
        REGISTER,
        /** integer: an integer */
        INTEGER,
        /** Fraction: a fraction with the form a/b (no spaces) */
        FRACTION,
        /** OPERATOR: a valid opperator /, *, +, - */
        OPERATOR,
        /** Invalid, none of the above */
        INVALID
    }// Enum to store different types of user input

    /**
     * takes in a string and determines if it is a lowercase charachter
     * 
     * @param val string to check
     * @return either the lowercase charachter or newline to signify failure
     */
    public static char isLowercase(String val) {
        int lowercaseStart = (int) 'a';
        int lowercaseEnd = (int) 'z';
        if (val.length() > 1) {
            return ('\n');
        } // if you input a string that cannot be a char return newline to sig failure
        char valChar = val.charAt(0);
        if ((valChar > lowercaseEnd) || (valChar < lowercaseStart)) {
            return ('\n');
        } // if a charachter that is not lowercase is found return newline to sig failure
        return valChar;
    }// isLowercase(String)

    /**
     * checks to see if a string is an int, returning a respective Big Integer if so
     * 
     * @param val value to check
     * @return return value
     */
    public static BigInteger isInt(String val) {
        try {
            // checking valid integer using parseInt()
            Integer thisInteger = Integer.parseInt(val);
            return BigInteger.valueOf(thisInteger);
        } catch (NumberFormatException e) {
            return null;
        } // try catch to test if it is an int or not
    }// isInt(String)

    /**
     * checks to see if a string is a Frac, returning a Big Fraction if so
     * 
     * @param val // value to check
     * @return // returns the corresponding big Fraction
     */
    public static BigFraction isFrac(String val) {
        if (val.contains("/")) {
            String[] numDenom = val.split("/");
            if (numDenom.length != 2) {
                return null;
            } // if length is not two, more than two / are present
            BigInteger num = isInt(numDenom[0]);
            BigInteger denom = isInt(numDenom[1]);

            if ((num != null) && (denom != null)) {
                return new BigFraction(num, denom);
            } else {
                return null;
            } // deal with the edge case of either num or denom not being actual values

        } else {
            return null;
        } // if no / is contained, it must not be in fractional form
    }// isFrac(String)

    /**
     * Function to define what input one currently has based off a string
     * 
     * @param str string to check
     * @return the userInput enum that dictates what we found
     */
    public static userInput whatInput(String str) {

        if (str.equals("STORE")) {
            return userInput.STORE;
        } else if (isLowercase(str) != '\n') {
            return userInput.REGISTER;
        } else if (str.equals("+") || str.equals("+") || str.equals("+") || str.equals("+")) {
            return userInput.OPERATOR;
        } else if (isInt(str) != null) {
            return userInput.INTEGER;
        } else if (isFrac(str) != null) {
            return userInput.FRACTION;
        } else {
            return userInput.INVALID;
        } // chekcs a variatey of possible values to define what type of user input we
          // where handed
    }// whatInput(String)

    /**
     * if given a string which can take the form of an int, fraction or register, returns the correspodning big fraction or null if otherwise
     * @param str // the string we are checking
     * @param register // the register to pull from
     * @return // the resulting big fraction or null if empty
     */
    public static BigFraction getBigFraction(String str, BFRegisterSet register) {
        userInput input = whatInput(str);
        if (input == userInput.REGISTER) {
            return register.get(isLowercase(str));
        } if (input == userInput.INTEGER) {
            return new BigFraction(isInt(str));
        } if (input == userInput.FRACTION) {
            return isFrac(str);
        } else {
            return null;
        } // if elses checking possible valid types of input and returning the corresponding value

    }// getBigFraction
/**
 * the main workhorse, takes in a string that should be a command for a calculator, determines what command it is, and runs the corresponding method
 * @param str  the string to check
 * @param register the register we want to write to and pull from
 * @param calculator the calculator we are currently using
 * @param QuickCalc // is this being used by a quick calculator? (used for print formating)
 */
    public static void execCalculatorCommand(String str, BFRegisterSet register, BFCalculator calculator,
            Boolean QuickCalc) {

        PrintWriter pen = new PrintWriter(System.out, true);
        String[] cmdArray = str.split(" ");
        userInput input = whatInput(cmdArray[0]);

        if (input == userInput.STORE) {
            if (cmdArray.length != 2) {
                System.err.println(
                        "Error: inacurate number of inputs for store if using store only pass in 'STORE char' .");
                System.exit(0);
            } // if unacuate amount of commands is used throw and error
            char registerChar = isLowercase(cmdArray[1]);

            if ((registerChar == '\n')) {
                System.err.println("Error: a lowercase charachter must be the second value.");
                System.exit(0);
            } // if the charachter given is not a charachter raise an error

            register.store(registerChar, calculator.get());
        } else if (getBigFraction(cmdArray[0], register) != null) {
            // the line above checks to see if a value that can be interpreted as a big fraction was first before a space
            // this tells us that we have a sequence separated by spaces and opperators
            calculator.clear();
            String PreviousFunc = "+"; // used to add the first value to the calculator
            for (int i = 0; i < cmdArray.length; i++) {
                // itterate through all values and opperators
                String command = cmdArray[i];
                if (i % 2 == 0) {
                    //if you are even that means you must be a value to add to the calculator
                    BigFraction frac = getBigFraction(command, register);
                    if (frac != null) {
                        if (PreviousFunc.equals("+")) {
                            calculator.add(frac);
                        } else if (PreviousFunc.equals("-")) {
                            calculator.subtract(frac);
                        } else if (PreviousFunc.equals("/")) {
                            calculator.divide(frac);
                        } else if (PreviousFunc.equals("*")) {
                            calculator.multiply(frac);
                        } else {
                            System.err.println("Error: invalid opperator, please use +, -, *, /");
                            System.exit(0);
                        } // check what was previously handed in as an opperator to know what to do with this value
                    } else {
                        System.err.println("Error: a vald value of an int frac or index should follow opperators");
                        System.exit(0);
                    } // raise an error if invalid
                } else {
                    PreviousFunc = command;
                }// else statement if odd, meaning you are an opperator. Simply store this value for the next func to use
            }// end of itteration through all values of function
            if (QuickCalc) {
                pen.println(str + " = " + calculator.get().toString());
            } else {
                pen.println(calculator.get().toString());
            } // print the equation result differently depending on the use of the command line

        } else {
            System.err.println(
                    "Error: invalid calculator input either store, give an equation or call a value");
            System.exit(0);
        }// if you made it this far raise an error as this must be an invalid input
    }//execCalculatorCommand(String, BFRegister, BFCalculator, boolean)

} // class Calculator Utils