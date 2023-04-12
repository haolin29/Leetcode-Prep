package company.FB;

import java.util.HashSet;
import java.util.Set;

// case sensitive ?
public class _3_LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

//        Map<Character, Integer> map = new HashMap<>();
        // 用 set 更方便，因为只能出现一次
        Set<Character> set = new HashSet<>();
        int l = 0, r = 0;
        int max = 1;

        while (r < s.length()) {
            while (r < s.length() && !set.contains(s.charAt(r))) {
                char c = s.charAt(r++);
                set.add(c);
            }

            int len = r - l;
            max = Math.max(max, len);

            set.remove(s.charAt(l++));
        }

        return max;

    }
}
