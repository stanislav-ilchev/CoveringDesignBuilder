package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.IOException;

import static utils.Library.*;

public class Checker {

    public static void main(String[] args) throws IOException {
        int v = 49, k = 6, t = 3, b = 163;
        int kSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, k);
        int i, j, numberOfMatches = 0;
        int[][] kSets = buildCombinations(v, k);
        int[][] subsets = readFromFile("C:\\Users\\stanislav.ilchev\\Desktop\\input.txt");
        System.out.println("Checking if this set is a wheel that guarantees a " + t + "-match...");
        for (i = 0; i < kSetsCount; i++) {
            for (j = 0; j < b; j++) {
                if (intersection(kSets[i], subsets[j]) >= t) {
                    numberOfMatches++;
                    break;
                }
            }
//            if (j == subsetSize) {
//                System.out.println(Arrays.toString(kSets[i]));
//            }
        }
        if (numberOfMatches == kSetsCount) {
            System.out.println("A " + t + "-match-guaranteed wheel was found!");
        } else {
            System.out.println("No " + t + "-match-guaranteed wheel was found!");
            System.out.println("Number of matches is " + numberOfMatches);
        }
    }
}