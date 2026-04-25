# Contracts for Find All Duplicates in an Array

## Preconditions
- `nums` is not `null`.
- `nums.length` is in the range `[1, 100000]`.
- Every value is in the range `[1, n]`, where `n = nums.length`.
- Each value appears at most twice.

## Invariants
- At method start and method end: for every element in the working array, `abs(nums[i])` remains in the range `[1, n]`.
- After each state-changing operation (`nums[index] = -nums[index]`): `abs(nums[index])` remains in the range `[1, n]`.

## Postconditions
- The returned list is not `null`.
- Every value in the returned list appears exactly twice in the original input.
- The returned list contains all and only the duplicated values.
- Duplicate values are returned in the same detection order as the algorithm traversal.

## Implementation Pre/Post-condition
- Preconditions are enforced via `IllegalArgumentException`.
- Invariant and postcondition violations are enforced via `IllegalStateException`.
- Postcondition validation uses a clone of the original input to verify correctness without depending on the sign-mutated working array.

## JaCoCo
- Total:
	- Missed Instructions: 20 of 256 (92% covered)
	- Missed Branches: 5 of 36 (86% covered)
	- Missed Complexity: 5 of 23
	- Missed Lines: 4 of 51
	- Missed Methods: 0 of 5

- Per method:
	- `validatePostconditions(int[], List)`: Instructions 82%, Branches 75%, Missed Complexity 3 of 7, Missed Lines 3 of 17, Missed Methods 0 of 1
	- `checkInvariants(int[])`: Instructions 85%, Branches 66%, Missed Complexity 2 of 4, Missed Lines 1 of 6, Missed Methods 0 of 1
	- `validatePreconditions(int[])`: Instructions 100%, Branches 100%, Missed Complexity 0 of 8, Missed Lines 0 of 14, Missed Methods 0 of 1
	- `findDuplicates(int[])`: Instructions 100%, Branches 100%, Missed Complexity 0 of 3, Missed Lines 0 of 13, Missed Methods 0 of 1
	- `FindAllDuplicatesInArray()`: Instructions 100%, Branches n/a, Missed Complexity 0 of 1, Missed Lines 0 of 1, Missed Methods 0 of 1

- Missed lines in the invariants and Postconditions part that would have to have the Preconditiones or the Function to missbehave.
