package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import static utils.Library.readFromFile;
import static utils.TupleBuilder.buildTuples;

public class Sorter {

    public static void main(String[] args) throws IOException {
        int v = 27;
        int k = 3;
        int b = 1297;
        int kSetsCount = (int) CombinatoricsUtils.binomialCoefficient(v, k);
        int g, i, j, count;
        int[][] kSets = buildTuples(v, k);
        int[][] subsets = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt");
        Map<Integer, int[]> map = new TreeMap<>();
        FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
        for (j = 0; j < b; j++) {
            Arrays.sort(subsets[j]);
        }
        for (g = 0; g < b; g++) {
            for (i = 0; i < kSetsCount; i++) {
                count = 0;
                for (j = 0; j < k; j++) {
                    if (kSets[i][j] == subsets[g][j]) {
                        count++;
                    }
                }
                if (count == k) {
                    map.put(Integer.parseInt(String.valueOf(i)), subsets[g]);
                    break;
                }
            }
        }
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            System.out.println(Arrays.toString(entry.getValue()).replace("[", "").replace("]", ""));
            fileWriter.flush();
            fileWriter.append(Arrays.toString(entry.getValue()).replace("[", "").replace("]", "") + "\n");
        }
        fileWriter.close();
    }
}