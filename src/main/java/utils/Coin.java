package utils;

class Coin {
    static final int INF = 100000;

    //k is number of denominations of the coin or length of d
    public static int coinChangeMod(int d[], int n, int k) {
        int[] M = new int[n + 1];
        M[0] = 0;

        int[] S = new int[n + 1];
        S[0] = 0;

        for (int j = 1; j <= n; j++) {
            System.out.println(j + " out of " + n);
            int minimum = INF;
            int coin = 0;

            for (int i = 1; i <= k; i++) {
                if (j >= d[i]) {
                    if ((1 + M[j - d[i]]) < minimum) {
                        minimum = 1 + M[j - d[i]];
                        coin = i;
                    }
                }
            }
            M[j] = minimum;
            S[j] = coin;
            System.out.println(coin);
        }

        int l = n;
        while (l > 0) {
            System.out.println(d[S[l]]);
            l = l - d[S[l]];
        }
        return M[n];
    }

    public static void main(String[] args) {
        // array starting from 1, element at index 0 is fake
        int d[] = {0, 1, 3, 4};
        System.out.println(coinChangeMod(d, 6, 3));
    }
}
