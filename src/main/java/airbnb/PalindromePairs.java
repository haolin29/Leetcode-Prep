package airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePairs {

    // ["aabc", "cbaa"]

    public static void main(String[] args) {
        String[] words = {"", "a", "ab", "ba", "abc","cb" };

        PalindromePairs palindromePairs = new PalindromePairs();

        for (List<Integer> list : palindromePairs.findPalindrome(words)) {
            for (int idx : list) {
                System.out.print(idx + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> findPalindrome(String[] words) {
        List<List<Integer>> res = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();

        int n = words.length;

        for (int i = 0; i < n; i++) {
            map.put(words[i], i);
        }

        // case 1: empty string + palindrome string
        if (map.containsKey("")) {
            int idx = map.get("");
            for (int i = 0; i < n; i++) {
                if (isPalindrome(words[i])) {
                    if (i != idx) {
                        res.add(Arrays.asList(i, idx));
                        res.add(Arrays.asList(idx, i));
                    }
                }
            }
        }

        // case 2: string + reverseString
        for (int i = 0; i < n; i++) {
            String reverse = getReverse(words[i]);
            if (map.containsKey(reverse)) {
                if (map.get(reverse) != i) {
                    res.add(Arrays.asList(i, map.get(reverse)));
                }
            }
        }

        // time: n*k^2 , k to the power of 2
        // case 3: abc + cb
        for (int i = 0; i < n; i++) {
            String cur = words[i];

            // cut point
            for (int j = 1; j < cur.length(); j++ ) {
                String leftCut = cur.substring(0, j);
                String rightCut = cur.substring(j);

                if (isPalindrome(leftCut)) {
                    String reverse = getReverse(rightCut);
                    if (map.containsKey(reverse)) {
                        if (map.get(reverse) != i) {
                            res.add(Arrays.asList(map.get(reverse), i));
                        }
                    }
                }

                if (isPalindrome(rightCut)) {
                    String reverse = getReverse(leftCut);
                    if (map.containsKey(reverse)) {
                        if (map.get(reverse) != i) {
                            res.add(Arrays.asList(i, map.get(reverse)));
                        }
                    }
                }
            }
        }

        return res;

    }

    private String getReverse(final String word) {
        StringBuilder sb = new StringBuilder(word);
        return sb.reverse().toString();
    }

    private boolean isPalindrome(final String word) {
        // important i == j, single char is palindrome
        for (int i = 0, j = word.length() - 1; i <= j; i++, j--) {
            if (word.charAt(i) != word.charAt(j)) {
                return false;
            }
        }

        return true;
    }

}
