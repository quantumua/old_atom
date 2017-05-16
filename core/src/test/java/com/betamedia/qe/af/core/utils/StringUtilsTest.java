package com.betamedia.qe.af.core.utils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.testng.annotations.Test;

/**
 * These tests check logic for StringUtil methods
 */
public class StringUtilsTest {

    private final String[] STRINGS = {"This", "is", "String", "for", "delimiting."};
    private final String COMMA = ",";
    private final String AT = "@";

    @Test
    public void testShouldCheckGenerateRandomId() {
        int sevenChars = 7;
        int tenChars = 10;
        assertTrue(StringUtils.generateRandomId(sevenChars).length() == sevenChars ||
                StringUtils.generateRandomId(sevenChars).length() == sevenChars - 1);
        assertTrue(StringUtils.generateRandomId(tenChars).length() == tenChars ||
                StringUtils.generateRandomId(tenChars).length() == tenChars - 1
        );
    }

    @Test
    public void shouldCheckGenerateUniqueIds() {
        Set<String> generatedIds = new HashSet<>();
        int count = 1000;
        int tenChars = 10;
        for (int k = 1; k <= count; k++) {
            generatedIds.add(StringUtils.generateRandomId(tenChars));
        }
        assertEquals(generatedIds.size(), count);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForNegative() {
        StringUtils.generateRandomId(-1);
    }

    @Test
    public void shouldCheckCommaDelimiter() {
        assertEquals(StringUtils.parseCommaDelimitedString(String.join(COMMA, STRINGS)),
                Arrays.asList(STRINGS));
    }

    @Test
    public void shouldCheckAtAsDelimiter() {
        assertEquals(StringUtils.parseDelimitedString(String.join(AT, STRINGS), AT),
                Arrays.asList(STRINGS));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNPEForNullString() {
        StringUtils.parseCommaDelimitedString(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldCheckNPEForNullString() {
        StringUtils.parseDelimitedString(null, COMMA);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNPEForNullDelimiter() {
        StringUtils.parseDelimitedString(String.join(AT, STRINGS), null);
    }
}