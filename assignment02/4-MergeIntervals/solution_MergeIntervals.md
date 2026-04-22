# Specification-based testing for MergeIntervals

## Tested partitions
- Empty input
- Single interval
- Non-overlapping intervals
- Overlapping intervals
- Touching intervals
- Contained intervals
- Unsorted input
- Negative values
- Intervals with same start

## Tested boundaries
- Smallest valid input size: 0 intervals
- Single interval
- Touching boundary case where one interval ends exactly when the next begins

## Bug found
The empty-input test revealed a bug.

Input:
- `[]`

Expected output:
- `[]`

Actual behavior:
- the method threw `ArrayIndexOutOfBoundsException`

Cause:
- the implementation assumed that at least one interval exists and accessed the first element of the array without handling the valid empty-input case.

## Fix
We added an early return for the case `intervals.length == 0`, returning an empty 2D array.

## JaCoCo

| Scope | Missed Instructions | Instruction Coverage | Missed Branches | Branch Coverage | Missed Complexity | Total Complexity | Missed Lines | Total Lines | Missed Methods | Total Methods |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| MergeIntervals | 0 of 79 | 100% | 0 of 6 | 100% | 0 | 6 | 0 | 13 | 0 | 3 |


The implementation class `MergeIntervals` achieved 100% line and branch coverage.