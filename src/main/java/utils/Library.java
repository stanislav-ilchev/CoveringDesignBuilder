package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import static org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient;
import static org.apache.commons.math3.util.CombinatoricsUtils.combinationsIterator;
import static java.lang.System.*;

public class Library {

    public static int v = 27, k = 6, m = 4, t = 3, b = 86;
    public static int kSetsCount = (int) binomialCoefficient(v, k);
    public static int mSetsCount = (int) binomialCoefficient(v, m);
    public static int[][] kSets = buildCombinations(v, k);
    public static int[][] mSets = buildCombinations(v, m);
    public static Random random = new Random();

    public static int[][] buildCombinations(int n, int k) {
        int kSetsCount = (int) binomialCoefficient(n, k);
        Iterator<int[]> iterator = combinationsIterator(n, k);
        int subsetNumber = 0;
        int[][] subsets = new int[kSetsCount][k];
        while (iterator.hasNext()) {
            subsets[subsetNumber] = iterator.next();
            subsetNumber++;
        }
//        rename(subsets, 1);
        return subsets;
    }

    public static int intersection(int[] set1, int[] set2) {
        int i, j;
        int intersectionSize = 0;
        for (i = 0; i < set1.length; i++) {
            for (j = 0; j < set2.length; j++) {
                if (set1[i] == set2[j]) {
                    intersectionSize++;
                    break;
                }
            }
        }
        return intersectionSize;
    }

    public static void print(int[][] array) {
        int i, j;
        for (i = 0; i < b; i++) {
            for (j = 0; j < k; j++) {
                out.print(array[i][j] + " ");
            }
            out.println();
        }
    }

    public static boolean isEmpty(int[][] array) {
        int i;
        for (i = 0; i < array.length; i++) {
            if (array[i] != null) {
                return false;
            }
        }
        return true;
    }

    public static int[][] readFromFile(String fileName) throws IOException {
        int i;
        int[][] subsets = new int[b][k];
        String line;
        String[] lineArray;
        int row = 0;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            line = br.readLine();
            while (line != null) {
                lineArray = line.replaceAll(",", " ").replaceAll("\\s{2,}", " ").trim().split(" ");
                for (i = 0; i < k; i++) {
                    subsets[row][i] = Integer.parseInt(lineArray[i]);
                }
                line = br.readLine();
                row++;
            }
        } finally {
            br.close();
        }
        return subsets;
    }

    public static int[][] readFromFile() throws IOException {
        int i;
        int[][] subsets = new int[b][k];
        String line;
        String[] lineArray;
        int row = 0;
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\stanislav.ilchev\\Desktop\\input.txt"));
        try {
            line = br.readLine();
            while (line != null) {
                lineArray = line.replaceAll(",", " ").replaceAll("\\s{2,}", " ").trim().split(" ");
                for (i = 0; i < k; i++) {
                    subsets[row][i] = Integer.parseInt(lineArray[i]);
                }
                line = br.readLine();
                row++;
            }
        } finally {
            br.close();
        }
        return subsets;
    }

    public static void writeToFile(String fileName, int[][] array) throws IOException {
        int i, j;
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.flush();
        for (i = 0; i < b; i++) {
            for (j = 0; j < k; j++) {
                fileWriter.append(array[i][j] + " ");
            }
            fileWriter.append("\n");
        }
        fileWriter.close();
    }

    public static String generateRandomNeighbor(int[][] array) {
        int i;
        int row = 0, column = 0, newNumber = 0, oldNumber;
        boolean end = false;
        loop:
        while (!end) {
            row = random.nextInt(b);
            column = random.nextInt(k);
            newNumber = random.nextInt(v);
            for (i = 0; i < k; i++) {
                if (array[row][i] == newNumber) {
                    continue loop;
                }
                end = true;
            }
        }
        oldNumber = array[row][column];
        array[row][column] = newNumber;
        return row + "," + column + "," + oldNumber;
    }

    public static int calculateMatches(int[][] array) {
        int i, j;
        int numberOfMatches = 0;
        for (i = 0; i < mSets.length; i++) {
            for (j = 0; j < array.length; j++) {
                if (intersection(mSets[i], array[j]) >= t) {
                    numberOfMatches++;
                    break;
                }
            }
        }
        return numberOfMatches;
    }

    public static void rename(int[][] array, int change) {
        int i, j;
        for (i = 0; i < b; i++) {
            for (j = 0; j < k; j++) {
                array[i][j] += change;
            }
        }
    }

    public static int[][] generateRandomSubsets(int b, int k) {
        int i, j;
        int kSetsCount = (int) binomialCoefficient(v, k);
        int[][] subsets = new int[b][k];
        int[][] kSets = buildCombinations(v, k);
        int randomNumber;
        for (i = 0; i < b; i++) {
            randomNumber = random.nextInt(kSetsCount);
            for (j = 0; j < k; j++) {
                subsets[i][j] = kSets[randomNumber][j];
            }
        }
        return subsets;
    }

    public static boolean isAnyNumberPresentAtMostNTimes(int[][] array, int n, int length, int allow) {
        int i, j, l, counter = 0;
        int[] frequencies = new int[v];
        for (i = 0; i <= length; i++) {
            for (j = 0; j < k; j++) {
                for (l = 0; l < v; l++) {
                    if (array[i][j] == l) {
                        frequencies[l] += 1;
                        if (frequencies[l] > n + 1) {
                            return false;
                        }
                        break;
                    }
                }
            }
        }
        for (i = 0; i < v; i++) {
            if (frequencies[i] > n) {
                counter++;
            }
        }
        if (counter > allow) {
            return false;
        }
        return true;
    }

    public static boolean areAnyTwoNumbersInAtMostOneKSet(int[][] array, int length) {
        int i, j;
        for (i = 0; i <= length; i++) {
            for (j = i + 1; j <= length; j++) {
                if (intersection(array[i], array[j]) > 2) {
//                    System.out.println(i + " " + j);
                    return false;
                }
            }
        }
        return true;
    }

}
