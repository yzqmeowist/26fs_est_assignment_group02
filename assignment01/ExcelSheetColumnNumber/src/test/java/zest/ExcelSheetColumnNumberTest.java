package zest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ExcelSheetColumnNumberTest {

    @ParameterizedTest
    @MethodSource("ValidTests")
    public void testValidCases(String columnTitle, long expected) {
        assertEquals(expected, ExcelSheetColumnNumber.titleToNumber(columnTitle));
    }

    public static Stream<Arguments> ValidTests() {
        return Stream.of(
            Arguments.of("A", 1L), // min input
            Arguments.of("AA", 27L), // double input
            Arguments.of("ZZZZZZZ", 8353082582L) // max 7-character input
        );
    }

    @ParameterizedTest
    @MethodSource("InvalidTests")
    public void testInvalidCases(String columnTitle) {
        assertThrows(IllegalArgumentException.class, () -> ExcelSheetColumnNumber.titleToNumber(columnTitle));  
    }

    public static Stream<Arguments> InvalidTests() {
        return Stream.of(
            Arguments.of((String) null), // null input
            Arguments.of(""), // empty input
            Arguments.of("1"), // number
            Arguments.of("a"), // lowercase character
            Arguments.of("A!"), // special character
            Arguments.of("AAAAAAAA") // to many input characters
        );
    }
}