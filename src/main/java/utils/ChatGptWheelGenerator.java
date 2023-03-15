package utils;

import java.util.*;

public class ChatGptWheelGenerator {
    public static void main(String[] args) {
        int[] numbers = new int[27];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }
        List<int[]> combinations = generateCombinations(numbers, 6);
        Collections.shuffle(combinations);
        List<int[]> groups = new ArrayList<>();
        for (int i = 0; i < combinations.size(); i += 3) {
            groups.add(combinations.subList(i, Math.min(i + 3, combinations.size())).toArray(new int[0][])[0]);
        }
        List<int[][]> wheels = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Collections.shuffle(groups);
            wheels.add(groups.toArray(new int[0][]));
        }
        System.out.println(Arrays.deepToString(wheels.toArray()));
    }

    private static List<int[]> generateCombinations(int[] numbers, int k) {
        List<int[]> combinations = new ArrayList<>();
        generateCombinationsHelper(numbers, k, new int[k], 0, 0, combinations);
        return combinations;
    }

    private static void generateCombinationsHelper(int[] numbers, int k, int[] combination, int index, int start, List<int[]> combinations) {
        if (index == k) {
            combinations.add(combination.clone());
        } else {
            for (int i = start; i < numbers.length; i++) {
                combination[index] = numbers[i];
                generateCombinationsHelper(numbers, k, combination, index + 1, i + 1, combinations);
            }
        }
    }
}
