package zest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class MinCostClimbingStairsTest {

	private final MinCostClimbingStairs solver = new MinCostClimbingStairs();

	@Test
	void example1_shouldReturn15() {
		assertEquals(15, solver.minCostClimbingStairs(new int[] {10, 15, 20}));
	}

	@Test
	void example2_shouldReturn6() {
		assertEquals(6, solver.minCostClimbingStairs(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
	}
    
    @Test
	void allZeroCosts_shouldReturn0() {
		assertEquals(0, solver.minCostClimbingStairs(new int[] {0, 0, 0, 0, 0}));
	}

	@Test
	void twoSteps_shouldChooseCheapestStart() {
		assertEquals(5, solver.minCostClimbingStairs(new int[] {5, 7}));
	}

   @Test
    void is_to_short() {
        assertThrows(IllegalArgumentException.class, () -> solver.minCostClimbingStairs(null));
        assertThrows(IllegalArgumentException.class, () -> solver.minCostClimbingStairs(new int[] {}));
        assertThrows(IllegalArgumentException.class, () -> solver.minCostClimbingStairs(new int[] {5}));
    }

	@Test
	void maxAndOverMaxLength_withAllOnes() {
		int[] cost1000 = new int[1000];
		Arrays.fill(cost1000, 1);
		assertEquals(500, solver.minCostClimbingStairs(cost1000));

		int[] cost1001 = new int[1001];
		Arrays.fill(cost1001, 1);
		assertThrows(IllegalArgumentException.class, () -> solver.minCostClimbingStairs(cost1001));
	}
    
    @Test
    void illegalCostValues() {
        assertEquals(1, solver.minCostClimbingStairs(new int[] {1, 999}));
        assertThrows(IllegalArgumentException.class, () -> solver.minCostClimbingStairs(new int[] {10, -5, 20}));
        assertThrows(IllegalArgumentException.class, () -> solver.minCostClimbingStairs(new int[] {10, 15, 1000}));
    }


}
