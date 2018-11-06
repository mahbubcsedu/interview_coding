package leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

//it will have limited capacity and anything over the capacity will be evicted
class LRUCache2 {

	LinkedHashMap<Integer,Integer> cache;
    
	public LRUCache2(int capacity) {
        this.cache=new LinkedHashMap<Integer, Integer>(capacity,1.0f,true) 
        {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
        	protected boolean removeEldestEntry(Map.Entry<Integer, Integer> e) {
        		return this.size()>capacity;
        	}
        };
    }
    
    public int get(int key) {
    	if(!cache.containsKey(key)) {
    		return -1;
    	}else {
		return cache.get(key);
    	}
        
    }
    
    public void put(int key, int value) {
    	//This rules has to ask, whether the new one with existing key will update the value
    	//or not, if updated, then just insert
    	/*if(!cache.containsKey(key)) {
    		cache.put(key, value);
    	}*/
    	cache.put(key, value);//leetcode only requires to updated the vlaue
    }
    
    
    
    
    /**
     * Another implementation without using LinkedHashMap is given here with hasmp and double linkedlist
     * @param args
     */
    
    
    
    public static void main(String args[]) {
    	LRUCache2 obj = new LRUCache2(2);
    	
    	System.out.println("operations");
    	int param_1 = obj.get(1);
    	System.out.printf("Search result: %d",param_1);
    	obj.put(1,1);
    	//System.out.printf("Search result: %d",param_1);
    	param_1 = obj.get(1);
    	System.out.printf("Search result: %d",param_1);
    	obj.put(2,1);
    	obj.put(2,2);
    	//System.out.printf("Search result: %d",param_1);
    	param_1 = obj.get(2);
    	System.out.printf("Search result: %d",param_1);
    	obj.put(3,1);
    	//System.out.printf("Search result: %d",param_1);
    	param_1 = obj.get(2);
    	System.out.printf("Search result: %d",param_1);
    	obj.put(4,1);
    	//System.out.printf("Search result: %d",param_1);
    	param_1 = obj.get(1);
    	System.out.printf("Search result: %d",param_1);
    	obj.put(5,1);
    	//System.out.printf("Search result: %d",param_1);
    	//int param_1 = obj.get(1);
    	//int param_1 = obj.get(1);
    	//int param_1 = obj.get(1);
    	obj.put(2,2);
    }
}
