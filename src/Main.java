import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Wprowadź wyrażenie");
        String expression = scan.nextLine();

        System.out.println("Wprowadzono:  " + expression);
        Integer calculate = calculate(expression);

        System.out.println("Wynik:  " + calculate);

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
}
