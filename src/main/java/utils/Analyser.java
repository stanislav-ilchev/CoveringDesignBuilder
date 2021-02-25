package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static utils.Library.*;

public class Analyser {

    public static void main(String[] args) throws IOException {
        int start = 0;
        int i, j, l, numberOfMatches, count = 1, previousCoverage = 435, bestNumberOfMatches = 0, bestSetNumber = 0, randomNumber;
        int[][] wheel = new int[b][k];
        boolean startFromFile = true;
        Random random = new Random();
        if (startFromFile) {
            wheel = readFromFile();
        } else {
            for (i = 0; i < b; i++) {
                randomNumber = random.nextInt(kSetsCount);
                for (j = 0; j < k; j++) {
                    wheel[i][j] = kSets[randomNumber][j];
                }
            }
        }
        int[][] result = new int[b][k];
        List<Integer> used = new ArrayList<>();
        result[0] = wheel[start];
        used.add(start);
        System.out.println(previousCoverage);
        while (count < b) {
            for (i = 0; i < b; i++) {
                if (used.contains(i)) {
                    continue;
                }
                result[count] = wheel[i];
                numberOfMatches = 0;
                for (j = 0; j < mSetsCount; j++) {
                    for (l = 0; l <= count; l++) {
                        if (intersection(mSets[j], result[l]) >= t) {
                            numberOfMatches++;
                            break;
                        }
                    }
                }
                if (numberOfMatches > bestNumberOfMatches) {
                    bestNumberOfMatches = numberOfMatches;
                    bestSetNumber = i;
                }
            }
            result[count] = wheel[bestSetNumber];
            used.add(bestSetNumber);
            System.out.println(bestNumberOfMatches - previousCoverage);
            previousCoverage = bestNumberOfMatches;
            count++;
        }
    }
}