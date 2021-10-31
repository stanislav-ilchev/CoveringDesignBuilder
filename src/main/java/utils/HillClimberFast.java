package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.Math.min;
import static utils.TupleBuilder.buildTuples;

public class HillClimberFast {

    static int v = 27;
    static int k = 6;
    static int m = 4;
    static int t = 3;
    static int b = 86;
    static int coverNumber = 1;
    static int sentinel = v + 1;
    static int kTuplesCount = (int) CombinatoricsUtils.binomialCoefficient(v, k);
    static int mTuplesCount = (int) CombinatoricsUtils.binomialCoefficient(v, m);
    static int i, j, l, n, o, p, q, count = 0;
    static int neighborLen = k * (v - k);
    static int coverLen = 436;
    static int setNumber;
    static int nextS;
    static int[][] kTuples = buildTuples(v, k);
    static int[][] mTuples = buildTuples(v, m);
    static int[][] mset = buildTuples(v, m);
    static int[] kset = new int[b];
    static int[][] neighbors = new int[kTuplesCount][neighborLen];
    static int[][] coverings = new int[kTuplesCount][coverLen - 1];
    static int[] covered = new int[mTuplesCount];
    static int[][] wheel = new int[b][k];
    static int[] costs = new int[kTuplesCount];
    static int[] costds = new int[kTuplesCount];
    static int costDelta = 0;
    static int uncovered = 0;
    static String[] lineArray;

