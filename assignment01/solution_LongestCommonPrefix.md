# LongestCommonPrefix
## Specification-Based Testing Principles
* Test Scenarios
    * The shared starting substring can be found.
    * The shared starting substring cannot be found.
    * The boundary value
      * The array is null.
      * The group contains only one string.
      * The group contains empty strings.
      * The group contains strings that are identical.
* Test techniques
    * Equivalence Partitioning
    * Boundary Value Analysis

The source code contains bugs: NO

## Structural Testing
The report from Jacoco is as below:

| Element                       | Missed Instructions | Cov. | Missed Branches | Cov. | Missed | Cxty | Missed | Lines | Missed | Methods |
|-------------------------------|---------------------|------|-----------------|------|--------|------|--------|-------|--------|---------|
| LongestCommonPrefix()         | 3                   | 0%   | n/a             | n/a  | 1      | 1    | 1      | 1     | 1      | 1       |
| longestCommonPrefix(String[]) | 42/46               | 91%  | 8/10            | 80%  | 2      | 6    | 2      | 11    | 0      | 1       |
| Total                         | 7 of 49             | 85%  | 2 of 10         | 100% | 3      | 7    | 3      | 12    | 1      | 2       |

The test does not have 100% line coverage as there is no class instantiation.

There is a branch not covered in line 18:

```java
if (strs.length == 0) { // 1 of 2 branches missed
    return ""; // uncovered line
}
```

As there is a constraint: "The array contains between 1 and 200 strings.". Therefore, the lengths of `strs` can never be 0. We should fix it by removing the empty check of `strs`.

There is a branch not covered in line 26:

```java
while (!strs[i].startsWith(prefix)) {
    if (prefix.isEmpty()) { // 1 of 2 branches missed
        return ""; // uncovered line
    }
    prefix = prefix.substring(0, prefix.length() - 1);
}
```

However, the condition of while-loop infers that `prefix` is not an empty string, otherwise the loop cannot be entered, which means `prefix.isEmpty()` is always false. Therefore, the true branch including `return` inside never runs.

To fix it, we remove the empty check of `prefix` and get a new report from Jacoco:


| Element                       | Missed Instructions | Cov. | Missed Branches | Cov. | Missed | Cxty | Missed | Lines | Missed | Methods |
|-------------------------------|---------------------|------|-----------------|------|--------|------|--------|-------|--------|---------|
| LongestCommonPrefix()         | 3                   | 0%   | n/a             | n/a  | 1      | 1    | 1      | 1     | 1      | 1       |
| longestCommonPrefix(String[]) | 36/36               | 100% | 6/6             | 100% | 0      | 4    | 0      | 7     | 0      | 1       |
| Total                         | 3 of 39             | 92%  | 0 of 6          | 100% | 1      | 5    | 1      | 8     | 1      | 2       |

The branch and statement coverage are all 100%. No extra test added.

## Mutation Testing
The report from Pit Test is as below:

| Number of Classes | Line Coverage | Mutation Coverage | Test Strength |
|-------------------|---------------|-------------------|---------------|
| 1                 | 7/8 -> 88%    | 6/6 -> 100%       | 6/6 -> 100%   | 

The mutation coverage is 100% which means all mutants are killed.