## Solution MinCostClimbingStairs

**Step 1**: Take the Examples to tests
**Step 2**: All zeros, min length, empty and under min length Tests. Max range and Max +1 test as well as under min and over max tests as well as upper limmit.

**Findings**: No tests for min and max length as well as cost value. Additional not correct handling of the logic as the example comes back false.

Implemented input tests.
Corrected Bug: dp[0] was = 0 not equals cost[0]. So the first value was always 0 instead of the real cost.

**JaCoCO**: Reports 0 of 14 Missed Instructions and 0 of 14 Missed Branches. So 100% Coverage