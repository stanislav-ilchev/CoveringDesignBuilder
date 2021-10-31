package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static utils.Library.*;

public class HillClimberAddElement {

    public static void main(String[] args) throws IOException {
        int oldNumber, randomNumber3;
        int numberOfMatches, maxNumberOfMatches = 0, biggestMaxNumberOfMatches = 0;
        int i, j, l, count, randomNumber, randomNumber2;
        long iterations = b * k * (v - k);
        int[][] wheel;
        int[][][] used = new int[b][k][v + 1];
        Random random = new Random();
        System.out.printf("Searching for a (%s,%s,%s,%s,1) covering in %s blocks. (v,k,m,t,lambda)\n", v, k, m, t, b);
        wheel = readFromFile();
        count = 0;
        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < b; j++) {
                if (intersection(mSets[i], wheel[j]) >= t) {
                    maxNumberOfMatches++;
                    break;
                }
            }
        }
        while (true) {
//            if (count % 1000 == 0) {
//                System.out.println(count);
//            }
            count++;
            if (maxNumberOfMatches > biggestMaxNumberOfMatches) {
                biggestMaxNumberOfMatches = maxNumberOfMatches;
                if (maxNumberOfMatches == mSetsCount) {
                    System.out.println("A " + t + "-match-guaranteed wheel was found!");
                    FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
                    fileWriter.flush();
                    fileWriter.write("A " + t + "-match-guaranteed wheel was found!" + "\n");
                    for (i = 0; i < b; i++) {
                        for (j = 0; j < k; j++) {
//                        System.out.print(wheel[i][j] + " ");
                            fileWriter.append(wheel[i][j] + " ");
                        }
//                    System.out.println();
                        fileWriter.append("\n");
                    }
                    fileWriter.close();
                    return;
                } else {
                    System.out.println(mSetsCount - maxNumberOfMatches);
                    FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt");
                    fileWriter.flush();
                    for (i = 0; i < b; i++) {
                        for (j = 0; j < k; j++) {
//                        System.out.print(wheel[i][j] + " ");
                            fileWriter.append(wheel[i][j] + " ");
                        }
//                    System.out.println();
                        fileWriter.append("\n");
                    }
                    fileWriter.close();
                }
            }
            if (count == iterations) {
                b++;
                System.out.println("Adding element " + b);
                used = new int[b][k][v + 1];
                wheel = readFromFile();
                maxNumberOfMatches = 0;
                count = 0;
                iterations = b * k * (v - k);
            }
            numberOfMatches = 0;
            randomNumber = random.nextInt(b);
            randomNumber2 = random.nextInt(k);
            randomNumber3 = random.nextInt(v);
            while (contains(wheel[randomNumber], randomNumber3) || used[randomNumber][randomNumber2][randomNumber3] == 1) {
                randomNumber = random.nextInt(b);
                randomNumber2 = random.nextInt(k);
                randomNumber3 = random.nextInt(v);
            }
            used[randomNumber][randomNumber2][randomNumber3] = 1;
            oldNumber = (byte) wheel[randomNumber][randomNumber2];
            wheel[randomNumber][randomNumber2] = randomNumber3;
            for (i = 0; i < mSetsCount; i++) {
                for (j = 0; j < b; j++) {
                    if (intersection(mSets[i], wheel[j]) >= t) {
                        numberOfMatches++;
                        break;
                    }
                }
            }
            if (numberOfMatches >= maxNumberOfMatches) {
                if (numberOfMatches > maxNumberOfMatches) {
                    count = 0;
                }
                maxNumberOfMatches = numberOfMatches;
                for (i = 0; i < b; i++) {
                    for (j = 0; j < k; j++) {
                        for (l = 0; l < v + 1; l++) {
                            used[i][j][l] = 0;
                        }
                    }
                }
            } else {
                wheel[randomNumber][randomNumber2] = oldNumber;
            }
        }
    }
}