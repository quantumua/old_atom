package com.betamedia.qe.af.core.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
}
