# MoveZeroes
## Specification-Based Testing Principles
* Test Scenarios
    * The array has numbers and zeroes.
    * The boundary value
      * The array is null.
      * The array contains only zeroes.
      * The array contains no zero.
      * The array contains multiple consecutive zeroes.
      * The array is single-element.
* Test techniques
    * Equivalence Partitioning
    * Boundary Value Analysis

The source code contains bugs: YES

All the tests, except the case where the array contains only zeroes, fail. We find that in the first pass the upper bound of the loop is wrong, which causes the last element of the array to never be handled.

To fix it, we change the upper bound from `numbers.length-1` to `numbers.length`. All tests pass. 

## Structural Testing
The report from Jacoco is as below:

| Element           | Missed Instructions | Cov. | Missed Branches | Cov. | Missed | Cxty | Missed | Lines | Missed | Methods |
|-------------------|---------------------|------|-----------------|------|--------|------|--------|-------|--------|---------|
| MoveZeroes()      | 3                   | 0%   | n/a             | n/a  | 1      | 1    | 1      | 1     | 1      | 1       |
| moveZeroes(int[]) | 39/39               | 100% | 8/8             | 100% | 0      | 5    | 0      | 11    | 0      | 1       |
| Total             | 3 of 49             | 92%  | 0 of 8          | 100% | 1      | 6    | 1      | 12    | 1      | 2       |

The test does not have 100% line coverage as there is no class instantiation.

The branch and statement coverage are all 100%. No extra test added.

## Mutation Testing
The report from Pit Test is as below:

| Number of Classes | Line Coverage | Mutation Coverage | Test Strength |
|-------------------|---------------|-------------------|---------------|
| 1                 | 11/12 -> 88%  | 7/7 -> 100%       | 7/7 -> 100%   | 

The mutation coverage is 100% which means all mutants are killed.