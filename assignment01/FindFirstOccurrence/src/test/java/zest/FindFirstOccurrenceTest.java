package zest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.of;

public class FindFirstOccurrenceTest {

    @ParameterizedTest
    @MethodSource({"cases"})
    void testStrStrCases(String haystack, String needle, int expected) {
        assertEquals(expected, FindFirstOccurrence.strStr(haystack, needle));
    }

    static Stream<Arguments> cases() {
        return Stream.of(
                /* Specification-based Testing */

                /* 1. found (return the position) */
                // from the beginning
                of("nihao", "nih", 0),

                // from the middle
                of("hello", "ll", 2),

                // from the end
                of("gutentag", "tag", 5),

                /* 2. not found (return -1) */
                // needle longer than haystack
                of("abc", "abcd", -1),

                // needle not found
                of("fghijk", "de", -1),

                /* 3. boundary value */
                // haystack equals needle
                of("great", "great", 0),

                // haystack and needle have minimum length 1
                of("a", "a", 0),
                of("b", "c", -1),
                of("abc", "a", 0),

                // multiple occurrences of needle: return the first
                of("abcabc", "abc", 0)

        );
    }

    @ParameterizedTest
    @MethodSource({"nullCases"})
    void testStrStrNullCases(String haystack, String needle) {
        assertThrows(IllegalArgumentException.class,
                () -> FindFirstOccurrence.strStr(haystack, needle));
    }

    static Stream<Arguments> nullCases() {
        return Stream.of(
                of(null, "a"),
                of("a", null),
                of(null, null)
        );
    }

}
