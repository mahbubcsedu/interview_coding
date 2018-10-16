package company.uber;
import java.util.*;
/**
 * Given a list of words and a number k, 
 * you have to find out the top k frequent word
 * @author mahbub
 *{@link https://leetcode.com/problems/top-k-frequent-words/description/}
 *Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
 */
public class TopKFrequentWord {

	/**
	 * The obvious solution is to find the frequency first using O(n) time and store to a new array or list 
	 * sort the list non-decreasing order
	 * return first k elements
	 * But sorting takes O(nlogn) time which dictates the cost
	 */
	/**
	 * 
	 * @return
	 */
	public static List<String> getTopKFrequentWords(String[] words, int k){
		
		Map<String, Integer> freq=new HashMap<>();
		
		for(int i=0;i<words.length;i++) {
		 freq.put(words[i], freq.getOrDefault(words[i], 0)+1);	
		}
		
		List<String> candidates=new ArrayList<>(freq.keySet());
		
		//Order will be changed if negative is return from compartor, will not change for positive
		//so, if two elements are (s1,3) (s2,4), it will swap, the opposite will not sort
		Collections.sort(candidates, 
				(w1,w2)->freq.get(w1).equals(freq.get(w2))? 
						w1.compareTo(w2):freq.get(w1)-freq.get(w2));
		
		//sorting will be in non-increasing order
		Collections.sort(candidates, 
				(w1,w2)->freq.get(w1).equals(freq.get(w2))? 
						w1.compareTo(w2):freq.get(w2)-freq.get(w1));
		
		//return candidates.subList(candidates.size()-k,candidates.size());
		return candidates.subList(0, k);
	}
	
	
	/**
	 * we can use priority queue to minimize the space cost as well as running cost limited to k from n
	 * @param args
	 */
	
public static List<String> getTopKFrequentWordsHeap(String[] words, int k){
		
		Map<String, Integer> freq=new HashMap<>();
		
		for(int i=0;i<words.length;i++) {
		 freq.put(words[i], freq.getOrDefault(words[i], 0)+1);	
		}
		//this needs to maxHeap and its natural order. The big one will be in the top or root
		PriorityQueue<String> count=new PriorityQueue<>((w1,w2)->freq.get(w1).equals(freq.get(w2)) ? w1.compareTo(w2) : freq.get(w1)-freq.get(w2));
		for(String e : freq.keySet()) {
			count.offer(e);
			if(count.size()>k)
				count.poll();
		}
		
		List<String> candidates=new ArrayList<>();
		while(!count.isEmpty())
			candidates.add(count.poll());
		
		 Collections.reverse(candidates);
		return candidates;
	}
	public static void main(String args[]) {
		String[] words=new String[] {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
		System.out.println(Arrays.deepToString(getTopKFrequentWords(words,2).toArray()));
		System.out.println(Arrays.deepToString(getTopKFrequentWordsHeap(words,2).toArray()));
	}
}
