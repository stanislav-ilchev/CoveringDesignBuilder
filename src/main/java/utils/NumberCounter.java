package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.IOException;
import java.util.Random;

import static utils.Library.buildCombinations;
import static utils.Library.readFromFile;

public class NumberCounter {

    public static void main(String[] args) throws IOException {
        int v = 27, k = 6, b = 86;
        boolean startFromFile = true;
        int i, j, l;
        int possibleCombinations = (int) CombinatoricsUtils.binomialCoefficient(v, k);
        int[][] subsets = new int[b][k];
        int[] frequencies = new int[v];
        int[][] combinations = buildCombinations(v, k);
        int randomNumber;
        Random random = new Random();
        if (startFromFile) {
            subsets = readFromFile("C:\\Users\\stanislav.ilchev\\Desktop\\input.txt");
        } else {
            for (i = 0; i < b; i++) {
                randomNumber = random.nextInt(possibleCombinations);
                for (j = 0; j < k; j++) {
                    subsets[i][j] = combinations[randomNumber][j];
                }
            }
        }
        for (i = 0; i < b; i++) {
            for (j = 0; j < k; j++) {
                for (l = 0; l < v; l++) {
                    if (subsets[i][j] == l) {
                        frequencies[l] += 1;
                        break;
                    }
                }
            }
        }
        for (i = 0; i < v; i++) {
            System.out.println((i) + "\t" + frequencies[i]);
        }
    }
}