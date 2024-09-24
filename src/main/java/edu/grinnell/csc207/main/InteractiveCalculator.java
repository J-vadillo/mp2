package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import java.io.PrintWriter;
import java.util.Scanner; // Import the Scanner class

/**
 * @author Jana Vadillo
 *         the primary main function of an interactive caluclator which can run
 *         functions straght from the command line.
 */
public class InteractiveCalculator {
    public static void main(String[] args) {
        BFCalculator calculator = new BFCalculator();
        BFRegisterSet register = new BFRegisterSet();
        while (true) {
            Scanner eyes = new Scanner(System.in); // Create a Scanner object
            String commandLine = eyes.nextLine(); // Read user input
            String[] cmdArray = commandLine.split(" ");

            if (commandLine.equals("QUIT")) {
                break;
            }

            else if (cmdArray[0].equals("STORE")) {
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

            // if 


            eyes.close();
        } // loop to take in input over and over again

    }// main(args)

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

    public static String isValidVal(String str){
        Double tryInt = Double.parseDouble(str);
        if (isLowercase(str)!= '\n'){
            return "register";
        }

        // if (tryInt.isNan()){
        //     return "integer";
        // }
        else{
            return "Invalid";
        }
    
      }

}
