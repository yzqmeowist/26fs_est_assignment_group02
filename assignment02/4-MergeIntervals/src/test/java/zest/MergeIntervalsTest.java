package zest;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testRejectNullInput() {
        MergeIntervals solver = new MergeIntervals();

        assertThrows(IllegalArgumentException.class,
                () -> solver.merge(null));
    }

    @Test
    void testRejectNullInterval() {
        MergeIntervals solver = new MergeIntervals();

        assertThrows(IllegalArgumentException.class,
                () -> solver.merge(new int[][]{{1, 3}, null}));
    }

    @Test
    void testRejectIntervalWithWrongLength() {
        MergeIntervals solver = new MergeIntervals();

        assertThrows(IllegalArgumentException.class,
                () -> solver.merge(new int[][]{{1, 2, 3}}));
    }

    @Test
    void testRejectIntervalWithStartGreaterThanEnd() {
        MergeIntervals solver = new MergeIntervals();

        assertThrows(IllegalArgumentException.class,
                () -> solver.merge(new int[][]{{5, 2}}));
    }

    @Property
    void singleIntervalStaysTheSame(
            @ForAll @IntRange(min = -1000, max = 1000) int start,
            @ForAll @IntRange(min = 0, max = 100) int length) {

        MergeIntervals solver = new MergeIntervals();

        int[][] input = new int[][]{{start, start + length}};
        assertArrayEquals(input, solver.merge(input));
    }

    @Property
    void touchingIntervalsAreMerged(
            @ForAll @IntRange(min = -1000, max = 1000) int start,
            @ForAll @IntRange(min = 0, max = 100) int length1,
            @ForAll @IntRange(min = 0, max = 100) int length2) {

        MergeIntervals solver = new MergeIntervals();

        int mid = start + length1;
        int end = mid + length2;

        int[][] input = new int[][]{{start, mid}, {mid, end}};
        assertArrayEquals(new int[][]{{start, end}}, solver.merge(input));
    }

    @Property
    void nonOverlappingIntervalsStaySeparate(
            @ForAll @IntRange(min = -1000, max = 1000) int start,
            @ForAll @IntRange(min = 0, max = 50) int length1,
            @ForAll @IntRange(min = 1, max = 50) int gap,
            @ForAll @IntRange(min = 0, max = 50) int length2) {

        MergeIntervals solver = new MergeIntervals();

        int firstEnd = start + length1;
        int secondStart = firstEnd + gap;
        int secondEnd = secondStart + length2;

        int[][] input = new int[][]{{start, firstEnd}, {secondStart, secondEnd}};
        assertArrayEquals(new int[][]{{start, firstEnd}, {secondStart, secondEnd}}, solver.merge(input));
    }
}
