package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.IOException;

import static utils.Library.*;

public class Checker {

    public static void main(String[] args) throws IOException {
        int v = 27, m = 4, t = 3, b = 86;
        int mSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, m);
        int i, j, numberOfMatches = 0;
        int[][] mSets = buildCombinations(v, m);
        int[][] subsets = readFromFile("C:\\Users\\stanislav.ilchev\\Desktop\\input.txt");
        System.out.println("Checking if this set is a wheel that guarantees a " + t + "-match...");
        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < b; j++) {
                if (intersection(mSets[i], subsets[j]) >= t) {
                    numberOfMatches++;
                    break;
                }
            }
//            if (j == subsetSize) {
//                System.out.println(Arrays.toString(kSets[i]));
//            }
        }
        if (numberOfMatches == mSetsCount) {
            System.out.println("A " + t + "-match-guaranteed wheel was found!");
        } else {
            System.out.println("No " + t + "-match-guaranteed wheel was found!");
            System.out.println("Number of matches is " + numberOfMatches);
        }
    }
}