package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class TupleBuilder {

    public static int[][] buildTuples(int v, int k) {
        int i, count = 0, tuplesCount;
        int tuples[][];
        try {
            tuplesCount = (int) CombinatoricsUtils.binomialCoefficient(v, k);
        } catch (org.apache.commons.math3.exception.MathArithmeticException e) {
            System.out.println("MathArithmeticException!");
            return new int[1][];
        }
        try {
            tuples = new int[tuplesCount][];
        } catch (java.lang.OutOfMemoryError e) {
            System.out.println("OutOfMemoryError!");
            return new int[1][];
        }
        int total, r;
        int arrs[] = new int[k];
        int display[] = new int[99];
        for (i = 1; i <= 99; i++) {
            display[count] = i;
            count++;
        }
        count = 0;
        boolean status = false, change, skipPrint;
        for (i = 0; i < tuplesCount; i++) {
            tuples[i] = new int[k];
        }
        for (r = 0; r < k; r++)
            arrs[r] = 0;
//        long start = System.currentTimeMillis();
        while (!status) {
//            if (System.currentTimeMillis() - start > 100000) {
//                System.out.println("Too much time, probably an endless cycle!");
//                break;
//            }
            skipPrint = false;
            total = 0;
            for (r = 0; r < k; r++)
                total += arrs[r];
            if (total == (v - 1) * k)
                status = true;
            for (r = 0; r < k - 1; r++) {
                if (arrs[r + 1] <= arrs[r]) {
                    skipPrint = true;
                }
            }
            if (!skipPrint) {
                for (r = 0; r < k; r++) {
//                    System.out.print(display[arrs[r]] + " ");
                    tuples[count][r] = display[arrs[r]];
                }
//                System.out.println();
                count++;
            }
            change = true;
            r = k - 1;
            while (change && r >= 0) {
                if (++arrs[r] > v - 1) {
                    arrs[r] = 0;
                    change = true;
                } else {
                    change = false;
                }
                r = r - 1;
            }
        }
        return tuples;
    }

    public static void main(String[] args) {
        buildTuples(21, 15);
    }
}