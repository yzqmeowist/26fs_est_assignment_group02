# LengthOfLastWord
## Specification-Based Testing Principles
* Test Scenarios
  * normal cases
    * A string with two words.
    * A string with multiple words.
  * boundary value
    * A null string.
    * A string with only a word.
    * A string with trailing spaces.
    * A string with leading spaces.
    * A string with multiple consecutive spaces.
* Test techniques
  * Equivalence Partitioning
  * Boundary Value Analysis

The source code contains bugs: NO

## Structural Testing
The report from Jacoco is as below:

| Element                  | Missed Instructions | Cov. | Missed Branches | Cov. | Missed | Cxty | Missed | Lines | Missed | Methods |
|--------------------------|---------------------|------|-----------------|------|--------|------|--------|-------|--------|---------|
| LengthOfLastWord()       | 3                   | 0%   | n/a             | n/a  | 1      | 1    | 1      | 1     | 1      | 1       |
| lengthOfLastWord(String) | 35/35               | 100% | 9/10            | 90%  | 1      | 6    | 0      | 10    | 0      | 1       |
| Total                    | 3 of 38             | 92%  | 1 of 10         | 90%  | 2      | 7    | 1      | 11    | 1      | 2       |

The test does not have 100% line coverage as there is no class instantiation. 

If we want to maximize the branch and condition coverage to 100%, the case that the input is an empty string should be considered. However, the constraint exists: "There is at least one word in the string." 

Therefore, no extra test should be added.

## Mutation Testing
The report from Pit Test is as below:

| Number of Classes | Line Coverage | Mutation Coverage | Test Strength |
|-------------------|---------------|-------------------|---------------|
| 1                 | 10/11 -> 91%  | 9/10 -> 90%       | 9/10 -> 90%   | 

The surviving mutant is a change to conditional boundary in line 22:

```java
while (i >= 0 && s.charAt(i) == ' ') {
```

It is an equivalent mutant and not worth writing additional tests for. With the constraint "There is at least one word in the string.", no observable behavior changes. 
