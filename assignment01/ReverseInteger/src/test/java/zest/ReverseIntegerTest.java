package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReverseIntegerTest {
    @Test
    public void testZero() {
        int input = 0;
        int expected = 0;

        assertEquals(expected, ReverseInteger.reverse(input));
    }

    @Test
    public void testPositive() {
        int input = 123;
        int expected = 321;

        assertEquals(expected, ReverseInteger.reverse(input));
    }

    @Test
    public void testNegative() {
        int input = -123;
        int expected = -321;

        assertEquals(expected, ReverseInteger.reverse(input));
    }

    @Test
    public void testEndingInZero() {
        int input = 120;
        int expected = 21;

        assertEquals(expected, ReverseInteger.reverse(input));
    }

    @Test
    public void testSingleDigit() {
        int input = 8;
        int expected = 8;

        assertEquals(expected, ReverseInteger.reverse(input));
    }

    @Test
    public void testMaximum() {
        int input = 2147483647;
        int expected = 0;

        assertEquals(expected, ReverseInteger.reverse(input));
    }

    @Test
    public void testMinimum() {
        int input = -2147483648;
        int expected = 0;

        assertEquals(expected, ReverseInteger.reverse(input));
    }

    @Test
    public void testMaxReversible() {
        int input = 1463847412;
        int expected = 2147483641;

        assertEquals(expected, ReverseInteger.reverse(input));
    }

    @Test
    public void testMinReversible() {
        int input = -1463847412;
        int expected = -2147483641;

        assertEquals(expected, ReverseInteger.reverse(input));
    }

    @Test
    public void testOverflowExample() {
        int input = 1534236469;
        int expected = 0;

        assertEquals(expected, ReverseInteger.reverse(input));
    }

    @Test
    public void testPositiveOverflowBoundary() {
        int input = 1563847412;
        int expected = 0;

        assertEquals(expected, ReverseInteger.reverse(input));
    }

    @Test
    public void testNegativeOverflowBoundary() {
        int input = -1563847412;
        int expected = 0;

        assertEquals(expected, ReverseInteger.reverse(input));
    }


}