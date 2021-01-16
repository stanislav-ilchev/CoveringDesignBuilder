package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class HillClimberAddElement {

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
        int lotteryNumbers = 7;
        int pickedNumbers = 6;
        int ticketNumberLimit = 1;
        int possibleCombinations = (int) CombinatoricsUtils.binomialCoefficient(lotteryNumbers, pickedNumbers);
        int subsetSize = 1;
        byte a, b, c, d, e, f, g = 0, h, randomNumber3;
        int numberOfMatches = 0, maxNumberOfMatches = 0, biggestMaxNumberOfMatches = 0, neighbourhoodSize = ticketNumberLimit * pickedNumbers, perturbation = 1, index, previousIndex;
        int i, j, k, l, m, n, o, count = 0, numbersToMatch = 3, randomNumber = 0, randomNumber2 = 0;
        long iterations = CombinatoricsUtils.binomialCoefficient(subsetSize * pickedNumbers, 1) * CombinatoricsUtils.binomialCoefficient(lotteryNumbers - pickedNumbers, 1);
        short[][] combinations = new short[possibleCombinations][];
        short[][] fourTuples = new short[17550][];
        short[][] threeTuples = new short[2925][];
        short[][] subsets = new short[ticketNumberLimit][];
        short[][] subsets2 = new short[subsetSize][];
        short[][] subsetsCopy = new short[120][];
        List<Integer> usedNumbers = new ArrayList<>();
        List<String> usedNumbers2 = new ArrayList<>();
        Map<Integer, short[]> map = new TreeMap<>();
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
        for (a = 1; a <= lotteryNumbers - 3; a++) {
            for (b = (byte) (a + 1); b <= lotteryNumbers - 2; b++) {
                for (c = (byte) (b + 1); c <= lotteryNumbers - 1; c++) {
                    for (d = (byte) (c + 1); d <= lotteryNumbers; d++) {
                        fourTuples[count] = new short[4];
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
//        for (a = 1; a <= lotteryNumbers - 2; a++) {
//            for (b = (byte) (a + 1); b <= lotteryNumbers - 1; b++) {
//                for (c = (byte) (b + 1); c <= lotteryNumbers; c++) {
//                    threeTuples[count] = new short[3];
//                    threeTuples[count][0] = a;
//                    threeTuples[count][1] = b;
//                    threeTuples[count][2] = c;
//                    count++;
//                }
//            }
//        }
//        count = 0;
        Random random = new Random();
//        subsetSize = ticketNumberLimit;
        System.out.println("Checking if this set is a wheel that guarantees a " + numbersToMatch + "-match...");
        String line = "";
        int row = 0;
        for (i = 0; i < ticketNumberLimit; i++) {
            subsets[i] = new short[6];
        }
        for (i = 0; i < subsetSize; i++) {
            subsets2[i] = new short[6];
        }
        for (i = 0; i < subsetsCopy.length; i++) {
            subsetsCopy[i] = new short[6];
        }
//        for (i = 0; i < subsetSize; i++) {
//            subsets[i] = combinations[random.nextInt(possibleCombinations)];
//        }
//        previousIndex = ThreadLocalRandom.current().nextInt(0, 10000);
//        for (i = 0; i < subsetSize; i++) {
//            index = ThreadLocalRandom.current().nextInt(3482 * i - 20000, 3482 * i + 10000);
//            while (index < 0 || index >= 296010 || previousIndex >= index) {
//                index = ThreadLocalRandom.current().nextInt(3482 * i - 20000, 3482 * i + 10000);
//            }
//            for (j = 0; j < 6; j++) {
//                subsets[i][j] = combinations[index][j];
//            }
//            previousIndex = index;
//        }
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\stani\\Desktop\\input.txt"));
        try {
            line = br.readLine();
            while (line != null) {
                for (i = 0; i < 6; i++) {
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
        count = 0;

        for (i = 0; i < subsetSize; i++) {
            m = random.nextInt(ticketNumberLimit);
            while (usedNumbers.contains(m)) {
                m = random.nextInt(ticketNumberLimit);
            }
            for (j = 0; j < 6; j++) {
                subsets2[i][j] = subsets[m][j];
            }
            usedNumbers.add(m);
        }

//        for (i = 0; i < perturbation; i++) {
//            randomNumber = random.nextInt(subsetSize);
//            randomNumber2 = random.nextInt(6);
//            randomNumber3 = (byte) (random.nextInt(lotteryNumbers) + 1);
//            while (subsets2[randomNumber][0] == randomNumber3 || subsets2[randomNumber][1] == randomNumber3 || subsets2[randomNumber][2] == randomNumber3 || subsets2[randomNumber][3] == randomNumber3 ||
//                    subsets2[randomNumber][4] == randomNumber3 || subsets2[randomNumber][5] == randomNumber3) {
//                randomNumber3 = (byte) (random.nextInt(lotteryNumbers) + 1);
//            }
//            subsets2[randomNumber][randomNumber2] = randomNumber3;
//        }

        for (i = 0; i < subsetSize; i++) {
            for (j = 0; j < 6; j++) {
                subsetsCopy[i][j] = subsets2[i][j];
            }
        }

        for (i = 0; i < possibleCombinations; i++) {
            for (j = 0; j < subsetSize; j++) {
                if (intersection(combinations[i], subsets2[j]) >= numbersToMatch) {
                    maxNumberOfMatches++;
                    break;
                }
            }
        }
        test:
        while (lotteryNumbers <= 49) {
            count++;
//            System.out.println(count);
            if (maxNumberOfMatches > biggestMaxNumberOfMatches) {
                biggestMaxNumberOfMatches = maxNumberOfMatches;
                if (maxNumberOfMatches == possibleCombinations) {
                    System.out.println("A " + numbersToMatch + "-match-guaranteed wheel was found for " + lotteryNumbers + " lottery numbers with " + subsets2.length + " combinations!");
                    FileWriter fileWriter = new FileWriter("C:\\Users\\stani\\Desktop\\result.txt");
                    fileWriter.flush();
                    fileWriter.write("A " + numbersToMatch + "-match-guaranteed wheel was found for " + lotteryNumbers + " lottery numbers with " + subsets2.length + " combinations!" + "\n");
                    for (j = 0; j < subsets2.length; j++) {
//                        System.out.println(subsets2[j][0] + "," + subsets2[j][1] + "," + subsets2[j][2] + "," + subsets2[j][3] + "," + subsets2[j][4] + "," + subsets2[j][5]);
                        fileWriter.append(subsets2[j][0] + "," + subsets2[j][1] + "," + subsets2[j][2] + "," + subsets2[j][3] + "," + subsets2[j][4] + "," + subsets2[j][5] + "\n");
                    }
                    fileWriter.close();
                    lotteryNumbers++;
                    iterations = CombinatoricsUtils.binomialCoefficient(subsets2.length * pickedNumbers, 1) * CombinatoricsUtils.binomialCoefficient(lotteryNumbers - pickedNumbers, 1);
                    possibleCombinations = (int) CombinatoricsUtils.binomialCoefficient(lotteryNumbers, pickedNumbers);
                    combinations = new short[possibleCombinations][];
                    count = 0;
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
//                    return;
                } else {
//                    System.out.println("No " + numbersToMatch + "-match-guaranteed wheel was found!");
//                    System.out.println(maxNumberOfMatches);
//                    FileWriter fileWriter = new FileWriter("C:\\Users\\stani\\Desktop\\result.txt");
//                    fileWriter.flush();
//                    fileWriter.write("No " + numbersToMatch + "-match-guaranteed wheel was found!" + "\n" + "Number of matches is " + maxNumberOfMatches + "\n");
//                    for (j = 0; j < subsetSize; j++) {
//                        Arrays.sort(subsets2[j]);
//                    }
//                    Arrays.sort(subsets2, (q, r) -> Short.compare(q[0], r[0]));
                    for (j = 0; j < subsets2.length; j++) {
//                        System.out.println(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5]);
//                        fileWriter.append(subsets2[j][0] + "," + subsets2[j][1] + "," + subsets2[j][2] + "," + subsets2[j][3] + "," + subsets2[j][4] + "," + subsets2[j][5] + "\n");
                    }
//                    fileWriter.close();
                }
            }
            if (count == iterations) {
//                System.out.println("Adding 1 element...");
                maxNumberOfMatches = 0;
                count = 0;
                for (i = 0; i < subsets2.length; i++) {
                    for (j = 0; j < 6; j++) {
                        subsetsCopy[i][j] = subsets2[i][j];
                    }
                }
                subsets2 = new short[subsets2.length + 1][];
                for (i = 0; i < subsets2.length; i++) {
                    subsets2[i] = new short[6];
                }
                for (i = 0; i < subsets2.length - 1; i++) {
                    for (j = 0; j < 6; j++) {
                        subsets2[i][j] = subsetsCopy[i][j];
                    }
                }
                outer:
                for (i = 0; i < possibleCombinations; i++) {
                    for (j = 0; j < subsets2.length; j++) {
                        if (intersection(combinations[i], subsets2[j]) >= numbersToMatch) {
                            continue outer;
                        }
                    }
                    for (k = 0; k < 6; k++) {
                        subsets2[subsets2.length - 1][k] = combinations[i][k];
                    }
                    break;
                }
                iterations = CombinatoricsUtils.binomialCoefficient(subsets2.length * pickedNumbers, 1) * CombinatoricsUtils.binomialCoefficient(lotteryNumbers - pickedNumbers, 1);
            }
            numberOfMatches = 0;
            randomNumber = random.nextInt(subsets2.length);
            randomNumber2 = random.nextInt(6);
            randomNumber3 = (byte) (random.nextInt(lotteryNumbers) + 1);
            while (subsets2[randomNumber][0] == randomNumber3 || subsets2[randomNumber][1] == randomNumber3 || subsets2[randomNumber][2] == randomNumber3 || subsets2[randomNumber][3] == randomNumber3 ||
                    subsets2[randomNumber][4] == randomNumber3 || subsets2[randomNumber][5] == randomNumber3) {
                randomNumber3 = (byte) (random.nextInt(lotteryNumbers) + 1);
            }
            g = (byte) subsets2[randomNumber][randomNumber2];
            subsets2[randomNumber][randomNumber2] = randomNumber3;
            for (i = 0; i < possibleCombinations; i++) {
                for (j = 0; j < subsets2.length; j++) {
                    if (intersection(combinations[i], subsets2[j]) >= numbersToMatch) {
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
//                System.out.println("Yes");
            } else {
                subsets2[randomNumber][randomNumber2] = g;
            }
        }
    }
}
