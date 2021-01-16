package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import static utils.TupleBuilder.buildTuples;

public class Assembler {

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

    public static boolean isEmpty(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        int v = 27;
        int k = 6;
        int m = 4;
        int t = 3;
        boolean readFile = false;
        boolean useHeuristics = true;
        int sampleSize = 10000;
        int[][] kTuples = buildTuples(v, k);
        int[][] mTuples = buildTuples(v, m);
        int[][] result = new int[10000][k];
        int kTuplesCount = (int) CombinatoricsUtils.binomialCoefficient(v, k);
        int mTuplesCount = (int) CombinatoricsUtils.binomialCoefficient(v, m);
        int count = (int) CombinatoricsUtils.binomialCoefficient(k, m);
        int nextSize, bestNextSize;
        Random random = new Random();
        int a, i, j, b, l, counter = 0, numberOfMatches;
        String line;
        int row = 0;
        int[][] subsets = new int[mTuplesCount][];
        for (i = 0; i < mTuplesCount; i++) {
            subsets[i] = new int[m];
        }
        if (readFile) {
            mTuplesCount = 200;
            subsets = new int[mTuplesCount][];
            for (i = 0; i < mTuplesCount; i++) {
                subsets[i] = new int[m];
            }
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\stanislav.ilchev\\Desktop\\input.txt"));
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
                br.close();
            }
        } else {
            for (i = 0; i < mTuplesCount; i++) {
                for (j = 0; j < m; j++) {
                    subsets[i][j] = mTuples[i][j];
                }
            }
        }
//        Collections.shuffle(Arrays.asList(subsets));
        if (m != t && readFile == false) {
            count = 0;
            for (j = 0; j < mTuplesCount; j++) {
                if (intersection(kTuples[0], subsets[j]) >= t) {
                    count++;
                }
            }
        }

        System.out.println(count);
        while (!isEmpty(subsets)) {
            for (i = 0; i < kTuplesCount; i++) {
                numberOfMatches = 0;
                for (j = 0; j < mTuplesCount; j++) {
                    if (subsets[j] != null && intersection(kTuples[i], subsets[j]) >= t) {
                        numberOfMatches++;
                    }
                }
                if (numberOfMatches >= count) {
                    for (b = 0; b < k; b++) {
                        System.out.print(kTuples[i][b] + " ");
                        result[counter][b] = kTuples[i][b];
                    }
                    counter++;
                    System.out.println();
                    for (a = 0; a < mTuplesCount; a++) {
                        if (subsets[a] != null && intersection(kTuples[i], subsets[a]) >= t) {
                            subsets[a] = null;
                        }
                    }
                }
            }
            if (useHeuristics) {
                bestNextSize = 0;
                for (l = 0; l < kTuplesCount/*sampleSize*/; l++) {
                    result[counter] = kTuples[l];//kTuples[random.nextInt(kTuplesCount)];
                    nextSize = 0;
                    for (a = 0; a < mTuplesCount; a++) {
                        if (subsets[a] != null && intersection(result[counter], subsets[a]) >= t) {
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