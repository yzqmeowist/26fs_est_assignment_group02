package zest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlusOneTest {

    @Test
    public void testSingleElementMinimum() {
        int[] input = {0};
        int[] expected = {1};

        assertArrayEquals(expected, PlusOne.plusOne(input));
    }

    @Test
    public void testSingleNine() {
        int[] input = {9};
        int[] expected = {1, 0};

        assertArrayEquals(expected, PlusOne.plusOne(input));
    }

    @Test
    public void testNoCarry() {
        int[] input = {1, 2, 3};
        int[] expected = {1, 2, 4};

        assertArrayEquals(expected, PlusOne.plusOne(input));
    }

    @Test
    public void testCarryOnLastDigit() {
        int[] input = {1, 2, 9};
        int[] expected = {1, 3, 0};

        assertArrayEquals(expected, PlusOne.plusOne(input));
    }

    @Test
    public void testCarryPropagate() {
        int[] input = {1, 9, 9};
        int[] expected = {2, 0, 0};

        assertArrayEquals(expected, PlusOne.plusOne(input));
    }

    @Test
    public void testZeroInsideNumber() {
        int[] input = {1, 0, 9};
        int[] expected = {1, 1, 0};

        assertArrayEquals(expected, PlusOne.plusOne(input));
    }

    @Test
    public void testAllNines() {
        int[] input = {9, 9, 9};
        int[] expected = {1, 0, 0, 0};

        assertArrayEquals(expected, PlusOne.plusOne(input));
    }





}