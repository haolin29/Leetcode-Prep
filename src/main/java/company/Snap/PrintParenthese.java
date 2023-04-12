package company.Snap;

public class PrintParenthese {

    public static void main(String[] args) {
        print("(Hi Hello(Hey(yoyo ) ) Beauty)");
    }

    public static void print(String input) {
        if (input == null || input.length() == 0) {
            return;
        }

        boolean needPrint = false;
        String curLine = "";
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                if (needPrint)
                    System.out.println(curLine);
                System.out.println(addPad(count) + "(");
                count++;
                needPrint = false;

            } else if (input.charAt(i) == ')') {
                if (needPrint)
                    System.out.println(curLine);
                count--;
                System.out.println(addPad(count) + ")");
                needPrint = false;

            } else if (input.charAt(i) == ' ') {
                if (needPrint)
                    System.out.println(curLine);
                needPrint = false;

            } else {
                if (needPrint == false)
                    curLine = addPad(count);
                curLine += input.charAt(i);
                needPrint = true;
            }
        }
    }

    public static String addPad(int count) {
        String res = "";
        for (int i = 0; i < count; i++) {
            res += "  ";
        }
        return res;
    }
}
