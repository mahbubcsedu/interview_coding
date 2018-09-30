package leetcode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import leetcode.PalindromPairs336;

class PalindromPairs336Test {

	@Test
	void testIsPalindrom() {
		PalindromPairs336 n=new PalindromPairs336();
		assertEquals(true,n.isPalindrom("bazzbbzz",2,7));
	}
	
	@Test
	void testGetPalindrom() {
		String[] d=new String[] {"a","ba","aaa"};
        PalindromPairs336 n=new PalindromPairs336();
        
       /* Object[] res=new ArrayList<>();
        res.add(Arrays.asList(0,1));
        res.add(Arrays.asList(0,3));


        assertEquals(Arrays.asList(Arrays.deepToString(res),n.getPalindromPairs(d));
	*/}

}
