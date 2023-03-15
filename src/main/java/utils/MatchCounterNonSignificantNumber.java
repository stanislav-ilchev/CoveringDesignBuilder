package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.IOException;

import static utils.Library.*;

public class MatchCounterNonSignificantNumber {

    public static void main(String[] args) throws IOException {
        int subsetSize = b;
        int tSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, t);
        int numberOfCoveredTuples, numberOfCoveredTuples2, bestNumberOfCoveredTuples = 0;
        int[][] tSets = buildCombinations(v, t);
        int oldNumber, randomNumber3;
        int numberOfMatches, numberOfTMatches, maxNumberOfMatches = 17550;
        int i, j, randomNumber, randomNumber2;
        int[][] wheel2;
        long iterations = b * k;
        int count;
//        byte[][] mIntersections = new byte[mSetsCount][kSetsCount];
//        byte[][] tIntersections = new byte[tSetsCount][kSetsCount];
//        for (i = 0; i < mSetsCount; i++) {
//            for (j = 0; j < kSetsCount; j++) {
//                mIntersections[i][j] = (byte) intersection(mSets[i], kSets[j]);
//            }
//        }
//        for (i = 0; i < tSetsCount; i++) {
//            for (j = 0; j < kSetsCount; j++) {
//                tIntersections[i][j] = (byte) intersection(tSets[i], kSets[j]);
//            }
//        }

        test:
        while (true) {
            count = 0;
            wheel2 = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\cover\\cover.res");
            numberOfCoveredTuples = 0;
            numberOfCoveredTuples2 = 0;

            while (true) {

                count++;
                if (count == iterations) {
                    break;
                }
                numberOfMatches = 0;
                randomNumber = random.nextInt(subsetSize);
                randomNumber2 = random.nextInt(k);
                randomNumber3 = random.nextInt(v);
                while (contains(wheel2[randomNumber], randomNumber3)) {
                    randomNumber = random.nextInt(subsetSize);
                    randomNumber2 = random.nextInt(k);
                    randomNumber3 = random.nextInt(v);
                }
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

                if (numberOfMatches == maxNumberOfMatches) {

                    wheel2[randomNumber][randomNumber2] = 27;

                    test2:
                    for (i = 0; i < mSetsCount; i++) {
                        for (j = 0; j < b; j++) {
                            if (intersection(mSets[i], wheel2[j]) >= t) {
                                continue test2;
                            }
                        }
                        continue test;
                    }

                    wheel2[randomNumber][randomNumber2] = randomNumber3;

                    for (i = 0; i < tSetsCount; i++) {
                        numberOfTMatches = 0;
                        for (j = 0; j < b; j++) {
                            if (intersection(tSets[i], wheel2[j]) == t) {
                                numberOfTMatches++;
                                if (numberOfTMatches == 1) {
                                    numberOfCoveredTuples++;
                                }
                                break;
                            }
                        }
                    }

                    wheel2[randomNumber][randomNumber2] = 27;

                    for (i = 0; i < tSetsCount; i++) {
                        numberOfTMatches = 0;
                        for (j = 0; j < b; j++) {
                            if (intersection(tSets[i], wheel2[j]) == t) {
                                numberOfTMatches++;
                                if (numberOfTMatches == 1) {
                                    numberOfCoveredTuples2++;
                                }
                                break;
                            }
                        }
                    }

                    int diff = numberOfCoveredTuples - numberOfCoveredTuples2;

                    if (numberOfCoveredTuples - diff + 10 > bestNumberOfCoveredTuples) {
                        bestNumberOfCoveredTuples = numberOfCoveredTuples - diff + 10;
                        System.out.println(numberOfCoveredTuples - diff + 10);
                        writeToFile(wheel2);
                    }

                    break;
                } else {
                    wheel2[randomNumber][randomNumber2] = oldNumber;
                }
            }
        }
    }
}