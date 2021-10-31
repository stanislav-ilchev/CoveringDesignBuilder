package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static utils.TupleBuilder.buildTuples;

public class RedundancyChecker {

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
        int lotteryNumbers = 27;
        int pickedNumbers = 6;
        int ticketNumberLimit = 86;
        int possibleCombinations = (int) CombinatoricsUtils.binomialCoefficient(lotteryNumbers, pickedNumbers);
        int fourTuplesCount = (int) CombinatoricsUtils.binomialCoefficient(lotteryNumbers, 4);
        int subsetSize = ticketNumberLimit;
        boolean readFile = false;
        byte a, b, c, d, e, f, g = 0, h;
        int i, j, k, l, m, n, o, count = 0, numbersToMatch = 3, rowNumber, numberOfMatches, initialNumberOfMatches = 0;
        int[][] combinations = buildTuples(lotteryNumbers, 6);
        int[][] fourTuples = buildTuples(lotteryNumbers, 4);
        int[][] subsets = new int[ticketNumberLimit][6];
        int[][] subsets2 = new int[subsetSize][6];
        BufferedReader br;
        Random random = new Random();
        System.out.println("Checking if this set is a wheel that guarantees a " + numbersToMatch + "-match...");
        String line = "";
        int row = 0;
        if (!readFile) {
            for (i = 0; i < subsetSize; i++) {
                rowNumber = random.nextInt(possibleCombinations);
                for (j = 0; j < 6; j++) {
                    subsets[i][j] = combinations[rowNumber][j];
                }
            }
        } else {
            br = new BufferedReader(new FileReader("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt"));
            try {
                line = br.readLine();
                while (line != null) {
                    for (i = 0; i < 6; i++) {
                        if (line.contains(",")) {
                            subsets[row][i] = Byte.parseByte(line.replaceAll("\\s", "").split(",")[i]);
                        } else {
                            subsets[row][i] = Byte.parseByte(line.replaceAll("\\s{2,}", " ").trim().replace(" ", ",").split(",")[i]);
                        }
                    }
                    line = br.readLine();
                    row++;
                }
            } finally {
                br.close();
            }
        }
        for (j = 0; j < fourTuplesCount; j++) {
            for (k = 0; k < subsetSize; k++) {
                if (intersection(fourTuples[j], subsets[k]) >= numbersToMatch) {
                    initialNumberOfMatches++;
                    break;
                }
            }
        }
        System.out.println(initialNumberOfMatches);
        for (i = 0; i < subsetSize; i++) {
            numberOfMatches = 0;
            for (j = 0; j < fourTuplesCount; j++) {
                for (k = 0; k < subsetSize; k++) {
                    if (k == i) {
                        continue;
                    }
                    if (intersection(fourTuples[j], subsets[k]) >= numbersToMatch) {
                        numberOfMatches++;
                        break;
                    }
                }
            }
//            System.out.println(numberOfMatches);
            if (numberOfMatches == initialNumberOfMatches) {
                System.out.println(i);
            }
        }
    }
}