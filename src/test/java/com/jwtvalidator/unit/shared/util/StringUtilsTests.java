package com.jwtvalidator.unit.shared.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jwtvalidator.shared.util.StringUtils;

@ExtendWith(MockitoExtension.class)
public class StringUtilsTests {

    @Test
    public void testHasNumberWithNumbers() {
        assertTrue(StringUtils.hasNumber("abc123"));
        assertTrue(StringUtils.hasNumber("1abc"));
        assertTrue(StringUtils.hasNumber("abc1"));
        assertTrue(StringUtils.hasNumber("123"));
        assertTrue(StringUtils.hasNumber("0"));
    }

    @Test
    public void testHasNumberWithoutNumbers() {
        assertFalse(StringUtils.hasNumber("abc"));
        assertFalse(StringUtils.hasNumber(""));
        assertFalse(StringUtils.hasNumber("!@#"));
        assertFalse(StringUtils.hasNumber("abc!@#"));
    }

    @Test
    public void testStringUtilsNotNull() {
        StringUtils stringUtils = new StringUtils();
        assertNotNull(stringUtils);
    }
}