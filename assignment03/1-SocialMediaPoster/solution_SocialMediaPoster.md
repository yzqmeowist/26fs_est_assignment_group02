# Solution SocialMediaPoster

## A
### Questions
#### What are the external dependencies and which ones should be tested?
The only external dependency is `SocialMediaAPI`. It simulates an external API call 
and should thus be mocked.

#### Does the code need to be refactored to use mocks?
`SocialMediaPoster` does not need to be refactored, since `SocialMediaAPI` is already 
injected in the constructor. This makes testing a lot easier.

#### What are the disadvantages of using mocks in the tests implemented for this task?
The main disadvantage is that the tests only verify the interaction with the mocked 
`SocialMediaAPI`, not the behavior of a real social media platform. Another disadvantage 
is that the tests can become coupled to implementation details. For example, 
`verify(api).post("Twitter", "Hello")` assumes that `postContent` delegates directly to 
`api.post()`. If the implementation later changes internally, the test might fail even 
if the externally visible behavior is still correct.


## B
### TDD
#### TDD Step 1: Invalid platform list

Tests written: `rejectsNullPlatformList`, `rejectsEmptyPlatformList`

Expected behavior: A null or empty platform list is invalid, so `postBatch` throws 
`IllegalArgumentException` and does not call the API.

Code change: Added validation for `platforms == null` and `platforms.isEmpty()`.

#### TDD Step 2: Null, empty, and blank content

Tests written: `rejectsNullBatchContent`, `rejectsEmptyBatchContent`, `rejectsBlankBatchContent`

Expected behavior: Null, empty, and blank content are invalid, so `postBatch` throws 
`IllegalArgumentException` and does not call the API.

Code change: Added validation for `content == null` and `content.trim().isEmpty()`.

#### TDD Step 3: Content too long

Test written: `rejectsLongBatchContent`

Expected behavior: Content longer than 280 characters is invalid, so `postBatch` throws 
`IllegalArgumentException` and does not call the API.

Code change: Added validation for `content.length() > 280`.

#### TDD Step 4: Rate limit is zero

Test written: `returnsZeroWhenRateLimitIsZero`

Expected behavior: If the rate limit is `0`, no posts are attempted and `postBatch` 
returns `0`.

Code change: Added use of `api.getRateLimitRemaining()` to determine how many platforms 
may be posted to.

#### TDD Step 5: One successful post

Test written: `returnsOneForSuccessfulPost`

Expected behavior: If one platform is within the rate limit and the API succeeds, 
`postBatch` returns `1`.

Code change: Added a call to `api.post()` inside the loop and incremented the success counter 
when the API returns `true`.

#### TDD Step 6: One failed post

Test written: `returnsZeroForFailedPost`

Expected behavior: If one platform is within the rate limit but the API fails, `postBatch` returns 
`0`.

Code change: No code change was needed because the previous success-counting logic already only 
increments the counter when `api.post()` returns `true`.

#### TDD Step 7: Multiple posts with mixed results

Test written: `countsOnlySuccessfulPosts`

Expected behavior: When multiple platforms are within the rate limit, `postBatch` attempts each 
post and returns only the number of successful API calls.

Code change: No code change was needed because the loop already counts only successful posts.

#### TDD Step 8: More platforms than rate limit

Test written: `onlyPostsUpToRateLimit`

Expected behavior: If more platforms are given than the remaining rate limit allows, only the 
first platforms within the limit are posted.

Code change: No code change was needed because the loop already uses `Math.min(platforms.size(), 
api.getRateLimitRemaining())`.

#### TDD Step 9: Platform count at and below rate limit

Tests written: `postsAllWhenCountEqualsLimit`,
`postsAllWhenCountBelowLimit`

Expected behavior: If the number of platforms is equal to or below the rate limit, all platforms 
are posted.

Code change: No code change was needed because the existing `Math.min(platforms.size(), 
api.getRateLimitRemaining())` logic already handled these cases.

#### TDD Step 10: Invalid platform inside list

Tests written: `rejectsNullPlatformInList`, `rejectsEmptyPlatformInList`,
`rejectsBlankPlatformInList`

Expected behavior: If any platform in the list is null, empty, or blank, `postBatch` throws 
`IllegalArgumentException`.

Code change: Added validation for each platform before posting.

#### TDD Step 11: Content exactly 280 characters

Test written: `acceptsBatchContentWith280Characters`

Expected behavior: Content with exactly 280 characters is valid and should be posted.

Code change: No code change was needed because the existing check only rejects content longer 
than 280 characters.

### Questions

#### What are the external dependencies and which ones should be tested?
The external dependency is still `SocialMediaAPI`. It should be mocked because `postBatch` uses 
it to check the rate limit and to post the content.

#### Does the code need to be refactored to use mocks?
No, no refactoring was needed. The API is already passed through the constructor.

#### What are the disadvantages of using mocks in the tests implemented for this task?
The tests only check if our code talks to the mocked API correctly. They do not prove that a real 
social media API would behave the same way. Also, some tests are tied to implementation details, 
for example that `postBatch` calls `getRateLimitRemaining()` and only then calls `post()`.