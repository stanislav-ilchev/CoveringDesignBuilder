package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient;
import static utils.Library.*;

public class HillClimberThreeMatch2 {

    public static void main(String[] args) throws IOException {
        int[][] tSets = buildCombinations(v, t);
        int[][] tSets2 = buildCombinations(v + 1, t);
        int tSetsCount = (int) binomialCoefficient(v, t);
        int tSetsCount2 = (int) binomialCoefficient(v + 1, t);
        int subsetSize = b;
        boolean startFromFile = true;
        int oldNumber, randomNumber3 = 0, previous = 100;
        int numberOfMatches, maxNumberOfMatches = 0, biggestMaxNumberOfMatches = 0;
        int numberOfThreeMatches, maxNumberOfThreeMatches = 0, biggestMaxNumberOfThreeMatches = 0;
        int i, j, l, count, randomNumber = 0, randomNumber2 = 0;
        long iterations = subsetSize * k * (v - k);
        int[][] wheel = new int[b][k];
        int[][] wheel2 = new int[subsetSize][k];
        int[][][] used = new int[subsetSize][k][v + 1];
        List<Integer> usedNumbers = new ArrayList<>();
        List<Integer> usedNumbers2 = new ArrayList<>();
        Random random = new Random();
        System.out.printf("Searching for a (%s,%s,%s,%s,1) covering in %s blocks. (v,k,m,t,lambda)\n", v, k, m, t, b);
        if (!startFromFile) {
            for (i = 0; i < subsetSize; i++) {
                randomNumber = random.nextInt(kSetsCount);
                for (j = 0; j < k; j++) {
                    wheel[i][j] = kSets[randomNumber][j];
                }
            }
        } else {
            wheel = readFromFile();
        }
        count = 0;
        for (i = 0; i < subsetSize; i++) {
            j = random.nextInt(b);
            while (usedNumbers.contains(j)) {
                j = random.nextInt(b);
            }
            for (l = 0; l < k; l++) {
                wheel2[i][l] = wheel[j][l];
            }
            usedNumbers.add(j);
        }
        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < subsetSize; j++) {
                if (intersection(mSets[i], wheel2[j]) >= t) {
                    maxNumberOfMatches++;
                    break;
                }
            }
        }
        for (i = 0; i < tSetsCount; i++) {
            for (j = 0; j < subsetSize; j++) {
                if (intersection(tSets[i], wheel2[j]) >= t) {
                    maxNumberOfThreeMatches++;
                    break;
                }
            }
        }
        biggestMaxNumberOfMatches = maxNumberOfMatches;
        biggestMaxNumberOfThreeMatches = maxNumberOfThreeMatches;
        System.out.println("initial 3-match-count: " + maxNumberOfThreeMatches);
        while (true) {
//            if (count % 1000 == 0) {
//                System.out.println(count);
//            }
            count++;
            if (maxNumberOfMatches > biggestMaxNumberOfMatches || maxNumberOfThreeMatches > biggestMaxNumberOfThreeMatches) {
                System.out.println(maxNumberOfThreeMatches);
                biggestMaxNumberOfThreeMatches = maxNumberOfThreeMatches;
                if (maxNumberOfMatches == mSetsCount) {
                    System.out.println("A " + t + "-match-guaranteed wheel was found!");
                    FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
                    fileWriter.flush();
                    fileWriter.write("A " + t + "-match-guaranteed wheel was found!" + "\n");
                    for (i = 0; i < b; i++) {
                        for (j = 0; j < k; j++) {
//                        System.out.print(wheel[i][j] + " ");
                            fileWriter.append(wheel2[i][j] + " ");
                        }
//                    System.out.println();
                        fileWriter.append("\n");
                    }
                    fileWriter.close();
                    return;
                } else {
                    System.out.println(mSetsCount - maxNumberOfMatches);
                    FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
                    fileWriter.flush();
                    for (i = 0; i < b; i++) {
                        for (j = 0; j < k; j++) {
//                        System.out.print(wheel[i][j] + " ");
                            fileWriter.append(wheel2[i][j] + " ");
                        }
//                    System.out.println();
                        fileWriter.append("\n");
                    }
                    fileWriter.close();
                }
            }
            if (count == iterations) {
                for (i = 0; i < subsetSize; i++) {
                    for (j = 0; j < k; j++) {
                        for (l = 0; l < v + 1; l++) {
                            used[i][j][l] = 0;
                        }
                    }
                }
                System.out.println("Restarting...");
                if (!startFromFile) {
                    for (i = 0; i < subsetSize; i++) {
                        randomNumber = random.nextInt(kSetsCount);
                        for (j = 0; j < k; j++) {
                            wheel[i][j] = kSets[randomNumber][j];
                        }
                    }
                } else {
                    wheel = readFromFile();
                }
                maxNumberOfMatches = 0;
                maxNumberOfThreeMatches = 0;
                count = 0;
                usedNumbers.clear();
                usedNumbers2.clear();
                for (i = 0; i < subsetSize; i++) {
                    j = random.nextInt(b);
                    while (usedNumbers.contains(j)) {
                        j = random.nextInt(b);
                    }
                    for (l = 0; l < k; l++) {
                        wheel2[i][l] = wheel[j][l];
                    }
                    usedNumbers.add(j);
                }
            }
            numberOfMatches = 0;
            numberOfThreeMatches = 0;
            randomNumber = random.nextInt(subsetSize);
            randomNumber2 = random.nextInt(k);
            randomNumber3 = random.nextInt(v);
            while (contains(wheel2[randomNumber], randomNumber3) || used[randomNumber][randomNumber2][randomNumber3] == 1) {
                randomNumber = random.nextInt(subsetSize);
                randomNumber2 = random.nextInt(k);
                randomNumber3 = random.nextInt(v);
            }
            used[randomNumber][randomNumber2][randomNumber3] = 1;
            oldNumber = (byte) wheel2[randomNumber][randomNumber2];
            wheel2[randomNumber][randomNumber2] = randomNumber3;
            for (i = 0; i < tSetsCount; i++) {
                for (j = 0; j < subsetSize; j++) {
                    if (intersection(tSets[i], wheel2[j]) >= t) {
                        numberOfThreeMatches++;
                        break;
                    }
                }
            }
            for (i = 0; i < mSetsCount; i++) {
                for (j = 0; j < subsetSize; j++) {
                    if (intersection(mSets[i], wheel2[j]) >= t) {
                        numberOfMatches++;
                        break;
                    }
                }
            }
            if (numberOfMatches >= maxNumberOfMatches || numberOfThreeMatches >= maxNumberOfThreeMatches) {
//                if (randomNumber2 == previous) {
//                    wheel2[randomNumber][randomNumber2] = v;
//                    if (!usedNumbers2.contains(randomNumber2)) {
//                        usedNumbers2.add(randomNumber2);
//                        System.out.println("Non-significant position found: " + randomNumber2);
//                    }
//                }
//                previous = randomNumber2;
//                wheel2[randomNumber][randomNumber2] = randomNumber3;
//                if (numberOfMatches == maxNumberOfMatches) {
//                    print(wheel2[randomNumber]);
//                }
                count = 0;
                maxNumberOfMatches = numberOfMatches;
                maxNumberOfThreeMatches = numberOfThreeMatches;
                for (i = 0; i < subsetSize; i++) {
                    for (j = 0; j < k; j++) {
                        for (l = 0; l < v + 1; l++) {
                            used[i][j][l] = 0;
                        }
                    }
                }
            } else {
                wheel2[randomNumber][randomNumber2] = oldNumber;
            }
        }
    }
}