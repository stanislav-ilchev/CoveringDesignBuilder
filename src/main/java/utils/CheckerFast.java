package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static utils.Library.*;

public class CheckerFast {

    public static void main(String[] args) throws IOException {
        int v = 27, m = 4, t = 3, b = 86;
        int mSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, m);
        int i, j;
        int[][] mSets = buildCombinations(v, m);
        Collections.shuffle(Arrays.asList(mSets));
        int[][] subsets = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt");
        System.out.println("Checking if this set is a wheel that guarantees a " + t + "-match...");
        test2:
        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < b; j++) {
                if (intersection(mSets[i], subsets[j]) >= t) {
                    continue test2;
                }
            }
            System.out.println("No " + t + "-match-guaranteed wheel was found!");
            return;
        }
        System.out.println("A " + t + "-match-guaranteed wheel was found!");
    }
}