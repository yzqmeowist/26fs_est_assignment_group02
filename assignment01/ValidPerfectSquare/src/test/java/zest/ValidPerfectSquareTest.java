package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ValidPerfectSquareTest {

    @Test
    public void testLowerBoundary() {
        assertTrue(ValidPerfectSquare.isPerfectSquare(1));
    }

    @Test
    public void testSmallPerfectSquares() {
        assertTrue(ValidPerfectSquare.isPerfectSquare(4));
        assertTrue(ValidPerfectSquare.isPerfectSquare(9));
        assertTrue(ValidPerfectSquare.isPerfectSquare(16));
    }

    @Test
    public void testSmallNonSquares() {
        assertFalse(ValidPerfectSquare.isPerfectSquare(2));
        assertFalse(ValidPerfectSquare.isPerfectSquare(3));
        assertFalse(ValidPerfectSquare.isPerfectSquare(5));
    }

    @Test
    public void testAroundSquares() {
        assertFalse(ValidPerfectSquare.isPerfectSquare(8));
        assertTrue(ValidPerfectSquare.isPerfectSquare(9));
        assertFalse(ValidPerfectSquare.isPerfectSquare(10));
    }

    @Test
    public void testLargePerfectSquare() {
        assertTrue(ValidPerfectSquare.isPerfectSquare(100));
        assertTrue(ValidPerfectSquare.isPerfectSquare(144));
        assertTrue(ValidPerfectSquare.isPerfectSquare(1024));
    }

    @Test
    public void testLargeNonPerfectSquare() {
        assertFalse(ValidPerfectSquare.isPerfectSquare(99));
        assertFalse(ValidPerfectSquare.isPerfectSquare(145));
        assertFalse(ValidPerfectSquare.isPerfectSquare(1023));
    }

    @Test
    public void testLargestPerfectSquare() {
        assertTrue(ValidPerfectSquare.isPerfectSquare(2147395600));
    }

    @Test
    public void testVeryLargeNonPerfectSquare() {
        assertFalse(ValidPerfectSquare.isPerfectSquare(2147483647));
    }

}