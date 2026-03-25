## Solution CompareVisionNumbers

**Step 1**: Change pom file to be able to run tests.

**Step 2**: Make specification based Tests according to the README file.

Test if all ouputs are correct, if it handles different length of string comparison, max and min allowed ranbge for length and value in the allowed range and in the not allowed range

**Failed tests**: Logic that returns the number is inverted.Different representation of same version, Max int testing, negative numbers, as well as no error in to long numbers.

**JaCoCO** Missed 13 of 119 Instructions (89%) and 3 of 26 Branches(88%).

Missed the Class name as but not important and negative numbers. Added two negative number tests and  the forgotten different length test for the 100%.

**PItest**
Line coverge 97%, Mutation Coverage 92% 

Mutation uncovered an test that was ment for onpoint max test was onsided so there needed one Test to be made and in line 33 if it is changed from < to <= it runs normally but makes no difference as if it goes one to long it replaces the new place with 0 and compares 0 with 0 so no change and no new tests needed

End Mutation Coverage 96%