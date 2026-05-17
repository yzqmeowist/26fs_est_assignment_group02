# Solution NewsAggregator

## Unit tests

### Cache hit

Test written: `returnsCachedArticlesOnCacheHit`

Expected behavior: If the cache contains articles for the category, 
`getLatestNews` returns the cached articles and does not call the API.

### Cache miss

Test written: `fetchesFromApiOnCacheMiss`

Expected behavior: If the cache has no articles for the category, 
`getLatestNews` fetches fresh articles from the API, stores them in the 
cache, and returns them.

### API failure on cache miss

Test written: `throwsErrorWhenApiFails`

Expected behavior: If the cache misses and the API fails, 
`getLatestNews` throws the error and does not cache anything.

### Invalid category

Tests written: `rejectsNullCategory`, `rejectsEmptyCategory`, 
`rejectsBlankCategory`

Expected behavior: A null, empty, or blank category is invalid. 
`getLatestNews` throws `IllegalArgumentException` and does not 
call the cache or API.

### API returns no articles

Test written: `cachesEmptyApiResult`

Expected behavior: If the cache misses and the API returns an 
empty list, `getLatestNews` returns the empty list and caches it.

### API unavailable

Test written: `throwsErrorWhenApiUnavailable`

Expected behavior: If the cache misses and the API is unavailable, 
`getLatestNews` throws an error and does not call `fetchNews`.

Code change: Added a check for `newsAPI.isAvailable()` before 
fetching fresh articles.

### Empty cache

Test written: `fetchesFromApiOnEmptyCache`

Expected behavior: If the cache returns an empty list, it is 
treated like a cache miss. Fresh articles are fetched from the 
API and cached.

### Different category

Test written: `fetchesTechnologyNewsOnCacheMiss`

Expected behavior: The requested category is passed to the API 
and cache. In this case, Technology articles are fetched and cached.