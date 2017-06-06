package com.betamedia.atom.core.fwdataaccess.repository.util;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * @author mbelyaev
 * @since 5/31/17
 */
public class RepositoryVersion implements Comparable<RepositoryVersion> {
    public final String implementationVersion;
    public final LocalDateTime revisionDate;

    public RepositoryVersion(String implementationVersion, LocalDateTime revisionDate) {
        this.implementationVersion = implementationVersion;
        this.revisionDate = revisionDate;
    }

    @Override
    public int compareTo(RepositoryVersion o) {
        if (o.implementationVersion == null) {
            return handleNulls(this.revisionDate, o.revisionDate, LocalDateTime::compareTo);
        }
        int diff = handleNulls(this.implementationVersion, o.implementationVersion, RepositoryVersion::compareVersionStrings);
        if (diff != 0) {
            return diff;
        }
        return handleNulls(this.revisionDate, o.revisionDate, LocalDateTime::compareTo);
    }

    private static <T> int handleNulls(T o1, T o2, BiFunction<T, T, Integer> biFunction) {
        if (o1 == null) {
            if (o2 == null) {return 0;}
            return +1;
        }
        if (o2 == null) {return -1;}
        return biFunction.apply(o1, o2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositoryVersion that = (RepositoryVersion) o;
        return this.compareTo(that) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(implementationVersion, revisionDate);
    }

    /**
     * Compares two version strings.
     * <p>
     * Use this instead of String.compareTo() for a non-lexicographical
     * comparison that works for version strings. e.g. "1.10".compareTo("1.6").
     *
     * @param str1 a string of ordinal numbers separated by decimal points.
     * @param str2 a string of ordinal numbers separated by decimal points.
     * @return The result is a negative integer if str1 is _numerically_ less than str2.
     * The result is a positive integer if str1 is _numerically_ greater than str2.
     * The result is zero if the strings are _numerically_ equal.
     * @note It does not work if "1.10" is supposed to be equal to "1.10.0".
     */
    private static int compareVersionStrings(String str1, String str2) {
        String[] vals1 = str1.split("\\.");
        String[] vals2 = str2.split("\\.");
        int i = 0;
        // set index to first non-equal ordinal or length of shortest version string
        while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
            i++;
        }
        // compare first non-equal ordinal number
        if (i < vals1.length && i < vals2.length) {
            int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff);
        }
        // the strings are equal or one string is a substring of the other
        // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
        return Integer.signum(vals1.length - vals2.length);
    }
}
