package edu.grinnell.csc207.main;


import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.CalculatorUtil;

 
 /**
 * @author Jana Vadillo
 *         the primary main function of an interactive caluclator which can run
 *         functions straght from the command line.
 */
public class QuickCalculator {
    public static void main(String[] args) {
        BFCalculator calculator = new BFCalculator();
        BFRegisterSet register = new BFRegisterSet();
        for (int i = 0; i < args.length; i++){

            String commandLine = args[i]; // Read user input

            CalculatorUtil.execCalculatorCommand(commandLine, register, calculator, true);

        } // loop to take in input over and over again

    }// main(args)
}   
    