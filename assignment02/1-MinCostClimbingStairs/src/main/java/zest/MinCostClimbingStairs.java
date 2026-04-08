package zest;

public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null) {
            throw new IllegalArgumentException("cost must not be null");
        }

        int n = cost.length;
        if (n < 2 || n > 1000) {
            throw new IllegalArgumentException("cost length must be in range [2, 1000]");
        }

        for (int c : cost) {
            if (c < 0 || c > 999) {
                throw new IllegalArgumentException("each cost value must be in range [0, 999]");
            }
        }

        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }

        return Math.min(dp[n - 1], dp[n - 2]);
    }
}
