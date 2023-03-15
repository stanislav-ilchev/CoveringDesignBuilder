package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static utils.Library.*;

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

        boolean startFromFile = true;
        int numberOfMatches;
        int i, j, l, rowNumber;
        int[][] subsets = new int[b][k];
        int[] uncoveredTuplesCount = new int[b];
        BufferedReader br;
        Random random = new Random();
        String line = "";
        int row = 0;
        if (!startFromFile) {
            for (i = 0; i < b; i++) {
                rowNumber = random.nextInt(kSetsCount);
                for (j = 0; j < k; j++) {
                    subsets[i][j] = kSets[rowNumber][j];
                }
            }
        } else {
            br = new BufferedReader(new FileReader("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt"));
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
            for (j = 0; j < mSetsCount; j++) {
                for (l = 0; l < b; l++) {
                    if (l == i) {
                        continue;
                    }
                    if (intersection(mSets[j], subsets[l]) >= t) {
                        numberOfMatches++;
                        break;
                    }
                }
            }
//            System.out.println(i + " " + (mSetsCount - numberOfMatches));
            uncoveredTuplesCount[i] = mSetsCount - numberOfMatches;
        }
        Arrays.sort(uncoveredTuplesCount);
        System.out.println(Arrays.toString(uncoveredTuplesCount));
    }
}