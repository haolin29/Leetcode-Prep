package Other;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorganizeString {

    public String reorganizeString(String S) {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : S.toCharArray()) {
            map.putIfAbsent(c, 0);
            map.put(c, map.get(c) + 1);

            if (map.get(c) > S.length() / 2) {
                return "";
            }
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());

        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            pq.offer(entry);
        }

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();

            if (sb.length() == 0 || entry.getKey() != sb.charAt(sb.length() - 1)) {
                sb.append(entry.getKey());
                if (entry.getValue() > 1) {
                    entry.setValue(entry.getValue() - 1);
                    pq.offer(entry);
                }
            } else {
                Map.Entry<Character, Integer> second = pq.poll();
                sb.append(second.getKey());
                if (second.getValue() > 1) {
                    second.setValue(second.getValue() - 1);
                    pq.offer(second);
                }
                pq.offer(entry);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "aaab";

        ReorganizeString test = new ReorganizeString();
        System.out.println(test.reorganizeString(s));
    }
}
