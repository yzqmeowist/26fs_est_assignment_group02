import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NewsAggregatorTest {

    private NewsAPI newsAPI;
    private ContentCache contentCache;
    private NewsAggregator newsAggregator;

    @BeforeEach
    void setUp() {
        newsAPI = mock(NewsAPI.class);
        contentCache = mock(ContentCache.class);
        newsAggregator = new NewsAggregator(newsAPI, contentCache);
    }

    @Test
    void returnsCachedArticlesOnCacheHit() {
        NewsArticle article = new NewsArticle(
                "Title",
                "Content",
                "BBC",
                "Sports",
                123L
        );

        when(contentCache.getCachedArticles("Sports"))
                .thenReturn(List.of(article));

        List<NewsArticle> result = newsAggregator.getLatestNews("Sports");

        assertEquals(List.of(article), result);
        verify(contentCache).getCachedArticles("Sports");
        verifyNoInteractions(newsAPI);
    }

    @Test
    void fetchesFromApiOnCacheMiss() {
        NewsArticle article = new NewsArticle(
                "Title",
                "Content",
                "BBC",
                "Sports",
                123L
        );

        when(contentCache.getCachedArticles("Sports")).thenReturn(null);
        when(newsAPI.fetchNews("Sports", 10)).thenReturn(List.of(article));
        when(newsAPI.isAvailable()).thenReturn(true);

        List<NewsArticle> result = newsAggregator.getLatestNews("Sports");

        assertEquals(List.of(article), result);
        verify(contentCache).getCachedArticles("Sports");
        verify(newsAPI).fetchNews("Sports", 10);
        verify(contentCache).cacheArticles("Sports", List.of(article));
    }

    @Test
    void throwsErrorWhenApiFails() {
        when(contentCache.getCachedArticles("Sports")).thenReturn(null);
        when(newsAPI.fetchNews("Sports", 10))
                .thenThrow(new RuntimeException("API unavailable"));
        when(newsAPI.isAvailable()).thenReturn(true);

        assertThrows(RuntimeException.class,
                () -> newsAggregator.getLatestNews("Sports"));

        verify(contentCache).getCachedArticles("Sports");
        verify(newsAPI).fetchNews("Sports", 10);
        verify(contentCache, never()).cacheArticles(anyString(), anyList());
    }

    @Test
    void rejectsNullCategory() {
        assertThrows(IllegalArgumentException.class,
                () -> newsAggregator.getLatestNews(null));

        verifyNoInteractions(contentCache);
        verifyNoInteractions(newsAPI);
    }

    @Test
    void rejectsEmptyCategory() {
        assertThrows(IllegalArgumentException.class,
                () -> newsAggregator.getLatestNews(""));

        verifyNoInteractions(contentCache);
        verifyNoInteractions(newsAPI);
    }

    @Test
    void rejectsBlankCategory() {
        assertThrows(IllegalArgumentException.class,
                () -> newsAggregator.getLatestNews("   "));

        verifyNoInteractions(contentCache);
        verifyNoInteractions(newsAPI);
    }

    @Test
    void cachesEmptyApiResult() {
        when(contentCache.getCachedArticles("Sports")).thenReturn(null);
        when(newsAPI.fetchNews("Sports", 10)).thenReturn(List.of());
        when(newsAPI.isAvailable()).thenReturn(true);

        List<NewsArticle> result = newsAggregator.getLatestNews("Sports");

        assertTrue(result.isEmpty());
        verify(newsAPI).fetchNews("Sports", 10);
        verify(contentCache).cacheArticles("Sports", List.of());
    }

    @Test
    void throwsErrorWhenApiUnavailable() {
        when(contentCache.getCachedArticles("Sports")).thenReturn(null);
        when(newsAPI.isAvailable()).thenReturn(false);

        assertThrows(IllegalStateException.class,
                () -> newsAggregator.getLatestNews("Sports"));

        verify(contentCache).getCachedArticles("Sports");
        verify(newsAPI).isAvailable();
        verify(newsAPI, never()).fetchNews(anyString(), anyInt());
        verify(contentCache, never()).cacheArticles(anyString(), anyList());
    }

    @Test
    void fetchesFromApiOnEmptyCache() {
        NewsArticle article = new NewsArticle("Title", "Content", "BBC", "Sports", 123L);

        when(contentCache.getCachedArticles("Sports")).thenReturn(List.of());
        when(newsAPI.isAvailable()).thenReturn(true);
        when(newsAPI.fetchNews("Sports", 10)).thenReturn(List.of(article));

        List<NewsArticle> result = newsAggregator.getLatestNews("Sports");

        assertEquals(List.of(article), result);
        verify(contentCache).getCachedArticles("Sports");
        verify(newsAPI).isAvailable();
        verify(newsAPI).fetchNews("Sports", 10);
        verify(contentCache).cacheArticles("Sports", List.of(article));
    }

    @Test
    void fetchesTechnologyNewsOnCacheMiss() {
        NewsArticle article = new NewsArticle(
                "Tech Title",
                "Tech Content",
                "TechCrunch",
                "Technology",
                123L
        );

        when(contentCache.getCachedArticles("Technology")).thenReturn(null);
        when(newsAPI.isAvailable()).thenReturn(true);
        when(newsAPI.fetchNews("Technology", 10)).thenReturn(List.of(article));

        List<NewsArticle> result = newsAggregator.getLatestNews("Technology");

        assertEquals(List.of(article), result);
        verify(contentCache).getCachedArticles("Technology");
        verify(newsAPI).fetchNews("Technology", 10);
        verify(contentCache).cacheArticles("Technology", List.of(article));
    }
}