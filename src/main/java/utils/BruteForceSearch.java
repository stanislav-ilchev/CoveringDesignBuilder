package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static utils.Library.buildCombinations;

public class BruteForceSearch {

    public static void main(String[] args) throws IOException {
        int v = 27, k = 6, m = 4, t = 3, b = 86;
        int kSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, k);
        int mSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, m);
        int intersectionSize;
        int i, j, n, l, count = 0;
        int[][] kSets = buildCombinations(v, k);
        int[][] mSets = buildCombinations(v, m);
        int[][] wheel = new int[b][k];
        long start = System.currentTimeMillis();
        Collections.shuffle(Arrays.asList(kSets));
        Collections.shuffle(Arrays.asList(mSets));
        Random random = new Random();
        test:
        while (true) {
            count++;
//            System.out.println(count);
            if (count == 30000) {
                System.out.println(System.currentTimeMillis() - start);
                start = System.currentTimeMillis();
                count = 0;
            }
            for (i = 0; i < b; i++) {
                wheel[i] = kSets[random.nextInt(kSetsCount)];
            }
            test2:
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
                        continue test2;
                    }
                }
                continue test;
            }
            System.out.println("A " + t + "-guaranteed wheel was found with a size = " + b);
            FileWriter fileWriter = new FileWriter("C:\\Users\\stanislav.ilchev\\Desktop\\result.txt");
            fileWriter.flush();
            fileWriter.write("A " + t + "-guaranteed wheel was found with a size = " + b + "\n");
            for (i = 0; i < b; i++) {
                for (j = 0; j < k; j++) {
//                    System.out.print(wheel[i][j] + " ");
                    fileWriter.append(wheel[i][j] + " ");
                }
//                System.out.println();
                fileWriter.append("\n");
            }
            fileWriter.close();
            return;
//            b--;
        }
    }
}