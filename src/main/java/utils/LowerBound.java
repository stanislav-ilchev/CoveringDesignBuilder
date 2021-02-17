package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.IOException;

public class LowerBound {

    public static void main(String[] args) throws IOException {
        int v = 27, k = 6, m = 4, t = 3, b = 15;
        int kTuplesCount = (int) CombinatoricsUtils.binomialCoefficient(v, k);
        int mTuplesCount = (int) CombinatoricsUtils.binomialCoefficient(v, m);
        System.out.println(mTuplesCount - b * 435);
    }
}
