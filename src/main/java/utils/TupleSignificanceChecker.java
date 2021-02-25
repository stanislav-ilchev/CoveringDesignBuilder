package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static utils.Library.buildCombinations;

public class TupleSignificanceChecker {

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
        int v = 27;
        int k = 6;
        int m = 4;
        int t = 3;
        int b = 86;
        int kTuplesCount = (int) CombinatoricsUtils.binomialCoefficient(v, k);
        int mTuplesCount = (int) CombinatoricsUtils.binomialCoefficient(v, m);
        boolean startFromFile = true;
        int numberOfMatches;
        int i, j, l, rowNumber;
        int[][] kTuples = buildCombinations(v, k);
        int[][] mTuples = buildCombinations(v, m);
        int[][] subsets = new int[b][k];
        int[] uncoveredTuplesCount = new int[b];
        BufferedReader br;
        Random random = new Random();
        String line = "";
        int row = 0;
        if (!startFromFile) {
            for (i = 0; i < b; i++) {
                rowNumber = random.nextInt(kTuplesCount);
                for (j = 0; j < k; j++) {
                    subsets[i][j] = kTuples[rowNumber][j];
                }
            }
        } else {
            br = new BufferedReader(new FileReader("C:\\Users\\stanislav.ilchev\\Desktop\\input.txt"));
            try {
                line = br.readLine();
                while (line != null) {
                    for (i = 0; i < k; i++) {
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
        for (i = 0; i < b; i++) {
            numberOfMatches = 0;
            for (j = 0; j < mTuplesCount; j++) {
                for (l = 0; l < b; l++) {
                    if (l == i) {
                        continue;
                    }
                    if (intersection(mTuples[j], subsets[l]) >= t) {
                        numberOfMatches++;
                        break;
                    }
                }
            }
//            System.out.println(i + " " + (mTuplesCount - numberOfMatches));
            uncoveredTuplesCount[i] = mTuplesCount - numberOfMatches;
        }
        Arrays.sort(uncoveredTuplesCount);
        System.out.println(Arrays.toString(uncoveredTuplesCount));
    }
}