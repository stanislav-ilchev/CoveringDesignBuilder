package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static utils.Library.*;

public class HillClimbingSteepestAscent {

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
        int subsetSize = b - 1;
        boolean readFile = true, removeCombination = true;
        byte g, newNumber, bestNewNumber = 0;
        int numberOfMatches = 0, maxNumberOfMatches = -1, biggestMaxNumberOfMatches = 0;
        int elementsToChange = 1, perturbation = 0;
        int bestRow = 0;
        int i, j, l, o, n, count = 0, rowNumber = 0, positionInRow = 0, bestRowNumber = 0, bestPositionInRow = 0;
        int[][] kTuples = buildCombinations(v, k);
        int[][] mTuples = buildCombinations(v, m);
        int[][] subsets = new int[b][k];
        int[][] subsets2 = new int[subsetSize][k];
        BufferedReader br;
        Random random = new Random();
        System.out.println("Checking if this set is a wheel that guarantees a " + t + "-match...");
        String line = "";
        int row = 0;
        if (!readFile) {
            for (i = 0; i < subsetSize; i++) {
                rowNumber = random.nextInt(kSetsCount);
                for (j = 0; j < k; j++) {
                    subsets[i][j] = kTuples[rowNumber][j];
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
        if (removeCombination) {
            for (i = 0; i < b; i++) {
                numberOfMatches = 0;
                for (j = 0; j < mSetsCount; j++) {
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
                if (maxNumberOfMatches < numberOfMatches) {
                    maxNumberOfMatches = numberOfMatches;
                    bestRow = i;
                    System.out.println(bestRow + " " + maxNumberOfMatches);
                }
            }
        }
        for (i = 0; i < b; i++) {
            if (i == bestRow) {
                continue;
            }
            for (j = 0; j < k; j++) {
                subsets2[count][j] = subsets[i][j];
            }
            count++;
        }
        for (i = 0; i < perturbation; i++) {
            rowNumber = random.nextInt(subsetSize);
            positionInRow = random.nextInt(k);
            newNumber = (byte) (random.nextInt(v) + 1);
            while (subsets2[rowNumber][0] == newNumber || subsets2[rowNumber][1] == newNumber || subsets2[rowNumber][2] == newNumber || subsets2[rowNumber][3] == newNumber ||
                    subsets2[rowNumber][4] == newNumber || subsets2[rowNumber][5] == newNumber) {
                newNumber = (byte) (random.nextInt(v) + 1);
            }
            subsets2[rowNumber][positionInRow] = newNumber;
        }
        while (true) {
            if (maxNumberOfMatches == biggestMaxNumberOfMatches) {
                return;
            }
            if (maxNumberOfMatches > biggestMaxNumberOfMatches) {
                biggestMaxNumberOfMatches = maxNumberOfMatches;
                System.out.println(maxNumberOfMatches);
                FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
                fileWriter.flush();
                fileWriter.write("No " + t + "-match-guaranteed wheel was found!" + "\n" + "Number of matches is " + maxNumberOfMatches + "\n");
                for (j = 0; j < subsetSize; j++) {
//                        System.out.println(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5]);
                    fileWriter.append(subsets2[j][0] + "," + subsets2[j][1] + "," + subsets2[j][2] + "," + subsets2[j][3] + "," + subsets2[j][4] + "," + subsets2[j][5] + "\n");
                }
                fileWriter.close();
            }
            for (o = 0; o < subsetSize; o++) {
                for (n = 0; n < k; n++) {
                    for (l = 1; l <= v; l++) {
                        numberOfMatches = 0;
                        rowNumber = o;
                        positionInRow = n;
                        newNumber = (byte) l;
                        if (subsets2[rowNumber][0] == newNumber || subsets2[rowNumber][1] == newNumber || subsets2[rowNumber][2] == newNumber || subsets2[rowNumber][3] == newNumber ||
                                subsets2[rowNumber][4] == newNumber || subsets2[rowNumber][5] == newNumber) {
                            continue;
                        }
                        g = (byte) subsets2[rowNumber][positionInRow];
                        subsets2[rowNumber][positionInRow] = newNumber;
                        for (i = 0; i < mSetsCount; i++) {
                            for (j = 0; j < subsetSize; j++) {
                                if (intersection(mTuples[i], subsets2[j]) >= t) {
                                    numberOfMatches++;
                                    break;
                                }
                            }
                        }
                        if (numberOfMatches > maxNumberOfMatches) {
                            System.out.println(numberOfMatches);
                            maxNumberOfMatches = numberOfMatches;
                            bestRowNumber = rowNumber;
                            bestPositionInRow = positionInRow;
                            bestNewNumber = newNumber;
                        }
                        subsets2[rowNumber][positionInRow] = g;
                    }
                }
            }
            subsets2[bestRowNumber][bestPositionInRow] = bestNewNumber;
        }
    }
}