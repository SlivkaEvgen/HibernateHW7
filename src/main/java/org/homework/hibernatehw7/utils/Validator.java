package org.homework.hibernatehw7.utils;

public class Validator {

    public static boolean validNumber(String hasNumbers) {
        return hasNumbers.matches("\\d+");
    }

    public static boolean validString(String hasLetters) {
        return !hasLetters.matches("\\d+");
    }
}
