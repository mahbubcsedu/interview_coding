package company.walmart;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
	
// In Java a special LinkedHashMap is implemented to provde the function of  LRU Cache.
//it implements Hash with Double linkedlist and maintain the order of last accesssed order
LinkedHashMap<Integer, Integer> isbnPrice;

LRUCache(final int capacity){
	//LinkedHashMap capacity, loadfactor, true or accessorder, false for insert order
	this.isbnPrice=new LinkedHashMap<Integer, Integer>(capacity, 1.0f,true){
		@Override
		protected boolean removeEldestEntry(Map.Entry<Integer,Integer> e) {
			return this.size()>capacity;
		}
	};
}

public Integer lookup(Integer key) {
	if(!isbnPrice.containsKey(key)) {
		return null;
	}else
		return isbnPrice.get(key);
}

public Integer insert(Integer key, Integer value) {
	//we only add key if it is not present--we do not update key if it is already present
	Integer currentValue=isbnPrice.get(key);
	
	if(!isbnPrice.containsKey(key)) {
		isbnPrice.put(key, currentValue);
		return currentValue;
	}else
		{
		return null;
		}
}

public Integer erase(Object key) {return isbnPrice.remove(key);}

public static void main(String args[]) {
	final int CAPACITY = 2;
    LRUCache c = new LRUCache(CAPACITY);
    System.out.println("c.insert(1, 1)");
    c.insert(1, 1);
    System.out.println("c.insert(1, 10)");
    c.insert(1, 10);
    System.out.println("c.lookup(2, val)");
    assert(null == c.lookup(2));
    System.out.println("c.lookup(1, val)");
    assert(c.lookup(1) == 1);
    c.erase(1);
    assert(null == c.lookup(1));

    // test capacity constraints honored, also FIFO ordering
    c = new LRUCache(CAPACITY);
    c.insert(1, 1);
    c.insert(2, 1);
    c.insert(3, 1);
    c.insert(4, 1);
    assert(null == c.lookup(1));
    assert(null == c.lookup(2));
    assert(1 == c.lookup(3));
    assert(1 == c.lookup(4));

    // test retrieval moves to front
    c = new LRUCache(CAPACITY);
    c.insert(1, 1);
    c.insert(2, 1);
    c.insert(3, 1);
    c.lookup(2);
    c.insert(4, 1);
    assert(null == c.lookup(1));
    assert(1 == c.lookup(2));
    assert(null == c.lookup(3));
    assert(1 == c.lookup(4));

    // test update moves to front
    c = new LRUCache(CAPACITY);
    c.insert(1, 1);
    c.insert(2, 1);
    c.insert(3, 1);
    c.insert(2, 2);
    c.insert(4, 1);
    assert(null == c.lookup(1));
    assert(1 == c.lookup(2));
    assert(null == c.lookup(3));
    assert(1 == c.lookup(4));

    // test erase
    c = new LRUCache(CAPACITY);
    c.insert(1, 1);
    c.insert(2, 1);
    c.erase(2);
    c.insert(3, 3);
    assert(1 == c.lookup(1));
    assert(null == c.lookup(2));
    assert(3 == c.lookup(3));
}
}
