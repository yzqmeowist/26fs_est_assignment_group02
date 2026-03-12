package zest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SortColorsTest {

    @Test
    public void testSingleNumber() {
        int[] actual = {0};
        SortColors.sortColors(actual);
        int[] expected = {0};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSorted() {
        int[] actual = {0,1,2};
        SortColors.sortColors(actual);
        int[] expected = {0,1,2};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testReversed() {
        int[] actual = {2,1,0};
        SortColors.sortColors(actual);
        int[] expected = {0,1,2};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testOnlyOneColor() {
        int[] actual = {0,0,0,0};
        SortColors.sortColors(actual);
        int[] expected = {0,0,0,0};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testOnlyTwoColors() {
        int[] actual = {1,0};
        SortColors.sortColors(actual);
        int[] expected = {0,1};

        assertArrayEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("cases")
    public void testSortColors(int[] input, int[] expected) {
        SortColors.sortColors(input);
        assertArrayEquals(expected, input);
    }

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{2, 0, 2, 1, 1, 0},
                        new int[]{0, 0, 1, 1, 2, 2}
                ),
                Arguments.of(
                        new int[]{2, 1, 0, 2, 1, 0},
                        new int[]{0, 0, 1, 1, 2, 2}
                ),
                Arguments.of(
                        new int[]{0, 2, 1, 0, 2, 1, 0, 2, 1},
                        new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2}
                ),
                Arguments.of(
                        new int[]{1, 2, 0, 1, 2, 0, 1, 2, 0},
                        new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2}
                ),
                Arguments.of(
                        new int[]{2, 2, 2, 1, 1, 1, 0, 0, 0},
                        new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2}
                ),
                Arguments.of(
                        new int[]{0, 1, 0, 1, 2, 2, 1, 0, 2, 1, 0},
                        new int[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2}
                ),
                Arguments.of(
                        new int[]{2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1},
                        new int[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}
                )
        );
    }


}