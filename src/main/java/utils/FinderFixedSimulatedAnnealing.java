package com.ecm.selenium.adidas.pages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FinderFixedSimulatedAnnealing {

    static int nCr(int n, int r) {
        return fact(n) / (fact(r) * fact(n - r));
    }

    static int fact(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }

    public static int intersection(int[] list1, int[] list2) {
        int intersectionSize = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (list1[i] == list2[j]) {
                    intersectionSize++;
                    break;
                }
            }
        }
        return intersectionSize;
    }

    public static void main(String[] args) throws IOException {
        int lotteryNumbers = 27;
        int pickedNumbers = 6;
        int ticketNumberLimit = 85;
        int possibleCombinations = 296010;
        int subsetSize, biggestMaxNumberOfMatches = 0, iterations = ticketNumberLimit * pickedNumbers * (lotteryNumbers - pickedNumbers);
        double temperature = 0.2, temperatureChange = 0.9999, probability = 0;
        int a, b, c, d, e, f, g, i, j, count = 0, numberOfMatches = 0, numbersToMatch = 3, maxNumberOfMatches = 0, randomNumber, randomNumber2, randomNumber3;
        int[][] combinations = new int[possibleCombinations][];
        int[][] fourTuples = new int[17550][];
        int[][] subsets = new int[ticketNumberLimit][];
        for (a = 1; a <= lotteryNumbers - 5; a++) {
            for (b = a + 1; b <= lotteryNumbers - 4; b++) {
                for (c = b + 1; c <= lotteryNumbers - 3; c++) {
                    for (d = c + 1; d <= lotteryNumbers - 2; d++) {
                        for (e = d + 1; e <= lotteryNumbers - 1; e++) {
                            for (f = e + 1; f <= lotteryNumbers; f++) {
                                combinations[count] = new int[6];
                                combinations[count][0] = a;
                                combinations[count][1] = b;
                                combinations[count][2] = c;
                                combinations[count][3] = d;
                                combinations[count][4] = e;
                                combinations[count][5] = f;
                                count++;
                            }
                        }
                    }
                }
            }
        }
        count = 0;
        for (a = 1; a <= lotteryNumbers - 3; a++) {
            for (b = (byte) (a + 1); b <= lotteryNumbers - 2; b++) {
                for (c = (byte) (b + 1); c <= lotteryNumbers - 1; c++) {
                    for (d = (byte) (c + 1); d <= lotteryNumbers; d++) {
                        fourTuples[count] = new int[4];
                        fourTuples[count][0] = a;
                        fourTuples[count][1] = b;
                        fourTuples[count][2] = c;
                        fourTuples[count][3] = d;
                        count++;
                    }
                }
            }
        }
        count = 0;
        Random random = new Random();
        FileWriter fileWriter;
        subsetSize = ticketNumberLimit;
        System.out.println("Checking if this set is a wheel that guarantees a " + numbersToMatch + "-match...");
        String line = "";
        int row = 0;
        for (i = 0; i < ticketNumberLimit; i++) {
            subsets[i] = new int[6];
        }
//        for (i = 0; i < subsetSize; i++) {
//            subsets[i] = combinations[random.nextInt(possibleCombinations)];
//        }
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\stanislav.ilchev\\Desktop\\input.txt"));
        try {
            line = br.readLine();
            while (line != null) {
                for (i = 0; i < 6; i++) {
                    subsets[row][i] = Integer.parseInt(line.replaceAll("\\s{2,}", " ").trim().replace(" ", ",").split(",")[i]);
                }
                line = br.readLine();
                row++;

            }
        } finally {
            br.close();
        }
        for (i = 0; i < 17550; i++) {
            for (j = 0; j < subsetSize; j++) {
                if (intersection(fourTuples[i], subsets[j]) >= numbersToMatch) {
                    maxNumberOfMatches++;
                    break;
                }
            }
        }
        maxNumberOfMatches = 0;
        while (numberOfMatches <= 17550) {
            count++;
//            System.out.println(count);
            if (maxNumberOfMatches > biggestMaxNumberOfMatches) {
                biggestMaxNumberOfMatches = maxNumberOfMatches;
//                System.out.println("No " + numbersToMatch + "-match-guaranteed wheel was found!");
                System.out.println(maxNumberOfMatches);
                fileWriter = new FileWriter("C:\\Users\\stanislav.ilchev\\Desktop\\result.txt");
                fileWriter.flush();
                fileWriter.write("No " + numbersToMatch + "-match-guaranteed wheel was found!" + "\n" + "Number of matches is " + maxNumberOfMatches + "\n");
                for (j = 0; j < subsetSize; j++) {
//                    System.out.println(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5]);
                    fileWriter.append(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5] + "\n");
                }
                fileWriter.close();
            }
            if (count == iterations) {
                System.out.println("Restarting...");
                count = 0;
                temperature = 0.2;
//                for (i = 0; i < subsetSize; i++) {
//                    subsets[i] = combinations[random.nextInt(possibleCombinations)];
//                }
                row = 0;
                br = new BufferedReader(new FileReader("C:\\Users\\stanislav.ilchev\\Desktop\\input.txt"));
                try {
                    line = br.readLine();
                    while (line != null) {
                        for (i = 0; i < 6; i++) {
                            subsets[row][i] = Integer.parseInt(line.replaceAll("\\s{2,}", " ").trim().replace(" ", ",").split(",")[i]);
                        }
                        line = br.readLine();
                        row++;

                    }
                } finally {
                    br.close();
                }
                maxNumberOfMatches = 0;
//                biggestMaxNumberOfMatches = 0;
            }
            temperature = temperature * temperatureChange;
            numberOfMatches = 0;
            randomNumber = random.nextInt(ticketNumberLimit);
            randomNumber2 = random.nextInt(6);
            randomNumber3 = (byte) (random.nextInt(lotteryNumbers) + 1);
            while (subsets[randomNumber][0] == randomNumber3 || subsets[randomNumber][1] == randomNumber3 || subsets[randomNumber][2] == randomNumber3 || subsets[randomNumber][3] == randomNumber3 ||
                    subsets[randomNumber][4] == randomNumber3 || subsets[randomNumber][5] == randomNumber3) {
                randomNumber3 = (byte) (random.nextInt(lotteryNumbers) + 1);
            }
            g = subsets[randomNumber][randomNumber2];
            subsets[randomNumber][randomNumber2] = randomNumber3;
            for (i = 0; i < 17550; i++) {
                for (j = 0; j < subsetSize; j++) {
                    if (intersection(fourTuples[i], subsets[j]) >= numbersToMatch) {
                        numberOfMatches++;
                        break;
                    }
                }
            }
//            System.out.println(numberOfMatches);
            if (numberOfMatches >= maxNumberOfMatches) {
                count = 0;
                maxNumberOfMatches = numberOfMatches;
            } else if (Math.exp((numberOfMatches - maxNumberOfMatches) / temperature) > Math.random()) {
                count = 0;
                maxNumberOfMatches = numberOfMatches;
            } else {
                subsets[randomNumber][randomNumber2] = g;
                continue;
            }
            if (numberOfMatches == 17550) {
                System.out.println("A " + numbersToMatch + "-match-guaranteed wheel was found!");
                fileWriter = new FileWriter("C:\\Users\\stanislav.ilchev\\Desktop\\result.txt");
                fileWriter.flush();
                fileWriter.write("A " + numbersToMatch + "-match-guaranteed wheel was found!" + "\n");
                for (j = 0; j < subsetSize; j++) {
                    System.out.println(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5]);
                    fileWriter.append(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5] + "\n");
                }
                fileWriter.close();
                return;
            }
        }
    }
}
