package week9.probs;

import java.util.Arrays;

public class q2 {

    public static int[] mergeTokens(int[] counterA, int[] counterB) {
        int m = counterA == null ? 0 : counterA.length;
        int n = counterB == null ? 0 : counterB.length;
        int[] merged = new int[m + n];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < m && j < n) {
            if (counterA[i] <= counterB[j]) {
                merged[k++] = counterA[i++];
            } else {
                merged[k++] = counterB[j++];
            }
        }

        while (i < m) {
            merged[k++] = counterA[i++];
        }

        while (j < n) {
            merged[k++] = counterB[j++];
        }

        return merged;
    }

    public static void main(String[] args) {
        int[] counterA1 = {3, 8, 15, 20};
        int[] counterB1 = {5, 8, 12};
        System.out.println(Arrays.toString(mergeTokens(counterA1, counterB1)));

        int[] counterA2 = {};
        int[] counterB2 = {4, 9};
        System.out.println(Arrays.toString(mergeTokens(counterA2, counterB2)));
    }
}
