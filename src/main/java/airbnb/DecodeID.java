package airbnb;

public class DecodeID {

    public Integer decode(String str) {
        return "a1bc".equals(str) ? 123 : null;
    }

    public Integer find(String badStr) {
        return helper(badStr, 0, new StringBuilder());
    }

    private Integer helper(final String badStr, final int i, final StringBuilder sb) {
        if (sb.length() == badStr.length()) {
            return decode(sb.toString());
        }

        char c = badStr.charAt(i);

        sb.append(Character.toUpperCase(c));
        Integer res = helper(badStr, i + 1, sb);
        if (res != null) return res;
        sb.setLength(sb.length() - 1);


        sb.append(Character.toLowerCase(c));
        res = helper(badStr, i + 1, sb);
        if (res != null) return res;
        sb.setLength(sb.length() - 1);


        return null;
    }

    public static void main(String[] args) {
        DecodeID decodeID = new DecodeID();

        System.out.println(decodeID.find("A1bc"));

        System.out.println(Character.toLowerCase(1));
    }

}
