import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SocialMediaPosterTest {

    private SocialMediaAPI api;
    private SocialMediaPoster poster;

    @BeforeEach
    void setUp() {
        api = mock(SocialMediaAPI.class);
        poster = new SocialMediaPoster(api);
    }

    @Test
    void postContentReturnsTrueWhenApiSucceeds() {
        when(api.post("Twitter", "Hello")).thenReturn(true);

        boolean result = poster.postContent("Twitter", "Hello");

        assertTrue(result);
        verify(api).post("Twitter", "Hello");
    }

    @Test
    void postContentReturnsFalseWhenApiFails() {
        when(api.post("Twitter", "Hello")).thenReturn(false);

        boolean result = poster.postContent("Twitter", "Hello");

        assertFalse(result);
        verify(api).post("Twitter", "Hello");
    }

    @Test
    void postContentRejectsNullPlatform() {
        assertThrows(IllegalArgumentException.class,
                () -> poster.postContent(null, "Hello"));

        verifyNoInteractions(api);
    }

    @Test
    void postContentRejectsEmptyPlatform() {
        assertThrows(IllegalArgumentException.class,
                () -> poster.postContent("", "Hello"));

        verifyNoInteractions(api);
    }

    @Test
    void postContentRejectsBlankPlatform() {
        assertThrows(IllegalArgumentException.class,
                () -> poster.postContent("   ", "Hello"));

        verifyNoInteractions(api);
    }

    @Test
    void postContentRejectsNullContent() {
        assertThrows(IllegalArgumentException.class,
                () -> poster.postContent("Twitter", null));

        verifyNoInteractions(api);
    }

    @Test
    void postContentRejectsEmptyContent() {
        assertThrows(IllegalArgumentException.class,
                () -> poster.postContent("Twitter", ""));

        verifyNoInteractions(api);
    }

    @Test
    void postContentRejectsBlankContent() {
        assertThrows(IllegalArgumentException.class,
                () -> poster.postContent("Twitter", "   "));

        verifyNoInteractions(api);
    }

    @Test
    void postContentAcceptsContentWithExactly280Characters() {
        String content = "a".repeat(280);
        when(api.post("Twitter", content)).thenReturn(true);

        boolean result = poster.postContent("Twitter", content);

        assertTrue(result);
        verify(api).post("Twitter", content);
    }

    @Test
    void postContentRejectsContentLongerThan280Characters() {
        String content = "a".repeat(281);

        assertThrows(IllegalArgumentException.class,
                () -> poster.postContent("Twitter", content));

        verifyNoInteractions(api);
    }


    // postBatch tests
    @Test
    void postBatchRejectsEmptyPlatformList() {
        assertThrows(IllegalArgumentException.class,
                () -> poster.postBatch(List.of(), "Hello"));

        verifyNoInteractions(api);
    }

    @Test
    void postBatchRejectsNullPlatformList() {
        assertThrows(IllegalArgumentException.class,
                () -> poster.postBatch(null, "Hello"));

        verifyNoInteractions(api);
    }

    @Test
    void postBatchRejectsNullContent() {
        assertThrows(IllegalArgumentException.class,
                () -> poster.postBatch(List.of("Twitter"), null));

        verifyNoInteractions(api);
    }

    @Test
    void postBatchRejectsEmptyContent() {
        assertThrows(IllegalArgumentException.class,
                () -> poster.postBatch(List.of("Twitter"), ""));

        verifyNoInteractions(api);
    }

    @Test
    void postBatchRejectsBlankContent() {
        assertThrows(IllegalArgumentException.class,
                () -> poster.postBatch(List.of("Twitter"), "   "));

        verifyNoInteractions(api);
    }

    @Test
    void postBatchRejectsContentLongerThan280Characters() {
        String content = "a".repeat(281);

        assertThrows(IllegalArgumentException.class,
                () -> poster.postBatch(List.of("Twitter"), content));

        verifyNoInteractions(api);
    }

    @Test
    void postBatchReturnsZeroWhenRateLimitIsZero() {
        when(api.getRateLimitRemaining()).thenReturn(0);

        int result = poster.postBatch(List.of("Twitter"), "Hello");

        assertEquals(0, result);
        verify(api, never()).post(anyString(), anyString());
    }

    @Test
    void postBatchReturnsOneForOneSuccessfulPost() {
        when(api.getRateLimitRemaining()).thenReturn(1);
        when(api.post("Twitter", "Hello")).thenReturn(true);

        int result = poster.postBatch(List.of("Twitter"), "Hello");

        assertEquals(1, result);
        verify(api).post("Twitter", "Hello");
    }

    @Test
    void postBatchReturnsZeroForOneFailedPost() {
        when(api.getRateLimitRemaining()).thenReturn(1);
        when(api.post("Twitter", "Hello")).thenReturn(false);

        int result = poster.postBatch(List.of("Twitter"), "Hello");

        assertEquals(0, result);
        verify(api).post("Twitter", "Hello");
    }

    @Test
    void postBatchCountsOnlySuccessfulPosts() {
        when(api.getRateLimitRemaining()).thenReturn(3);
        when(api.post("Twitter", "Hello")).thenReturn(true);
        when(api.post("Facebook", "Hello")).thenReturn(false);
        when(api.post("Instagram", "Hello")).thenReturn(true);

        int result = poster.postBatch(
                List.of("Twitter", "Facebook", "Instagram"),
                "Hello"
        );

        assertEquals(2, result);
        verify(api).post("Twitter", "Hello");
        verify(api).post("Facebook", "Hello");
        verify(api).post("Instagram", "Hello");
    }

    @Test
    void postBatchOnlyPostsUpToRateLimit() {
        when(api.getRateLimitRemaining()).thenReturn(2);
        when(api.post("Twitter", "Hello")).thenReturn(true);
        when(api.post("Facebook", "Hello")).thenReturn(true);

        int result = poster.postBatch(
                List.of("Twitter", "Facebook", "Instagram"),
                "Hello"
        );

        assertEquals(2, result);
        verify(api).post("Twitter", "Hello");
        verify(api).post("Facebook", "Hello");
        verify(api, never()).post("Instagram", "Hello");
    }

    @Test
    void postBatchPostsAllPlatformsWhenPlatformCountEqualsRateLimit() {
        when(api.getRateLimitRemaining()).thenReturn(2);
        when(api.post("Twitter", "Hello")).thenReturn(true);
        when(api.post("Facebook", "Hello")).thenReturn(true);

        int result = poster.postBatch(List.of("Twitter", "Facebook"), "Hello");

        assertEquals(2, result);
        verify(api).post("Twitter", "Hello");
        verify(api).post("Facebook", "Hello");
    }

    @Test
    void postBatchPostsAllPlatformsWhenPlatformCountIsBelowRateLimit() {
        when(api.getRateLimitRemaining()).thenReturn(3);
        when(api.post("Twitter", "Hello")).thenReturn(true);
        when(api.post("Facebook", "Hello")).thenReturn(true);

        int result = poster.postBatch(List.of("Twitter", "Facebook"), "Hello");

        assertEquals(2, result);
        verify(api).post("Twitter", "Hello");
        verify(api).post("Facebook", "Hello");
    }

    @Test
    void postBatchRejectsNullPlatformInList() {
        List<String> platforms = new ArrayList<>();
        platforms.add("Twitter");
        platforms.add(null);

        assertThrows(IllegalArgumentException.class,
                () -> poster.postBatch(platforms, "Hello"));

        verifyNoInteractions(api);
    }

    @Test
    void postBatchRejectsEmptyPlatformInList() {
        assertThrows(IllegalArgumentException.class,
                () -> poster.postBatch(List.of("Twitter", ""), "Hello"));

        verifyNoInteractions(api);
    }

    @Test
    void postBatchRejectsBlankPlatformInList() {
        assertThrows(IllegalArgumentException.class,
                () -> poster.postBatch(List.of("Twitter", "   "), "Hello"));

        verifyNoInteractions(api);
    }

    @Test
    void postBatchAcceptsContentWithExactly280Characters() {
        String content = "a".repeat(280);
        when(api.getRateLimitRemaining()).thenReturn(1);
        when(api.post("Twitter", content)).thenReturn(true);

        int result = poster.postBatch(List.of("Twitter"), content);

        assertEquals(1, result);
        verify(api).post("Twitter", content);
    }


}