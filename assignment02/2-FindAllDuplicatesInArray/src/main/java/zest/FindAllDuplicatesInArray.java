package zest;

import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicatesInArray {
    public List<Integer> findDuplicates(int[] nums) {
        validatePreconditions(nums);
        checkInvariants(nums);

        int[] original = nums.clone();
        List<Integer> duplicates = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                duplicates.add(Math.abs(nums[i]));
            } else {
                checkInvariants(nums);
                nums[index] = -nums[index];
            }
        }

        checkInvariants(nums);
        validatePostconditions(original, duplicates);
        return duplicates;
    }

    private void validatePreconditions(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("nums must not be null");
        }

        int n = nums.length;
        if (n < 1 || n > 100000) {
            throw new IllegalArgumentException("nums length must be in range [1, 100000]");
        }

        int[] frequency = new int[n + 1];
        for (int value : nums) {
            if (value < 1 || value > n) {
                throw new IllegalArgumentException("each value must be in range [1, n]");
            }

            int count = frequency[value] + 1;
            if (count > 2) {
                throw new IllegalArgumentException("each value may appear at most twice");
            }
            frequency[value] = count;
        }
    }

    private void checkInvariants(int[] nums) {
        int n = nums.length;
        for (int value : nums) {
            int absoluteValue = Math.abs(value);
            if (absoluteValue < 1 || absoluteValue > n) {
                throw new IllegalStateException("invariant failed: abs(nums[i]) must stay in [1, n]");
            }
        }
    }

    private void validatePostconditions(int[] original, List<Integer> duplicates) {
        if (duplicates == null) {
            throw new IllegalStateException("postcondition failed: result must not be null");
        }

        int n = original.length;
        int[] frequency = new int[n + 1];
        List<Integer> expected = new ArrayList<>();
        for (int value : original) {
            frequency[value]++;
            int count = frequency[value];
            if (count == 2) {
                expected.add(value);
            }
        }

        for (int duplicate : duplicates) {
            if (frequency[duplicate] != 2) {
                throw new IllegalStateException("postcondition failed: each reported duplicate must appear exactly twice");
            }
        }

        if (!duplicates.equals(expected)) {
            throw new IllegalStateException("postcondition failed: result must contain all and only duplicated values in detection order");
        }
    }
}
