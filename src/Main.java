import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Wprowadź wyrażenie. Dozwolone znaki to : +, -, *, / ,( ,) oraz cyfry. Wyrażenie musi zaczynać się od cyfry");
        String expression;
        boolean error = true;
        do {
            expression = scan.nextLine();

            try {
                Validator.valid(expression.replaceAll("\\s+", ""));
                error = false;
            } catch (NoAllowedCharException | NotNumberException ex) {
                System.err.println("Nie poprawne wyrażenie!");
                System.err.println(ex.getMessage());
                System.err.println("Wprowadź jeszcze raz");
            }
        } while (error);


        System.out.println("Wprowadzono:  " + expression);
        scan.close();

        String onp = convertToOnp(expression);

        System.out.println("ONP:  " + onp);

        Integer calculate = calculate(onp);

        System.out.println("Wynik:  " + calculate);

    }


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
