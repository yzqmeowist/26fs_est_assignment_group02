# SortColors
## Specification-Based Testing Principles

* Test Scenarios
    * Single-element arrays.
    * Already sorted arrays.
    * Reverse sorted arrays.
    * Arrays containing only one color (all 0s, all 1s, or all 2s).
    * Arrays containing two of the three colors.
    * Arrays containing all three colors in random order.
    * Arrays with alternating color patterns.
    * Larger arrays with mixed color distributions.
    * The boundary values
        * Array length:
            * lower boundary: `nums.length == 1`
            * upper boundary: `nums.length == 300`
        * Element values:
            * lower boundary: `nums[i] = 0`
            * upper boundary: `nums[i] = 2`


* Test techniques
    * Equivalence Partitioning
    * Boundary Value Analysis

The source code contains bugs: NO

All tests passed successfully. The implementation correctly handles
single-element arrays, already sorted arrays, reverse-sorted arrays,
arrays containing one or two colors, and larger arrays with mixed color 
patterns.

## Structural Testing
The report from Jacoco is as below:

| Element | Class Coverage | Method Coverage | Line Coverage | Branch Coverage |
|--------|----------------|-----------------|---------------|----------------|
| `SortColors` | 100% (1/1) | 100% (2/2) | 94% (16/17) | 87% (7/8) |

The tests cover the main logic of the algorithm, including:
* already sorted arrays,
* reverse sorted arrays,
* arrays containing only one color,
* arrays containing two colors,
* single-element arrays,
* and longer arrays with mixed color patterns.

Line and branch coverage are slightly below 100% because the defensive
null check (`if (nums == null)`) is not triggered by the tests.
This case is outside the problem specification, which guarantees
a valid input array containing only 0, 1, and 2.

Therefore, no additional tests were added during structural testing.

## Mutation Testing
The report from Pit Test is as below:

| Number of Classes | Line Coverage | Mutation Coverage | Test Strength |
|-------------------|---------------|-------------------|---------------|
| 1 | 90% (18/20) | 100% (10/10) | 100% (10/10) |

All generated mutants were killed by the tests, resulting in a mutation
coverage of 100%. This indicates that the test suite is effective at
detecting incorrect behavior in the implementation.

Line coverage is slightly below 100% because the defensive null check

`if (nums == null)`

is not triggered by the tests. This case is outside the problem
specification, which guarantees a valid input array containing only
values 0, 1, and 2.

Therefore, no additional tests were added during mutation testing.