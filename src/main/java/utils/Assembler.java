package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import static utils.Library.*;

public class Assembler {

    public static void main(String[] args) throws IOException {
        boolean startFromFile = false;
        boolean useHeuristics = false;
        int sampleSize = 10000;
        int[][] result = new int[1000][k];
        byte[][] intersections = new byte[mSetsCount][kSetsCount];
        int count = (int) CombinatoricsUtils.binomialCoefficient(k, m);
        int nextSize, bestNextSize, randomNumber;
        Random random = new Random();
        int a, i, j, b, l, counter = 0, numberOfMatches;
        String line;
        int row = 0;
        int[][] subsets = new int[mSetsCount][m];
        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < kSetsCount; j++) {
                intersections[i][j] = (byte) intersection(mSets[i], kSets[j]);
            }
        }
        if (startFromFile) {
            mSetsCount = 1292;
            subsets = new int[mSetsCount][];
            for (i = 0; i < mSetsCount; i++) {
                subsets[i] = new int[m];
            }
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt"));
            try {
                line = br.readLine();
                while (line != null) {
                    for (i = 0; i < m; i++) {
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
                for (i = 0; i < mSetsCount; i++) {
                    for (j = 0; j < kSetsCount; j++) {
                        intersections[i][j] = (byte) intersection(subsets[i], kSets[j]);
                    }
                }
                br.close();
            }
        } else {
            for (i = 0; i < mSetsCount; i++) {
                subsets[i] = mSets[i];
            }
        }
        if (m != t && startFromFile == false) {
            count = 0;
            for (j = 0; j < mSetsCount; j++) {
                if (intersection(kSets[0], subsets[j]) >= t) {
                    count++;
                }
            }
        }

        System.out.println(count);
        while (!isEmpty(subsets)) {
            for (i = 0; i < kSetsCount; i++) {
                numberOfMatches = 0;
                for (j = 0; j < mSetsCount; j++) {
                    if (subsets[j] != null && intersections[j][i] >= t) {
                        numberOfMatches++;
                    }
                }
                if (numberOfMatches >= count) {
                    for (b = 0; b < k; b++) {
                        System.out.print(kSets[i][b] + " ");
                        result[counter][b] = kSets[i][b];
                    }
                    counter++;
                    System.out.println();
                    for (a = 0; a < mSetsCount; a++) {
                        if (subsets[a] != null && intersections[a][i] >= t) {
                            subsets[a] = null;
                        }
                    }
                }
            }
            if (useHeuristics) {
                bestNextSize = 0;
                for (l = 0; l < /*kSetsCount*/sampleSize; l++) {
                    randomNumber = random.nextInt(kSetsCount);
                    result[counter] = /*kSets[l];*/kSets[randomNumber];
                    nextSize = 0;
                    for (a = 0; a < mSetsCount; a++) {
                        if (subsets[a] != null && intersections[a][randomNumber] >= t) {
                            nextSize++;
                        }
                    }
                    if (bestNextSize < nextSize) {
                        bestNextSize = nextSize;
                    }
                }
                if (bestNextSize == 0) {
                    count--;
                } else {
                    count = bestNextSize;
                }
            } else {
                count--;
            }
            System.out.println(count);
            if (count == 0) {
                break;
            }
        }
        System.out.println(counter);
    }
}