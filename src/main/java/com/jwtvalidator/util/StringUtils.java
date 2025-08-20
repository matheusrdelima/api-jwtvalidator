package com.jwtvalidator.util;

public class StringUtils {
    public static boolean hasNumber(String value) {
        return value.matches(".*\\d.*");
    }
}
