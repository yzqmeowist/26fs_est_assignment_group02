package zest;

import java.util.Arrays;

public class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        validatePreconditions(nums);

        int n = nums.length;
        int[] lengths = new int[n];
        int[] counts = new int[n];
        Arrays.fill(lengths, 1);
        Arrays.fill(counts, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (lengths[j] + 1 > lengths[i]) {
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    } else if (lengths[j] + 1 == lengths[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
        }

        int maxLength = 0;
        for (int length : lengths) {
            if (length > maxLength) {
                maxLength = length;
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (lengths[i] == maxLength) {
                result += counts[i];
            }
        }

        validatePostconditions(result, nums.length);

        return result;
    }

    private void validatePreconditions(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("nums must not be null");
        }

        if (nums.length < 1 || nums.length > 2000) {
            throw new IllegalArgumentException("nums length must be between 1 and 2000");
        }
    }

    private void validatePostconditions(int result, int length) {
        if (result < 1 || result > length) {
            throw new IllegalArgumentException("result must be between 1 and nums.length");
        }
    }
}
