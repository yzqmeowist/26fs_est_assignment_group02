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

## JaCoCo (Task 1)

| Scope | Missed Instructions | Instruction Coverage | Missed Branches | Branch Coverage | Missed Complexity | Total Complexity | Missed Lines | Total Lines | Missed Methods | Total Methods |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| MergeIntervals | 0 of 79 | 100% | 0 of 6 | 100% | 0 | 6 | 0 | 13 | 0 | 3 |


The implementation class `MergeIntervals` achieved 100% line and branch coverage.

## Contracts
### Preconditions
- `intervals` is not `null`
- the number of intervals is in the range of `[0, 10^4]`
- every interval has length `2`
- for every interval `[start, end]`, `start <= end`

### Invariants
- The class is stateless, so there is no non-trivial object invariant.

### Postconditions
- the returned array is not `null`
- every returned interval has length `2`
- the returned intervals are sorted by start value
- no returned intervals overlap
- every original interval is covered by some returned interval

## Contract Testing
- Verified normal operation on valid inputs using the specification-based tests.
- Verified that invalid inputs are rejected with `IllegalArgumentException`:
  - `null` input
  - `null` interval inside the array
  - interval with length different from `2`
  - interval with `start > end`
- Verified the postconditions indirectly on valid inputs, since all returned 
intervals are non-overlapping and cover the original intervals in the tested cases. 

## Property-based testing
We used jqwik to test general interval-merging properties on many automatically 
generated values.

Tested properties:
- a single interval remains unchanged after merging
- two touching intervals are merged into one interval
- two non-overlapping intervals remain separate

Instead of generating arbitrary interval arrays, we generated a few integers and 
constructed valid interval inputs from them. This made the properties easier to 
understand and ensured that all generated inputs satisfied the problem constraints.