package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOfLongestIncreasingSubsequenceTest {

    @Test
    void testSingleElementArray() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertEquals(1, solver.findNumberOfLIS(new int[]{5}));
    }

    @Test
    void testTwoIncreasingElements() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertEquals(1, solver.findNumberOfLIS(new int[]{1, 2}));
    }

    @Test
    void testTwoEqualElements() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertEquals(2, solver.findNumberOfLIS(new int[]{1, 1}));
    }

    @Test
    void testTwoDecreasingElements() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertEquals(2, solver.findNumberOfLIS(new int[]{2, 1}));
    }

    @Test
    void testStrictlyIncreasingArrayHasOneLongestSubsequence() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertEquals(1, solver.findNumberOfLIS(new int[]{1, 2, 3, 4}));
    }

    @Test
    void testStrictlyDecreasingArrayHasOneLisPerElement() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertEquals(4, solver.findNumberOfLIS(new int[]{4, 3, 2, 1}));
    }

    @Test
    void testExampleFromSpecificationOne() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertEquals(2, solver.findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
    }

    @Test
    void testExampleFromSpecificationTwo() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertEquals(5, solver.findNumberOfLIS(new int[]{2, 2, 2, 2, 2}));
    }

    @Test
    void testMultipleLongestIncreasingSubsequencesWithDuplicates() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertEquals(3, solver.findNumberOfLIS(new int[]{1, 2, 2, 2, 3}));
    }

    @Test
    void testNegativeNumbers() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertEquals(1, solver.findNumberOfLIS(new int[]{-3, -2, -1}));
    }

    @Test
    void testMixOfNegativeZeroAndPositiveNumbers() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertEquals(1, solver.findNumberOfLIS(new int[]{-31, 0, 1}));
    }

    @Test
    void testLargeValue() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertEquals(1, solver.findNumberOfLIS(new int[]{-31, 0, 1_000_000}));
    }

    @Test
    void testLargeValueInBetween() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertEquals(2, solver.findNumberOfLIS(new int[]{-31, 0, 1_000_000, 1}));
    }

    @Test
    void testRejectNullInput() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertThrows(IllegalArgumentException.class,
                () -> solver.findNumberOfLIS(null));
    }

    @Test
    void testRejectEmptyArray() {
        NumberOfLongestIncreasingSubsequence solver =
                new NumberOfLongestIncreasingSubsequence();

        assertThrows(IllegalArgumentException.class,
                () -> solver.findNumberOfLIS(new int[]{}));
    }
}