    public static void calculateNeighbors() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Stanislav Ilchev\\Desktop\\neighbors-2.txt"));
        String line;
        int row = 0;
        try {
            line = br.readLine();
            while (line != null) {
                line = line.split(":")[1];
                lineArray = line.trim().split(" ");
                for (i = 0; i < neighborLen; i++) {
                    neighbors[row][i] = Integer.parseInt(lineArray[i]);
                }
                line = br.readLine();
                row++;
            }
        } finally {
            br.close();
        }

//        FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\neighbors.txt");
//        for (i = 0; i < kTuplesCount; i++) {
//            fileWriter.flush();
//            fileWriter.append(i + ": ");
////            System.out.print(i + ": ");
//            count = 0;
//            for (j = 0; j < kTuplesCount; j++) {
//                if (intersection(kTuples[i], kTuples[j]) == k - 1) {
//                    neighbors[i][count] = j;
//                    count++;
//                    fileWriter.flush();
//                    fileWriter.append(j + " ");
////                    System.out.print(j + " ");
//                }
//            }
//            if (i % 1000 == 0) {
//                System.out.println(i);
//            }
//            fileWriter.flush();
//            fileWriter.append("\n");
//        }
//        fileWriter.close();
    }

    public static void calculateCoverings() {
        for (i = 0; i < kTuplesCount; i++) {
//            System.out.print(i + ": ");
            count = 0;
            for (j = 0; j < mTuplesCount; j++) {
                if (intersection(kTuples[i], mset[j]) >= t) {
                    coverings[i][count] = j;
                    count++;
//                    System.out.print(j + " ");
                }
            }
            if (i % 1000 == 0) {
                System.out.println(i);
            }
        }
    }

    public static void calculateCovered() {
        for (i = 0; i < mTuplesCount; i++) {
//            System.out.print(i + ": ");
            for (j = 0; j < b; j++) {
                if (intersection(mset[i], wheel[j]) >= t) {
                    covered[i]++;
                }
            }
//            System.out.println(covered[i]);
        }
    }

    public static void calculateCosts() {
        int i;
        for (i = 0; i <= b; i++)
            if (i < coverNumber)
                costs[i] = coverNumber - i;
            else
                costs[i] = 0;
        for (i = 0; i < b; i++)
            costds[i] = costs[i] - costs[i + 1];
    }

    public static int computeNeighbor() {
        costDelta = 0;
        int currS;
        int currPtr, nextPtr;
        Random random = new Random();
        setNumber = random.nextInt(b);
        currS = kset[setNumber];
        nextS = neighbors[currS][random.nextInt(neighborLen)];
        currPtr = currS;
        nextPtr = nextS;
        for (i = 0; i < (coverLen - 1) * 2; i++)
            if (currPtr == nextPtr) {
                currPtr++;
                nextPtr++;
                i++;
            } else if (currPtr < nextPtr) {
                costDelta += costds[covered[currPtr++] - 1];
            } else
                costDelta -= costds[covered[nextPtr++]];
        return costDelta;
    }

    public static void acceptNeighbor() {
        int i;
        int currS;
        currS = kset[setNumber];
        for (i = 0; i < mTuplesCount; i++) {
            if (intersection(mTuples[i], kTuples[currS]) >= t)
                covered[i]--;
        }
        for (i = 0; i < mTuplesCount; i++) {
            if (intersection(mTuples[i], kTuples[nextS]) >= t)
                covered[i]++;
        }
        kset[setNumber] = nextS;
    }

    public static int calculateUncoveredMsets() {
        uncovered = 0;
        for (i = 0; i < mTuplesCount; i++) {
            if (covered[i] == 0) {
                uncovered++;
            }
        }
        return uncovered;
    }

    /*
     ** computeNeighbor() calculates the cost difference between the current
     ** solution and a random neighbor of the current solution. It employs
     ** the sentinels at the end of the covered sets of each k-set. (That's
     ** why they were put there in calculateNeighbors().)
     **
     */

 /*   public static int computeNeighbor() {
        coverLen = 0;
        for (i = 0; i <= min(k - t, m - t); i++) {
            coverLen += (int) CombinatoricsUtils.binomialCoefficient(k, t + i) * CombinatoricsUtils.binomialCoefficient(v - k, m - t - i);
        }
        coverLen++;
        Random random = new Random();
        int costDelta = 0;
        int i;
        int currS;
        int currPtr, nextPtr;
        setNumber = random.nextInt(b);
        currS = kset[setNumber];
        nextS = neighbors[currS * neighborLen + random.nextInt(neighborLen)];
        currPtr = coverings + currS * coverLen;
        nextPtr = coverings + nextS * coverLen;
        for (i = 0; i < (coverLen - 1) * 2; i++)
            if (currPtr == nextPtr) {
                currPtr++;
                nextPtr++;
                i++;
            } else if (currPtr < nextPtr) {
                costDelta += costds[covered[currPtr++] - 1];
            } else
                costDelta -= costds[covered[nextPtr++]];
        return costDelta;
    }

    public static void acceptNeighbor() {
        int i;
        int[] currS;
        int coveringsPtr;
        currS = kset[setNumber];
        coveringsPtr = coverings + currS * coverLen;
        for (i = 0; i < coverLen - 1; i++)
            covered[coveringsPtr[i]]--;
        coveringsPtr = coverings + nextS * coverLen;
        for (i = 0; i < coverLen - 1; i++)
            covered[coveringsPtr[i]]++;
        kset[setNumber] = nextS;
    }
*/

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
        calculateNeighbors();
        calculateCoverings();
        calculateCosts();
        int subsetSize = b;
        boolean readFile = false;
        int i, j, l, count, randomNumber;
        long iterations = CombinatoricsUtils.binomialCoefficient(subsetSize * k, 1) * CombinatoricsUtils.binomialCoefficient(v - k, 1);
        System.out.println(iterations);
        BufferedReader br;
        Random random = new Random();
        System.out.println("Checking if this set is a wheel that guarantees a " + t + "-match...");
        String line;
        int row = 0;
        if (!readFile) {
            for (i = 0; i < subsetSize; i++) {
                randomNumber = random.nextInt(kTuplesCount);
                for (j = 0; j < k; j++) {
                    wheel[i][j] = kTuples[randomNumber][j];
                }
                kset[i] = randomNumber;
            }
        } else {
            br = new BufferedReader(new FileReader("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt"));
            try {
                line = br.readLine();
                while (line != null) {
                    for (i = 0; i < k; i++) {
                        if (line.contains(",")) {
                            wheel[row][i] = Byte.parseByte(line.replaceAll("\\s", "").split(",")[i]);
                        } else {
                            wheel[row][i] = Byte.parseByte(line.replaceAll("\\s{2,}", " ").trim().replace(" ", ",").split(",")[i]);
                        }
                    }
                    line = br.readLine();
                    row++;
                }
            } finally {
                br.close();
            }
        }
        calculateCovered();
        costDelta = computeNeighbor();
        System.out.println(costDelta);
        count = 0;
        while (true) {
            count++;
//            System.out.println(count);
            if (count == iterations) {
                calculateUncoveredMsets();
                System.out.println("Uncovered: " + uncovered);
                if (uncovered <= 2) {
                    System.out.println("A new record was found!");
                    FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
                    fileWriter.flush();
                    for (i = 0; i < b; i++) {
                        for (j = 0; j < kTuplesCount; j++) {
                            if (kset[i] == j) {
                                for (l = 0; l < k; l++) {
                                    fileWriter.append(kTuples[j][l] + " ");
                                }
                                fileWriter.append("\n");
                                break;
                            }
                        }
                    }
                    fileWriter.close();
                    return;
                } else {
                    System.out.println("Restarting...");
                    if (!readFile) {
                        for (i = 0; i < subsetSize; i++) {
                            randomNumber = random.nextInt(kTuplesCount);
                            for (j = 0; j < k; j++) {
                                wheel[i][j] = kTuples[randomNumber][j];
                            }
                            kset[i] = randomNumber;
                        }
                    } else {
                        row = 0;
                        br = new BufferedReader(new FileReader("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt"));
                        try {
                            line = br.readLine();
                            while (line != null) {
                                for (i = 0; i < k; i++) {
                                    if (line.contains(",")) {
                                        wheel[row][i] = Byte.parseByte(line.replaceAll("\\s", "").split(",")[i]);
                                    } else {
                                        wheel[row][i] = Byte.parseByte(line.replaceAll("\\s{2,}", " ").trim().replace(" ", ",").split(",")[i]);
                                    }
                                }
                                line = br.readLine();
                                row++;

                            }
                        } finally {
                            br.close();
                        }
                    }
                    count = 0;
                    calculateCovered();
                    costDelta = computeNeighbor();
                    System.out.println(costDelta);
                }
            }
            computeNeighbor();
            if (costDelta >= 0) {
                acceptNeighbor();
                count = 0;
                if (costDelta > 0) {
                    System.out.println(costDelta);
                }
            }
        }
    }
}