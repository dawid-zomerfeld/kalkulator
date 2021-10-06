import exeption.NoAllowedCharException;
import exeption.NotNumberException;
import helper.CalculatorHelper;
import validator.Validator;

import java.util.Scanner;

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

        String onp = CalculatorHelper.convertToOnp(expression);

        System.out.println("ONP:  " + onp);

        Integer calculate = CalculatorHelper.calculate(onp);

        System.out.println("Wynik:  " + calculate);

    }


}
