package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static utils.Library.*;

public class HillClimber {

    public static void main(String[] args) throws IOException {
        int subsetSize = b;
        int iterationsWithoutImprovement = 0;
        int threshold = 0;
        boolean startFromFile = true;
        boolean coverOnlySomeMsets = false;
        int oldNumber, randomNumber3 = 0;
        int numberOfMatches, maxNumberOfMatches = 0, biggestMaxNumberOfMatches = 0;
        int i, j, l, count, randomNumber = 0, randomNumber2 = 0;
        long iterations = subsetSize * k * (v - k);
        int[][] wheel = new int[b][k];
        int[][] wheel2 = new int[subsetSize][k];
        int[][][] used = new int[subsetSize][k][v + 1];
        List<Integer> usedNumbers = new ArrayList<>();
        System.out.printf("Searching for a (%s,%s,%s,%s,1) covering in %s blocks. (v,k,m,t,lambda)\n", v, k, m, t, b);
        if (coverOnlySomeMsets) {
            mSetsCount = 1224;
            mSets = readFromFileNLines(mSetsCount);
        } else {
            if (!startFromFile) {
                for (i = 0; i < subsetSize; i++) {
                    randomNumber = random.nextInt(kSetsCount);
                    for (j = 0; j < k; j++) {
                        wheel[i][j] = kSets[randomNumber][j];
                    }
                }
            } else {
                wheel = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt");
            }
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
        while (true) {
            iterationsWithoutImprovement++;
            if (iterationsWithoutImprovement == iterations) {
                threshold = 0;
//                System.out.println("Threshold: " + threshold);
                iterationsWithoutImprovement = 0;
            }
//            if (count % 1000 == 0) {
//                System.out.println(count);
//            }
            count++;
            if (maxNumberOfMatches > biggestMaxNumberOfMatches) {
                iterationsWithoutImprovement = 0;
                threshold = 0;
                biggestMaxNumberOfMatches = maxNumberOfMatches;
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
                count = 0;
                usedNumbers.clear();
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
            for (i = 0; i < mSetsCount; i++) {
                for (j = 0; j < subsetSize; j++) {
                    if (intersection(mSets[i], wheel2[j]) >= t) {
                        numberOfMatches++;
                        break;
                    }
                }
            }
            if (numberOfMatches >= maxNumberOfMatches || numberOfMatches >= biggestMaxNumberOfMatches - threshold) {
//                if (numberOfMatches == maxNumberOfMatches) {
//                    print(wheel2[randomNumber]);
////                    writeToFile(wheel2);
//                }
                count = 0;
                maxNumberOfMatches = numberOfMatches;
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