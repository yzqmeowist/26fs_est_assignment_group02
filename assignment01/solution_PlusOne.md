# PlusOne
## Specification-Based Testing Principles
* Test Scenarios
    * The array represents a valid non-negative integer with digits between 0 and 9 and no leading zeroes.
    * Increment without carry.
    * Increment with carry affecting only the last digit.
    * Increment where carry propagates through multiple digits.
    * Increment where all digits are 9, producing an additional digit.
    * Single-digit inputs.
    * Numbers containing internal zeroes.
    * The boundary values
      * Array length:
          * lower boundary: `digits.length == 1`
          * upper boundary: `digits.length == 100`
      * Digit values:
          * lower boundary: `digits[i] = 0`
          * upper boundary: `digits[i] = 9`
* Test techniques
    * Equivalence Partitioning
    * Boundary Value Analysis

The source code contains bugs: YES

5 out of 7 tests pass. The failing tests are `testSingleNine` and `testAllNines`, 
which check whether the method correctly handles cases where incrementing the 
number increases the length of the array.

When all digits are `9`, the loop sets each digit to `0` and exits. The method 
then creates a new result array. However, the implementation incorrectly 
creates an array of length `digits.length`, which is too small for the correct 
result.

For example:

`[9]` + 1 should produce `[1,0]` but the implementation creates an array of 
length 1, producing `[1]`.

To fix it, we change the following line `int[] result = new int[digits.length];` 
to `int[] result = new int[digits.length + 1];`. This correctly increases the
array size when incrementing a number consisting entirely of 9s.

## Structural Testing
The report from Jacoco is as below:

| Element           | Class Coverage | Method Coverage | Line Coverage | Branch Coverage |
|-------------------|----------------|-----------------|---------------|-----------------|
| `PlusOne`         | 100% (1/1)     | 100% (1/1)      | 90% (9/10)    | 75% (6/8)       |

The tests cover the main logic of the algorithm completely, including:
* increment without carry,
* increment with carry on the last digit, 
* carry propagation across multiple digits, 
* and the case where all digits are 9.

The uncovered line and missing branches come from the defensive input validation:

`if (digits == null || digits.length == 0)`

These cases were not tested because they are outside the problem specification, which only allows valid arrays with `1 ≤ digits.length ≤ 100`.

Therefore, no additional tests were added during structural testing.

## Mutation Testing
The report from Pit Test is as below:

| Number of Classes | Line Coverage | Mutation Coverage | Test Strength |
|-------------------|---------------|-------------------|---------------|
| 1                 | 82% (9/11)    | 100% (11/11)      | 100% (11/11)  |

All mutants were killed by the tests, giving a mutation coverage of 100%.
This shows that the test suite can detect incorrect behavior.

Line coverage is below 100% because the defensive check for `null`
or empty arrays is not tested. These cases are outside the problem
specification (`1 ≤ digits.length ≤ 100`).