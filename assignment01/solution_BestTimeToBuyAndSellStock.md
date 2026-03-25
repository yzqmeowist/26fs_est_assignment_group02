## Solution BestTimeToBuyAndSellStock

**Step 1**: Change pom.xml file to run Tests

**Step 2**: Write Specification based Tests according to the README file.

Tests for on and off points limits of length and value as well as ascending, desending and multiple peaks.

Findings: decending works not correct ascending and multiple have not minus buy prize. No handling for negative, to long lists and values existing.

Changes: Adding non valid input tests and a min Price 

**JaCoCO**: Missed instructions 3/80 (96%) and Missed Branch coverage 1/18 (94%)

Missed the Classinstruction and None as an imput. After new Test 100% Branch coverage.

**Pltest** : 93% line coverage (class not done), 88% mutation coverage missed 2 one in line 29 the <= mutation and line 33 >= mutation but it should not make any differences in the end product if the bug survives even therefore no more tests needed.
