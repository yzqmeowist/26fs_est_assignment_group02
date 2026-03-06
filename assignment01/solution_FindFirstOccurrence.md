# FindFirstOccurrence
## Specification-Based Testing Principles
* Test Scenarios
  * The needle can be found in the haystack.
    * The needle is found at the beginning of the haystack.
    * The needle is found at the middle of the haystack.
    * The needle is found at the end of the haystack.
  * The needle cannot be found in the haystack.
    * The needle is longer than the haystack.
    * The needle is not a part of the haystack.
  * The boundary value
    * The haystack equals with the needle.
    * The haystack and needle have the minimum length 1.
    * The needle occurs for multiple times so that the first occurrence should be returned.
    * The haystack and needle are both null, or one of them is null.
* Test techniques
  * Equivalence Partitioning
  * Boundary Value Analysis

The source code contains bugs: NO

## Structural Testing
The report from Jacoco is as below:

| Element                | Missed Instructions | Cov. | Missed Branches | Cov. | Missed | Cxty | Missed | Lines | Missed | Methods |
|------------------------|---------------------|------|-----------------|------|--------|------|--------|-------|--------|---------|
| FindFirstOccurrence()  | 3                   | 0%   | n/a             | n/a  | 1      | 1    | 1      | 1     | 1      | 1       |
| strStr(String, String) | 52                  | 100% | 100%            | 100% | 0      | 8    | 0      | 13    | 0      | 1       |
| Total                  | 3 of 55             | 94%  | 0 of 14         | 100% | 1      | 9    | 1      | 14    | 1      | 2       |

The test does not have 100% line coverage as there is no class instantiation. The statement, branch and condition coverage of method `strStr` are 100%.

No extra test added.

## Mutation Testing
The report from Pit Test is as below:

| Number of Classes | Line Coverage | Mutation Coverage | Test Strength |
|-------------------|---------------|-------------------|---------------|
| 1                 | 13/14 -> 93%  | 15/15 -> 100%     | 15/15 -> 100% | 

The mutation coverage is 100% which means all mutants are killed.