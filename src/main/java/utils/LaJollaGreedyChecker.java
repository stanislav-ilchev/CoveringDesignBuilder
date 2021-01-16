package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static utils.Library.buildCombinations;

public class LaJollaGreedyChecker {

    public static int intersection(int[] list1, int[] list2) {
        int intersectionSize = 0;
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if (list1[i] == list2[j]) {
                    intersectionSize++;
                    break;
                }
            }
        }
        return intersectionSize;
    }

    public static void main(String[] args) throws IOException {
        int ticketNumberLimit = 200000, kTuplesCount, tTuplesCount, rowCount = 1513;
        boolean readFile = true;
        int i, j, m, count, counter = 0;
        int[][] subsets = new int[rowCount][];
        int kTuples[][];
        int tTuples[][];
        int[][] result = new int[ticketNumberLimit][];
        String line;
        int row = 0;
        for (i = 0; i < rowCount; i++) {
            subsets[i] = new int[3];
        }
        if (readFile) {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\stanislav.ilchev\\Desktop\\input.txt"));
            try {
                line = br.readLine();
                while (line != null) {
                    for (i = 0; i < 3; i++) {
                        if (line.contains(",")) {
                            subsets[row][i] = Byte.parseByte(line.replaceAll("\\s", "").split(",")[i]);
                        } else {
                            subsets[row][i] = Integer.parseInt(line.replaceAll("\\s{2,}", " ").trim().replace(" ", ",").split(",")[i]);
                        }
                    }
                    line = br.readLine();
                    row++;

                }
            } finally {
                br.close();
            }
        }
        loop3:
        for (m = 0; m < rowCount; m++) {
            try {
                if (CombinatoricsUtils.binomialCoefficient(subsets[m][0], subsets[m][1]) > 1000000000) {
//                    counter++;
//                    System.out.println(counter + ": Too big!");
//                    continue;
                }
            } catch (org.apache.commons.math3.exception.MathArithmeticException e) {
                counter++;
                System.out.println(counter + ": Exceptionally big!");
                continue;
            }
            counter++;
            System.out.println(counter + ": " + subsets[m][0] + ", " + subsets[m][1] + ", " + subsets[m][2]);
            count = 1;
            try {
                kTuples = buildCombinations(subsets[m][0], subsets[m][1]);
                tTuples = buildCombinations(subsets[m][0], subsets[m][2]);
            } catch (java.lang.OutOfMemoryError e) {
                counter++;
                System.out.println(counter + ": OutOfMemoryError!");
                continue;
            }
            kTuplesCount = (int) CombinatoricsUtils.binomialCoefficient(subsets[m][0], subsets[m][1]);
            tTuplesCount = (int) CombinatoricsUtils.binomialCoefficient(subsets[m][0], subsets[m][2]);
            result[0] = kTuples[0];
//                for (k = 0; k < subsets[m][1]; k++) {
//                    System.out.print(result[0][k] + " ");
//                }
//                System.out.println();
            loop1:
            for (i = 1; i < kTuplesCount; i++) {
                for (j = 0; j < count; j++) {
                    if (intersection(kTuples[i], result[j]) >= subsets[m][2]) {
                        continue loop1;
                    }
                }
                result[count] = kTuples[i];
//                    for (k = 0; k < subsets[m][1]; k++) {
//                        System.out.print(result[count][k] + " ");
//                    }
//                    System.out.println();
                count++;
            }
            loop2:
            for (i = 0; i < tTuplesCount; i++) {
                for (j = 0; j < count; j++) {
                    if (intersection(tTuples[i], result[j]) >= subsets[m][2]) {
                        continue loop2;
                    }
                }
                continue loop3;
            }
            System.out.println("Found!");
        }
    }
}