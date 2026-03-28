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
    * Invalid inputs:
        * `digits == null`
        * `digits.length == 0`
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

| Element   | Class Coverage | Method Coverage | Line Coverage | Branch Coverage |
|-----------|----------------|-----------------|---------------|-----------------|
| `PlusOne` | 100% (1/1)     | 100% (1/1)      | 100% (10/10)  | 100% (8/8)      |

The initial specification-based tests covered the main increment logic, but missed the
defensive input validation branch:

`if (digits == null || digits.length == 0)`

To improve structural coverage, two additional tests were added:
* `testNullInput`
* `testEmptyInput`

These tests cover the exceptional behavior of the method and exercise the missing line 
and branches. After adding them, the test suite achieves full line and branch coverage 
for this class.

## Mutation Testing
The report from Pit Test is as below:

| Number of Classes | Line Coverage | Mutation Coverage | Test Strength |
|-------------------|---------------|-------------------|---------------|
| 1                 | 91% (10/11)   | 100% (11/11)      | 100% (11/11)  |

Although PIT does not report full line coverage, all mutants were killed. 
Therefore, mutation testing did not reveal any weakness in the test suite, 
so no additional tests were necessary.