package company.LinkedIn;

public class _5_LongestPalindromicSubstring {

    int l = 0, max = 1;
    public String longestPalindrome(String s) {
        int n = s.length();

        for (int i = 0; i < n - 1; i++) {
            helper(s, i, i);
            helper(s, i, i + 1);
        }

        return s.substring(l, l + max);
    }

    private void helper(String s, int i, int j) {
        for (; i >= 0 && j < s.length(); i--, j++) {
            if (s.charAt(i) != s.charAt(j)) {
                break;
            }
        }

        int len = j - i - 1;
        if (len > max) {
            max = len;
            l = i + 1;
        }
    }

    public static void main(String[] args) {
        _5_LongestPalindromicSubstring test = new _5_LongestPalindromicSubstring();
        System.out.println(test.longestPalindrome("a"));
    }


}
