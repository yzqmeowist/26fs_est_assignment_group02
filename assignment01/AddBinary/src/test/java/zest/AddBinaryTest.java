package zest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class AddBinaryTest {

    @ParameterizedTest
    @MethodSource("boarders")
    public void testAddBinaryArguments(String a, String b, String expected) {
        assertEquals(expected, AddBinary.addBinary(a, b));
    }

     public static Stream<Arguments> boarders(){
        return Stream.of(
                Arguments.of("0", "0", "0"), // both a and b are legal
                Arguments.of("1", "0", "1"),  // both a and b are legal
                Arguments.of("1", "1", "10"),  // carry works correctly
                Arguments.of("1010", "11", "1101"), // different lengths of a and b
                Arguments.of("11", "1010", "1101"), // different lengths of a and b
                Arguments.of("11111", "11111", "111110"), // carry works correctly multible times
                Arguments.of("1"+"0".repeat(9999), "1", "1"+"0".repeat(9998)+"1"), // on point max a (10000 chars)
                Arguments.of("1", "1"+"0".repeat(9999), "1"+"0".repeat(9998)+"1") // on point max b (10000 chars)
            );
    }

    @ParameterizedTest
    @MethodSource("boarders2")
    public void testAddBinaryIllegalarguments(String a, String b) {
        assertThrows(IllegalArgumentException.class, () -> AddBinary.addBinary(a, b));
    }

     public static Stream<Arguments> boarders2(){
        return Stream.of(
                Arguments.of(null, "0"), // a is null
                Arguments.of("1", null),  // b is null
                Arguments.of("","1"), // a is empty
                Arguments.of("1",""), // b is empty
                Arguments.of("2", "1"), // a contains non-binary character
                Arguments.of("1", "3"),  // b contains non-binary character
                Arguments.of("01", "1"),  // a has leading zeros
                Arguments.of("1", "01"),  // b has leading zeros
                Arguments.of("01", "01"),  // both a and b have leading zeros
                Arguments.of("1".repeat(10001), "1"), // a is too long (> 10000)
                Arguments.of("1", "1".repeat(10001))  // b is too long (> 10000)
        );
    }
}