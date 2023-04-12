package company.FB;


// 226 -> "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6)
public class _91_DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int dp[] = new int[3];

        dp[0] = 1;
        dp[1] = s.charAt(0) - '0' == 0 ? 0 : 1;

        for (int i = 2; i <= s.length(); i++) {

            if (s.charAt(i - 1) != '0') {
                dp[i % 3] = dp[(i - 1) % 3];
            }

            int lastTwo = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');

            if (lastTwo >= 10 && lastTwo <= 26) {
                dp[i % 3] += dp[(i - 2) % 3];
            }
        }

        return dp[s.length() % 3];
    }
}
