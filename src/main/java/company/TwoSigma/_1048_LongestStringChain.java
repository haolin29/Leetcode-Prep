package company.TwoSigma;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _1048_LongestStringChain {

    // Input: words = ["a","b","ba","bca","bda","bdca"]
    // Output: 4
    // Explanation: One of the longest word chains is ["a","ba","bda","bdca"].

//    Sort the words by word's length. (also can apply bucket sort)
//    For each word, loop on all possible previous word with 1 letter missing.
//    If we have seen this previous word, update the longest chain for the current word.
//    Finally return the longest word chain.


    public static int longestStrChain(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }

        Arrays.sort(words, (a,b) -> a.length() - b.length());
        Map<String, Integer> map = new HashMap<>();
        int res = 0;

        for (String w : words) {
            map.put(w, 1);

            for (int i = 0; i < w.length(); i++) {
                StringBuilder sb = new StringBuilder(w);
                String next = sb.deleteCharAt(i).toString();

                // If the new chain is longer, update the map
                if (map.containsKey(next) && map.get(next) + 1 > map.get(w)) {
                    map.put(w, map.get(next) + 1);
                }
            }

            res = Math.max(res, map.get(w));
        }

        return res;
    }

    public static void main(String[] args) {
        String[] words = {"a","b","ba","bca","bda","bdca"};

        System.out.println(longestStrChain(words));

        System.out.println(isPredecessor("a", "ab"));
    }

    private static boolean isPredecessor(String x, String y) {
        if (y.length() != x.length() + 1) {
            return false;
        }

        for (int i = 0; i < y.length(); i++) {
            StringBuilder sb = new StringBuilder(y);

            if (sb.deleteCharAt(i).toString().equals(x)) {
                return true;
            }
        }

        return false;
    }
}
