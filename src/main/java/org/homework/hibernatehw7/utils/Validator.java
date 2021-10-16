package org.homework.hibernatehw7.utils;

import java.io.Serializable;

public class Validator implements Serializable {

    private static final long serialVersionUID = 3335044651928374654L;

    public static boolean validNumber(String hasNumbers) {
        return hasNumbers.matches("\\d+");
    }

    public static boolean validString(String hasLetters) {
        return !hasLetters.matches("\\d+");
    }
}
