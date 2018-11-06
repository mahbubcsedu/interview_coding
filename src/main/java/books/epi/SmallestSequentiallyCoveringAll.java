package books.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import books.epi.SmallestSubArrayCoveringAllValue.SubArray;

public class SmallestSequentiallyCoveringAll 
{
	static class SubArray
	{
		int startWindow;
		int endWindow;

		SubArray(int s,int e){
			this.startWindow=s;
			this.endWindow=e;
		}

	}


	public static SubArray findSmallestSequentially(List<Integer> paragraph, List<Integer> keyword) {

		/**
		 * we need to keep the sequential order of keywords, so, we will maintain a hashmap item --> index
		 */

		Map<Integer,Integer> keyToIndex=new HashMap<>();
		/**
		 * Another map we will maintain another hashmap where we know the last occurrence of each keyword which maintain sequential length
		 * of that keyword 
		 */
		List<Integer> lastOccurrence=new ArrayList<>();
		/**
		 * Another subarray we will maintain to keep the shortest subarray length which will keep sequential distance of each keyword
		 * so, it starts from the first keyword and then next one from the first and distance of 3rd keyword from the first keywords occurance position
		 */

		List<Integer> shortestLength=new ArrayList<>(keyword.size());

		/**
		 * first we will initialize last occurrences, shortest subarraylength and keywordIndex
		 */

		for(int i=0; i<keyword.size();i++) {
			lastOccurrence.add(-1);//initialize the last occurrence as -1 as 0 could be the first one
			shortestLength.add(Integer.MAX_VALUE);//put infinite to shortest distance
			keyToIndex.put(keyword.get(i),i);//keyword should be stored by their corresponding pos

		}

		//now we need to maintain shortest distance if we have multiple, so we need a variable with initialize infinite
		int shortestDis=Integer.MAX_VALUE;

		SubArray result=new SubArray(-1,-1);

		//we will process one by one item from the list

		for(int i=0; i<paragraph.size();i++) {
			Integer keyWordIdx=keyToIndex.get(paragraph.get(i));
			//if this word/item is not keyword
			if(keyWordIdx!=null) {
				//first check that this is the first keyword, we will have a new starting point
				if(keyWordIdx==0) 
				{   //even if the first keyword appeared again before completing, it will update just its position
					// so later if we already have 2 and now 3 and then next 1, this can handle easily
					//see the first test case
					shortestLength.set(0, 1);// 0th keyword and length ==1
				}else if(shortestLength.get(keyWordIdx-1)!=Integer.MAX_VALUE) {
					//if it is not the first keyword, we will search the previous keywords position and shortest length until that point
					//then we can add from that point to current index to get the shortest distance for current keyword
					int disToPrevKeyword=i-lastOccurrence.get(keyWordIdx-1);
					shortestLength.set(keyWordIdx, disToPrevKeyword+shortestLength.get(keyWordIdx-1));

				}
				//so this will not handle 3 2 3 4  1 for keyword 3 2 1, we will check
				lastOccurrence.set(keyWordIdx, i);//we will set the last occurances of current element to current index


				//we will also check whether we already got on answer, if so, is it the shortest

				if(keyWordIdx == keyword.size()-1 && shortestLength.get(shortestLength.size()-1) < shortestDis) 
				{
					shortestDis=shortestLength.get(shortestLength.size()-1);//update the shortestdis using the last keyword's shortestdistance
					//as this is the length from starting keyword to the current keyword maxdistnce
					result.startWindow=i-shortestLength.get(shortestLength.size()-1)+1; //we will get the starting position by substracting total
					//distance from the current index;
					result.endWindow=i;
				}

			}

		}

		return result;

	}


	public static void test1() {
		Integer[] para= {3,7,2,3,1,3,5,2,0,3,4,1,5,3};
		Integer[] query= {3,2,1};
		//Set<Integer> qset=new HashSet<>();
		//qset.addAll(Arrays.asList(query));
		SubArray res=findSmallestSequentially(Arrays.asList(para),Arrays.asList(query));
		System.out.println(res.startWindow+" "+res.endWindow);
	}

	public static void test2() {
		Integer[] para= {3,7,2,3,1,3,5,2,0,3,2,1,5,3};
		Integer[] query= {3,2,1};
		//Set<Integer> qset=new HashSet<>();
		//qset.addAll(Arrays.asList(query));
		SubArray res=findSmallestSequentially(Arrays.asList(para),Arrays.asList(query));
		System.out.println(res.startWindow+" "+res.endWindow);
	}
	public static void main(String args[]) {
		//test2();
		test1();
	}
}
