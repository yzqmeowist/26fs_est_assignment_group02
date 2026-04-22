package zest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeIntervalsTest {

    @Test
    void testEmptyInput() {
        MergeIntervals solver = new MergeIntervals();

        assertArrayEquals(new int[][]{}, solver.merge(new int[][]{}));
    }

    @Test
    void testSingleInterval() {
        MergeIntervals solver = new MergeIntervals();

        assertArrayEquals(new int[][]{{1, 3}}, solver.merge(new int[][]{{1, 3}}));
    }

    @Test
    void testNonOverlappingIntervals() {
        MergeIntervals solver = new MergeIntervals();

        assertArrayEquals(
                new int[][]{{1, 2}, {4, 5}, {7, 8}},
                solver.merge(new int[][]{{1, 2}, {4, 5}, {7, 8}})
        );
    }

    @Test
    void testOverlappingIntervals() {
        MergeIntervals solver = new MergeIntervals();

        assertArrayEquals(
                new int[][]{{1, 6}, {8, 10}, {15, 18}},
                solver.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})
        );
    }

    @Test
    void testTouchingIntervals() {
        MergeIntervals solver = new MergeIntervals();

        assertArrayEquals(
                new int[][]{{1, 5}},
                solver.merge(new int[][]{{1, 4}, {4, 5}})
        );
    }

    @Test
    void testContainedInterval() {
        MergeIntervals solver = new MergeIntervals();

        assertArrayEquals(
                new int[][]{{1, 10}},
                solver.merge(new int[][]{{1, 10}, {2, 3}, {4, 8}})
        );
    }

    @Test
    void testUnsortedInput() {
        MergeIntervals solver = new MergeIntervals();

        assertArrayEquals(
                new int[][]{{1, 6}, {8, 10}},
                solver.merge(new int[][]{{8, 10}, {1, 3}, {2, 6}})
        );
    }

    @Test
    void testNegativeNumbers() {
        MergeIntervals solver = new MergeIntervals();

        assertArrayEquals(
                new int[][]{{-5, 0}, {2, 4}},
                solver.merge(new int[][]{{-5, -1}, {-3, 0}, {2, 4}})
        );
    }

    @Test
    void testSameStartDifferentEnds() {
        MergeIntervals solver = new MergeIntervals();

        assertArrayEquals(
                new int[][]{{1, 5}},
                solver.merge(new int[][]{{1, 3}, {1, 5}})
        );
    }

}
