package edu.grinnell.csc207.util;
// import java.math.BigInteger;

// import edu.grinnell.csc207.util.BigFraction;

/**
 * a register used to store multiple big fractions at once :)
 *
 * @author Jana Vadillo
 */
public class BFRegisterSet {
    /** length of alphabet */
    int LENGTH_OF_ALPHABET = 26;
    /** primary register, arrya of big fractions */
    BigFraction[] fullRegister; 

    public BFRegisterSet(char register, BigFraction val) {
        this.fullRegister = new BigFraction[LENGTH_OF_ALPHABET];
        return;
    }

    /**
     * function used to turn letters into their corresponding integers, only works
     * lowercase.
     *
     * @param letter the letter to be inputed
     * @return returns a corresponding integer
     */
    private static int letter2int(char letter) {
        int base = (int) 'a';
        int n = (int) letter;
        n -= base;
        return n; // takes in a letter and returns the corresponding integer
    } // letter2int(char);

    /**
     * used to store a particular value at a set register.
     * 
     * @param register the adress at which to store it at
     * @param val      the value to store
     */

    public void store(char register, BigFraction val) {
        int address = letter2int(register);
        this.fullRegister[address] = val;
        return;
    }

    /**
     * used to get a value from a particular register address
     * 
     * @param register the register at which to find it at
     * @return BigFraction that is desired.
     */

    public BigFraction get(char register) {
        int address = letter2int(register);
        BigFraction value = this.fullRegister[address];
        return (value);
    }// get(char)

} // class BFRegisterset
