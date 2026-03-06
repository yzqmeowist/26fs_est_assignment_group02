package zest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.of;

public class LongestCommonPrefixTest {

    @ParameterizedTest
    @MethodSource({"cases"})
    void testLongestCommonPrefixCases(String[] strs, String expected) {
        assertEquals(expected, LongestCommonPrefix.longestCommonPrefix(strs));
    }

    static Stream<Arguments> cases() {
        return Stream.of(
                /* Specification-based Testing */

                /* 1. found */
                of(new String[]{"est", "esths", "easports", "estfs"}, "e"),

                /* 2. not found (return an empty string) */
                of(new String[]{"abc", "efgh"}, ""),

                /* 3. boundary value */
                // only one string
                of(new String[]{"aaaaa"}, "aaaaa"),

                // contains empty strings
                of(new String[]{"", "ffss"}, ""),

                // contains strings that are identical
                of(new String[]{"id", "id"}, "id")
        );
    }

    @Test
    void nullInput_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> LongestCommonPrefix.longestCommonPrefix(null)
        );
    }
}
