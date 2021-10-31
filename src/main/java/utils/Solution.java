package utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    public static List<String> findSolution(String availableCharacters, List<String> candidates) {
        int j, maxLength = 0;
        List<String> solution = new ArrayList<>();
        List<String> finalSolution = new ArrayList<>();
        Map<String, Long> availableChars = IntStream.range(0, availableCharacters.length())
                .mapToObj(i -> availableCharacters.substring(i, i + 1))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (String candidate : candidates) {
            Map<String, Long> candidateChars = IntStream.range(0, candidate.length())
                    .mapToObj(i -> candidate.substring(i, i + 1))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            for (j = 0; j < candidate.length(); j++) {
                if (availableChars.containsKey(candidate.charAt(j)) && availableChars.get(candidate.charAt(j)) < candidateChars.get(candidate.charAt(j))) {
                    break;
                }
            }
            if (j == candidate.length()) {
                if (maxLength < candidate.length()) {
                    maxLength = candidate.length();
                }
                solution.add(candidate);
            }
        }
        for (String element : solution) {
            if (element.length() == maxLength) {
                finalSolution.add(element);
            }
        }

        return finalSolution;
    }

    public static void main(String[] args) {
        String availCharacters = "fhfhtknxsshbdhxlqeriop";
        List<String> candidates = new ArrayList<>();
        candidates.add("fhfhtknxsshbdhxl");
        candidates.add("knxsshbdhxlqeriop");
        candidates.add("hgfdstter");
        System.out.println(findSolution(availCharacters, candidates));
    }
}

