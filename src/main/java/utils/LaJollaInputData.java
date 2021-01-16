package utils;

import java.io.IOException;

import static org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient;

public class LaJollaInputData {
    static int v, k, t;

    public static void main(String[] args) throws IOException {
        for (v = 4; v <= 99; v++) {
            for (k = 3; k < v; k++) {
                for (t = 2; t < k; t++) {
                    try {
                        if (binomialCoefficient(v, t) % binomialCoefficient(k, t) == 0 && k <= 25 && t <= 8) {
                            System.out.println(v + "," + k + "," + t);
                        }
                    } catch (org.apache.commons.math3.exception.MathArithmeticException e) {
                        continue;
                    }
                }
            }
        }
    }
}
