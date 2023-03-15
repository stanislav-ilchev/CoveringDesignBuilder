package utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient;
import static utils.Library.shuffle;

public class HillClimberForSortingProblemTest {

    public static void main(String[] args) throws IOException {
        int i, cost, numbersCount = 30, bestCost = 0, randomNumber1, position1, randomNumber2, position2, counter = 0, iterations, randomNumber3, position3, randomNumber4, position4;
        int[] numbers = new int[numbersCount];
        Random random = new Random();
        for (i = 0; i < numbersCount; i++) {
            numbers[i] = i;
        }
        shuffle(numbers);
        iterations = (int) binomialCoefficient(numbersCount, 2);
        while (bestCost < numbersCount - 1) {
            counter++;
//            System.out.println(counter);
//            if (counter > iterations * 10000) {
//                for (i = 0; i < numbersCount; i++) {
//                    numbers[i] = i;
//                }
//                shuffle(numbers);
//                counter = 0;
//                bestCost = 0;
//                System.out.println("Restarting...");
//            }
            cost = 0;
            position1 = random.nextInt(numbersCount);
            position2 = random.nextInt(numbersCount);
            randomNumber1 = numbers[position1];
            randomNumber2 = numbers[position2];
            numbers[position1] = randomNumber2;
            numbers[position2] = randomNumber1;
            position3 = random.nextInt(numbersCount);
            position4 = random.nextInt(numbersCount);
            randomNumber3 = numbers[position3];
            randomNumber4 = numbers[position4];
            numbers[position3] = randomNumber4;
            numbers[position4] = randomNumber3;
            for (i = 0; i < numbersCount - 1; i++) {
                if (numbers[i] <= numbers[i + 1]) {
                    cost++;
                }
            }
            if (cost >= bestCost) {
//                counter = 0;
                if (cost > bestCost) {
                    bestCost = cost;
                    System.out.println(bestCost);
                }
            } else {
                numbers[position3] = randomNumber3;
                numbers[position4] = randomNumber4;
                numbers[position1] = randomNumber1;
                numbers[position2] = randomNumber2;
            }
        }
        System.out.println(Arrays.toString(numbers));
    }
}