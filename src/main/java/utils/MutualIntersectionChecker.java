package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class MutualIntersectionChecker {

    public static int intersection(int[] list1, int[] list2) {
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
        int lotteryNumbers = 27;
        int pickedNumbers = 6;
        int ticketNumberLimit = 86;
        int possibleSixTuples = (int) CombinatoricsUtils.binomialCoefficient(lotteryNumbers, 6);
        int possibleCombinations = (int) CombinatoricsUtils.binomialCoefficient(lotteryNumbers, pickedNumbers);
        int subsetSize, intersectionSize, count = 0;
        int zeroIntersection = 0, oneIntersection = 0, twoIntersection = 0, threeIntersection = 0, fourIntersection = 0, fiveIntersection = 0, sixIntersection = 0;
        int a, b, c, d, e, f, i, j;
        int overlappingNumber = 0;
        int[][] subsets = new int[ticketNumberLimit][];
        int[][] sixTuples = new int[possibleSixTuples][];
        subsetSize = ticketNumberLimit;
        boolean readFile = true;
        String line = "";
        int row = 0;
        Random random = new Random();
        for (i = 0; i < ticketNumberLimit; i++) {
            subsets[i] = new int[6];
        }
        for (a = 1; a <= lotteryNumbers - 5; a++) {
            for (b = (byte) (a + 1); b <= lotteryNumbers - 4; b++) {
                for (c = (byte) (b + 1); c <= lotteryNumbers - 3; c++) {
                    for (d = (byte) (c + 1); d <= lotteryNumbers - 2; d++) {
                        for (e = (byte) (d + 1); e <= lotteryNumbers - 1; e++) {
                            for (f = (byte) (e + 1); f <= lotteryNumbers; f++) {
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
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt"));
        if (readFile) {
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
        } else {
            for (i = 0; i < subsetSize; i++) {
                subsets[i] = sixTuples[random.nextInt(possibleSixTuples)];
            }
        }
        for (i = 0; i < subsetSize; i++) {
            for (j = i + 1; j < subsetSize; j++) {
                intersectionSize = intersection(subsets[i], subsets[j]);
                overlappingNumber += intersectionSize;
                if (intersectionSize == 0) {
                    zeroIntersection++;
                } else if (intersectionSize == 1) {
                    oneIntersection++;
                } else if (intersectionSize == 2) {
                    twoIntersection++;
                } else if (intersectionSize == 3) {
                    threeIntersection++;
                } else if (intersectionSize == 4) {
                    fourIntersection++;
                } else if (intersectionSize == 5) {
                    fiveIntersection++;
                } else if (intersectionSize == 6) {
                    sixIntersection++;
                }
            }
        }
        System.out.println(overlappingNumber);
        System.out.println(zeroIntersection + " " + oneIntersection + " " + twoIntersection + " " + threeIntersection + " " + fourIntersection + " " + fiveIntersection + " " + sixIntersection);
    }
}

