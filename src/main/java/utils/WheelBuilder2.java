package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

import static utils.Library.*;

public class WheelBuilder2 {

    public static void main(String[] args) throws IOException {
        int v = 27, k = 6, m = 4, t = 3, b = 86;
        int times = ((b * k) / v);
        int allow = b * k - v * times;
        int kSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, k);
        int i, j, count = 0, randomNumber;
        int[][] kSets = buildCombinations(v, k);
        int[][] wheel = new int[b][k];
        Random random = new Random();
        HashSet<Integer> usedKSets = new HashSet<>();
        randomNumber = random.nextInt(kSetsCount);
        wheel[count] = kSets[randomNumber];
        usedKSets.add(randomNumber);
        for (j = 0; j < k; j++) {
            System.out.print(wheel[count][j] + " ");
        }
        System.out.println();
        count++;
        while (usedKSets.size() < kSetsCount) {
            randomNumber = random.nextInt(kSetsCount);
            wheel[count] = kSets[randomNumber];
            usedKSets.add(randomNumber);
            while (usedKSets.size() < kSetsCount && (!isAnyNumberPresentAtMostNTimes(wheel, times, count, allow) || !areAnyTwoNumbersInAtMostOneKSet(wheel, count))) {
                randomNumber = random.nextInt(kSetsCount);
                usedKSets.add(randomNumber);
                wheel[count] = kSets[randomNumber];
            }
            if (isAnyNumberPresentAtMostNTimes(wheel, times, count, allow) && areAnyTwoNumbersInAtMostOneKSet(wheel, count)) {
                for (j = 0; j < k; j++) {
                    System.out.print(wheel[count][j] + " ");
                }
                System.out.println();
                count++;
            }
        }
    }
}