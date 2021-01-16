package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static utils.Library.*;

public class MatchCounter {

    public static void main(String[] args) throws IOException {
        int i, j, l, numberOfMatches, numberOfCoveredTuples = 0;
        int tSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, t);
        int[][] tSets = buildCombinations(v, t);
        int[][] subsets = readFromFile();
        String matchingTuples;
        FileWriter fileWriter = new FileWriter("C:\\Users\\stanislav.ilchev\\Desktop\\result.txt");
        for (i = 0; i < tSetsCount; i++) {
            numberOfMatches = 0;
            matchingTuples = "";
            for (j = 0; j < b; j++) {
                if (intersection(tSets[i], subsets[j]) == t) {
                    numberOfMatches++;
                    matchingTuples = matchingTuples + Arrays.toString(subsets[j]) + " ";
                    if (numberOfMatches == 1) {
                        numberOfCoveredTuples++;
                    }
                    break;
                }
            }
            if (numberOfMatches != 0) {
                fileWriter.flush();
//                fileWriter.append(Arrays.toString(tSets[i]) + " is covered " + numberOfMatches + " times in " + matchingTuples + "\n");
//                System.out.println(Arrays.toString(tSets[i]) + " is covered " + numberOfMatches + " times in " + matchingTuples);
                for (l = 0; l < t; l++) {
                    fileWriter.append(tSets[i][l] + " ");
                }
                fileWriter.append("\n");
            }
        }
//        fileWriter.write("The number of " + t + "-tuples covered is " + numberOfCoveredTuples);
        fileWriter.close();
        System.out.println("The number of " + t + "-tuples covered is " + numberOfCoveredTuples);
    }
}