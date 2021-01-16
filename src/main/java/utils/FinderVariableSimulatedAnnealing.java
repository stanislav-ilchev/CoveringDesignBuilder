package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FinderVariableSimulatedAnnealing {

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
        int possibleFourTuples = (int) CombinatoricsUtils.binomialCoefficient(lotteryNumbers, 4);
        int possibleSixTuples = (int) CombinatoricsUtils.binomialCoefficient(lotteryNumbers, 6);
        int subsetSize, previousNumberOfMatches;
        double temperature = 100, temperatureChange = 0.98;
        byte g;
        int a, b, c, d, e, f, i, j, count = 0, numberOfMatches = 0, numbersToMatch = 3, maxNumberOfMatches = 0, randomNumber = 0, randomNumber2, randomNumber3, rowCount;
        int[][] fourTuples = new int[possibleFourTuples][];
        int[][] sixTuples = new int[possibleSixTuples][];
        int[][] subsets = new int[ticketNumberLimit][];
        int[][] placeHolder = new int[ticketNumberLimit][];
        int[] randomTicketNumber = new int[ticketNumberLimit];
        int[] randomCombinationNumber = new int[possibleFourTuples];
        List<Integer> usedNumbers = new ArrayList<>();
        boolean acceptNext = false;
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
        for (a = 1; a <= lotteryNumbers - 5; a++) {
            for (b = a + 1; b <= lotteryNumbers - 4; b++) {
                for (c = b + 1; c <= lotteryNumbers - 3; c++) {
                    for (d = c + 1; d <= lotteryNumbers - 2; d++) {
                        for (e = d + 1; e <= lotteryNumbers - 1; e++) {
                            for (f = e + 1; f <= lotteryNumbers; f++) {
                                sixTuples[count] = new int[6];
                                sixTuples[count][0] = a;
                                sixTuples[count][1] = b;
                                sixTuples[count][2] = c;
                                sixTuples[count][3] = d;
                                sixTuples[count][4] = e;
                                sixTuples[count][5] = f;
                                count++;
                            }
                        }
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
        for (i = 0; i < 1; i++) {
            placeHolder[i] = new int[6];
        }
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\stani\\Desktop\\input.txt"));
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
        for (i = 0; i < possibleFourTuples; i++) {
            for (j = 0; j < subsetSize; j++) {
                if (intersection(fourTuples[i], subsets[j]) >= numbersToMatch) {
                    maxNumberOfMatches++;
                    break;
                }
            }
        }
        while (numberOfMatches <= possibleFourTuples) {
//            count++;
//            System.out.println(count);
            temperature = temperature * temperatureChange;
            numberOfMatches = 0;
            randomNumber = random.nextInt(subsetSize);
            randomNumber2 = random.nextInt(6);
            randomNumber3 = (byte) (random.nextInt(lotteryNumbers) + 1);
            while (subsets[randomNumber][0] == randomNumber3 || subsets[randomNumber][1] == randomNumber3 || subsets[randomNumber][2] == randomNumber3 || subsets[randomNumber][3] == randomNumber3 ||
                    subsets[randomNumber][4] == randomNumber3 || subsets[randomNumber][5] == randomNumber3) {
                randomNumber3 = (byte) (random.nextInt(lotteryNumbers) + 1);
            }
            g = (byte) subsets[randomNumber][randomNumber2];
            subsets[randomNumber][randomNumber2] = randomNumber3;
            for (i = 0; i < possibleFourTuples; i++) {
                for (j = 0; j < subsetSize; j++) {
                    if (intersection(fourTuples[i], subsets[j]) >= numbersToMatch) {
                        numberOfMatches++;
                        break;
                    }
                }
            }
            if (numberOfMatches >= maxNumberOfMatches) {
                maxNumberOfMatches = numberOfMatches;
            } else if (Math.exp((numberOfMatches - maxNumberOfMatches) / temperature) > Math.random()) {
                maxNumberOfMatches = numberOfMatches;
            } else {
                subsets[randomNumber][randomNumber2] = g;
                continue;
            }
            if (numberOfMatches == possibleFourTuples) {
                System.out.println("A " + numbersToMatch + "-match-guaranteed wheel was found!");
                fileWriter = new FileWriter("C:\\Users\\stani\\Desktop\\result.txt");
                fileWriter.flush();
                fileWriter.write("A " + numbersToMatch + "-match-guaranteed wheel was found!" + "\n");
                for (j = 0; j < subsetSize; j++) {
                    System.out.println(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5]);
                    fileWriter.append(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5] + "\n");
                }
                fileWriter.close();
                return;
            } else {
//                System.out.println("No " + numbersToMatch + "-match-guaranteed wheel was found!");
                System.out.println(numberOfMatches);
//                fileWriter = new FileWriter("C:\\Users\\stani\\Desktop\\result.txt");
//                fileWriter.flush();
//                fileWriter.write("No " + numbersToMatch + "-match-guaranteed wheel was found!" + "\n");
//                fileWriter.write("Number of matches is " + numberOfMatches + "\n");
//                for (j = 0; j < subsetSize; j++) {
//                    System.out.println(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5]);
//                    fileWriter.append(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5] + "\n");
//                }
//                fileWriter.close();
            }
        }
    }
}
