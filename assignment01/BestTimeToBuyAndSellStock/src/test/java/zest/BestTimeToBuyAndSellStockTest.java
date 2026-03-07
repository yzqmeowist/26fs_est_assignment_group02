package zest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BestTimeToBuyAndSellStockTest {

    @ParameterizedTest
    @MethodSource("ValidTests")
    public void testValid(int[] prices, int expected) {
        assertEquals(expected, BestTimeToBuyAndSellStock.maxProfit(prices));
    }

    public static Stream<Arguments> ValidTests() {
        return Stream.of(
            Arguments.of(new int[]{0}, 0), // on point value and length
            Arguments.of(new int[100000], 0), // on point length
            Arguments.of(new int[]{10000}, 0), // on point value
            Arguments.of(new int[]{3,2,1}, 0), // Desending
            Arguments.of(new int[]{1,2,3,4}, 3), // Ascending
            Arguments.of(new int[]{1,8,2,9}, 8) // Multiple peaks and valleys

        );
    }

    @ParameterizedTest
    @MethodSource("InvalidTests")
    public void testInvalid(int[] prices) {
        assertThrows(IllegalArgumentException.class,()->BestTimeToBuyAndSellStock.maxProfit(prices));
    }

    public static Stream<Arguments> InvalidTests() {
        return Stream.of(
            Arguments.of(new int[]{}),// Empty array
            Arguments.of(new int[100001]),// To long array
            Arguments.of(new int[]{-1}),// Negative value
            Arguments.of(new int[]{10001}),// Too large value
            Arguments.of((int[]) null) // Null array
        );
    }

}