package org.utilityclient.utils;

/**
 * @author Sam302
 * @since 2.14
 */
public class MathUtil {
    /**
     * @param numerator The numerator of your fraction
     * @param denominator The denominator of your fraction
     * @return Your fraction as integer
     */
    public static int fraction(int numerator, int denominator) {
        if(denominator == 0) return 0; // Fail safe
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

    /**
     * Calculate the ratio between v1 and v2.
     * @param v1 The first value
     * @param v2 The last value
     * @return The ratio between v1 and v2.
     * @since 2.15 LTS
     */
    public static int ratio(int v1, int v2) {
        return fraction(v2, v1);
    }

    /**
     * Are all values equal?
     * Useful for comparing ratios of triangles or squares.
     * @param v 1 or more Integer values, that should get compared.
     * @return True if all values are equal, false if not.
     */
    public static boolean allEqual(int... v) {
        if(v.length == 0) return true;
        int previous = v[0];
        for (int i = 1; i < v.length; i++) {
            if(v[i] != previous) return false;
            else previous = v[i];
        }
        return true;
    }
}
