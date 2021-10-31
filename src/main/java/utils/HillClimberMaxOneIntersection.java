package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class HillClimberMaxOneIntersection {

    public static int intersection(short[] list1, short[] list2) {
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
        int lotteryNumbers = 27;
        int pickedNumbers = 6;
        int ticketNumberLimit = 86;
        int possibleSixTuples = (int) CombinatoricsUtils.binomialCoefficient(lotteryNumbers, 6);
        int subsetSize = ticketNumberLimit;
        boolean readFile = false;
        byte a, b, c, d, e, f, g, randomNumber3, numberOfImprovements = 0;
        int oneIntersection = 0, biggestOneIntersection = 0, maxOneIntersection = 0, intersectionSize;
        int i, j, k, l, m, n, o, count = 0, numbersToMatch = 3, randomNumber, randomNumber2;
        long iterations = CombinatoricsUtils.binomialCoefficient(subsetSize * pickedNumbers, 1) * CombinatoricsUtils.binomialCoefficient(lotteryNumbers - pickedNumbers, 1);
        short[][] sixTuples = new short[possibleSixTuples][];
        short[][] subsets = new short[ticketNumberLimit][];
        short[][] subsets2 = new short[subsetSize][];
        short[][] subsetsCopy = new short[ticketNumberLimit][];
        short[][][] used = new short[subsetSize][pickedNumbers][lotteryNumbers + 1];
        List<Integer> usedNumbers = new ArrayList<>();
        BufferedReader br;
        for (a = 1; a <= lotteryNumbers - 5; a++) {
            for (b = (byte) (a + 1); b <= lotteryNumbers - 4; b++) {
                for (c = (byte) (b + 1); c <= lotteryNumbers - 3; c++) {
                    for (d = (byte) (c + 1); d <= lotteryNumbers - 2; d++) {
                        for (e = (byte) (d + 1); e <= lotteryNumbers - 1; e++) {
                            for (f = (byte) (e + 1); f <= lotteryNumbers; f++) {
                                sixTuples[count] = new short[6];
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
        String line = "";
        int row = 0;
        for (i = 0; i < ticketNumberLimit; i++) {
            subsets[i] = new short[6];
            subsetsCopy[i] = new short[6];
        }
        for (i = 0; i < subsetSize; i++) {
            subsets2[i] = new short[6];
        }
        if (!readFile) {
            for (i = 0; i < subsetSize; i++) {
                subsets[i] = sixTuples[random.nextInt(possibleSixTuples)];
            }
        } else {
            br = new BufferedReader(new FileReader("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt"));
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
        for (i = 0; i < ticketNumberLimit; i++) {
            for (j = 0; j < 6; j++) {
                subsetsCopy[i][j] = subsets[i][j];
            }
        }
        for (i = 0; i < subsetSize; i++) {
            for (j = i + 1; j < subsetSize; j++) {
                intersectionSize = intersection(subsets2[i], subsets2[j]);
                if (intersectionSize == 1) {
                    maxOneIntersection++;
                }
            }
        }
        test:
        while (true) {
            count++;
            if (maxOneIntersection > biggestOneIntersection) {
//                if (numberOfImprovements > 100) {
//                    return;
//                }
//                numberOfImprovements++;
                biggestOneIntersection = maxOneIntersection;
                System.out.println(biggestOneIntersection);
                FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
                fileWriter.flush();
                for (j = 0; j < subsetSize; j++) {
//                        System.out.println(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5]);
                    fileWriter.append(subsets2[j][0] + "," + subsets2[j][1] + "," + subsets2[j][2] + "," + subsets2[j][3] + "," + subsets2[j][4] + "," + subsets2[j][5] + "\n");
                }
                fileWriter.close();
            }
            if (count == iterations) {
                System.out.println("Restarting...");
                for (i = 0; i < subsetSize; i++) {
                    for (j = 0; j < pickedNumbers; j++) {
                        for (k = 0; k < lotteryNumbers + 1; k++) {
                            used[i][j][k] = 0;
                        }
                    }
                }
                if (!readFile) {
                    for (i = 0; i < ticketNumberLimit; i++) {
                        subsets[i] = sixTuples[random.nextInt(possibleSixTuples)];
                    }
                } else {
                    for (i = 0; i < ticketNumberLimit; i++) {
                        for (j = 0; j < 6; j++) {
                            subsets[i][j] = subsetsCopy[i][j];
                        }
                    }
                }
                count = 0;
                usedNumbers.clear();
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
                maxOneIntersection = 0;
                for (i = 0; i < subsetSize; i++) {
                    for (j = i + 1; j < subsetSize; j++) {
                        intersectionSize = intersection(subsets2[i], subsets2[j]);
                        if (intersectionSize == 1) {
                            maxOneIntersection++;
                        }
                    }
                }
            }
            oneIntersection = 0;
            randomNumber = random.nextInt(subsetSize);
            randomNumber2 = random.nextInt(6);
            randomNumber3 = (byte) (random.nextInt(lotteryNumbers) + 1);
            while (subsets2[randomNumber][0] == randomNumber3 || subsets2[randomNumber][1] == randomNumber3 || subsets2[randomNumber][2] == randomNumber3 || subsets2[randomNumber][3] == randomNumber3 ||
                    subsets2[randomNumber][4] == randomNumber3 || subsets2[randomNumber][5] == randomNumber3
                    || used[randomNumber][randomNumber2][randomNumber3] == 1) {
                randomNumber3 = (byte) (random.nextInt(lotteryNumbers) + 1);
            }
            used[randomNumber][randomNumber2][randomNumber3] = 1;
            g = (byte) subsets2[randomNumber][randomNumber2];
            subsets2[randomNumber][randomNumber2] = randomNumber3;
            for (i = 0; i < subsetSize; i++) {
                for (j = i + 1; j < subsetSize; j++) {
                    intersectionSize = intersection(subsets2[i], subsets2[j]);
                    if (intersectionSize == 1) {
                        oneIntersection++;
                    }
                }
            }
            if (oneIntersection >= maxOneIntersection) {
                count = 0;
                maxOneIntersection = oneIntersection;
                for (i = 0; i < subsetSize; i++) {
                    for (j = 0; j < pickedNumbers; j++) {
                        for (k = 0; k < lotteryNumbers + 1; k++) {
                            used[i][j][k] = 0;
                        }
                    }
                }
            } else {
                subsets2[randomNumber][randomNumber2] = g;
            }
        }
    }
}