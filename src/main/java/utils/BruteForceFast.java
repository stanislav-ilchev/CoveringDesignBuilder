package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static utils.Library.buildCombinations;
import static utils.Library.intersection;

public class BruteForceFast {

    public static void main(String[] args) throws IOException {
        int v = 27, k = 6, m = 4, t = 3, b = 300;
        int kSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, k);
        int mSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, m);
        int i, j, count = 0;
        int[][] kSets = buildCombinations(v, k);
        int[][] mSets = buildCombinations(v, m);
        int[] wheel = new int[b];
        byte[][] intersections = new byte[mSetsCount][kSetsCount];
        long start = System.currentTimeMillis();
        Collections.shuffle(Arrays.asList(kSets));
        Collections.shuffle(Arrays.asList(mSets));
        Random random = new Random();
        for (i = 0; i < mSetsCount; i++) {
//            if (i % 1000 == 0) {
//                System.out.println(i);
//            }
            for (j = 0; j < kSetsCount; j++) {
                intersections[i][j] = (byte) intersection(mSets[i], kSets[j]);
            }
        }
        test:
        while (true) {
//            count++;
////            System.out.println(count);
//            if (count == 200000) {
//                System.out.println(System.currentTimeMillis() - start);
//                start = System.currentTimeMillis();
//                count = 0;
//            }
            for (i = 0; i < b; i++) {
                wheel[i] = random.nextInt(kSetsCount);
            }
            test2:
            for (i = 0; i < mSetsCount; i++) {
                for (j = 0; j < b; j++) {
                    if (intersections[i][wheel[j]] >= t) {
                        continue test2;
                    }
                }
                continue test;
            }
            System.out.println("A " + t + "-guaranteed wheel was found with a size = " + b);
            FileWriter fileWriter = new FileWriter("C:\\Users\\stanislav.ilchev\\Desktop\\result.txt");
            fileWriter.flush();
//            fileWriter.write("A " + t + "-guaranteed wheel was found with a size = " + b + "\n");
            for (i = 0; i < b; i++) {
                for (j = 0; j < k; j++) {
//                    System.out.print(wheel[i] + " ");
                    fileWriter.append(kSets[wheel[i]][j] + " ");
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