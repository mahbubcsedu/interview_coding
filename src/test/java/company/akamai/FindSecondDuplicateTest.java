package company.akamai;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import company.akamai.FindSecondDuplicate;

class FindSecondDuplicateTest {

	@Test
	void testGetSecondDuplicate() {
		FindSecondDuplicate s=new FindSecondDuplicate();
		//create few testcase first, then go for algorithm
		/**
		 * we need to ask the question what would be the second, in sorting order or in curent order
		 * if in current order, we cannot use sorting technique at all
		 */
		assertEquals(3,s.getSecondDuplicate(new int[] {2,3,5,7,1,8,1,4,10,3}));
		//assertEquals(3,s.getSecondDuplicate(new int[] {2,3,5,7,1,8,1,4,10,3}));
		assertEquals(-1,s.getSecondDuplicate(new int[] {2,3,5,7,3,8,1,4,10}));
		
	}
	
	@Test
	void testGetSecondDuplicate2() {
		FindSecondDuplicate s=new FindSecondDuplicate();
		//create few testcase first, then go for algorithm
		/**
		 * we need to ask the question what would be the second, in sorting order or in curent order
		 * if in current order, we cannot use sorting technique at all
		 * the cost O(n) and space also O(n)
		 */
		assertEquals(3,s.getSecondDuplicate(new int[] {2,3,5,7,1,8,1,4,10,3}));
		//assertEquals(3,s.getSecondDuplicate(new int[] {2,3,5,7,1,8,1,4,10,3}));
		assertEquals(-1,s.getSecondDuplicate(new int[] {2,3,5,7,3,8,1,4,10}));
		
	}

}
