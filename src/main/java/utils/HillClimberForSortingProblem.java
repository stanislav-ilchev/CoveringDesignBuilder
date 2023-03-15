package utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static utils.Library.*;

public class HillClimberForSortingProblem {

    public static void main(String[] args) throws IOException {
        int i, cost, numbersCount = 30, bestCost = 0, randomNumber1, position1, randomNumber2, position2;
        int[] numbers = new int[numbersCount];
        Random random = new Random();
        for (i = 0; i < numbersCount; i++) {
            numbers[i] = i;
        }
        shuffle(numbers);
        while (bestCost < numbersCount - 1) {
            cost = 0;
            position1 = random.nextInt(numbersCount);
            position2 = random.nextInt(numbersCount);
            randomNumber1 = numbers[position1];
            randomNumber2 = numbers[position2];
            numbers[position1] = randomNumber2;
            numbers[position2] = randomNumber1;
            for (i = 0; i < numbersCount - 1; i++) {
                if (numbers[i] <= numbers[i + 1]) {
                    cost++;
                }
            }
            if (cost >= bestCost) {
                if (cost > bestCost) {
                    bestCost = cost;
                    System.out.println(bestCost);
                }
            } else {
                numbers[position1] = randomNumber1;
                numbers[position2] = randomNumber2;
            }
        }
        System.out.println(Arrays.toString(numbers));
    }
}