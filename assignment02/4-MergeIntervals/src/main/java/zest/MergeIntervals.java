package zest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        validatePreconditions(intervals);

        if (intervals.length == 0) {
            int[][] output = new int[][]{};
            validatePostconditions(intervals, output);
            return output;
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);

        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                newInterval = interval;
                result.add(newInterval);
            }
        }
        int[][] output = result.toArray(new int[result.size()][]);

        validatePostconditions(intervals, output);

        return output;
    }

    private void validatePreconditions (int[][] intervals) {
        if (intervals == null) {
            throw new IllegalArgumentException("intervals must not be null");
        }

        if (intervals.length > 10000) {
            throw new IllegalArgumentException("interval length exceeds 10000");
        }

        for (int i = 0; i < intervals.length; i++) {
            if  (intervals[i] == null) {
                throw new IllegalArgumentException("intervals must not be null");
            }
            if (intervals[i].length != 2) {
                throw new IllegalArgumentException("interval length exceeds 2");
            }
            if (intervals[i][0] > intervals[i][1]) {
                throw new IllegalArgumentException("interval start must always be smaller than end");
            }
        }
    }

    private void validatePostconditions(int[][] input, int[][] output) {
        if (output == null) {
            throw new IllegalStateException("output must not be null");
        }

        for (int i = 0; i < output.length; i++) {
            if (output[i] == null) {
                throw new IllegalStateException("output must not be null");
            }
            if (output[i].length != 2) {
                throw new IllegalStateException("each output interval must have length 2");
            }
            if (output[i][0] > output[i][1]) {
                throw new IllegalStateException("output interval start must be <= end");
            }
        }

        for (int i = 1; i < output.length; i++) {
            if (output[i - 1][0] > output[i][0]) {
                throw new IllegalStateException("output intervals must be sorted by start");
            }
            if (output[i][0] <= output[i - 1][1]) {
                throw new IllegalStateException("output intervals must not overlap");
            }
        }
        for (int[] in : input) {
            boolean covered = false;
            for (int[] out : output) {
                if (out[0] <= in[0] && in[1] <= out[1]) {
                    covered = true;
                    break;
                }
            }
            if (!covered) {
                throw new IllegalStateException("every input interval must be covered by some output interval");
            }
        }
    }
}
