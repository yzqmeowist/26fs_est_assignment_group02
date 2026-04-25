# Specification-based testing for NumberOfLongestIncreasingSubsequence

## Tested partitions
- Single-element array
- Two-element array, increasing
- Two-element array, equal
- Two-element array, decreasing
- Strictly increasing array
- Strictly decreasing array
- Array with all equal elements
- Array with multiple longest increasing subsequences
- Array with negative values
- Array with large values inside the valid range


## Tested boundaries
- Smallest valid input size: array of length 1
- Small two-element arrays to distinguish increasing, equal, and decreasing cases

## Bug found
The initial tests revealed a bug in the implementation.  
The dynamic-programming arrays were initialized with `0`, which caused incorrect 
LIS counts even for simple inputs such as `[5]` and `[1,2]`.

## Fix
Changed the initialization so that:
- `lengths` starts with `1`
- `counts` starts with `1`

## JaCoCo (Task 1)

| Scope | Missed Instructions | Instruction Coverage | Missed Branches | Branch Coverage | Missed Complexity | Total Complexity | Missed Lines | Total Lines | Missed Methods | Total Methods |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| Total | 37 of 163 | 77% | 1 of 18 | 94% | 3 | 13 | 5 | 28 | 2 | 4 |
| NumberOfLongestIncreasingSubsequence | 0 of 126 | 100% | 1 of 18 | 94% | 1 | 11 | 0 | 23 | 0 | 2 |
| Main | 37 of 37 | 0% | n/a | n/a | 2 | 2 | 5 | 5 | 2 | 2 |

The implementation class `NumberOfLongestIncreasingSubsequence` achieved 100% 
line coverage. The lower total coverage is caused by the additional `Main` class, 
which was not relevant for testing the algorithm itself. One branch in 
`NumberOfLongestIncreasingSubsequence` remained uncovered, although all lines of 
the implementation were executed.

## Contracts
### Preconditions
- `nums` is not null
- `nums.length` is in the range of `[1, 2000]`
- every element in `nums` is in the range of `[-10^6, 10^6]`

### Invariants
- The class is stateless, so there is no non-trivial object invariant

### Postconditions
- the returned value is at least `1`
- the returned value is at most `nums.length`

## Contract testing
- Verified normal operation on valid inputs using the specification-based tests.
- Verified that invalid inputs are rejected with `IllegalArgumentException`
  - `null` input
  - empty array
  - values below `-10^6`
  - values above `10^6`
- Verified the postcondition indirectly on valid inputs, since all test results 
stay within the range `[1, nums.length]`.

## Property-based testing
We used jqwik to test general properties of the algorithm instead of only fixed 
examples.

Tested properties:
- any single-element array has exactly one LIS
- any strictly increasing array has exactly one LIS
- any strictly decreasing array of length 4 has 4 LIS
- any array of four equal elements has 4 LIS

These properties helped us verify the general behavior of the implementation on 
many automatically generated inputs.