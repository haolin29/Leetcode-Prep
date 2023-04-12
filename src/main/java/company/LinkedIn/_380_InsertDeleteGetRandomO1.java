package company.LinkedIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
// 2020-1-1
public class _380_InsertDeleteGetRandomO1 {
    class RandomizedSet {

        Random rand;
        List<Integer> list;
        Map<Integer, Integer> map;
        /** Initialize your data structure here. */
        public RandomizedSet() {
            rand = new Random();
            list = new ArrayList<>();
            map = new HashMap<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            } else {
                map.put(val, list.size());
                list.add(val);
                return true;
            }
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            } else {
                int idx = map.get(val);
                map.remove(val);
                int last = list.size() - 1;
                if (idx != last) {
                    // swap with last
                    int temp = list.get(last);
                    list.set(last, list.get(idx));
                    list.set(idx, temp);
                }
                list.remove(last);
                return true;
            }
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }
}
