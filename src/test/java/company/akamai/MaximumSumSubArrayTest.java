package company.akamai;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import company.akamai.MaximumSumSubArray;

class MaximumSumSubArrayTest {

	@Test
	void testGetMaxSubArraySum() {
		assertEquals(6,MaximumSumSubArray.getMaxSubArraySum(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
	}
	
	@Test
	void testgetMaxSubArraySum2() {
		assertEquals(6,MaximumSumSubArray.getMaxSubArraySum(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
	}

}
