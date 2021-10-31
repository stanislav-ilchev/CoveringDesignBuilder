package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FinderUncoveredGreedy {

    static int nCr(int n, int r) {
        return fact(n) / (fact(r) * fact(n - r));
    }

    static int fact(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }

    public static int intersection(short[] list1, short[] list2) {
        int intersectionSize = 0;
        for (int i = 0; i < 6; i++) {
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
        int lotteryNumbers = 49;
        int pickedNumbers = 6;
        int ticketNumberLimit = 162;
        int possibleCombinations = 13983816;
        int subsetSize;
        byte a, b, c, d, e, f, randomNumber3;
        short g = 0;
        int numberOfMatches = 0, maxNumberOfMatches = 0, bestNumberOfMatches = 0, maxNumberOfMatchesWithoutOneElement, intersectionSize, maximum = 0;
        int i, j, k, l, m, count = 0, number = 0, numbersToMatch = 3, randomNumber = 0, randomNumber2 = 0, numberOfTries = 0;
        int bestRandomNumber = 0, bestRandomNumber2 = 0, bestRandomNumber3 = 0;
        short[][] combinations = new short[possibleCombinations][];
        short[][][] uncoveredCombinationsForEveryElement = new short[ticketNumberLimit][][];
        int[] numberOfCoveredCombinationsForEveryElement = new int[ticketNumberLimit];
        short[][] subsets = new short[ticketNumberLimit][];
        int[][] placeHolder = new int[1][];
        for (a = 1; a <= lotteryNumbers - 5; a++) {
            for (b = (byte) (a + 1); b <= lotteryNumbers - 4; b++) {
                for (c = (byte) (b + 1); c <= lotteryNumbers - 3; c++) {
                    for (d = (byte) (c + 1); d <= lotteryNumbers - 2; d++) {
                        for (e = (byte) (d + 1); e <= lotteryNumbers - 1; e++) {
                            for (f = (byte) (e + 1); f <= lotteryNumbers; f++) {
                                combinations[count] = new short[6];
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
        Random random = new Random();
        subsetSize = ticketNumberLimit;
        System.out.println("Checking if this set is a wheel that guarantees a " + numbersToMatch + "-match...");
        String line = "";
        int row = 0;
        for (i = 0; i < ticketNumberLimit; i++) {
            uncoveredCombinationsForEveryElement[i] = new short[200000][];
        }
        for (i = 0; i < ticketNumberLimit; i++) {
            subsets[i] = new short[6];
        }
        for (i = 0; i < 1; i++) {
            placeHolder[i] = new int[6];
        }
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt"));
        try {
            line = br.readLine();
            while (line != null) {
                for (i = 0; i < 6; i++) {
                    subsets[row][i] = Byte.parseByte(line.replaceAll("\\s{2,}", " ").trim().replace(" ", ",").split(",")[i]);
                }
                line = br.readLine();
                row++;

            }
        } finally {
            br.close();
        }
        for (k = 0; k < ticketNumberLimit; k++) {
            System.out.println(k);
            maxNumberOfMatchesWithoutOneElement = 0;
            for (i = 0; i < possibleCombinations; i++) {
                for (j = 0; j < ticketNumberLimit; j++) {
                    if (j != k && intersection(combinations[i], subsets[j]) >= numbersToMatch) {
                        maxNumberOfMatchesWithoutOneElement++;
                        break;
                    }
                }
                if (j == subsetSize) {
                    uncoveredCombinationsForEveryElement[k][number] = combinations[i];
                    number++;
                }
            }
            numberOfCoveredCombinationsForEveryElement[k] = maxNumberOfMatchesWithoutOneElement;
            number = 0;
        }
        for (i = 0; i < possibleCombinations; i++) {
            for (j = 0; j < subsetSize; j++) {
                if (intersection(combinations[i], subsets[j]) >= numbersToMatch) {
                    maxNumberOfMatches++;
                    break;
                }
            }
        }
        bestNumberOfMatches = maxNumberOfMatches;

        while (numberOfMatches < possibleCombinations) {
//            count++;
//            System.out.println(count);
            intersectionSize = 0;

            for (i = 0; i < ticketNumberLimit; i++) {
                for (j = 0; j < 6; j++) {
                    for (k = 1; k <= 49; k++) {
                        intersectionSize = 0;
                        randomNumber = i;
                        randomNumber2 = j;
                        randomNumber3 = (byte) k;
                        if (subsets[randomNumber][0] == randomNumber3 || subsets[randomNumber][1] == randomNumber3 || subsets[randomNumber][2] == randomNumber3 ||
                                subsets[randomNumber][3] == randomNumber3 || subsets[randomNumber][4] == randomNumber3 || subsets[randomNumber][5] == randomNumber3) {
                            continue;
                        }
                        count++;
                        g = subsets[randomNumber][randomNumber2];
                        subsets[randomNumber][randomNumber2] = randomNumber3;
                        for (l = 0; l < 200000; l++) {
                            if (uncoveredCombinationsForEveryElement[randomNumber][l] != null) {
                                if (intersection(subsets[randomNumber], uncoveredCombinationsForEveryElement[randomNumber][l]) >= numbersToMatch) {
                                    intersectionSize++;
                                }
                            }
                        }
                        numberOfMatches = numberOfCoveredCombinationsForEveryElement[randomNumber] + intersectionSize;
                        if (numberOfMatches >= bestNumberOfMatches) {
                            subsets[randomNumber][randomNumber2] = g;
                            for (m = 0; m < 6; m++) {
                                System.out.print(subsets[randomNumber][m] + " ");
                            }
                            System.out.println();
                            subsets[randomNumber][randomNumber2] = randomNumber3;
                            for (m = 0; m < 6; m++) {
                                System.out.print(subsets[randomNumber][m] + " ");
                            }
                            System.out.println();
                            System.out.println();

                            bestRandomNumber = randomNumber;
                            bestRandomNumber2 = randomNumber2;
                            bestRandomNumber3 = randomNumber3;
                            bestNumberOfMatches = numberOfMatches;
                        }
//                        System.out.println(count);
                        subsets[randomNumber][randomNumber2] = g;
                    }
                }
            }
            count = 0;
            subsets[bestRandomNumber][bestRandomNumber2] = (short) bestRandomNumber3;
            if (bestNumberOfMatches >= maxNumberOfMatches) {
                maxNumberOfMatches = bestNumberOfMatches;
                if (bestNumberOfMatches == possibleCombinations) {
                    System.out.println("A " + numbersToMatch + "-match-guaranteed wheel was found!");
                    FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
                    fileWriter.flush();
                    fileWriter.write("A " + numbersToMatch + "-match-guaranteed wheel was found!" + "\n");
                    for (j = 0; j < subsetSize; j++) {
                        System.out.println(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5]);
                        fileWriter.append(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5] + "\n");
                    }
                    fileWriter.close();
                    return;
                } else {
                    System.out.println("No " + numbersToMatch + "-match-guaranteed wheel was found!");
                    System.out.println("Number of matches is " + bestNumberOfMatches);
                    FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
                    fileWriter.flush();
                    fileWriter.write("No " + numbersToMatch + "-match-guaranteed wheel was found!" + "\n" + "Number of matches is " + bestNumberOfMatches + "\n");
                    for (j = 0; j < subsetSize; j++) {
                        System.out.println(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5]);
                        fileWriter.append(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5] + "\n");
                    }
                    fileWriter.close();
                }
                for (i = 0; i < uncoveredCombinationsForEveryElement.length; i++) {
                    uncoveredCombinationsForEveryElement[i] = null;
                }
                for (i = 0; i < ticketNumberLimit; i++) {
                    uncoveredCombinationsForEveryElement[i] = new short[200000][];
                }
                for (k = 0; k < ticketNumberLimit; k++) {
//                    System.out.println(k);
                    maxNumberOfMatchesWithoutOneElement = 0;
                    for (i = 0; i < possibleCombinations; i++) {
                        for (j = 0; j < ticketNumberLimit; j++) {
                            if (j != k && intersection(combinations[i], subsets[j]) >= numbersToMatch) {
                                maxNumberOfMatchesWithoutOneElement++;
                                break;
                            }
                        }
                        if (j == subsetSize) {
                            uncoveredCombinationsForEveryElement[k][number] = combinations[i];
                            number++;
                        }
                    }
                    numberOfCoveredCombinationsForEveryElement[k] = maxNumberOfMatchesWithoutOneElement;
                    number = 0;
                }
            } else {
                System.out.println("No more improvements are possible!");
                return;
            }
        }
    }
}
