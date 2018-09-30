package leetcode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import leetcode.MaxProdSubArray152;

class MaxProdSubArray152Test {

	@Test
	void testmaxProduct() {
		assertEquals(6,MaxProdSubArray152.maxProduct(new int[] {2,3,-2,4}));
		assertEquals(0,MaxProdSubArray152.maxProduct(new int[] {-2,0,-1}));
		assertEquals(4,MaxProdSubArray152.maxProduct(new int[] {-2,4,-3}));
	}
	
	@Test
	void testmaxProductDP() {
		assertEquals(6,MaxProdSubArray152.maxProductDP(new int[] {2,3,-2,4}));
		assertEquals(0,MaxProdSubArray152.maxProductDP(new int[] {-2,0,-1}));
	}

}
