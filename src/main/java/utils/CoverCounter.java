package utils;

import java.io.IOException;
import java.util.*;

import static java.lang.System.*;
import static utils.Library.*;

public class CoverCounter {

    public static void main(String[] args) throws IOException {
        int i, j;
        boolean startFromFile = true;
        int[][] kSets;
        int[] coverings = new int[mSetsCount];
        if (startFromFile) {
            kSets = readFromFile("C:\\Users\\stanislav.ilchev\\Desktop\\input.txt");
        } else {
            kSets = generateRandomSubsets(b, k);
        }
        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < b; j++) {
                if (intersection(mSets[i], kSets[j]) >= t) {
                    coverings[i]++;
                }
            }
        }
        Arrays.sort(coverings);
        out.println(Arrays.toString(coverings));
    }
}