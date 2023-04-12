package company.Snap;

import java.util.ArrayList;
import java.util.List;

// #93 https://leetcode.com/problems/restore-ip-addresses/description/

public class RestoreIPAddress {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return res;
        }

        dfs(s, res,"", 1, 0);

        return res;
    }

    private void dfs(String s, List<String> res, String temp, int section, int idx) {
        if (idx >= s.length()) return;

        if (section == 4) {
            String tail = s.substring(idx);
            if (isValid(tail)) {
                temp = temp + "." + tail;
                res.add(temp);
            }
            return;
        }

        for (int i = 1; i < 4 && i + idx <= s.length() ; i++) {
            String str = s.substring(idx, idx + i);
            if (isValid(str)) {
                if (section == 1) {
                    dfs(s, res, temp + str, section + 1, idx + i);
                } else {
                    dfs(s, res, temp + "." + str, section + 1, idx + i);
                }
            }
        }
    }

    private boolean isValid(String s) {
        if (s.length() > 3) {
            return false;
        }

        if (s.charAt(0) == '0' && s.length() > 1) return false;

        int num = Integer.parseInt(s);

        if (num >= 0 || num <= 255) {
            return true;
        }

        return false;
    }
}