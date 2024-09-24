package edu.grinnell.csc207.util;


/**
 * The primary workhorse for other calculators that use the Big fraction tupe
 *
 * @author Samuel A. Rebelsky
 * @author Jana Vadillo
 */
public class BFCalculator {
    /** the last calculated value by the user */
    BigFraction last;

    // these are supposed to be calculators which have objects so we must have an
    // initializer
    public BFCalculator() {
        last = new BigFraction(0, 0);
    } // BigFraction(BigInteger, BigInteger)

    public BFCalculator(BigFraction startVal) {
        last = startVal;
    } // BigFraction(BigInteger, BigInteger)

    /**
     * returns the last used big fraction or 0/0 if unused
     * 
     * @return the last computed big fraction
     */
    public BigFraction get() {
        return (this.last);
    } // get()

    /**
     * subtracts a big fraction from the last value
     * 
     * @param val
     */

    public void subtract(BigFraction val) {
        this.last = this.last.subtract(val);
        return;
    }// subtract(BigFraction)

    /**
     * adds a big fraction from the last value
     * 
     * @param val
     */

    public void add(BigFraction val) {
        this.last = this.last.add(val);
        return;
    }// add(BigFraction)

    /**
     * multipliplies a big fraction with  the last value
     * 
     * @param val
     */

     public void multiply(BigFraction val) {
        this.last = this.last.multiply(val);
        return;
    }// multiply(BigFraction)

    /**
     * divides the last value by val
     * 
     * @param val
     */

     public void divide(BigFraction val) {
        this.last = this.last.divide(val);
        return;
    }// divde (BigFraction)

    /**
     * clears the calculator and sets last to 0/0
     */
    public void clear(){
        this.last = new BigFraction(0,0);
        return;
    }
} // end of class