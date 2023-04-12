package company.FB;

import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParentheses {

    public List<String> remove(String s) {
        int left = 0, right = 0;

        for (char c: s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left == 0) {
                    right++;
                } else {
                    left--;
                }
            }
        }
        List<String> result = new ArrayList<>();
        dfs(s, left, right, 0, result);

        return result;
    }

    private void dfs(String s, final int left, final int right, final int start, final List<String> result) {
        if (left == 0 && right == 0) {
            if (isValid(s)) {
                result.add(s);
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);

            if (i > 0 && s.charAt(i - 1) == c) continue;

            if (c == '(' || c == ')') {
                s = deleteChar(s, i);
                if (right > 0) {
                    dfs(s, left, right - 1, i, result);
                } else if (left > 0) {
                    dfs(s, left - 1, right, i, result);
                }

            }

        }
    }

    private boolean isValid(final String s) {
        int count = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;

            if (count < 0) return false;
        }

        return count == 0;
    }

    private String deleteChar(final String s, final int i) {
        StringBuilder sb = new StringBuilder(s);
        sb.deleteCharAt(i);
        return sb.toString();
    }
}
