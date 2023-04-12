package Other;

public class RemoveKDigits {

    public static String removeKdigits(String num, int k) {
        if(num.length() == k) return "0";

        StringBuilder sb = new StringBuilder(num);
        for(int j = 0; j < k; j++) {
            int i = 0;
            while(i < sb.length() - 1 && sb.charAt(i) <= sb.charAt(i + 1)) {
                i++;
            }
            sb.deleteCharAt(i);
        }

        int i = 0;
        // remove heading 0
        while(sb.length() > 1 && sb.charAt(i) == '0') {
            sb.deleteCharAt(0);
        }

        if(sb.length() == 0) {
            return "0";
        }

        return sb.toString();


    }

    public static void main(String[] args) {
        String num = "112";
        int k = 1;

        System.out.println(removeKdigits(num, k));
    }
}
