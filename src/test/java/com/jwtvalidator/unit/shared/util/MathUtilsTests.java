package com.jwtvalidator.unit.shared.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jwtvalidator.shared.util.MathUtils;

@ExtendWith(MockitoExtension.class)
public class MathUtilsTests {

    @Test
    public void testIsPrimeWithPrimes() {
        assertTrue(MathUtils.isPrime(2));
        assertTrue(MathUtils.isPrime(3));
        assertTrue(MathUtils.isPrime(5));
        assertTrue(MathUtils.isPrime(7));
        assertTrue(MathUtils.isPrime(13));
        assertTrue(MathUtils.isPrime(17));
        assertTrue(MathUtils.isPrime(97));
    }

    @Test
    public void testIsPrimeWithNonPrimes() {
        assertFalse(MathUtils.isPrime(0));
        assertFalse(MathUtils.isPrime(1));
        assertFalse(MathUtils.isPrime(4));
        assertFalse(MathUtils.isPrime(6));
        assertFalse(MathUtils.isPrime(9));
        assertFalse(MathUtils.isPrime(15));
        assertFalse(MathUtils.isPrime(100));
    }

    @Test
    public void testIsPrimeWithNegativeNumbers() {
        assertFalse(MathUtils.isPrime(-1));
        assertFalse(MathUtils.isPrime(-7));
        assertFalse(MathUtils.isPrime(-13));
    }

    @Test
    public void testIsPrimeWithLargePrime() {
        assertTrue(MathUtils.isPrime(104729));
    }

    @Test
    public void testIsPrimeWithLargeNonPrime() {
        assertFalse(MathUtils.isPrime(104730));
    }

    @Test
    public void testIsPrimeWithLongMaxValue() {
        assertFalse(MathUtils.isPrime(Long.MAX_VALUE));
    }

    @Test
    public void testIsPrimeWithEdgeCases() {
        assertFalse(MathUtils.isPrime(Long.MIN_VALUE));
        assertFalse(MathUtils.isPrime(-1000000));
        assertTrue(MathUtils.isPrime(7919));
    }

    @Test
    public void testIsPrimeDivisibleByI() {
        assertFalse(MathUtils.isPrime(25));
    }

    @Test
    public void testIsPrimeDivisibleByIPlus2() {
        assertFalse(MathUtils.isPrime(35));
    }

    @Test
    public void testIsPrimeWithPrimeBetweenIAndIPlus2() {
        assertTrue(MathUtils.isPrime(29));
    }

    @Test
    public void testMathUtilsNotNull() {
        MathUtils mathUtils = new MathUtils();
        assertNotNull(mathUtils);
    }
}