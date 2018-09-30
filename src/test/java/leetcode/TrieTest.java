package leetcode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import leetcode.ShortestUniquePrefixEpi;

class TrieTest {

	@Test
	void testFindShortestPrefix() {
		Set<String> D=new HashSet<>();
		D.addAll(Arrays.asList(new String[] {"dog","be","cut"}));
		
		assertEquals("ca",ShortestUniquePrefixEpi.findShortestPrefix("cat", D));
		//fail("Not yet implemented");
	}

	@Test
	void testInsert() {
		//fail("Not yet implemented");
	}

}
