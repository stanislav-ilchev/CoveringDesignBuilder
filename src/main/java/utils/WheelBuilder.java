package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.IOException;

import static utils.Library.*;
import static utils.TupleBuilder.buildTuples;

public class WheelBuilder {

    public static void main(String[] args) throws IOException {
        int v = 27, k = 6, m = 4, t = 3, b = 1000;
        int kSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, k);
//        int mSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, m);
        int i, j, count = 0;
        int[][] kSets = buildTuples(v, k);
//        int[][] mSets = buildCombinations(v, m);
        int[][] wheel = new int[b][k];
        while (!isEmpty(kSets)) {
            for (i = 0; i < kSetsCount; i++) {
                if (kSets[i] == null) {
                    continue;
                }
                for (j = 0; j < k; j++) {
                    wheel[count][j] = kSets[i][j];
                }
                for (j = 0; j < k; j++) {
                    System.out.print(wheel[count][j] + " ");
                }
                System.out.println();
                for (i = 0; i < kSetsCount; i++) {
                    if (kSets[i] != null && intersection(wheel[count], kSets[i]) >= t) {
                        kSets[i] = null;
                    }
                }
                count++;
            }
        }
    }
}