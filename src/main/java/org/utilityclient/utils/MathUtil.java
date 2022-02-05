package org.utilityclient.utils;

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
}
