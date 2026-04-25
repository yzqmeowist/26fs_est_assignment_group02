package zest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FindAllDuplicatesInArrayTest {

	private final FindAllDuplicatesInArray solver = new FindAllDuplicatesInArray();

	@Test
	void example1_shouldReturnTwoAndThree() {
		List<Integer> actual = solver.findDuplicates(new int[] {4, 3, 2, 7, 8, 2, 3, 1});

		assertEquals(List.of(2, 3), actual);
	}

	@Test
	void example2_shouldReturnOne() {
		List<Integer> actual = solver.findDuplicates(new int[] {1, 1, 2});

		assertEquals(List.of(1), actual);
	}

	@Test
	void example3_shouldReturnEmptyList() {
		List<Integer> actual = solver.findDuplicates(new int[] {1});

		assertEquals(List.of(), actual);
	}

	@Test
	void validInput_shouldAllowSingleAndDuplicateValues() {
		List<Integer> actual = solver.findDuplicates(new int[] {2, 5, 1, 2, 5, 3, 4});

		assertEquals(List.of(2, 5), actual);
	}

	@Test
	void nullInput_shouldFailPrecondition() {
		assertThrows(IllegalArgumentException.class, () -> solver.findDuplicates(null));
	}

	@Test
	void emptyArray_shouldFailLengthPrecondition() {
		assertThrows(IllegalArgumentException.class, () -> solver.findDuplicates(new int[] {}));

		int[] tooLong = new int[100001];
		for (int i = 0; i < tooLong.length; i++) {
            if(i == 0 || i == 1) {
                tooLong[i] = 1; // valid value but length is too long not below 1
            } else {
			tooLong[i] = i-1; // valid values but length is too long
		    }
        }
		assertThrows(IllegalArgumentException.class, () -> solver.findDuplicates(tooLong));
	}

	@Test
	void valueOutsideRange_shouldFailPrecondition() {
		assertThrows(IllegalArgumentException.class, () -> solver.findDuplicates(new int[] {1, 0}));
		assertThrows(IllegalArgumentException.class, () -> solver.findDuplicates(new int[] {1, 4, 2}));
	}

	@Test
	void valueAppearingMoreThanTwice_shouldFailPrecondition() {
		assertThrows(IllegalArgumentException.class, () -> solver.findDuplicates(new int[] {1, 1, 1}));
	}

	@Test
	void maximumSizeValidInput_shouldReturnAllDuplicatesInOrder() {
		int[] nums = new int[100000];
		for (int i = 0; i < 50000; i++) {
			nums[i] = i + 1;
			nums[i + 50000] = i + 1;
		}

		List<Integer> actual = solver.findDuplicates(nums);

		assertEquals(50000, actual.size());
		assertEquals(1, actual.get(0));
		assertEquals(50000, actual.get(49999));
	}

}
