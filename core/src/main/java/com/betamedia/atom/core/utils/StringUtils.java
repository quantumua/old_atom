package com.betamedia.atom.core.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/16/17.
 */
public class StringUtils {

    public static final String COMMA = ",";

    public static List<String> parseCommaDelimitedString(String commaDelimitedString) {
        return parseDelimitedString(commaDelimitedString, COMMA);
    }

    public static List<String> parseDelimitedString(String delimitedString, String delimiter) {
        return Arrays.stream(delimitedString.split(delimiter))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static String generateRandomId(int length) {
        return new BigInteger(length * 5, new SecureRandom()).toString(32);
    }

    public static String generateNumbersSequence(int signsNumber) {
        return IntStream.range(0, signsNumber)
                .mapToObj(i -> generateDigitAsChar())
                .collect(Collectors.joining());
    }

    private static int generateDigit() {
        Random rand = new Random();
        return rand.nextInt(10);
    }

    private static String generateDigitAsChar() {
        return String.valueOf(generateDigit());

    }
}
