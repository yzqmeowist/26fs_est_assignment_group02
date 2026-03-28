# ValidPerfectSquare
## Specification-Based Testing Principles

* Test Scenarios
    * The smallest valid input (`num = 1`).
    * Small perfect squares (e.g., `4`, `9`, `16`).
    * Small non-perfect squares (e.g., `2`, `3`, `5`).
    * Numbers just below a perfect square (e.g., `8`, `15`).
    * Numbers just above a perfect square (e.g., `10`, `17`).
    * Larger perfect squares.
    * Larger non-perfect squares.
    * Very large perfect squares within the integer range.
    * Very large non-perfect squares close to the upper boundary.
    * The boundary values
        * Input range:
            * lower boundary: `num = 1`
            * upper boundary: `num = 2^31 - 1 = 2147483647`
        * Largest perfect square within the integer range:
            * `46340^2 = 2147395600`


* Test techniques
    * Equivalence Partitioning
    * Boundary Value Analysis

The source code contains bugs: YES

6 out of 8 tests passed. The failing tests are `testLargePerfectSquare`
and `testLargestPerfectSquare`.

The issue is caused by the binary search condition `while (left < right)`,
which stops the search when `left == right`. This prevents the final
candidate value from being checked, causing some perfect squares (e.g.,
`1024` and `2147395600`) to return `false`.

Changing the condition to `while (left <= right)` fixes the issue.

## Structural Testing
The report from Jacoco is as below:

| Element | Class Coverage | Method Coverage | Line Coverage | Branch Coverage |
|--------|----------------|-----------------|---------------|----------------|
| `ValidPerfectSquare` | 100% (1/1) | 100% (1/1) | 93% (14/15) | 90% (9/10) |

The tests cover the main logic of the algorithm, including:
* small perfect squares,
* small non-perfect squares,
* values around perfect squares,
* larger perfect squares,
* and values near the upper boundary of the input range.

Line and branch coverage are slightly below 100% because the exception
case (`num <= 0`) is not triggered by the tests. 

To cover this missing branch, additional tests were added for `num = 0`
and for a negative input. These tests verify that invalid inputs are
rejected with an `IllegalArgumentException`.

After adding these tests, the JaCoCo report was as follows:

| Element | Class Coverage | Method Coverage | Line Coverage | Branch Coverage |
|--------|----------------|-----------------|---------------|----------------|
| `ValidPerfectSquare` | 100% (1/1) | 100% (1/1) | 100% (15/15) | 100% (10/10) |

## Mutation Testing
The report from Pit Test is as below:

| Number of Classes | Line Coverage | Mutation Coverage | Test Strength |
|-------------------|---------------|-------------------|---------------|
| 1                 | 94% (16/17)   | 94% (16/17)       | 94% (16/17)   |


Most generated mutants were killed by the tests, indicating that the test
suite is effective at detecting incorrect behavior.

One surviving mutant changes the boundary in the condition square < num. 
This does not affect observable behavior because the equality case is already 
handled by the preceding condition if (square == num). Therefore, no additional 
test was added.