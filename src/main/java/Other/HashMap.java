package Other;

public class HashMap {

    private static int capacity = 1000;
    public int getHash(String key) {
        int sum  = 0;

        for (int i = 0; i < key.length(); i++) {
            sum = sum * 32 + (int) key.charAt(i);
            sum %= capacity;
        }

        return sum;
    }
}
