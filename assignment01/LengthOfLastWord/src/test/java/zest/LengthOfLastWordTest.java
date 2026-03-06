package zest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.of;

public class LengthOfLastWordTest {

    @ParameterizedTest
    @MethodSource({"cases"})
    void testLengthOfLastWordCases(String s, int expected) {
        assertEquals(expected, LengthOfLastWord.lengthOfLastWord(s));
    }

    static Stream<Arguments> cases() {
        return Stream.of(
                /* Specification-based Testing */

                /* 1. normal case */
                // two words
                of("Hello World", 5),

                // multiple words
                of("how are you", 3),

                /* 2. boundary value */
                // single word
                of("great", 5),

                // trailing spaces
                of("hey est   ", 3),

                // leading spaces
                of("   software testing",7),

                // multiple consecutive spaces
                of("he     she", 3)
        );
    }

    @Test
    void nullInput_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> LengthOfLastWord.lengthOfLastWord(null)
        );
    }
}
