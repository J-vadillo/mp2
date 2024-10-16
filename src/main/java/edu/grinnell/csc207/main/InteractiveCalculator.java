package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.CalculatorUtil;
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

            if (commandLine.equals("QUIT")) {
                break;
            }
            CalculatorUtil.execCalculatorCommand(commandLine, register, calculator);


            // if 


            eyes.close();
        } // loop to take in input over and over again

    }// main(args)
}
