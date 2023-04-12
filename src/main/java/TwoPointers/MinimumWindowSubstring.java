package TwoPointers;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> target = new HashMap<>();

        for (char c : t.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        int counter = t.length();
        int left = 0, right = 0, start = 0;
        int minLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            char c = s.charAt(right);

            if (target.containsKey(c)) {
                if (target.get(c) > 0) {
                    counter--;
                }
                target.put(c, target.get(c) - 1);
            }

            right++;

            // find the window
            while (counter == 0) {
                char tc = s.charAt(left);

                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }

                if (target.containsKey(tc)) {
                    target.put(tc, target.get(tc) + 1);

                    if (target.get(tc) > 0) {
                        counter++;
                    }
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);

    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println(minWindow(s, t));
    }
}
