package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import static utils.Library.*;

public class BruteForce {

    public static void main(String[] args) throws IOException {
        int coverage = 0, bestCoverage = 0;
        int kSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, k);
        int mSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, m);
        int intersectionSize;
        int i, j, n, l;
        int[][] kSets = buildCombinations(v, k);
        int[][] mSets = buildCombinations(v, m);
        int[][] wheel = new int[b][k];
        Random random = new Random();
        while (coverage < mSetsCount) {
            coverage = 0;
            for (i = 0; i < b; i++) {
                wheel[i] = kSets[random.nextInt(kSetsCount)];
            }
            for (i = 0; i < mSetsCount; i++) {
                for (j = 0; j < b; j++) {
                    intersectionSize = 0;
                    for (n = 0; n < m; n++) {
                        for (l = 0; l < k; l++) {
                            if (mSets[i][n] == wheel[j][l]) {
                                intersectionSize++;
                                break;
                            }
                        }
                    }
                    if (intersectionSize >= t) {
                        coverage++;
                        break;
                    }
                }
            }
            if (coverage > bestCoverage) {
                System.out.println(coverage);
                bestCoverage = coverage;
                FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
                fileWriter.flush();
                for (i = 0; i < b; i++) {
                    for (j = 0; j < k; j++) {
//                        System.out.print(wheel[i][j] + " ");
                        fileWriter.append(wheel[i][j] + " ");
                    }
//                    System.out.println();
                    fileWriter.append("\n");
                }
                fileWriter.close();
            }
        }
    }
}