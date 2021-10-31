package utils;

import java.io.IOException;

import static utils.Library.*;

public class Checker {

    public static void main(String[] args) throws IOException {
        int i, j, numberOfMatches = 0;
        int[][] subsets = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt");
        System.out.println("Checking if this set is a wheel that guarantees a " + t + "-match...");
        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < b; j++) {
                if (intersection(mSets[i], subsets[j]) >= t) {
                    numberOfMatches++;
                    break;
                }
            }
            if (j == b) {
                printMSet(mSets[i]);
            }
        }
        if (numberOfMatches == mSetsCount) {
            System.out.println("A " + t + "-match-guaranteed wheel was found!");
        } else {
            System.out.println("No " + t + "-match-guaranteed wheel was found!");
            System.out.println("Number of matches is " + numberOfMatches);
        }
    }
}