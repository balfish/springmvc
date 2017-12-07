package com.balfish.hotel.zzAlgorithm;

import com.google.common.collect.ImmutableMap;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 中缀表达式 -> 逆波兰表达式(后缀表达式) -> 计算结果
 * <p>
 * 仅为了说明算法的实现，我们假设操作数均为0-9，符号只有加减乘除和括号
 * <p>
 * Created by yhm on 2017/11/21 AM11:26.
 */
public class Test1121 {

    /**
     * guava jar
     * 获得符号对应的优先级，我们假设只有'#', '+', '-', '*', '/', '(', ')'
     */
    private static final ImmutableMap<Character, Integer> map = ImmutableMap
            .<Character, Integer>builder()
            .put('#', 0) //初始栈
            .put('(', 1)
            .put('+', 2)
            .put('-', 2)
            .put('*', 3)
            .put('/', 3)
            .put(')', 4)
            .build();

    public static void main(String[] args) {
        System.out.println(middleToSuffixExpression("a + b * c + (d * e + f)*g"));
        System.out.println(middleToSuffixExpression("5 * (3 + 4) - 6 + 8/2"));
        System.out.println(calSuffixExpression(middleToSuffixExpression("5 * (3 + 4) - 6 + 8/2")));
    }

    /**
     * 中缀表达式转成后缀表达式
     * a + b*c + (d * e + f) * g -> abc*+de*f+g*+
     *
     * @param middle 中缀表达式
     * @return 后缀表达式
     */
    private static String middleToSuffixExpression(String middle) {
        MyStack<Character> charStack = new MyStack<>();
        StringBuilder stringBuilder = new StringBuilder();
        charStack.push('#');

        for (char c : middle.toCharArray()) {
            if (c == ' ') {
                continue;
            }

            if (map.get(c) == null) { //不是操作符
                stringBuilder.append(c);
                continue;
            }

            if (c == '(') {
                charStack.push(c);
            } else if (c == ')') {
                while (true) {
                    Character peek = charStack.peek();
                    charStack.pop();
                    if (peek == '(') {
                        break;
                    }
                    stringBuilder.append(peek);
                }
            } else {
                while (true) {
                    Character peek = charStack.peek();
                    if (map.get(c) > map.get(peek)) {
                        charStack.push(c);
                        break;
                    }
                    // 栈顶优先级高,需要弹出
                    charStack.pop();
                    stringBuilder.append(peek);
                }
            }
        }

        // 剩余的符号全部弹出
        while (true) {
            Character peek = charStack.peek();
            if (peek == '#') {
                break;
            }
            charStack.pop();
            stringBuilder.append(peek);
        }
        return stringBuilder.toString();
    }

    /**
     * 计算后缀表达式
     *
     * @param suffix 后缀表达式
     * @return 结果，方便起见，我们假设结果为int不暴栈
     */
    private static Integer calSuffixExpression(String suffix) {
        Stack<Integer> stack = new Stack<>();
        for (char c : suffix.toCharArray()) {
            if (Pattern.compile("[0-9]").matcher(String.valueOf(c)).matches()) {
                stack.push(c - '0');
            } else {
                int second = stack.peek();
                stack.pop();
                int first = stack.peek();
                stack.pop();
                Integer tmp = cal(first, second, c);
                stack.push(tmp);
            }
        }
        return stack.peek();
    }

    private static Integer cal(int first, int second, char c) {
        Integer ret = null;
        switch (c) {
            case '+':
                ret = first + second;
                break;
            case '-':
                ret = first - second;
                break;
            case '*':
                ret = first * second;
                break;
            case '/':
                ret = first / second;
                break;
        }
        return ret;
    }
}
