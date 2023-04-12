package Other;

public class MagicDict {

    class Node {
        char c;
        boolean isWord;
        Node[] children = new Node[26];

        public Node(char c) {
            this.c = c;
        }

        public Node() {}
    }

    private Node root;

    /** Initialize your data structure here. */
    public MagicDict() {
        root = new Node();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            Node cur = root;

            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new Node(c);
                }

                cur = cur.children[c - 'a'];
            }

            cur.isWord = true;
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {

        char[] cs = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (cs[i] - 'a' == j) continue;

                cs[i] = (char)('a' + j);
                if (canFind(cs)) {
                    return true;
                }

                // only change one char
                cs[i] = word.charAt(i);
            }
        }

        return false;
    }

    private boolean canFind(char[] cs) {
        Node cur = root;

        for (int i = 0; i < cs.length; i++) {
            cur = cur.children[cs[i] - 'a'];

            if (cur == null) return false;
        }

        return cur.isWord;
    }
}
