## Solution AddBinary
**Step 1**: Change pom.xml file to adapt to needs.

**Step 2**: Make specification Based Tests according to the README file.

**Step 3**: Run the tests and look at the programm to fix the test and adding more test to test on,off points

Tests done:

a and b legal, carry works, diffentet length, long argument

fails inputs as null strings, empty strings, non binary numbers, leading zeros

**Findings**: non correct addition of 1+1 = 10, empty string throws no error, non binary makes no error, leading zero has no error. While looking at code seeing no max length check is implemented as stated in README.

**Corrections**: adding empty string comparison. if there is a carry in the end add a 0 to the end because the number gets one longer. Added loop throu a and b that throws error if there is somthing other than 0 and 1. Test added for leading 0 os for a and b to throw errors.

**JaCoCo**: Misssed Instructions are 8 of 178(95%) and Missed Branches are 2 of 42 (95%)

Added 2 tests that have more than 10000 numbers one for a and b

Now final evaluation 100% branch coverage and 97% Line coverage (missed class declaration)

**Pltest**: 97% linecoverage( class declaration) 33/35 Mutation Coverage (94%).

Both mutations survived line 18 with >= instead of the > since there is no on point test only off point test.

There for added an on Point test. After this 100% Mutant coverage.

It is usefull to have all on points tested as even if it is rare to see that big inputs it is important to clearly test them, but it would have been save even without the additional Tests.