# Solution InventoryManagement

## A
### Questions

#### What are the external dependencies and which ones should be tested?
The external dependency is `InventoryDatabaseConnector`. It should be mocked because it represents 
access to the database.

#### Does the code need to be refactored to use mocks?
No refactoring was needed. `InventoryDatabaseConnector` is already injected through the constructor.

#### What are the disadvantages of using mocks in the tests implemented for this task?
The tests check how `InventoryManager` interacts with the mocked database connector, not with a real 
database. For example, the mock can return any product list we define, but this does not prove that 
the real database connector works correctly.


## B
### TDD
#### TDD Step 1: Invalid category

Tests written: `getProductsByCategoryRejectsNullCategory`, `getProductsByCategoryRejectsEmptyCategory`, 
`getProductsByCategoryRejectsBlankCategory`

Expected behavior: A null, empty, or blank category is invalid, so `getProductsByCategory` throws 
`IllegalArgumentException` and does not call the database connector.

Code change: Added validation for `category == null` and `category.trim().isEmpty()`.

#### TDD Step 2: One matching category

Test written: `getProductsByCategoryReturnsProductsFromDatabase`

Expected behavior: For a valid category, `getProductsByCategory` returns the products provided 
by the database connector and closes the connector.

Code change: Added a call to `databaseConnector.getProductsByCategory(category)` and returned 
its result.

#### TDD Step 3: Database call fails

Test written: `getProductsByCategoryClosesDatabaseWhenQueryFails`

Expected behavior: If the database query fails, the exception is propagated, but the database 
connector is still closed.

Code change: No code change was needed because the method already uses `finally` to close the 
connector.

#### TDD Step 4: Valid category with no products

Test written: `getProductsByCategoryReturnsEmptyListWhenDatabaseReturnsNoProducts`

Expected behavior: If the database connector returns an empty list for a valid category, 
`getProductsByCategory` also returns an empty list.

Code change: No code change was needed because the method already returns the database 
connector result.

#### TDD Step 5: Uses requested category

Test written: `getProductsByCategoryUsesRequestedCategory`

Expected behavior: `getProductsByCategory` passes the requested category to the database 
connector.

Code change: No code change was needed because the method already forwards the category 
parameter.

### Questions

#### What are the external dependencies and which ones should be tested?
The external dependency is still `InventoryDatabaseConnector`. It should be mocked because 
`getProductsByCategory` uses it to query the database.

#### Does the code need to be refactored to use mocks?
No, no refactoring was needed. The database connector is already passed through the constructor.

#### What are the disadvantages of using mocks in the tests implemented for this task?
The tests only check if `InventoryManager` talks to the mocked database connector correctly. 
They do not prove that a real database query would work. Also, some tests are tied to 
implementation details, for example that `getProductsByCategory` calls `getProductsByCategory()` 
on the connector and then closes it.