package org.utilityclient.utils;

/**
 * @author GamingCraft
 * @since 2.14
 */
public class MathUtil {
    /**
     * @param numerator The numerator of your fraction
     * @param denominator The denominator of your fraction
     * @return Your fraction as integer
     */
    public static int fraction(int numerator, int denominator) {
        return numerator / denominator;
    }

    /**
     * @param percent The percentage you want to use
     * @return Your percentage as integer
     */
    public static int percent(int percent) {
        return fraction(percent, 100);
    }

    /**
     * @param degree The degree you want to use
     * @return Your degree as integer
     */
    public static int degree(int degree) { return fraction(degree, 360); }
}
