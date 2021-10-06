package helper;

import java.util.Stack;

public class CalculatorHelper {

    public static String convertToOnp(String expression) {

        Stack<Character> stack = new Stack<>();
        StringBuilder outExpression = new StringBuilder();
        char actualChar;

        for (int i = 0; i < expression.length(); i++) {

            actualChar = expression.charAt(i);

            // jeśli otwarty nawias
            if (actualChar == '(') {
                stack.push(actualChar);
            }
            // jeśli zamkniety nawias
            if (actualChar == ')') {

                while (!stack.isEmpty()) {
                    Character stackCharacter = stack.pop();
                    if (stackCharacter == '(')
                        break;
                    outExpression.append(" ").append(stackCharacter);
                }
            }

            // jeśli cyfra to dajemy na wyjscie
            if (Character.isDigit(actualChar)) {
                outExpression.append(actualChar);
            }
            // jeśli jest operatorem
            if (isOperator(actualChar)) {
                outExpression.append(" ");
                if (!stack.isEmpty()) {
                    while (!stack.isEmpty()) {
                        Character stackCharacter = stack.pop();
                        if (priorityOfChar(actualChar) > priorityOfChar(stackCharacter)) {
                            stack.push(stackCharacter);
                            break;
                        } else
                            outExpression.append(" ").append(stackCharacter).append(" ");
                    }
                }
                stack.push(actualChar);
            }
        }

        while (!stack.isEmpty())
            outExpression.append(" ").append(stack.pop());

        return outExpression.toString().replaceAll("  ", " ");
    }

    public static Integer calculate(String expression) {

        String[] splitExpression = expression.trim().split(" ");

        Stack<Integer> stack = new Stack<>();

        for (String s : splitExpression) {

            if (s.matches("\\d+"))
                stack.push(Integer.valueOf(s));

            else {
                Integer a;
                Integer b;

                switch (s) {
                    case "+" -> {
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b + a);
                    }
                    case "-" -> {
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b - a);
                    }
                    case "*" -> {
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b * a);
                    }
                    case "/" -> {
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b / a);
                    }
                }
            }

        }

        return stack.pop();
    }

    private static boolean isOperator(Character operator) {

        return "+-*/".contains(operator.toString());
    }


    private static int priorityOfChar(Character operator) {

        int n = 0;

        if (operator.equals('+') || operator.equals('-'))
            n = 1;
        if (operator.equals('*') || operator.equals('/'))
            n = 2;

        return n;
    }
}
