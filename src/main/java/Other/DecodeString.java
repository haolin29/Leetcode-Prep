package Other;

public class DecodeString {
    private int i = 0;

    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int k = 0;

        while(i < s.length()) {
            char c = s.charAt(i);

            if(c == '[') {
                i++;
                String str = decodeString(s);
                while(--k > 0) {
                    sb.append(str);
                }
            } else if(c == ']') {
                break;
            } else if(Character.isDigit(c)) {
                k = 10 * k + c - '0';
            } else {
                sb.append(c);
            }

            i++;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        DecodeString test = new DecodeString();

        System.out.println(test.decodeString(s));

    }
}
