package string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class _20_ValidParentheses {

//     Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

    // use stack to track open bracket
    public boolean isValid(String s) {

        LinkedList<Character> stack = new LinkedList<>();
        Map<Character, Character> map = ImmutableMap.of('(', ')', '{', '}', '[',']');

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                // right bracket
                if (!stack.isEmpty() && map.get(stack.peek()).equals(c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new _20_ValidParentheses().isValid("()"));
        System.out.println(new _20_ValidParentheses().isValid("()[]{}"));
        System.out.println(new _20_ValidParentheses().isValid("(]"));
    }
}
