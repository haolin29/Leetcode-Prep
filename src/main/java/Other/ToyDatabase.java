package Other;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ToyDatabase {

    enum Direction {
        ASC, DESC
    }

    // step1
    public static Map<String, Integer> minByKey(String key, List<Map<String, Integer>> records) {
        //        Map<String, Integer> res = new HashMap<>();
        //
        //        int min = Integer.MAX_VALUE;
        //        for (Map<String, Integer> record : records) {
        //            int value = record.getOrDefault(key, 0);
        //            if (value < min) {
        //                min = value;
        //                res = record;
        //            }
        //        }

        //        return res;

        return firstByKey(key, "ASC", records);
    }

    public static Map<String, Integer> firstByKey(String key, String direction, List<Map<String, Integer>> records) {
        int first;
        if (Direction.ASC == Direction.valueOf(direction)) {
            first = Integer.MAX_VALUE;
        } else {
            first = Integer.MIN_VALUE;
        }
        Map<String, Integer> res = new HashMap<>();

        for (Map<String, Integer> record : records) {
            int value = record.getOrDefault(key, 0);

            if (Direction.ASC == Direction.valueOf(direction)) {
                if (value < first) {
                    first = value;
                    res = record;
                }
            } else {
                if (value > first) {
                    first = value;
                    res = record;
                }
            }
        }

        return res;
    }

    static class RecordComparator implements Comparator<Map<String, Integer>> {

        private final String key;
        private final String direction;

        public RecordComparator(String key, String direction) {
            this.key = key;
            this.direction = direction;
        }

        @Override
        public int compare(Map<String, Integer> left, Map<String, Integer> right) {
            int lv = left.getOrDefault(key, 0);
            int rv = right.getOrDefault(key, 0);

            return "asc".equals(direction) ? lv - rv : rv - lv;
        }
    }


    //[("b", "asc"), ("a", "asc")]
    public static Map<String, Integer> firstBySortOrder(LinkedHashMap<String, String> sortOrder, List<Map<String, Integer>> records) {
        if (records == null || records.size() == 0) {
            return new HashMap<>();
        }

        List<RecordComparator> recordComparators = new ArrayList<>();
        sortOrder.forEach((k, v) -> {
            recordComparators.add(new RecordComparator(k, v));
        });

        Map<String, Integer> res = records.get(0);

        for (int i = 1; i < records.size(); i++) {
            Map<String, Integer> right = records.get(i);

            for (RecordComparator recordComparator: recordComparators) {
                if (recordComparator.compare(res, right) > 0) {
                    res = right;
                    break;
                } else if (recordComparator.compare(res, right) < 0) {
                    break;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
