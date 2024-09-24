package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * A simple implementation of arbitrary-precision Fractions.
 *
 * @author Samuel A. Rebelsky
 * @author Jana Vadillo
 */
public class BigFraction {
    // +------------------+---------------------------------------------
    // | Design Decisions |
    // +------------------+

    /*
     * (1) Denominators are always positive. Therefore, negative fractions
     * are represented with a negative numerator. Similarly, if a fraction
     * has a negative numerator, it is negative.
     *
     * (2) Fractions are not necessarily stored in simplified form. To
     * obtain a fraction in simplified form, one must call the `simplify`
     * method.
     */

    // +-----------+---------------------------------------------------
    // | Constants |
    // +-----------+


    // +--------+-------------------------------------------------------
    // | Fields |
    // +--------+

    /** The numerator of the fraction. Can be positive, zero or negative. */
    BigInteger num;

    /** The denominator of the fraction. Must be non-negative. */
    BigInteger denom;

    // +--------------+-------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Build a new fraction with numerator num and denominator denom.
     *
     * Warning! Not yet stable.
     *
     * @param numerator
     *                    The numerator of the fraction.
     * @param denominator
     *                    The denominator of the fraction.
     */
    public BigFraction(BigInteger numerator, BigInteger denominator) {
        this.num = numerator;
        this.denom = denominator;
        this.simplify();
    } // BigFraction(BigInteger, BigInteger)
    /**
     * Builds a new fraction out of only a numerator
     * @param numerator the numerator to be used.
     */
    public BigFraction(BigInteger numerator) {
        this.num = numerator;
        this.denom = BigInteger.valueOf(1);
    } // BigFraction(BigInteger)


    /**
     * Build a new fraction with numerator num and denominator denom.
     *
     * Warning! Not yet stable.
     *
     * @param numerator
     *                    The numerator of the fraction.
     * @param denominator
     *                    The denominator of the fraction.
     */
    public BigFraction(int numerator, int denominator) {
        this.num = BigInteger.valueOf(numerator);
        this.denom = BigInteger.valueOf(denominator);
        this.simplify();
    } // BigFraction(int, int)

    /**
     * Build a new fraction by parsing a string.
     *
     * @param str
     *            The fraction in string form
     */
    public BigFraction(String str) {
        int split = str.indexOf("/");
        this.num = new BigInteger(str.substring(0, split));
        this.denom = new BigInteger(str.substring(split + 1));
        this.simplify();
    } // BigFraction

    // +---------+------------------------------------------------------
    // | Methods |
    // +---------+

    /**
     * Express this fraction as a double.
     *
     * @return the fraction approxiamted as a double.
     */
    public double doubleValue() {
        return this.num.doubleValue() / this.denom.doubleValue();
    } // doubleValue()

    /**
     * returns a new simplified version of the fraction
     * 
     * @return BigFraction simplified
     */
    public void simplify() {
        BigInteger newNum = this.num;
        BigInteger newDenom = this.denom;
        BigInteger commonMod = newNum.gcd(newDenom);
        if (commonMod.intValue() != 0) {
            newNum = newNum.divide(commonMod);
            newDenom = newDenom.divide(commonMod);
        } else {
            newNum = new BigInteger("0");
            newDenom = new BigInteger("0");
        }

        this.num = newNum;
        this.denom = newDenom;
    } // simplify()

    /**
     * Add another faction to this fraction.
     *
     * @param addend
     *               The fraction to add.
     *
     * @return the result of the addition.
     */
    public BigFraction add(BigFraction addend) {
        BigInteger resultNumerator;
        BigInteger resultDenominator;

        // The denominator of the result is the product of this object's
        // denominator and addend's denominator
        resultDenominator = this.denom.multiply(addend.denom);
        // The numerator is more complicated
        resultNumerator = (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));

        // Return the computed value
        return new BigFraction(resultNumerator, resultDenominator);
    } // add(BigFraction)

    /**
     * subtracts two fractions
     * @param subtracted value to subtract
     * @return neew simplified subtracted fraction
     */

    public BigFraction subtract(BigFraction subtracted) {
        BigInteger resultNumerator;
        BigInteger resultDenominator;

        // The denominator of the result is the product of this object's
        // denominator and addend's denominator
        resultDenominator = this.denom.multiply(subtracted.denom);
        // The numerator is more complicated
        resultNumerator = (this.num.multiply(subtracted.denom)).subtract(subtracted.num.multiply(subtracted.denom));

        // Return the computed value
        return new BigFraction(resultNumerator, resultDenominator);
    } // subtract(BigFraction)


    /**
     * multiplies two fractions by each other
     * 
     * @param otherFraction other fraction to be multiplied
     * @return new fraction that was multiplied
     */

    public BigFraction multiply(BigFraction otherFraction) {
        BigInteger resultNumerator;
        BigInteger resultDenominator;

        resultDenominator = this.denom.multiply(otherFraction.denom);
        resultNumerator = this.denom.multiply(otherFraction.num);

        return new BigFraction(resultNumerator, resultDenominator);
    }// multiply(BigFraction)

    /**
     * divides a fraction by the other fraction
     * 
     * @param otherFraction fraction to divide by
     * @return new simplified valuie
     */

    public BigFraction divide(BigFraction otherFraction) {
        BigInteger resultNumerator;
        BigInteger resultDenominator;

        resultDenominator = this.denom.multiply(otherFraction.num);
        resultNumerator = this.denom.multiply(otherFraction.denom);

        return new BigFraction(resultNumerator, resultDenominator);
    }// divide(BigFraction)

    /**
     * finds and returns the fractional part of the fraction, ie the bit that is
     * larger than the remainder
     * 
     * @return a new bigfractuion that is just the fractional bits.
     */

    public BigFraction fractional() {
        BigInteger newNumerator = this.num;
        while (newNumerator.compareTo(this.denominator()) == 1) {
            newNumerator = this.num.subtract(this.denom);
        }

        return (new BigFraction(newNumerator, this.denom));

    }// Fractional()

    /**
     * Get the denominator of this fraction.
     *
     * @return the denominator
     */
    public BigInteger denominator() {
        return this.denom;
    } // denominator()

    /**
     * Get the numerator of this fraction.
     *
     * @return the numerator
     */
    public BigInteger numerator() {
        return this.num;
    } // numerator()

    /**
     * Convert this fraction to a string for ease of printing.
     *
     * @return a string that represents the fraction.
     */
    public String toString() {
        // Special case: It's zero
        if (this.num.equals(BigInteger.ZERO)) {
            return "0";
        } // if it's zero
        this.simplify();

        // Lump together the string represention of the numerator,
        // a slash, and the string representation of the denominator
        // return this.num.toString().concat("/").concat(this.denom.toString());
        return this.num + "/" + this.denom;
    } // toString()
} // class BigFraction
