package zest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CompareVersionNumbersTest {

    @ParameterizedTest
    @MethodSource("ValidTests")
    public void testValid(String version1, String version2, int expected) {
        assertEquals(expected, CompareVersionNumbers.compareVersion(version1, version2));
    }

    public static Stream<Arguments> ValidTests() {
        return Stream.of(
            Arguments.of("1.0", "1.1", -1), // version1 < version2
            Arguments.of("1.1", "1.0", 1), // version1 > version2
            Arguments.of("1.0", "1.0", 0), // version1 == version2
            Arguments.of("1.0.0", "1.0", 0), // version1 == version2 different length
            Arguments.of("1.0", "1.0.1", -1), // version2 has more parts //Added after
            Arguments.of("1.001", "1.01", 0), // version1 == version2 different version numbers with leading zeros
            Arguments.of("1","0", 1), // shortest allowed
            Arguments.of("1." + "0".repeat(498), "1.0", 0), // exactly 500 chars (1. + 498 zeros)
            Arguments.of("1.0", "1." + "0".repeat(498), 0), // version2 exactly 500 chars // Added after
            Arguments.of("2147483647.0", "1.0", 1),  // Integer.MAX_VALUE
            Arguments.of("0.2147483647", "0.1", 1),  // MAX_VALUE in second position
            Arguments.of("1.0", "2147483647.0", -1) // Compare against MAX_VALUE

        );
    }

    @ParameterizedTest
    @MethodSource("InvalidTests")
        public void testInvalid(String version1, String version2) {
            assertThrows(IllegalArgumentException.class, () -> CompareVersionNumbers.compareVersion(version1, version2));
        }
    
    public static Stream<Arguments> InvalidTests() {
        return Stream.of(
            Arguments.of(null, "1.0"), // Null version1
            Arguments.of("1.0", null), // Null version2
            Arguments.of("", "1.0"), // Empty version1
            Arguments.of("1.0", ""), // Empty version2
            Arguments.of("1." + "0".repeat(499), "1.0"), // exactly 501 chars - too long
            Arguments.of("1." + "0".repeat(500), "1.0"), // too long version1
            Arguments.of("1.0", "1." + "0".repeat(500)), // too long version2
            Arguments.of("1..0", "1.0"), // Invalid format in version1
            Arguments.of("1.0", "1..0"), // Invalid format in version2
            Arguments.of("a.b.c", "1.0"), // Non-numeric characters in version1
            Arguments.of("1.0", "a.b.c"), // Non-numeric characters in version2
            Arguments.of("2147483648.0", "1.0"),  // Exceeds MAX_VALUE by 1
            Arguments.of("-2147483649.0", "1.0"), // Below MIN_VALUE (if negatives allowed)
            Arguments.of("-1.0", "1.0"),  // Negative number in version1
            Arguments.of("1.0", "-1.0")  // Negative number in version2
        );
    }   
}
