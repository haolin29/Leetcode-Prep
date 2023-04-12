package company.LinkedIn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<Integer> words = new HashSet<>();
        Set<Integer> repeat = new HashSet<>();

        int[] map = new int[26];
        // A C G T
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        List<String> res = new ArrayList<>();

        for (int i = 0; i < s.length() - 9; i++) {
            int key = 0;

            for (int j = i; j < s.length() && j < i + 10; j++) {
                key <<= 2;
                key |= map[s.charAt(j) - 'A'];
            }

            if (!words.add(key) && repeat.add(key)) {
                res.add(s.substring(i, i + 10));
            }
        }

        return res;
    }
}
