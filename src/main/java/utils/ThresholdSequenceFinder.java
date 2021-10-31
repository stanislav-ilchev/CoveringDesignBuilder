package utils;

import java.io.IOException;
import java.util.Arrays;

import static utils.Library.*;

public class ThresholdSequenceFinder {

    public static void main(String[] args) throws IOException {
        int i, j, l, randomNumber;
        int[][] wheel = new int[b][k];
        int[] thresholds = new int[1000];
        for (l = 0; l < 1000; l++) {
            wheel = readFromFile();
//            for (i = 0; i < b; i++) {
//                randomNumber = random.nextInt(kSetsCount);
//                for (j = 0; j < k; j++) {
//                    wheel[i][j] = kSets[randomNumber][j];
//                }
//            }
            thresholds[l] = Math.abs(calculateCost(wheel) - calculateCost(generateRandomNeighbor(wheel)));
        }
        Arrays.sort(thresholds);
        System.out.println(Arrays.toString(thresholds));
    }
}