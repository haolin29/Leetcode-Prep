package company.FB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _438_FindAllAnagramsInAString {
    public static List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int counter = map.size();

        int l = 0, r = 0;
        List<Integer> res = new ArrayList<>();

        while(r < s.length()) {
            char c = s.charAt(r++);

            // need to exclude irrelative chars
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);

                if (map.get(c) == 0) {
                    counter--;
                }
            }

            while (counter == 0) {
                if (r - l == p.length()) {
                    res.add(l);
                }

                char lc = s.charAt(l++);

                if (map.containsKey(lc)) {
                    map.put(lc, map.getOrDefault(lc, 0) + 1);

                    if (map.get(lc) > 0) {
                        counter++;
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p =  "abc";

        System.out.println(findAnagrams(s, p).toString());
    }
}
