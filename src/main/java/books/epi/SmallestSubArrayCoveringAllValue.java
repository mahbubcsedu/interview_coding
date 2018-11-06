package books.epi;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class SmallestSubArrayCoveringAllValue {

	/**
	 * This problem in broad range could be a google search keyword, my parameount object in this struggle is to save the union, and is not 
	 * either to save or test destroy slavery. It could save the entire union ....
	 *
	 * 
	 * query strng "save to union"
	 * 
	 * we also can cosider the integer array q={1,5,7} and A={3, 2, 0, 1, 3,5,7,2,3,1,4,4,7,3,5,1,0
	 * @param paragraph
	 * @param query
	 * @return
	 */
	
	static class SubArray{
		int startWindow;
		int endWindow;
		SubArray(int s,int e){
			this.startWindow=s;
			this.endWindow=e;
		}
		
	}
	
	//this is not able to handle repeated keyword to query
	//if we want this, we have to keep track to the number of frequency as well
	public static SubArray findSmallestSubArrayCoveringAll(List<String> paragraph, Set<String> query) {
		
		SubArray window=new SubArray(-1,-1);
		
	    //store to each string of query to a hashmap to better search
		Map<String,Integer> keyToCover=new HashMap<>();
		
		for(String s:query) {
			keyToCover.put(s, keyToCover.getOrDefault(s,0)+1);
		}
		
		//we will iterate over all the strings of the paragraph starting from left
		int left=0,right=0;
		int numOfKeyToCover=query.size();
		
		for(left=0,right=0;right<paragraph.size();right++) {
			
			//we will keep checking for right pointer first
			
			String key=paragraph.get(right);
			//check the current string from paragraph and see whether this is one of the keyword or not
			if(keyToCover.containsKey(key)){
				keyToCover.put(key, keyToCover.get(key)-1);
				if(numOfKeyToCover>=0) {
					numOfKeyToCover--;//one key to cover reduce
				}
			}
			
			//probably we can remove some of the keyword in the left, 
			// if q={1,3} and p={1 2 1 3} then if we start from 0 and right to 3, then we can move left to 1 and still it will cover
			
			//if(numOfKeyToCover==0) 
			//{
			  while(numOfKeyToCover==0)
			    {//we will do the left shrinking
				 
				  //we will update the window boundary
				  if((window.startWindow==-1 && window.endWindow==-1) || (window.endWindow-window.startWindow > right-left)) 
				  {
					  window.startWindow=left;
					  window.endWindow=right;
				  }
				  key=paragraph.get(left);
				  
				  if(keyToCover.containsKey(key))
				  {
					  keyToCover.put(key, keyToCover.get(key)+1);
					  numOfKeyToCover++;
				  }
				
				 left++;
			    }
			//}
			
			
		}
		
		
		return window;
	}
	
	
public static SubArray findSmallestSubArrayCoveringAllDup(List<String> paragraph, Set<String> query) {
		
		SubArray window=new SubArray(-1,-1);
		
	    //store to each string of query to a hashmap to better search
		Map<String,Integer> keyToCover=new HashMap<>();
		
		for(String s:query) {
			keyToCover.put(s, keyToCover.getOrDefault(s,0)+1);
		}
		
		//we will iterate over all the strings of the paragraph starting from left
		int left=0,right=0;
		int numOfKeyToCover=query.size();
		
		for(left=0,right=0;right<paragraph.size();right++) {
			
			String key=paragraph.get(right);
			
			//keywordcount will track how many times the current key occurs in the query
			Integer keyWordCount=keyToCover.get(key);
			
			//we will keep checking for right pointer first
			
			
			//check the current string from paragraph and see whether this is one of the keyword or not
			
			if(keyWordCount!=null) {
				keyToCover.put(key, keyToCover.get(key)-1);
				//as one keyword occurs multiple times, we only track if keyword count is greater than or equal zero
				if(keyWordCount>=0) {
					numOfKeyToCover--;
				}
			}
			
			
			//probably we can remove some of the keyword in the left, 
			// if q={1,3} and p={1 2 1 3} then if we start from 0 and right to 3, then we can move left to 1 and still it will cover
			
			//if(numOfKeyToCover==0) 
			//{
			  while(numOfKeyToCover==0)
			    {//we will do the left shrinking
				 
				  //we will update the window boundary
				  if((window.startWindow==-1 && window.endWindow==-1) || (window.endWindow-window.startWindow > right-left)) 
				  {
					  window.startWindow=left;
					  window.endWindow=right;
				  }
				  key=paragraph.get(left);
				  
				  keyWordCount=keyToCover.get(key);
				  
				  if(keyWordCount!=null) {
					  keyToCover.put(key, keyToCover.get(key)+1);
					  if(keyWordCount > 0) {
						  numOfKeyToCover++;
					  }
				  }
				
				 left++;
			    }
			//}
			
			
		}
		
		
		return window;
	}
	


    private static Integer getValueOfFirstEntry(LinkedHashMap<String, Integer> m) {
    
    	/**
    	 * LinkedHashMap guarantees iteration over key value paris take places in the insertion order , most recent first
    	 * 
    	 */
    	
    	Integer result=null;
    	for(Map.Entry<String, Integer> entry: m.entrySet()) {
    		result=entry.getValue();
    		//should break after first entry to get the most recent one
    		break;
    	}
    	return result;
    }
    
    public static SubArray findSmallestSubArray(List<String> paragraph, Set<String> query) {
    	
    	SubArray result=new SubArray(-1,-1);
    	//we will maintain double linkedlist using LinkedHashMap of java standard library instead of implementing one
    	LinkedHashMap<String, Integer> dict=new LinkedHashMap<>();
    	
    	for(String s:query) {
    		dict.put(s, null);//first we insert null as first entry a.k.a it is not discovered yet
    	}
    	
    	int keywordSeen=0;
    	
    	//int currIndex=0;
    	
    	for(int left=0;left<paragraph.size();left++) {
    		String key=paragraph.get(left);
    		
    		if(dict.containsKey(key)) {
    			Integer it=dict.get(key);
    			
    			if(it==null) {
    				//this is discovered first time,
    				keywordSeen++;
    				
    			}
    			
    			//modify double linked list
    			dict.remove(key);
    			//we have to remove because if the key exists, the library does not move this to front
    			dict.put(key, left);
    		}
    		
    		
    		//now check if all keywords are covered
    		
    		if(keywordSeen == query.size()) {
    			if((result.startWindow==-1 && result.endWindow==-1) ||
    					(left-getValueOfFirstEntry(dict) <result.endWindow-result.startWindow)) {
    				result.endWindow=left;
    				result.startWindow=getValueOfFirstEntry(dict);
    			}
    			
    		}
    	}
    	return result;
    }
    
    public static void test1() {
    	String[] para= {"i","like","cooking","food","she","like","to","eat","food","he","like","food"};
		String[] query= {"like","food"};
		Set<String> qset=new HashSet<>();
		qset.addAll(Arrays.asList(query));
		SubArray res=findSmallestSubArrayCoveringAll(Arrays.asList(para),qset);
		System.out.println(res.startWindow+" "+res.endWindow);
    }
    
    public static void test2() {
    	String[] para= {"i","like","cooking","food","she","like","to","to","eat","food","he","like","food"};
		String[] query= {"like","to","to"};
		Set<String> qset=new HashSet<>();
		qset.addAll(Arrays.asList(query));
		SubArray res=findSmallestSubArrayCoveringAll(Arrays.asList(para),qset);
		System.out.println(res.startWindow+" "+res.endWindow);
    }
	public static void main(String args[]) {
		test2();
	}
}
