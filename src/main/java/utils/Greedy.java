package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.IOException;

import static utils.Library.*;

public class Greedy {

    public static void main(String[] args) throws IOException {
        int v = 27, k = 6, m = 4, t = 3, i, j, l, n, count = 0, bestSetNumber = 0, numberOfMatches, bestNumberOfMatches = 0;
        int[][] kSets = buildCombinations(v, k);
        int[][] mSets = buildCombinations(v, m);
        int[][] result = new int[1000][k];
        int kSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, k);
        int mSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, m);
        while (bestNumberOfMatches != mSetsCount) {
            for (i = 0; i < kSetsCount; i++) {
//                if (i % 1000 == 0) {
//                    System.out.println(i);
//                }
                result[count] = kSets[i];
                numberOfMatches = 0;
                for (j = 0; j < mSetsCount; j++) {
                    for (l = 0; l <= count; l++) {
                        if (intersection(mSets[j], result[l]) >= t) {
                            numberOfMatches++;
                            break;
                        }
                    }
                }
                if (numberOfMatches > bestNumberOfMatches) {
                    bestNumberOfMatches = numberOfMatches;
                    bestSetNumber = i;
                }
            }
            result[count] = kSets[bestSetNumber];
            for (n = 0; n < k; n++) {
                System.out.print(result[count][n] + " ");
            }
            System.out.println();
            count++;
        }
    }
}