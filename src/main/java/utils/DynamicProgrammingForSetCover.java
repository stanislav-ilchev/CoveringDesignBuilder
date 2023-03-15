package utils;

import java.io.IOException;
import java.util.Arrays;

import static utils.Library.*;

public class DynamicProgrammingForSetCover {

    static int i, k, l;
    static final int INFINITY = 1000000000;
    static int[][] U;
    static int[][][] F;
    static int[][] S;

    static {
        try {
            U = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\Lottery Wheeling\\1224.txt");
            S = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\Lottery Wheeling\\1224.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int[][][] computeF(int[][] U) {
        int[][][] F = new int[2][][];
        F[0] = buildCombinations(27, 3);
        F[1] = buildCombinations(27, 3);
        for (i = 0; i < 1; i++) {
            for (k = 0; k < 3; k++) {
                F[0][i][k] = 0;
            }
        }
        return F;
    }

    static int minCover(int[][] X, int j) {
        if (isEmpty(S) && j == 0) {
            return 0;
        } else if (!isEmpty(S) && j == 0) {
            return INFINITY;
        }
        // F[j] = {S1, S2, ..., Sj}
        // Remove Sj from S
        for (i = 0; i < F[j - 1].length; i++) {
            for (k = 0; k < S.length; k++) {
                if (Arrays.equals(F[j - 1][i], S[k])) {
                    S[k] = null;
                    break;
                }
            }
        }
        return Math.min(minCover(X, j - 1), minCover(S, j - 1) + 1);
    }

    public static void main(String[] args) {
        F = computeF(U);
        System.out.println(minCover(U, F.length));
    }
}