package week9.probs;

public class q4 {

    public static int countAlerts(int[] readings, int k, int threshold) {
        if (readings == null || readings.length < k || k <= 0) {
            return 0;
        }

        long requiredSum = (long) k * threshold;
        long windowSum = 0;

        for (int i = 0; i < k; i++) {
            windowSum += readings[i];
        }

        int count = 0;
        if (windowSum >= requiredSum) {
            count++;
        }

        for (int i = k; i < readings.length; i++) {
            windowSum += readings[i] - readings[i - k];
            if (windowSum >= requiredSum) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        int threshold = 4;

        int result = countAlerts(readings, k, threshold);
        System.out.println(result);
    }
}
