package company.Snap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];

        int maxLen = 0;

        for (String word : dict) {
            maxLen = Math.max(maxLen, word.length());
        }

        dp[0] = true;
//        for (int i = 1; i <= s.length(); i++) {
//            for (int j = i - 1; j >= 0 && i - j <= maxLen ; j--) {
//                dp[i] = dp[j] && dict.contains(s.substring(j, i));
//            }
//        }

        for (int i = 0; i < s.length(); i++) {
            if (dp[i]) {
                for (int j = i + 1; j <= s.length() && j - i <= maxLen; j++) {
                    dp[j] = dict.contains(s.substring(i, j));
                }
            }
        }
        return dp[s.length()];

    }

    // 返回所有的解

    Map<String, List<String>> mem = new HashMap<>();
    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }

        Set<String> dict = new HashSet<>(wordDict);
        return dfs(s, dict);
    }

    private List<String> dfs(String s, Set<String> dict) {
        if (mem.containsKey(s)) {
            return mem.get(s);
        }

        List<String> res = new ArrayList<>();

        // s = cat
        if (dict.contains(s)) {
            res.add(s);
        }

        for (int i = 1; i < s.length(); i++) {
            String right = s.substring(i);

            if (!dict.contains(right)) continue;

            String left = s.substring(0, i);

            List<String> leftCuts = addToList(dfs(left, dict), right);

            res.addAll(leftCuts);
        }

        mem.put(s, res);
        return res;
    }

    private List<String> addToList(List<String> list, final String right) {
        List<String> res = new ArrayList<>();

        for (String s : list) {
            res.add(s + " " + right);
        }

        return res;
    }
}
