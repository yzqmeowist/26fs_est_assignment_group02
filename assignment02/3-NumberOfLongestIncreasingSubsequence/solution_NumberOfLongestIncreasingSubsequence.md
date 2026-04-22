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


## JaCoCo

| Scope | Missed Instructions | Instruction Coverage | Missed Branches | Branch Coverage | Missed Complexity | Total Complexity | Missed Lines | Total Lines | Missed Methods | Total Methods |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| Total | 37 of 163 | 77% | 1 of 18 | 94% | 3 | 13 | 5 | 28 | 2 | 4 |
| NumberOfLongestIncreasingSubsequence | 0 of 126 | 100% | 1 of 18 | 94% | 1 | 11 | 0 | 23 | 0 | 2 |
| Main | 37 of 37 | 0% | n/a | n/a | 2 | 2 | 5 | 5 | 2 | 2 |

The implementation class `NumberOfLongestIncreasingSubsequence` achieved 100% line coverage. The lower 
total coverage is caused by the additional `Main` class, which was not relevant for testing the 
algorithm itself. One branch in `NumberOfLongestIncreasingSubsequence` remained uncovered, although 
all lines of the implementation were executed.