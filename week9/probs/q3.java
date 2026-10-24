package week9.probs;

import java.util.*;

public class q3 {

    public static class PopularItem {
        public String item;
        public int count;

        public PopularItem(String item, int count) {
            this.item = item;
            this.count = count;
        }

        @Override
        public String toString() {
            return "(\"" + item + "\", " + count + ")";
        }
    }

    public static PopularItem mostPopular(String[] orders) {
        if (orders == null || orders.length == 0) {
            return null;
        }

        Map<String, Integer> frequency = new HashMap<>();
        for (String order : orders) {
            frequency.put(order, frequency.getOrDefault(order, 0) + 1);
        }

        int maxCount = 0;
        for (int count : frequency.values()) {
            if (count > maxCount) {
                maxCount = count;
            }
        }

        for (String order : orders) {
            if (frequency.get(order) == maxCount) {
                return new PopularItem(order, maxCount);
            }
        }

        return null;
    }

    public static void main(String[] args) {
        String[] orders1 = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        System.out.println(mostPopular(orders1));

        String[] orders2 = {"tea", "coffee", "coffee", "tea"};
        System.out.println(mostPopular(orders2));
    }
}
