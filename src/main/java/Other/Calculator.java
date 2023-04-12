package Other;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Calculator {
    public int calculate(String s) {
        return solveRPN(getRPN(s));
    }

    private String getRPN(String s) {
        int i = 0;
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        s = s.replace(" ", "");
        while(i < s.length()) {
            char c = s.charAt(i);

            if(Character.isDigit(c)) {
                sb.append(c);
            } else {
                sb.append(' ');

                if(c == '(') {
                    stack.push(c);
                } else if(c == ')') {
                    while(!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop() + ' ');
                    }
                    // pop (
                    stack.pop();
                } else {
                    while(!stack.isEmpty() && getOrder(c) <= getOrder(stack.peek())) {
                        sb.append(stack.pop() + ' ');
                    }
                    System.out.println(c);
                    stack.push(c);
                }
            }

            i++;
        }

        while(!stack.isEmpty()) {
            System.out.println(stack.peek());


            sb.append(' ');
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());

        return sb.toString();
    }

    private int getOrder(char c) {
        if(c == '+' || c == '-') {
            return 1;
        } else if(c == '*' || c == '/') {
            return 2;
        } else {
            return 0;
        }
    }

    private int solveRPN(String s) {
        String[] strs = s.split(" ");

        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for(String str : strs) {
            if(str.equals("")) continue;
            if("+-*/".contains(str)) {
                int num2 = stack.pop();
                int num1 = stack.pop();

                if(str.equals("+")) {
                    stack.push(num1 + num2);
                }

                if(str.equals("-")) {
                    stack.push(num1 - num2);
                }

                if(str.equals("*")) {
                    stack.push(num1 * num2);
                }

                if(str.equals("/")) {
                    stack.push(num1 / num2);
                }
            } else {
                stack.push(Integer.parseInt(str));
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        String exp = "1 + 1";

        Calculator c = new Calculator();

        System.out.println(c.calculate(exp));
    }
}
