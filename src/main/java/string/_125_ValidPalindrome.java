package string;

public class _125_ValidPalindrome {

    // Given a string s, return true if it is a palindrome, or false otherwise.

    // two pointers from head and tail
    // if is character, to lowercase, and compare
    // if not, skip
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int head = 0;
        int tail = s.length() - 1;

        while (head < tail) {
            char ch = Character.toLowerCase(s.charAt(head));
            char ct = Character.toLowerCase(s.charAt(tail));


            if (!Character.isLetterOrDigit(ch)) {
                head++;
                continue; // skip to the next iteration
            }

            if (!Character.isLetterOrDigit(ct)) {
                tail--;
                continue; // skip to the next iteration
            }

            if (ch != ct) {
                return false;
            }

            head++;
            tail--;

        }

        return true;
    }
}
