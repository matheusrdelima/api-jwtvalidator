package com.jwtvalidator.util;

public class StringHasNumber {
    public static boolean check(String value) {
        return value.matches(".*\\d.*");
    }
}
