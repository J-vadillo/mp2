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
    public enum userInput{
        /** asking you to store a variable */
        STORE,
        /** register: a lowercase value storing a BigFraction */
        REGISTER,
        /** integer: an integer */
        INTEGER,
        /** Fraction: a fraction with the form a/b (no spaces) */
        FRACTION,
        /** OPERATOR: a valid opperator /, *, +, -  */
        OPERATOR,
        /** Invalid, none of the above  */
        INVALID
    }//Enum to store different types of user input


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


    public static BigInteger isInt(String val) {
        try {
            // checking valid integer using parseInt()
            Integer thisInteger = Integer.parseInt(val);
            return BigInteger.valueOf(thisInteger);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }// isLowercase(String)

    public static BigFraction isFrac(String val) {
        if (val.contains("/")){
            String[] numDenom = val.split("/");
            if (numDenom.length !=2){
                return null;
            }
            BigInteger num = isInt(numDenom[0]);
            BigInteger denom = isInt(numDenom[1]);

            if ((num!= null) && (denom != null)){
                return new BigFraction(num, denom);
            }
            else{
                return null;
            }
            
        }
        else{
            return null;
        }
    }// isLowercase(String)


    
    
/**
 * Function to define what input one currently has based off a string
 * @param str
 * @return
 */
    public static userInput whatInput(String str){
        

        if (str.equals("STORE")){
            return userInput.STORE; 
        }//check for store
        else if (isLowercase(str) != '\n'){
            return userInput.REGISTER;
        }// check for register
        else if (str.equals("+") || str.equals("+") || str.equals("+") || str.equals("+")){
            return userInput.OPERATOR;
        }
        else if(isInt(str) != null){
            return userInput.INTEGER;
        }

        else if(isFrac(str) != null){
            return userInput.FRACTION;
        }
        else{
            return userInput.INVALID;
        }

    } 

    public static BigFraction getBigFraction(String str, BFRegisterSet register){
        userInput input = whatInput(str);
        if (input == userInput.REGISTER){
            return register.get(isLowercase(str));
        }

        if (input == userInput.INTEGER){
            return new BigFraction(isInt(str));
        }

        if (input == userInput.FRACTION){
            return isFrac(str);
        }
        else{
            return null;
        }
        

        
    }

    public static void execCalculatorCommand(String str, BFRegisterSet register ,BFCalculator calculator){
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
        }
        else if (getBigFraction(cmdArray[0], register) != null){
            String PreviousFunc = "+";
            for (int i = 0; i<cmdArray.length; i++){
                String command = cmdArray[i];
                if (i%2 == 0){
                    BigFraction frac = getBigFraction(command, register);
                    if (frac!= null){
                        if (PreviousFunc.equals("+")){
                            calculator.add(frac);
                        }
                        else if (PreviousFunc.equals("-")){
                            calculator.subtract(frac);
                        }
                        else if (PreviousFunc.equals("/")){
                            calculator.divide(frac);
                        }
                        else if (PreviousFunc.equals("*")){
                            calculator.multiply(frac);
                        }
                        else{
                            System.err.println("Error: invalid opperator, please use +, -, *, /");
                            System.exit(0);

                        }
                    } 
                    else{
                        System.err.println("Error: a vald value of an int frac or index should follow opperators");
                        System.exit(0); 
                    }
                }
                else{
                    PreviousFunc = command;
                }
            }
            pen.println(calculator.get().toString());
        }
        else{
            System.err.println(
                "Error: invalid calculator input either store, give an equation or call a value");
            System.exit(0);

        }


        
    }




} // class Calculator Utils