package week9.probs;

public class q1 {

    public static class Result {
        public int rowIndex;
        public int total;

        public Result(int rowIndex, int total) {
            this.rowIndex = rowIndex;
            this.total = total;
        }

        @Override
        public String toString() {
            return "(" + rowIndex + ", " + total + ")";
        }
    }

    public static Result findTopper(int[][] marks) {
        if (marks == null || marks.length == 0) {
            return new Result(-1, 0);
        }

        int bestRow = 0;
        int maxTotal = -1;

        for (int i = 0; i < marks.length; i++) {
            int currentTotal = 0;
            for (int j = 0; j < marks[i].length; j++) {
                currentTotal += marks[i][j];
            }

            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                bestRow = i;
            }
        }

        return new Result(bestRow, maxTotal);
    }

    public static void main(String[] args) {
        int[][] marks = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };

        Result topper = findTopper(marks);
        System.out.println(topper);
    }
}
