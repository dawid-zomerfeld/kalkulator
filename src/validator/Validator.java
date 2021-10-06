package validator;

import exeption.NoAllowedCharException;
import exeption.NotNumberException;

public class Validator {

    public static void valid(String expression) throws NoAllowedCharException, NotNumberException {
        char actualChar;

        if(!Character.isDigit(expression.charAt(0)))
            throw new NotNumberException("Wyrażenie musi zaczynać się od cyfry");

        for (int i = 0; i < expression.length(); i++) {

            actualChar = expression.charAt(i);
            if(!Character.isDigit(actualChar) && !isAllowedCharacters(actualChar))
                throw new NoAllowedCharException("Wprowadzono niedozwolony znak: " + actualChar);

        }


    }

    private static boolean isAllowedCharacters(Character characters){
        return "+-*/()".contains(characters.toString());
    }

}
