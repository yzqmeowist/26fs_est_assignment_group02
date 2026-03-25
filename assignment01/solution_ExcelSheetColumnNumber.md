## Solution ExcelSheetColumnNumber

**Step 1**: Program the pom file to alllow running of tests.

**Step 2**: Make specification based tests according to the README file.

Test for min double and max input to test validation

Test with null,empty or illegal arguments should fail as well as to long inputs.

**Findings**: 8 As give no mistake but 7 Zs give and aöways output 0 for A and AA

Changes to code: +1 to end value because A = 1 and 1-1 not equals to 1.

Added check for not to long inputs.

Needed to change int to long as there is no negative nummber possible and ZZZZZZZ is legal but over max int value

**JaCoCo**: 3/60 Missed instructions (95%) and 0/12 Missed Branches (100%). Only missed the Class so it was all done compleatly.

**PITest**  Linecoverage 11/12 (92%) only class missed and 15/15 (100%) mutation coverage so no additional tests needed.