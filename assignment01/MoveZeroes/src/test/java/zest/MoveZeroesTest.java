package zest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;

public class MoveZeroesTest {
    @ParameterizedTest
    @MethodSource({"cases"})
    void testMoveZeroesCases(int[] numbers, int[] expected) {
        MoveZeroes.moveZeroes(numbers);
        assertArrayEquals(expected, numbers);
    }

    static Stream<Arguments> cases() {
        return Stream.of(
                /* Specification-based Testing */

                /* 1. The array has numbers and zeroes */
                of(new int[]{0, 1, 0, 3, 12}, new int[]{1, 3, 12, 0, 0}),

                /* 2. boundary value */
                // contains only zeroes
                of(new int[]{0, 0, 0}, new int[]{0, 0, 0}),

                // contains no zero
                of(new int[]{3, -10, 23}, new int[]{3, -10, 23}),

                // contains multiple consecutive zeroes
                of(new int[]{8, 0, 0, 0, 0, -45, 14}, new int[]{8, -45, 14, 0, 0, 0, 0}),

                // single-element
                of(new int[]{233}, new int[]{233}),
                of(new int[]{0}, new int[]{0})
        );
    }

    @Test
    void nullInput_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> MoveZeroes.moveZeroes(null)
        );
    }
}