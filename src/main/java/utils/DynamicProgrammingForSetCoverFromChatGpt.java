package utils;

import java.util.*;

public class DynamicProgrammingForSetCoverFromChatGpt {

    public static List<Integer> getMinimumSetCover(List<Set<Integer>> sets, Set<Integer> universe) {
        int n = sets.size();
        int[] dp = new int[1 << n];
        int[] parent = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    int newMask = mask | (1 << i);
                    if (dp[mask] + 1 < dp[newMask]) {
                        dp[newMask] = dp[mask] + 1;
                        parent[newMask] = mask;
                    }
                }
            }
        }
        List<Integer> cover = new ArrayList<>();
        int mask = (1 << n) - 1;
        while (mask > 0) {
            int prevMask = parent[mask];
            int diff = mask ^ prevMask;
            for (int i = 0; i < n; i++) {
                if ((diff & (1 << i)) > 0) {
                    cover.add(i);
                }
            }
            mask = prevMask;
        }
        Collections.reverse(cover);
        return cover;
    }

    public static void main(String[] args) {
        List<Set<Integer>> sets = new ArrayList<>();
        sets.add(new HashSet<>(Arrays.asList(1, 2, 3)));
        sets.add(new HashSet<>(Arrays.asList(2, 4)));
        sets.add(new HashSet<>(Arrays.asList(3, 4)));
        sets.add(new HashSet<>(Arrays.asList(4, 5)));
        Set<Integer> universe = new HashSet<>(Arrays.asList(1, 2, 3));
        List<Integer> cover = getMinimumSetCover(sets, universe);
        System.out.println(cover);
    }
}
