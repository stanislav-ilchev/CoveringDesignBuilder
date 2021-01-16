package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.*;

public class DynamicProgramming {

    static final int INF = 100000;
    static int lotteryNumbers = 27;
    static int possibleSixTuples = (int) CombinatoricsUtils.binomialCoefficient(lotteryNumbers, 6);
    static int possibleFourTuples = (int) CombinatoricsUtils.binomialCoefficient(lotteryNumbers, 4);
    static int a, b, c, d, e, f, i, j, k, l, m, count, counter, numbersToMatch = 3, denomination = 0, index, limit;
    static int[][] sixTuples = new int[possibleSixTuples][];
    static int[][] fourTuples = new int[possibleFourTuples][];
    static int[] denominationsResult;
    static int[] denominations = new int[1];
    static Random random;
    static int level = 0;
    static int maxDenominationsCount = 1000;
    static int denominationsCount = 1;
    static int[][][] results = new int[possibleFourTuples][maxDenominationsCount][6];
    static int[] S;
    static int denominationLimits[] = new int[maxDenominationsCount];

    public static int coinsUsed(int[] S, int denomination) {
        int count = 0;
        for (i = 0; i < S.length; i++) {
            if (S[i] == denomination) {
                count++;
            }
        }
        return count;
    }

    public static int[] computeDenominations(int level) {
        denominations = new int[maxDenominationsCount];
        count = 1;
        int[][] array = new int[possibleFourTuples][];
        for (m = 0; m < 5; m++) {
            for (j = 0; j < level; j++) {
                index = random.nextInt(maxDenominationsCount);
                array[j] = results[j][index];
                limit = 0;
                while (results[j][index][0] == 0) {
                    index = random.nextInt(maxDenominationsCount);
                    array[j] = results[j][index];
                    limit++;
                    if (limit == maxDenominationsCount) {
                        break;
                    }
                }
            }
            for (i = 0; i < possibleFourTuples; i++) {
                for (j = 0; j < level; j++) {
                    if (fourTuples[i] != null && intersection(fourTuples[i], array[j]) >= numbersToMatch) {
                        fourTuples[i] = null;
                        break;
                    }
                }
            }
            for (l = 0; l < maxDenominationsCount; l++) {
                i = random.nextInt(possibleSixTuples);
                denomination = 0;
                for (j = 0; j < possibleFourTuples; j++) {
                    if (fourTuples[j] != null && intersection(sixTuples[i], fourTuples[j]) >= numbersToMatch) {
                        denomination++;
                    }
                }
                for (k = 0; k < count; k++) {
                    if (denominations[k] == denomination) {
                        break;
                    }
                }
                if (k == count) {
                    results[level][count - 1] = sixTuples[i];
                    System.out.println(count + " " + denomination);
                    denominations[count] = denomination;
                    count++;
                }
            }
            denominationsResult = new int[count];
            for (i = 0; i < count; i++) {
                denominationsResult[i] = denominations[i];
            }
            counter = 0;
            for (a = 1; a <= lotteryNumbers - 3; a++) {
                for (b = (byte) (a + 1); b <= lotteryNumbers - 2; b++) {
                    for (c = (byte) (b + 1); c <= lotteryNumbers - 1; c++) {
                        for (d = (byte) (c + 1); d <= lotteryNumbers; d++) {
                            fourTuples[counter] = new int[4];
                            fourTuples[counter][0] = a;
                            fourTuples[counter][1] = b;
                            fourTuples[counter][2] = c;
                            fourTuples[counter][3] = d;
                            counter++;
                        }
                    }
                }
            }
        }
        denominationsCount = count;
        Arrays.sort(denominationsResult);
        return denominationsResult;
    }

    //k is number of denominations of the coin or length of denominations
    public static int coinChange(int[] denominations, int n, int k) {
        int[] M = new int[n + 1];
        M[0] = 0;
        S = new int[n + 1];
        S[0] = 0;

        for (int j = 1; j <= n; j++) {
//            try {
//                j += denominations[k] - 1;
//                if (j > n) {
//                    j = n;
//                }
            System.out.println(j);
            int minimum = INF;
            int coin = 0;
            for (int i = 1; i <= k; i++) {
                if (j >= denominations[i]) {
                    if ((1 + M[j - denominations[i]]) < minimum) {
                        if (denominationLimits[denominations[i]] > 0) {
                            minimum = 1 + M[j - denominations[i]];
                            coin = i;
                        }
                    }
                }
            }
            M[j] = minimum;
            S[j] = coin;
            denominationLimits[denominations[coin]] = denominationLimits[denominations[coin]] - 1;
//                level++;
//                denominations = computeDenominations(level);
//                k = denominations.length - 1;
//            } catch (ArrayIndexOutOfBoundsException e) {
//                System.out.println(e.getStackTrace());
//                continue;
//            }
        }
        System.out.println("Coins:");
        int l = n;
        while (l > 0) {
            l = l - denominations[S[l]];
            if (denominations[S[l]] == 0) {
                break;
            }
            System.out.println(denominations[S[l]]);
        }
        System.out.println("Number of coins = " + M[n]);
        return M[n];
    }

    public static int intersection(int[] list1, int[] list2) {
        int intersectionSize = 0;
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if (list1[i] == list2[j]) {
                    intersectionSize++;
                    break;
                }
            }
        }
        return intersectionSize;
    }

    public static void main(String[] args) {
        random = new Random();
        for (a = 1; a <= lotteryNumbers - 5; a++) {
            for (b = a + 1; b <= lotteryNumbers - 4; b++) {
                for (c = b + 1; c <= lotteryNumbers - 3; c++) {
                    for (d = c + 1; d <= lotteryNumbers - 2; d++) {
                        for (e = d + 1; e <= lotteryNumbers - 1; e++) {
                            for (f = e + 1; f <= lotteryNumbers; f++) {
                                sixTuples[count] = new int[6];
                                sixTuples[count][0] = a;
                                sixTuples[count][1] = b;
                                sixTuples[count][2] = c;
                                sixTuples[count][3] = d;
                                sixTuples[count][4] = e;
                                sixTuples[count][5] = f;
                                count++;
                            }
                        }
                    }
                }
            }
        }
        count = 0;
        for (a = 1; a <= lotteryNumbers - 3; a++) {
            for (b = (byte) (a + 1); b <= lotteryNumbers - 2; b++) {
                for (c = (byte) (b + 1); c <= lotteryNumbers - 1; c++) {
                    for (d = (byte) (c + 1); d <= lotteryNumbers; d++) {
                        fourTuples[count] = new int[4];
                        fourTuples[count][0] = a;
                        fourTuples[count][1] = b;
                        fourTuples[count][2] = c;
                        fourTuples[count][3] = d;
                        count++;
                    }
                }
            }
        }
        for (i = 0; i < 6; i++) {
            results[0][0][i] = i + 1;
        }
        // array starting from 1, element at index 0 is fake
        denominations[0] = 0;
//        denominations[1] = 1;
//        denominations = computeDenominations(1);
//        System.out.println(coinChange(denominations, possibleFourTuples, denominations.length - 1));
        denominationLimits[1] = 20000;
//        denominationLimits[435] = 10;
//        int denominations[] = {0, 1, 435};
        for (i = 2; i <= 435; i++) {
            denominationLimits[i] = 10;
        }
        int[] denominations = new int[436];
        for (i = 0; i <= 435; i++) {
            denominations[i] = i;
        }
        coinChange(denominations, possibleFourTuples, denominations.length - 1);
    }
}