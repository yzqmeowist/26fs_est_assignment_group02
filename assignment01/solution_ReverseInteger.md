# ReverseInteger
## Specification-Based Testing Principles
* Test Scenarios
    * Zero.
    * Positive integers.
    * Negative integers.
    * Integers ending in zero.
    * Single-digit integers.
    * Overflow when reversing digits.
    * The boundary values
        * Integer range:
            * lower boundary: `-2^31 = -2147483648`
            * upper boundary: `2^31 - 1 = 2147483647`


* Test techniques
    * Equivalence Partitioning
    * Boundary Value Analysis

The source code contains bugs: NO

All tests passed successfully. No incorrect behavior was detected during
specification-based testing. The implementation correctly handles zero, positive
and negative integers, numbers ending in zero, single-digit inputs, and
overflow cases.

## Structural Testing
The report from Jacoco is as below:

| Element | Class Coverage | Method Coverage | Line Coverage | Branch Coverage |
|--------|----------------|-----------------|---------------|----------------|
| `ReverseInteger` | 100% (1/1) | 100% (1/1) | 100% (10/10) | 85% (12/14) |


The tests cover the main logic of the algorithm, including:
* reversing zero,
* reversing positive integers,
* reversing negative integers,
* handling integers ending in zero,
* handling single-digit integers,
* and detecting overflow cases.

Branch coverage is not 100% because the equality-based overflow checks are not
reachable for valid 32-bit integer inputs:

`reversed == Integer.MAX_VALUE / 10 && digit > 7`

`reversed == Integer.MIN_VALUE / 10 && digit < -8`

Any input that would trigger these branches would already be outside the valid
input range before reversal.

Therefore, no additional tests were added during structural testing.

## Mutation Testing
The report from Pit Test is as below:

| Number of Classes | Line Coverage | Mutation Coverage | Test Strength |
|-------------------|---------------|-------------------|---------------|
| 1                 | 92% (11/12)   | 81% (13/16)       | 81% (13/16)   |

Out of 16 generated mutants, 13 were killed by the tests, resulting in a
mutation coverage of 81%.

The surviving mutants are located in the overflow and underflow checks. These 
are boundary-related mutations, and no additional tests were added because the 
remaining uncovered behavior appears to be tied to overflow boundary checks 
that are unreachable for valid 32-bit integer inputs.