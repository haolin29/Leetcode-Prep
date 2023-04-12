package company.LinkedIn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class _432_AllO1DataStructure {
    class AllOne {

        Map<String, Integer> keyMap;
        Map<Integer, Set<String>> valMap;
        // keep track min and max value
        LinkedList<Integer> list;

        /** Initialize your data structure here. */
        public AllOne() {
            keyMap = new HashMap<>();
            valMap = new HashMap<>();
            list = new LinkedList<>();
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            if (!keyMap.containsKey(key)) {
                keyMap.put(key, 1);
                putInNodeMap(1, key);
            } else {
                int val = keyMap.get(key);
                removeInNodeMap(val, key);
                keyMap.put(key, val + 1);
                putInNodeMap(val + 1, key);
            }
        }

        private void removeInNodeMap(int val, String key) {
            if (!valMap.containsKey(val)) {
                return;
            }

            valMap.get(val).remove(key);

            if (valMap.get(val).size() == 0) {
                valMap.remove(val);
                if (!list.isEmpty() && list.peekFirst() == val) {
                    // head, smaller
                    list.removeFirst();
                }

                if (!list.isEmpty() && list.peekLast() == val) {
                    // tail, greater
                    list.removeLast();
                }

            }
        }

        private void putInNodeMap(int val, String key) {
            // first node
            if (!valMap.containsKey(val)) {
                valMap.put(val, new HashSet<>());
            }
            valMap.get(val).add(key);
            if (list.isEmpty() || list.peekFirst() > val) {
                list.addFirst(val);
            }

            if (!list.isEmpty() && list.peekLast() < val) {
                list.addLast(val);
            }
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            if (!keyMap.containsKey(key)) {
                return;
            } else {
                int val = keyMap.get(key);
                if (val == 1) {
                    keyMap.remove(key);
                    removeInNodeMap(1, key);
                } else {
                    removeInNodeMap(val, key);
                    keyMap.put(key, val - 1);

                    putInNodeMap(val - 1, key);
                }
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            if (list.isEmpty()) {
                return "";
            }

            return valMap.get(list.getLast()).iterator().next();
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            return "";
        }
    }
}
