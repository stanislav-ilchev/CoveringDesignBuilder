package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.apache.commons.math3.util.CombinatoricsUtils;

import static utils.Library.*;
import static utils.Library.random;

public class HillClimberMinOverlap {

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
        int subsetSize = b;
        boolean startFromFile = true;
        int zeroIntersection = 0, oneIntersection = 0, twoIntersection = 0, threeIntersection = 0, fourIntersection = 0, fiveIntersection = 0, sixIntersection = 0;
        byte g;
        int randomNumber3;
        int overlappingNumber, initialOverlappingNumber = 0, minOverlappingNumber, intersectionSize;
        int i, j, l, m, count, randomNumber, randomNumber2;
        long iterations = CombinatoricsUtils.binomialCoefficient(subsetSize * k, 1) * CombinatoricsUtils.binomialCoefficient(v - k, 1);
        int[][] sixTuples = buildCombinations(v, k);
        int[][] subsets = new int[b][k];
        int[][] subsets2 = new int[subsetSize][k];
        short[][][] used = new short[subsetSize][k][v + 1];
        List<Integer> usedNumbers = new ArrayList<>();
        BufferedReader br;
        Random random = new Random();
        String line;
        int row = 0;
        if (!startFromFile) {
            for (i = 0; i < subsetSize; i++) {
                subsets[i] = sixTuples[random.nextInt(kSetsCount)];
            }
        } else {
            br = new BufferedReader(new FileReader("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt"));
            try {
                line = br.readLine();
                while (line != null) {
                    for (i = 0; i < k; i++) {
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
            m = random.nextInt(b);
            while (usedNumbers.contains(m)) {
                m = random.nextInt(b);
            }
            for (j = 0; j < k; j++) {
                subsets2[i][j] = subsets[m][j];
            }
            usedNumbers.add(m);
        }
        for (i = 0; i < subsetSize; i++) {
            for (j = i + 1; j < subsetSize; j++) {
                intersectionSize = intersection(subsets2[i], subsets2[j]);
                initialOverlappingNumber += intersectionSize;
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
        System.out.println(zeroIntersection + " " + oneIntersection + " " + twoIntersection + " " + threeIntersection + " " + fourIntersection + " " + fiveIntersection + " " + sixIntersection);
        minOverlappingNumber = initialOverlappingNumber + 1;
        test:
        while (initialOverlappingNumber >= 0) {
            count++;
            if (initialOverlappingNumber < minOverlappingNumber) {
                minOverlappingNumber = initialOverlappingNumber;
                System.out.println(initialOverlappingNumber);
                System.out.println(zeroIntersection + " " + oneIntersection + " " + twoIntersection + " " + threeIntersection + " " + fourIntersection + " " + fiveIntersection + " " + sixIntersection);
                FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
                fileWriter.flush();
                print(subsets2);
//                for (j = 0; j < subsetSize; j++) {
////                        System.out.println(subsets[j][0] + "," + subsets[j][1] + "," + subsets[j][2] + "," + subsets[j][3] + "," + subsets[j][4] + "," + subsets[j][5]);
//                    fileWriter.append(subsets2[j][0] + "," + subsets2[j][1] + "," + subsets2[j][2] + "," + subsets2[j][3] + "," + subsets2[j][4] + "," + subsets2[j][5] + "\n");
//                }
                fileWriter.close();
            }
            zeroIntersection = 0;
            oneIntersection = 0;
            twoIntersection = 0;
            threeIntersection = 0;
            fourIntersection = 0;
            fiveIntersection = 0;
            sixIntersection = 0;
            if (initialOverlappingNumber == 0) {
                return;
            }
            if (count == iterations) {
                System.out.println("Restarting...");
                zeroIntersection = 0;
                oneIntersection = 0;
                twoIntersection = 0;
                threeIntersection = 0;
                fourIntersection = 0;
                fiveIntersection = 0;
                sixIntersection = 0;
                for (i = 0; i < subsetSize; i++) {
                    for (j = 0; j < k; j++) {
                        for (l = 0; l < v + 1; l++) {
                            used[i][j][l] = 0;
                        }
                    }
                }
                if (!startFromFile) {
                    for (i = 0; i < b; i++) {
                        subsets[i] = sixTuples[random.nextInt(kSetsCount)];
                    }
                } else {
                }
                count = 0;
                usedNumbers.clear();
                for (i = 0; i < subsetSize; i++) {
                    m = random.nextInt(b);
                    while (usedNumbers.contains(m)) {
                        m = random.nextInt(b);
                    }
                    for (j = 0; j < k; j++) {
                        subsets2[i][j] = subsets[m][j];
                    }
                    usedNumbers.add(m);
                }
                initialOverlappingNumber = 0;
                for (i = 0; i < subsetSize; i++) {
                    for (j = i + 1; j < subsetSize; j++) {
                        intersectionSize = intersection(subsets2[i], subsets2[j]);
                        initialOverlappingNumber += intersectionSize;
                    }
                }
                minOverlappingNumber = initialOverlappingNumber + 1;
            }
            overlappingNumber = 0;
            randomNumber = random.nextInt(subsetSize);
            randomNumber2 = random.nextInt(k);
            randomNumber3 = random.nextInt(v);
            while (contains(subsets2[randomNumber], randomNumber3) || used[randomNumber][randomNumber2][randomNumber3] == 1) {
                randomNumber = random.nextInt(subsetSize);
                randomNumber2 = random.nextInt(k);
                randomNumber3 = random.nextInt(v);
            }
            used[randomNumber][randomNumber2][randomNumber3] = 1;
            g = (byte) subsets2[randomNumber][randomNumber2];
            subsets2[randomNumber][randomNumber2] = randomNumber3;
            for (i = 0; i < subsetSize; i++) {
                for (j = i + 1; j < subsetSize; j++) {
                    intersectionSize = intersection(subsets2[i], subsets2[j]);
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
            if (overlappingNumber <= initialOverlappingNumber) {
                count = 0;
                initialOverlappingNumber = overlappingNumber;
                for (i = 0; i < subsetSize; i++) {
                    for (j = 0; j < k; j++) {
                        for (l = 0; l < v + 1; l++) {
                            used[i][j][l] = 0;
                        }
                    }
                }
            } else {
                subsets2[randomNumber][randomNumber2] = g;
            }
        }
    }
}