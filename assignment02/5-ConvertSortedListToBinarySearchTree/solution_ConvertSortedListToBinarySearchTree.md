# ConvertSortedListToBinarySearchTree

## Task 1: Code Coverage
We use structural testing with specification-based testing for the test suite:
   - `sortedListToBSTReturnsNullForEmptyList()` verifies the empty-list case.
   - `sortedListToBSTBuildsBalancedSearchTreeFromSortedList()` verifies the expected tree structure for the example input `[-10, -3, 0, 5, 9]`.
   - `test_ListNodeConstructors()` verifies the constructors of `ListNode`.
   - `test_treeNodeConstructors()` verifies the constructors of `TreeNode`.
   - `printBFSTraversalPrintsEmptyTree()` verifies the output for an empty tree.
   - `printBFSTraversalPrintsTrimmedLevelOrder()` verifies the BFS-printing logic for a normal tree.
   - `mainPrintsExampleTraversal()` verifies the whole demo flow in `Main.main(...)`.

The report from Jacoco is as below:

| Element                             | Missed Instructions | Cov. | Missed Branches | Cov. | Missed | Cxty | Missed | Lines | Missed | Methods | Missed | Classes |
|-------------------------------------|---------------------|------|-----------------|------|--------|------|--------|-------|--------|---------|--------|---------|
| Main                                | 3/125               | 97%  | 1/10            | n/a  | 2      | 8    | 1      | 29    | 1      | 3       | 0      | 1       |
| ConvertSortedListToBinarySearchTree | 70/70               | 100% | 4/4             | 100% | 0      | 5    | 0      | 13    | 0      | 3       | 0      | 1       |
| TreeNode                            | 21/21               | 100% |                 | n/a  | 0      | 3    | 0      | 7     | 0      | 3       | 0      | 1       |
| ListNode                            | 18/18               | 100% |                 | n/a  | 0      | 3    | 0      | 3     | 0      | 3       | 0      | 1       |
| Total                               | 3 of 234            | 98%  | 1 of 14         | 92%  | 2      | 19   | 1      | 52    | 1      | 12      | 0      | 4       |

The test does not have 100% line coverage as there is no class instantiation for `Main`.

The test does not have 100% branch coverage. In particular, one branch is still reported as missed in `Main.printBFSTraversal(...)` at the following condition:

```java
while (i >= 0 && result.get(i).equals("null")) {
```

This is not a bug since the left side branch `i >= 0` is effectively unreachable:

- If `root == null`, the method returns early before it reaches this loop.
- If `root != null`, the BFS result list always contains at least one non-null element.

Therefore, the trimming loop may remove trailing `null` values, but it cannot remove the entire list and drive `i` down to `-1`.

## Task 2: Designing Contracts
The contracts for this problem are derived only from the problem description and constraints in README.md. 

We only design contracts for `ConvertSortedListToBinarySearchTree.sortedListToBST`.

No contracts for `ConvertSortedListToBinarySearchTree.sortedArrayToBST` as it is a private helper. 

* `ConvertSortedListToBinarySearchTree.sortedListToBST(ListNode head)`
  * Preconditions:
    - `head` can be `null`.
    - The linked list length is in `[0, 20000]`.
    - The linked list values are sorted in ascending order.
    - Every node value is in `[-100000, 100000]`.

  * Postconditions:
    - If the input list is empty, the result is `null`, otherwise the result is the root of a binary search tree.
    - The returned tree is built from the values of the input list.
    - The returned tree is height-balanced.

  * Invariants:
    - `ConvertSortedListToBinarySearchTree` is stateless, so it has no invariant.

## Task 3: Testing Contracts

The tests based on contracts we design in Task 2 are:
   - `test_contractAcceptsBoundaryValueInput()` verifies that valid boundary values `-100000` and `100000` are accepted.
   - `test_contractAcceptsInputLengthExactly20000()` verifies that an input list of length exactly `20000` is accepted.
   - `test_contractRejectsUnsortedInput()` verifies that a non-sorted input list triggers an `AssertionError`.
   - `test_contractRejectsValueBelowMinimum()` verifies that a value below `-100000` triggers an `AssertionError`.
   - `test_contractRejectsValueAboveMaximum()` verifies that a value above `100000` triggers an `AssertionError`.
   - `test_contractRejectsInputLongerThan20000()` verifies that an input list longer than `20000` triggers an `AssertionError`.

## Task 4: Property-Based Testing

We use `jqwik` to generate many legal sorted inputs automatically. The properties for this problem are:
   - `property_emptyInputReturnsNull()` verifies that the empty input still returns `null`.
   - `property_inorderTraversalEqualsInput()` verifies that for any generated legal sorted input, the inorder traversal of the returned tree equals the input sequence.
   - `property_resultIsHeightBalanced()` verifies that for any generated legal sorted input, the returned tree is height-balanced.