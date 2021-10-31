package utils;

import java.io.IOException;
import java.util.Arrays;

import static utils.Library.*;

public class Test {

    public static int[] solution(int[] numbers) {
        int i, product = 1;
        int[] array = new int[numbers.length];
        for (i = 0; i < numbers.length; i++) {
            product *= numbers[i];
        }
        for (i = 0; i < numbers.length; i++) {
            array[i] = product / numbers[i];
        }
        return array;
    }

    public static void main(String[] args) throws IOException {
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(solution(numbers)));

    }
}